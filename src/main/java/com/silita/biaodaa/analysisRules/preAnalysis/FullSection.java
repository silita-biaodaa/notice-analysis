package com.silita.biaodaa.analysisRules.preAnalysis;

import com.silita.biaodaa.analysisRules.inter.PreAnalysisRule;
import com.silita.biaodaa.utils.HtmlTagUtils;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * Created by dh on 2018/4/2.
 */
@Component
public class FullSection implements PreAnalysisRule {
    @Override
    public String[] buildAnalysisList(EsNotice esNotice, String split1, String split2) throws Exception{
        if(MyStringUtils.isNull(esNotice.getContent())){
            throw new Exception("公告内容为空。[esNotice.getContent():"+esNotice.getContent()+"]");
        }
        String[] lists ;
        Document doc = Jsoup.parse(esNotice.getContent());
        Elements trp = doc.select("table").select("tr").select("p");
        if(null != trp && trp.size() > 5) {
            //只要表格
            String content = HtmlTagUtils.clearInvalidTag(esNotice.getContent());
            content = content.replaceAll("(</p>|<p>)", "");
            lists = content.split("(</tr>)");
        } else {
            String content = HtmlTagUtils.clearInvalidTag(esNotice.getContent());
            lists = content.split("(</p>|</tr>)");
        }
//        String[] lists = {HtmlTagUtils.clearInvalidTag(esNotice.getContent())};
        return lists;
    }

}
