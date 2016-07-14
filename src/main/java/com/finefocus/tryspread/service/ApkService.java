package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.ApkBean;
import com.finefocus.tryspread.pojo.ChannelPacUrlBean;

import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/22
 * @Description: ${todo}
 */
public interface ApkService {
    String getApkUrl(Integer taskId);

    ApkBean getApkUrlAndApkId(Integer taskId);

    Map<String, Object> getApkUrlAndApkIdToMap(Integer taskId);

    Integer getLimitedByApkId(Integer apkId);

    void setStateByApkId(Integer apkId);

}
