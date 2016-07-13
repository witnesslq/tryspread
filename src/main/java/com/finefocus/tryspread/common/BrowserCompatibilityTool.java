package com.finefocus.tryspread.common;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/7/13
 * @Description: ${todo}
 */
public class BrowserCompatibilityTool {
    /**
     * @throws UnsupportedEncodingException
     * @Title: processFileName
     * @Description: ie, chrom, firfox下处理文件名显示乱码
     */
    public static String processFileName(HttpServletRequest request, String fileNames) throws Exception {
        String codedfilename = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                && -1 != agent.indexOf("Trident")) {// ie
            String name = java.net.URLEncoder.encode(fileNames, "UTF8");
            codedfilename = name;
        } else {// 火狐,chrome等
            codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
        }
        return codedfilename;
    }
}
