package com.silita.biaodaa.analysisRules.vo;

import java.util.List;

/**
 * 表格实体对象
 * Created by dh on 2018/10/30.
 */
public class AnalysisTable {
    private List<AnalysisField> fieldElement;

    public List<AnalysisField> getFieldElement() {
        return fieldElement;
    }

    public void setFieldElement(List<AnalysisField> fieldElement) {
        this.fieldElement = fieldElement;
    }
}
