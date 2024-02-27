package com.acat.gymnasium.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtils {

    public static boolean isPhoneNum(String phoneNum) {
        String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(isPhoneNum("1858422734"));
    }
}
