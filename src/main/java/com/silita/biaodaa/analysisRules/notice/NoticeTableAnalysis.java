package com.silita.biaodaa.analysisRules.notice;

import com.silita.biaodaa.analysisRules.inter.TableAnalysis;
import com.silita.biaodaa.analysisRules.vo.AnalysisField;
import com.silita.biaodaa.analysisRules.vo.AnalysisTbLog;
import com.silita.biaodaa.analysisRules.vo.AnalysisTd;
import com.silita.biaodaa.analysisRules.vo.PairRule;
import com.silita.biaodaa.common.config.CustomizedPropertyConfigurer;
import com.silita.biaodaa.service.TableAnalysisService;
import com.silita.biaodaa.utils.HtmlTagUtils;
import com.silita.biaodaa.utils.LoggerUtils;
import com.silita.biaodaa.utils.MyStringUtils;
import com.silita.biaodaa.utils.RegexUtils;
import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.silita.biaodaa.analysisRules.factory.PairRuleFactory.getPairRuleList;
import static com.silita.biaodaa.analysisRules.notice.AnalysisConstant.*;
import static com.silita.biaodaa.utils.LoggerUtils.buildRow;
import static com.silita.biaodaa.utils.RegexUtils.matchValue;

/**
 * 表格解析逻辑类
 * Created by dh on 2018/10/30.
 */
@Component
public class NoticeTableAnalysis implements TableAnalysis{
    private Logger logger = Logger.getLogger(this.getClass());

    private static final String TABLE_LOG_FLAG =  (String)CustomizedPropertyConfigurer.getContextProperty("analysis.table.logs");


    @Autowired
    private TableAnalysisService tableAnalysisService;

    @Override
    public Map<String, String> analysis(EsNotice esNotice, String segment,String flag) throws Exception {
        LoggerUtils.debugTrace("表格解析开始",esNotice,logger);
        segment = HtmlTagUtils.clearTagByTable(segment);
        Map<String, String> resMap = null;
        try {
            //解析html，获取有效表格
            List<List<AnalysisTd>> tableContent = parseContent(segment,flag);
            if(tableContent==null){
                logger.debug("tableSize is null...");
                return null;
            }else {

                logger.debug("tableSize:" + tableContent.size());
                //把实体转换为标准的二维表
                String[][] tbArray = mappingArray(tableContent);
                //打印映射的二维表
                String tableString = LoggerUtils.infoArray(tbArray);

                //行、列式表格数据抽取
                logger.debug("行式表格数据抽取开始。。。");
                //按有效值进行结对判断
                List<AnalysisField> rowStyleList =extractRowData(tbArray);
                //行式表格结果展示
                String rowListString = debugShow(rowStyleList);

                logger.debug("列式表格数据抽取开始。。。");
                List<AnalysisField> colStyleList = extractColumnData(tbArray);
                //列式表格结果展示
                String colListString = debugShow(colStyleList);

                if (rowStyleList.size() > 0 || colStyleList.size() > 0) {
                    resMap = new HashMap<String, String>();
                    //需要提取的字段
                    String[] fields = {FD_ONE_NAME,FD_ONE_OFFER,FD_TIIME_LIMIT,FD_TIMES,FD_PJ_NAME,FD_PJ_NO,FD_SEGMENT,FD_ORDER};
                    for(String desc: fields){
                        Map temp = filterFiedValues(rowStyleList,colStyleList,desc);
                        if(temp!=null)
                        resMap.putAll(temp);
                    }
                }

                if("true".equalsIgnoreCase(TABLE_LOG_FLAG)) {
                    tableString = MyStringUtils.controllLength(tableString,2000);
                    rowListString = MyStringUtils.controllLength(rowListString,1500);
                    colListString = MyStringUtils.controllLength(colListString,1500);
                    //table标签解析的公告，记录解析日志
                    insertAnalysisLog(esNotice,(resMap !=null && resMap.size()>0), tableString,
                            rowListString, rowStyleList.size(),
                            colListString, colStyleList.size(),
                            ((resMap != null && resMap.size()>0) ? resMap.toString() : null));

                    //清理对象
                    rowListString = null;
                    colListString = null;
                    rowStyleList=null;
                    colStyleList = null;
                    tableString = null;
                }
            }

        }catch(Exception e){
            logger.error("[title:"+esNotice.getTitle()+"][source:"+esNotice.getSource()+"][redisId:"+esNotice.getRedisId()+"]表格解析异常"+e,e);
        }finally {
            LoggerUtils.debugTrace("[title:"+esNotice.getTitle()+"][source:"+esNotice.getSource()+"][redisId:"+esNotice.getRedisId()+"]表格解析结束",esNotice,logger);
            if(resMap!=null) {
                logger.info("###resMap:"+resMap.toString());
            }
            segment=null;
        }
        return resMap;
    }


    /**
     * 从横，纵向表格解析结果中提取相应字段信息
     * @param rowStyleList
     * @param colStyleList
     * @param fieldDesc
     * @return
     */
    private Map<String,String> filterFiedValues(List<AnalysisField> rowStyleList,List<AnalysisField> colStyleList,String fieldDesc){
        Map<String,String> resMap = null;
        int rowCount=0,colCount=0;
        List<AnalysisField> targetListRow = new ArrayList<>();
        List<AnalysisField> targetListCol = new ArrayList<>();
        try {
            //收集、比对横，纵表格的命中数量
            for (AnalysisField af : rowStyleList) {
                if (af.getDesc().equals(fieldDesc)) {
                    af.setDelete(true);
                    rowCount++;
                    targetListRow.add(af);
                }
            }
            for (AnalysisField af : colStyleList) {
                if (af.getDesc().equals(fieldDesc)) {
                    af.setDelete(true);
                    colCount++;
                    targetListCol.add(af);
                }
            }

            if (colCount > rowCount) {
                resMap =buildFieldMap(targetListCol,fieldDesc);
            } else if(colCount == rowCount && colCount != 0 && rowCount != 0) {
                if(rowStyleList.size() > 0 && colStyleList.size() > 0) {
                    if(rowStyleList.get(0).getValues().length > colStyleList.get(0).getValues().length) {
                        resMap =buildFieldMap(targetListRow,fieldDesc);
                    } else {
                        resMap =buildFieldMap(targetListCol,fieldDesc);
                    }
                }
            } else {
                resMap =buildFieldMap(targetListRow,fieldDesc);
            }
        }catch (Exception e){
            logger.error(e,e);
        }finally {
            targetListRow =null;
            targetListCol=null;
        }
        return  resMap;
    }

    /**
     * 多值筛选：取序号第一
     * @param fieldList
     * @return
     */
    private AnalysisField filterOne(List<AnalysisField> fieldList){
        //判断title中的序号信息
        String oneRegex = "(第)?(一|1|壹)";
        for(AnalysisField af : fieldList){
            if(af.getTitleAttach() !=null
                    && RegexUtils.matchExists(af.getTitleAttach(), oneRegex)) {
                    return af;
            }
        }
        //无序号取队列第一个
        return fieldList.get(0);
    }

    /**
     * 返回某类解析字段的值
     * @param fieldList
     * @param fieldDesc
     * @return
     */
    private Map<String,String> buildFieldMap(List<AnalysisField> fieldList,String fieldDesc){
        Map<String,String> resMap = null;
        if(fieldList.size()>0){
            resMap = new HashMap<String, String>();
            AnalysisField hitAf = null;
            if(fieldList.size()>1){
                // 多值筛选
                switch(fieldDesc){
                    case FD_ONE_NAME: hitAf = filterOne(fieldList);break;
                    case FD_ONE_OFFER: hitAf = filterOne(fieldList);break;
                    default:hitAf =fieldList.get(0);
                }
            }else{
                //单匹配值，直接转化
                hitAf = fieldList.get(0);
            }

            //匹配的键值对进行结果收集：进行键值互联判断,修正value值
            switch(fieldDesc){
                case FD_ONE_OFFER: wrapOfferValue(hitAf);break;
                default:break;
            }

            resMap.put(hitAf.getDesc(),hitAf.getValues()[0]);
        }else{
            logger.warn("no match field");
        }
        return resMap;
    }

    private void wrapOfferValue(AnalysisField hitAf){
        String[] unitW = {"万元","（ 万元 ）","（万）","(万)","（ 万 ）"};
        String[] unitY = {"（元）","(元)","（ 元 ）"};
        for(String u: unitW) {
            if (hitAf.getTitle().indexOf(u)>0) {
                if(MyStringUtils.isNotNull(hitAf.getValues()[0])
                        && hitAf.getValues()[0].indexOf(u)==-1){
                        hitAf.getValues()[0]=insertUnit(hitAf.getValues()[0],"万");
                        return;
                }
            }
        }

        for(String u: unitY) {
            if (hitAf.getTitle().indexOf(u)>0) {
                if(MyStringUtils.isNotNull(hitAf.getValues()[0])
                        && hitAf.getValues()[0].indexOf(u)==-1){
                    hitAf.getValues()[0]=insertUnit(hitAf.getValues()[0],"元");
                    return;
                }
            }
        }
    }

    /**
     * 把金额后面插入对应单位字符
     * @param s
     * @param unitStr
     * @return
     */
    private String insertUnit(String s,String unitStr){
        String regex="\\d{1,50}[.]{0,100}\\d{1,50}";
        s=RegexUtils.insertMatchValuePos(s,regex,unitStr);
        return s;
    }

    private void insertAnalysisLog(EsNotice esNotice
            ,boolean hasResult,String tableString
            ,String rowListString
            ,int rowListSize
            ,String colListString
            ,int colListSize
            ,String resultMap){
        AnalysisTbLog log = new AnalysisTbLog();
        try {
            log.setRedisId(esNotice.getRedisId());
            log.setSource(esNotice.getSource());
            log.setCity(esNotice.getCity());
            log.setTitle(esNotice.getTitle());
            log.setHasResult(hasResult);
            log.setNoticeUrl(esNotice.getUrl());
            log.setGsDate(esNotice.getOpenDate());
            log.setTable_mapping(tableString);
            log.setRows_parser(rowListString);
            log.setRows_parser_size(rowListSize);
            log.setCols_parser(colListString);
            log.setCols_parser_size(colListSize);
            log.setResult_map(resultMap);
            tableAnalysisService.saveParseLog(log);
        }catch (Exception e){
            logger.error(e,e);
        }finally {
            log.setTable_mapping(null);
            log.setOrigin_content(null);
            log.setRows_parser(null);
            log.setCols_parser(null);
            log.setResult_map(null);
            log=null;
        }

    }



    /**
     * 把实体映射为标准的二维表
     * @param tableContent
     * @return
     */
    private String[][] mappingArray(List<List<AnalysisTd>> tableContent){
        String[][] tbArray = null;
        if(tableContent != null && !tableContent.isEmpty()) {
            //构造二维数组
            int rowCount = tableContent.size();
            int colCount = 0;
            for(int i=0;i<tableContent.size(); i++){
                List<AnalysisTd> li = tableContent.get(i);
                int tmpColCount = 0;
                for(AnalysisTd ad: li){
                    if(MyStringUtils.isNotNull(ad.getColspan())){
                        int cs = Integer.parseInt(ad.getColspan());
                        tmpColCount=tmpColCount+cs;
                    }else{
                        tmpColCount++;
                    }
                }
                if(tmpColCount>colCount){
                    colCount = tmpColCount;
                }
//                logger.debug("[colCount:"+colCount+"]第"+i+"行元素个数："+tmpColCount);
            }
            logger.debug("初始化二维表["+rowCount+"]["+colCount+"]");
            tbArray=new String[rowCount][colCount];

            //二维表填充值
            rowloop:for(int x=0,newX=0; x<rowCount; x++,newX++){
                List<AnalysisTd> rows=tableContent.get(x);//取一行数据
                colloop:for(int y=0,newY=0;y<rows.size(); y++,newY++){
                    AnalysisTd td =rows.get(y);
                    String colspan = td.getColspan();
                    String rowspan = td.getRowspan();
                    String v = (td.getValue()==null ? "":td.getValue());

                    //跳过已合并单元格的元素
                    while (tbArray[newX][newY] != null){
                        logger.debug("跳过已被合并的单元格["+newX+"]["+newY+"]");
                        newY++;
                        if(newY>=rows.size()){
                            break colloop;//换行
                        }
                    }

                    //赋值
//                    logger.debug("单元格赋值["+newX+"]["+newY+"]="+v);
                    tbArray[newX][newY]=v;

                    //行式，合并单元格赋值
                    if(MyStringUtils.isNotNull(colspan)){
                        for(int cs = Integer.parseInt(colspan)-1;cs>0;cs--){
                            newY++;
//                            logger.debug("colspan["+colspan+"]合并单元格赋值["+newX+"]["+newY+"]="+v);
                            tbArray[newX][newY]=v;
                        }
                    }

                    //列式，合并单元格
                    if(MyStringUtils.isNotNull(rowspan)){
                        int tmpX = newX;
                        for(int rsp = Integer.parseInt(rowspan)-1;rsp>0;rsp--){
                            tmpX++;
//                            logger.debug("rowspan["+rowspan+"]合并单元格赋值["+tmpX+"]["+newY+"]="+v);
                            if(tmpX < rowCount) {
                                tbArray[tmpX][newY] = v;
                            }else{
                                break;
                            }
                        }
                    }
                }
            }
        }
        return tbArray;
    }

    /**
     * 检验table的有效性
     * @param tc
     * @return
     */
    private boolean verifyTable(String tc){
        boolean isValid=false;
        for(int i=0;i<VALID_TABLE_KEYS.length;i++){
            if(-1 !=tc.indexOf(VALID_TABLE_KEYS[i])){
                isValid=true;break;
            }
        }
        return isValid;
    }


    @Override
    public List<List<AnalysisTd>> parseContent(String segment,String flag) throws Exception {
        List<List<AnalysisTd>> tableList =null;
        //中标表格,范围预选尝试
        if(flag.equals(ZHONGBIAO_TB)){
            String subSeg = matchValue(segment,TB_RANGE_BID);
            if(subSeg !=null){
                tableList = buildTableListByHtml(subSeg);
                subSeg=null;
            }
        }

        //按全文顺序获取表格
        if(tableList==null) {
            tableList = buildTableListByHtml(segment);
        }
        return tableList;
    }

    /**
     * 按html中的顺序，取第一个有效的表格
     * @param html
     * @return
     */
    private List<List<AnalysisTd>> buildTableListByHtml(String html){
        List<List<AnalysisTd>> tableList =null;
        Document doc = Jsoup.parse(html);
        // 根据tagid获取table
        Elements tables = doc.getElementsByTag("table");

        //获取第一个有效表格
        for(Element tb: tables ){
            if(tb.previousElementSibling() == null) {
                continue;
            }
            tableList =parseTableTag(tb);
            if(tableList !=null) {
                break;
            }
        }
        return tableList;
    }

    private List<List<AnalysisTd>> parseTableTag(Element tb){
        List<List<AnalysisTd>> tableList =null;
        logger.debug("##############################");
        // 使用选择器选择该table内所有的<tr> <tr/>
        Elements trs = tb.select("tr");
        Elements tmptds = trs.select("td");
        String tmpTxt = tmptds.text();
//            logger.debug("tmptds.outerHtml():"+tmptds.outerHtml());
//            logger.debug("tmptds.text():" + tmpTxt);
        if(verifyTable(tmpTxt)) {
            if (trs.size() > 1 && !tmptds.isEmpty()) {
                tableList = new ArrayList<List<AnalysisTd>>(trs.size());
                for (Element tr : trs) {
                    Elements tds = tr.select("td");
                    ArrayList tdList = new ArrayList(tds.size());
                    for (Element td : tds) {
                        String colspan = td.attr("colspan");
                        String rowspan = td.attr("rowspan");
                        String tdValue = td.text();
                        if (tdValue != null) {
                            tdValue = tdValue.trim();
                        }
                        AnalysisTd atd = new AnalysisTd(tdValue, colspan, rowspan);
                        tdList.add(atd);
                    }
                    tableList.add(tdList);
                }
            }
        }else{
            //无效表格
            logger.debug("跳过无效表格，td.outerHtml："+tmptds.outerHtml());
        }
        return tableList;
    }

    /**
     * 表格横向数据抽取
     * @param tbArray
     * @return
     */
    private  List<AnalysisField> extractRowData(String[][] tbArray){
        List<AnalysisField> rowStyleList = new ArrayList<>();
        ArrayList<String> arr = new ArrayList<String>();
        List hashList = new ArrayList();
        for (int x = 0; x < tbArray.length; x++) {
            arr.clear();
            for (int y = 0; y < tbArray[x].length; y++) {
//                logger.debug("array["+x+"]["+y+"]:"+tbArray[x][y]);
                if(tbArray[x][y] != null) {
                    if (y > 0 && tbArray[x][y].equals(tbArray[x][y - 1])) {
                        continue;
                    } else {
                        arr.add(tbArray[x][y]);
                    }
                }else{
                    continue;
                }
            }

            //行式，结对数据判断
            for (int i = 0; i < arr.size() - 1; i++, i++) {
                AnalysisField af = rowStyle(arr.get(i), arr.get(i + 1));
                if (af != null) {
                    int hash = af.hashCode();
                    if(hashList.isEmpty()){
                        hashList.add(hash);
                        rowStyleList.add(af);
                    }else{
                        if(!hashList.contains(hash)){//排除【key、values】对相同的值
                            hashList.add(hash);
                            rowStyleList.add(af);
                        }
                    }
                }
            }

            if (rowStyleList.size() > 0) {
                logger.debug("第" + x + "行内容：" + buildRow(tbArray[x]) + ",判断为：行式表格数据");
            }
        }
        return rowStyleList;
    }


    /**
     * 表格纵向数据抽取
     * @param tbArray
     * @return
     */
    private  List<AnalysisField> extractColumnData(String[][] tbArray){
        List<AnalysisField> colStyleList = new ArrayList<>();
        List<AnalysisField> colStyleListTmp = new ArrayList<>();
        List hashList = new ArrayList();

        for (int y = 0; y < tbArray[0].length; y++) {
            AnalysisField af = new AnalysisField(EXTRACT_STYLE_COL);
            //根据列式表格获取数据
            for (int x = 0; x < tbArray.length; x++) {
                if (MyStringUtils.isNotNull(tbArray[x][y])) {
                    //第一个有效字符为列头
                    if (MyStringUtils.isNull(af.getTitle())) {
                        logger.debug("[x:" + x + "][y:" + y + "][" + tbArray[x][y] + "]");
                        af.setTitle(tbArray[x][y]);
                    } else {
                        af.addValue(tbArray[x][y]);
                    }
                }
            }
            colStyleListTmp.add(af);
        }
        //列式队列展示
        debugShow(colStyleListTmp);

        for (AnalysisField af : colStyleListTmp) {
            //验证列式数据
            boolean isValidate = false;
            if(af.getValues() !=null) {
                while (!isValidate) {
                    //成对规则校验
                    isValidate = verifyColStyle(af);
                    if (!isValidate) {
                        if (af.getValues().length >= 2) {
                            String[] vs = af.getValues();
                            af.setTitle(vs[0]);
                            af.setValues(Arrays.copyOfRange(vs, 1, vs.length));
                        } else {
                            break;
                        }
                    }
                }
            }
            if (isValidate) {
                int hash = af.hashCode();
                if(hashList.isEmpty()){
                    hashList.add(hash);
                    colStyleList.add(af);
                }else{
                    if(!hashList.contains(hash)){
                        hashList.add(hash);
                        colStyleList.add(af);
                    }
                }

            }
        }

        return colStyleList;
    }

    private String debugShow(List<AnalysisField> rowStyleList){
        StringBuilder sb = null;
        if(rowStyleList.size() > 0){
            sb = new StringBuilder();
            for(AnalysisField af:rowStyleList){
                sb.append("[t:"+af.getTitle()+"][v:"+ Arrays.deepToString(af.getValues())+"]\n");
            }
            logger.debug(sb.toString());
        }
        return (sb !=null ? sb.toString(): null);
    }

    private void infoShow(List<AnalysisField> rowStyleList){
        if(rowStyleList.size() > 0){
            for(AnalysisField af:rowStyleList){
                logger.info("title:"+af.getTitle()+"==value:"+ Arrays.deepToString(af.getValues()));
            }
        }
    }

    /**
     * 水平型表格模型判断
     * @param label
     * @param value
     * @return
     */
    private AnalysisField rowStyle(String label,String value){
        AnalysisField afield = null;
        boolean isHoriz=false;
        //成对匹配规则
        List<PairRule> pairRules= getPairRuleList();
        for(PairRule pr : pairRules) {
            String lr = pr.getkRegex();
            String vr = pr.getvRegex();
            String labelKey = matchValue(label, lr);
            if ( labelKey != null && matchValue(value, vr) != null) {
                logger.debug("["+pr.getDesc()+"][values:"+value+"]行式成对匹配成功。lr:"+lr+"--vr:"+vr);
                afield = new AnalysisField(label,new String[]{value},pr.getDesc(),EXTRACT_STYLE_ROW);
                afield.setTitleKey(labelKey);
                afield.setTitleAttach(label.replace(labelKey,""));
                isHoriz = true;
                break;
            }
        }
        logger.debug("rowStyle finished:"+isHoriz);
        return afield;
    }

    /**
     * 列式表格成对规则校验
     * @param af
     * @return
     */
    private boolean verifyColStyle(AnalysisField af){
        boolean isVertical = false;
        //成对匹配规则
        List<PairRule> pairRules= getPairRuleList();

        String label=af.getTitle();
        for(PairRule pr : pairRules) {
            String lr = pr.getkRegex();
            String vr = pr.getvRegex();
//            logger.debug("列式：成对判断["+label+"]["+lr+"]");
            String labelKey = matchValue(label, lr);
            if (labelKey != null) {
                List<String> mList = RegexUtils.matchExists(af.getValues(), vr);
//                logger.debug("列式：matchExists成对判断["+af.getValues()+"]["+vr+"]");
                if(mList.size() > 0){
                    isVertical =true;
                    //更新values队列
                    af.setDesc(pr.getDesc());
                    af.setValues(mList.toArray(new String[mList.size()]));
                    af.setTitleKey(labelKey);
                    af.setTitleAttach(label.replace(labelKey,""));
                    logger.debug("["+pr.getDesc()+"][values:"+Arrays.deepToString(mList.toArray(new String[mList.size()]))+"]列式成对匹配成功。lr:"+lr+"--vr:"+vr);
                    break;
                }
            }
        }

        return isVertical;
    }

}
