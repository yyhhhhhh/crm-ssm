package com.yyh.crm;

import org.junit.Test;

import java.util.UUID;

/**
 * @author yyh
 * @date 2022-03-10 21:38
 */
public class UUIDTest {

    @Test
    public void testUUID(){
        for(int i=0;i<10;i++){
            System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
        }
    }
}
