package com.gameball.tools;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;

public class MD5Util {
    public static String MD5(String str) {
        try {
            int i;
            MessageDigest md5 = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            char[] charArray = str.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (byte b : md5Bytes) {
                int val = b & 255;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
