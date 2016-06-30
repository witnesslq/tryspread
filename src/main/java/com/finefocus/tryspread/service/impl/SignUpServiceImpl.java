package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.model.CodeAndMsg;
import com.finefocus.tryspread.model.Register;
import com.finefocus.tryspread.pojo.*;
import com.finefocus.tryspread.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "signUpService")
public class SignUpServiceImpl implements SignUpService {
    protected Logger LOGGER = LoggerFactory.getLogger(SignUpServiceImpl.class);
    @Autowired
    private UserService userService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private TaskLogService taskLogService;
    @Autowired
    private ExchangeRecordService exchangeRecordService;
    @Autowired
    private InvitationRecordService invitationRecordService;
    @Autowired
    private StepService stepService;

    @Autowired
    private TaskService taskService;

    public Map<String, Object> signUp(UserBean userBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        Register register = new Register();
        if (userBean != null) {
            if (userBean.getImei() != null) {
                int userId = userService.getUserIdByImei(userBean.getImei());
                if (userId == 0) {
                    userService.saveUser(userBean);
                    LOGGER.info("保存用户信息");
                    map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
                    map.put(CodeAndMsg.CODE, CodeAndMsg.OK);
                    map.put(CodeAndMsg.DATA, register);
                    map.put(CodeAndMsg.MSG, "保存用户");
                    //初始化积分
                    userId = userService.getUserIdByImei(userBean.getImei());

                    integralService.saveIntegralByUserId(userId);
                }

                if (userId != 0) {
                    try {
                        register.setUserId(userId);
                        IntegralBean integral = integralService.getIntegralByUserId(userId);
                        LOGGER.info("UserId : {" + userId + "} 查询出的积分信息完成");
                        register.setIntegral(integral);
                        List<TaskLogBean> taskLogList = taskLogService.getTaskLogByUserId(userId);
                        LOGGER.info("UserId : {" + userId + "} 查询出的任务信息完成 ");
                        register.setTaskLog(taskLogList);
                        List<ExchangeRecordBean> exchangeRecordList = exchangeRecordService.getExchangeRecordByUserId(userId);
                        LOGGER.info("UserId : {" + userId + "} 查询出的兑换信息完成 ");
                        register.setExchangeRecord(exchangeRecordList);
                        List<InvitationRecordBean> invitationRecordList = invitationRecordService.getInvitationRecordByUserId(userId);
                        LOGGER.info("UserId : {" + userId + "} 查询出的邀请信息完成");
                        register.setInvitationRecord(invitationRecordList);
                        map.put(CodeAndMsg.RESULT, CodeAndMsg.SUCCESS);
                        map.put(CodeAndMsg.CODE, CodeAndMsg.OK);
                        map.put(CodeAndMsg.DATA, register);
                        map.put(CodeAndMsg.MSG, "返回结果");
                        return map;
                    } catch (Exception e) {
                        LOGGER.error("UserId : {" + userId + "} 查询异常: {" + e.getMessage() + "}");
                        map.put(CodeAndMsg.RESULT, CodeAndMsg.ERROR);
                        map.put(CodeAndMsg.CODE, CodeAndMsg.EXCENTION);
                        map.put(CodeAndMsg.DATA, register);
                        map.put(CodeAndMsg.MSG, "内部异常");
                        return map;
                    }

                }
            }
        }
//        return register;
        return map;
    }
}
