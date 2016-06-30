package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.Task;

import java.util.List;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/15
 * @Description: ${todo}
 */
public interface TaskService {

    List<Task> getTasks();

    Task getTaskByTaskId(Integer taskId);

}
