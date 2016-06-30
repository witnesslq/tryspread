package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.UserDao;
import com.finefocus.tryspread.pojo.UserBean;
import com.finefocus.tryspread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public int getUserIdByImei(String imei) {
        Integer userId = userDao.getUserIdByImei(imei);
        if (userId == null) {
            return 0;
        }
        return userId;
    }

    public void saveUser(UserBean userBean) {

        userDao.saveUser(userBean);
    }


}
