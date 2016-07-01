package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.IntegralDao;
import com.finefocus.tryspread.pojo.IntegralBean;
import com.finefocus.tryspread.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "integralService")
public class IntegralServiceImpl implements IntegralService {
    @Autowired
    private IntegralDao integralDao;

    public IntegralBean getIntegralByUserId(int userId) {
        return integralDao.IntegralByUserId(userId);
    }

    public void updateIntegralById(IntegralBean integralBean) {
        integralDao.updateIntegralById(integralBean);
    }

    public void saveIntegralByUserId(int userId) {
        integralDao.saveIntegralByUserId(userId);
    }

    public void updateCurrentIntegrationByUserId(int currentIntegration, Integer userId) {
        integralDao.updateCurrentIntegrationByUserId(currentIntegration, userId);
    }
}
