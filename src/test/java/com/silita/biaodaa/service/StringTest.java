package com.silita.biaodaa.service;

import com.silita.biaodaa.utils.MyStringUtils;
import org.junit.Test;

/**
 * Created by 91567 on 2018/3/20.
 */
public class StringTest {
    private static final String[] endKeys = {"公司","研究院"};


    public static void main(String[] args) {
//        String dateStr = "2017-03-12";
//        System.out.println(dateStr.substring(0,4));
//
//        Map ss= new HashMap();
//        System.out.println(ss.keySet().size());
        String s = "单位名称新疆成汇工程管理研究院有限投标报价小写217800.00大写贰拾壹公司万柒仟捌佰圆整投标工期107(日历天)质量标准合格总监姓名刘永恒注册级别房屋建筑工程";
        System.out.println(customfilterResult(s));
    }

    @Test
    public void repTest(){
        String s = "城市交通设计研究院；<br>第二中标候选人：大连市市政设计研究院有限责任公司；<br>第三中标候选人：大连理工大学土木建筑设计研究院有限公司(联合体：中冶沈勘工程技术有限公司)。</p>";
        String r = ")";
        if(r.equals(")")){
            r = "["+r+"]";
        }
        s.replaceFirst(r,"");

    }

    @Test
    public void testESC(){
        String t = "速度快(放假)sdfk速度快（sd sdk）111222222222222222(代收款)";
        System.out.println(convertESC(t));
    }

    private String convertESC(String s){
        String[] e = {"(","（","）",")"};
        for (int i=0; i<e.length;i++){
            int si = s.indexOf(e[i]);
            do {
                if(si !=-1){
                    s = s.substring(0,si)+"\\"+s.substring(si);
                }
                si = s.indexOf(e[i],si+2);
            }while (si!=-1);
        }
        return s;
    }

    protected static String customfilterResult(String analysisResult){
        if(MyStringUtils.isNotNull(analysisResult)){
            int len = analysisResult.length();
            for(String endKey: endKeys){
                int kIdx = analysisResult.indexOf(endKey);
                int kLen = endKey.length();
                if(kIdx!= -1){
                    if((kIdx+kLen) < len){//满足截取条件
                        analysisResult=analysisResult.substring(0,kIdx+kLen);
                        break;
                    }
                }else{
                    continue;
                }
            }
        }
        return analysisResult;
    }
}


