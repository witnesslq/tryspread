package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.service.APkResetStateJobService;
import com.finefocus.tryspread.service.ApkService;
import com.finefocus.tryspread.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/7/18
 * @Description: ${todo}
 */
@Service(value = "aPkResetStateJobService")
public class APkResetStateJobServiceImpl implements APkResetStateJobService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ApkService apkService;

    public void aPkResetState() {
        redisService.batchDelete(RedisKeyProperties.getPropertyValue("redis_apk_download_apkid"));
        apkService.setAllApkState();
    }
}
