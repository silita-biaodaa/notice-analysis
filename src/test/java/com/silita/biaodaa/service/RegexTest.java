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
    String content="<table id=\"InfoDetail_tblInfo\" cellspacing=\"1\" cellpadding=\"1\" width=\"100%\" align=\"center\" border=\"0\">\n" +
            "\t<tbody><tr>\n" +
            "\t\t<td id=\"InfoDetail_tdTitle\" align=\"center\">\n" +
            "\t\t\t<font size=\"4\">\n" +
            "\t\t\t\t<span id=\"InfoDetail_lblTitle\" style=\"color:#000000;font-weight:bold;size:25pxpt;\">临澧县城乡一体化智能交通PPP项目 (第二次)资格预审公告</span></font>\n" +
            "\t\t\t<hr size=\"1\">\n" +
            "\t\t\t<span id=\"InfoDetail_lblDate\" style=\"color:Silver;\">（更新时间：2018-02-12&nbsp;&nbsp;阅读次数：646）</span></td>\n" +
            "\t</tr>\n" +
            "\t<tr id=\"InfoDetail_trLine\" height=\"1\">\n" +
            "\t</tr>\n" +
            "\t<tr>\n" +
            "\t\t<td height=\"400\" valign=\"top\">\n" +
            "\t\t\t<!--EpointContent--><span id=\"InfoDetail_spnContent\" class=\"infodetail\"><p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">本采购项目临澧县城乡一体化智能交通</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">PPP</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">项目已批准实施采用</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">PPP</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">模式，临澧县人民政府授权临澧县交通运输局作为本项目实施机构，现委托湖南天鉴工程项目管理有限公司进行招标采购，特邀请各潜在投标人（以下简称申请人）提出资格预审申请。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><a name=\"_Toc488845993\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">1</span></b></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">.</span></b></span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> </span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">项目信息</span></b><b></b></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; MARGIN-LEFT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）项目名称</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">:</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">临澧县城乡一体化智能交通</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">PPP</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">项目</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; MARGIN-LEFT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）采购计划编号：临财采计</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">[2018]000047</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）采购代理编号：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">HNTJCD2018-CG-003</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><a name=\"_Toc488845994\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">2</span></b></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">.</span></b></span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">项目概况与</span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; Arial: \">采购范围</span></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）项目内容：本项目静态总投资</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">(</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">不含建设期利息</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">)</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">约为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">164131.59</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">万元，共</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">4</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个子项目，其中：</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）临澧县城乡交通一体化项目</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">沅澧快速干线</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">号大道、东常高速临澧连接线及省道</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">S229</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">三段等共计</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">5</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">条道路，投资估算为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">149331.6</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">万元；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）临澧县城社会停车场项目：</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">总占地面积为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">15</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">亩，建设内容为具有</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">800</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">㎡的仓储能力及配套汽修和洗车服务能力的</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个停车场，投资估算为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">7079</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">万元；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）临澧县城乡公交一体化项目：</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">建设内容为枢纽站</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个，停保场</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个，客运站</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">16</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个，公交首末站</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">8</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个，停靠站、招呼站</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">318</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个及公交智能系统</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">套等工程，投资估算为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">5720.99</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">万元；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">4</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）临澧县城乡物流智能化项目：</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">建设内容为城乡物流智能化平台，投资估算约</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2000</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">万元。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）项目地点：本项目位于临澧县境内；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）建设工期：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">4</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）缺陷责任期：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">24</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个月；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">5</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）质量标准：合格。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">3. </span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; Arial: \">项目授权主体、项目实施机构和项目名称</span></b><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）项目授权主体：临澧县人民政府</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）项目实施机构：临澧县交通运输局</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）项目名称</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">:</span><span> </span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">临澧县城乡一体化智能交通</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">PPP</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">项目</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><a name=\"_Toc489882314\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">4.</span></b></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; Arial: \">采购需求</span></b></span><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）临澧县人民政府授权临澧县交通局作为项目实施机构，与中标社会资本先草签《</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">PPP</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">合同》，待项目公司成立后，再由县交通局与项目公司正式签署《</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">PPP</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">合同》。项目公司拥有本项目的独家经营权，由项目公司负责对本项目进行投资、融资、优化设计、建设和运营维护，项目合作期满后项目公司将项目资产设施无偿完好的移交到县交通局或政府指定机构；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）临澧县人民政府授权临澧县融达交通建设投资有限公司作为政府方股东代表与中标的社会资本共同出资组建项目公司；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><a name=\"_Toc489882315\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span></a><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）合作期限：本项目的合作期限为</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">17</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年（含建设期</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年）；</span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><a name=\"_Toc489882316\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">4</span></a><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）资金来源：本项目静态总投资</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">(</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">不含建设期利息</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">)</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">约为</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">164131.59</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">万元，由项目资本金和融资两部分组成，其中项目资本金部分占项目总投资的</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">20%,</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">融资部分占项目总投资的</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">80%</span></span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">，融资部分由项目公司的社会资本方负责筹措，政府不提供任何抵押、增信；</span></span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">5</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）项目公司股权结构：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">项目公司由中标的社会资本与政府方股东合资在临澧县组建，项目资本金暂定为项目总投资的</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> <span>20% </span></span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">。项目公司中社会资本占</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">90%</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">，政府方股东占</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">10%</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">6</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）回报机制：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">本项目采用“可行性缺口补助”的回报机制。项目公司的收入包括经营性收入和财政补贴两大部分，其中经营性收入主要为停车场、广告及附属设施经营与智能平台等运营收入。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><a name=\"_Toc488845996\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">5</span></b></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">.</span></b></span><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">申请人</span></b></span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">应具备的条件</span></b></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21.1pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><a name=\"OLE_LINK4\"></a><a name=\"OLE_LINK5\"><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>（1）</span></span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">申请人基本资格条件</span></b></span></a><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">：</span></b><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">申请人须是在中国境内依法注册的企业法人，符合《政府采购法》第二十二条规定：</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>1)</span></span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">具有独立承担民事责任的能力；</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>2)</span></span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">具有良好的商业信誉和健全的财务会计制度；</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>3)</span></span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">具有履行合同所必需的设备和专业技术能力；</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>4)</span></span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">有依法缴纳税收和社会保障资金的良好记录；</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>5)</span></span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">参加政府采购活动前三年内，在经营活动中没有重大违法记录；</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>6)</span></span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">法律、行政法规规定的其他条件。</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21.1pt; MARGIN-LEFT: 0cm\" class=\"MsoNormal\" align=\"left\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"><span>（2）</span></span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">申请人特定资格条件</span></b><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><a name=\"OLE_LINK10\"></a><a name=\"OLE_LINK11\"></a><a name=\"OLE_LINK7\"></a><a name=\"OLE_LINK6\"></a><span><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1)</span></b></span></span><span><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">资质要求：</span></b></span></span><span><span><b></b></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1.1</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、具有建设行政主管部门颁发的公路工程施工总承包二级及以上资质证书，安全生产许可证处于有效期。本次招标阶段对湖南省外企业不作入湘登记要求，由中标单位中标后再按照湖南省住房和城乡建设厅湘建建</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">[2015]190</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">号文件要求办理省外入湘企业基本情况登记；</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1.2</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、拟任项目经理应具有公路工程专业一级建造师注册证书、有效的</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">B</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">类安全生产考核合格证书，拟任项目总工应具有相关专业高级工程师职称证书和有效的</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">B</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">类安全生产考核合格证书；</span><span> </span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1.3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2013</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">月</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">日</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">至今，申请人具有</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1 </span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">个及以上</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">PPP</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">项目（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">BOT</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">TOT</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">ROT</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">或其他形式的投融资项目），其单项合同投资金额不少于</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">7</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">亿元人民币（业绩证明材提供合同文件或中标通知书）。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2)</span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">财务要求</span></b><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2.1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、申请人净资产不低于</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">5</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">亿元人民币（具体以上一年度经会计师事务所审计的财务审计报告及报表为准，若未能提供上一年度上述资料的则以提供再上一年度</span><span style=\"FONT-FAMILY: 宋体\">上述资料为准</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2.2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、财务状况良好，最近连续三年（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2014-2016</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">或</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2015-2017</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）每年均为盈利（提供</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2014-2016</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">或</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2015-2017</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年度经会计师事务所审计的财务审计报告及报表），没有处于财产被接管、冻结、破产或其他不良状态，无重大不良资产或不良投资项目；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2.3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、有</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">10</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">亿人民币（或等值货币）及以上的有效的银行授信额度证明（以银行授信证明文件为准，可由多家银行提供，累计总额度不低于</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">10</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">亿元）；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2.4</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、申请人资产负债率小于</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">80%</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（含）；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2.5</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、若申请人为联合体的，联合体牵头人需满足上述财务要求。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3)</span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">信誉要求</span></b><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3.1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、根据《关于在招标投标活动中全面开展行贿犯罪档案查询的通知》（高检汇【</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2015</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">】</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">号）申请人及拟任的项目经理及项目总工近</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年不得存在行贿犯罪记录。申请人在资格预审申请文件中需附申请人注册地或项目所在地检察机关出具的“查询犯罪档案结果告知函”。特别说明：近</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年指</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2015</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">月</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">日</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">至检察机关出具行贿犯罪档案查询结果告知函之日，该函出具日期须介于本资格预审公告发布日期至递交资格预审申请文件截至日期之间；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3.2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、根据《关于政府采购活动中查询及使用信用记录有关问题的通知》（财政部财库【</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2016</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">】</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">125</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">号）文件规定，本次采购要求申请人不得存在以下不良信用记录：被列入失信执行人名单（即有失信记录）、企业经营异常名录、被列入重大税收违法案件当事人名单，信用信息查询的渠道为“信用中国”网站（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">http://www.creditchina.gov.cn/</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）；被列入政府采购严重违法失信行为记录名单且罚期未满，信用信息查询的渠道为：中国政府采购网（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">http://www.ccgp.gov.cn</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3.3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、本次采购要求申请人不得存在以下情况：被湖南省交通运输厅评为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2016</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年度</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">D</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">级、连续两年（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2015</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年和</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2016</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年）评为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">C</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">级、连续三年（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2014</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">～</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2016</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年）评为</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">B</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">级及以下信用等级。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">4)</span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">其他要求：</span></b><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">4.1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、具有投资参股关系的关联企业，或具有直接管理和被管理关系的母子公司，或同一母公司的子公司（国务院国有资产监督管理机构直接监管的中央企业均不属于本条规定</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">“</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">母公司</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">”</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">，其一级子公司可同时对本项目投标，但同属一个子公司的二级子公司不得同时对本项目投标），或法定代表人为同一人的两个及两个以上法人不得同时对本项目提出投标申请</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">,</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">否则均按不合格申请人处理；</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">4.2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">本项目允许联合体投标，</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">联合体申请资格预审的，应满足下列要求：</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><a name=\"OLE_LINK42\"></a><a name=\"OLE_LINK41\"></a><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">4.2.1</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">、联合体成员不得超过<span>3</span>家；</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">4.2.2</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">、联合体共同满足申请人特定资格条件各项要求；</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">4.2.3</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">、联合体申请资格预审的，应当提供联合体协议书，并明确<a name=\"OLE_LINK36\"></a><a name=\"OLE_LINK27\"><span>联合体牵头人</span></a>、权利义务、出资额或出资比例。联合体牵头人代表联合体在资格审查、投标、中标、组建项目公司、项目实施过程中承担其义务和法律责任，联合体其他成员负连带的和各自的法律责任；</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">4.2.4</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">、联合体中有同类资质的申请人按照联合体分工承担相同工作的，应当按照资质等级较低的申请人确定资质等级；</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><a name=\"OLE_LINK38\"></a><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">4.2.5</span></span></span></span><span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">、联合体牵头人</span></span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">代表联合体提交并签署资格预审申请文件、投标文件等；授权委托书应由联合体牵头人的法定代表人签署并加盖公章；</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><a name=\"OLE_LINK40\"></a><a name=\"OLE_LINK39\"></a><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">4.2.6</span></span></span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">、联合体在报名后，联合体的组成结构或职责及出资比例均不得变动；</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">4.2.7</span></span></span><span><span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">、联合体各方签订联合体协议书后，不得再以自己名义单独在本采购项目中投标，也不得组成新的联合体参加本采购项目的投标。</span></span></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">6.</span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; Arial: \">资格预审方法</span></b><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 26.25pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">本资格预审采用合格制，所有通过资格预审的申请人均可参与本项目后续采购。递交资格预审申请文件的申请人数量少于三家，或通过资格预审的申请人数量少于三家的，本次资格预审终止，采购人按照相关规定对后续工作进行安排。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><a name=\"_Toc489882318\"></a><a name=\"OLE_LINK18\"></a><a name=\"OLE_LINK17\"></a><a name=\"OLE_LINK16\"></a><a name=\"OLE_LINK15\"></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">7</span></b></span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">.</span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">申请报名</span></b></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">7.1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、凡符合投标资格要求并有意参加投标者请于</span><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2018</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> <span>2</span></span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">月</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">13</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">日</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">-2018<span style=\"FONT-FAMILY: 宋体\"><span>年</span></span>3<span style=\"FONT-FAMILY: 宋体\"><span>月</span></span>14<span style=\"FONT-FAMILY: 宋体\"><span>日</span></span></span></u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">登录《中国湖南常德市政府采购网</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">http://changd.ccgp-hunan.gov.cn</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）或《常德市公共资源交易网》（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">http:// ggzy.changde.gov.cn</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）下载招标文件；</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">7.2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、下载方式：供应商须办理</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">CA</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">认证卡（数字认证卡）并在《中国湖南常德市政府采购网》注册后自行下载，办理</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">CA</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">认证卡的方法见《中国湖南政府采购网》（</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">http://www.ccgp-hunan.gov.cn</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">）或《中国湖南常德市政府采购网》“</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">CA</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">数字证书办理专栏”。</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> </span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">其下载的招标文件与书面招标文件具有同等法律效力<b>；</b></span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">7.3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">售价：资格预审文件每套售价</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">1000</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">元，售后不退（开标现场收取）。</span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\"><br style=\"PAGE-BREAK-BEFORE: always\" clear=\"all\">8.</span></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">资格预审文件的获取</span></b></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">8.1.</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">时间：</span><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2018</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> 2</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">月</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">13</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">日</span></u><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">-2018<span style=\"FONT-FAMILY: 宋体\"><span>年</span></span>3<span style=\"FONT-FAMILY: 宋体\"><span>月</span></span>14<span style=\"FONT-FAMILY: 宋体\"><span>日</span></span></span></u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">（法定公休日、节假日除外）。</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">8.2.</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">地点：<b>网上获取；</b></span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><a name=\"_Toc489882319\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">9.</span></b></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; Arial: \">递交</span></b></span><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">资格预审文件</span></b></span><b></b></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">9.1</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、提交资格预审申请文件截止时间：</span><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">2018</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">年</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">3</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">月</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">14</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">日</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">14</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">时</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">30</span></u></b><b><u><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">分</span></u></b><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">；</span></b></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">9.2</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、提交资格预审申请文件地点：<b><u>常德市公共资源交易中心（泓鑫桃林商业广场六楼第四开标室）</u>；</b></span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\" align=\"left\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">9.3</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">、逾期提交或者未送达指定地点的资格预审申请文件不予接收。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%\" class=\"MsoNormal\"><a name=\"_Toc489882321\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">10.</span></b></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; Arial: \">发布</span></b></span><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">公告媒介</span></b></span><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 21pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">本次资格预审公告在中国政府采购网、湖南省常德政府采购网</span><span style=\"FONT-FAMILY: 宋体\">、常德市公共资源交易网</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">上发布。</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; tab-stops: 256.2pt\" class=\"MsoNormal\"><a name=\"_Toc489882322\"><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">1</span></b></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">1</span></b></span><a name=\"_Toc489882323\"></a><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">.</span></b></span><span><b><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '楷体_GB2312;Arial Unicode MS','serif'\">联系方式</span></b></span><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">采购人：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">临澧县交通运输局</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">地址：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> </span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">临澧县安福镇朝阳西街</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">158</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">号</span><b></b></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">联系人：叶先生</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> </span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">联系电话</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">: 13973640263</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\">&nbsp;</p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">采购代理机构：湖南天鉴工程项目管理有限公司</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> </span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">地址：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体\">常德市武陵区柳叶大道<span>3038</span>号常德商会大厦<span>26</span>楼（市联通公司旁）</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">联系人：熊向阳</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\"> </span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">宋超俊</span></p>\n" +
            "<p style=\"LINE-HEIGHT: 150%; TEXT-INDENT: 23.65pt\" class=\"MsoNormal\"><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; SimSun: \">联系电话：</span><span style=\"LINE-HEIGHT: 150%; FONT-FAMILY: '宋体;SimSun','serif'\">13507361276 18974207628</span></p>\n" +
            "<p style=\"TEXT-ALIGN: left; punctuation-trim: leading\" class=\"MsoNormal\" align=\"left\">&nbsp;</p>\n" +
            "<p style=\"TEXT-ALIGN: right; punctuation-trim: leading\" class=\"MsoNormal\" align=\"right\">&nbsp;</p><p></p></span></td></tr><tr><td valign=\"top\"><table cellspacing=\"0\" border=\"0\" style=\"width:100%;border-collapse:collapse;\"><tbody><tr><td><font color=\"blue\"><b></b></font></td></tr><tr><td><a href=\"http://ggzy.changde.gov.cn/cdhy/ZHManageMis_CD/Pages/GetBZJZhangHao/Agree.aspx?ProjectType=ZFCG&amp;GongGaoGuid=61247ac0-d4af-4f29-ad61-800a13d9f0ce\"><img border=\"0\" src=\"/cdweb/Images/GetZiZhangHao.jpg\"></a></td></tr></tbody></table></td></tr><!--EpointContent-->\n" +
            "\t\t\n" +
            "\t\n" +
            "\t<tr>\n" +
            "\t\t<td id=\"InfoDetail_infomessagetd\" align=\"right\" height=\"50\"><span id=\"InfoDetail_infomessage\"></span></td>\n" +
            "\t</tr>\n" +
            "\t<tr>\n" +
            "\t\t<td height=\"30\"></td>\n" +
            "\t</tr>\n" +
            "\t<tr id=\"InfoDetail_feedback\">\n" +
            "\t\t<td align=\"center\">\n" +
            "           <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "              <tbody><tr>\n" +
            "                <td style=\"border:1px #fadaaf solid;\">\n" +
            "                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "\t\t  <tbody><tr>\n" +
            "\t\t    <td height=\"32\" valign=\"bottom\" background=\"../template/default/images/index_33.jpg\" style=\"background-repeat:no-repeat;\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "\t\t      <tbody><tr>\n" +
            "\t\t        <td width=\"41\">&nbsp;</td>\n" +
            "\t\t        <td width=\"104\" height=\"32\" style=\"color:#ffffff; font-size:14px; font-weight:bold; padding-top:5px;\">咨询答疑</td>\n" +
            "\t\t        <td background=\"../template/default/images/infodetail_td_bg01.jpg\">&nbsp;</td>\n" +
            "\t\t        <td width=\"51\" background=\"../template/default/images/infodetail_td_bg01.jpg\"></td>\n" +
            "\t          </tr>\n" +
            "\t\t      </tbody></table></td>\n" +
            "\t      </tr>\n" +
            "\t  </tbody></table>\n" +
            "                \n" +
            "\n" +
            "<script language=\"javascript\">\n" +
            "<!--\n" +
            "    function AddCheck() {\n" +
            "\n" +
            "        var Inputlist = document.getElementsByTagName(\"Input\");\n" +
            "        for (var i = 0; i < Inputlist.length; i++) {\n" +
            "            var len = Inputlist[i].id.split('_').length - 1;\n" +
            "            if (len > 0) {\n" +
            "                if (Inputlist[i].id.split('_')[len] == 'txtNickName') {\n" +
            "                    if (document.getElementById(Inputlist[i].id).value == '') {\n" +
            "                        alert('请输入昵称！')\n" +
            "                        return false;\n" +
            "                    }\n" +
            "                }\n" +
            "\n" +
            "            }\n" +
            "\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "        var Inputlist = document.getElementsByTagName(\"textarea\");\n" +
            "        for (var i = 0; i < Inputlist.length; i++) {\n" +
            "            var len = Inputlist[i].id.split('_').length - 1;\n" +
            "            if (len > 0) {\n" +
            "                if (Inputlist[i].id.split('_')[len] == 'txtComment') {\n" +
            "                    if (document.getElementById(Inputlist[i].id).value == '') {\n" +
            "                        alert('评论内容不能为空！')\n" +
            "                        return false;\n" +
            "                    }\n" +
            "                }\n" +
            "\n" +
            "            }\n" +
            "\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "//-->\n" +
            "</script>\n" +
            "\n" +
            "<table width=\"100%\" border=\"0\">\n" +
            "    <tbody><tr>\n" +
            "        <td id=\"InfoDetail_InfoFeedBack_infoid1_TD_Title\" style=\"padding-left: 20px\" align=\"left\">标题：临澧县城乡一体化智能交通PPP项目 (第二次)资格预审公告</td>\n" +
            "\t\t\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td height=\"8\">\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding-left: 20px\" align=\"left\">\n" +
            "            内容：</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>\n" +
            "            </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td align=\"right\" style=\"padding-right: 20px\">\n" +
            "            \n" +
            "\t\t<!------------------------------ AspNetPager V4.3.3 Start ------------------------------>\n" +
            "\t\t<!-------------------- Copyright:2003-2005 Webdiyer(www.webdiyer.com) --------------------->\n" +
            "\t\t<div id=\"InfoDetail_InfoFeedBack_infoid1_Pager\">\n" +
            "\t\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "\t\t\t\t<tbody><tr>\n" +
            "\t\t\t\t\t<td valign=\"bottom\" align=\"left\" nowrap=\"true\" style=\"width:40%;\">记录总数：<font color=\"blue\"><b>0</b></font> 总页数：<font color=\"blue\"><b>0</b></font> 当前页：<font color=\"red\"><b>1</b></font></td><td valign=\"bottom\" align=\"notset\" nowrap=\"true\" class=\"\" style=\"width:60%;\"><a disabled=\"true\">首页</a><span style=\"width:5px;\"></span><a disabled=\"true\">上一页</a><span style=\"width:5px;\"></span><a disabled=\"true\">下一页</a><span style=\"width:5px;\"></span><a disabled=\"true\">尾页</a><span style=\"width:5px;\"></span></td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t</tbody></table>\n" +
            "\t\t</div>\n" +
            "\t\t<!------------------------------- AspNetPager V4.3.3 End -------------------------------->\n" +
            "\n" +
            "\t\t\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "</tbody></table>\n" +
            "<table id=\"InfoDetail_InfoFeedBack_infoid1_Table1\" cellspacing=\"1\" cellpadding=\"1\" width=\"600\" align=\"center\" border=\"0\">\n" +
            "\t\t\t<tbody><tr>\n" +
            "\t\t\t\t<td nowrap=\"nowrap\" align=\"center\" width=\"50\">\n" +
            "            主题：</td>\n" +
            "\t\t\t\t<td align=\"left\" width=\"550\">\n" +
            "            <input name=\"InfoDetail$InfoFeedBack_infoid1$txtNickName\" type=\"text\" id=\"InfoDetail_InfoFeedBack_infoid1_txtNickName\" class=\"inputtxt1\" style=\"width: 250\">&nbsp;&nbsp;\n" +
            "            验证码：<span id=\"InfoDetail_InfoFeedBack_infoid1_yzm\" style=\"background-color:#99CCFF;\">9906</span>\n" +
            "                <input name=\"InfoDetail$InfoFeedBack_infoid1$yzm2\" type=\"text\" id=\"InfoDetail_InfoFeedBack_infoid1_yzm2\">\n" +
            "            <input type=\"submit\" name=\"InfoDetail$InfoFeedBack_infoid1$BtnComment\" value=\"发表评论\" onclick=\"return AddCheck();\" id=\"InfoDetail_InfoFeedBack_infoid1_BtnComment\" class=\"Btnbg\">\n" +
            "        </td>\n" +
            "\t\t\t</tr>\n" +
            "\t\t\t<tr>\n" +
            "\t\t\t\t<td align=\"center\" colspan=\"2\">\n" +
            "            <textarea name=\"InfoDetail$InfoFeedBack_infoid1$txtComment\" id=\"InfoDetail_InfoFeedBack_infoid1_txtComment\" class=\"inputtxt1\" style=\"width: 600px; height: 100px\" rows=\"6\" cols=\"40\"></textarea></td>\n" +
            "\t\t\t</tr>\n" +
            "\t\t</tbody></table>\n" +
            "\t\t\n" +
            "\n" +
            "                </td>\n" +
            "              </tr>\n" +
            "           </tbody></table>\n" +
            "        \t\t \n" +
            "       </td>\n" +
            "\t</tr>\n" +
            "\t<tr>\n" +
            "\t\t<td align=\"center\">\n" +
            "\t\t\t<a href=\"javascript:window.print()\"><img height=\"16\" src=\"/cdweb/images/paintpage.gif\" width=\"50\" border=\"0\">\n" +
            "\t\t\t</a><a href=\"javascript:self.close()\"><img height=\"16\" src=\"/cdweb/images/close.gif\" width=\"50\" border=\"0\">\n" +
            "\t\t\t</a>\n" +
            "\t\t</td>\n" +
            "\t</tr>\n" +
            "</tbody></table>";

    @Test
    public void cleanHtmlTag(){

//        string regexstr = @"<[^>]*>"; //去除所有的标签
//
//        @"<script[^>]*?>.*?</script >"; //去除所有脚本，中间部分也删除
//
//        string regexstr = @"<img[^>]*>"; //去除图片的正则
//
//        string regexstr = @"<(?!br).*?>"; //去除所有标签，只剩br
//
//        string regexstr = @"<table[^>]*?>.*?</table>"; //去除table里面的所有内容
//
//        string regexstr = @"<(?!img|br|p|/p).*?>"; //去除所有标签，只剩img,br,p

        content = content.replaceAll("<(?!img|br|p|/p).*?>",""); //去除所有标签，只剩img,br,p
        content = content.replaceAll("<script[^>]*?>.*?</script >","");//去除script块
        content = content.replaceAll("(style|class|align)+?=*?\".*\"","");//去除样式属性
        content = content.replaceAll("<!--[\\s\\S\\W\\w.]*-->","");//去除样式属性
        content = content.replaceAll("&.*?;","");//去除转义字符
        System.out.println(content);
    }


}
