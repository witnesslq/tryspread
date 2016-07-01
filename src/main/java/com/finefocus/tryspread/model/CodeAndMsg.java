package com.finefocus.tryspread.model;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.model
 * @date 2016/6/15
 * @Description: ${todo}
 */
public class CodeAndMsg {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROE";
    public static final String RESULT = "RESULT";
    public static final String CODE = "CODE";
    public static final String MSG = "MSG";
    public static final String OK = "201";//无异常
    public static final String EXCENTION = "500";//内部异常
    public static final String NULL = "404";//为找到
    public static final String PARAMETERERROR = "400";//参数错误
    public static final String DATA = "DATA";
    public static final String MinutesIsNotEnough = "202";//分钟数不够
    public static final String DAYIsNotEnough = "203";//天数间隔不够
    public static final String TaskCreateOk = "204";//任务创建完成
    public static final String TaskIsOK = "205";//任务已经完成
    public static final String ApkIdIsNull = "206";//下载计数apkId=null
    public static final String FirstDownload = "207";//第一次下载
    public static final String DownloadOverLimit = "208";//下载超过限制次数
    public static final String IncreaseTheCounter = "209";//增加计数器
    public static final String ExchangeListingIsNull = "210";//兑换列表为空
    public static final String ExchangeListingIsOk = "211";//获取可兑换列表成功
    public static final String EnoughPoints = "212";//积分不够
    public static final String PointsAreDeducted = "213";//积分已扣除


}
