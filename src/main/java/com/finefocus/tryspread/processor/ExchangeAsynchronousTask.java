package com.finefocus.tryspread.processor;

import com.finefocus.tryspread.model.ExchangeInformation;
import com.finefocus.tryspread.service.ExchangeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.processor
 * @date 2016/7/4
 * @Description: ${todo}
 */
public class ExchangeAsynchronousTask implements Runnable {
    protected Logger logger = LoggerFactory.getLogger(ExchangeAsynchronousTask.class);

    private ExchangeInformation exchangeInformation;
    private ExchangeRecordService exchangeRecordService;

    public ExchangeAsynchronousTask(ExchangeInformation exchangeInformation,
                                    ExchangeRecordService exchangeRecordService) {
        super();
        this.exchangeInformation = exchangeInformation;
        this.exchangeRecordService = exchangeRecordService;
    }

    public void run() {
        System.out.println("异步处理中~~~~~");
        exchangeRecordService.exchange(exchangeInformation);

    }
}
