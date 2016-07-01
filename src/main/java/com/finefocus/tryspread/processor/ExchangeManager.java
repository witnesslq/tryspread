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
    private static Map<Integer, ExchangeInformation> toBeProcessMap = new ConcurrentHashMap<Integer, ExchangeInformation>();
    private static BlockingQueue<Integer> requestSerialQueue = new LinkedBlockingQueue<Integer>();
    private static Thread requestProcessor;


    /**
     * 添加请求
     *
     * @param
     * @throws Exception
     */
    public static void addRequest(ExchangeInformation exchangeInformation) throws Exception {
        toBeProcessMap.put(exchangeInformation.getUserId(), exchangeInformation);
        requestSerialQueue.put(exchangeInformation.getUserId());
    }
}
