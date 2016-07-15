package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.Task;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/15
 * @Description: ${todo}
 */
@QfBatisRepository
public interface TaskDao {

    /**
     * 获取全部任务（全部的app等）
     *
     * @return 全部任务
     */
    List<Task> getTasks();

    Task getTaskByTaskId(Integer taskId);

    String getTaskNameByTaskId(Integer taskId);
}
