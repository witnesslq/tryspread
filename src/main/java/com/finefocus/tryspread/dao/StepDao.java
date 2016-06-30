package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.StepBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/15
 * @Description: 操作步骤表的dao
 */
@QfBatisRepository
public interface StepDao {
    /**
     * 根据任务id查询当前任务下的所有步骤
     *
     * @param taskId 任务id
     * @return 所有步骤
     */
    List<StepBean> getSteps(Integer taskId);

    StepBean getStepByTaskIdStepId(@Param("taskId") Integer taskId, @Param("stepId") Integer stepId);

    Date getTaskLogTaskTimeByUserIdAndTaskId(@Param("userId") Integer userId, @Param("taskId") Integer taskId);
}
