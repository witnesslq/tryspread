package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.pojo.IntegralBean;
import com.finefocus.tryspread.pojo.TaskLogBean;
import com.finefocus.tryspread.service.IntegralService;
import com.finefocus.tryspread.service.IntegrationJobService;
import com.finefocus.tryspread.service.InvitationRecordService;
import com.finefocus.tryspread.service.TaskLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/7/12
 * @Description: ${todo}
 */
@Service(value = "integrationJobService")
public class IntegrationJobServiceImpl implements IntegrationJobService {
    protected Logger LOGGER = LoggerFactory.getLogger(IntegrationJobServiceImpl.class);
    @Autowired
    private TaskLogService taskLogService;
    @Autowired
    private InvitationRecordService invitationRecordService;
    @Autowired
    private IntegralService integralService;

    public void addGeneralizedIntegral(String Date) {
        //获取当前时间下的完成任务的用户id 与总获得的积分
        List<Map<String, Object>> maps = taskLogService.getUserIdAndIntegrationSumByDate(Date);
        for (Map<String, Object> map : maps) {
            if (map == null) {
                LOGGER.info(Date + "未找到完成任务记录，即当天没有用户完成任务");
                break;
            }
            if (map != null) {
                int integrationSum = ((BigDecimal) map.get("integration_sum")).intValue();
                int userId = (Integer) map.get("user_id");
                // 通过用户id查找到父id 与 父父id 分别添加积分
                Integer parentId = invitationRecordService.getParentIdByUserId(userId);
                Integer grandpaId = invitationRecordService.getGrandpaIdByUserId(userId);
                if (parentId != null) {
                    //给父级添加积分 20%
                    IntegralBean parentIntegralBean = integralService.getIntegralByUserId(parentId);
                    if (parentIntegralBean != null) {
                        parentIntegralBean.setCurrentIntegration((int) (parentIntegralBean.getCurrentIntegration() + integrationSum * 0.2));
                        parentIntegralBean.setTotalIntegral((int) (parentIntegralBean.getTotalIntegral() + integrationSum * 0.2));
                        integralService.updateIntegralById(parentIntegralBean);
                    }

                }
                if (grandpaId != null) {
                    //给父级添加积分 20%
                    IntegralBean grandpaIntegralBean = integralService.getIntegralByUserId(grandpaId);
                    if (grandpaIntegralBean != null) {
                        grandpaIntegralBean.setCurrentIntegration((int) (grandpaIntegralBean.getCurrentIntegration() + integrationSum * 0.1));
                        grandpaIntegralBean.setTotalIntegral((int) (grandpaIntegralBean.getTotalIntegral() + integrationSum * 0.1));
                        integralService.updateIntegralById(grandpaIntegralBean);
                    }

                }

            }
        }
    }
}
