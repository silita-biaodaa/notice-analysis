package com.silita.biaodaa.analysisRules.notice.zhongbiao;

import com.silita.biaodaa.analysisRules.notice.AnalysisConstant;
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

    private static final String[] preKeys = {":","："};

    @Override
    protected void init() {
        this.fieldName="firstCandidate";
    }

    /**
     * 正则匹配之前（获取表格解析结果）
     * @param esNotice
     * @param matchPart
     * @param regListMap
     * @param rangeRegex
     * @return
     */
    protected String beforeAccurateMatch(EsNotice esNotice,String matchPart,Map<String ,List<Map<String, Object>>> regListMap,String rangeRegex){
        String res = null;
        try {
            Map<String,String> resMap = esNotice.getTbAnalysisMap();
            if(resMap !=null && resMap.size()>0){
                res = resMap.get(AnalysisConstant.FD_ONE_NAME);
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
     * 规则匹配，表格解析都无结果时执行
     * @param html
     * @param esNotice
     * @param regListMap
     * @return
     */
    @Override
    protected String afterAllMatch(String html, EsNotice esNotice, Map<String, List<Map<String, Object>>> regListMap) {
        String result = null;
        String matchCorpName = (String) CustomizedPropertyConfigurer.getContextProperty("analysis.firstCandidate.match.corpName");
        if (matchCorpName != null && matchCorpName.equalsIgnoreCase("true")) {
            //无匹配结果时，根据当前省份企业库进行匹配
            String source = esNotice.getSource();
            long start = System.nanoTime();
            List<String> nameList = companyService.queryProvComName(source);
            try {
                //分批次进行处理，减少一次性资源占用
                int minIdx = -1;
                int size = nameList.size();
                int batchCount = 1000;
                if (nameList != null && size > batchCount) {
                    int loopNum = size / batchCount;
                    int lastLoopSize = size % batchCount;
                    int tmpMin = -1;
                    String tmpRes = null;
                    //分批次处理
                    for (int i = 0; i < loopNum; i++) {
                        int lastListSize = 0;
                        if (i + 1 == loopNum) {//最后一批增加取余数（防止遗漏数据）
                            if (lastLoopSize > 0) {
                                lastListSize = lastLoopSize;
                            }
                        }

                        List<String> subList = nameList.subList(i*batchCount, (batchCount*i)+batchCount+lastListSize);
                        Object[] resObj = findMinIdxName(html, subList);
                        //挑选最小的index
                        tmpMin = Integer.parseInt(resObj[0].toString());
                        tmpRes = resObj[1].toString();
                        if (tmpMin != -1) {
                            if (minIdx == -1) {
                                minIdx = tmpMin;
                                result = tmpRes;
                            } else {
                                if (tmpMin < minIdx) {
                                    minIdx = tmpMin;
                                    result = tmpRes;
                                }
                            }
                        }
                    }
                }
            } catch (Error error) {
                logger.error(error, error);
            } catch (Exception e) {
                logger.error(e, e);
            } finally {
                long cost = (System.nanoTime() - start);
                logger.info("afterAllMatch：企业库匹配中标候选人耗时：" + cost / 1000000 + "ms" + cost / 1000 + "微秒 ####  [noticeId:" + esNotice.getUuid() + "][redisId:" + esNotice.getRedisId() + "][rank:" + esNotice.getRank() + "][source:" + esNotice.getSource() + "][ur:" + esNotice.getUrl() + "]" + esNotice.getTitle() + esNotice.getOpenDate());
            }
            logger.debug("afterAllMatch,result-->" + result);
        }
        return result;
    }

    private Object[] findMinIdxName(String html,List<String> nameList){
        int minIdx = -1;
        String result = "";
        Object[] res = new Object[2];
        for (String comName : nameList) {
            int idx = html.indexOf(comName);
            if (idx != -1) {
                if (minIdx != -1) {
                    if (idx < minIdx) {
                        minIdx = idx;
                        result = comName;
                        continue;
                    }
                } else {
                    minIdx = idx;
                    result = comName;
                }
            }
        }
        res[0]=minIdx;
        res[1]=result;
        return res;
    }

    /**
     * 对匹配出的值进行过滤
     * @param analysisResult
     * @param esNotice
     * @return
     */
    protected String customfilterResult(String analysisResult,EsNotice esNotice){
        if(MyStringUtils.isNotNull(analysisResult)){
            int len = analysisResult.length();
            //1.祛除冒号等符号前面的内容
            for(String preKey: preKeys){
                int kIdx = analysisResult.lastIndexOf(preKey);
                if(kIdx!= -1 && kIdx<(len-1)){
                    logger.debug("customfilterResult：从结果中["+analysisResult+"]祛除特殊符号前缀。。");
                    analysisResult = analysisResult.substring(kIdx+1);
                }
            }

            //2.判断是否以公司关键字结尾，如果匹配到的关键字不是字符串的结尾，则进行截取处理。
            for(String endKey: endKeys){
                int kIdx = analysisResult.lastIndexOf(endKey);
                int kLen = endKey.length();
                if(kIdx!= -1){
                    if((kIdx+kLen) < len){//满足截取条件
                        logger.debug("customfilterResult：["+analysisResult+"]满足截取条件，执行截取。。");
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
