package com.finefocus.tryspread.service;


import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/16
 * @Description: ${todo}
 */
public interface GetTaskService {
    Map<String, Object> getTask(Integer userId, Integer taskId, Integer num);

    Map<String, Object> syncTask(Integer userId, Integer taskId, Integer stepId);

    Map<String, Object> syncApk(Integer apkId);
}
