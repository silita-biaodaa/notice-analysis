package com.silita.biaodaa.analysisRules.notice.zhongbiao;

import com.silita.biaodaa.analysisRules.notice.AnalysisConstant;
import com.silita.biaodaa.analysisRules.template.SingleFieldAnalysisTemplate;
import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Create by IntelliJ Idea 2018.1
 * Company: silita
 * Author: gemingyi
 * Date: 2018-12-27 16:50
 */
@Component
public class FirstProjectManagerRule extends SingleFieldAnalysisTemplate {

    private static final String[] preKeys = {":","："};
    private static final String[] containKeys = {"编号","姓名", "时间", "单位", "电话", "资格", "资质", "等级", "证书", "情况", "注册"};

    private Logger logger = Logger.getLogger(FirstProjectManagerRule.class);

    @Override
    protected void init() {
        this.fieldName="firstProjectManager";
    }


    protected String beforeAccurateMatch(EsNotice esNotice, String matchPart, Map<String ,List<Map<String, Object>>> regListMap, String rangeRegex){
        String res = null;
        try {
            Map<String,String> resMap = esNotice.getTbAnalysisMap();
            if(resMap !=null && resMap.size()>0){
                res = resMap.get(AnalysisConstant.FD_PROJECT_MANAGER);
            }
        }catch (Exception e){
            logger.error("第一中标项目经理,表格解析异常"+e,e);
        }finally {
            logger.info("第一中标项目经理,表格解析结果："+res);
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
        if(!StringUtils.isEmpty(analysisResult)) {
            int len = analysisResult.length();
            for(String preKey: preKeys){
                int kIdx = analysisResult.lastIndexOf(preKey);
                if(kIdx!= -1 && kIdx<(len-1)){
                    logger.debug("customfilterResult：从结果中["+analysisResult+"]祛除特殊符号前缀。。");
                    analysisResult = analysisResult.substring(kIdx+1);
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
        for(String temp : containKeys) {
            if(analysisResult.contains(temp)) {
                analysisResult = null;
            }
        }
        return analysisResult;
    }

}
