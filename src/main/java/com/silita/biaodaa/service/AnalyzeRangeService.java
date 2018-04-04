package com.silita.biaodaa.service;

import com.silita.biaodaa.dao.AnalyzeRangeMapper;
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
public class AnalyzeRangeService {
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    AnalyzeRangeMapper analyzeRangeMapper;

    @Cacheable(value = "pbModeCache", key="#table")
    public List<Map<String, Object>> queryAnalyzeRangePbMode(String table){
        logger.debug("$$$查詢數據庫queryAnalyzeRangePbMode:"+table);
        return analyzeRangeMapper.queryAnalyzeRangePbMode(table);
    }
}
