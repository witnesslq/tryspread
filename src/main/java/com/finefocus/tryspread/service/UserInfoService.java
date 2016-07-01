package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.UserInfoBean;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/7/1
 * @Description: ${todo}
 */
public interface UserInfoService {
    Integer getUserInfoByUserId(Integer integer);

    void saveUserInfo(UserInfoBean userInfoBean);

    void updateUserInfoById(UserInfoBean userInfoBean);
}
