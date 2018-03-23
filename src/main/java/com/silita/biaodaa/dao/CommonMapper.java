package com.silita.biaodaa.dao;

import java.util.List;
import java.util.Map;

/**
 * 公共查询部分
 */
public interface CommonMapper {

    List<Map<String, Object>> queryRegexByField( String field);

}
