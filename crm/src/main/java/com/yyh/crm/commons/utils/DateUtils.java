package com.yyh.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yyh
 * @date 2022-03-09 13:33
 * 对Date类型数据进行处理的工具类
 */
public class DateUtils {

    /**
     * 对指定的date对象进行格式化 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
