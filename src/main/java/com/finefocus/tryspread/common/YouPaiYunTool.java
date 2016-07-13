package com.finefocus.tryspread.common;

import main.java.com.UpYun;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/7/13
 * @Description: ${todo}
 */
public class YouPaiYunTool {
    public static UpYun getUpyun() {
        UpYun upyun = new UpYun(RedisKeyProperties.getPropertyValue("upyun_service"), RedisKeyProperties.getPropertyValue("upyun_username"), RedisKeyProperties.getPropertyValue("upyun_password"));
        upyun.setDebug(true);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);
        return upyun;

    }
}
