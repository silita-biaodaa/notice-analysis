package com.silita.biaodaa.analysisRules.preAnalysis;

import com.silita.biaodaa.analysisRules.inter.PreAnalysisRule;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.springframework.stereotype.Component;

import java.util.List;

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
        String plainHtml = getPlainText(esNotice.getContent());
        String[] main = {plainHtml};
        return main;
    }

    public String getPlainText(String str) {
        Document element = Jsoup.parse(str);
        FormattingVisitor formatter = new FormattingVisitor();
        NodeTraversor traversor = new NodeTraversor(formatter);
        traversor.traverse(element); // walk the DOM, and call .head() and
        element.select("span,a,u,b,font,img,br").unwrap();
        String text = element.select("body").html().replaceAll("[　]*", "");
//		text = text.replaceAll("[ ]*", "");
//        text = text.replaceAll("<[^>]+>", "");
        text = text.replaceAll("\\n\\s*", "");
        text = text.replaceAll("&nbsp;", "");
        text = text.replaceAll("<[a-zA-Z0-9/]+:[\\w\\s='\"-\\:;@\\,~]+>", "");
        text = text.replaceAll("<!---->","");
//        text = text.replaceAll("<.?[^>]?p>", "");
        text = text.replace("<table>", "<table class='table table-bordered'>");
        return text;
    }

    // the formatting rules, implemented in a breadth-first DOM traverse
    //// TODO: 2018/4/2 先轉移代碼，此處後續優化
    private class FormattingVisitor implements NodeVisitor {
        // hit when the node is first seen
        public void head(Node node, int depth) {
        }
        // hit when all of the node's children (if any) have been visited
        public void tail(Node node, int depth) {
            if (node instanceof TextNode) {
            } else {
                List<Attribute> attrs = node.attributes().asList();
                for (int k = attrs.size()-1;k>=0; k--) {
                    String attributeKey = attrs.get(k).getKey();
                    node.removeAttr(attributeKey);
                }
                //System.out.println("------------"+node.toString());
            }
        }
    }
}
