package com.silita.biaodaa.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.common.CommonMethods;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;

public abstract class BaseHandler implements EventHandler<AnalyzeEvent> {
    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    protected String fieldDesc= null;

    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        EsNotice esNotice = event.getEsNotice();
        AnalyzeDetail ad = esNotice.getDetail();
        if(MyStringUtils.isNull(ad.getBmSite())) {
            String[] list = CommonMethods.buildAnalysisList(esNotice);
            Object s3 = null;
            try {
                for(int i=0;i<list.length;i++) {
                    if(MyStringUtils.isNull(s3)) {
                        s3 = executeAnalysis(list[i]);
                        if(MyStringUtils.isNotNull(s3)) {
                            break;
                        }
                    }
                }
                if(MyStringUtils.isNull(s3)) {
                    saveResult(esNotice,s3);
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
     * 设置每个片段的解析规则
     * @param stringPart
     * @return
     */
    protected abstract Object executeAnalysis(String stringPart);

    /**
     * 解析结果保存
     * @param esNotice
     * @param analysisResult
     */
    protected abstract void saveResult(EsNotice esNotice,Object analysisResult);


}
