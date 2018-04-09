package com.silita.biaodaa.disruptor.event;

import com.snatch.model.EsNotice;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Getter
@Setter
public class AnalyzeEvent {
    private EsNotice esNotice;
}
