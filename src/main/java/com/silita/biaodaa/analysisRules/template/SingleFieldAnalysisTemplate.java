package com.silita.biaodaa.analysisRules.template;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.utils.MyStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析模板类（单字段解析）
 */
public abstract class SingleFieldAnalysisTemplate implements SingleFieldAnalysis {

    private Log logger = LogFactory.getLog(SingleFieldAnalysisTemplate.class);

    @Autowired
    CommonService commonService;

    protected String fieldName;

    /**
     * 初始化，设置维度名
     */
    protected abstract void init();

    @Override
    public String analysis(String segment,String keyWork) throws Exception{
        init();
        if (MyStringUtils.isNull(fieldName)){
            throw new Exception ("fieldName is null!");
        }
        return analyzeSingleField(segment);
    }

    /**
     * 同一个范围匹配结果内，在“默认精准匹配逻辑”之前执行的解析规则，可由子类实现
     * @param regListMap
     * @param matchPart
     * @param rangeRegex
     * @return
     */
    protected String beforeAccurateMatch(Map<String ,List<Map<String, Object>>> regListMap,String matchPart,String rangeRegex){
        return null;
    }

    /**
     * 同一个范围匹配结果内，在“默认精准匹配逻辑”没有匹配值之后执行的解析规则，可由子类实现
     * @param regListMap
     * @param matchPart
     * @param rangeRegex
     * @return
     */
    protected String afterAccurateMatch(Map<String ,List<Map<String, Object>>> regListMap,String matchPart,String rangeRegex){
        return null;
    }

    /**
     * 维度解析主逻辑
     * return 维度解析结果
     */
    public String analyzeSingleField(String html) {
        String analysisResult = null;

        //获取该字段关联的规则
        Map<String ,List<Map<String, Object>>> regListMap = commonService.queryRegexMapByField(fieldName);
        if(regListMap!=null && regListMap.keySet().size()>0){
            List<Map<String, Object>> rangeList = regListMap.get("range");//范围匹配规则
            List<Map<String, Object>> accurateList = regListMap.get("accurate");//精准匹配规则
            List<Map<String, Object>> filterResultRegList= regListMap.get("filterResult");//结果过滤规则
            //1.范围匹配
            outerMtr: for(Map rangeMap:rangeList) {
                String rangeRegex=(String)rangeMap.get("regex")+rangeMap.get("ext_content");
                Pattern outerPtn = Pattern.compile(rangeRegex,Pattern.CASE_INSENSITIVE);
                Matcher outerMtr = outerPtn.matcher(html);
                String matchPart = null;

                while (outerMtr.find()) {
                    matchPart = outerMtr.group();
                    //仅只匹配出表达式的字符段跳过
                    if(MyStringUtils.isNull(matchPart.replace(outerMtr.group(1),"").trim())){
                        continue;
                    }

                    //2.精准匹配前的业务规则匹配，匹配出值则跳过后续匹配
                    analysisResult =beforeAccurateMatch(regListMap,matchPart,rangeRegex);
                    if(MyStringUtils.isNotNull(analysisResult)){
                        break outerMtr;
                    }

                    //3.默认精准匹配
                    for (Map accurateMap:accurateList) {
                        String innerRegex = accurateMap.get("regex").toString();
                        Pattern innerPtn = Pattern.compile(innerRegex,Pattern.CASE_INSENSITIVE);
                        Matcher innerMtr = innerPtn.matcher(matchPart);

                        logger.debug(html + "\n[regex:" + innerRegex + "][groupCount:" + innerMtr.groupCount() + "]");
                        while (innerMtr.find()) {
                            int gCount = innerMtr.groupCount();
                            if (innerMtr.groupCount() > 2) {
                                try {
                                    analysisResult = innerMtr.group().replaceFirst(innerMtr.group(1), "").replaceFirst(innerMtr.group(gCount), "");//去掉首尾
                                }catch (Exception e){
//                                logger.error(e.getMessage(),e);
                                    analysisResult = innerMtr.group().replaceFirst(innerMtr.group(1), "");
                                }
                            } else if (innerMtr.groupCount() > 1) {
                                analysisResult = innerMtr.group().replaceFirst(innerMtr.group(1), "");//去掉首
                            } else {
                                analysisResult = innerMtr.group();
                            }
                            if (MyStringUtils.isNotNull(analysisResult)) {
                                logger.info( "\n##默认精准匹配结果:[analysisResult:" + analysisResult + "] by " +
                                        "[rangeRegex:\""+rangeRegex+"\"][innerRegex:\"" + innerRegex + "\"]" +
                                        "[groupCount:" + innerMtr.groupCount() + "]\n##被解析片段：" + matchPart );
                                break outerMtr;
                            }
                        }
                    }

                    //4.默认精准匹配没有匹配出结果时，其他自定义逻辑匹配
                    analysisResult =afterAccurateMatch(regListMap,matchPart,rangeRegex);
                    if(MyStringUtils.isNotNull(analysisResult)){
                        break outerMtr;
                    }
                }
                matchPart=null;
            }

            //5.过滤解析结果无效字符
            if(MyStringUtils.isNotNull(analysisResult)) {
                analysisResult = filterAnalysisResult(analysisResult, filterResultRegList);
            }
        }
        return analysisResult;
    }

    /**
     * 根据过滤规则集合，过滤匹配字符
     * @param str
     * @param filterResultRegList
     * @return
     */
    private String filterAnalysisResult(String str,List<Map<String, Object>> filterResultRegList){
        if(MyStringUtils.isNull(str)){
            return null;
        }
        for(Map regexMap: filterResultRegList) {
            String regex =  (String)regexMap.get("regex");
            Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = ptn.matcher(str);
            String tmp = null;
            while (matcher.find()) {
                tmp=  matcher.group();
                if(tmp!=null && !tmp.equals(str)) {
                    str = str.replaceAll(tmp, "");
                }
            }
        }
        return str;
    }


}
