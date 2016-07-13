package com.itsm.util;

/**
 * Created by itsm on 16/7/2.
 */
public class Utils {
    private static final char UNDERLINE = '_';

    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String firstLetterToUpper(String str){
        char[] array = str.toCharArray();
        array[0] -= 32;
        return String.valueOf(array);
    }
    public static String firstLetterToLower(String str){
        char[] array = str.toCharArray();
        array[0] += 32;
        return String.valueOf(array);
    }


}
