package cn.myesn.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Utils {

    public static String encrypt(String value) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(messageDigest.digest(value.getBytes(StandardCharsets.UTF_8)));
    }
}
