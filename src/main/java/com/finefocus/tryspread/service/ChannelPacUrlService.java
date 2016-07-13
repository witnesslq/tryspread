package com.finefocus.tryspread.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/7/13
 * @Description: ${todo}
 */
public interface ChannelPacUrlService {

    String getUrlByUserId(Integer userId);

    String getUrlByUserIdFromLocal(HttpServletRequest request, Integer userId);
}
