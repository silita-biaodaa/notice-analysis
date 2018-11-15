package com.silita.biaodaa.analysisRules.factory;

import com.silita.biaodaa.analysisRules.vo.PairRule;

import java.util.ArrayList;
import java.util.List;

import static com.silita.biaodaa.analysisRules.notice.NoticeTableAnalysis.*;

/**
 * Created by dh on 2018/11/13.
 */
public class PairRuleFactory {
    public static List<PairRule> pairRuleList =null;

    public static Object lock;

    public static List<PairRule>  getPairRuleList(int style){
        if(pairRuleList==null) {
            synchronized (lock) {
                if(pairRuleList==null) {
                    //横向：成对匹配规则
                    List<PairRule> pairRules = new ArrayList<PairRule>();
                    pairRules.add(new PairRule("(编号)", "\\d{5,}", FD_PJ_NO));
                    pairRules.add(new PairRule("(工程名称|项目名称)", ".{2,}(工程|项目)", FD_PJ_NAME));
                    pairRules.add(new PairRule("(日期|时间)", "^[1-9]\\d{3}(年|-)(0[1-9]|1[0-2])(月|-)([1-9]|0[1-9]|[1-2][0-9]|3[0-1])(日)?\\s*((2[0-3]|[0-1]\\d|[0-9])(:|时|点)([0-5]\\d|[0-9])(:|分)?([0-5]\\d|[0-5])?)?$"
                            , FD_TIMES));
                    pairRules.add(new PairRule("(工期)", "^\\d{1,}(天|日|月)?$", FD_TIIME_LIMIT));
                    pairRules.add(new PairRule("(中标单位|中标人名称|中标候选人|投标人|第一名|第一推荐中标候选人)", "[\\s\\S\\W\\w.]*((部|中心|合作社|队|所|局|站|院|厂|处|苗圃|城|部|店|公司|事务所)[\\s\\S\\W\\w.]*$)"
                            , FD_ONE_NAME));
                    pairRules.add(new PairRule("(标段)", "(\\d\\d?|[一二三四五六七八九十]|十一|十二|[壹贰叄肆伍])(标段)", FD_SEGMENT));
                    pairRules.add(new PairRule("(排序|排名)", "(第)?(\\d\\d?|[一二三四五六七八九十]|十一|十二|[壹贰叄肆伍])(名)?", FD_ORDER));
                    pairRules.add(new PairRule("(中标价|投标报价|投标总价)", "\\d{1,}\\.\\d{1,}(元|圆|万元|万|w)?", FD_ONE_OFFER));
                    pairRuleList = pairRules;
                }
            }
        }
        return pairRuleList;
    }
}
