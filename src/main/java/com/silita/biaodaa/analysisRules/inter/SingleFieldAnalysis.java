package com.silita.biaodaa.analysisRules.inter;

import com.snatch.model.EsNotice;

/**
 * 解析规则接口
 * 解析单个字段
 */
public interface SingleFieldAnalysis {
    String analysis(String segment, EsNotice esNotice, String keyWork)  throws Exception;
}
