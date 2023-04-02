package com.blog.ranger.utils;

/**
 * @create 2023-04-01-21:16
 */
public class StrMapIntUtils {
    public static Integer strs2Int(String... strs) {
        String s = "";
        for (String str : strs)
            s += str;
        Integer res = 0;
        for (int i = 0; i != s.length(); ++i)
            res += s.charAt(i);
        return res;
    }
}
