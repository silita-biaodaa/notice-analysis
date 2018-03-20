package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.AnalyzeDetail;
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

    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        String assureSumRemit = "";
        AnalyzeDetail ad = event.getAnalyzeDetail();
        Notice notice = event.getNotice();
        if ("https://www.ynggzyxx.gov.cn/jyxx/jsgcZbggDetail?guid=dc0574c9-5edd-4dc9-9ed8-d199cf817418&isOther=false".equals(notice.getUrl())) {
            System.out.println(1);
        }
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
}
