package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.StepBean;

import java.util.Date;
import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/15
 * @Description: ${todo}
 */

public interface StepService {
    List<StepBean> getSteps(Integer taskId);

    StepBean getStepByTaskIdStepId(Integer taskId, Integer stepId);

    Date getTaskLogTaskTimeByUserIdAndTaskId(Integer userId, Integer taskId);
}
