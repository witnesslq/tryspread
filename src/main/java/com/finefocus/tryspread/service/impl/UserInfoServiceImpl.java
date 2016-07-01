package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.UserInfoDao;
import com.finefocus.tryspread.pojo.UserInfoBean;
import com.finefocus.tryspread.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/7/1
 * @Description: ${todo}
 */
@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public Integer getUserInfoByUserId(Integer userId) {
        return userInfoDao.getUserInfoByUserId(userId);
    }

    public void saveUserInfo(UserInfoBean userInfoBean) {
        userInfoDao.saveUserInfo(userInfoBean);
    }

    public void updateUserInfoById(UserInfoBean userInfoBean) {
        userInfoDao.updateUserInfoById(userInfoBean);
    }

}
