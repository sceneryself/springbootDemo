package com.self.util;

import java.util.regex.Pattern;

public class RegexUtil {

    public static final String REGEX_MOBILE = "^(1[3,4,5,7,8][0-9])\\\\d{8}$";
    public static final String REGEX_PHONE = "^([0-9]{3}-?[0-9]{8})|([0-9]{4}-?[0-9]{7})$";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean isMobile(String mobile){
        Pattern p = Pattern.compile(REGEX_MOBILE);
        return p.matcher(mobile).matches();
    }

    public static boolean isPhone(String phone){
        Pattern p = Pattern.compile(REGEX_PHONE);
        return p.matcher(phone).matches();
    }

    public static boolean isEmail(String email){
        Pattern p = Pattern.compile(REGEX_EMAIL);
        return p.matcher(email).matches();
    }

}
