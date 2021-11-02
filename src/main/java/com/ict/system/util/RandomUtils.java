package com.ict.system.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机工具类
 *
 * @author Lizbeth9421
 */
public class RandomUtils {


    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final Random random = new Random();
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");

    /**
     * 得到当前日期
     */
    public static String getCurrentDateForString() {
        return RandomUtils.sdf1.format(new Date());
    }


    /**
     * 生成文件名使用时间+4位随机数
     *
     * @param fileName 文件名称
     */
    public static String createFileNameUseTime(final String fileName) {
        final String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        final String time = RandomUtils.sdf2.format(new Date());
        final Integer num = RandomUtils.random.nextInt(9000) + 1000;
        return time + num + fileSuffix;
    }

    /**
     * 生成文件名使用时间+4位随机数
     *
     * @param fileName 文件名称
     * @param suffix   临时文件后缀
     */
    public static String createFileNameUseTime(final String fileName, final String suffix) {
        final String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        final String time = RandomUtils.sdf2.format(new Date());
        final Integer num = RandomUtils.random.nextInt(9000) + 1000;
        return time + num + fileSuffix + suffix;
    }

    /**
     * 生成文件名使用UUID
     *
     * @param fileName 文件名称
     */
    public static String createFileNameUseUUID(final String fileName) {
        final String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return UUID.randomUUID().toString().replace("-", "").toUpperCase() + fileSuffix;
    }


    /**
     * 根据时间+五位随机数成字符串
     *
     * @param preffx
     * @return
     */
    public static String createRandomStringUseTime(final String preffx) {
        return preffx + "_" + RandomUtils.sdf3.format(new Date()) + "_" + (RandomUtils.random.nextInt(90000) + 10000);
    }
}
