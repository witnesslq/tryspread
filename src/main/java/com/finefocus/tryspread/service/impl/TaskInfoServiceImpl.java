package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.TaskInformationDao;
import com.finefocus.tryspread.pojo.TaskInformationBean;
import com.finefocus.tryspread.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/16
 * @Description: ${todo}
 */
@Service(value = "taskInfoService")
public class TaskInfoServiceImpl implements TaskInfoService {
    @Autowired
    private TaskInformationDao taskInformationDao;

    public TaskInformationBean getTaskInfoByTaskId(Integer taskId) {
        return taskInformationDao.getTaskInfoByTaskId(taskId);
    }
}
