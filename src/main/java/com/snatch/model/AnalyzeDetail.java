package com.snatch.model;

import java.io.Serializable;

/**
 * Created by jianlan on 2017/3/22.
 */
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

    public String getTbAssureSum() {
        return tbAssureSum;
    }

    public void setTbAssureSum(String tbAssureSum) {
        this.tbAssureSum = tbAssureSum;
    }

    public String getProjSum() {
        return projSum;
    }

    public void setProjSum(String projSum) {
        this.projSum = projSum;
    }

    public String getAssureSumRemit() {
        return assureSumRemit;
    }

    public void setAssureSumRemit(String assureSumRemit) {
        this.assureSumRemit = assureSumRemit;
    }

    public String getBmStartDate() {
        return bmStartDate;
    }

    public void setBmStartDate(String bmStartDate) {
        this.bmStartDate = bmStartDate;
    }

    public String getBmEndDate() {
        return bmEndDate;
    }

    public void setBmEndDate(String bmEndDate) {
        this.bmEndDate = bmEndDate;
    }

    public String getBmEndTime() {
        return bmEndTime;
    }

    public void setBmEndTime(String bmEndTime) {
        this.bmEndTime = bmEndTime;
    }

    public String getBmSite() {
        return bmSite;
    }

    public void setBmSite(String bmSite) {
        this.bmSite = bmSite;
    }

    public String getKbSite() {
        return kbSite;
    }

    public void setKbSite(String kbSite) {
        this.kbSite = kbSite;
    }

    public String getTbEndDate() {
        return tbEndDate;
    }

    public void setTbEndDate(String tbEndDate) {
        this.tbEndDate = tbEndDate;
    }

    public String getAnalyzeDate() {
        return analyzeDate;
    }

    public void setAnalyzeDate(String analyzeDate) {
        this.analyzeDate = analyzeDate;
    }

    public String getTbEndTime() {
        return tbEndTime;
    }

    public void setTbEndTime(String tbEndTime) {
        this.tbEndTime = tbEndTime;
    }

    public String getGsDate() {
        return gsDate;
    }

    public void setGsDate(String gsDate) {
        this.gsDate = gsDate;
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

    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getZzRank() {
        return zzRank;
    }

    public void setZzRank(String zzRank) {
        this.zzRank = zzRank;
    }

    public String getPbMode() {
        return pbMode;
    }

    public void setPbMode(String pbMode) {
        this.pbMode = pbMode;
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

    public String getTbAssureEndDate() {
        return tbAssureEndDate;
    }

    public void setTbAssureEndDate(String tbAssureEndDate) {
        this.tbAssureEndDate = tbAssureEndDate;
    }

    public String getTbAssureEndTime() {
        return tbAssureEndTime;
    }

    public void setTbAssureEndTime(String tbAssureEndTime) {
        this.tbAssureEndTime = tbAssureEndTime;
    }

    public String getLyAssureSum() {
        return lyAssureSum;
    }

    public void setLyAssureSum(String lyAssureSum) {
        this.lyAssureSum = lyAssureSum;
    }

    public String getSlProveSum() {
        return slProveSum;
    }

    public void setSlProveSum(String slProveSum) {
        this.slProveSum = slProveSum;
    }

    public String getAssureEndDate() {
        return assureEndDate;
    }

    public void setAssureEndDate(String assureEndDate) {
        this.assureEndDate = assureEndDate;
    }

    public String getAssureEndTime() {
        return assureEndTime;
    }

    public void setAssureEndTime(String assureEndTime) {
        this.assureEndTime = assureEndTime;
    }

    public String getZgCheckDate() {
        return zgCheckDate;
    }

    public void setZgCheckDate(String zgCheckDate) {
        this.zgCheckDate = zgCheckDate;
    }

    public String getKbStaffAsk() {
        return kbStaffAsk;
    }

    public void setKbStaffAsk(String kbStaffAsk) {
        this.kbStaffAsk = kbStaffAsk;
    }

    public String getFileCost() {
        return fileCost;
    }

    public void setFileCost(String fileCost) {
        this.fileCost = fileCost;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getZbName() {
        return zbName;
    }

    public void setZbName(String zbName) {
        this.zbName = zbName;
    }

    public String getZbContactMan() {
        return zbContactMan;
    }

    public void setZbContactMan(String zbContactMan) {
        this.zbContactMan = zbContactMan;
    }

    public String getZbContactWay() {
        return zbContactWay;
    }

    public void setZbContactWay(String zbContactWay) {
        this.zbContactWay = zbContactWay;
    }

    public String getDlName() {
        return dlName;
    }

    public void setDlName(String dlName) {
        this.dlName = dlName;
    }

    public String getDlContactMan() {
        return dlContactMan;
    }

    public void setDlContactMan(String dlContactMan) {
        this.dlContactMan = dlContactMan;
    }

    public String getDlContactWay() {
        return dlContactWay;
    }

    public void setDlContactWay(String dlContactWay) {
        this.dlContactWay = dlContactWay;
    }

    public String getPersonRequest() {
        return personRequest;
    }

    public void setPersonRequest(String personRequest) {
        this.personRequest = personRequest;
    }

    public String getShebaoRequest() {
        return shebaoRequest;
    }

    public void setShebaoRequest(String shebaoRequest) {
        this.shebaoRequest = shebaoRequest;
    }

    public String getYejiRequest() {
        return yejiRequest;
    }

    public void setYejiRequest(String yejiRequest) {
        this.yejiRequest = yejiRequest;
    }

    public String getRegistrationForm() {
        return registrationForm;
    }

    public void setRegistrationForm(String registrationForm) {
        this.registrationForm = registrationForm;
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

    public String getSupplementNoticeNumber() {
        return supplementNoticeNumber;
    }

    public void setSupplementNoticeNumber(String supplementNoticeNumber) {
        this.supplementNoticeNumber = supplementNoticeNumber;
    }

    public String getSupplementNoticeReason() {
        return supplementNoticeReason;
    }

    public void setSupplementNoticeReason(String supplementNoticeReason) {
        this.supplementNoticeReason = supplementNoticeReason;
    }

    public String getFlowStandardFlag() {
        return flowStandardFlag;
    }

    public void setFlowStandardFlag(String flowStandardFlag) {
        this.flowStandardFlag = flowStandardFlag;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRelationUrl() {
        return relationUrl;
    }

    public void setRelationUrl(String relationUrl) {
        this.relationUrl = relationUrl;
    }
}
