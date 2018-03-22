package com.silita.biaodaa.analysisRules.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 其他站点报名地址解析
 * Created by dh on 2018/3/20.
 */
@Component
public class OtherApplyAddress implements SingleFieldAnalysis {
    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    @Override
    public String analysis(String segment,String keyWord) {
        return noticeAnalyzeService.analyzeApplyAddress(segment);
    }
}
