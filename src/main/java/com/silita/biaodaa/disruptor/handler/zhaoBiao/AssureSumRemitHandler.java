package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.EsNotice;
import com.snatch.model.Notice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 保证金汇款方式
 * Created by maofeng on 2018/3/19.
 */
@Component
public class AssureSumRemitHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    public AssureSumRemitHandler () {
        this.fieldDesc = "保证金汇款方式";
    }

    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        String assureSumRemit = "";
        AnalyzeDetail ad = event.getEsNotice().getDetail();
        EsNotice notice = event.getEsNotice();
        try {
            assureSumRemit = noticeAnalyzeService.analyzeAssureSumRemit(notice.getContent());
            logger.info("===解析["+notice.getTitle()+"]的保证金汇款方式["+assureSumRemit+"]===");
            if (!"".equals(assureSumRemit) && null != assureSumRemit && !Constant.DEFAULT_STRING.equals(assureSumRemit) && ad.getAssureSumRemit() == null) {
                ad.setAssureSumRemit(assureSumRemit);
            }
        } catch (Exception e) {
            System.out.println("error--li" + e.getMessage());
            logger.error("error--li" + e, e);
        }
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getAssureSumRemit();
    }

    @Override
    protected String executeAnalysis(String stringPart,String source) {
        return noticeAnalyzeService.analyzeAssureSumRemit(stringPart);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object assureSumRemit) {
        esNotice.getDetail().setAssureSumRemit((String)assureSumRemit);
    }

}
