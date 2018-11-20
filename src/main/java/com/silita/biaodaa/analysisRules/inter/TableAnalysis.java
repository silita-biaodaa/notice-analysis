package com.silita.biaodaa.analysisRules.inter;

import com.silita.biaodaa.analysisRules.vo.AnalysisField;
import com.silita.biaodaa.analysisRules.vo.AnalysisTable;
import com.silita.biaodaa.analysisRules.vo.AnalysisTd;
import com.snatch.model.EsNotice;

import java.util.List;
import java.util.Map;

/**
 * 表格内容解析接口
 */
public interface TableAnalysis {
    Map<String, String> analysis(EsNotice esNotice, String segment,String flag)  throws Exception;

    /**
     * 格式化html中的二维表
     * @param segment
     * @return
     * @throws Exception
     */
    List<List<AnalysisTd>> parseContent(String segment,String flag)throws Exception;

    /**
     * 识别表格类型 1：纵向型；2：横向型
     * @return
     * @throws Exception
     */
    List<AnalysisField> recognitionStyleData(String[][] tbArray)throws Exception;

    /**
     * 表格数据转换为解析模型
     * @param tDate
     * @return
     */
    AnalysisTable dataToObj(List<List> tDate);

    /**
     * 根据关键字，挑选字段值
     * @return
     */
    String pickField(String[] keys);
}
