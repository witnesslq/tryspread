package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.ExchangeRecordBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/15
 * @Description: 操作兑换记录表的dao
 */
@QfBatisRepository
public interface ExchangeRecordDao {
    /**
     * 查询兑换记录
     *
     * @param userId 用户id
     * @return 兑换记录
     */
    List<ExchangeRecordBean> getExchangeRecordByUserId(int userId);
}
