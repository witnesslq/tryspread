package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.IntegralBean;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/15
 * @Description: ${todo}
 */
public interface IntegralService {
    IntegralBean getIntegralByUserId(int userId);

    void updateIntegralById(IntegralBean integralBean);

    void saveIntegralByUserId(int userId);

    void updateCurrentIntegrationByUserId(int currentIntegration, Integer userId);
}
