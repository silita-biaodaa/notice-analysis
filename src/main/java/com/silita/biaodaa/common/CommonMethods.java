package com.silita.biaodaa.common;

import com.silita.biaodaa.utils.ChineseCompressUtil;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dh on 2018/3/19.
 */
public class CommonMethods {

    private static Log logger = LogFactory.getLog(CommonMethods.class);

    public static String getClassString(Class cls){
        if(cls !=null){
            String name = cls.getName();
            int endIdx = name.lastIndexOf(".");
            return name.substring(endIdx+1);
        }else{
            return null;
        }
    }

    /**
     * 获取需要解析的片段
     * @param esNotice
     * @return
     */
    public static String[] buildAnalysisList(EsNotice esNotice,String split)throws Exception{
        if(MyStringUtils.isNull(esNotice.getContent())){
            throw new Exception("公告内容为空。[esNotice.getContent():"+esNotice.getContent()+"]");
        }
        ChineseCompressUtil util = new ChineseCompressUtil();
        String plainHtml = util.getPlainText(esNotice.getContent());
        String[] str = plainHtml.split(split);
        if(str.length>1) {
            str = Arrays.copyOf(str, str.length + 1);
            str[str.length - 1] = plainHtml;
        }
        return str;
    }

    @Deprecated //// TODO: 2018/4/2 此方法後面考慮刪除 ，由preAnalysis包中的規則替換
    public static String[] buildAnalysisList(EsNotice esNotice,String split1,String split2)throws Exception{
        if(MyStringUtils.isNull(esNotice.getContent())){
            throw new Exception("公告内容为空。[esNotice.getContent():"+esNotice.getContent()+"]");
        }
        ChineseCompressUtil util = new ChineseCompressUtil();
        String plainHtml = util.getPlainText(esNotice.getContent());
        String[] str = plainHtml.split(split1);
        List<String> list = Arrays.asList(str);
        List<String> mainList = new ArrayList<>();
        for(String temp : list){
            String [] str2 = temp.split(split2);
            List<String> list2 = Arrays.asList(str2);
            mainList.addAll(list2);
        }
        String[] array = new String[mainList.size()];
        String [] main = mainList.toArray(array);
        if(main.length>1) {
            main = Arrays.copyOf(main, main.length + 1);
            main[main.length - 1] = plainHtml;
        }
        return main;
    }

}
