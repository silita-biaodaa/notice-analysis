package com.silita.biaodaa.dao;

import java.util.List;

/**
 * 报名地址
 */
public interface ApplyAddressMapper {

    List<String> queryAnalyzeRangeBmAddr();

    List<String> queryBaseBmAddress(String address);
}
