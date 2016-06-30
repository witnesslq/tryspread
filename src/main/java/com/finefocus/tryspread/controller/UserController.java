package com.finefocus.tryspread.controller;

import com.finefocus.tryspread.model.CodeAndMsg;
import com.finefocus.tryspread.model.Register;
import com.finefocus.tryspread.pojo.*;
import com.finefocus.tryspread.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.controller
 * @date 2016/6/15
 * @Description: 用户controller
 */
@RestController
public class UserController {

    @Autowired
    private SignUpService signUpService;

    /**
     * 用户注册登录
     *
     * @param userBean 用户信息
     * @return 结果
     */
    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signUp(UserBean userBean) {
        Map<String, Object> map = signUpService.signUp(userBean);
        return map;

    }
}
