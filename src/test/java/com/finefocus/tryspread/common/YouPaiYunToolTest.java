package com.finefocus.tryspread.common;

import junit.framework.TestCase;
import main.java.com.UpYun;
import org.junit.Test;

import java.io.File;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/7/13
 * @Description: ${todo}
 */
public class YouPaiYunToolTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    @Test
    public void testGetUpyun() throws Exception {
        UpYun upyun = YouPaiYunTool.getUpyun();
        File file = new File("C:/111/111_00000002.apk");
        String filePath = "C:/111/111_00000002.apk";
        upyun.setContentMD5(UpYun.md5(file));
        boolean result = upyun.writeFile(filePath, file, true);
        if (result) {
            System.out.println("文件上传完成，路径为：" + filePath);
        }
    }

    @Test
    public void testDownload() throws Exception {
        UpYun upyun = YouPaiYunTool.getUpyun();
        File file = new File("C:/111/111_00000002.apk");
        String filePath = "/test";
        upyun.setContentMD5(UpYun.md5(file));
        boolean result = upyun.writeFile(filePath, file, true);
        if (result) {
            System.out.println("文件上传完成，路径为：" + filePath);
        }
    }
}