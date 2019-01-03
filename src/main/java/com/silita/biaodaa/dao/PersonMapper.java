package com.silita.biaodaa.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by IntelliJ Idea 2018.1
 * Company: silita
 * Author: gemingyi
 * Date: 2019-01-03 18:34
 */
public interface PersonMapper {

    /**
     * 根据公司名称获取公司下面的人
     * @param source
     * @param comName
     * @return
     */
    List<String> listPersonNameByCompanyName(@Param("source") String source, @Param("comName") String comName);
}
