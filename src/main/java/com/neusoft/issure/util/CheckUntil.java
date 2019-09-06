package com.neusoft.issure.util;
import java.util.Arrays;

public class CheckUntil {

    private static final String token = "这里写的你的token";

    public static boolean checkSignatures(String signature, String timestamp, String nonce) {
        String[] strings = new String[]{nonce, token, timestamp};
        Arrays.sort(strings);
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : strings) {
            stringBuffer.append(string);
        }
        if (SHA1.encode(stringBuffer.toString()).equals(signature)) {
            return true;
        }
        return false;
    }
}

