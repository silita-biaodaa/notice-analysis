package com.silita.biaodaa.service;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dh on 2018/3/21.
 */
public class RegexTest {
    public static void main(String args[]) {
//        String str = "8.3招标文件获取地点：日照市公共资源交易中心（日照市国际金融中心B座2楼，烟台路与济南路交汇处）报名与购买招标文件。 HELLO ";
//        String pattern = "(EL)+/S*";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(m.matches());

//        test2();
    }

    @Test
    public void test1(){
        String content = "<table class='table table-bordered'><tbody><tr><td><style></style>\n" +
                "<div>临沂市临港经济开发区龙潭湾水库工程项目-工程质量检测及科学研究试验二次招标公告-检测标,其他标招标公告</div>\n" +
                "<div>1.招标条件\n" +
                "<div>临沂市临港经济开发区龙潭湾水库工程项目-工程质量检测及科学研究试验二次招标公告已经山东省水利厅以“鲁水发规字【2014】51号”批准建设，项目法人和招标人为临沂临港经济开发区龙潭湾拦河闸工程建设管理局，建设资金已经落实，项目已具备招标条件。现委托山东金润建设咨询有限公司对该项目检测标,其他标进行国内公开招标。</div></div>\n" +
                "<div>2.招标内容和招标范围\n" +
                "<div>本次招标为2个标段。检测标：工程质量检测标段标段内容:对龙潭湾水库工程所包含的全部永久工程中各主要建筑物及其主要结构部件和设施设备进行竣工验收质量抽检，并出具质量检测报告。其他标：蓄水安全鉴定标段标段内容:对龙潭湾水库工程中与蓄水安全有关的工程设计、施工、设备制造与安装的质量进行检查，对影响工程安全的因素进行评价，提出蓄水安全鉴定意见，明确是否具备水库蓄水验收的条件，并出具蓄水安全鉴定报告。详细招标内容见招标文件。详细招标内容见招标文件。</div>\n" +
                "<div>龙潭湾水库建于龙王河干流中游，临沂市临港经济开发区境内，坝址位于壮岗镇西北侧1.0km处。工程由大坝、溢洪道、放水洞、泵站等组成。</div>\n" +
                "<div>本次招标为2个标段。其他工程标：临沂临港经济开发区龙潭湾水库蓄水安全鉴定标标段内容:对龙潭湾水库工程中与蓄水安全有关的工程设计、施工、设备制造与安装的质量进行检查，对影响工程安全的因素进行评价，提出蓄水安全鉴定意见，明确是否具备水库蓄水验收的条件，并出具蓄水安全鉴定报告。资格审查项:1.资质证书.2.营业执照.3.检察机关出具的无行贿犯罪查询结果告知函.4.法人代表授权委托书.5.投标保证金电汇凭证.6.授权代表是否为固定投标人员.7.技术负责人职称证.质量检测标：临沂临港经济开发区龙潭湾水库工程质量检测标标段内容:对龙潭湾水库工程所包含的全部永久工程中各主要建筑物及其主要结构部件和设施设备进行竣工验收质量抽检，并出具质量检测报告资格审查项:1.资质证书.2.营业执照.3.检察机关出具的无行贿犯罪查询结果告知函.4.法人代表授权委托书.5.投标保证金电汇凭证.6.授权代表是否为固定投标人员.7.项目负责人的职称证和水利工程质量检验员资格证书.</div></div>\n" +
                "<div>3.规模\n" +
                "<div>龙潭湾水库建于龙王河干流中游，临沂市临港经济开发区境内，坝址位于壮岗镇西北侧1.0km处。工程由大坝、溢洪道、放水洞、泵站等组成。</div></div>\n" +
                "<div>4.资金来源\n" +
                "<div>财政投资</div></div>\n" +
                "<div>5.投标人资格要求\n" +
                "<div>5.1检测标：投标人须具有水利工程质量检测（同时具有岩土工程类、混凝土工程类、量测类、金属结构和机械电气类）乙级及以上资质，并在山东省水利厅有水利工程质量检测备案手续，人员、设备、资金等方面具有承担本项目的能力,拟派本项目项目负责人具有水利水电相关专业中级及以上技术职称。5.2蓄水安全鉴定标：投标人必须是中华人民共和国境内注册,具有独立法人资格,在水利部文件公布的蓄水安全鉴定单位名单中，从事与本招标项目相关的科研院所、高等院校或设计单位。其中设计单位应具有工程设计综合乙级资质，或具有水利行业乙级资质；拟投入本项目的项目负责人必须是投标申请人本单位在岗人员，且具有副高级或以上技术职称。5.3与招标人存在利害关系可能影响招标公正性的法人、其他组织或者个人，不得参加投标。5.4未经山东省水利建设市场信用信息平台公布信用信息的企业，不得参加本工程投标。凡由水利部或工程所在地省级水行政主管部门在其信用信息平台公布禁止进入水利建设市场或列入黑名单的，不得参加本工程投标。5.5本次招标不接受联合体投标。5.6本次招标采用资格后审方式，资格审查的具体要求见招标文件。5.7投标人报名前必须在临沂市公共资源交易中心办理入库备案并打印网上报名回执单。</div>\n" +
                "<div>本次招标不接受联合体投标</div></div>\n" +
                "<div>6.其他要求或说明\n" +
                "<div>6.1开标时间：2018年4月10日下午14时30分6.2开标地点：临沂市政务服务中心5楼（北城新区北京路8号）6.3不统一组织现场踏勘。</div></div>\n" +
                "<div>7.发布公告的媒介\n" +
                "<div>本次招标公告同时在《中国采购与招标网》、《山东省采购与招标网》、《山东省水利工程招标投标公共服务平台》、《临沂市公共资源交易中心网》上发布。</div></div>\n" +
                "<div>8.获取招标文件时间、地点及售价\n" +
                "<div>8.1招标文件获取时间：2018-03-1908:30:00~2018-03-2317:00:00（北京时间）。</div>\n" +
                "<div>8.2招标文件售价：每标段售价￥500元，现金支付，售后不退。</div>\n" +
                "<div>8.3招标文件获取地点：临沂市政务服务中心5楼代理服务区12号窗口（北城新区北京路8号）</div>\n" +
                "<div></div></div>\n" +
                "<div>9.联系方式<table class='table table-bordered'><tbody><tr><td>招标人</td><td>临沂临港经济开发区龙潭湾拦河闸工程建设管理局</td><td>招标代理机构</td><td>山东金润建设咨询有限公司</td></tr><tr><td>联系人</td><td>葛主任</td><td>联系人</td><td>王工郭工</td></tr><tr><td>联系电话</td><td>0539-7558919</td><td>联系电话</td><td>1866960999118653975151</td></tr><tr><td>电子邮箱</td><td></td><td>电子邮箱</td><td>sdjrly@163.com</td></tr><tr><td>地址</td><td>临沂市临港经济开发区</td><td>地址</td><td>临沂北城新区济南路与汶河路交汇处</td></tr></tbody></table></div></td></tr></tbody></table>";
        String pattern = "(招标文件获取地点\\S?).*(</.*\\b)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);

        System.out.println(m.groupCount());
        while (m.find()) {
            System.out.println(m.group());
            System.out.println(m.group().replaceFirst(m.group(1),"").replaceFirst(m.group(2),""));
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
