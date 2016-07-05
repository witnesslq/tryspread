package com.finefocus.tryspread.service;

import com.finefocus.tryspread.model.ExchangeInformation;
import com.finefocus.tryspread.pojo.ExchangeRecordBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/15
 * @Description: ${todo}
 */
public interface ExchangeRecordService {
    List<ExchangeRecordBean> getExchangeRecordByUserId(int userId);

    void exchange(ExchangeInformation exchangeInformation);
}
