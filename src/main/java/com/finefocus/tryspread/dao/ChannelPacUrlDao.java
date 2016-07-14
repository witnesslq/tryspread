package com.finefocus.tryspread.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/7/13
 * @Description: ${todo}
 */
@QfBatisRepository
public interface ChannelPacUrlDao {
    String getUrlByUserId(Integer userId);

    void saveDownUrlAndUserId(@Param("downUrl") String downUrl, @Param("userId") Integer userId, @Param("date") Date date);

    void UpdateDownUrlByUserId(@Param("downUrl") String downUrl, @Param("userId") Integer userId, @Param("date") Date date);
}
