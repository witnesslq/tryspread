package com.finefocus.tryspread.common;

import junit.framework.TestCase;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/7/13
 * @Description: ${todo}
 */
public class UrlToolTest extends TestCase {
    @Test
    public void testGetRequestUrl() throws Exception {
        HttpServletRequest httpServletRequest = null;
        String requestUrl = UrlTool.getRequestUrl(httpServletRequest);

    }
}