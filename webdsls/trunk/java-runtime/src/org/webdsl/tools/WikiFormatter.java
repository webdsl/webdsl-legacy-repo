package org.webdsl.tools;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.safety.Whitelist;
import org.pegdown.LinkRenderer;
import org.pegdown.PegDownProcessor;

import utils.AbstractPageServlet;


public final class WikiFormatter {
	
    private static final Pattern verbatim = Pattern.compile("<verbatim>(.+?)</verbatim>", Pattern.DOTALL | Pattern.MULTILINE);
    private static String currentRootUrl = "";
    private static LinkRenderer currentLinkRenderer = null;
    private static final Whitelist whitelist = org.jsoup.safety.Whitelist.relaxed();
    public static final int PARSE_TIMEOUT_MS = 10000;
    
	static{
		whitelist.addTags("abbr").addAttributes("abbr", "title");
		//allow id's on title tags
		for(int i=1;i<7;i++){
			whitelist.addAttributes("h"+i, "id");
		}
	}

    public static String wikiFormat(String text) {
    	AbstractPageServlet threadLocalPage = utils.ThreadLocalPage.get();
    	return org.jsoup.Jsoup.clean( wikiFormat( text, threadLocalPage.getPegDownProcessor(), threadLocalPage.getAbsoluteLocation()+utils.ThreadLocalServlet.getContextPath() ), whitelist );
    }
    
    //Similar to wikiFormat( text ) , but without cleaning by JSoup
    public static String wikiFormatNoTagFiltering(String text) {
    	AbstractPageServlet threadLocalPage = utils.ThreadLocalPage.get();
    	return wikiFormat( text, threadLocalPage.getPegDownProcessor(), threadLocalPage.getAbsoluteLocation()+utils.ThreadLocalServlet.getContextPath() );
    }
    
    public static String wikiFormat(String text, PegDownProcessor processor, String rootUrl){
    	String html = processor.markdownToHtml( processVerbatim(text), getLinkRenderer( rootUrl ) );
    	return html == null ? errorMessage(text) : html;
    }    
    
    private static String errorMessage( String text ){
    	StringBuilder sb = new StringBuilder(text.length() + 128);
    	sb.append("Something went wrong processing the following markdown text (parsing automatically stopped after ")
    	  .append(PARSE_TIMEOUT_MS)
    	  .append("ms): ")
    	  .append("<pre>")
    	  .append(text)
    	  .append("</pre>");
    	return sb.toString();
    }

    private static String processVerbatim(String text) {
        Matcher m = verbatim.matcher(text);
        if (m.find()){
        	StringBuffer sb = new StringBuffer( text.length() + 256 );
        	do {
        		String newText = "\n        " +
                        m.group(1).replaceAll("\n", "\n        ")
                                  .replaceAll("\\\\", "\\\\\\\\")
                                  .replaceAll("\\$", "\\\\\\$") +
                        "\n";
                m.appendReplacement(sb, newText);
        		
        	} while (m.find());
        	m.appendTail(sb);
        	return sb.toString();
        } else {
        	return text;
        }
    }
    
    private static synchronized LinkRenderer getLinkRenderer( String rootUrl ){
    	if(!currentRootUrl.equals(rootUrl) && rootUrl != null ) {
			currentRootUrl = rootUrl;
			currentLinkRenderer = new WebDSLLinkRenderer(rootUrl);
		}
    	return currentLinkRenderer;
    }

//    public static void main(String[] args) {
//        System.out.println("The output:");
//        System.out
//                .println(wikiFormat("This is a header\n=========\n\nAnd here's a link [[page(MainPage)|Main page]].\nHello people! [[home()]] -- [[home|Terug naar de homepage]]\n\nHere is some verbatim:\n<verbatim>Dit is een test <hoi>. Enzovoorts\nBlabla\n\n  Free $$$ for all!</verbatim>Doei.", "/test"));
//    }
    

}