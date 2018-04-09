package com.silita.biaodaa.service;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dh on 2018/3/21.
 */
public class RegexTest {
    public static void main(String args[]) {
        String str = "8.3招标文件获取地点：日照市公共资源交易中心（日照市国际金融中心B座2楼，烟台路与济南路交汇处）报名与购买招标文件。 HELLO ";
        String pattern = "(EL)+/S*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());

//        test2();
    }

    @Test
    public void test1(){
        String content = "投标报名</h3> </div>  <div>凡有意参加投标者，请于 2018 年 01月 19日 09时 00分至 2018年 01月 23日 16时 00分，通过远程或者到招标投标交易场所使用数字身份认证锁登录电子化平台（网址： www.bcactc.com）报名，并保存报名成功回执。  </div>  <div> <h3>5. 招标文件的获取</h3> </div>  <div>5.1 凡通过上述报名者，请于 2018 年 01月 22日 09时 00分至 2018年 01月 26日 16时 00分，通过远程或者到招标投标交易场所使用数字身份认证锁登录电子化平台（网址： www.bcactc.com ）下载招标文件。  5.2 凡通过上述报名者，请于 2018 年 01月 22日 09时 00分至 2018年 01月 26日 16时 00分，在  中建精诚工程咨询有限公司招标部（北京市西城区车公庄大街9号五栋大楼C座五层502室）（详细地址）持报名成功回执领取招标图纸。图纸押金 1000元，在退还图纸时退还（不计利息）。 </div>  <div> <h3>6. 投标文件的递交</h3> </div>  <div>6.1 投标文件递交的截止时间为 2018 年 02月 12日 14时 00分。投标人应当通过远程或者到招标投标交易场所使用数字身份认证锁登录电子化平台（网址： www.bcactc.com）上传，并保存文件上传成功回执，递交时间即为上传成功回执时间  6.2 逾期送达的投标文件，招标人不予受理。 </div>  <div> <h3>7. 发布公告的媒介</h3> </div>  <div>本次招标公告同时在 中国采购与招标网上发布。 </div>  <div> <h3>8. 联系方式</h3> </div>  <div> <table class='table table-bordered'> <tbody> <tr> <td>招 标 人：中国建筑第二工程局有限公司</td> <td>招标代理机构：中建精诚工程咨询有限公司</td> </tr> <tr> <td>地址：北京市通州区梨园镇北杨洼251号</td> <td>地址：北京市西城区车公庄大街9号院1号楼1门301室(德胜园内)</td> </tr> <tr> <td>邮编：100054</td> <";
        String pattern = "(123登录电子化平台\\S?).{1,100}(报名[。，,.])+";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);

        System.out.println(m.find());
        while (m.find()) {
//            System.out.println(m.group());
            System.out.println(m.group().replaceFirst(m.group(1),"").replaceFirst(m.group(m.groupCount()),""));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println("start(): "+m.start());
            System.out.println("end(): "+m.end());
            System.out.println(content.substring(m.start(),m.end()));
        }
    }

    private static void test2(){

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)((\\d+)(.*))";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
//        System.out.println(m.groupCount());

       while (m.find()) {
           System.out.println("value:"+m.group());
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        }
    }


    private static final String REGEX = "\\bcat\\b";
    private static final String INPUT =
            "cat cat cat cattie cat";

    @Test
    public  void test3( ){
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        int count = 0;

        while(m.find()) {
            count++;
            System.out.println("Match number "+count);
            System.out.println("start(): "+m.start());
            System.out.println("end(): "+m.end());
        }
    }



    @Test
    public void regexMatches(){
        final String REGEX = "foo";
        final String INPUT = "fooooooooooooooooo";
        final String INPUT2 = "ooooofoooooooooooo";
        Pattern pattern;
        Matcher matcher;
        Matcher matcher2;
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);
        matcher2 = pattern.matcher(INPUT2);

        System.out.println("Current REGEX is: "+REGEX);
        System.out.println("Current INPUT is: "+INPUT);
        System.out.println("Current INPUT2 is: "+INPUT2);


        System.out.println("lookingAt(): "+matcher.lookingAt());
        System.out.println("matches(): "+matcher.matches());
        System.out.println("lookingAt(): "+matcher2.lookingAt());
    }

    @Test
    public void replace() {
        String REGEX = "dog";
        String INPUT = "The dog says meow. All dogs say meow.";
        String REPLACE = "cat";
        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT);
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);
    }


}
