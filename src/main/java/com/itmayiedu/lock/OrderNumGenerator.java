package com.itmayiedu.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wukuijun on 2018/10/19
 */
public class OrderNumGenerator {

    private static int count = 0;
    public String getNumber(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date())+(++count);
    }
}
