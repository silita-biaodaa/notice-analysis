package com.silita.biaodaa.analysisRules.notice.zhongbiao;

import com.silita.biaodaa.analysisRules.template.SingleFieldAnalysisTemplate;
import com.silita.biaodaa.common.config.CustomizedPropertyConfigurer;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.service.CompanyService;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 第一中标人解析
 */
@Component
public class FirstCandidateRule extends SingleFieldAnalysisTemplate {

    private Log logger = LogFactory.getLog(FirstCandidateRule.class);

    @Autowired
    CommonService commonService;

    @Autowired
    CompanyService companyService;

    @Override
    protected void init() {
        this.fieldName="firstCandidate";
    }

    protected String verifyAnalysisResult(EsNotice esNotice,Map<String , List<Map<String, Object>>> regListMap, String analysisResult){
        //根据规则校验解析结果
        List<Map<String, Object>> verifyRuleList = regListMap.get("verifyResult");
        for(Map<String, Object> verifyRule:verifyRuleList){
            String regex = verifyRule.get("regex").toString();
            Pattern ptn = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
            Matcher matcher = ptn.matcher(analysisResult);
            if(!matcher.find()){
                logger.info("解析结果[analysisResult:"+analysisResult+"]，表达式验证失败，返回null。[regex:"+regex+"]");
                analysisResult= null;
                break;
            }
        }

        if(MyStringUtils.isNotNull(analysisResult)) {
            //根据企业名称库,校验解析结果
            String corpFilterStatus = (String) CustomizedPropertyConfigurer.getContextProperty("analysis.corpName.filter");
            if (corpFilterStatus != null && corpFilterStatus.equals("true")) {
                logger.info("校验企业名称库开始。。。[title:"+esNotice.getTitle()+"][analysisResult:"+analysisResult+"][source:"+esNotice.getSource()+"]");
                boolean isExists = companyService.existsCorpName(analysisResult);
                if(!isExists){
                    companyService.saveSuspectCompanyName(fieldName,analysisResult,esNotice);
                    logger.warn("企业名称库匹配失败[analysisResult:"+analysisResult+"]");
                    analysisResult=null;

                }
                logger.info("校验企业名称库结束。。。[title:"+esNotice.getTitle()+"][analysisResult:"+analysisResult+"]");
            }
        }

        return analysisResult;
    }

//    @Override
//    public String analysis(String html,String keyWork) {
//        String rangeHtml="";
//        String onePeople = "";
//        List<Map<String, Object>> arList = commonService.queryRegexByField("firstCandidate");
//        for (int i = 0; i < arList.size(); i++) {
//            String start = arList.get(i).get("startKey").toString();
//            String end = arList.get(i).get("endKey").toString();
//            int indexStart = 0;
//            int indexEnd = 0;
//            if (!"".equals(start)) {
//                indexStart = html.indexOf(start);//范围开始位置
//            }
//            if (!"".equals(end)) {
//                indexEnd = html.indexOf(end);//范围结束位置
//            }
//            if(indexStart != -1 && indexEnd != -1) {
//                if (indexEnd > indexStart) {
//                    rangeHtml = html.substring(indexStart, indexEnd + 1);//截取范围之间的文本
//                } else if (indexStart > indexEnd) {
//                    if (html.length() - indexStart < 30) {
//                        rangeHtml = html.substring(indexStart, html.length());//截取范围开始到结尾
//                    } else {
//                        rangeHtml = html.substring(indexStart, indexStart + 30);
//                    }
//                }
//                rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
//                onePeople = rangeHtml.replace("第一名", "").replace("第一候选人", "")
//                        .replace("第一中标候选人", "").replace("：","").replace("第1名", "").replace(":", "")
//                        .replace("中标人", "").replace("<p>", "").replace("<td>", "").replace("</td>", "")
//                        .replace("</strong>", "").replace("中标单位名称", "").replace("乙方", "").replace("</div>", "")
//                        .replace("第一中标排序单位名称", "").replace("中标单位", "").replace("第1中标候选人", "")
//                        .replace("根据法律、法规、规章和招标文件的规定，<strong>", "").replace("单位名称", "")
//                        .replace("5、采购人名称","").replace("建设单位","").replace("<div>", "").replace("</p>", "")
//                        .replace("中?标?单?位", "").replace("中标（成交）供应商名称", "").replace("设计预中标人", "")
//                        .replace("中标人名称", "").replace("(主)", "").replace("为联合体牵头人","").replace("第二中候选人", "").replace("名称","");
//                if (onePeople.indexOf("公司") == -1 && onePeople.indexOf("院") == -1) {
//                    onePeople = "";
//                }
//                if(onePeople.length() > 0) {
//                    break;
//                }
//            }
//        }
//        if(onePeople.contains("公司")){
//            onePeople = onePeople.substring(0,onePeople.indexOf("公司")+2);
//        }else if (onePeople.contains("院")){
//            onePeople = onePeople.substring(0,onePeople.indexOf("院")+1);
//        }
//        if(onePeople.length()>21){
//            onePeople = "";
//        }
//
//        return onePeople;
//    }
}
