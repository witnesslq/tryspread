package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.UserBean;

import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/15
 * @Description: ${todo}
 */
public interface SignUpService {
    Map<String, Object> signUp(UserBean userBean, Integer parentId);
}
