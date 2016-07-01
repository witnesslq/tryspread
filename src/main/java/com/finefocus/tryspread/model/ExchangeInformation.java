package com.finefocus.tryspread.model;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.model
 * @date 2016/7/1
 * @Description: ${todo}
 */
public class ExchangeInformation {

    private Integer userId;//用户id
    private Integer exchangeProductsId;//兑换商品id
    private Integer exchangeNum;//兑换的数目

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExchangeProductsId() {
        return exchangeProductsId;
    }

    public void setExchangeProductsId(Integer exchangeProductsId) {
        this.exchangeProductsId = exchangeProductsId;
    }

    public Integer getExchangeNum() {
        return exchangeNum;
    }

    public void setExchangeNum(Integer exchangeNum) {
        this.exchangeNum = exchangeNum;
    }
}
