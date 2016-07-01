package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.UserInfoBean;
import org.apache.ibatis.annotations.Param;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/7/1
 * @Description: ${todo}
 */
@QfBatisRepository
public interface UserInfoDao {
    Integer getUserInfoByUserId(@Param("userId") Integer userId);

    void saveUserInfo(UserInfoBean userInfoBean);

    void updateUserInfoById(UserInfoBean userInfoBean);
}
