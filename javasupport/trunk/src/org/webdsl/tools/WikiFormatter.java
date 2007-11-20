package org.webdsl.tools;

import com.petebevin.markdown.*;
import java.util.regex.*;

public final class WikiFormatter {
	private static final Pattern links = Pattern.compile("\\[\\[(\\w+)(\\(([^\\)]*)\\))?(\\|([^\\]]+))?\\]\\]");

	public static String wikiFormat(String text, String rootUrl) {
		Matcher m = links.matcher(text);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String url, title;
			if(m.group(2) == null || m.group(3).length() == 0) {
				url = rootUrl + "/" + m.group(1);
				title = m.group(1);
			} else {
				url = rootUrl + "/" + m.group(1) + "/" + m.group(3);
				title = m.group(3);
			}
			if(m.group(5) != null) {
				title = m.group(5);
			}
			m.appendReplacement(sb, "[" + title + "](" + url + ")");
		}
		m.appendTail(sb);
		MarkdownProcessor processor = new MarkdownProcessor();
		return processor.markdown(sb.toString());
	}

	public static void main(String[] args) {
		System.out.println("The output:");
		System.out
				.println(wikiFormat("This is a header\n=========\n\nAnd here's a link [[page(MainPage)|Main page]].\nHello people! [[home()]] -- [[home|Terug naar de homepage]]", "/test"));
	}
}