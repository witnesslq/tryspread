package com.finefocus.tryspread.pojo;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.pojo
 * @date 2016/6/14
 * @Description: ${todo}
 */
public class StepBean {
    /**
     * stepId : 1
     * des : 描述
     * useDays : 使用天数
     * useTime : 使用时常
     * integral : 积分
     */
    private Integer stepId;
    private String des;
    private Integer useDays;
    private Integer useTime;
    private Integer integral;

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getUseDays() {
        return useDays;
    }

    public void setUseDays(Integer useDays) {
        this.useDays = useDays;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
