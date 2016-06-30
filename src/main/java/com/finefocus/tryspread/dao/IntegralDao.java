package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.IntegralBean;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/15
 * @Description: 操作积分表的dao
 */
@QfBatisRepository
public interface IntegralDao {
    /**
     * 积分信息
     *
     * @param userId 用户id
     * @return 当前用户的积分
     */
    IntegralBean IntegralByUserId(int userId);

    void updateIntegralById(IntegralBean integralBean);

    void saveIntegralByUserId(int userId);
}
