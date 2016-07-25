package com.finefocus.tryspread.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.dao.ApkDao;
import com.finefocus.tryspread.pojo.ApkBean;
import com.finefocus.tryspread.service.ApkService;
import com.finefocus.tryspread.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected Logger LOGGER = LoggerFactory.getLogger(ApkServiceImpl.class);
    @Autowired
    private ApkDao apkDao;
    @Autowired
    private RedisService redisService;

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
        Map<String, Object> apkBeanMap = null;
        String redisApkIdAndDownurl = RedisKeyProperties.getPropertyValue("redis_apkid_down_taskId") + taskId;
        try {
            String s = redisService.get(redisApkIdAndDownurl);
            if (s == null) {
                Date newDate = new Date();
                int hours = newDate.getHours();
                apkBeanMap = apkDao.getApkUrlAndApkIdToMap(taskId, newDate, hours, 1);
                redisService.set(redisApkIdAndDownurl, OBJECT_MAPPER.writeValueAsString(apkBeanMap), 3600);
                return apkBeanMap;
            }
            if (s != null) {
//                JavaType javaType = JsonTool.getCollectionType(Map.class, StepBean.class);
                apkBeanMap = (Map<String, Object>) OBJECT_MAPPER.readValue(s, Map.class);
                return apkBeanMap;

            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("从redis中获取ApkId与ApkDownUrl失败~ taskId = " + taskId, e.getMessage());
        }
        return apkBeanMap;
    }

    public Integer getLimitedByApkId(Integer apkId) {
        return apkDao.getLimitedByApkId(apkId);
    }

    public void setStateByApkId(Integer apkId) {
        apkDao.setStateByApkId(apkId);
    }

    public void setAllApkState() {
        apkDao.setAllApkState();
    }

}
