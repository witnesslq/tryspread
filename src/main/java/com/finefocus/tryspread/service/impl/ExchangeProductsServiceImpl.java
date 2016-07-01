package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.ExchangeProductsDao;
import com.finefocus.tryspread.pojo.ExchangeProductsBean;
import com.finefocus.tryspread.service.ExchangeProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/7/1
 * @Description: ${todo}
 */
@Service(value = "exchangeProductsService")
public class ExchangeProductsServiceImpl implements ExchangeProductsService {
    @Autowired
    private ExchangeProductsDao exchangeProductsDao;

    public List<ExchangeProductsBean> getExchangeProducts() {
        return exchangeProductsDao.getExchangeProducts();
//        return null;
    }

    public Integer getRatioById(Integer id) {
        return exchangeProductsDao.getRatioById(id);
    }
}
