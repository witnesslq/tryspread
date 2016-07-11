package com.finefocus.tryspread.common;

import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/6/16
 * @Description: ${todo}
 */
public class DateTool {
    /**
     * 如果第一个任务完成时间为空 则当前任务为第一个任务
     *
     * @param createTime  任务创建时间
     * @param currentTime 第一个任务完成时间
     * @param day         任务完成需要的天数
     * @return
     */
    public static boolean comparisonDate(Date createTime, Date currentTime, Integer day) {
        if (currentTime == null) {
            return true;
        }
//        long createTimeTime = createTime.getTime();
//        int createTimeDay = createTime.getDay();
        int createTimeDay = createTime.getDate();
        int currentTimeDay = currentTime.getDate();
//        long currentTimeTime = currentTime.getTime();
        if (createTimeDay >= currentTimeDay) {
//            int i = (int) (createTimeDay - currentTimeDay) / 86400000;
            int i = createTimeDay - currentTimeDay;
            if (i >= day) {
                return true;
            }
            if (i < day) {
                return false;
            }
        }
        if (createTimeDay < currentTimeDay) {
            return false;
        }

        return false;
    }

    /**
     * 分钟数校验 如果任务创建时间为空 直接返回false
     *
     * @param createTime  任务创建时间
     * @param currentTime 当前时间
     * @param useTime     需要使用的分钟数
     * @return
     */
    public static boolean comparisonMin(Date createTime, Date currentTime, Integer useTime) {
        if (createTime == null) {
            return false;
        }
        long createTimeTime = createTime.getTime();
        long currentTimeTime = currentTime.getTime();
        if (currentTimeTime >= createTimeTime) {
            int i = (int) (currentTimeTime - createTimeTime) / 60000;
            if (i >= useTime) {
                return true;
            }
            if (i < useTime) {
                return false;
            }
        }
        if (currentTimeTime < createTimeTime) {
            return false;
        }
        return false;
    }
}
