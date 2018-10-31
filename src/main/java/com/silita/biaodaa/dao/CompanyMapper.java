package com.silita.biaodaa.dao;

import java.util.Map;

/**
 * Created by dh on 2018/10/17.
 */
public interface CompanyMapper {

    int existsCompany(String comName);

    void insertSuspectCompanyName(Map argMap);
}
