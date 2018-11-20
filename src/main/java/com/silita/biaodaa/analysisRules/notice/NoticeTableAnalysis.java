package com.silita.biaodaa.analysisRules.notice;

import com.silita.biaodaa.analysisRules.inter.TableAnalysis;
import com.silita.biaodaa.analysisRules.vo.*;
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
import static com.silita.biaodaa.utils.LoggerUtils.buildRow;
import static com.silita.biaodaa.utils.RegexUtils.matchExists;

/**
 * 表格解析逻辑类
 * Created by dh on 2018/10/30.
 */
@Component
public class NoticeTableAnalysis implements TableAnalysis{
    private Logger logger = Logger.getLogger(this.getClass());
    /** 数据抽取来源：行式*/
    public static final int EXTRACT_STYLE_ROW=1;

    /** 数据抽取来源：列式*/
    public static final int EXTRACT_STYLE_COL=2;

    /** 表格模式—横向表格*/
    public static final int TB_STYLE_ROW=1;

    /** 表格模式—纵向表格*/
    public static final int TB_STYLE_COL=2;

    /** 表格模式—混合嵌套型表格*/
    public static final int TB_STYLE_MIX=3;


    public static final String ZHONGBIAO_TB="zhongbiao";

    public static final String ZHAOBIAO_TB="zhaobiao";

    public static final String FD_ONE_NAME="第一中标候选人";

    public static final String FD_ONE_OFFER="第一中标报价";

    public static final String FD_TIIME_LIMIT="工期";

    public static final String FD_TIMES="时间/日期";

    public static final String FD_PJ_NAME="项目名称";

    public static final String FD_PJ_NO="项目编号";

    public static final String FD_SEGMENT="标段";

    public static final String FD_ORDER="排序";

    /**
     * 中标，有效表格判定关键字
     */
    public static final String[] VALID_TABLE_KEYS = {"名称","单位","工程"
            ,"项目","标段","报价","第一","名次"
            ,"标段","投标","排名","得分","工期"
            ,"中标候选人"};

    @Autowired
    private TableAnalysisService tableAnalysisService;

    @Override
    public Map<String, String> analysis(EsNotice esNotice, String segment,String flag) throws Exception {
        LoggerUtils.debugTrace("表格解析开始",esNotice,logger);
        segment = HtmlTagUtils.clearTagByTable(segment);
        logger.debug("segment:"+segment);
        Map<String, String> resMap = null;
        try {
            //解析html，获取有效表格
            List<List<AnalysisTd>> tableContent = parseContent(segment,flag);
            if(tableContent==null){
                logger.info("tableSize is null...");
                return resMap;
            }else {

                logger.info("tableSize:" + tableContent.size());
                //把实体转换为标准的二维表
                String[][] tbArray = mappingArray(tableContent);
                //打印映射的二维表
                String tableString = LoggerUtils.infoArray(tbArray);

                //行、列式表格数据抽取
                logger.info("行式表格数据抽取开始。。。");
                //按有效值进行结对判断
                List<AnalysisField> rowStyleList =extractRowData(tbArray);
                //行式表格结果展示
                String rowListString = debugShow(rowStyleList);

                logger.info("列式表格数据抽取开始。。。");
                List<AnalysisField> colStyleList =extracColumnData(tbArray);
                //列式表格结果展示
                String colListString = debugShow(colStyleList);

                //行，列匹配数据合并
                List<AnalysisField> afLists= new ArrayList<AnalysisField>(rowStyleList);
                afLists.addAll(colStyleList);

                boolean hasResult = false;
                if (afLists.size() > 0) {
                    hasResult = true;
                    resMap = new HashMap<String, String>();
                    //// TODO: 2018/11/12 多个value时需要联合多个维度判断挑选
                    for (AnalysisField af : afLists) {
                        resMap.put(af.getTitle(), af.getValues()[0]);
                    }
                }

                //仅存在table解析的，进行记录表格解析日志
                insertAnalysisLog(esNotice,hasResult,tableString,
                        rowListString,rowStyleList.size(),
                        colListString,colStyleList.size(),
                        (resMap!=null? resMap.toString():null));
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
            log.setOrigin_content(esNotice.getContent());
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
            logger.info("初始化二维表["+rowCount+"]["+colCount+"]");
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
                            tbArray[tmpX][newY]=v;
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
            String rgx= "(?<=(中标结果|谈判情况))[\\s\\S.]*<table.*?>[\\s\\S.]*?</table.*?>";
            String subSeg = RegexUtils.matchValue(segment,rgx);
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
                    rowStyleList.add(af);
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
    private  List<AnalysisField> extracColumnData(String[][] tbArray){
        List<AnalysisField> colStyleList = new ArrayList<>();

        List<AnalysisField> colStyleListTmp = new ArrayList<>();
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
                colStyleList.add(af);
            }
        }

        return colStyleList;
    }


    @Override
    public List<AnalysisField> recognitionStyleData(String[][] tbArray){
        List<AnalysisField> resList = null;
        //行式表格数据集合
        List<AnalysisField> rowStyleList = null;
        //列式表格数据集合
        List<AnalysisField> colStyleList = null;
        try {
            //按有效值进行结对判断
            logger.info("#######tbArray.length:" + tbArray.length + "##tbArray[x].length:" + tbArray[0].length);
            logger.debug("行式结对数据判断开始。。。");
            rowStyleList =extractRowData(tbArray);
            //行式判断结果展示
            logger.debug("行式匹配结果：" + rowStyleList.size());
            infoShow(rowStyleList);

            logger.info("列式结对数据判断开始。。。");
            colStyleList =extracColumnData(tbArray);
            //列式结果展示
            logger.info("列式匹配结果：" + colStyleList.size());
            infoShow(colStyleList);

        }catch (Exception e ){
            logger.error(e,e);
        }finally {
            resList = new ArrayList<AnalysisField>(rowStyleList);
            resList.addAll(colStyleList);
        }
        return resList;
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
            if (matchExists(label, lr)
                    && matchExists(value, vr)) {
                logger.debug("["+pr.getDesc()+"][values:"+value+"]行式成对匹配成功。lr:"+lr+"--vr:"+vr);
                afield = new AnalysisField(pr.getDesc(),new String[]{value},EXTRACT_STYLE_ROW);
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
            if (matchExists(label, lr)) {
                List<String> mList = RegexUtils.matchExists(af.getValues(), vr);
//                logger.debug("列式：matchExists成对判断["+af.getValues()+"]["+vr+"]");
                if(mList.size() > 0){
                    isVertical =true;
                    //更新values队列
                    af.setTitle(pr.getDesc());
                    af.setValues(mList.toArray(new String[mList.size()]));
                    logger.debug("["+pr.getDesc()+"][values:"+Arrays.deepToString(mList.toArray(new String[mList.size()]))+"]列式成对匹配成功。lr:"+lr+"--vr:"+vr);
                    break;
                }
            }
        }

        return isVertical;
    }


    @Override
    public AnalysisTable dataToObj(List<List> tDate) {
        return null;
    }

    @Override
    public String pickField(String[] keys) {
        return null;
    }
}
