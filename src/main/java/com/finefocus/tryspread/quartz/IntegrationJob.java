package com.finefocus.tryspread.quartz;

import com.finefocus.tryspread.service.IntegrationJobService;
import com.finefocus.tryspread.service.impl.IntegrationJobServiceImpl;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.core.QuartzScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.quartz
 * @date 2016/7/12
 * @Description: ${todo}
 */
public class IntegrationJob extends QuartzJobBean {
    private int count = 1;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("IntegrationJob 开始执行 ！！,执行次数 : " + (count++));
        //获取spring容器  通过spring容器拿到bean 执行其中的方法
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new DateTime().minusDays(1).toDate()) + "%";
//        String date="2016-07-01%";
        applicationContext.getBean(IntegrationJobService.class).addGeneralizedIntegral(date);
    }
}
