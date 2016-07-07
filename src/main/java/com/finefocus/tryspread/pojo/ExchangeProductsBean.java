package com.finefocus.tryspread.pojo;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/7/1
 * @Description: ${todo}
 */
public class ExchangeProductsBean {
    //    @JsonIgnore
    private Integer id;
    private String name;//商品名称
    private String icon;//图标url
    private Integer ratio;//比率
    private String type;//商品类型1需要qq号 2需要手机号 3 需要支付宝帐号跟姓名
    private String des;//描述

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }
}
