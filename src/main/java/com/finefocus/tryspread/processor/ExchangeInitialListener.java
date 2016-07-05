package com.finefocus.tryspread.processor;

import com.finefocus.tryspread.service.ExchangeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.processor
 * @date 2016/7/4
 * @Description: ${todo}
 */
@Component
public class ExchangeInitialListener implements ApplicationListener<ContextRefreshedEvent> {
    protected Logger logger = LoggerFactory.getLogger(ExchangeInitialListener.class);
    @Autowired
    private ExchangeRecordService exchangeRecordService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //启动CA申请异步执行
        if (ExchangeManager.getRequestProcessor() == null) {
            ExchangeManager.setRequestProcessor(new ExchangeRequestProcessor(threadPoolTaskExecutor, exchangeRecordService));
            ExchangeManager.startProcessor();
        }

    }
}
