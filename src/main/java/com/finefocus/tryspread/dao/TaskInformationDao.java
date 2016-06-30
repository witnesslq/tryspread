package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.TaskInformationBean;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/16
 * @Description: ${todo}
 */
@QfBatisRepository
public interface TaskInformationDao {
    TaskInformationBean getTaskInfoByTaskId(Integer taskId);
}
