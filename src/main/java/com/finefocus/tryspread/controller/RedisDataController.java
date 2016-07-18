package com.finefocus.tryspread.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.pojo.ExchangeProductsBean;
import com.finefocus.tryspread.pojo.StepBean;
import com.finefocus.tryspread.pojo.Task;
import com.finefocus.tryspread.service.ExchangeProductsService;
import com.finefocus.tryspread.service.RedisService;
import com.finefocus.tryspread.service.StepService;
import com.finefocus.tryspread.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.controller
 * @date 2016/7/18
 * @Description: ${todo}
 */
@RestController
public class RedisDataController {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected Logger LOGGER = LoggerFactory.getLogger(RedisDataController.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private StepService stepService;
    @Autowired
    private ExchangeProductsService exchangeProductsService;
    private String redisGetTasks = RedisKeyProperties.getPropertyValue("redis_get_tasks");
    private String redisGetStepByTaskId = RedisKeyProperties.getPropertyValue("redis_get_step_by_taskId");
    private String redisExchangeProducts = RedisKeyProperties.getPropertyValue("redis_exchange_products");

    @RequestMapping(name = "redisRefreshData", method = RequestMethod.GET)
    public void redisRefreshData() {
        //刷新redis中的数据
        List<Task> tasks = taskService.getTasks();
        try {
            redisService.set(redisGetTasks, OBJECT_MAPPER.writeValueAsString(tasks));
        } catch (Exception e) {
            LOGGER.error("刷新任务列表失败~" + e.getMessage());
        }
        for (Task task : tasks) {
            List<StepBean> steps = stepService.getSteps(task.getId());
            try {
                redisService.set(redisGetStepByTaskId + "_" + task.getId(), OBJECT_MAPPER.writeValueAsString(steps));
            } catch (Exception e) {
                LOGGER.error("刷新步骤列表失败~" + e.getMessage());

            }
        }
        //刷新兑换商品列表
        List<ExchangeProductsBean> exchangeProducts = exchangeProductsService.getExchangeProducts();
        try {
            redisService.set(redisExchangeProducts, OBJECT_MAPPER.writeValueAsString(exchangeProducts));
        } catch (Exception e) {
            LOGGER.error("刷新兑换产品列表失败~" + e.getMessage());
        }
    }
}
