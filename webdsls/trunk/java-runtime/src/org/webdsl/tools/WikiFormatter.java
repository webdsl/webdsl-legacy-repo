package org.webdsl.tools;


import java.util.regex.*;

import org.pegdown.LinkRenderer;
import org.pegdown.PegDownProcessor;

import utils.AbstractPageServlet;


public final class WikiFormatter {
    private static final Pattern verbatim = Pattern.compile("<verbatim>(.+?)</verbatim>", Pattern.DOTALL | Pattern.MULTILINE);
    private static String currentRootUrl = "";
    private static LinkRenderer currentLinkRenderer = null;

    public static String wikiFormat(String text) {
    	AbstractPageServlet threadLocalPage = utils.ThreadLocalPage.get();
    	return org.jsoup.Jsoup.clean( wikiFormat( text, threadLocalPage.getPegDownProcessor(), threadLocalPage.getAbsoluteLocation()+utils.ThreadLocalServlet.getContextPath() ), org.jsoup.safety.Whitelist.relaxed() );
    }
    
    //Similar to wikiFormat( text ) , but without cleaning by JSoup
    public static String wikiFormatNoTagFiltering(String text) {
    	AbstractPageServlet threadLocalPage = utils.ThreadLocalPage.get();
    	return wikiFormat( text, threadLocalPage.getPegDownProcessor(), threadLocalPage.getAbsoluteLocation()+utils.ThreadLocalServlet.getContextPath() );
    }
    
    public static String wikiFormat(String text, PegDownProcessor processor, String rootUrl){
    	return processor.markdownToHtml( processVerbatim(text), getLinkRenderer( rootUrl ) );
    }    

    private static String processVerbatim(String text) {
        Matcher m = verbatim.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String newText = m.group(1).replaceAll("\n", "\n        ");
            newText = newText.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$");
            m.appendReplacement(sb, "\n        "+newText+"\n");
        }
        m.appendTail(sb);
        return sb.toString();
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