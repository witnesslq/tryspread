package com.finefocus.tryspread.controller;

import com.finefocus.tryspread.service.GetTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.controller
 * @date 2016/6/15
 * @Description: ${todo}
 */
@RestController
public class TaskController {

    @Autowired
    private GetTaskService getTaskService;

    @RequestMapping(value = "getTask/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTask(@PathVariable Integer userId) {

        Map<String, Object> map = getTaskService.getTask(userId);
        return map;

    }

    @RequestMapping(value = "syncTask/{userId}/{taskId}/{stepId}")
    public Map<String, Object> syncTask(@PathVariable Integer userId, @PathVariable Integer taskId, @PathVariable Integer stepId) {
        Map<String, Object> map = getTaskService.syncTask(userId, taskId, stepId);
        return map;

    }

    @RequestMapping(value = "syncApk/{apkId}")
    public Map<String, Object> syncApk(@PathVariable Integer apkId) {
        Map<String, Object> map = getTaskService.syncApk(apkId);
        return map;

    }

}
