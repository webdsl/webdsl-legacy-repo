package webdsl.topics;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import webdsl.users.*;
import webdsl.html.*;
import webdsl.topics.*;

public class WikiParser
{
    private Writer out;
    private int state;
    private StringBuffer text; 
    private int pos;
    private int len;
    private int line;
    private char curchar;
    private String at_end_of_line = "";
    private String at_end_of_par = "";
    private int list_level = 0;
    private boolean eof = false;

    public WikiParser(Writer out, String doc)
    {
	this.out   = out;
	this.text  = new StringBuffer(doc);
	this.pos   = 0;
	this.len   = text.length();
	this.line  = 0;
    }

    private boolean nextChar()
    {
	if (pos < len)
	    {
		curchar = text.charAt(pos);
		pos++;
		return true;
	    }
	else
	    {
		eof = true;
		return false;
	    }
    }

    private void resetCurrentChar(int pos)
    {
	if (pos > 1)
	    {
		this.pos = pos;
		curchar = text.charAt(pos - 1);
	    }
	else
	    {
		this.pos = 0;
		nextChar();
	    }
    }

    public void parse() throws IOException
    {
	out.write("this text rendered by WikiParser <p />\n");
	nextChar();
	if (startNewLine()) 
	    nextChar();
	do {
	    switch (curchar) {
	    case '\n' :
		startNewLine();
		break;

	    case '[' :
		if (!parseLink())
		    out.write(curchar);
		break;

	    case '/' :
		if(!matchPathExp())
		    out.write(curchar);
		break;

	    case 'A' :
	    case 'B' :
	    case 'C' :
	    case 'D' :
	    case 'E' :
	    case 'F' :
	    case 'G' :
	    case 'H' :
	    case 'I' :
	    case 'J' :
	    case 'K' :
	    case 'L' :
	    case 'M' :
	    case 'N' :
	    case 'O' :
	    case 'P' :
	    case 'Q' :
	    case 'R' :
	    case 'S' :
	    case 'T' :
	    case 'U' :
	    case 'V' :
	    case 'W' :
	    case 'X' :
	    case 'Y' :
	    case 'Z' :
		if(!matchWikiWord())
		    out.write(curchar);
		break;

	    default :
		out.write(curchar);
	    }
	} while(nextChar());
	out.write("<p /> full text rendered by WikiParser <p />\n");
    }

    private boolean startNewLine() throws IOException
    {
	line++;
	out.write(at_end_of_line);
	at_end_of_line = "";
	return newParagraph() 
	    || sectionHeading() 
	    || listItem()
	    || separator()
	    || verbatim();
    }

    private void skipWhiteSpace() throws IOException
    {
	while(curchar == ' ' || curchar == '\t') { nextChar(); }
    }

    private int readChars(char c)
    {
	int count = 0;
	while(curchar == c)
	    {
		nextChar();
		count++;
	    }
	return count;
    }

    private boolean newParagraph() throws IOException
    {
	// ([\ \t]* [\n])+	
	if(skipBlankLine())
	    {
		while (skipBlankLine()) {}
		
		for (int i = list_level; i > 0; i--)
		    {
			out.write("</ul>\n");
		    }
		list_level = 0;

		out.write(at_end_of_par + "\n<p />\n");
		at_end_of_par = "";

		return startNewLine();
	    }
	else 
	    return false;
    }

    private boolean skipBlankLine() throws IOException
    {
	// [\ \t]* [\n]
	int oldpos = pos;
	nextChar();
	skipWhiteSpace();
	if (curchar == '\n' || curchar == '\r')
	    {
		return true;
	    }
	else
	    {
		resetCurrentChar(oldpos);
		return false;
	    }
    }

    private void copyLineVerbatim() throws IOException
    {
	// ~[\n]* [\n]
	nextChar();
	while (curchar != '\n' || curchar == '\r')
	    {
		switch (curchar) {
		case '<' : 
		    out.write("&lt;");
		    break;
		case '>' :
		    out.write("&gt;");
		    break;
		case '&' :
		    out.write("&amp;");
		    break;
		default : 
		    out.write(curchar);
		    break;
		}
		nextChar();
	    }
    }

    private boolean sectionHeading() throws IOException
    {
	// \n ([-]3+ [\+]+ [\n])+
	
	int oldpos = pos;
	int level;
	nextChar();
	if (readChars('-') >= 3 && (level = readChars('+')) > 0)
	    {
		skipWhiteSpace();
		pos--;
		out.write("<h" + level + ">");
		at_end_of_line = at_end_of_line + "</h" + level + ">";
		return true;
	    }
	else
	    {
		resetCurrentChar(oldpos);
		return false;
	    }
    }

    private boolean listItem() throws IOException
    {
	int oldpos = pos;
	nextChar();
	int spaces = readChars(' ');
	int level = spaces / 3;
	if ((level * 3 == spaces) && (curchar == '*'))
	    {
		if (level > list_level)
		    for (int i = level - list_level; i > 0; i--)
			{
			    out.write("<ul>\n");
			}
		else
		    for (int i = list_level - level; i > 0; i--)
			{
			    out.write("</ul>\n");
			}
		list_level = level;
		out.write("<li>");
		return true;
	    }
	else
	    {
		resetCurrentChar(oldpos);
		return false;
	    }
    }

    private boolean separator() throws IOException
    {
	// [-]3+ 
	
	int oldpos = pos;
	nextChar();
	int count = readChars('-');
	if (count >= 3)
	    {
		pos--;
		out.write("<hr />");
		return true;
	    }
	else
	    {
		resetCurrentChar(oldpos);
		return false;
	    }
    }

    private boolean parseLink() throws IOException
    {
	int oldpos = pos;
	String link = bracketed();
	if (link != null)
	    {
		String ref = bracketed();
		if (nextChar() && curchar == ']')
		    {
			if (ref == null)
			    ref = link;
			// todo: process wikiwords
			out.write("<a href=\"" + wikiWordToURL(link) + "\">" + ref + "</a>");
			return true;
		    }
	    }
	resetCurrentChar(oldpos);
	return false;
    }

    private boolean matchWikiWord() throws IOException
    {
	
	if (matchQualifiedWikiWord() || matchPathExp())
	    return true;

	String word = matchBasicWikiWord();
	if (word != null)
	    {
		out.write("<a href=\"" + word + "\">" + word + "</a>");	
		return true;
	    }
	return false;
    }

    private boolean matchQualifiedWikiWord() throws IOException
    {
	int oldpos = pos;

	StringBuffer pref = new StringBuffer("");

	if(isUpper(curchar))
	    {
		pref.append(curchar);
		while (nextChar() && (isUpper(curchar) || isLower(curchar)))
		    {
			pref.append(curchar);
		    }
		
		if (curchar == '.' && nextChar() && isUpper(curchar))
		    {
			String suff = matchBasicWikiWord();
			if (suff != null)
			    {
				out.write("<a href=\"/user1/view/" + pref + "/" + suff + "\">" + suff + "</a>" );
				return true;
			    }
		    }
	    }
	resetCurrentChar(oldpos);
	return false;
    }

    private String matchBasicWikiWord() throws IOException
    {
	int oldpos = pos;
	StringBuffer word = new StringBuffer("");
	if (isUpper(curchar))
	    {
		word.append(curchar);
		while (nextChar() && isLower(curchar))
		    word.append(curchar);
		if (isUpper(curchar))
		    {
			word.append(curchar);
			while (nextChar() && (isUpper(curchar) || isLower(curchar)))
			    {
				word.append(curchar);
			    }
			resetCurrentChar(pos - 1);
			return word.toString();
		    }
	    }
	resetCurrentChar(oldpos);
	return null;
    }
    
    private boolean matchPathExp() throws IOException
    {
	int oldpos = pos;
	boolean hasslash = false;
	StringBuffer path = new StringBuffer("");

	path.append(curchar);
	hasslash = hasslash || curchar == '/';
	while (nextChar() 
	       && (isUpper(curchar) || isLower(curchar) 
		   || isDigit(curchar) || curchar == '/'))
	    {
		hasslash = hasslash || curchar == '/';
		path.append(curchar);
	    }
	if (hasslash)
	    {
		if (path.charAt(0) == '/')
		    out.write("<a href=\"/user1/view" + path + "\">" + path + "</a>");
		else
		    out.write("<a href=\"" + path + "\">" + path + "</a>");
		return true;
	    }
	resetCurrentChar(oldpos);
	return false;
    }

    private boolean isUpper(char c)
    {
	return (int)c >= 65 && (int)c <= 90;
    }

    private boolean isLower(char c)
    {
	return (int)c >= 97 && (int)c <= 122;
    }

    private boolean isDigit(char c)
    {
	return (int)c >= 48 && (int)c <= 57;
    }

    private String wikiWordToURL(String s)
    {
	if (s.startsWith("http:") || s.startsWith("ftp:"))
	    {
		// an absolute URL
		return s;
	    }
	else if (s.startsWith("/"))
	    {
		// an 'absolute' wiki path
		return "/user1/view" + s;
	    }
	else if (s.matches("[A-Z][a-z]+[A-Z][a-z]+"))
	    {
		// wiki word relative to current 'directory'
		return s;
	    }
	else if (s.matches("[A-Z][A-Za-z]*\\.[A-Z][a-z]+[A-Z][a-z]+"))
	    {
		return "/user1/view/" + s.replaceFirst("\\.", "/");
	    }
	else 
	    { 
		// interpret as phrase to be wikiwordified
		// capitalize first letters of each word
		// e.g. [[wiki word]] -> [[WikiWord]]

		StringBuffer b = new StringBuffer(s);
		StringBuffer c = new StringBuffer("");
		
		c.append(b.substring(0,1).toUpperCase());
		for(int i = 1; i < b.length(); i++)
		    {
			if (b.charAt(i) == ' ')
			    {
				if(i + 1 < b.length())
				    {
					i++;
					c.append(b.substring(i,i+1).toUpperCase());
				    }
			    }
			else
			    {
				c.append(b.charAt(i));
			    }
		    }
		return c.toString();
	    }
    }

    private String bracketed()
    {
	int oldpos = pos;
	StringBuffer b = new StringBuffer("");

	if (nextChar() && curchar == '[')
	    {
		while(nextChar() && curchar != ']' 
		      && curchar != '\n' && curchar != '\r')
		    {
			b.append(curchar);
		    }
		if (curchar == ']')
		    return b.toString();
	    }
	resetCurrentChar(oldpos);
	return null;
    }

    private boolean verbatim() throws IOException
    {
	if (!matchWord("<verbatim>"))
	    {
		return false;
	    }
	
	out.write("<pre>\n");

	while (!matchWord("</verbatim>") && !eof)
	    copyLineVerbatim();

	out.write("</pre>\n");

	return true;
    }
    
    private boolean matchWord(String w) throws IOException
    {
	int wlen = w.length();
	int oldpos = pos;
	boolean match = true;
	for (int i = 0; i < wlen && nextChar(); i++)
	    {
		if (w.charAt(i) != curchar)
		    {
			match = false;
			break;
		    }
	    }
	if (!match)
	    resetCurrentChar(oldpos);
	return match;
    }

}
