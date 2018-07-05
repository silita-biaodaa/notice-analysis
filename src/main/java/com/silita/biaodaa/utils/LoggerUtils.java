package com.silita.biaodaa.utils;

import org.slf4j.Logger;

public class LoggerUtils {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerUtils.class);

    public static void showJVM(){
        showJVM("");
    }

    public static void showJVM(String str){
        double memTotal = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
        logger.info(str+"当前jvm内存用量："+memTotal+"MB");
    }
}
