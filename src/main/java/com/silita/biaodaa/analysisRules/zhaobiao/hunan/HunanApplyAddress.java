package com.silita.biaodaa.analysisRules.zhaobiao.hunan;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 湖南省报名地址解析
 * Created by dh on 2018/3/20.
 */
@Component
public class HunanApplyAddress implements SingleFieldAnalysis {
    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    @Override
    public String analysis(String segment) {
        return noticeAnalyzeService.analyzeApplyAddress(segment);
    }
}
