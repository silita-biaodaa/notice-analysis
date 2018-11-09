package com.silita.biaodaa.utils;


import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;

public class LoggerUtils {
    private static Logger logger = Logger.getLogger(LoggerUtils.class);

    public static void showJVM(){
        showJVM("");
    }

    public static void showJVM(String str){
        double memTotal = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
        logger.info(str+"当前jvm内存用量："+memTotal+"MB");
    }

    public static void debugTrace(String info, EsNotice n,Logger logObj){
        if(logObj==null){
            logObj= logger;
        }
        if(n!=null) {
            logObj.debug(info + "[title:" + n.getTitle() + "][redis:" + n.getRedisId() + "][source:" + n.getSource() + "][url:"+n.getUrl()+"]");
        }else{
            logObj.debug(info);
        }
    }

    public static String buildRow(String [] arr){
        StringBuilder sb = new StringBuilder();
        for(int y=0; y<arr.length;y++){
            sb.append("||||"+arr[y]);
        }
        return sb.toString();
    }

    public static void infoArray(String[][] array){
        StringBuilder sb = new StringBuilder("\n");
        for(int x=0; x<array.length;x++){
            sb.append("第"+(x+1)+"行");
            for(int y=0; y<array[x].length;y++){
                logger.debug("array["+x+"]["+y+"]:"+array[x][y]);
                sb.append("||||"+array[x][y]);
            }
            sb.append("\n");
        }
        logger.info(sb.toString());
    }


}
