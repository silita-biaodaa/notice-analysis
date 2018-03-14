package com.silita.biaodaa.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import java.util.List;

/**
 * 将湖南招标信息网的信息格式化为标准文档
 * @author Administrator
 *
 */
	public class ChineseCompressUtil {

	/**
	 * Format an Element to plain-text
	 *
	 * @param str
	 *            the root element to format
	 * @return formatted text
	 */
	public String getPlainText(String str) {
		Document element = Jsoup.parse(str);
		FormattingVisitor formatter = new FormattingVisitor();
		NodeTraversor traversor = new NodeTraversor(formatter);
		traversor.traverse(element); // walk the DOM, and call .head() and
		element.select("span,a,u,b,font,img,br").unwrap();
		String text = element.select("body").html().replaceAll("[　]*", "");
		text = text.replaceAll("[ ]*", "");
		text = text.replaceAll("\\n\\s*", "");
		text = text.replaceAll("&nbsp;", "");
		text = text.replaceAll("<[a-zA-Z0-9/]+:[\\w\\s='\"-\\:;@\\,~]+>", "");
		text = text.replaceAll("<!---->","");
		text = text.replaceAll("<p></p>", "");
		text = text.replace("<table>", "<table class='table table-bordered'>");
		return text;
	}

	// the formatting rules, implemented in a breadth-first DOM traverse
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
