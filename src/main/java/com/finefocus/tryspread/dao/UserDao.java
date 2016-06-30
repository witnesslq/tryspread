package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.UserBean;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/15
 * @Description: ${todo}
 */
@QfBatisRepository
public interface UserDao {
    /**
     * 查询用户id
     *
     * @param imei 手机唯一标识码
     * @return
     */
    Integer getUserIdByImei(String imei);

    /**
     * 保存用户信息
     *
     * @param userBean
     */
    void saveUser(UserBean userBean);
}
