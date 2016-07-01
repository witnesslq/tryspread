package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.ExchangeProductsBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/7/1
 * @Description: ${todo}
 */
public interface ExchangeProductsService {
    List<ExchangeProductsBean> getExchangeProducts();

    Integer getRatioById(Integer id);
}
