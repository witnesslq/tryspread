package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.StepDao;
import com.finefocus.tryspread.pojo.StepBean;
import com.finefocus.tryspread.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "stepService")
public class StepServiceImpl implements StepService {
    @Autowired
    private StepDao stepDao;

    public List<StepBean> getSteps(Integer taskId) {
        return stepDao.getSteps(taskId);
    }

    public StepBean getStepByTaskIdStepId(Integer taskId, Integer stepId) {
        return stepDao.getStepByTaskIdStepId(taskId, stepId);
    }

    public Date getTaskLogTaskTimeByUserIdAndTaskId(Integer userId, Integer taskId) {
        return stepDao.getTaskLogTaskTimeByUserIdAndTaskId(userId, taskId);
    }
}
