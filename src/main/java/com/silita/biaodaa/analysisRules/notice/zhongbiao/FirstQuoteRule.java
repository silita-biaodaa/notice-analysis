package com.silita.biaodaa.analysisRules.notice.zhongbiao;

import com.silita.biaodaa.analysisRules.notice.AnalysisConstant;
import com.silita.biaodaa.analysisRules.template.SingleFieldAnalysisTemplate;
import com.silita.biaodaa.utils.RegexUtils;
import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 第一中标金额
 */
@Component
public class FirstQuoteRule extends SingleFieldAnalysisTemplate {

    private Logger logger = Logger.getLogger(FirstQuoteRule.class);

    @Override
    protected void init() {
        this.fieldName="firstQuote";
    }

    protected String beforeAccurateMatch(EsNotice esNotice,String matchPart,Map<String ,List<Map<String, Object>>> regListMap,String rangeRegex){
        String res = null;
        try {
            Map<String,String> resMap = esNotice.getTbAnalysisMap();
            if(resMap !=null && resMap.size()>0){
                res = resMap.get(AnalysisConstant.FD_ONE_OFFER);
                if(res != null && res.length()>50){
                    res = res.substring(0,50);
                }
            }
        }catch (Exception e){
            logger.error("第一中标报价,表格解析值获取异常"+e,e);
        }finally {
            logger.info("第一中标报价，表格解析结果："+res);
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
        //遇到%号，直接置空
        if(RegexUtils.matchExists(analysisResult,"\\%")){
            return null;
        }
        //进行过滤,剔除标签与括号内容
        String[] filters = {"/span","(?<=\\d{1,50}[,.]{0,5}\\d{1,50}[,.]{0,5}\\d{0,50}[,.]{0,5}\\d{0,50})(\\(|\\（)[.\\s\\S]*"};
        for(String f: filters){
            analysisResult=analysisResult.replaceAll(f,"");
        }
        //截取最后一个有效数字之前的内容
        String[] lastChar = {"万元","元"};
        for(String lc:lastChar) {
            int lastIdx = analysisResult.lastIndexOf(lc);
            if(lastIdx>0){
                analysisResult =analysisResult.substring(0,lastIdx)+lc;
                break;
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
        if(!RegexUtils.matchExists(analysisResult,"\\d+([.]?\\d+)?(万元|万|元|圆|w|W)?$")){
            analysisResult=null;
        }
        return analysisResult;
    }


}
