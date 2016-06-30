package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.ApkBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/22
 * @Description: ${todo}
 */
@QfBatisRepository
public interface ApkDao {
    String getApkUrl(@Param("taskId") Integer taskId, @Param("newDate") Date newDate, @Param("hours") int hours, @Param("state") int state);

    ApkBean getApkUrlAndApkId(@Param("taskId") Integer taskId, @Param("newDate") Date newDate, @Param("hours") int hours, @Param("state") int state);

    Map<String, Object> getApkUrlAndApkIdToMap(@Param("taskId") Integer taskId, @Param("newDate") Date newDate, @Param("hours") int hours, @Param("state") int state);

    Integer getLimitedByApkId(@Param("apkId") Integer apkId);

    void setStateByApkId(@Param("apkId") Integer apkId);
}
