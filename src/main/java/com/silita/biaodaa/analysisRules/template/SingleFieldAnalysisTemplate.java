package com.silita.biaodaa.analysisRules.template;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.utils.MyStringUtils;
import com.silita.biaodaa.utils.RedisCacheUtil;
import com.snatch.model.EsNotice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.silita.biaodaa.common.Constant.PROCESS_INFO;

/**
 * 解析模板类（单字段解析）
 */
public abstract class SingleFieldAnalysisTemplate implements SingleFieldAnalysis {

    private static Log logger = LogFactory.getLog(SingleFieldAnalysisTemplate.class);

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    private String keyPre= "analysis_";

    @Autowired
    CommonService commonService;

    protected String fieldName;

    /**
     * 初始化，设置维度名
     */
    protected abstract void init();

    @Override
    public String analysis(String segment, EsNotice esNotice, String keyWork) throws Exception{
        String analysisRes =null;
        try {
            init();
            if (MyStringUtils.isNull(fieldName)){
                throw new Exception ("fieldName is null!");
            }
            analysisRes = analyzeSingleField(segment,esNotice);
            if(analysisRes!=null && analysisRes.length()>100){
                analysisRes=analysisRes.substring(0,100);
            }
        }catch (Exception e){
            logger.error(e,e);
        }catch (Error re){
            logger.error(re,re);
        }
        return analysisRes;
    }

    /**
     * 同一个范围匹配结果内，在“默认精准匹配逻辑”之前执行的解析规则，可由子类实现
     * @param regListMap 规则集合
     * @param matchPart 解析字符片段
     * @param rangeRegex 外层（范围匹配）命中规则
     * @return
     */
    protected String beforeAccurateMatch(Map<String ,List<Map<String, Object>>> regListMap,String matchPart,String rangeRegex){
        return null;
    }

    /**
     * 在“默认精准匹配逻辑”没有匹配值之后执行的解析规则，可由子类实现
     * @param regListMap 规则集合
     * @param matchPart 解析字符片段
     * @param rangeRegex 外层（范围匹配）命中规则
     * @return
     */
    protected String afterAccurateMatch(Map<String ,List<Map<String, Object>>> regListMap,String matchPart,String rangeRegex){
        return null;
    }

    /**
     * 解析值，有效性检验（具体规则子类实现）
     * @param regListMap 规则集合
     * @param analysisResult 解析结果值
     * @return
     */
    protected String verifyAnalysisResult(EsNotice esNotice,Map<String ,List<Map<String, Object>>> regListMap,String analysisResult){
        return analysisResult;
    }


    /**
     * 个性化过滤/截取规则，由子类实现
     * @param analysisResult
     * @param esNotice
     * @return
     */
    protected String customfilterResult(String analysisResult,EsNotice esNotice) {
        return analysisResult;
    }

    /**
     * @param esNotice 公告对象
     * @param regListMap 规则集合
     * @param matchPart 解析片段
     * @param rangeRegex 外层匹配命中的规则
     * @return
     */
    private String  innertMatchRules(EsNotice esNotice,Map<String ,List<Map<String, Object>>> regListMap,String matchPart,String rangeRegex){
        String result = null;
        List<Map<String, Object>> accurateList = regListMap.get("accurate");//精准匹配规则

        //1.精准匹配前,自定义规则（子类实现）
        result =beforeAccurateMatch(regListMap,matchPart,rangeRegex);
        if(MyStringUtils.isNotNull(result)){
            logger.debug("2.精准匹配前的自定义业务规则匹配值:"+result);
            if(Constant.IS_DEBUG) {
                PROCESS_INFO.put("2","精准匹配前的自定义业务规则匹配值:"+result);
            }
            return result;
        }

        //2.默认精准匹配
        for (Map accurateMap:accurateList) {
            String innerRegex = accurateMap.get("regex").toString();
            Pattern innerPtn = Pattern.compile(innerRegex,Pattern.CASE_INSENSITIVE);
            Matcher innerMtr = innerPtn.matcher(matchPart);

            logger.debug("3.精确匹配-->扫描段落："+matchPart + "\n[regex:" + innerRegex + "][groupCount:" + innerMtr.groupCount() + "]");
            while (innerMtr.find()) {
                int gCount = innerMtr.groupCount();
                if (innerMtr.groupCount() > 2) {
                    try {
                        result = innerMtr.group().replaceFirst(innerMtr.group(1), "").replaceFirst(innerMtr.group(gCount), "");//去掉首尾
                    }catch (Exception e){
                        result = innerMtr.group().replaceFirst(innerMtr.group(1), "");
                    }
                } else if (innerMtr.groupCount() > 1) {
                    result = innerMtr.group().replaceFirst(innerMtr.group(1), "");//去掉首
                } else {
                    result = innerMtr.group();
                }
//                            result = innerMtr.group();
                if (MyStringUtils.isNotNull(result)) {
                    logger.info( "3.1 精准规则匹配结果:[result:" + result + "] by " +
                            "[rangeRegex:\""+rangeRegex+"\"][innerRegex:\"" + innerRegex + "\"]" +
                            "[groupCount:" + innerMtr.groupCount() + "]");
                    logger.debug("##匹配段落：" + matchPart );
                    if(Constant.IS_DEBUG){
                        PROCESS_INFO.put("3.1","[解析字段："+fieldName+"]\n[范围匹配规则:"+rangeRegex+"]\n[精确规则:" + innerRegex + "]" +
                                "\n[groupCount:" + innerMtr.groupCount() + "]\n[解析结果:"+result+"]");
                    }
                    return result;
                }
            }
        }

        //3.精准匹配没有匹配出结果时，其他自定义逻辑匹配
        result =afterAccurateMatch(regListMap,matchPart,rangeRegex);
        if(MyStringUtils.isNotNull(result)){
            logger.debug("4.精准无匹配结果时，自定义匹配值:"+result);
            if(Constant.IS_DEBUG) {
                PROCESS_INFO.put("4", "精准无匹配结果时，自定义匹配值" + result);
            }
            return result;
        }else{//无匹配结果
            return result;
        }
    }

    /**
     * 维度解析主逻辑
     * return 维度解析结果
     */
    public String analyzeSingleField(String html,EsNotice esNotice) throws Exception{
        String analysisResult = null;

        //获取该字段关联的规则
        Map<String ,List<Map<String, Object>>> regListMap = commonService.queryRegexMapByField(fieldName);
        if(regListMap!=null && regListMap.keySet().size()>0){
            List<Map<String, Object>> rangeList = regListMap.get("range");//范围匹配规则
            List<Map<String, Object>> accurateList = regListMap.get("accurate");//精准匹配规则
            List<Map<String, Object>> filterResultRegList= regListMap.get("filterResult");//结果过滤规则
            logger.debug("##########启用规则数量################");
            if(rangeList!=null) {
                logger.debug("rangeList:" + rangeList.size());
            }
            logger.debug("accurateList:"+accurateList.size());
            logger.debug("filterResultRegList:"+filterResultRegList.size());
            logger.debug("#####################################");

            // 1.1范围匹配判断
            if(rangeList!=null && rangeList.size()>0) {
                outerMtr:
                for (Map rangeMap : rangeList) {
                    String rangeRegex = (String) rangeMap.get("regex") + rangeMap.get("ext_content");
                    Pattern outerPtn = Pattern.compile(rangeRegex, Pattern.CASE_INSENSITIVE);
                    Matcher outerMtr = outerPtn.matcher(html);
                    String matchPart = null;

                    while (outerMtr.find()) {
                        matchPart = outerMtr.group();
                        logger.debug("1.[title:"+esNotice.getTitle()+"]范围匹配-->扫描段落：" + matchPart);
                        //仅只匹配出表达式的开头部分则跳过
                        if (MyStringUtils.isNull(matchPart.replace(outerMtr.group(1), "").trim())) {
                            continue;
                        }

                        //1.内层匹配规则
                        analysisResult = innertMatchRules(esNotice,regListMap, matchPart, rangeRegex);
                        if (MyStringUtils.isNotNull(analysisResult)) {
                            //命中规则，停止执行其他规则
                            break outerMtr;
                        }

                    }
                    matchPart = null;
                }
            }else{
                //1.1 无范围匹配
                logger.debug("[title:"+esNotice.getTitle()+"]无范围匹配规则，直接进行（内层）规则匹配");
                analysisResult = innertMatchRules(esNotice,regListMap, html, null);
            }
            //2.解析结果过滤
            if(MyStringUtils.isNotNull(analysisResult)) {
                logger.info("3.1 解析结果过滤前：[title:"+esNotice.getTitle()+"][analysisResult:"+analysisResult+"]");
                analysisResult = filterAnalysisResult(analysisResult, filterResultRegList);
                logger.info("3.2 解析结果规则过滤后： [title:"+esNotice.getTitle()+"][analysisResult:"+analysisResult+"]");
                analysisResult = customfilterResult(analysisResult, esNotice);
                logger.info("3.3 解析结果自定义过滤后： [title:"+esNotice.getTitle()+"][analysisResult:"+analysisResult+"]");
                if(Constant.IS_DEBUG){
                    PROCESS_INFO.put("5","[title:"+esNotice.getTitle()+"]结果过滤完成后："+analysisResult);
                }
            }

            //3.解析结果有效性检验，检验失败返回空
            if(MyStringUtils.isNotNull(analysisResult)) {
                try {
                    analysisResult = verifyAnalysisResult(esNotice, regListMap, analysisResult);
                }catch(Exception e){
                    logger.error("[title:"+esNotice.getTitle()+"]解析结果有效性检验出错！"+e,e);
                    analysisResult=null;
                }
                if(Constant.IS_DEBUG){
                    PROCESS_INFO.put("6","[title:"+esNotice.getTitle()+"]解析结果有效性检验结果[analysisResult："+analysisResult+"]");
                }
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
        //结果段落分割
        str = splitSection(str,"</p>");
        str = str.replaceFirst("\\n","");
        str = str.trim();
        //输出结果过滤
        for(Map regexMap: filterResultRegList) {
            String regex =  (String)regexMap.get("regex");
            Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = ptn.matcher(str);
            String tmp = null;
            while (matcher.find()) {
                tmp=  matcher.group();
                if(tmp!=null && !tmp.equals(str)) {
                    tmp = MyStringUtils.convertESC(tmp);
                    str = str.replaceAll(tmp, "");
                }
            }
        }
        return str;
    }

    /**
     * 对字符进行分割，获取分割之后第一个有效的字符序列
     * @param orgin 源字符串
     * @param splitStr 分割符号
     * @return
     */
    private String splitSection(String orgin, String splitStr){
        String result = null;
        String[] splits  = orgin.split(splitStr);
        if(splits!=null && splits.length>1){
            for(String firstValid: splits){
                if(MyStringUtils.isNotNull(firstValid) && firstValid.length()>=1) {
                    result =firstValid;
                    break;
                }
            }
            if(result==null){
                result = orgin;
            }
        }else{
            result = orgin;
        }
        return result;
    }

    public static void main(String[] args){
//        String orgin = "<p>速度快放假</p><p>常德市公共资源交易中心五楼（常德市武陵区建设路808号泓鑫桃林商业广场）</p> <p>sdfsdf</p>";
//        System.out.println(SingleFieldAnalysisTemplate.splitSection(orgin,"</p>"));
    }


}
