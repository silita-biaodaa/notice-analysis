package com.silita.biaodaa.task;

import com.silita.biaodaa.disruptor.DisruptorOperator;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Component
public class TestTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DisruptorOperator disruptorOperator;

    @Autowired
    RedisTemplate redisTemplate;


    private Lock lock = new ReentrantLock();//基于底层IO阻塞考虑


    @Override
    public void run() {
        logger.info("=================定时任务测试执行=============");

        try {
            Notice notice= takeFromHead(0);
            //notice转化为EsNotice
            EsNotice esNotice = noticeToEsNotice(notice);
            disruptorOperator.publish(esNotice);
        } catch (Exception e) {
            logger.error("定时任务出错",e);
        }


    }

    public Notice takeFromHead(int timeout) throws InterruptedException{
        lock.lockInterruptibly();
        try{
            return (Notice) redisTemplate.opsForList().leftPop("liuqi",timeout, TimeUnit.SECONDS);
        }finally{
            lock.unlock();
        }
    }

    private EsNotice noticeToEsNotice(Notice notice){
        EsNotice esNotice = null;
        if (notice != null) {
            esNotice = new EsNotice();
            esNotice.setTitle(notice.getTitle());
            esNotice.setContent(notice.getContent());
            esNotice.setUrl(notice.getUrl());
            esNotice.setOpenDate(notice.getOpendate());
            esNotice.setRedisId(notice.getRedisId());
            Integer type = Integer.parseInt(notice.getCatchType());
            esNotice.setType(type);//catchType
            esNotice.setProvince(notice.getProvinceCode());
            esNotice.setCity(notice.getCityCode());
            esNotice.setCounty(notice.getCountyCode());
            esNotice.setSnatchNumber(notice.getSnatchNumber());
            esNotice.setAreaRank(notice.getAreaRank());
            esNotice.setBusinessType(notice.getType());//区分工程类与政府采购类
            esNotice.setSource(notice.getSource());

            Dimension dimension = notice.getDimension();
            if(notice.getCatchType().equals("2")) { //中标
                AnalyzeDetailZhongBiao zdzb = new AnalyzeDetailZhongBiao();
                zdzb.setNoticeUrl(notice.getUrl());
                zdzb.setTitle(notice.getTitle());
                zdzb.setGsDate(notice.getOpendate());
                zdzb.setProvince(notice.getProvince());
                zdzb.setCity(notice.getCity());
                zdzb.setCounty(notice.getCounty());
                zdzb.setRedisId(notice.getRedisId());
                if (notice.getDimension() != null && MyStringUtils.isNotDefaultStringAndNull(notice.getDimension().getRelation_url())) {
                    zdzb.setRelationUrl(notice.getDimension().getRelation_url());
                }
                zhongbiaoReplaceFiled(dimension,zdzb);//接收抓取的维度信息
                esNotice.setDetailZhongBiao(zdzb);
            } else {
                AnalyzeDetail ad = new AnalyzeDetail();
                ad.setNoticeUrl(notice.getUrl());
                ad.setTitle(notice.getTitle());
                ad.setGsDate(notice.getOpendate());
                ad.setProvince(notice.getProvince());
                ad.setCity(notice.getCity());
                ad.setCounty(notice.getCounty());
                ad.setRedisId(notice.getRedisId());
                if (notice.getDimension() != null && MyStringUtils.isNotDefaultStringAndNull(notice.getDimension().getRelation_url())) {
                    ad.setRelationUrl(notice.getDimension().getRelation_url());
                }

                zhaobiaoReplaceFiled(dimension,ad);//接收抓取的维度信息
                esNotice.setDetail(ad);
            }
        }
        return esNotice;
    }


    private void zhaobiaoReplaceFiled(Dimension dimension, AnalyzeDetail ad) {
        if (dimension != null) {
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getProjDq())) {
                ad.setProjDq(dimension.getProjDq());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getProjXs())) {
                ad.setProjXs(dimension.getProjXs());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getProjType())) {
                ad.setProjType(dimension.getProjType());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getBmStartDate())) {
                ad.setBmStartDate(dimension.getBmStartDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getBmEndDate())) {
                ad.setBmEndDate(dimension.getBmEndDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getBmEndTime())) {
                ad.setBmEndTime(dimension.getBmEndTime());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getTbEndDate())) {
                ad.setTbEndDate(dimension.getTbEndDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getTbEndTime())) {
                ad.setTbEndTime(dimension.getTbEndTime());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getTbAssureEndDate())) {
                ad.setTbAssureEndDate(dimension.getTbAssureEndDate());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getTbAssureEndTime())) {
                ad.setTbAssureEndTime(dimension.getTbAssureEndTime());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getTbAssureSum())) {
                ad.setTbAssureSum(dimension.getTbAssureSum());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getZbName())) {
                ad.setZbName(dimension.getZbName());
            }
            if(MyStringUtils.isNotDefaultStringAndNull(dimension.getFileCost())) {
                ad.setFileCost(dimension.getFileCost());
            }
            if(MyStringUtils.isNotDefaultStringAndNull(dimension.getDlName())) {
                ad.setDlName(dimension.getDlName());
            }
            if(MyStringUtils.isNotDefaultStringAndNull(dimension.getProjSum())) {
                ad.setProjSum((dimension.getProjSum()));
            }
        }
    }

    private AnalyzeDetailZhongBiao zhongbiaoReplaceFiled(Dimension dimension, AnalyzeDetailZhongBiao ad) {
        if (dimension!= null) {
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getOneOffer())) {
                ad.setOneOffer(dimension.getOneOffer());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getOneProjDuty())) {
                ad.setOneProjDuty(dimension.getOneProjDuty());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getOneName())) {
                ad.setOneName(dimension.getOneName());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getTwoName())) {
                ad.setTwoName(dimension.getTwoName());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getThreeName())) {
                ad.setThreeName(dimension.getThreeName());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getProjectTimeLimit())) {
                ad.setProjectTimeLimit(dimension.getProjectTimeLimit());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getProjDq())) {
                ad.setProjDq(dimension.getProjDq());
            }
            if (MyStringUtils.isNotDefaultStringAndNull(dimension.getProjXs())) {
                ad.setProjXs(dimension.getProjXs());
            }
        }
        return ad;
    }




}
