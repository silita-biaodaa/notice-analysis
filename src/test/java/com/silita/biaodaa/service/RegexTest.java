package com.silita.biaodaa.service;

import com.silita.biaodaa.utils.HtmlTagUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dh on 2018/3/21.
 */
public class RegexTest {
    public static void main(String args[]) {
        String s = "2017年扶余市增盛，，镇高。标准农田公司建设sd ";
        //获取双引号之间的内容: (?<=\").*?(?=\")
        //匹配公司开始之后的内容，b
        String regex = "(?<=公司).*";
        System.out.println(testReg(s,regex));
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

    private static String testReg(String s,String r){
        System.out.println("origin value:"+s);
        String v = null;
        Pattern ptn = Pattern.compile(r);
        Matcher m = ptn.matcher(s);
        System.out.println("m.matches():"+m.matches());
        boolean fm= m.find();
        System.out.println("m.find():"+fm);
        if (fm) {
            v = m.group();
            System.out.println("value:"+v);

            System.out.println("m.group(0) :" + m.group(0) );
            for(int i=0; i<m.groupCount();i++){
                System.out.println("m.group("+i+") :" + m.group(i) );
            }
        }
        return v;
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
    String content="<div class=\"xiangxiyekuang\"><p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">受岳阳市人民代表大会常务委员会办公室的委托，对岳阳市人大常委会机关主任会议室电子会议系统建设项目进行公开招标采购，欢迎符合资格条件、有实力并对此有兴趣的供应商前来投标。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">一、项目名称：岳阳市人大常委会机关主任会议室电子会议系统建设项目<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">二、政府采购编号：岳财市采计【2018】000064号 &nbsp;&nbsp;委托代理编号：HNTC2018ZB0304<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">三、预算上限：1541000元<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">四、采购方式：公开招标<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">五、评标办法：综合评分法<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">六、投标人资格要求：<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1、投标单位基本资格条件：投标单位必须是符合《中华人民共和国政府采购法》第二十二条的合格投标单位且能提供：<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.1、企业在工商部门注册的有效“营业执照”；事业单位提供“事业单位法人证书”；非企业专业服务机构提供“执业许可证”等证明文件；个体工商户提供“个体工商户营业执照”；如供应商是自然人，需提供有效的自然人身份证。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.2、投标人为法人的，提供2017年度经审计的财务报告,或其基本开户银行出具的资信证明。部分其他组织和自然人如无经审计的财务报告可以提供其近3个月的报表，或其基本开户银行出具的资信证明。（提供财政部门认可的政府采购专业担保机构出具的有投标担保函的不需要提供其他财务状况报告）。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.3、提供社会保险登记证或参加政府采购活动前三个月内缴纳社会保险的凭据（专用收据或社会保险缴纳清单）；（依法不需要缴纳社会保障资金的，提供相应文件证明）。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.4、提供参加政府采购采购活动前三年内，在经营活动中没有重大违法记录的书面声明函。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.5、提供在信用中国网站查询投标人无重大失信行为等被禁止投标的记录。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.6、法律、行政法规规定的其他条件。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">2、投标人特定资格条件：具有电子与智能化工程专业承包贰级（含贰级）以上资质。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">3、本项目不接受联合体投标、中标转让。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">七、获取招标文件的时间、地点、方式及招标文件售价：<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.有意投标供应商必须办理数字CA,投标人在网上注册应对所提供的证件和证明材料的真实性承担法律责任。<br>\n" +
            "&nbsp;&nbsp;&nbsp;&nbsp;2.本次招标只支持网上支付标书费报名并从系统中下载招标文件。（联系电话：0730-8181091）<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">3.招标文件售价：人民币400元/份，只支持网银支付。本项目只有网上购买标书，投标人才有资格生成投标保证金账号，缴纳保证金。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">4、招标文件提供期限：2018年03月23日至2018年03月30日。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">八、投标保证金：<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1.投标保证金交纳：人民币叁万元整（￥30000.00元）<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">（1）缴纳时间：2018年4月12日中午12时前（含）（北京时间），以岳阳市公共资源交易中心保证金支付系统到账时间为准（保证金咨询电话：0730-8882193）<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">（2）缴纳方式：投标保证金应以投标人银行账户转账等非现金形式递交。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">账户名：岳阳市公共资源交易中心<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">开户行：投标人在网上可自行选择保证金专户银行账户。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">账号：投标人随机获取对应本项目（标段）投标保证金子账号<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">1）投标人在岳阳市公共资源交易网（ggzy.yueyang.gov.cn）选择“投标单位登入”（首次登入需注册,完成注册并绑定投标人CA等相关手续后进入交易系统），投标人选择对应项目进行投标操作，生成对应本项目（标段）的投标保证金子账号。该账号为投标人缴纳本项目（标段）投标保证金的唯一账号，请注意保密。（投标人必须办理湖南CA数字证书才能完成登入及后续操作）<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">2）投标人在提交保证金时，应按照随机获取的保证金子账号信息准确填写银行账单（投标保证金只能从投标人的银行基本户转出），投标人可通过登入系统查询保证金到账及退还情况。 <o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">（3）CA数字证书办理<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">岳阳市公共资源交易中心404室（岳阳市政府政务服务中心4楼）电话：0730-8181828。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">九、投标截止时间、开标时间及地点：<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">兹定于2018年04月13日09：00时（北京时间）在岳阳市公共资源交易中心四楼开标大厅公开开标，逾期送达的或者未送达指定地点的投标文件将拒绝接收。届时请投标人的法定代表人或其委托代理人出席开标仪式。<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">十、采购项目联系人姓名和电话：<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">采 购 人：岳阳市人民代表大会常务委员会办公室 <o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">联 系 人：陈颖<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">电 &nbsp;&nbsp; 话：18673028099<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">代理机构：湖南同创工程项目管理有限公司 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">地 &nbsp;&nbsp;&nbsp;址：岳阳市岳阳楼区五里牌第一世家1102<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">联 系 人：高女士 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<o:p></o:p></p>\n" +
            "<p style=\"text-align: justify; line-height: 2; text-indent: 2em; font-family: 宋体; font-size: 12pt; margin-top: 0px; margin-bottom: 0px; -ms-text-justify: inter-ideograph;\">电 &nbsp;&nbsp;&nbsp;话：0730-8201199<o:p></o:p></p>\n" +
            "&nbsp;&nbsp;&nbsp;</div>";

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

        content = HtmlTagUtils.clearInvalidTag(content);
        System.out.println(content);
    }


}
