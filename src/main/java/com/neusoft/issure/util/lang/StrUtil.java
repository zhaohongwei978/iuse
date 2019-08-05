package com.neusoft.issure.util.lang;

/**
 * @Title:  StrUtil.java
 * @Description:  (用一句话描述该文件做什么)
 * @author: jetty
 * @date:   2019/3/11 11:59
 * @version V1.0
 */
public class StrUtil {

    private static final char CHAR_UPPER_A = 'A';
    private static final char CHAR_UPPER_Z = 'Z';
    private static final char CHAR_LOWER_A = 'a';
    private static final char CHAR_LOWER_Z = 'z';

    protected StrUtil() {
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static String replaceOnce(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, 1);
    }

    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    public static String replace(String text, String searchString, String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase >= 0 ? increase : 0;
        increase *= max >= 0 ? max <= 64 ? max : 64 : 16;
        StringBuilder buf = new StringBuilder(text.length() + increase);
        do {
            if (end == -1) {
                break;
            }
            buf.append(text, start, end).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        } while (true);
        buf.append(text.substring(start));
        return buf.toString();
    }

    public static String substring(String text, int beginIndex) {
        if (text == null) {
            return null;
        }
        return text.substring(beginIndex);
    }

    public static String substring(String text, int beginIndex, int endIndex) {
        if (text == null) {
            return null;
        }
        return text.substring(beginIndex, endIndex);
    }

    public static String lastSubstring(String text, int lastBeginIndex) {
        if (text == null) {
            return null;
        }
        return text.substring(text.length() - lastBeginIndex);
    }

    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= CHAR_UPPER_A && firstChar <= CHAR_UPPER_Z) {
            char[] arr = str.toCharArray();
            arr[0] += (CHAR_LOWER_A - CHAR_UPPER_A);
            return new String(arr);
        }
        return str;
    }

    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= CHAR_LOWER_A && firstChar <= CHAR_LOWER_Z) {
            char[] arr = str.toCharArray();
            arr[0] -= (CHAR_LOWER_A - CHAR_UPPER_A);
            return new String(arr);
        }
        return str;
    }
}
