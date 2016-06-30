package com.finefocus.tryspread.pojo;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.pojo
 * @date 2016/6/14
 * @Description: 积分表
 */
public class IntegralBean {
    private Integer id;//积分表id
    private Integer userId;//用户id
    private Integer totalIntegral;//总积分
    private Integer currentIntegration;//当前积分

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

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public Integer getCurrentIntegration() {
        return currentIntegration;
    }

    public void setCurrentIntegration(Integer currentIntegration) {
        this.currentIntegration = currentIntegration;
    }
}
