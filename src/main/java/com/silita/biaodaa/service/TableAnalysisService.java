package com.silita.biaodaa.service;

import com.silita.biaodaa.analysisRules.vo.AnalysisTbLog;
import com.silita.biaodaa.dao.TableAnalysisMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dh on 2018/11/20.
 */
@Service
public class TableAnalysisService {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private TableAnalysisMapper tableAnalysisMapper;

    public void saveParseLog(AnalysisTbLog log){
        tableAnalysisMapper.insertAnlyTbLogs(log);
    }
}
