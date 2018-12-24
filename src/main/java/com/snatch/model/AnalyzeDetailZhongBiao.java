package com.snatch.model;

import java.io.Serializable;

/**
 * Created by 91567 on 2017/7/25.
 */
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

    private String projectTimeLimit ;
    private String projectCompletionDate ;
    private String block;  //   标段

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRedisId() {
        return redisId;
    }

    public void setRedisId(Integer redisId) {
        this.redisId = redisId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getGsDate() {
        return gsDate;
    }

    public void setGsDate(String gsDate) {
        this.gsDate = gsDate;
    }

    public String getProjSum() {
        return projSum;
    }

    public void setProjSum(String projSum) {
        this.projSum = projSum;
    }

    public String getProjDq() {
        return projDq;
    }

    public void setProjDq(String projDq) {
        this.projDq = projDq;
    }

    public String getProjXs() {
        return projXs;
    }

    public void setProjXs(String projXs) {
        this.projXs = projXs;
    }

    public String getPbMode() {
        return pbMode;
    }

    public void setPbMode(String pbMode) {
        this.pbMode = pbMode;
    }

    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getOneName() {
        return oneName;
    }

    public void setOneName(String oneName) {
        this.oneName = oneName;
    }

    public String getOneUUid() {
        return oneUUid;
    }

    public void setOneUUid(String oneUUid) {
        this.oneUUid = oneUUid;
    }

    public String getOneOffer() {
        return oneOffer;
    }

    public void setOneOffer(String oneOffer) {
        this.oneOffer = oneOffer;
    }

    public String getOneProjDuty() {
        return oneProjDuty;
    }

    public void setOneProjDuty(String oneProjDuty) {
        this.oneProjDuty = oneProjDuty;
    }

    public String getOneProjDutyUUid() {
        return oneProjDutyUUid;
    }

    public void setOneProjDutyUUid(String oneProjDutyUUid) {
        this.oneProjDutyUUid = oneProjDutyUUid;
    }

    public String getOneSkillDuty() {
        return oneSkillDuty;
    }

    public void setOneSkillDuty(String oneSkillDuty) {
        this.oneSkillDuty = oneSkillDuty;
    }

    public String getOneSgy() {
        return oneSgy;
    }

    public void setOneSgy(String oneSgy) {
        this.oneSgy = oneSgy;
    }

    public String getOneAqy() {
        return oneAqy;
    }

    public void setOneAqy(String oneAqy) {
        this.oneAqy = oneAqy;
    }

    public String getOneZly() {
        return oneZly;
    }

    public void setOneZly(String oneZly) {
        this.oneZly = oneZly;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName;
    }

    public String getTwoOffer() {
        return twoOffer;
    }

    public void setTwoOffer(String twoOffer) {
        this.twoOffer = twoOffer;
    }

    public String getTwoProjDuty() {
        return twoProjDuty;
    }

    public void setTwoProjDuty(String twoProjDuty) {
        this.twoProjDuty = twoProjDuty;
    }

    public String getTwoSkillDuty() {
        return twoSkillDuty;
    }

    public void setTwoSkillDuty(String twoSkillDuty) {
        this.twoSkillDuty = twoSkillDuty;
    }

    public String getTwoSgy() {
        return twoSgy;
    }

    public void setTwoSgy(String twoSgy) {
        this.twoSgy = twoSgy;
    }

    public String getTwoAqy() {
        return twoAqy;
    }

    public void setTwoAqy(String twoAqy) {
        this.twoAqy = twoAqy;
    }

    public String getTwoZly() {
        return twoZly;
    }

    public void setTwoZly(String twoZly) {
        this.twoZly = twoZly;
    }

    public String getThreeName() {
        return threeName;
    }

    public void setThreeName(String threeName) {
        this.threeName = threeName;
    }

    public String getThreeOffer() {
        return threeOffer;
    }

    public void setThreeOffer(String threeOffer) {
        this.threeOffer = threeOffer;
    }

    public String getThreeProjDuty() {
        return threeProjDuty;
    }

    public void setThreeProjDuty(String threeProjDuty) {
        this.threeProjDuty = threeProjDuty;
    }

    public String getThreeSkillDuty() {
        return threeSkillDuty;
    }

    public void setThreeSkillDuty(String threeSkillDuty) {
        this.threeSkillDuty = threeSkillDuty;
    }

    public String getThreeSgy() {
        return threeSgy;
    }

    public void setThreeSgy(String threeSgy) {
        this.threeSgy = threeSgy;
    }

    public String getThreeAqy() {
        return threeAqy;
    }

    public void setThreeAqy(String threeAqy) {
        this.threeAqy = threeAqy;
    }

    public String getThreeZly() {
        return threeZly;
    }

    public void setThreeZly(String threeZly) {
        this.threeZly = threeZly;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getRelationUrl() {
        return relationUrl;
    }

    public void setRelationUrl(String relationUrl) {
        this.relationUrl = relationUrl;
    }

    public String getProjectTimeLimit() {
        return projectTimeLimit;
    }

    public void setProjectTimeLimit(String projectTimeLimit) {
        this.projectTimeLimit = projectTimeLimit;
    }

    public String getProjectCompletionDate() {
        return projectCompletionDate;
    }

    public void setProjectCompletionDate(String projectCompletionDate) {
        this.projectCompletionDate = projectCompletionDate;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
