package com.silita.biaodaa.analysisRules.notice;

/**
 * Created by dh on 2018/12/21.
 */
public class AnalysisConstant {

    /** 数据抽取来源：行式*/
    public static final int EXTRACT_STYLE_ROW=1;

    /** 数据抽取来源：列式*/
    public static final int EXTRACT_STYLE_COL=2;

    /** 表格模式—横向表格*/
    public static final int TB_STYLE_ROW=1;

    /** 表格模式—纵向表格*/
    public static final int TB_STYLE_COL=2;

    /** 表格模式—混合嵌套型表格*/
    public static final int TB_STYLE_MIX=3;


    public static final String ZHONGBIAO_TB="zhongbiao";

    public static final String ZHAOBIAO_TB="zhaobiao";

    public static final String FD_ONE_NAME="第一中标候选人";

    public static final String FD_ONE_OFFER="第一中标报价";

    public static final String FD_TIIME_LIMIT="工期";

    public static final String FD_TIMES="时间/日期";

    public static final String FD_PJ_NAME="项目名称";

    public static final String FD_PJ_NO="项目编号";

    public static final String FD_SEGMENT="标段";

    public static final String FD_ORDER="排序";

    public static final String FD_PROJECT_MANAGER="第一中标项目经理";

    /** 中标公告：表格范围优先规则*/
    public static final String TB_RANGE_BID ="(?<=(" +
            "谈判情况|中标结果|中标候选人|中标（成交）结果|参与谈判情况" +
            "|推荐中标候选人名单|参与询价情况|成交结果信息" +
            "|供应商提交响应文件情况|报价及综合得分排序表" +
            "))[\\s\\S.]*<table.*?>[\\s\\S.]*?</table.*?>";

    /**
     * 中标: 有效表格判定关键字
     */
    public static final String[] VALID_TABLE_KEYS = {"名称","单位","工程"
            ,"项目","标段","报价","第一","名次"
            ,"标段","投标","排名","得分","工期"
            ,"中标候选人","供应商名称","投标报价"};
}
