package com.silita.biaodaa.service;

import com.silita.biaodaa.dao.CompanyMapper;
import com.silita.biaodaa.utils.BeanUtils;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dh on 2018/10/17.
 */
@Service
public class CompanyService {

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
}
