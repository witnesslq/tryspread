package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.TaskLogDao;
import com.finefocus.tryspread.pojo.TaskLogBean;
import com.finefocus.tryspread.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "taskLogService")
public class TaskLogServiceimpl implements TaskLogService {
    @Autowired
    private TaskLogDao taskLogDao;

    public List<TaskLogBean> getTaskLogByUserId(int userId) {
        return taskLogDao.getTaskLogByUserId(userId);
    }

    public void save(TaskLogBean taskLog) {
        taskLogDao.save(taskLog);
    }

    public TaskLogBean getTaskLogByUserIdAndTaskIdStepId(Integer userId, Integer id, Integer stepId) {
        return taskLogDao.getTaskLogByUserIdAndTaskIdStepId(userId, id, stepId);
    }

    public Integer getTaskLogMap(Map<String, Object> hashMap) {
        Integer taskLogId = taskLogDao.getTaskLogByMap(hashMap);
        if (taskLogId == null) {
            taskLogId = 0;
        }
        return taskLogId;
    }

    public List<Integer> getTaskIdsByUserId(Integer userId) {
        return taskLogDao.getTaskIdsByUserId(userId);
    }

    public Integer getTaskLogByUserIdAndTaskId(Integer userId, Integer taskId, int state) {
        return taskLogDao.getTaskLogByUserIdAndTaskIdAndState(userId, taskId, state);
    }

    public void updateStateByByUserIdAndTaskIdStepId(Integer userId, Integer taskId, Integer stepId, int state) {
        taskLogDao.updateStateByByUserIdAndTaskIdStepId(userId, taskId, stepId, state);
    }

    public void createTask(Integer userId, Integer taskId, Integer stepId, int state, Date date) {
        taskLogDao.createTask(userId, taskId, stepId, state, date);
    }

    public void completeTask(Integer userId, Integer taskId, Integer stepId, int state, Date taskTime) {
        taskLogDao.completeTask(userId, taskId, stepId, state, taskTime);
    }

    public List<Map<String, Object>> getUserIdAndIntegrationSumByDate(String date) {

        return taskLogDao.getUserIdAndIntegrationSumByDate(date);
    }
}
