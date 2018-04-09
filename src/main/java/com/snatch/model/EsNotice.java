package com.snatch.model;

import java.io.Serializable;

public class EsNotice implements Serializable{

	private static final long serialVersionUID = 1L;
	private String source;//数据源标识
	private String uuid;
	private String url;
	private String title;
	private String openDate;
	private String content;
	private String province;
	private String city;
	private String county;
    /**
     * 对应noticeSnatch中的catchType
     * 1:补充  2：答疑  3：流标    4：澄清  5：延期 6：更正  7：废标  8：终止 9：修改  10：控制价  11：合同  12：资审结果
     * 21:补充  22：答疑  23：流标    24：澄清  25：延期
     */
	private Integer type; //catchtype字段
	private Integer rank;
	private Integer redisId;
	private Integer websitePlanId;
	private String tableName;

    private String snatchNumber;   // 抓取批次
    private String areaRank;           // 地区等级
    private String businessType; //区分政府采购与工程类公告


    private String otherType;//兼容旧程序

    /**
     * 类型:0施工;1设计,2监理,3采购
     * 对应snatchNotice中的type 0 对应 3，1对应其他 需要转换入库
     */
    private String biddingType;//兼容旧程序

	private AnalyzeDetail detail;

	private AnalyzeDetailZhongBiao detailZhongBiao;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public String getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(String biddingType) {
        this.biddingType = biddingType;
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public AnalyzeDetailZhongBiao getDetailZhongBiao() {
		return detailZhongBiao;
	}

	public void setDetailZhongBiao(AnalyzeDetailZhongBiao detailZhongBiao) {
		this.detailZhongBiao = detailZhongBiao;
	}

	public AnalyzeDetail getDetail() {
		return detail;
	}

	public void setDetail(AnalyzeDetail detail) {
		this.detail = detail;
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getRedisId() {
		return redisId;
	}
	public void setRedisId(Integer redisId) {
		this.redisId = redisId;
	}
	public Integer getWebsitePlanId() {
		return websitePlanId;
	}
	public void setWebsitePlanId(Integer websitePlanId) {
		this.websitePlanId = websitePlanId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
