package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.common.MCPTool;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.common.UrlTool;
import com.finefocus.tryspread.dao.ChannelPacUrlDao;
import com.finefocus.tryspread.service.ChannelPacUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/7/13
 * @Description: ${todo}
 */
@Service(value = "channelPacUrlService")
public class ChannelPacUrlServiceImpl implements ChannelPacUrlService {
    @Autowired
    private ChannelPacUrlDao channelPacUrlDao;

    public String getUrlByUserId(Integer userId) {

        return channelPacUrlDao.getUrlByUserId(userId);
    }

    public String getUrlByUserIdFromLocal(HttpServletRequest request, Integer userId) {
        String downUrl = "";
        String apkSourcePath = RedisKeyProperties.getPropertyValue("apk_source_path");
        File apkSourceFile = new File(apkSourcePath);
        if (apkSourceFile.isFile()) {
            String apkSourceFileName = apkSourceFile.getName();
            int dot = apkSourceFileName.lastIndexOf(".");
            String prefix = apkSourceFileName.substring(0, dot);
            String suffix = apkSourceFileName.substring(dot);
            String apkChannelName = prefix + "_" + String.valueOf(userId) + suffix;
            File apkChannelPath = new File(RedisKeyProperties.getPropertyValue("apk_channel_path"), apkChannelName);
            if (apkChannelPath.exists()) {
                //本地有该渠道包，拼接url返回
                downUrl = UrlTool.getRequestUrl(request) + apkChannelName;
                return downUrl;
            }
            if (!apkChannelPath.exists()) {
                //本地没有该apk文件生成apk文件
                try {
                    MCPTool.writeChannelAndGetPath(String.valueOf(userId));
                    downUrl = UrlTool.getRequestUrl(request) + apkChannelName;
                    //生成apk渠道文件后异步上传到cdn，并添加cdn表记录
                    return downUrl;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return downUrl;
    }
}
