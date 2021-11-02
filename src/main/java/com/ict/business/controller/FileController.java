package com.ict.business.controller;

import com.ict.system.constant.constant;
import com.ict.system.util.AppFileUtils;
import com.ict.system.util.DataGridView;
import com.ict.system.util.RandomUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/07/23:28
 */
@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * @param mf
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/uploadFile")
    public DataGridView uploadFile(final MultipartFile mf) throws IllegalStateException, IOException {
        //得到文件上传的父目录
        final String parentPath = AppFileUtils.PATH;
        //得到当前日期作为文件夹的名称
        final String dirName = RandomUtils.getCurrentDateForString();
        //构建文件夹对象
        final File dirFile = new File(parentPath, dirName);
        if (!dirFile.exists()) {
            //若该文件夹不纯在，创建文件夹
            dirFile.mkdirs();
        }
        //得到文件原名
        final String oldName = mf.getOriginalFilename();
        //根据文件原名得到新名
        final String newName = RandomUtils.createFileNameUseTime(oldName, constant.FILE_UPLOAD_TEMP);
        final File dest = new File(dirFile, newName);
        mf.transferTo(dest);

        final Map<String, Object> map = new HashMap<>();
        map.put("src", dirName + "/" + newName);
        return new DataGridView(map);
    }

    /**
     * 不下载只显示
     */
    @RequestMapping("/downloadShowFile")
    public ResponseEntity<Object> downloadShowFile(final String path, final HttpServletResponse response) {
        return AppFileUtils.downloadFile(response, path, "");
    }

    /**
     * 下载图片
     *
     * @param path
     * @param response
     * @return
     */
    @RequestMapping("/downloadFile")
    public ResponseEntity<Object> downloadFile(final String path, final HttpServletResponse response) {
        final String oldName = "";
        return AppFileUtils.downloadFile(response, path, oldName);
    }
}
