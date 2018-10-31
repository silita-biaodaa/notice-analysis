package com.silita.biaodaa.analysisRules.notice.zhaobiao.other;

import com.silita.biaodaa.analysisRules.template.SingleFieldAnalysisTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 其他站点报名地址解析
 * Created by dh on 2018/3/20.
 */
@Component
public class OtherApplyAddressRule extends SingleFieldAnalysisTemplate {

    private Log logger = LogFactory.getLog(OtherApplyAddressRule.class);

    /**
     * 设置报名地址维度名
     */
    protected void init(){
        this.fieldName="applyAddress";
    }

    /**
     * 网上报名解析逻辑
     * @param regListMap
     * @param matchPart
     * @param rangeRegex
     * @return
     */
    protected String beforeAccurateMatch(Map<String ,List<Map<String, Object>>> regListMap,String matchPart,String rangeRegex){
        String address =null;
        List<Map<String, Object>> onlineList= regListMap.get("online");//在线报名业务规则
        for(Map onLineMap: onlineList) {
            String onLineRgx = (String)onLineMap.get("regex");
            Pattern onlinePtn = Pattern.compile(onLineRgx, Pattern.CASE_INSENSITIVE);
            if (onlinePtn.matcher(matchPart).find()) {
                address = "网上报名";
                logger.info( "\n@@@线上报名解析结果:[address:" + address + "] by " +
                        "[rangeRegex:"+rangeRegex+"][innerRegex:" + onLineRgx + "]\n@@@被解析片段：" + matchPart );
                break;
            }
        }
        return address;
    }

    /**
     * 无匹配出解析值，设置默认值
     * @param regListMap
     * @param matchPart
     * @param rangeRegex
     * @return
     */
    protected String afterAccurateMatch(Map<String ,List<Map<String, Object>>> regListMap,String matchPart,String rangeRegex){
        return "现场报名";
    }

}
