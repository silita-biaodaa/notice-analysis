package com.snatch.model;

import java.io.Serializable;

public class Notice implements Serializable{
    private static final long serialVersionUID = 1111L;
    private String source;                     //抓取源所属省份
    private int id;                        	//公告id
    private Integer redisId;
    private String title;                     	//公告标题
    private String noticeUuid;                     	//
    private String opendate;                 	//公示日期
    private String content;                  	//公告内容
    private String url;                       	//公告url
    private String pdfURL;                   	//公告内容为pdf时pdf的url
    private String areaCode;                 	//地区code
    private String catchType;                	//抓取公告类型 招标公告 中标公告 补充公告 等
    private String noticeType;                	//公告类型
    private String catchTime;                	//抓取时间
    private String province;                   	//省
    private String city;                      	//市
    private String county;                      //县
    private String provinceCode="";                      //
    private String cityCode="";                      //
    private String countyCode="";                      //
    private String photoUrl;                    //照片的Url
    private String type;
    private String snatchNumber;                // 抓取批次
    private String areaRank;                    // 地区等级 （0:省   1：市  2:县）

    private Dimension dimension;                // 维度信息



    private Integer expFlag=0;					//异常数据用

    private String syncUrl=null;//公告内容的异步url，此变量有值时详情内容从此url取。前端展示还是以url显示。


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRedisId() {
        return redisId;
    }

    public void setRedisId(Integer redisId) {
        this.redisId = redisId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoticeUuid() {
        return noticeUuid;
    }

    public void setNoticeUuid(String noticeUuid) {
        this.noticeUuid = noticeUuid;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPdfURL() {
        return pdfURL;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCatchType() {
        return catchType;
    }

    public void setCatchType(String catchType) {
        this.catchType = catchType;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getCatchTime() {
        return catchTime;
    }

    public void setCatchTime(String catchTime) {
        this.catchTime = catchTime;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSnatchNumber() {
        return snatchNumber;
    }

    public void setSnatchNumber(String snatchNumber) {
        this.snatchNumber = snatchNumber;
    }

    public String getAreaRank() {
        return areaRank;
    }

    public void setAreaRank(String areaRank) {
        this.areaRank = areaRank;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Integer getExpFlag() {
        return expFlag;
    }

    public void setExpFlag(Integer expFlag) {
        this.expFlag = expFlag;
    }

    public String getSyncUrl() {
        return syncUrl;
    }

    public void setSyncUrl(String syncUrl) {
        this.syncUrl = syncUrl;
    }
}
