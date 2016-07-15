package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.TaskLogBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/15
 * @Description: ${todo}
 */
public interface TaskLogService {
    List<TaskLogBean> getTaskLogByUserId(int userId);

    void save(TaskLogBean taskLog);

    TaskLogBean getTaskLogByUserIdAndTaskIdStepId(Integer userId, Integer id, Integer stepId);

    Integer getTaskLogMap(Map<String, Object> hashMap);

    List<Integer> getTaskIdsByUserId(Integer userId);

    Integer getTaskLogByUserIdAndTaskId(Integer userId, Integer taskId, int state);

    void updateStateByByUserIdAndTaskIdStepId(Integer userId, Integer taskId, Integer stepId, int state);

    void createTask(Integer userId, Integer taskId, Integer stepId, int state, Date date);

    void completeTask(Integer userId, Integer taskId, Integer stepId, int state, Date taskTime);

    List<Map<String, Object>> getUserIdAndIntegrationSumByDate(String date);

    void createNewTask(Integer userId, Integer taskId, Integer stepId);
}
