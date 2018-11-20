package com.silita.biaodaa.analysisRules.vo;

/**
 * 表格解析日志对象
 * Created by dh on 2018/11/20.
 */
public class AnalysisTbLog {
    private int id;
    private int style;
    private boolean hasResult;
    private int redisId;
    private String noticeUrl;
    private String title;
    private String gsDate;
    private String origin_content;
    private String table_mapping;
    private String rows_parser;
    private int rows_parser_size;
    private String cols_parser;
    private int cols_parser_size;
    private String source;
    private String city;
    private String result_map;

    public String getResult_map() {
        return result_map;
    }

    public void setResult_map(String result_map) {
        this.result_map = result_map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }

    public int getRedisId() {
        return redisId;
    }

    public void setRedisId(int redisId) {
        this.redisId = redisId;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGsDate() {
        return gsDate;
    }

    public void setGsDate(String gsDate) {
        this.gsDate = gsDate;
    }

    public String getOrigin_content() {
        return origin_content;
    }

    public void setOrigin_content(String origin_content) {
        this.origin_content = origin_content;
    }

    public String getTable_mapping() {
        return table_mapping;
    }

    public void setTable_mapping(String table_mapping) {
        this.table_mapping = table_mapping;
    }

    public String getRows_parser() {
        return rows_parser;
    }

    public void setRows_parser(String rows_parser) {
        this.rows_parser = rows_parser;
    }

    public int getRows_parser_size() {
        return rows_parser_size;
    }

    public void setRows_parser_size(int rows_parser_size) {
        this.rows_parser_size = rows_parser_size;
    }

    public String getCols_parser() {
        return cols_parser;
    }

    public void setCols_parser(String cols_parser) {
        this.cols_parser = cols_parser;
    }

    public int getCols_parser_size() {
        return cols_parser_size;
    }

    public void setCols_parser_size(int cols_parser_size) {
        this.cols_parser_size = cols_parser_size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
