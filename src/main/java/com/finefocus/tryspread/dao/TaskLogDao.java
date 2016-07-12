package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.TaskLogBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/15
 * @Description: ${todo}
 */
@QfBatisRepository
public interface TaskLogDao {
    /**
     * 根据用户id查询出以完成的任务
     *
     * @param userId 用户id
     * @return 已完成任务
     */
    List<TaskLogBean> getTaskLogByUserId(int userId);

    /**
     * 保存任务信息
     *
     * @param taskLog
     */
    void save(TaskLogBean taskLog);

    TaskLogBean getTaskLogByUserIdAndTaskIdStepId(@Param("userId") Integer userId, @Param("taskId") Integer taskId, @Param("stepId") Integer stepId);

    Integer getTaskLogByMap(Map<String, Object> hashMap);

    List<Integer> getTaskIdsByUserId(Integer userId);

    Integer getTaskLogByUserIdAndTaskIdAndState(@Param("userId") Integer userId, @Param("taskId") Integer taskId, @Param("state") int state);

    void updateStateByByUserIdAndTaskIdStepId(@Param("userId") Integer userId, @Param("taskId") Integer taskId, @Param("stepId") Integer stepId, @Param("state") int state);


    void createTask(@Param("userId") Integer userId, @Param("taskId") Integer taskId, @Param("stepId") Integer stepId, @Param("state") int state, @Param("date") Date date);

    void completeTask(@Param("userId") Integer userId, @Param("taskId") Integer taskId, @Param("stepId") Integer stepId, @Param("state") int state, @Param("taskTime") Date taskTime);

    List<Map<String, Object>> getUserIdAndIntegrationSumByDate(String date);
}
