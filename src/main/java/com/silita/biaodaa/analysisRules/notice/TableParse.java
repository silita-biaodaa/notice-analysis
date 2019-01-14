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

    private static final String[] firstRow = {
            //"投标单位" "投标企业"
            "中标候选人", "中标侯选人", "中标人", "投标人", "供应商", "中标供应商", "竞标单位", "中标单位", "候选人名称", "单位名称", "候选人", "投标企业", "投标单位", "公司名称",
            "编号", "序号", "排序", "排名", "名次", "包号", "候选名单", "目号",
            "中标价", "投标价", "价格", "报价", "金额", "评标价", "总价",
            "负责人", "项目经理", "姓名", "授权代表",
            "信誉", "评分", "总分", "得分",
            "项目名称", "业绩", "工期", "评审结果", "标段", "获奖", "结果"
    };

    public Element parseTable(Element tb) {
        String html = tb.toString();
        html = html.replaceAll("style=\\\"(.*?)\\\"", "");
        Map<String, Object> map = this.getRowAndOtherRow(html);
        String table = this.returnFirstCandidate(map);
        if (StringUtils.isEmpty(table)) {
            return null;
        } else {
            Document tableDoc = Jsoup.parse(table);
            return tableDoc.select("table").first();
        }
    }

    private String returnFirstCandidate(Map<String, Object> map) {
        if (null == map) {
            return null;
        } else {
            //全部行
            Map<String, ArrayList> allRows = (Map) map.get("allRows");
            //第一行
            ArrayList firstRow = allRows.get(String.valueOf(0));
            //最后一行
            ArrayList lastRow = allRows.get(String.valueOf(allRows.size() - 1));
            //第一列
            ArrayList firstCol = new ArrayList(allRows.size());
            for (int i = 0; i < allRows.size(); i++) {
                firstCol.add(allRows.get(String.valueOf(i)).get(0));
            }
            firstCol.remove("");
            //全部列
            Map<String, ArrayList> allCols = new LinkedHashMap<>(firstRow.size());
            for (int j = 0; j < allRows.get(String.valueOf(0)).size(); j++) {
                ArrayList tempCols = new ArrayList();
                for (int i = 0; i < allRows.size(); i++) {
                    tempCols.add(allRows.get(String.valueOf(i)).get(j));
                }
                allCols.put(String.valueOf(j), tempCols);
            }

            //其他全部行
            Map<String, ArrayList> otherRows = (Map) map.get("otherRows");
            if (allRows.size() == 0) {
                return null;
            }
            //其他第一行
            ArrayList otherFirstRow = null;
            //其他最后行
            ArrayList otherLastRow = null;
            if (otherRows.size() > 0) {
                otherFirstRow = otherRows.get(String.valueOf(0));
                otherLastRow = otherRows.get(String.valueOf(otherRows.size() - 1));
            }
            //其他第一列
            ArrayList otherFirstCol = new ArrayList(otherRows.size());
            if (otherRows.size() > 0) {
                for (int i = 0; i < otherRows.size(); i++) {
                    otherFirstCol.add(otherRows.get(String.valueOf(i)).get(0));
                }
            }
            //其他全部列
            Map<String, ArrayList> otherCols = new LinkedHashMap<>(firstRow.size());
            //用于判断特殊表格
            Set<String> firstColSet = new HashSet(firstCol.size());
            firstColSet.addAll(firstCol);
            //用于判断是否是需要截取的表格
            Map otherPosition = matchTableHead(otherRows);
            Map rowsPosition = matchTableHead(allRows);
            if((null != rowsPosition && ((int)rowsPosition.get("firstRowPosition") > allRows.size() / 2)) || (firstRow.size() == 2 && lastRow.size() == 2)) {
                rowsPosition = null;
            }
            if (null == otherPosition && null == rowsPosition) {
                if (otherRows.size() > 0 && (otherFirstRow.size() == otherLastRow.size())) {
                    for (int j = 0; j < otherRows.get(String.valueOf(0)).size(); j++) {
                        ArrayList tempCols = new ArrayList();
                        for (int i = 0; i < otherRows.size(); i++) {
                            tempCols.add(otherRows.get(String.valueOf(i)).get(j));
                        }
                        otherCols.put(String.valueOf(j), tempCols);
                    }
                    Map otherColPosition = this.matchTableHead(otherCols);
                    //http://www.ahtba.org.cn/Notice/NoticeDetail?id=549514 这种表格
                    if (otherColPosition != null && otherFirstCol.size() > 1) {
                        return mapToString(otherCols);
                    }
                }
            }

            //简单表格判断规则
            if (firstRow.size() == 1 && lastRow.size() == 1) {
                if (null != otherFirstRow) {
                    if (otherFirstRow.size() == 1) {
                        System.out.println("简单表格");
//                        return this.mapToString(allRows);
                        return null;
                    } else {
                        if (otherPosition != null) {
                            int flag = 0;
                            Map<String, ArrayList> subOtherRows = new LinkedHashMap<>(15);
                            int removePosition = (int) otherPosition.get("firstRowPosition") - 1;
                            ArrayList tempFirstRow = otherRows.get(String.valueOf(removePosition));
                            for (int i = removePosition; i < otherRows.size(); i++) {
                                ArrayList tempRow = otherRows.get(String.valueOf(i));
                                //td数量等于第一行的才添加
                                if (tempRow.size() == tempFirstRow.size()) {
                                    subOtherRows.put(String.valueOf(flag++), tempRow);
                                }
                            }
                            System.out.println("特殊表格，部分截取");
                            map.put("allRows", subOtherRows);
                            map.put("otherRows", new LinkedHashMap<String, ArrayList>(1));
                            return returnFirstCandidate(map);
                        } else {
                            System.out.println("表格判断失败");
                            return null;
                        }
                    }
                }
                System.out.println("简单表格");
                return null;
                //http://www.jszb.com.cn/jszb/YW_info/ZhongBiaoGS/ViewGSDetail.aspx?RowID=954613&categoryNum=012&siteid=1    不走表格解析
            } else if (firstRow.size() == 2 && lastRow.size() == 2 && allRows.size() > 6 && otherPosition == null) {
                System.out.println("简单表格,不走表格解析!");
                return null;
            }
            //http://www.gxzbtb.cn/gxzbw/ZBGG_Detail.aspx?InfoID=bce26261-b4eb-42e6-9a50-ca9b87621002&CategoryNum=001001005    特殊表格规则
            else if (firstColSet.size() < firstCol.size() / 2 && (allRows.size() > 20 && allRows.size() < 100)) {
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
                map.put("otherRows", new LinkedHashMap<String, ArrayList>(1));
                return returnFirstCandidate(map);
                //http://www.biaodaa.com/neirong/1900470    部分截取表格规则
            } else if (null != otherFirstRow && (otherFirstRow.size() > firstRow.size()) && otherPosition != null && rowsPosition == null) {
                int flag = 0;
                Map<String, ArrayList> subOtherRows = new LinkedHashMap<>(15);
                int removePosition = (int) otherPosition.get("firstRowPosition") - 1;
                ArrayList tempFirstRow = otherRows.get(String.valueOf(removePosition));
                for (int i = removePosition; i < otherRows.size(); i++) {
                    ArrayList tempRow = otherRows.get(String.valueOf(i));
                    if (tempRow.size() == tempFirstRow.size()) {
                        subOtherRows.put(String.valueOf(flag++), tempRow);
                    }
                }
                System.out.println("特殊表格，部分截取");
                map.put("allRows", subOtherRows);
                map.put("otherRows", new LinkedHashMap<String, ArrayList>(1));
                return returnFirstCandidate(map);
            } else {
                //普通横向表格表头判断
                if (rowsPosition != null && allRows.size() > 1) {
                    //http://ggzy.yueyang.gov.cn/004/004002/004002003/20180816/2c5d7a18-bd5b-4b75-928f-240b1457c5b7.html    处理这种误判
                    if (firstColSet.size() <= allRows.size() && firstColSet.contains("推荐意见")) {
                        System.out.println("无效横向表格, 跳过");
                        return null;
                    } else {
                        System.out.println("横向表格");
                        return mapToString(allRows);
                    }
                } else {
                    System.out.println("竖向表格");
                    //http://www.bidding.hunan.gov.cn/jyxxzbhx/39131.jhtml 普通竖向表格表头判断
                    if (this.matchTableHead(allCols) != null && firstCol.size() > 1) {
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

        Element table = Jsoup.parse(temp).select("table").first();
        //删除空白、
        for (int i = 0; i < table.select("tr").size(); i++) {
            Element tempRow = table.select("tr").get(i);
            String tdColsStr = tempRow.select("td").text().replaceAll("(\\s*)|(&nbsp;)", "");
            if(StringUtils.isEmpty(tdColsStr) && i == 0) {
                tdColsStr = tempRow.select("th").text().replaceAll("(\\s*)|(&nbsp;)", "");
            }
            if (StringUtils.isEmpty(tdColsStr) ) {
                table.select("tr").get(i).remove();
            }
        }
        //处理rowspan\th
        for (int i = 0; i < table.select("tr").size(); i++) {
            Element tempRow = table.select("tr").get(i);
            if(i == 0) {
                if(tempRow.select("td").size() == 0 && tempRow.select("th").size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("<tr>");
                    for (int j = 0; j < tempRow.select("th").size(); j++) {
                        sb.append("<td>").append(tempRow.select("th").get(j).text()).append("</td>");
                    }
                    table.select("tr").get(0).remove();
                    table.select("tr").get(0).before(sb.toString() + "</tr>");
                }
            }
            int colSize = tempRow.select("td").size();
            for (int j = 0; j < colSize; j++) {
                Element tempCol = tempRow.select("td").get(j);
                if (!StringUtils.isEmpty(tempCol.attr("rowspan"))) {
                    if (tempCol.text().length() > 100) {
                        continue;
                    } else {
                        int rowSpan = Integer.parseInt(tempCol.attr("rowspan"));
                        //存在下一行
                        if (null != tempRow.nextElementSibling()) {
                            for (int k = 1; k < rowSpan; k++) {
                                Element cloneCol = tempCol.clone();
                                cloneCol.removeAttr("rowspan");
                                if(j == 0) {
                                    table.select("tr").get(i + k).select("td").get(0).before(cloneCol);
                                } else {
                                    table.select("tr").get(i + k).select("td").get(j - 1).before(cloneCol);
                                }
                            }
                        }
                    }
                }
            }
        }
        //删除无效tr
        for (int i = 0; i < table.select("tr").size(); i++) {
            Element tempRow = table.select("tr").get(i);
            if (i < table.select("tr").size() - 2) {
                Element nextRow = null;
                if (i <= table.select("tr").size() - 1) {
                    nextRow = table.select("tr").get(i + 1);
                }
                if (null != nextRow && (nextRow.select("td").size() > 1 && tempRow.select("td").size() == 1)) {
                    table.select("tr").get(i).remove();
                    if (i != 0) {
                        i--;
                    }
                }
            }
        }
        for (int i = 0; i < table.select("tr").size(); i++) {
            ArrayList tempRows = new ArrayList(8);
            ArrayList OtherRows = new ArrayList(8);

            Element tempRow = table.select("tr").get(i);
            //广东特殊表格
            tempRow.select("table").remove();
            if (i == 0) {
                //第一行的列数
                firstColSize = tempRow.select("td").size();
            }
            int colSize = tempRow.select("td").size();
            if (colSize < firstColSize) {
                continue;
            }
            //比第一行td数多的行，单独存储一份
            if ((colSize - firstColSize) > 0) {
                for (int j = 0; j < colSize; j++) {
                    Element tempCol = tempRow.select("td").get(j);
                    if ((colSize - j) > firstColSize) {
                        OtherRows.add(tempCol.text().replaceAll(" +", ""));
                        continue;
                    }
                    tempRows.add(tempCol.text().replaceAll(" +", ""));
                    OtherRows.add(tempCol.text().replaceAll(" +", ""));
                }
                otherRow.put(String.valueOf(otherPosition++), OtherRows);
            } else {
                for (int j = 0; j < colSize; j++) {
                    Element tempCol = tempRow.select("td").get(j);
                    tempRows.add(tempCol.text().replaceAll(" +", ""));
                }
            }
            allRow.put(String.valueOf(position++), tempRows);
        }
        result.put("allRows", allRow);
        result.put("otherRows", otherRow);
        return result;
    }

    /**
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
     *
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
     * 匹配表头位置
     *
     * @param RowsOrCols
     * @return
     */
    private Map matchTableHead(Map<String, ArrayList> RowsOrCols) {
        Map result = new HashMap(2);
        for (int i = 0; i < RowsOrCols.size(); i++) {
            ArrayList tempList = RowsOrCols.get(String.valueOf(i));
            Set set = new HashSet(tempList.size());
            for (int j = 0; j < tempList.size(); j++) {
                String tempStr = (String) tempList.get(j);
                for (int k = 0; k < firstRow.length; k++) {
                    if (tempStr.contains(firstRow[k]) && !(tempStr.indexOf("第") == 0)) {
                        set.add(firstRow[k]);
                    }
                }
            }
            if (tempList.size() <= 2 && set.size() >= 1 && rowIsContainOneName(set)) {
                result.put("firstRowPosition", i + 1);
                return result;
            } else if ((tempList.size() >= 3 && tempList.size() <= 4) && set.size() >= 2 && rowIsContainOneName(set)) {
                result.put("firstRowPosition", i + 1);
                return result;
            } else if ((tempList.size() > 4 && tempList.size() <= 10) && set.size() >= 3 && rowIsContainOneName(set)) {
                result.put("firstRowPosition", i + 1);
                return result;
            } else if (set.size() >= 4 && rowIsContainOneName(set)) {
                result.put("firstRowPosition", i + 1);
                return result;
            }
        }
        return null;
    }

    private boolean rowIsContainOneName(Set set) {
        //表头必须包含中标候选人
        if (null == set || set.size() == 0) {
            return false;
        } else {
            boolean flag = false;
            for (int j = 0; j < 14; j++) {
                if (set.contains(firstRow[j])) {
                    flag = true;
                }
            }
            return flag;
        }
    }
}
