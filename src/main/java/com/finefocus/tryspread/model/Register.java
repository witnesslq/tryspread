package com.finefocus.tryspread.model;

import com.finefocus.tryspread.pojo.ExchangeRecordBean;
import com.finefocus.tryspread.pojo.IntegralBean;
import com.finefocus.tryspread.pojo.InvitationRecordBean;
import com.finefocus.tryspread.pojo.TaskLogBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.model
 * @date 2016/6/14
 * @Description: 用户注册返回结果信息
 */
public class Register {

    /**
     * userId : 231
     * integral : {"totalIntegral":"90000","currentIntegration":"1000"}
     * taskLog : [{"taskName":"京东","taskTime":"2016年6月13日 16:25:10","taskIntegration":"2000"},{"taskName":"淘宝","taskTime":"2016年6月13日 16:25:10","taskIntegration":"10000"}]
     * exchangeRecord : [{"itemName":"支付宝20元","exchangeTime":"2016年6月13日 16:25:10","exchangeIntegral":"2000"},{"itemName":"QB100个","exchangeTime":"2016年6月13日 16:25:10","exchangeIntegral":"2000"}]
     * invitationRecord : [{"userId":"123","invitationTime":"2016年6月13日 16:25:10","invitationClass":"1"},{"userId":"111111","invitationTime":"2016年6月13日 16:25:10","invitationClass":"2"}]
     */

    private Integer userId;
    /**
     * totalIntegral : 90000
     * currentIntegration : 1000
     */

    private IntegralBean integral;
    /**
     * taskName : 京东
     * taskTime : 2016年6月13日 16:25:10
     * taskIntegration : 2000
     */

    private List<TaskLogBean> taskLog;
    /**
     * itemName : 支付宝20元
     * exchangeTime : 2016年6月13日 16:25:10
     * exchangeIntegral : 2000
     */

    private List<ExchangeRecordBean> exchangeRecord;
    /**
     * userId : 123
     * invitationTime : 2016年6月13日 16:25:10
     * invitationClass : 1
     */

    private List<InvitationRecordBean> invitationRecord;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public IntegralBean getIntegral() {
        return integral;
    }

    public void setIntegral(IntegralBean integral) {
        this.integral = integral;
    }

    public List<TaskLogBean> getTaskLog() {
        return taskLog;
    }

    public void setTaskLog(List<TaskLogBean> taskLog) {
        this.taskLog = taskLog;
    }

    public List<ExchangeRecordBean> getExchangeRecord() {
        return exchangeRecord;
    }

    public void setExchangeRecord(List<ExchangeRecordBean> exchangeRecord) {
        this.exchangeRecord = exchangeRecord;
    }

    public List<InvitationRecordBean> getInvitationRecord() {
        return invitationRecord;
    }

    public void setInvitationRecord(List<InvitationRecordBean> invitationRecord) {
        this.invitationRecord = invitationRecord;
    }


}
