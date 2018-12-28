package com.silita.biaodaa.analysisRules.notice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Create by IntelliJ Idea 2018.1
 * Company: silita
 * Author: gemingyi
 * Date: 2018-12-27 9:44
 */
public class TableParse {

    public Element parseTable(Element tb) {
        String html = tb.toString();
        html = html.replaceAll("style=\\\"(.*?)\\\"", "");
        Map<String, Object> map = this.getRowAndOtherRow(html);
        String table = this.returnFirstCandidate(map);
        Document tableDoc = Jsoup.parse(table);
        return tableDoc.select("table").first();
    }

    private String returnFirstCandidate(Map<String, Object> map) {
        if (null == map) {
            return null;
        } else {
            //行
            Map<String, ArrayList> allRows = (Map) map.get("allRows");
            ArrayList firstRow = allRows.get(String.valueOf(0));
            //其他行
            ArrayList otherFirstRow = null;
            Map<String, ArrayList> otherRows = (Map) map.get("otherRows");
            //列
            Map<String, ArrayList> allCols = new LinkedHashMap<>(15);
            ArrayList firstCol = new ArrayList();
            ArrayList tempList = allRows.get(String.valueOf(0));
            for (int j = 0; j < tempList.size(); j++) {
                ArrayList tempCols = new ArrayList();
                for (int i = 0; i < allRows.size(); i++) {
                    tempCols.add(allRows.get(String.valueOf(i)).get(j));
                }
                allCols.put(String.valueOf(j), tempCols);
            }
            for (int i = 0; i < allRows.size(); i++) {
                firstCol.add(allRows.get(String.valueOf(i)).get(0));
            }
            if (otherRows.size() > 0) {
                otherFirstRow = otherRows.get(String.valueOf(0));
            }
            Set<String> firstColSet = new HashSet(firstCol.size());
            firstColSet.addAll(firstCol);

            Map otherPosition = matchTableHead(otherRows);
            if (firstRow.size() == 1 || allRows.size() < 3) {
                System.out.println("简单横向表格，可不走表格解析");
                return this.mapToString(allRows);
//                return null;
                //判断特殊表格规则//http://www.gxzbtb.cn/gxzbw/ZBGG_Detail.aspx?InfoID=bce26261-b4eb-42e6-9a50-ca9b87621002&CategoryNum=001001005
            } else if (firstColSet.size() <= firstCol.size() / 2) {
                int position = 0;
                System.out.println("特殊表格，特殊处理");
                //截取中标候选人
                String maxCountValue = this.keyWordCountSort(firstCol);
                int firstPosition = firstCol.indexOf(maxCountValue);
                int lastPosition = firstCol.lastIndexOf(maxCountValue);
                Map<String, ArrayList> subRow = new LinkedHashMap<>(lastPosition - firstPosition + 1);
                for (int i = firstPosition; i < lastPosition; i++) {
                    subRow.put(String.valueOf(position++), allRows.get(String.valueOf(i)));
                }
                int startCol = 0;
                ArrayList firstSubRow = subRow.get(String.valueOf(0));
                for (int i = 0; i < firstSubRow.size(); i++) {
                    Set<String> set = new HashSet<>(subRow.size());
                    for (int j = 0; j < subRow.size(); j++) {
                        set.add((String) subRow.get(String.valueOf(j)).get(i));
                    }
                    startCol++;
                    if (set.size() > 2) {
                        break;
                    }
                }
                int startRow = 0;
                Map<String, ArrayList> colsToRows = new LinkedHashMap(subRow.size());
                for (int i = startCol; i < firstSubRow.size(); i++) {
                    ArrayList tempRow = new ArrayList(subRow.size());
                    for (int j = 0; j < subRow.size() / 3; j++) {
                        tempRow.add(subRow.get(String.valueOf(j)).get(i));
                    }
                    colsToRows.put(String.valueOf(startRow++), tempRow);
                }
                map.put("allRows", colsToRows);
                map.put("otherRows", colsToRows);
                return returnFirstCandidate(map);
                //判断部分截取表格规则//http://www.biaodaa.com/neirong/1900470
            } else if (null != otherFirstRow && (otherFirstRow.size() > firstRow.size()) && otherPosition != null) {
                int flag = 0;
                Map<String, ArrayList> subOtherRows = new LinkedHashMap<>(15);
                int removePosition = (int) otherPosition.get("firstRowPosition");
                for (int i = removePosition; i < otherRows.size(); i++) {
                    subOtherRows.put(String.valueOf(flag++), otherRows.get(String.valueOf(i)));
                }
                System.out.println("特殊表格，部分截取");
                map.put("allRows", subOtherRows);
                map.put("otherRows", subOtherRows);
                return returnFirstCandidate(map);
            } else {
                Map result = this.matchTableHead(allRows);
                if (result != null) {
                    System.out.println("横向表格");
                    return mapToString(allRows);
                } else {
                    System.out.println("竖向表格");
                    result = this.matchTableHead(allCols);
                    if (result != null) {
//                        Map<String, ArrayList> tempRows = new LinkedHashMap<>(allCols.size());
//                        ArrayList tempRowList = allCols.get(String.valueOf(0));
//                        for (int j = 0; j < allCols.size(); j++) {
//                            ArrayList tempRow = new ArrayList();
//                            for (int i = 0; i < tempRowList.size(); i++) {
//                                tempRow.add(allCols.get(String.valueOf(j)).get(i));
//                            }
//                            tempRows.put(String.valueOf(j), tempRow);
//                        }
//                        return mapToString(tempRows);
                        return mapToString(allRows);
                    } else {
                        System.out.println("表格判断失败");
                        return null;
                    }
                }
            }
        }
    }

    /**
     *
     * @param temp
     * @return
     */
    private Map<String, Object> getRowAndOtherRow(String temp) {
        int firstColSize = 0;
        int position = 0;
        int otherPosition = 0;

        Map result = new HashMap<>(2);
        Map<String, ArrayList> allRow = new LinkedHashMap<>(30);
        Map<String, ArrayList> otherRow = new LinkedHashMap<>(15);

        Document tables = Jsoup.parse(temp);
        Element table = tables.select("table").first();
        for (int i = 0; i < table.select("tr").size(); i++) {
            ArrayList tempRows = new ArrayList();
            ArrayList OtherRows = new ArrayList();

            Element tempRow = table.select("tr").get(i);
            //广东特殊表格
            tempRow.select("table").remove();
            if (i == 0) {
                //第一行的列数
                firstColSize = tempRow.select("td").size();
                if (firstColSize == 0) {
                    firstColSize = tempRow.select("th").size();
                }
            }
            int colSize = tempRow.select("td").size();
            if (colSize < firstColSize) {
                continue;
            }
            //比第一列大小,大
            if ((colSize - firstColSize) > 0) {
                for (int j = 0; j < colSize; j++) {
                    Element tempCol = tempRow.select("td").get(j);
                    if (!StringUtils.isEmpty(tempCol.attr("rowspan"))) {
                        int rowSpan = Integer.parseInt(tempCol.attr("rowspan"));
                        for (int k = 1; k < rowSpan; k++) {
                            Element cloneCol = tempCol.clone();
                            cloneCol.removeAttr("rowspan");
                            table.select("tr").get(i + k).select("td").get(j).before(cloneCol);
                        }
                    }
                    if ((colSize - j) > firstColSize) {
                        OtherRows.add(tempCol.text());
                        continue;
                    }
//                    System.out.println(tempCol.text() + "|");
                    tempRows.add(tempCol.text().trim());
                    OtherRows.add(tempCol.text().trim());
                }
                otherRow.put(String.valueOf(otherPosition++), OtherRows);
            } else {
                for (int j = 0; j < colSize; j++) {
                    Element tempCol = tempRow.select("td").get(j);
                    if (!StringUtils.isEmpty(tempCol.attr("rowspan"))) {
                        int rowSpan = Integer.parseInt(tempCol.attr("rowspan"));
                        for (int k = 1; k < rowSpan; k++) {
                            Element cloneCol = tempCol.clone();
                            cloneCol.removeAttr("rowspan");
                            table.select("tr").get(i + k).select("td").get(j).before(cloneCol);
                        }
                    }
//                    System.out.println(tempCol.text() + "|");
                    tempRows.add(tempCol.text().trim());
                }
            }
//            System.out.println("-----------------------");
            allRow.put(String.valueOf(position++), tempRows);
        }
        result.put("allRows", allRow);
        result.put("otherRows", otherRow);
        return result;
    }

    /**
     *
     * @param list
     * @return
     */
    private String keyWordCountSort(ArrayList list) {
        //统计集合重复元素出现次数，并且去重返回hashmap
        Map<String, Long> map = (Map<String, Long>) list.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //由于hashmap无序，所以在排序放入LinkedHashMap里(key升序)
        Map<String, Long> sortMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
        //获取排序后map的key集合
        List<String> keys = new LinkedList<>();
        sortMap.entrySet().stream().forEachOrdered(e -> keys.add(e.getKey()));
        //获取排序后map的value集合
        List<Long> values = new LinkedList<>();
        sortMap.entrySet().stream().forEachOrdered(e -> values.add(e.getValue()));
        return ((LinkedList<String>) keys).getFirst();
    }

    /**
     * Map(行)转html
     * @param rows
     * @return
     */
    private String mapToString(Map<String, ArrayList> rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        for (int i = 0; i < rows.size(); i++) {
            sb.append("<tr>");
            ArrayList tempRow = rows.get(String.valueOf(i));
            for (int j = 0; j < tempRow.size(); j++) {
                sb.append("<td>");
                sb.append(tempRow.get(j));
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * 匹配表头
     * @param RowsOrCols
     * @return
     */
    private Map matchTableHead(Map<String, ArrayList> RowsOrCols) {
        Map result = new HashMap(2);
        String[] testValues = {
                "中标候选人", "结果", "中标人", "投标人", "供应商",
                "编号", "序号", "排序", "排名", "名次",
                "中标价", "投标价", "价格", "报价", "金额",
                "负责人", "项目经理", "姓名",
                "项目名称", "业绩", "工期"
        };
        for (int i = 0; i < RowsOrCols.size(); i++) {
            ArrayList tempList = RowsOrCols.get(String.valueOf(i));
            Set set = new HashSet(tempList.size());
            for (int j = 0; j < tempList.size(); j++) {
                String tempStr = (String) tempList.get(j);
                for (int k = 0; k < testValues.length; k++) {
                    if (tempStr.contains(testValues[k])) {
                        set.add(testValues[k]);
                    }
                }
            }
            if(tempList.size() <= 4 && set.size() >= 2) {
                result.put("firstRowPosition", i + 1);
                return result;
            } else if(tempList.size() > 4 && tempList.size() <= 10 && set.size() >= 3) {
                result.put("firstRowPosition", i + 1);
                return result;
            } else if(set.size() >= 4){
                result.put("firstRowPosition", i + 1);
                return result;
            }
        }
        return null;
    }
}
