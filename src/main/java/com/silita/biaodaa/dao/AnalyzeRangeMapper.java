package com.silita.biaodaa.dao;

import com.snatch.model.AnalyzeDetail;
import com.snatch.model.AnalyzeDetailZhongBiao;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiahui on 18/3/13.
 */
public interface AnalyzeRangeMapper {

    List<Map<String, Object>> queryAnalyzeRangeByField(String field);

    void insertAnalyzeDetail(AnalyzeDetail analyzeDetail);

    void batchInsertAnalyzeDetail(Map<String,Object> param);

    List<String> queryAnalyzeRangeBmAddr();

    List<String> queryBaseBmAddress(String address);

    List<Map<String, Object>> queryAnalyzeRangePbMode(String table);

    void insertAnalyzeDetailZhongbiao(AnalyzeDetailZhongBiao analyzeDetailZhongBiao);


}
