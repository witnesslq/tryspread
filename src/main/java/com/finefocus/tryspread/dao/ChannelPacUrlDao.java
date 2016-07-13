package com.finefocus.tryspread.dao;

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
}
