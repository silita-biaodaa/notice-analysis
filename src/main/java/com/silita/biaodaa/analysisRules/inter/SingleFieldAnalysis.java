package com.silita.biaodaa.analysisRules.inter;

/**
 * 解析规则接口
 * 解析单个字段
 */
public interface SingleFieldAnalysis {
    String analysis(String segment,String keyWork)  throws Exception;
}
