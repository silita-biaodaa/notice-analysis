package com.silita.biaodaa.common;

import com.silita.biaodaa.utils.ChineseCompressUtil;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;

import java.util.Arrays;

/**
 * Created by dh on 2018/3/19.
 */
public class CommonMethods {

    /**
     * 获取需要解析的片段
     * @param esNotice
     * @return
     */
    public static String[] buildAnalysisList(EsNotice esNotice)throws Exception{
        if(MyStringUtils.isNull(esNotice.getContent())){
            throw new Exception("公告内容为空。[esNotice.getContent():"+esNotice.getContent()+"]");
        }
        ChineseCompressUtil util = new ChineseCompressUtil();
        String plainHtml = util.getPlainText(esNotice.getContent());
        String[] str = plainHtml.split("</p>");
        str = Arrays.copyOf(str,str.length+1);
        str[str.length-1]=plainHtml;
        return str;
    }
}
