package com.snatch.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jianlan on 2017/3/22.
 */
@Getter
@Setter
public class AnalyzeDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer redisId;
    private String content;                         //
    private String title;                         //
    private String noticeUrl;                         //公告url
    private String tbAssureSum;                         //保证金
    private String projSum;                         //项目金额
    private String assureSumRemit;//保证金汇款方式

    private String bmStartDate;              //报名时间
    private String bmEndDate;           //报名截止时间
    private String bmEndTime;           //报名截止时间点

    private String bmSite;                         //报名地址
    private String kbSite;                      //开标地址

    private String tbEndDate;                         //投标截止日期
    private String analyzeDate;                         //解析日期
    private String tbEndTime;//投标截止日期点

    private String gsDate;//公示时间
    private String province;//省
    private String city;//市
    private String county;//县
    private String projType;//公告类型。招标公告1；中标公告2
    private String zzRank;//资质
    private String pbMode;//评标办法
    private String projDq;//项目地区
    private String projXs;//项目县市


    private String tbAssureEndDate;//投标保证金截止时间
    private String tbAssureEndTime;//投标保证金截止时间点
    private String lyAssureSum;//履约保证金
    private String slProveSum;//其他保证金
    private String assureEndDate;//保证金截止时间
    private String assureEndTime;//保证金截止时间点
    private String zgCheckDate;//资格审查时间
    private String kbStaffAsk;//开标人员要求
    private String fileCost;//开标文件费
    private String otherCost;//图纸及其他费用
    private String zbName;//招标人
    private String zbContactMan;//招标联系人
    private String zbContactWay;//招标联系方式
    private String dlName;          // 代理人
    private String dlContactMan;//代理联系人
    private String dlContactWay;//代理联系方式
    private String personRequest;//人员要求
    private String shebaoRequest;//社保要求
    private String yejiRequest;//业绩要求
    private String registrationForm;//报名方式
    private String projectTimeLimit;//项目工期
    private String projectCompletionDate;//计划竣工时间
    private String supplementNoticeNumber;//补充公共次数
    private String supplementNoticeReason;//补充公告原因
    private String flowStandardFlag;//是否流标
    private String money;//资金来源
    private String block;//标段信息
    private String relationUrl; // 相关公告
}
