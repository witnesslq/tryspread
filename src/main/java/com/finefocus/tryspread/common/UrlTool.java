package com.finefocus.tryspread.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/7/13
 * @Description: ${todo}
 */
public class UrlTool {
    public static String getRequestUrl(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
        return tempContextUrl + "download/";

    }
}
