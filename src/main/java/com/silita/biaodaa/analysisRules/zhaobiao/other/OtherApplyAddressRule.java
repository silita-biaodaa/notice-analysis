package com.silita.biaodaa.analysisRules.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.dao.ApplyAddressMapper;
import com.silita.biaodaa.dao.CommonMapper;
import com.silita.biaodaa.utils.MyStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 其他站点报名地址解析
 * Created by dh on 2018/3/20.
 */
@Component
public class OtherApplyAddressRule implements SingleFieldAnalysis {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplyAddressMapper applyAddressMapper;

    @Autowired
    CommonMapper commonMapper;

    @Override
    public String analysis(String segment,String keyWork) {
        return analyzeApplyAddress(segment);
    }

    /**
     * 解析报名地址
     * 数据匹配
     * return 报名地址
     */
    public String analyzeApplyAddress(String html) {
        String address = null;
        List<Map<String, Object>> arList = commonMapper.queryRegexByField("applyAddress");
        for (int i = 0; i < arList.size(); i++) {
            Map map = arList.get(i);
            String start = map.get("startKey").toString();
            String end = map.get("endKey").toString();
            String regex = map.get("regex").toString();
            Pattern r = Pattern.compile(regex);
            Matcher m = r.matcher(html);

            logger.debug(html+"\n[regex:"+regex+"][groupCount:"+m.groupCount()+"]");
            while (m.find()) {
                if (m.groupCount() > 1) {
                    address = m.group().replaceFirst(m.group(1),"").replaceFirst(m.group(2),"");
                } else {
                    address = m.group();
                }
            }

//            logger.debug("解析段："+html+"\n[解析值："+address+"][regex:"+regex+"][groupCount:"+m.groupCount()+"][address:"+address+"]");
            if(MyStringUtils.isNotNull(address)){
                logger.info("##\n解析段："+html+"\n解析结果:[address:"+address+"] by [regex:"+regex+"][groupCount:"+m.groupCount()+"]");
                break;
            }
        }

        if(MyStringUtils.isNull(address)) {
            String onLineApply = "网上报名";
            if (html.indexOf("下载招标文件") != -1 || html.indexOf("下载获取招标文件") != -1) {
                return onLineApply;
            }
            int start = html.indexOf("下载");
            int end = html.indexOf("招标文件");
            if ((start != -1 && end != -1) && (end - start > 0 && end - start < 20)) {
                return onLineApply;
            }
            if (html.indexOf("在") != -1 && html.indexOf("进行网上下载") != -1) {
                return onLineApply;
            }
            address="详见原文";
        }else{
            if(address!=null && address.length()>200) {
                address = address.substring(0, 200);
            }
        }

        return address;
    }


}
