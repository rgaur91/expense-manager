
package com.pr.expense.security;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class OneWayEncryption {
    private static OneWayEncryption instance;

    private OneWayEncryption() {
    }

    public synchronized String oneWayEncrypt(String var1){
        MessageDigest var2 = null;

        try {
            var2 = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6.getMessage());
        }

        try {
            var2.update(var1.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var5) {
            throw new RuntimeException(var5.getMessage());
        }

        byte[] var3 = var2.digest();
		return (new BASE64Encoder()).encode(var3);
    }

    public static synchronized OneWayEncryption getInstance() {
        if(instance == null) {
            instance = new OneWayEncryption();
        }

        return instance;
    }
}
