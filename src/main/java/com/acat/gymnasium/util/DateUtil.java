package com.acat.gymnasium.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
}
