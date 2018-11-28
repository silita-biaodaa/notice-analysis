package analysis;

import com.silita.biaodaa.service.ConfigTest;
import com.silita.biaodaa.utils.HtmlTagUtils;
import com.silita.biaodaa.utils.RegexUtils;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by dh on 2018/11/8.
 */
public class UtilsTest extends ConfigTest {

    @Test
    public void test(){
        String s="<p style=\"text-align: left; text-indent: 107px;\"><strong><span style=\"font-family: 宋体; font-size: 21px; font-style: normal; font-weight: bold;\"><span style=\"font-family: 宋体;\">娄底市环卫处涟钢生活区公厕改造提质工程</span></span></strong></p><p style=\"text-align: left; text-indent: 171px;\"><strong><span style=\"font-family: 宋体; font-size: 21px; font-style: normal; font-weight: bold;\"><span style=\"font-family: 宋体;\">竞争性磋商成交结果公告</span></span></strong></p><p style=\"text-align: left; line-height: 150%;\"><span style=\"text-decoration: underline;\"><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; text-underline: single;\">&nbsp;</span></span></p><p style=\"text-align: left; line-height: 150%;\"><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">娄底市环境卫生管理处</span></span><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">（采购人）的</span></span><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">涟钢生活区公厕改造提质工程</span></span><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">（采购项目名称）竞争性磋商项目于</span>2018年10月2</span><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">9</span><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">日（日期）在娄底市公共资源交易中心组织评审结束，现将成交结果公告如下：</span></span></p><p style=\"text-align: left; line-height: 150%;\"><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">一、项目名称：</span></span><span style=\"color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">娄底市环境卫生管理处涟钢生活区公厕改造提质工程</span></span></p><p style=\"background: rgb(255, 255, 255); text-align: left; line-height: 150%;\"><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">&nbsp;&nbsp;&nbsp; 预 算：</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">393.46</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">万元</span></span></p><p style=\"background: rgb(255, 255, 255); text-align: left; line-height: 150%;\"><strong><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px; font-weight: bold;\"><span style=\"font-family: 宋体;\">二、编号：</span></span></strong></p><p style=\"background: rgb(255, 255, 255); text-align: left; line-height: 150%;\"><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">1、采购计划批号：娄财采计[201</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">7</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">]</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">1249</span></p><p style=\"background: rgb(255, 255, 255); line-height: 150%;\"><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">2、采购代理编号：</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">LDZC-2018CS0037</span></p><p style=\"background: rgb(255, 255, 255); text-align: left; line-height: 150%;\"><strong><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px; font-weight: bold;\"><span style=\"font-family: 宋体;\">三、邀请供应商的情况</span></span></strong></p><p style=\"background: rgb(255, 255, 255); text-align: left; line-height: 150%;\"><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">1、供应商产生方式</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\">:</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">（</span>√）公告邀请（ ）供应商库抽取（&nbsp;）采购人</span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">、</span></span><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); line-height: 150%; font-family: 宋体; font-size: 14px;\"><span style=\"font-family: 宋体;\">专家推荐</span></span></p><p style=\"background: rgb(255, 255, 255); text-align: left; line-height: 150%;\"><strong><span style=\"background: rgb(255, 255, 255); color: rgb(0, 0, 0); font-family: 宋体; font-weight: bold;\"><span style=\"font-family: 宋体;\">四、评审情况</span></span></strong></p>\n" +
                "<table width=";
        System.out.println(HtmlTagUtils.clearTagByTable(s));
    }

    @Test
    public void numberFormat(){
        String oneOffer = "958.648123456万";//89.9789.3589.06 660.602277万  ￥529300.00元 1916376.36元 5948.192076万元 RMB261.80万  地勘：73.80元/米，钻孔剪切波检测：4600元
        oneOffer = numberUnitChange(oneOffer
                ,"(\\d{1,50}[,.]{0,5}\\d{1,50}[,.]{0,5}\\d{0,50}[,.]{0,5}\\d{0,50})"
                ,"(万|w|W)",0.0001);
        System.out.println(oneOffer);
    }


    private String numberUnitChange(String s,String sRegex,String unitRegex,double unitRt){
        String num = RegexUtils.matchValue(s,sRegex);

        int sIdx = s.indexOf(num);
        boolean isUnit=false;
        //取数字后的字符进行判断
        if(sIdx+num.length()+1 <= s.length()){
            System.out.println(s.substring(sIdx+num.length(),sIdx+num.length()+1));
            if(RegexUtils.matchExists(s.substring(sIdx+num.length(),sIdx+num.length()+1),unitRegex)){
                isUnit=true;
            }
        }

        DecimalFormat df = new DecimalFormat("#,###.######");
        try {
            if(!isUnit) {
                Number n = df.parse(num);
                Double d = n.doubleValue();
                s = String.valueOf(d *unitRt);
            }else{
                s = num;
            }
            s = df.format(Double.parseDouble(s));
        } catch (Exception e) {
            logger.error(e, e);
        }finally {
            return s;
        }
    }
}
