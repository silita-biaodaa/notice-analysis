package com.silita.biaodaa.analysisRules.notice.zhongbiao;

import com.silita.biaodaa.analysisRules.notice.NoticeTableAnalysis;
import com.silita.biaodaa.analysisRules.template.SingleFieldAnalysisTemplate;
import com.silita.biaodaa.common.config.CustomizedPropertyConfigurer;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.service.CompanyService;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;
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

    public static Logger logger = Logger.getLogger(FirstCandidateRule.class);

    @Autowired
    CommonService commonService;

    @Autowired
    CompanyService companyService;

    private static final String[] endKeys = {"公司","研究院"};

    @Override
    protected void init() {
        this.fieldName="firstCandidate";
    }

    protected String beforeAccurateMatch(EsNotice esNotice,String matchPart,Map<String ,List<Map<String, Object>>> regListMap,String rangeRegex){
        String res = null;
        try {
            Map<String,String> resMap = esNotice.getTbAnalysisMap();
            if(resMap !=null && resMap.size()>0){
                res = resMap.get(NoticeTableAnalysis.FD_ONE_NAME);
                if(res != null && res.length()>100){
                    res = res.substring(0,100);
                }
            }
        }catch (Exception e){
            logger.error("第一中标候选人,表格解析异常"+e,e);
        }finally {
            logger.info("表格解析结果："+res);
        }
        return res;
    }


    /**
     * 对匹配出的值进行过滤
     * @param analysisResult
     * @param esNotice
     * @return
     */
    protected String customfilterResult(String analysisResult,EsNotice esNotice){
        //判断是否以公司关键字结尾，如果匹配到的关键字不是字符串的结尾，则进行截取处理。
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

    /**
     * 最后步骤，检验值是否有效
     * @param esNotice
     * @param regListMap 规则集合
     * @param analysisResult 解析结果值
     * @return
     */
    protected String verifyAnalysisResult(EsNotice esNotice,Map<String , List<Map<String, Object>>> regListMap, String analysisResult){
        //根据规则校验解析结果是否有效，无效结果直接置空
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
            if (corpFilterStatus != null && corpFilterStatus.equalsIgnoreCase("true")) {
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


}
