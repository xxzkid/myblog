package org.xxz.myblog.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 清除html标签
 * @author xxzkid
 */
public class HtmlUtil {
	private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String REGEX_HTML = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String REGEX_SPACE = "\\s*|\t|\r|\n";// 定义空格回车换行符

	private static Pattern P_SCRIPT = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
	private static Pattern P_STYLE = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
	private static Pattern P_HTML = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
	private static Pattern P_SPACE = Pattern.compile(REGEX_SPACE, Pattern.CASE_INSENSITIVE);

	/**
	 * 删除Html标签
	 * @param html
	 * @return 
	 */
	public static String delHtmlTag(String html) {
		Matcher m_script = P_SCRIPT.matcher(html);
		html = m_script.replaceAll(""); // 过滤script标签

		Matcher m_style = P_STYLE.matcher(html);
		html = m_style.replaceAll(""); // 过滤style标签

		Matcher m_html = P_HTML.matcher(html);
		html = m_html.replaceAll(""); // 过滤html标签

		Matcher m_space = P_SPACE.matcher(html);
		html = m_space.replaceAll(""); // 过滤空格回车标签
		return html.trim(); // 返回文本字符串
	}

	public static String getTextFromHtml(String html) {
		html = delHtmlTag(html);
		html = html.replaceAll("&nbsp;", " ");
		return html;
	}

}
