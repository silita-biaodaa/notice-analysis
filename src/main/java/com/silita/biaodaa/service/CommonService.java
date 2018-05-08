package com.silita.biaodaa.service;

import com.silita.biaodaa.dao.CommonMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dh on 2018/4/4.
 */
@Service
public class CommonService {
    @Autowired
    CommonMapper commonMapper;

    private Log logger = LogFactory.getLog(this.getClass());

    @Cacheable(value = "regexCache", key="#field")
    public List<Map<String, Object>> queryRegexByField( String field){
        logger.debug("###查詢數據庫queryRegexByField:"+field);
        return commonMapper.queryRegexByField(field);
    }

    @Cacheable(value = "regexCache", key="#field+'Map'")
    public Map<String ,List<Map<String, Object>>> queryRegexMapByField( String field){
        logger.debug("###查詢數據庫queryRegexByField:"+field);
        return sortRegList(commonMapper.queryRegexByField(field));
    }

    /**
     * 根据reg_type对表达式进行分类
     * @param regList
     * @return
     */
    private Map<String ,List<Map<String, Object>>> sortRegList(List<Map<String, Object>> regList){
        Map<String ,List<Map<String, Object>>> regListMap=new HashMap<String ,List<Map<String, Object>>>();
        if(regList!=null && regList.size()>0) {
            List<String> typeList = new ArrayList<>();
            for (Map record : regList) {
                String reg_type = (String) record.get("reg_type");
                if (typeList.contains(reg_type)) {
                    regListMap.get(reg_type).add(record);
                } else {
                    List<Map<String, Object>> targetList = new ArrayList<Map<String, Object>>();
                    targetList.add(record);
                    regListMap.put(reg_type, targetList);
                    typeList.add(reg_type);
                }
            }
            typeList = null;
        }
        return regListMap;
    }
}
