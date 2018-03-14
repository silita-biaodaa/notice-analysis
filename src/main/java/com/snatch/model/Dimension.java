package com.snatch.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 维度信息
 * Created by maofeng on 2017/12/26.
 */
@Getter
@Setter
public class Dimension implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer noticeId;
    private String catchType;   // 公告类别
    private String cert;        // 资质
    private String projDq;      // 项目地区（市级）
    private String projXs;      // 项目县市 （县级）
    private String projType;    // 项目类型
    private String projSum;     // 项目金额
    private String pbMode;      // 评标办法
    private String bmStartDate; // 报名开始时间
    private String bmEndDate;   // 报名结束时间
    private String bmEndTime;   // 报名结束时间点
    private String bmSite;      // 报名地点
    private String tbAssureSum; // 投标保证金
    private String lyAssureSum; // 履约保证金
    private String slProveSum;  // 其他保证金
    private String tbAssureEndDate; // 投标保证金截止时间
    private String tbAssureEndTime; // 投标保证金截止时点
    private String assureEndDate;   // 保证金截止时间
    private String assureEndTime;   // 保证金截止时点
    private String tbEndDate;       // 投标截止时间
    private String tbEndTime;       // 投标截止时点
    private String kbSite;          // 开标地点
    private String registrationForm;// 报名方式
    private String block;           // 标段信息
    private String zbName;          // 招标人
    private String zbContactMan;    // 招标联系人
    private String zbContactWay;    // 招标联系方式
    private String dlName;          // 代理人
    private String dlContactMan;    // 代理联系人
    private String dlContactWay;    // 代理联系方式
    private String oneName;         // 第一中标候选人
    private String twoName;         // 第二中标候选人
    private String threeName;       // 第三中标候选人
    private String kbStaffAsk;      // 开标人员
    private String oneOffer;        // 报价
    private String projectTimeLimit;// 项目工期
    private String oneProjDuty;     // 项目负责人
    private String fileCost;    //开标文件费
    private String file_url;        // 附件url 多个url逗号隔开
    private String relation_url;    // 相关公告url 多个url逗号隔开
}