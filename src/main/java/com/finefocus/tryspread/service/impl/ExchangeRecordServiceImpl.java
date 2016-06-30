package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.ExchangeRecordDao;
import com.finefocus.tryspread.pojo.ExchangeRecordBean;
import com.finefocus.tryspread.service.ExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "exchangeRecordService")
public class ExchangeRecordServiceImpl implements ExchangeRecordService {
    @Autowired
    private ExchangeRecordDao exchangeRecordDao;

    public List<ExchangeRecordBean> getExchangeRecordByUserId(int userId) {
        return exchangeRecordDao.getExchangeRecordByUserId(userId);
    }
}
