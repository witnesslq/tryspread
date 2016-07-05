package com.finefocus.tryspread.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.model.CodeAndMsg;
import com.finefocus.tryspread.model.ExchangeInformation;
import com.finefocus.tryspread.pojo.ExchangeProductsBean;
import com.finefocus.tryspread.pojo.ExchangeRecordBean;
import com.finefocus.tryspread.pojo.IntegralBean;
import com.finefocus.tryspread.processor.ExchangeManager;
import com.finefocus.tryspread.service.ExchangeProductsService;
import com.finefocus.tryspread.service.ExchangeRecordService;
import com.finefocus.tryspread.service.IntegralService;
import com.finefocus.tryspread.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.controller
 * @date 2016/7/1
 * @Description: 兑换商品相关controller
 */
@RestController
public class ExchangeProductsController {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected Logger LOGGER = LoggerFactory.getLogger(ExchangeProductsController.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private ExchangeProductsService exchangeProductsService;
    @Autowired
    private ExchangeRecordService exchangeRecordService;

    @RequestMapping(value = "getExchangeProducts", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getExchangeProducts() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String exchangeProductsString = redisService.get(RedisKeyProperties.getPropertyValue("redis_exchange_products"));
            if (exchangeProductsString == null) {
                List<ExchangeProductsBean> exchangeProducts = exchangeProductsService.getExchangeProducts();
                if (exchangeProducts.isEmpty()) {
                    map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
                    map.put(CodeAndMsg.CODE, CodeAndMsg.ExchangeListingIsNull);
                    map.put(CodeAndMsg.DATA, exchangeProducts);
                    return map;
                }
                if (exchangeProducts != null) {
                    map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
                    map.put(CodeAndMsg.CODE, CodeAndMsg.ExchangeListingIsOk);
                    map.put(CodeAndMsg.DATA, exchangeProducts);
//                    将兑换列表放入缓存
                    try {
                        redisService.set(RedisKeyProperties.getPropertyValue("redis_exchange_products"), OBJECT_MAPPER.writeValueAsString(exchangeProducts));
                    } catch (Exception e) {
                        LOGGER.error("可兑换商品列表放入redis中失败！！！", e.getMessage());
                    }
                    return map;
                }
            } else {
                List<ExchangeProductsBean> exchangeProducts = OBJECT_MAPPER.readValue(exchangeProductsString, List.class);
                map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
                map.put(CodeAndMsg.CODE, CodeAndMsg.ExchangeListingIsOk);
                map.put(CodeAndMsg.DATA, exchangeProducts);
                return map;
            }
        } catch (Exception e) {
            LOGGER.error("可兑换商品列表从redis中获取失败！！！", e.getMessage());
        }

        return map;
    }

    @RequestMapping(value = "exchange", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> exchange(ExchangeInformation exchangeInformation) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        /**
         *
         *   1。1 根据用户id 查询出当前用户所拥有的积分
         *   1.2  根据兑换产品的id 查询出兑换产品所需要积分的比率
         *   1.3 用户当前积分-用户兑换所需要的积分>=0 扣除积分 并放入队列 返回给客户端提示成功
         *       如果不够  直接返回给客户端提示失败
         *
         */
        IntegralBean integralBean = integralService.getIntegralByUserId(exchangeInformation.getUserId());
        Integer integration = integralBean.getCurrentIntegration();//当前积分
        Integer ratio = exchangeProductsService.getRatioById(exchangeInformation.getExchangeProductsId());
        Integer requiredIntegration = ratio * exchangeInformation.getExchangeNum();
        if (integration < requiredIntegration) {
            //积分不够
            map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
            map.put(CodeAndMsg.CODE, CodeAndMsg.EnoughPoints);
            map.put(CodeAndMsg.MSG, "所拥有的积分不够");
            return map;

        }
        if (integration >= requiredIntegration) {
            int i = integration - requiredIntegration;
            //扣除积分 放入队列
            integralService.updateCurrentIntegrationByUserId(i, exchangeInformation.getUserId());
            map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
            map.put(CodeAndMsg.CODE, CodeAndMsg.PointsAreDeducted);
            map.put(CodeAndMsg.MSG, "积分已扣除");
            //放入队列
            exchangeInformation.setId(UUID.randomUUID().toString());
            try {
                ExchangeManager.addRequest(exchangeInformation);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("异步处理用户兑换，添加到队列失败！", e);
            }

            return map;

        }
        return map;
    }

}
