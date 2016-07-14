package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.ApkDao;
import com.finefocus.tryspread.pojo.ApkBean;
import com.finefocus.tryspread.pojo.ChannelPacUrlBean;
import com.finefocus.tryspread.service.ApkService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/22
 * @Description: ${todo}
 */
@Service(value = "apkService")
public class ApkServiceImpl implements ApkService {
    @Autowired
    private ApkDao apkDao;

    public String getApkUrl(Integer taskId) {
        //查询taskId下的所有启用状态的apk
        Date newDate = new Date();
        int hours = newDate.getHours();
        String apkUrl = apkDao.getApkUrl(taskId, newDate, hours, 1);
        return apkUrl;
    }

    public ApkBean getApkUrlAndApkId(Integer taskId) {
        Date newDate = new Date();
        int hours = newDate.getHours();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = sdf.format(newDate);
//        try {
//            newDate = sdf.parse(format);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        return apkDao.getApkUrlAndApkId(taskId, newDate, hours, 1);
    }

    public Map<String, Object> getApkUrlAndApkIdToMap(Integer taskId) {
        Date newDate = new Date();
        int hours = newDate.getHours();
        return apkDao.getApkUrlAndApkIdToMap(taskId, newDate, hours, 1);
    }

    public Integer getLimitedByApkId(Integer apkId) {
        return apkDao.getLimitedByApkId(apkId);
    }

    public void setStateByApkId(Integer apkId) {
        apkDao.setStateByApkId(apkId);
    }

}
