package com.silita.biaodaa.analysisRules.vo;

import java.util.Map;

/**
 * td元素实体
 * Created by dh on 2018/10/31.
 */
public class AnalysisTd {
    private String value;

    private String colspan;

    private String rowspan;

    private String clazz;

    //默认为0,不为0则为合并单元格范围
    private int childNo=0;

    private Map<String,String> others;

    public int getChildNo() {
        return childNo;
    }

    public void setChildNo(int childNo) {
        this.childNo = childNo;
    }

    /**
     *
     * @param value
     */
    public AnalysisTd(String value){
        this.value=value;
    }

    /**
     *
     * @param value
     * @param colspan
     * @param rowspan
     */
    public AnalysisTd(String value,String colspan,String rowspan){
        this.value=value;
        this.colspan=colspan;
        this.rowspan=rowspan;
    }

    public String getValue() {
        if(this.value==null){
            value="";
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColspan() {
        return colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    public String getRowspan() {
        return rowspan;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Map<String, String> getOthers() {
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }
}
