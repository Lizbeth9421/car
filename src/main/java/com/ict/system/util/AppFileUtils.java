package com.ict.system.util;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Properties;


/**
 * 文件上传下载的工具类
 *
 * @author Lizbeth9421
 */
public class AppFileUtils {

    /**
     * 得到文件上传的路径
     */
    public static String PATH = "F:/upload/";

    static {
        final InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        final Properties properties = new Properties();
        try {
            properties.load(stream);
            AppFileUtils.PATH = properties.getProperty("path");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件下载
     *
     * @param response
     * @param path
     * @param oldName
     * @return
     */
    public static ResponseEntity<Object> downloadFile(final HttpServletResponse response, final String path, String oldName) {
        //4,使用绝对路径+相对路径去找到文件对象
        final File file = new File(AppFileUtils.PATH, path);
        //5,判断文件是否存在
        if (file.exists()) {
            try {
                try {
                    //如果名字有中文 要处理编码
                    oldName = URLEncoder.encode(oldName, "UTF-8");
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                //把file转成一个bytes
                final byte[] bytes = FileUtils.readFileToByteArray(file);
                final HttpHeaders header = new HttpHeaders();
                //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //设置下载的文件的名称
                header.setContentDispositionFormData("attachment", oldName);
                //创建ResponseEntity对象
                final ResponseEntity<Object> entity =
                        new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
                return entity;
            } catch (final Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            final PrintWriter out;
            try {
                out = response.getWriter();
                out.write("文件不存在");
                out.flush();
                out.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    /**
     * 根据相对路径删除硬盘上文件
     *
     * @param path
     */
    public static void deleteFileUsePath(final String path) {
        final String realPath = AppFileUtils.PATH + path;
        //根据文件
        final File file = new File(realPath);
        if (file.exists()) {
            file.delete();
        }
    }


    /**
     * 更改文件名
     *
     * @param carimg
     * @param suffix 后缀
     */
    public static String updateFileName(final String carimg, final String suffix) {
        //找到文件
        try {
            final File file = new File(AppFileUtils.PATH, carimg);
            if (file.exists()) {
                file.renameTo(new File(AppFileUtils.PATH, carimg.replace(suffix, "")));
                return carimg.replace(suffix, "");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
