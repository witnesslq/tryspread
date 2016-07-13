package com.finefocus.tryspread.controller;

import com.finefocus.tryspread.common.BrowserCompatibilityTool;
import com.finefocus.tryspread.common.RedisKeyProperties;
import com.finefocus.tryspread.model.CodeAndMsg;
import com.finefocus.tryspread.service.ChannelPacUrlService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.controller
 * @date 2016/7/13
 * @Description: 推广相关controller
 */
@RestController
public class ExtensionController {
    @Autowired
    private ChannelPacUrlService channelPacUrlService;

    @RequestMapping(value = "getExtensionDownloadUrl/{userId}", method = RequestMethod.GET)
    public ModelAndView getExtensionDownloadUrl(@PathVariable Integer userId, HttpServletRequest request) {
        ModelAndView modelAndView = null;
        if (userId != null) {
            //查询数据库 获取到下载地址
            //查询cdn上是否有该渠道的文件，如果有直接拼接url重定向回去
            String url = channelPacUrlService.getUrlByUserId(userId);
            if (StringUtils.isNotEmpty(url)) {
                modelAndView = new ModelAndView("redirect:" + url);
                return modelAndView;
            }
            if (StringUtils.isEmpty(url)) {
                //cdn上没有 查询本地是否有渠道包
                url = channelPacUrlService.getUrlByUserIdFromLocal(request, userId);
                modelAndView = new ModelAndView("redirect:" + url);
                return modelAndView;
            }
        }
        return modelAndView;
    }

    /**
     * 下载apk
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "download/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable String fileName, HttpServletRequest request) {
        ResponseEntity<byte[]> fileStream = null;
        try {
            //根据文件名获取本地文件全路径

            String filePath = RedisKeyProperties.getPropertyValue("apk_channel_path") + "/" + fileName + ".apk";
            if (StringUtils.isEmpty(filePath)) {
                throw new Exception("文件名为空！");
            }
            File file = new File(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("ATTACHMENT", BrowserCompatibilityTool.processFileName(request, file.getName()));

            fileStream = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
//            logger.error("文件下载失败 ！", e);
        }
        return fileStream;
    }

}
