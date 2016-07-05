package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.ExchangeProductsBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/7/1
 * @Description: ${todo}
 */
@QfBatisRepository
public interface ExchangeProductsDao {
    List<ExchangeProductsBean> getExchangeProducts();

    Integer getRatioById(Integer id);

    String getNameById(Integer id);

    ExchangeProductsBean getExchangeProductById(Integer id);
}
