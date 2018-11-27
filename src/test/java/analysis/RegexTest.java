package analysis;

import com.silita.biaodaa.service.ConfigTest;
import com.silita.biaodaa.utils.RegexUtils;
import org.junit.Test;

/**
 * Created by dh on 2018/11/27.
 */
public class RegexTest extends ConfigTest{

    @Test
    public void insertUnit(){
        String g="\\d{1,100}[.]{0,100}\\d{1,100}";
        String s="高层125.00/米；多层95.00/米；边坡135.00元/米；钻孔1000";
        String unitStr="万元";
        System.out.println(RegexUtils.insertMatchValuePos(s,g,unitStr));
    }

    @Test
    public void test(){
        String regex="(?<=(中标价|投标报价|投标总价).*?)\\d{1,50}[.]{0,5}\\d{1,50}(万元|万|元|圆|w|W)?";
        String s="<div class=\"finalContent\">\n" +
                "                <div class=\"finalTitle\">\n" +
                "\t                <h3>衡阳县农业综合开发斜陂堰中型灌区节水配套改造项目中标候选人公示</h3>\n" +
                "\n" +
                "\t                <p>来源：衡阳市公共资源交易中心&nbsp;&nbsp;&nbsp;&nbsp;发布时间：2018-10-24</p>\n" +
                "                </div>\n" +
                "                <div class=\"article\">\n" +
                "\t\t\t<style type=\"text/css\">\n" +
                ".TRS_Editor P {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor DIV {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor TD {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor TH {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor SPAN {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor FONT {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor UL {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor LI {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                ".TRS_Editor A {\n" +
                "\tMARGIN-BOTTOM: 10px; FONT-SIZE: 12pt; FONT-FAMILY: 宋体; MARGIN-TOP: 5px; LINE-HEIGHT: 1.5\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "<style type=\"text/css\">\n" +
                "\n" +
                ".TRS_Editor P{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor DIV{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TD{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TH{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor SPAN{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor FONT{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor UL{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor LI{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor A{margin-top:5px;margin-bottom:10px;line-height:1.5;font-family:宋体;font-size:12pt;}</style>\n" +
                "<p align=\"center\">　　衡阳县农业综合开发斜陂堰中型灌区节水配套改造项目中标候选人公示</p>\n" +
                "<p align=\"justify\">　　湖南湘源工程项目管理咨询有限责任公司受衡阳县斜陂堰水库管理所的委托，就衡阳县农业综合开发斜陂堰中型灌区节水配套改造项目施工进行公开招标。按照《中华人民共和国招标投标法》及湖南省建设工程招标投标的有关规定，在监标人的监督下，于2018年10月23日上午10时00分（北京时间）在衡阳市公共资源交易中心进行了公开开标，并由依法组建的评标委员会按照招标文件确定的评标原则和评标办法进行了细致的评审，经评标委员会评审推荐的本项目中标候选人及排序如下：</p>\n" +
                "<p align=\"justify\">　　第一中标候选人：湖南长坤建设有限公司</p>\n" +
                "<p align=\"justify\">　　信用等级：AAA</p>\n" +
                "<p align=\"justify\">　　投标报价：13133081.96元</p>\n" +
                "<p align=\"justify\">　　工&nbsp;&nbsp;期：365日历天</p>\n" +
                "<p align=\"justify\">　　质量承诺：合格及以上</p>\n" +
                "<p align=\"justify\">　　项目负责人：廖志海（证书编号：湘143131411319）</p>\n" +
                "<p align=\"justify\">　　项目负责人业绩：广西新增千亿斤粮食产能规划平乐县2014年田间工程建设项目。</p>\n" +
                "<p align=\"justify\">　　投标人业绩：1、湖南省常宁市2016年农田水利项目县建设工程施工；2、衡阳县2016年度小型农田水利重点县建设项目；3、衡阳县2015年度小型农田水利重点县建设项目。</p>\n" +
                "<p align=\"justify\">　　第二中标候选人：湖南省德宇水电工程建设有限公司</p>\n" +
                "<p align=\"justify\">　　信用等级：AAA</p>\n" +
                "<p align=\"justify\">　　投标报价：13092218.33元</p>\n" +
                "<p align=\"justify\">　　工&nbsp;&nbsp;期：365日历天</p>\n" +
                "<p align=\"justify\">　　质量承诺：合格及以上</p>\n" +
                "<p align=\"justify\">　　项目负责人：艾跃辉（证书编号：湘143060802784）</p>\n" +
                "<p align=\"justify\">　　项目负责人业绩：&nbsp;新晃县2017年农田水利灌排渠道建设项目施工第二标段。</p>\n" +
                "<p align=\"justify\">　　投标人业绩：1、新晃县2017年农田水利灌排渠道建设项目施工第二标段；2、怀化市会同县2014年度小型农田水利重点县建设项目施工；3、桂东县2014年小型农田水利重点县建设项目工程施工。</p>\n" +
                "<p align=\"justify\">　　第三中标候选人：湖南和庆源建设工程有限公司</p>\n" +
                "<p align=\"justify\">　　信用等级：AAA</p>\n" +
                "<p align=\"justify\">　　投标报价：13037964.07元</p>\n" +
                "<p align=\"justify\">　　工&nbsp;&nbsp;期：365日历天</p>\n" +
                "<p align=\"justify\">　　质量承诺：合格及以上</p>\n" +
                "<p align=\"justify\">　　项目负责人：刘绍安（证书编号：湘243131323247）</p>\n" +
                "<p align=\"justify\">　　项目负责人业绩：无。</p>\n" +
                "<p align=\"justify\">　　投标人业绩：1、云溪区2016年度小型农田水利重点县建设项目；2、云溪区2015年度小型农田水利重点县建设项目；3、云溪区2014年度小型农田水利重点县建设项目。</p>\n" +
                "<p align=\"justify\">　　评标情况：湖南华纬水电工程公司、湖南省金科建设有限公司、益阳康飞水电工程有限公司、永州市水利水电建设有限责任公司4家公司业绩没提供已验收证明资料，为不合格投标人。</p>\n" +
                "<p align=\"justify\">　　公示期三天。公示期间，投标人和其他利害关系人如有异议，应按照《工程建设项目投标活动投诉处理办法》、《关于修改&lt;湖南省招标投标活动投诉处理办法&gt;部分内容的通知》（湘发改招〔2013〕909号）提出异议或投诉。</p>\n" +
                "<p align=\"justify\">　　招标人：衡阳县斜陂堰水库管理所</p>\n" +
                "<p align=\"justify\">　　联&nbsp;系人：&nbsp;申先生</p>\n" +
                "<p align=\"justify\">　　电　　话：13974787059</p>\n" +
                "<p align=\"justify\">　　招标代理：湖南湘源工程项目管理咨询有限责任公司</p>\n" +
                "<p align=\"justify\">　　地址：长沙市雨花区韶山北路356号包装大厦B座1207</p>\n" +
                "<p align=\"justify\">　　联系人：贺女士</p>\n" +
                "<p align=\"justify\">　　联系电话：0731-84392399 13319503106</p>\n" +
                "                         \n" +
                "\n" +
                "                        <script type=\"text/javascript\">\n" +
                "\t                      var appendix= '';\n" +
                "\t                      if(appendix!= \"\"){\n" +
                "\t                             document.write('<div class=\"fujian\">附件：</div>');}\n" +
                "                        </script>\n" +
                "                        \n" +
                "                </div>\n" +
                "    \t\t</div>";
        System.out.println(RegexUtils.matchValue(s,regex));
    }
}
