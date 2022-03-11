package com.yyh.crm.commons.utils;

import java.util.UUID;

/**
 * @author yyh
 * @date 2022-03-10 21:45
 */
public class UUIDUtils {

    /**
     * 获取32位UUID不带-
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
