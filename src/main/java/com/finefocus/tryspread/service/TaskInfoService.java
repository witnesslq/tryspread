package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.TaskInformationBean;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/16
 * @Description: ${todo}
 */
public interface TaskInfoService {
    TaskInformationBean getTaskInfoByTaskId(Integer taskId);
}
