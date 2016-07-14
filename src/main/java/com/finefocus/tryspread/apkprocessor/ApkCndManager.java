package com.finefocus.tryspread.apkprocessor;

import com.finefocus.tryspread.pojo.ChannelPacUrlBean;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.apkprocessor
 * @date 2016/7/14
 * @Description: ${todo}
 */
public class ApkCndManager {
    private static Map<Integer, ChannelPacUrlBean> toBeProcessMap = new ConcurrentHashMap<Integer, ChannelPacUrlBean>();
    private static BlockingQueue<Integer> requestSerialQueue = new LinkedBlockingQueue<Integer>();
    private static Thread requestProcessor;

    /**
     * 添加请求
     *
     * @param
     * @throws Exception
     */
    public static void addRequest(ChannelPacUrlBean channelPacUrlBean) throws Exception {
        toBeProcessMap.put(channelPacUrlBean.getUserId(), channelPacUrlBean);
        requestSerialQueue.put(channelPacUrlBean.getUserId());
    }

    /**
     * 从待处理队列获取请求
     *
     * @return
     * @throws Exception
     */
    public static ChannelPacUrlBean getRequest() throws Exception {
        Integer userId = requestSerialQueue.take();
        ChannelPacUrlBean channelPacUrlBean = toBeProcessMap.get(userId);
        toBeProcessMap.remove(userId);
        channelPacUrlBean.setUserId(userId);
        return channelPacUrlBean;
    }

    public static Thread getRequestProcessor() {
        return requestProcessor;
    }

    public static void setRequestProcessor(Thread requestProcessor) {
        ApkCndManager.requestProcessor = requestProcessor;
    }

    public static void startProcessor() {
        if (requestProcessor != null) {
            requestProcessor.start();
        }
    }
}
