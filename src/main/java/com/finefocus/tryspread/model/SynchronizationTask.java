package com.finefocus.tryspread.model;

import com.finefocus.tryspread.pojo.IntegralBean;
import com.finefocus.tryspread.pojo.TaskLogBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.pojo
 * @date 2016/6/14
 * @Description: 同步任务状态返回结果
 */
public class SynchronizationTask {

    /**
     * totalIntegral : 90000
     * currentIntegration : 1000
     */

    private IntegralBean integral;
    /**
     * taskName : 京东
     * taskTime : 2016年6月13日 16:25:10
     * taskIntegration : 2000
     */

    private List<TaskLogBean> taskLog;

    public IntegralBean getIntegral() {
        return integral;
    }

    public void setIntegral(IntegralBean integral) {
        this.integral = integral;
    }

    public List<TaskLogBean> getTaskLog() {
        return taskLog;
    }

    public void setTaskLog(List<TaskLogBean> taskLog) {
        this.taskLog = taskLog;
    }

}
