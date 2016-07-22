package com.finefocus.tryspread.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.dao.TaskInformationDao;
import com.finefocus.tryspread.pojo.TaskInformationBean;
import com.finefocus.tryspread.service.RedisService;
import com.finefocus.tryspread.service.TaskInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/16
 * @Description: ${todo}
 */
@Service(value = "taskInfoService")
public class TaskInfoServiceImpl implements TaskInfoService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected Logger LOGGER = LoggerFactory.getLogger(TaskInfoServiceImpl.class);
    @Autowired
    private TaskInformationDao taskInformationDao;
    @Autowired
    private RedisService redisService;

    public TaskInformationBean getTaskInfoByTaskId(Integer taskId) {

        String redisTaskInfoTaskId = RedisKeyProperties.getPropertyValue("redis_taskInfo_taskId") + taskId;
        TaskInformationBean taskInformationBean = null;
        try {
            String taskInfoTaskId = redisService.get(redisTaskInfoTaskId);

            if (taskInfoTaskId == null) {
                taskInformationBean = taskInformationDao.getTaskInfoByTaskId(taskId);
                redisService.set(redisTaskInfoTaskId, OBJECT_MAPPER.writeValueAsString(taskInformationBean));
                return taskInformationBean;
            }
            if (taskInfoTaskId != null) {
                taskInformationBean = OBJECT_MAPPER.readValue(taskInfoTaskId, TaskInformationBean.class);
                return taskInformationBean;
            }
        } catch (Exception e) {
            e.printStackTrace();

            LOGGER.error("从redis中获取taskInformationBean失败~taskId = " + taskId, e.getMessage());
        }
        return taskInformationBean;
    }
}
