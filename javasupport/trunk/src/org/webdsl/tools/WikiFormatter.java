package org.webdsl.tools;

import com.petebevin.markdown.*;
import java.util.regex.*;

public final class WikiFormatter {
	public static String wikiFormat(String text) {
		Pattern links = Pattern.compile("\\[\\[(\\w+)\\(([^\\)]+)\\)\\]\\]");
		Matcher m = links.matcher(text);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			System.out.println("Found a link!");
			m.appendReplacement(sb, "["+m.group(2)+"](" + m.group(1) + ".seam?" + m.group(1) + "=" + m.group(2) + ")");
		}
		m.appendTail(sb);
		MarkdownProcessor processor = new MarkdownProcessor();
		return processor.markdown(sb.toString());
	}

	public static void main(String[] args) {
		System.out.println("The output:");
		System.out
				.println(wikiFormat("This is a header\n=========\n\nAnd here's a link [[page(MainPage)]].\nHello people!"));
	}
}