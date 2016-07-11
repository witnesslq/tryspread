package com.finefocus.tryspread.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefocus.tryspread.common.DateTool;
import com.finefocus.tryspread.common.JsonTool;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.model.AcquisitionTask;
import com.finefocus.tryspread.model.CodeAndMsg;
import com.finefocus.tryspread.model.SynchronizationTask;
import com.finefocus.tryspread.pojo.*;
import com.finefocus.tryspread.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/16
 * @Description: ${todo}
 */
@Service(value = "getTaskService")
public class GetTaskServiceImpl implements GetTaskService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected Logger LOGGER = LoggerFactory.getLogger(GetTaskServiceImpl.class);
    @Autowired
    private IntegralService integralService;
    @Autowired
    private TaskLogService taskLogService;
    @Autowired
    private StepService stepService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private ApkService apkService;
    @Autowired
    private RedisService redisService;

    public Map<String, Object> getTask(Integer userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId == null) {
            map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
            map.put(CodeAndMsg.CODE, CodeAndMsg.PARAMETERERROR);
            map.put(CodeAndMsg.MSG, "用户id为空");
        }
        if (userId != null) {
            // 任务与用户建立关联 可加入redis
            List<Task> taskList = null;
            String redisGetTasks = RedisKeyProperties.getPropertyValue("redis_get_tasks");
            try {
                String taskListRedis = redisService.get(redisGetTasks);
                if (taskListRedis == null) {
                    taskList = taskService.getTasks();
                    redisService.set(redisGetTasks, OBJECT_MAPPER.writeValueAsString(taskList));
                }
                if (taskListRedis != null) {
                    JavaType javaType = JsonTool.getCollectionType(ArrayList.class, Task.class);
                    taskList = OBJECT_MAPPER.readValue(taskListRedis, javaType);
//                    taskList=  ( List<Task>) OBJECT_MAPPER.readValue(taskListRedis,javaType);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("从redis中获取全部任务列表失败！！" + e);
            }

            for (Task task : taskList) {
                TaskLogBean taskLog = new TaskLogBean();
                taskLog.setTaskId(task.getId());
                taskLog.setTaskName(task.getName());
                taskLog.setUserId(userId);
                String redisGetStepByTaskId = RedisKeyProperties.getPropertyValue("redis_get_step_by_taskId");
                List<StepBean> stepList = null;
                try {
                    String stepListRedis = redisService.get(redisGetStepByTaskId + "_" + task.getId());
                    if (stepListRedis == null) {
                        stepList = stepService.getSteps(task.getId());//可加入redis
                        redisService.set(redisGetStepByTaskId + "_" + task.getId(), OBJECT_MAPPER.writeValueAsString(stepList));
                    }
                    if (stepListRedis != null) {
                        JavaType javaType = JsonTool.getCollectionType(ArrayList.class, StepBean.class);
                        stepList = OBJECT_MAPPER.readValue(stepListRedis, javaType);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error("根据taskId从redis中获取stepList失败，taskId=" + task.getId(), e);
                }

                for (StepBean stepBean : stepList) {
                    taskLog.setStepId(stepBean.getStepId());
                    taskLog.setTaskIntegration(stepBean.getIntegral());
                    TaskLogBean taskLogBean = taskLogService.getTaskLogByUserIdAndTaskIdStepId(userId, task.getId(), stepBean.getStepId());
                    if (taskLogBean == null) {
                        taskLogService.save(taskLog);
                    }

                }
            }
            LOGGER.info("UserId : " + userId + "初始化任务完成！！");
            // 返回任务
            //根据userId查找到所有的task
            List<Integer> taskIds = taskLogService.getTaskIdsByUserId(userId);
            List<AcquisitionTask> list = new ArrayList<AcquisitionTask>();
            for (Integer taskId : taskIds) {
                //根据taskId获取到下面的所有满足条件的apk的url中的某一个
                Map<String, Object> apkBeanMap = apkService.getApkUrlAndApkIdToMap(taskId);
                //根据taskId查找task 与taskInfo 和step
                Task task = taskService.getTaskByTaskId(taskId);
                TaskInformationBean taskInformation = taskInfoService.getTaskInfoByTaskId(taskId);
                if (apkBeanMap != null) {
                    taskInformation.setApkId((Integer) apkBeanMap.get("id"));
                    taskInformation.setApkUrl((String) apkBeanMap.get("apk_url"));
                }

                List<StepBean> steps = stepService.getSteps(taskId);
                //根据用户id 与taskid  状态为正在做的 2 查找 stepid
                Integer stepId = taskLogService.getTaskLogByUserIdAndTaskId(userId, taskId, 2);
                AcquisitionTask acquisitionTask = new AcquisitionTask();
                taskInformation.setTaskId(taskId);
                taskInformation.setTaskName(task.getName());
                taskInformation.setTaskType(task.getType());
                acquisitionTask.setCurrentStep(stepId);
                acquisitionTask.setStep(steps);
                acquisitionTask.setTaskInformation(taskInformation);
                list.add(acquisitionTask);
            }
            LOGGER.info("UserId : " + userId + "获取任务完成！！");
            map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
            map.put(CodeAndMsg.CODE, CodeAndMsg.OK);
            map.put(CodeAndMsg.MSG, "返回结果");
            map.put(CodeAndMsg.DATA, list);

        }
        return map;
    }

    public Map<String, Object> syncTask(Integer userId, Integer taskId, Integer stepId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询任务是否创建
        TaskLogBean taskLog = taskLogService.getTaskLogByUserIdAndTaskIdStepId(userId, taskId, stepId);
        IntegralBean integralBean = integralService.getIntegralByUserId(userId);
        SynchronizationTask synchronizationTask = new SynchronizationTask();
        if (taskLog.getState() == 2) {
            //2代表任务正在进行
            Date taskCreatTime = taskLog.getTaskCreatTime();//任务创建时间
            //获取到任务需要执行的之间
            StepBean stepBean = stepService.getStepByTaskIdStepId(taskId, stepId);
            Integer useDays = stepBean.getUseDays();
            //获取第一个任务完成的时间
            Date taskTimeOk = stepService.getTaskLogTaskTimeByUserIdAndTaskId(userId, taskId);
            boolean comparisonDate = DateTool.comparisonDate(taskCreatTime, taskTimeOk, useDays);
            if (comparisonDate) {
                LOGGER.info("UserId : [" + userId + "]taskId :[ " + taskId + "]stepId :[" + stepId + " ]天数间隔够！");
                //天数间隔够了
                //当前时间- 任务创建时间是否满足分钟数要求
                boolean comparisonMin = DateTool.comparisonMin(taskCreatTime, new Date(), stepBean.getUseTime());
                if (comparisonMin) {
                    //分钟数也满足 完成任务
                    LOGGER.info("UserId : " + userId + "taskId " + taskId + "stepId " + stepId + " 分钟数间隔够！");
                    taskLogService.completeTask(userId, taskId, stepId, 1, new Date());
                    //修改积分
                    Integer integer = stepBean.getIntegral();
                    integralBean.setTotalIntegral(integralBean.getTotalIntegral() + integer);
                    integralBean.setCurrentIntegration(integralBean.getCurrentIntegration() + integer);
                    integralService.updateIntegralById(integralBean);

                    synchronizationTask.setIntegral(integralBean);
                    synchronizationTask.setTaskLog(taskLogService.getTaskLogByUserId(userId));
                    map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
                    map.put(CodeAndMsg.CODE, CodeAndMsg.OK);
                    map.put(CodeAndMsg.MSG, "任务执行完毕");
                    map.put(CodeAndMsg.DATA, synchronizationTask);
                    LOGGER.info("UserId : [" + userId + "]taskId : [" + taskId + "] stepId :[ " + stepId + "] 任务执行完毕！");
                    return map;
                } else {
                    synchronizationTask.setIntegral(integralBean);
                    synchronizationTask.setTaskLog(taskLogService.getTaskLogByUserId(userId));
                    map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
                    map.put(CodeAndMsg.CODE, CodeAndMsg.MinutesIsNotEnough);
                    map.put(CodeAndMsg.MSG, "分钟数不够");
                    map.put(CodeAndMsg.DATA, synchronizationTask);
                    LOGGER.info("UserId : [" + userId + "] taskId :[" + taskId + "] stepId :[ " + stepId + " ]分钟间隔不够！");
                    return map;
                }

            } else {
                synchronizationTask.setIntegral(integralBean);
                synchronizationTask.setTaskLog(taskLogService.getTaskLogByUserId(userId));
                map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
                map.put(CodeAndMsg.CODE, CodeAndMsg.DAYIsNotEnough);
                map.put(CodeAndMsg.MSG, "间隔天数不够");
                map.put(CodeAndMsg.DATA, synchronizationTask);
                LOGGER.info("UserId :[ " + userId + "] taskId :[" + taskId + "] stepId :[" + stepId + "] 间隔天数不够！");
                return map;
            }
        }
        if (taskLog.getState() == 0) {
            //创建任务
            taskLogService.createTask(userId, taskId, stepId, 2, new Date());
            //返回积分
            synchronizationTask.setIntegral(integralBean);
            synchronizationTask.setTaskLog(taskLogService.getTaskLogByUserId(userId));
            map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
            map.put(CodeAndMsg.CODE, CodeAndMsg.TaskCreateOk);
            map.put(CodeAndMsg.MSG, "任务创建完成");
            map.put(CodeAndMsg.DATA, synchronizationTask);
            LOGGER.info("UserId : [" + userId + "] taskId :[ " + taskId + "] stepId :[ " + stepId + "] 任务创建完成！！");
            return map;
        }
        if (taskLog.getState() == 1) {
            //任务已完成，返回积分
            //返回积分
            synchronizationTask.setIntegral(integralBean);
            synchronizationTask.setTaskLog(taskLogService.getTaskLogByUserId(userId));
            map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
            map.put(CodeAndMsg.CODE, CodeAndMsg.TaskIsOK);
            map.put(CodeAndMsg.MSG, "该任务已经完成");
            map.put(CodeAndMsg.DATA, synchronizationTask);
            LOGGER.info("UserId : [ " + userId + " ] taskId :[" + taskId + "]stepId :[" + stepId + "] 任务已经完成！！");
            return map;
        }

        return map;
    }

    public Map<String, Object> syncApk(Integer apkId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (apkId == null) {
            map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
            map.put(CodeAndMsg.CODE, CodeAndMsg.ApkIdIsNull);
            map.put(CodeAndMsg.MSG, "传递的apkId为空");
            return map;
        }
        if (apkId != null) {
//            amountOfDownloads 获取到的当前apk下载次数
            Integer amountOfDownloads = null;
            try {
                String s = redisService.get(String.valueOf(apkId));
                if (s != null) {
                    amountOfDownloads = Integer.parseInt(s);
                }

            } catch (Exception e) {
                LOGGER.error("redis 获取下载次数失败 apkId : " + apkId + e.getMessage());
            }
            if (amountOfDownloads == null) {
                try {
                    redisService.set(String.valueOf(apkId), "1", 3153600);
                } catch (Exception e) {
                    LOGGER.error("redis 放入计数器失败 apkId : " + apkId + "计数器值为： 1 ，第一次下载  ：" + e.getMessage());
                }
                map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
                map.put(CodeAndMsg.CODE, CodeAndMsg.FirstDownload);
                map.put(CodeAndMsg.MSG, "第一次下载");
                return map;
            }
            if (amountOfDownloads != null) {
                //获取最多能下载多少次
                Integer limited = apkService.getLimitedByApkId(apkId);
                if (amountOfDownloads >= limited) {
                    //下载超过或者等于限制将此条apk状态修改为弃用
                    apkService.setStateByApkId(apkId);
                    map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
                    map.put(CodeAndMsg.CODE, CodeAndMsg.DownloadOverLimit);
                    map.put(CodeAndMsg.MSG, "下载次数 >= apk限制次数,弃用该apk");
                    return map;
                }
                if (amountOfDownloads <= limited) {
                    try {
                        redisService.set(String.valueOf(apkId), String.valueOf(amountOfDownloads + 1), 3153600);
                    } catch (Exception e) {
                        LOGGER.error("redis 放入计数器失败 apkId : " + apkId + "计数器值 ：" + (amountOfDownloads + 1) + e.getMessage());
                    }
                    map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
                    map.put(CodeAndMsg.CODE, CodeAndMsg.IncreaseTheCounter);
                    map.put(CodeAndMsg.MSG, "下载次数未超过限制次数，累加下载次数计数器");
                    return map;
                }
            }
        }
        return map;
    }
}
