package webdsl.topics;

import java.io.*;
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

    public WikiParser(Writer out, String doc)
    {
	this.out   = out;
	this.text  = new StringBuffer(doc);
	this.pos   = 0;
	this.len   = text.length();
	this.line  = 0;
    }

    public boolean nextChar()
    {
	if (pos < len)
	    {
		curchar = text.charAt(pos);
		pos++;
		return true;
	    }
	else
	    return false;
    }

    public void parse() throws IOException
    {
	out.write("this text rendered by WikiParser <p />\n");
	nextChar();
	if (startNewLine()) nextChar();
	do {
	    switch (curchar) {
	    case '\n' :
		startNewLine();
		break;

		//case '[' :
		//parseLink();
		//break;
		
	    default :
		out.write(curchar);
	    }
	} while(nextChar());
	out.write("<p /> full text rendered by WikiParser <p />\n");
    }

    public boolean startNewLine() throws IOException
    {
	line++;
	out.write(at_end_of_line);
	at_end_of_line = "";
	return newParagraph() 
	    || sectionHeading() 
	    || listItem()
	    || separator();
    }

    public void skipWhiteSpace() throws IOException
    {
	while(curchar == ' ' || curchar == '\t') { nextChar(); }
    }

    public int readChars(char c)
    {
	int count = 0;
	while(curchar == c)
	    {
		nextChar();
		count++;
	    }
	return count;
    }

    public boolean newParagraph() throws IOException
    {
	// ([\ \t]* [\n])+	
	if(skipBlankLine())
	    {
		while (skipBlankLine()) {}
		out.write(at_end_of_par + "\n<p />\n");
		at_end_of_par = "";
		list_level = 0;
		return startNewLine();
	    }
	else 
	    return false;
    }

    public boolean skipBlankLine() throws IOException
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
		pos = oldpos;
		return false;
	    }
    }

    public boolean sectionHeading() throws IOException
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
		pos = oldpos;
		return false;
	    }
    }

    public boolean listItem() throws IOException
    {
	int oldpos = pos;
	nextChar();
	int spaces = readChars(' ');
	int level = spaces / 3;
	if ((level * 3 == spaces) && (curchar == '*'))
	    {
		for (int i = level - list_level; i > 0; i--)
		    {
			out.write("<ul>\n");
			at_end_of_par = at_end_of_par + "</ul>";
		    }
		list_level = level;
		out.write("<li>");
		return true;
	    }
	else
	    {
		pos = oldpos;
		return false;
	    }
    }

    public boolean verbatim()
    {
	return false;
    }

    public boolean separator()
    {
	// [-]3+ 
	
	int oldpos = pos;
	nextChar();
	int count = readChars('-');
	if (count >= 3)
	    {
		pos--;
		out.write(count + "<hr />");
		return true;
	    }
	else
	    {
		out.write("no sep");
		pos = oldpos;
		return false;
	    }
    }

    public void parseLink()
    {
    }

}
