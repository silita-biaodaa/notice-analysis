package com.silita.biaodaa.analysisRules.inter;

import com.silita.biaodaa.analysisRules.vo.AnalysisTd;
import com.snatch.model.EsNotice;

import java.util.List;
import java.util.Map;

/**
 * 表格内容解析接口
 */
public interface TableAnalysis {
    /**
     * 解析主逻辑
     * @param esNotice
     * @param segment
     * @param flag
     * @return
     * @throws Exception
     */
    Map<String, String> analysis(EsNotice esNotice, String segment,String flag)  throws Exception;

    /**
     * 格式化html中的二维表
     * @param segment
     * @return
     * @throws Exception
     */
    List<List<AnalysisTd>> parseContent(String segment,String flag)throws Exception;

}
