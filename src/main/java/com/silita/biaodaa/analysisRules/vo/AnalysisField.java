package com.silita.biaodaa.analysisRules.vo;

import com.silita.biaodaa.utils.MyStringUtils;

import java.util.Arrays;

/**
 * 表格解析元素实体
 * Created by dh on 2018/10/30.
 */
public class AnalysisField {

    private int maxLength = 200;

    /** 疑似 表头（label）*/
    private boolean delete;

    /** 疑似 表头（label）*/
    private String title;
    /** 标准字段描述*/
    private String desc;
    /** 疑似 对应值*/
    private String[] values;

    /** 数据抽取方式  1：横向 2：纵向*/
    private int extractStyle;

    /** 标题关键值*/
    private String titleKey;

    /** 标题附带字符*/
    private String titleAttach;

    public AnalysisField() {
    }

    public AnalysisField(int extractStyle) {
        this.extractStyle = extractStyle;
    }

    public AnalysisField(String title, String[] values, String desc, int extractStyle) {
        this.title = MyStringUtils.controllLength(title,maxLength);
        this.values = MyStringUtils.controllLength(values,maxLength);
        this.desc = desc;
        this.extractStyle = extractStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalysisField that = (AnalysisField) o;

        if (!title.equals(that.title)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(values, that.values);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public String getTitleAttach() {
        return titleAttach;
    }

    public void setTitleAttach(String titleAttach) {
        this.titleAttach = titleAttach;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        this.title = MyStringUtils.controllLength(title,maxLength);
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = MyStringUtils.controllLength(values,maxLength);
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
