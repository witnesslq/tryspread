package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.ExchangeProductsDao;
import com.finefocus.tryspread.dao.ExchangeRecordDao;
import com.finefocus.tryspread.dao.IntegralDao;
import com.finefocus.tryspread.dao.UserInfoDao;
import com.finefocus.tryspread.model.ExchangeInformation;
import com.finefocus.tryspread.pojo.ExchangeProductsBean;
import com.finefocus.tryspread.pojo.ExchangeRecordBean;
import com.finefocus.tryspread.pojo.IntegralBean;
import com.finefocus.tryspread.pojo.UserInfoBean;
import com.finefocus.tryspread.service.ExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UnknownFormatConversionException;

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
    @Autowired
    private ExchangeProductsDao exchangeProductsDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private IntegralDao integralDao;

    public List<ExchangeRecordBean> getExchangeRecordByUserId(int userId) {
        return exchangeRecordDao.getExchangeRecordByUserId(userId);
    }

    public void exchange(ExchangeInformation exchangeInformation) {
        /**
         *1exchangeProductsId 根据兑换商品id 查询出需要兑换商品的类型与积分比利
         * 2在根据用户id 查询出 用户的手机号等信息
         * 3 如果type为1 比较用户手机号等 成立保存兑换信息  状态 0：已提交 1：成功兑换 2：失败兑换
         *
         */

        ExchangeProductsBean exchangeProductsBean = exchangeProductsDao.getExchangeProductById(exchangeInformation.getExchangeProductsId());
        String type = exchangeProductsBean.getType();
        UserInfoBean userInfoBean = userInfoDao.getUserInfoBeanByUserId(exchangeInformation.getUserId());
        Integer exchangeIntegral = exchangeProductsBean.getRatio() * exchangeInformation.getExchangeNum();
        ExchangeRecordBean exchangeRecord = new ExchangeRecordBean();
        exchangeRecord.setUserId(exchangeInformation.getUserId());
        exchangeRecord.setExchangeTime(new Date());
        exchangeRecord.setExchangeIntegral(exchangeIntegral);
        exchangeRecord.setItemName(exchangeProductsBean.getName());
        if (userInfoBean != null) {
            if (type.equals("1")) {
                //type是1 需要的是QQ号
                Integer qq = userInfoBean.getQq();
                if (qq != null) {
                    //保存兑换记录是已提交
                    exchangeRecord.setState(0);
                    exchangeRecordDao.saveExchangeRecordBean(exchangeRecord);
//                id=exchangeRecord.getId();

                }
                if (qq == null) {
                    //非法操作 退还积分
                    IntegralBean integralBean = integralDao.IntegralByUserId(exchangeInformation.getUserId());
                    integralDao.updateCurrentIntegrationByUserId(integralBean.getCurrentIntegration() + exchangeIntegral, exchangeInformation.getUserId());
                    //保存记录是失败状态
                    exchangeRecord.setState(2);
                    exchangeRecordDao.saveExchangeRecordBean(exchangeRecord);
                }

            }
            if (type.equals("2")) {
                //type是1 需要的是QQ号
                String phone = userInfoBean.getPhone();
                if (phone != null) {
                    //保存兑换记录是已提交
                    exchangeRecord.setState(0);
                    exchangeRecordDao.saveExchangeRecordBean(exchangeRecord);
//                id=exchangeRecord.getId();

                }
                if (phone == null) {
                    //非法操作 退还积分
                    IntegralBean integralBean = integralDao.IntegralByUserId(exchangeInformation.getUserId());
                    integralDao.updateCurrentIntegrationByUserId(integralBean.getCurrentIntegration() + exchangeIntegral, exchangeInformation.getUserId());
                    exchangeRecord.setState(2);
                    exchangeRecordDao.saveExchangeRecordBean(exchangeRecord);
                }

            }
            if (type.equals("3")) {
                String alipay = userInfoBean.getAlipay();
                String alipayName = userInfoBean.getAlipayName();
                if (alipay != null && alipayName != null) {
                    exchangeRecord.setState(0);
                    exchangeRecordDao.saveExchangeRecordBean(exchangeRecord);
                }
                if (alipay == null || alipayName == null) {
                    IntegralBean integralBean = integralDao.IntegralByUserId(exchangeInformation.getUserId());
                    integralDao.updateCurrentIntegrationByUserId(integralBean.getCurrentIntegration() + exchangeIntegral, exchangeInformation.getUserId());
                    exchangeRecord.setState(2);
                    exchangeRecordDao.saveExchangeRecordBean(exchangeRecord);
                }
            }

        } else {
            IntegralBean integralBean = integralDao.IntegralByUserId(exchangeInformation.getUserId());
            integralDao.updateCurrentIntegrationByUserId(integralBean.getCurrentIntegration() + exchangeIntegral, exchangeInformation.getUserId());
            exchangeRecord.setState(2);
            exchangeRecordDao.saveExchangeRecordBean(exchangeRecord);
        }

    }

}
