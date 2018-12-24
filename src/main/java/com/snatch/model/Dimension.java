package com.snatch.model;

import java.io.Serializable;

/**
 * 维度信息
 * Created by maofeng on 2017/12/26.
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getCatchType() {
        return catchType;
    }

    public void setCatchType(String catchType) {
        this.catchType = catchType;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
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

    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getProjSum() {
        return projSum;
    }

    public void setProjSum(String projSum) {
        this.projSum = projSum;
    }

    public String getPbMode() {
        return pbMode;
    }

    public void setPbMode(String pbMode) {
        this.pbMode = pbMode;
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

    public String getTbAssureSum() {
        return tbAssureSum;
    }

    public void setTbAssureSum(String tbAssureSum) {
        this.tbAssureSum = tbAssureSum;
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

    public String getTbEndDate() {
        return tbEndDate;
    }

    public void setTbEndDate(String tbEndDate) {
        this.tbEndDate = tbEndDate;
    }

    public String getTbEndTime() {
        return tbEndTime;
    }

    public void setTbEndTime(String tbEndTime) {
        this.tbEndTime = tbEndTime;
    }

    public String getKbSite() {
        return kbSite;
    }

    public void setKbSite(String kbSite) {
        this.kbSite = kbSite;
    }

    public String getRegistrationForm() {
        return registrationForm;
    }

    public void setRegistrationForm(String registrationForm) {
        this.registrationForm = registrationForm;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
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

    public String getOneName() {
        return oneName;
    }

    public void setOneName(String oneName) {
        this.oneName = oneName;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName;
    }

    public String getThreeName() {
        return threeName;
    }

    public void setThreeName(String threeName) {
        this.threeName = threeName;
    }

    public String getKbStaffAsk() {
        return kbStaffAsk;
    }

    public void setKbStaffAsk(String kbStaffAsk) {
        this.kbStaffAsk = kbStaffAsk;
    }

    public String getOneOffer() {
        return oneOffer;
    }

    public void setOneOffer(String oneOffer) {
        this.oneOffer = oneOffer;
    }

    public String getProjectTimeLimit() {
        return projectTimeLimit;
    }

    public void setProjectTimeLimit(String projectTimeLimit) {
        this.projectTimeLimit = projectTimeLimit;
    }

    public String getOneProjDuty() {
        return oneProjDuty;
    }

    public void setOneProjDuty(String oneProjDuty) {
        this.oneProjDuty = oneProjDuty;
    }

    public String getFileCost() {
        return fileCost;
    }

    public void setFileCost(String fileCost) {
        this.fileCost = fileCost;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getRelation_url() {
        return relation_url;
    }

    public void setRelation_url(String relation_url) {
        this.relation_url = relation_url;
    }
}