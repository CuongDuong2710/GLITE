package com.glite.popeyes.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Tran Huy Phuc
 * @since 7/10/16
 */
public class EncryptUtils {

    /**
     * Convert plain string to sha1 string
     *
     * @param s plain string
     * @return converted SHA1 string
     */
    public static String sha1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(
                        (0xFF & messageDigest[i]) | 0x100).substring(1));
            }
            Log.e("SHA1", hexString.toString());
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (NullPointerException e) {
            return "";
        }
    }
}
