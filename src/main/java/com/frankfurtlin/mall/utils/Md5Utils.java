package com.frankfurtlin.mall.utils;

import com.frankfurtlin.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/25 10:50
 * 通过MD5实现加密效果
 */
public class Md5Utils {
    public static String getMd5String(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(messageDigest.digest((value + Constant.SALT).getBytes()));
    }

}
