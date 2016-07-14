package com.finefocus.tryspread.pojo;

import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.pojo
 * @date 2016/7/14
 * @Description: ${todo}
 */
public class ChannelPacUrlBean {
    private Integer id;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
    private String downloadUrl;
    private String filePath;//本地渠道包文件路径

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
