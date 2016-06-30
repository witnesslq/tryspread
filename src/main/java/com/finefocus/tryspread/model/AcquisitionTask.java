package com.finefocus.tryspread.model;

import com.finefocus.tryspread.pojo.StepBean;
import com.finefocus.tryspread.pojo.TaskInformationBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.model
 * @date 2016/6/14
 * @Description: ${todo}
 */
public class AcquisitionTask {

    /**
     * taskId : 123
     * taskName : 淘宝
     * taskType : 任务类型
     * taskInformation : {"appSize":"app大小","appIntroduce":"app介绍","appUrl":"app地址","appDes":"app描述"}
     * step : [{"stepId":"1","des":"描述","useDays":"使用天数","useTime":"使用时常","integral":"积分"},{"stepId":"2","des":"描述","useDays":"使用天数","useTime":"使用时常","integral":"积分"}]
     */

//    private Integer taskId; //任务id
//    private String taskName;//任务名称
//    private String taskType;//任务类型
    private Integer currentStep;
    /**
     * appSize : app大小
     * appIntroduce : app介绍
     * appUrl : app地址
     * appDes : app描述
     */

    private TaskInformationBean taskInformation;
    /**
     * stepId : 1
     * des : 描述
     * useDays : 使用天数
     * useTime : 使用时常
     * integral : 积分
     */

    private List<StepBean> step;

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

//    public Integer getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(Integer taskId) {
//        this.taskId = taskId;
//    }
//
//    public String getTaskName() {
//        return taskName;
//    }
//
//    public void setTaskName(String taskName) {
//        this.taskName = taskName;
//    }
//
//    public String getTaskType() {
//        return taskType;
//    }
//
//    public void setTaskType(String taskType) {
//        this.taskType = taskType;
//    }

    public TaskInformationBean getTaskInformation() {
        return taskInformation;
    }

    public void setTaskInformation(TaskInformationBean taskInformation) {
        this.taskInformation = taskInformation;
    }

    public List<StepBean> getStep() {
        return step;
    }

    public void setStep(List<StepBean> step) {
        this.step = step;
    }

}
