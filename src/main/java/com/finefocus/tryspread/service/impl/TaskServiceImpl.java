package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.TaskDao;
import com.finefocus.tryspread.pojo.Task;
import com.finefocus.tryspread.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private TaskDao taskDao;

    public List<Task> getTasks() {
        return taskDao.getTasks();
    }

    public Task getTaskByTaskId(Integer taskId) {
        return taskDao.getTaskByTaskId(taskId);
    }
}
