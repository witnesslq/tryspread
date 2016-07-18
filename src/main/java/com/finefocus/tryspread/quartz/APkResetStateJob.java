package com.finefocus.tryspread.quartz;

import com.finefocus.tryspread.service.APkResetStateJobService;
import com.finefocus.tryspread.service.IntegrationJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.quartz
 * @date 2016/7/18
 * @Description: ${todo}
 */
public class APkResetStateJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("APkResetStateJob 任务开始执行");
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
        applicationContext.getBean(APkResetStateJobService.class).aPkResetState();
    }
}
