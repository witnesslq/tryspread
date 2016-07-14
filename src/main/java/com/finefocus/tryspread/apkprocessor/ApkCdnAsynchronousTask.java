package com.finefocus.tryspread.apkprocessor;

import com.finefocus.tryspread.pojo.ChannelPacUrlBean;
import com.finefocus.tryspread.service.ApkService;
import com.finefocus.tryspread.service.ChannelPacUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.apkprocessor
 * @date 2016/7/14
 * @Description: ${todo}
 */
public class ApkCdnAsynchronousTask implements Runnable {


    protected Logger logger = LoggerFactory.getLogger(ApkCdnAsynchronousTask.class);

    private ChannelPacUrlBean channelPacUrlBean;
    private ChannelPacUrlService ChannelPacUrlService;

    public ApkCdnAsynchronousTask(ChannelPacUrlBean channelPacUrlBean,
                                  ChannelPacUrlService ChannelPacUrlService) {
        super();
        this.channelPacUrlBean = channelPacUrlBean;
        this.ChannelPacUrlService = ChannelPacUrlService;
    }

    public void run() {
        System.out.println("异步处理中~~~~~");
        ChannelPacUrlService.UploadApkToCdn(channelPacUrlBean);

    }
}
