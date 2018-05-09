package com.silita.biaodaa.dao;

import java.util.List;
import java.util.Map;

/**
 * 公共查询部分
 */
public interface CommonMapper {

    //TODO:兼容旧逻辑，待所有维度改造完毕后删除
    @Deprecated
    List<Map<String, Object>> queryRegexByField( String field);

    List<Map<String, Object>> queryRegexInfoByField( String field);
}
