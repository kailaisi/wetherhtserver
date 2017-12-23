package com.kailaisi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class CommonUtils {
    public CommonUtils() {
        throw new RuntimeException("u cannot init me.....");
    }

    /**
     * 获取系统信息
     *
     * @return
     */
    public static String getOsName() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static String getFileMd5(File f) {
        if (!f.exists() || !f.isFile()) {
            return null;
        }
        FileInputStream inputStream = null;
        BigInteger bigInteger = null;
        try {
            inputStream = new FileInputStream(f);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(buffer, 0, 1024)) != -1) {
                md5.update(buffer, 0, length);
            }
            bigInteger = new BigInteger(1, md5.digest());
            return bigInteger.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
