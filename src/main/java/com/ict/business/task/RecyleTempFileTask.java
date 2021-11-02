package com.ict.business.task;

import com.ict.system.constant.constant;
import com.ict.system.util.AppFileUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/09/16:10
 */
@Component
//开启定时任务
@EnableScheduling
public class RecyleTempFileTask {

    /**
     * 每天晚上十二点执行，删除临时文件
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public static void recyleTempFile() {
        final File file = new File(AppFileUtils.PATH);
        RecyleTempFileTask.deleteFile(file);
    }


    /**
     * 删除文件
     *
     * @param file 需要遍历的文件
     */
    public static void deleteFile(final File file) {
        if (file != null) {
            final File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (final File f : files) {
                    if (f.isFile()) {
                        if (f.getName().endsWith(constant.DEFAULT_CAR_IMG)) {
                            f.delete();
                        }
                    } else {
                        //如果是文件夹就再遍历一次，然后删除文件
                        RecyleTempFileTask.deleteFile(f);
                    }
                }
            }
        }
    }


}
