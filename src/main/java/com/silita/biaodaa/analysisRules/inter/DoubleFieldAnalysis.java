package com.silita.biaodaa.analysisRules.inter;

import com.snatch.model.EsNotice;

import java.util.List;

/**
 * 解析规则接口
 * 解析2个字段
 * Created by 91567 on 2018/3/21.
 */
public interface DoubleFieldAnalysis {
    List analysis(EsNotice esNotice, String segment);
}
