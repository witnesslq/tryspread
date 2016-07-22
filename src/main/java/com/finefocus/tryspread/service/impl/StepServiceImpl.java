package com.finefocus.tryspread.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefocus.tryspread.common.JsonTool;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.dao.StepDao;
import com.finefocus.tryspread.pojo.StepBean;
import com.finefocus.tryspread.service.RedisService;
import com.finefocus.tryspread.service.StepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected Logger LOGGER = LoggerFactory.getLogger(StepServiceImpl.class);
    @Autowired
    private StepDao stepDao;
    @Autowired
    private RedisService redisService;

    public List<StepBean> getSteps(Integer taskId) {
        String redisGetStepByTaskId = RedisKeyProperties.getPropertyValue("redis_get_step_by_taskId");
        List<StepBean> stepList = null;
        try {
            String stepListRedis = redisService.get(redisGetStepByTaskId + "_" + taskId);
            if (stepListRedis == null) {
                stepList = stepDao.getSteps(taskId);//可加入redis
                redisService.set(redisGetStepByTaskId + "_" + taskId, OBJECT_MAPPER.writeValueAsString(stepList));
            }
            if (stepListRedis != null) {
                JavaType javaType = JsonTool.getCollectionType(ArrayList.class, StepBean.class);
                stepList = OBJECT_MAPPER.readValue(stepListRedis, javaType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据taskId从redis中获取stepList失败，taskId=" + taskId, e);
        }
        return stepList;
    }

    public StepBean getStepByTaskIdStepId(Integer taskId, Integer stepId) {
        return stepDao.getStepByTaskIdStepId(taskId, stepId);
    }

    public Date getTaskLogTaskTimeByUserIdAndTaskId(Integer userId, Integer taskId) {
        return stepDao.getTaskLogTaskTimeByUserIdAndTaskId(userId, taskId);
    }
}
