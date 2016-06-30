package com.finefocus.tryspread.pojo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.pojo
 * @date 2016/6/14
 * @Description: ${todo}
 */
public class TaskInformationBean {
    @JSONField(name = "apkId")
    private Integer id;

    private Integer taskId; //任务id
    private String taskName;//任务名称
    private String taskType;//任务类型
    /**
     * appSize : app大小
     * appIntroduce : app介绍
     * appUrl : app地址
     * appDes : app描述
     */
    private String appSize;
    private String appIntroduce;
    private String extUrl;
    private String appDes;
    private String appPackage;
    private String appIcon;
    private String apkUrl;
    private Integer apkId;

    public TaskInformationBean() {
    }

    public String getExtUrl() {
        return extUrl;
    }

    public void setExtUrl(String extUrl) {
        this.extUrl = extUrl;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public Integer getApkId() {
        return apkId;
    }

    public void setApkId(Integer apkId) {
        this.apkId = apkId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }


    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppIntroduce() {
        return appIntroduce;
    }

    public void setAppIntroduce(String appIntroduce) {
        this.appIntroduce = appIntroduce;
    }


    public String getAppDes() {
        return appDes;
    }

    public void setAppDes(String appDes) {
        this.appDes = appDes;
    }
}
