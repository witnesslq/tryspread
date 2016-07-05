package com.finefocus.tryspread.processor;

import com.finefocus.tryspread.model.ExchangeInformation;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.processor
 * @date 2016/7/1
 * @Description: ${todo}
 */
public class ExchangeManager {
    private static Map<String, ExchangeInformation> toBeProcessMap = new ConcurrentHashMap<String, ExchangeInformation>();
    private static BlockingQueue<String> requestSerialQueue = new LinkedBlockingQueue<String>();
    private static Thread requestProcessor;


    /**
     * 添加请求
     *
     * @param
     * @throws Exception
     */
    public static void addRequest(ExchangeInformation exchangeInformation) throws Exception {
        toBeProcessMap.put(exchangeInformation.getId(), exchangeInformation);
        requestSerialQueue.put(exchangeInformation.getId());
    }

    /**
     * 从待处理队列获取请求
     *
     * @return
     * @throws Exception
     */
    public static ExchangeInformation getRequest() throws Exception {
        String id = requestSerialQueue.take();
        ExchangeInformation exchangeInformation = toBeProcessMap.get(id);
        toBeProcessMap.remove(id);
        return exchangeInformation;
    }

    public static Thread getRequestProcessor() {
        return requestProcessor;
    }

    public static void setRequestProcessor(Thread requestProcessor) {
        ExchangeManager.requestProcessor = requestProcessor;
    }

    public static void startProcessor() {
        if (requestProcessor != null) {
            requestProcessor.start();
        }
    }
}
