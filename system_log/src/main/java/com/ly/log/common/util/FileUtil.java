package com.ly.log.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.Properties;

public class FileUtil {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FileUtil.class);

    public static Properties loadPropertiesFileByPath(String filePath) {
        InputStream inputStream = null;
        Properties p =null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            p = new Properties();
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream){
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return p;
    }


    public static void main(String [] args){
    }
}
