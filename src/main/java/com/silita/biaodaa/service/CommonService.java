package com.silita.biaodaa.service;

import com.silita.biaodaa.dao.CommonMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
}
