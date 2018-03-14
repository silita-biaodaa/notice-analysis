package com.snatch.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by 91567 on 2017/7/25.
 */
@Getter
@Setter
public class AnalyzeDetailZhongBiao implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer redisId;
    private String content;                         //
    private String title;                         //
    private String noticeUrl;

    private String gsDate;
    private String projSum;
    public String projDq;
    public String projXs;
    private String pbMode;
    public String projType;

    private String oneName;
    private String oneUUid;
    private String oneOffer;
    private String oneProjDuty;
    private String oneProjDutyUUid;
    private String oneSkillDuty;
    private String oneSgy;
    private String oneAqy;
    private String oneZly;

    private String twoName;
    private String twoOffer;
    private String twoProjDuty;
    private String twoSkillDuty;
    private String twoSgy;
    private String twoAqy;
    private String twoZly;

    private String threeName;
    private String threeOffer;
    private String threeProjDuty;
    private String threeSkillDuty;
    private String threeSgy;
    private String threeAqy;
    private String threeZly;

    private String province;//省
    private String city;//市
    private String county;//县

    private String relationUrl; // 相关公告
}
