package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.Notice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiahui on 18/3/14.
 */
@Component
public class InsertAnalyzeDetailHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;



    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        AnalyzeDetail analyzeDetail = event.getAnalyzeDetail();
        Notice notice = event.getNotice();
        try {
            analyzeDetail.setRedisId(notice.getRedisId());
            analyzeDetail.setNoticeUrl(notice.getUrl());
            analyzeDetail.setTitle(notice.getTitle());
            analyzeDetail = zhaobiaoReplaceFiled(notice,analyzeDetail);
            noticeAnalyzeService.insertAnalyzeDetail(analyzeDetail);
        } catch (Exception e) {
            logger.error("入库" + e, e);
        }
    }

    private AnalyzeDetail zhaobiaoReplaceFiled(Notice no, AnalyzeDetail ad) {
        if (no.getDimension() != null) {
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getProjDq())) {
                ad.setProjDq(no.getDimension().getProjDq());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getProjXs())) {
                ad.setProjXs(no.getDimension().getProjXs());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getProjType())) {
                ad.setProjType(no.getDimension().getProjType());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getBmStartDate())) {
                ad.setBmStartDate(no.getDimension().getBmStartDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getBmEndDate())) {
                ad.setBmEndDate(no.getDimension().getBmEndDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getBmEndTime())) {
                ad.setBmEndTime(no.getDimension().getBmEndTime());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getTbEndDate())) {
                ad.setTbEndDate(no.getDimension().getTbEndDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getTbEndTime())) {
                ad.setTbEndTime(no.getDimension().getTbEndTime());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getTbAssureEndDate())) {
                ad.setTbAssureEndDate(no.getDimension().getTbAssureEndDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getTbAssureEndTime())) {
                ad.setTbAssureEndTime(no.getDimension().getTbAssureEndTime());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getTbAssureSum())) {
                ad.setTbAssureSum(no.getDimension().getTbAssureSum());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getZbName())) {
                ad.setZbName(no.getDimension().getZbName());
            }
            if(MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getFileCost())) {
                ad.setFileCost(no.getDimension().getFileCost());
            }
            if(MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getDlName())) {
                ad.setDlName(no.getDimension().getDlName());
            }
            if(MyStringUtils.isNotDefaultStringAndNull(no.getDimension().getProjSum())) {
                ad.setProjSum((no.getDimension().getProjSum()));
            }
        }
        return ad;
    }
}
