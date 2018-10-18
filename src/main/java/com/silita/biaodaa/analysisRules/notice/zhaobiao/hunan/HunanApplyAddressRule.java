package com.silita.biaodaa.analysisRules.notice.zhaobiao.hunan;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.dao.ApplyAddressMapper;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 湖南省报名地址解析
 * Created by dh on 2018/3/20.
 */
@Component
public class HunanApplyAddressRule implements SingleFieldAnalysis {
    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    @Autowired
    ApplyAddressMapper applyAddressMapper;

    @Autowired
    CommonService commonService;

    @Override
    public String analysis(String segment, EsNotice esNotice, String keyWord) {
        String rangeHtml="";
        String address = null;
        List<Map<String, Object>> arList = commonService.queryRegexByField("applyAddress");
        for (int i = 0; i < arList.size(); i++) {
            String start = arList.get(i).get("startKey").toString();
            String end = arList.get(i).get("endKey").toString();
            int indexStart=0;
            int indexEnd=0;
            if(!"".equals(start)){
                indexStart = segment.indexOf(start);//范围开始位置
            }
            if(!"".equals(end)){
                indexEnd = segment.indexOf(end);//范围结束位置
            }
            if(indexStart != -1 && indexEnd != -1){
                if(indexEnd > indexStart){
                    rangeHtml = segment.substring(indexStart, indexEnd+1);//截取范围之间的文本
                }else if(indexStart > indexEnd) {
                    if(segment.length() - indexStart >= 80){
                        rangeHtml = segment.substring(indexStart, indexStart + 80);
                    }else{
                        rangeHtml = segment.substring(indexStart, segment.length());//截取范围开始到结尾
                    }
                }
                List<String> addrList = applyAddressMapper.queryAnalyzeRangeBmAddr();
                for (int j = 0; j < addrList.size(); j++) {
                    int indexNum =  rangeHtml.indexOf(addrList.get(j));
                    if(indexNum != -1){
                        address = addrList.get(j);
                        break;
                    }
                }
                if(!"".equals(address)){
                    //查询标准化地址
                    List<String> baseList = applyAddressMapper.queryBaseBmAddress(address);
                    if(!baseList.isEmpty()){
                        address = baseList.get(0);
                    }
                    break;
                }
            }
        }
//		if("".equals(address)) {
        if(segment.indexOf("下载招标文件") != -1 ||segment.indexOf("下载获取招标文件") != -1) {
            return "网上下载";
        }
        int start = segment.indexOf("下载");
        int end = segment.indexOf("招标文件");
        if((start != -1 && end != -1) && (end - start > 0 && end - start <20)) {
            return "网上下载";
        }
        if(segment.indexOf("在") != -1 && segment.indexOf("进行网上下载") != -1) {
            return "网上下载";
        }
//		}
        return address;
    }
}
