package com.snatch.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hufang on 2017/3/6.
 */
@Setter
@Getter
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



}
