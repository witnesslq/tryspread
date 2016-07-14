package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.apkprocessor.ApkCndManager;
import com.finefocus.tryspread.common.MCPTool;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.common.UrlTool;
import com.finefocus.tryspread.common.YouPaiYunTool;
import com.finefocus.tryspread.dao.ChannelPacUrlDao;
import com.finefocus.tryspread.pojo.ChannelPacUrlBean;
import com.finefocus.tryspread.service.ChannelPacUrlService;
import main.java.com.UpYun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/7/13
 * @Description: ${todo}
 */
@Service(value = "channelPacUrlService")
public class ChannelPacUrlServiceImpl implements ChannelPacUrlService {
    protected Logger LOGGER = LoggerFactory.getLogger(ChannelPacUrlServiceImpl.class);

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
                    ChannelPacUrlBean channelPacUrl = new ChannelPacUrlBean();
                    channelPacUrl.setUserId(userId);
                    channelPacUrl.setFilePath(apkChannelPath.getPath());
                    //添加到上传cdn队列
                    ApkCndManager.addRequest(channelPacUrl);
                    return downUrl;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return downUrl;
    }

    public void UploadApkToCdn(ChannelPacUrlBean channelPacUrlBean) {
        System.out.println("上传apk渠道包文件到cdn");
        //本地文件路径
        String filePath = channelPacUrlBean.getFilePath();
        if (filePath.contains("\\")) {
            filePath = filePath.replaceAll("\\\\", "/");
        }
        //用户id
        Integer userId = channelPacUrlBean.getUserId();
        File localFile = new File(filePath);
        if (localFile.exists()) {
            //本地渠道包文件存在，调用cdn上传文件
            boolean uploadFile = false;
            try {
                UpYun upyun = YouPaiYunTool.getUpyun();
                uploadFile = upyun.writeFile(filePath, localFile, true);
            } catch (Exception e) {
                LOGGER.error(localFile.getName() + "文件上传失败~上传代码快异常" + e.getMessage());
            }
            if (uploadFile) {
                LOGGER.info(localFile.getName() + "文件上传到CDN成功！！");
                //拼接cdn下载地址
                String downUrl = RedisKeyProperties.getPropertyValue("cdn_url") + "/" + filePath;
                String urlByUserId = channelPacUrlDao.getUrlByUserId(userId);
                if (urlByUserId == null) {
                    //添加操作
                    channelPacUrlDao.saveDownUrlAndUserId(downUrl, userId, new Date());
                } else {
                    //更新操作
                    channelPacUrlDao.UpdateDownUrlByUserId(downUrl, userId, new Date());
                }

            } else {
                LOGGER.info(localFile.getName() + "文件上传到CDN失败！！");
            }

        }
    }
}
