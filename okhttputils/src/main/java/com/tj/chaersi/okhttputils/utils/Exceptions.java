package com.tj.chaersi.okhttputils.utils;

/**
 * Created by chaersi on 15/12/14.
 */
public class Exceptions {
    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }


}
