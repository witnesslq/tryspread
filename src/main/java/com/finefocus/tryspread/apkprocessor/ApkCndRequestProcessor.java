package com.finefocus.tryspread.apkprocessor;

import com.finefocus.tryspread.model.ExchangeInformation;
import com.finefocus.tryspread.pojo.ChannelPacUrlBean;
import com.finefocus.tryspread.processor.ExchangeAsynchronousTask;
import com.finefocus.tryspread.processor.ExchangeManager;
import com.finefocus.tryspread.service.ApkService;
import com.finefocus.tryspread.service.ChannelPacUrlService;
import com.finefocus.tryspread.service.ExchangeRecordService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.apkprocessor
 * @date 2016/7/14
 * @Description: ${todo}
 */
public class ApkCndRequestProcessor extends Thread {

    private ThreadPoolTaskExecutor executor;
    private ChannelPacUrlService channelPacUrlService;

    public ApkCndRequestProcessor(ThreadPoolTaskExecutor executor,
                                  ChannelPacUrlService channelPacUrlService) {
        super();
        this.executor = executor;
        this.channelPacUrlService = channelPacUrlService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ChannelPacUrlBean channelPacUrlBean = ApkCndManager.getRequest();
                if (channelPacUrlBean == null) {
                    continue;
                }
                if (channelPacUrlBean != null) {
                    executor.execute(new ApkCdnAsynchronousTask(channelPacUrlBean, channelPacUrlService));
                }

            } catch (Exception e) {
                continue;
            }
        }
    }
}
