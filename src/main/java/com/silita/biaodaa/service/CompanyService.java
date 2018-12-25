package com.silita.biaodaa.service;

import com.silita.biaodaa.dao.CompanyMapper;
import com.silita.biaodaa.utils.BeanUtils;
import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dh on 2018/10/17.
 */
@Service
public class CompanyService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CompanyMapper companyMapper ;

    public  boolean existsCorpName(String comName){
        int c = companyMapper.existsCompany(comName);
        if(c>0){
            return true;
        }else{
            return false;
        }
    }

    public void saveSuspectCompanyName(String filedName,String analysisResult, EsNotice esNotice){
        Map map = new HashMap();
        map.put("id", BeanUtils.buildId());
        map.put("field",filedName);
        map.put("com_name",analysisResult);
        map.put("title",esNotice.getTitle());
        map.put("url",esNotice.getUrl());
        map.put("source",esNotice.getSource());
        companyMapper.insertSuspectCompanyName(map);
    }

    @Cacheable(value = "queryProvComName", key="'queryProvComName'+#source")
    public List<String> queryProvComName(String source){
        logger.debug("queryProvComName查询数据库。。[source:"+source+"]");
        Map map = new HashMap();
        map.put("provName",source2ProvName(source));
        return companyMapper.queryProvComName(map);
    }

    private String source2ProvName(String source){
        String pName = null;
        switch (source){
            case "beij": pName="北京市";break;
            case "tianj": pName="天津市";break;
            case "hebei": pName="河北省";break;
            case "sanx": pName="山西省";break;
            case "neimg": pName="内蒙古自治区";break;
            case "liaon": pName="辽宁省";break;
            case "jil": pName="吉林省";break;
            case "heilj": pName="黑龙江省";break;
            case "shangh": pName="上海市";break;
            case "jiangs": pName="江苏省";break;
            case "zhej": pName="浙江省";break;
            case "anh": pName="安徽省";break;
            case "fuj": pName="福建省";break;
            case "jiangx": pName="江西省";break;
            case "shand": pName="山东省";break;
            case "henan": pName="河南省";break;
            case "hubei": pName="湖北省";break;
            case "guangd": pName="广东省";break;
            case "guangx": pName="广西壮族自治区";break;
            case "hain": pName="海南省";break;
            case "chongq": pName="重庆市";break;
            case "sichuan": pName="四川省";break;
            case "guiz": pName="贵州省";break;
            case "yunn": pName="云南省";break;
            case "shanxi": pName="陕西省";break;
            case "gans": pName="甘肃省";break;
            case "qingh": pName="青海省";break;
            case "ningx": pName="宁夏回族自治区";break;
            case "hunan": pName="湖南省";break;
            case "xinjiang": pName="新疆维吾尔自治区";break;
            case "xizang": pName="西藏自治区";break;
            case "hk": pName="香港";break;
            case "macau": pName="澳门";break;
            case "taiwan": pName="台湾";break;
            default:logger.error("企业省份转换失败！[source:"+source+"]");
        }
        return pName;
    }
}
