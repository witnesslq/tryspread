package com.finefocus.tryspread.common;

import java.util.ResourceBundle;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/7/1
 * @Description: ${todo}
 */
public class RedisKeyProperties {

    public final static ResourceBundle rb = ResourceBundle.getBundle("redisKey");

    public static String getPropertyValue(String key) {
        return rb.getString(key);
    }
}
