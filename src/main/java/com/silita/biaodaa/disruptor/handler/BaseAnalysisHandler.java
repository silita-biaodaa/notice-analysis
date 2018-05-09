package com.silita.biaodaa.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.analysisRules.inter.PreAnalysisRule;
import com.silita.biaodaa.analysisRules.preAnalysis.PreAnalysisFactory;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static com.silita.biaodaa.common.CommonMethods.getClassString;

public abstract class BaseAnalysisHandler implements EventHandler<AnalyzeEvent> {
    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    protected String fieldDesc= null;

    protected String keyWord = null;

    protected String keyValue = null;

    @Autowired
    PreAnalysisFactory preAnalysisFactory;

    private PreAnalysisRule preAnalysisRule;

    /**
     * 1.解析公告的預處理規則
     */
    private void routePreAnalysisRule(){
        this.preAnalysisRule = preAnalysisFactory.getPreAnalysisRule(getClassString(this.getClass()));
    }

    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        EsNotice esNotice = event.getEsNotice();
        routePreAnalysisRule();
        if(MyStringUtils.isNull(currentFieldValues(esNotice))) {
            String[] list = preAnalysisRule.buildAnalysisList(esNotice,"</p>","</td>");
            Object s3 = null;
            this.keyWord = null;
            this.keyValue = null;
            try {
                for(int i=0;i<list.length;i++) {
                    if(MyStringUtils.isNull(s3)) {
                        if(MyStringUtils.isNotNull(list[i])){
                            s3 = executeAnalysis(list[i],esNotice);
                            if(MyStringUtils.isNotNull(s3)) {
                                break;
                            }
                        }
                    }
                }
                if(MyStringUtils.isNotNull(s3)) {
                    saveResult(esNotice,s3);
                }else if(MyStringUtils.isNotNull(keyValue)){
                    saveResult(esNotice,keyValue);
                }
                logger.info("[title:"+esNotice.getTitle()+"]，[url:"+esNotice.getUrl()+"][fieldDesc:"+fieldDesc+"][feildValue:"+s3+"]");
            } catch (Exception e) {
                logger.error("[title:"+esNotice.getTitle()+"]，[url:"+esNotice.getUrl()+"][fieldDesc:"+fieldDesc+"][feildValue:"+s3+"]"+ e.getMessage(),e);
            }finally {
                list=null;
            }
        }
    }

    /**
     * 返回需要分析的字段值
     * @param esNotice
     * @return
     */
    protected  abstract Object currentFieldValues(EsNotice esNotice);


    /**
     * 设置每个片段的解析规则
     * @param stringPart
     * @return
     */
    protected abstract Object executeAnalysis(String stringPart,EsNotice esNotice)throws Exception;

    /**
     * 解析结果保存
     * @param esNotice
     * @param analysisResult
     */
    protected abstract void saveResult(EsNotice esNotice,Object analysisResult);


}
