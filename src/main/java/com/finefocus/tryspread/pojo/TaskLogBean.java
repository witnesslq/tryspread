package com.finefocus.tryspread.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.pojo
 * @date 2016/6/14
 * @Description: 任务记录表
 */
public class TaskLogBean {
    //    @JsonIgnore
    private Integer id;//任务表id
    @JsonIgnore
    private Integer userId;//用户id

    private Integer taskId;//任务表id
    private Integer stepId;//任务分步表id
    private Date taskCreatTime;//任务创建时间
    private String taskName;//任务名称
    private Date taskTime;//任务完成时间
    private Integer taskIntegration;//任务获得积分

    private Integer state;//任务完成状态 0 代表未完成 1代表完成

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Date getTaskCreatTime() {
        return taskCreatTime;
    }

    public void setTaskCreatTime(Date taskCreatTime) {
        this.taskCreatTime = taskCreatTime;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public Integer getTaskIntegration() {
        return taskIntegration;
    }

    public void setTaskIntegration(Integer taskIntegration) {
        this.taskIntegration = taskIntegration;
    }
}
