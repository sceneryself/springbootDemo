package com.self.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static Logger log = LoggerFactory.getLogger("MD5Utils");

    public static byte[] defaultMD5(String originString) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(originString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("md5 ", e);
        } catch (NoSuchAlgorithmException e){
            log.error("md5 ", e);
        }
        return null;
    }

    public static String encodeByMD5_16(String originString) {
        String sn = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(originString.getBytes());
            StringBuffer ret = new StringBuffer();
            for (byte c : digest) {
                byte b1 = (byte) '0';
                byte u1 = (byte) '9';
                byte b2 = (byte) 'A';
                byte u2 = (byte) 'Z';

                c &= 0x7F; // range 0 ~ 127

                if (c < b1) {
                    c %= (u1 - b1 + 1);
                    c += b1;
                } else if (c > u1 && c < b2 || c > u2) {
                    c %= (u2 - b2 + 1);
                    c += b2;
                }
                ret.append((char) c);
            }
            sn = ret.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("encodeByMD5 NoSuchAlgorithmException", e);
        }
        return sn;
    }

    public static String encodeByMD5_32(String originString) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(originString.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("encodeByMD5 NoSuchAlgorithmException", e);
        }
        return result;
    }
}
