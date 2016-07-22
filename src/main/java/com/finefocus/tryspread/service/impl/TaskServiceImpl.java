package com.finefocus.tryspread.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefocus.tryspread.common.JsonTool;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.dao.TaskDao;
import com.finefocus.tryspread.pojo.Task;
import com.finefocus.tryspread.service.RedisService;
import com.finefocus.tryspread.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private RedisService redisService;

    public List<Task> getTasks() {
        String redisGetTasks = RedisKeyProperties.getPropertyValue("redis_get_tasks");
        List<Task> taskList = null;
        try {
            String taskListRedis = redisService.get(redisGetTasks);
            if (StringUtils.isEmpty(taskListRedis)) {
                taskList = taskDao.getTasks();
                redisService.set(redisGetTasks, OBJECT_MAPPER.writeValueAsString(taskList));
                return taskList;
            }
            if (StringUtils.isNotEmpty(taskListRedis)) {
                JavaType javaType = JsonTool.getCollectionType(ArrayList.class, Task.class);
                taskList = OBJECT_MAPPER.readValue(taskListRedis, javaType);
                return taskList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("从redis中获取全部任务列表失败！！" + e);
        }
        return taskList;
    }

    public Task getTaskByTaskId(Integer taskId) {
        return taskDao.getTaskByTaskId(taskId);
    }
}
