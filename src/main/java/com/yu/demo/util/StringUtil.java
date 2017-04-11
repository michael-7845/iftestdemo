package com.yu.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/30.
 */
public class StringUtil {
    public static String removeNonPrinted(String original) {
        Pattern p = Pattern.compile("(\\s+|\\t|\\r|\\n)");
        Matcher m = p.matcher(original);
        String result = m.replaceAll("");
        return result;
    }
}
