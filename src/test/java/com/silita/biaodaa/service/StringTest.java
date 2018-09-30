package com.silita.biaodaa.service;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 91567 on 2018/3/20.
 */
public class StringTest {
    public static void main(String[] args) {
        String dateStr = "2017-03-12";
        System.out.println(dateStr.substring(0,4));

        Map ss= new HashMap();
        System.out.println(ss.keySet().size());
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
}
