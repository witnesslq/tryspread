package com.finefocus.tryspread.processor;

import com.finefocus.tryspread.model.ExchangeInformation;
import com.finefocus.tryspread.service.ExchangeRecordService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.processor
 * @date 2016/7/4
 * @Description: ${todo}
 */
public class ExchangeRequestProcessor extends Thread {
    private ThreadPoolTaskExecutor executor;
    private ExchangeRecordService exchangeRecordService;

    public ExchangeRequestProcessor(ThreadPoolTaskExecutor executor,
                                    ExchangeRecordService exchangeRecordService) {
        super();
        this.executor = executor;
        this.exchangeRecordService = exchangeRecordService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ExchangeInformation exchangeInformation = ExchangeManager.getRequest();
                if (exchangeInformation == null) {
                    continue;
                }
                if (exchangeInformation != null) {
                    executor.execute(new ExchangeAsynchronousTask(exchangeInformation, exchangeRecordService));
                }

            } catch (Exception e) {
                continue;
            }
        }
    }
}
