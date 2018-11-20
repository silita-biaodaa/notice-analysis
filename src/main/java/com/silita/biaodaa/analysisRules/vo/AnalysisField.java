package com.silita.biaodaa.analysisRules.vo;

import java.util.Arrays;

/**
 * 表格中的元素实体
 * Created by dh on 2018/10/30.
 */
public class AnalysisField {
    private String title;

    private String[] values;

    /** 数据抽取方式  1：横向 2：纵向*/
    private int extractStyle;

    public AnalysisField() {
    }

    public AnalysisField(int extractStyle) {
        this.extractStyle = extractStyle;
    }

    public AnalysisField(String title, String[] values, int extractStyle) {
        this.title = title;
        this.values = values;
        this.extractStyle = extractStyle;
    }

    public int getExtractStyle() {
        return extractStyle;
    }

    public void setExtractStyle(int extractStyle) {
        this.extractStyle = extractStyle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public void addValue(String v){
        if(values==null){
            values = new String[]{v};
        }else{
            values =Arrays.copyOf(values,values.length+1);
            values[values.length-1]=v;
        }
    }

    public String showValues(){
        return Arrays.deepToString(values);
    }
}
