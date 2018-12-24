package com.silita.biaodaa.disruptor.event;

import com.snatch.model.EsNotice;

/**
 * Created by zhangxiahui on 18/3/13.
 */


public class AnalyzeEvent {
    private EsNotice esNotice;

    public EsNotice getEsNotice() {
        return esNotice;
    }

    public void setEsNotice(EsNotice esNotice) {
        this.esNotice = esNotice;
    }
}
