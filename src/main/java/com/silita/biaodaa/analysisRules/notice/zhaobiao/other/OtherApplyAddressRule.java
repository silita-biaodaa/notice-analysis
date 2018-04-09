package com.silita.biaodaa.analysisRules.notice.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.utils.MyStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 其他站点报名地址解析
 * Created by dh on 2018/3/20.
 */
@Component
public class OtherApplyAddressRule implements SingleFieldAnalysis {

    private Log logger = LogFactory.getLog(OtherApplyAddressRule.class);

    @Autowired
    CommonService commonService;

    @Override
    public String analysis(String segment,String keyWork) {
        return analyzeApplyAddress(segment);
    }

    private static String[] headStrings = {"报名地点","报名地址","报名要求","投标报名","申请报名",
            "获取招标文件时间、地点及售价 ","报名及发售招标文件",
            "报名时间及地址","报名方式，时间及地点","报名方式与地点","报名起止时间",
            "招标文件获取地点", "招标文件的获取","报名方式及要求","报名及招标文件的获取",
            "报名及出售招标文件时间地点和要求","投标报名及招标文件发布",
            "招标文件的获取时间、地址","报名及招标文件发售办法",
            "报名和电子招标文件获取","招标文件发售",
            "投标文件的递交","投标文件和保证金的递交",
            "投标文件递交截止时间（开标时间）及地点","开标时间和地点",
            "网上报名及招标文件的获取", "下载或查阅招标文件","下载获取招标文件",
            "下载/获取招标文件","下载[(]获取[)]招标文件及图纸","下载招标文件","获取方式"};

    /**
     * 解析报名地址
     * 数据匹配
     * return 报名地址
     */
    public String analyzeApplyAddress(String html) {
        String address = null;
        String onLineApply = "网上报名";
        List<String> outerRegexList = new ArrayList<String>(headStrings.length);
        for(int i=0;i<headStrings.length;i++){
            StringBuffer outerRegex = new StringBuffer("(");
            outerRegex.append(headStrings[i]).append(")").append(".{0,500}");//匹配head字符后1000个字符
            outerRegexList.add(outerRegex.toString());
        }

        outerMtr: for(String outerRegex:outerRegexList) {
            Pattern outerPtn = Pattern.compile(outerRegex.toString(),Pattern.CASE_INSENSITIVE);
            Matcher outerMtr = outerPtn.matcher(html);
            String matchPart = null;

            while (outerMtr.find()) {
                matchPart = outerMtr.group();
                //仅只匹配出表达式的字符段跳过
                if(MyStringUtils.isNull(matchPart.replace(outerMtr.group(1),"").trim())){
                    continue;
                }

                //网上报名解析
                String onlineRegex = "((CA数字证书|登录电子化平台|数字证书登录|电子招标投标交易平台|网上支付)\\S?).*(报名|下载招标文件|交易平台|网上支付)*[。，,.]+";
                String onlineRegex2 = "(下载.0,10}招标文件|信息网上报名|网上报名|网上进行电子报名|网上报名并获取电子招标文件|网上获取电子招标文件及其它招标资料)|((公共)+(资源)?(交易|网上)+(中心)?(网站|平台|系统)+)";
                List<String > onlineList = new ArrayList<String>();
                onlineList.add(onlineRegex);
                onlineList.add(onlineRegex2);

                for(String onLineRgx: onlineList) {
                    Pattern onlinePtn = Pattern.compile(onLineRgx, Pattern.CASE_INSENSITIVE);
                    if (onlinePtn.matcher(matchPart).find()) {
                        address = onLineApply;
                        logger.info( "\n@@@线上报名解析结果:[address:" + address + "] by [outerRegex:"+outerRegex+"][innerRegex:" + onLineRgx + "]\n@@@被解析片段：" + matchPart );
                        break outerMtr;
                    }
                }

                //解析详细地址
                List<Map<String, Object>> arList = commonService.queryRegexByField("applyAddress");
                for (int i = 0; i < arList.size(); i++) {
                    Map map = arList.get(i);
                    String innerRegex = map.get("regex").toString();
                    Pattern innerPtn = Pattern.compile(innerRegex,Pattern.CASE_INSENSITIVE);
                    Matcher innerMtr = innerPtn.matcher(matchPart);

                    logger.debug(html + "\n[regex:" + innerRegex + "][groupCount:" + innerMtr.groupCount() + "]");
                    while (innerMtr.find()) {
                        int gCount = innerMtr.groupCount();
                        if (innerMtr.groupCount() > 2) {
                            try {
                                address = innerMtr.group().replaceFirst(innerMtr.group(1), "").replaceFirst(innerMtr.group(gCount), "");//去掉首尾
                            }catch (Exception e){
//                                logger.error(e.getMessage(),e);
                                address = innerMtr.group().replaceFirst(innerMtr.group(1), "");
                            }
                        } else if (innerMtr.groupCount() > 1) {
                            address = innerMtr.group().replaceFirst(innerMtr.group(1), "");//去掉首
                        } else {
                            address = innerMtr.group();
                        }
                        address = filterAnalysisResult(address);
                        //            logger.debug("解析段："+html+"\n[解析值："+address+"][regex:"+regex+"][groupCount:"+m.groupCount()+"][address:"+address+"]");
                        if (MyStringUtils.isNotNull(address)&& address.length()>4) {
                            logger.info( "\n##线下报名地址解析结果:[address:" + address + "] by [outerRegex:\""+outerRegex+"\"][innerRegex:\"" + innerRegex + "\"][groupCount:" + innerMtr.groupCount() + "]\n##被解析片段：" + matchPart );
                            break outerMtr;
                        }
                    }
                }
            }
            matchPart=null;
        }

//        if(MyStringUtils.isNull(address)){
//            address ="线下报名";
//        }
        return address;
    }

    private String filterAnalysisResult(String str){
        String regex = "(<.+>|[。，,.；：]*$|[ ]*$|^[ ]*|<.?[^>]?p>)";
        if(MyStringUtils.isNotNull(str)){
            Pattern ptn = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
            Matcher matcher = ptn.matcher(str);
            while (matcher.find()) {
                str = str.replaceAll(matcher.group(),"");
            }
            return str;
        }else{
            return null;
        }

    }


}
