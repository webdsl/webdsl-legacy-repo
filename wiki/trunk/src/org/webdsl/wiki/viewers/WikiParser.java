 
// TODO make sure that character content is flushed before starting a new element

package org.webdsl.wiki.viewers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class WikiParser
{
  // the parser sends ouput to an XML ContentHandler
  private ContentHandler handler;

  // characters for output are copied to a char array
  // should be flushed by means of characters methods of
  // content handler

  private static int MAXOUT = 1024;

  private char[] outchars = new char[MAXOUT];

  private int outlow = 0;

  private int outhigh = 0;

  // characters that are part of character content
  private void copyChar(char c) throws SAXException
  {
    if (outhigh >= MAXOUT)
      {
        handler.characters(outchars, outlow, outhigh);
        outlow = 0;
        outhigh = 0;
      }
    outchars[outhigh] = c;
    outhigh = outhigh + 1;
  }

  private void copyString(String s) throws SAXException
  {
    for (int i = 0; i < s.length(); i++)
      {
        copyChar(s.charAt(i));
      }
  }

  // send latest batch of characters
  private void flushChars() throws SAXException
  {
    if (outhigh > outlow)
      {
        handler.characters(outchars, outlow, outhigh);
        outhigh = outhigh + 1;
        outlow = outhigh;
      }
  }

  private StringBuffer text; // the text to be parsed

  private int pos; // current character position in the text

  private int len;

  private int line;

  private char curchar; // the current character

  private List<String> at_end_of_line = new LinkedList();

  private List<String> at_end_of_par = new LinkedList();

  private int list_level = 0;

  private boolean eof = false;

  private boolean autolink = true;

  public WikiParser(String doc, ContentHandler handler) throws IOException
  {
    this.text = new StringBuffer("\n");
    this.text.append(doc);
    this.pos = 0;
    this.len = text.length();
    this.line = 0;
    this.handler = handler;
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

  public void parse() throws SAXException
  {
    // out.write("this text rendered by WikiParser <p />\n");
    nextChar();
    // if(startNewLine())
    // nextChar();
    do
      {
        switch (curchar)
          {
          case '\n':
            startNewLine();
            break;

          case '[':
            if (!parseLink())
              copyChar(curchar);
            break;

          case '<':
            if (!(noautolink() || htmlTag()))
              copyChar(curchar);
            break;

          case '/':
            if (!(autolink && matchPathExp()))
              copyChar(curchar);
            break;

          case '%':
            if (!(/* matchInclude() */
            /* || matchTopicText() */
            matchVariable()))
              copyChar(curchar);
            break;

          case 'A':
          case 'B':
          case 'C':
          case 'D':
          case 'E':
          case 'F':
          case 'G':
          case 'H':
          case 'I':
          case 'J':
          case 'K':
          case 'L':
          case 'M':
          case 'N':
          case 'O':
          case 'P':
          case 'Q':
          case 'R':
          case 'S':
          case 'T':
          case 'U':
          case 'V':
          case 'W':
          case 'X':
          case 'Y':
          case 'Z':
            if (!(autolink && matchWikiWord()))
              copyChar(curchar);
            break;

          default:
            copyChar(curchar);
          }
      }
    while (!eof && nextChar());
    closeParagraph();

    // out.write("<p /> full text rendered by WikiParser <p />\n");
  }

  private boolean startNewLine() throws SAXException
  {
    line++;
    flushChars();
    for (String tag:at_end_of_line)
      {
        handler.endElement("", tag, null);
      }
    at_end_of_line.clear();
    return newParagraph() || sectionHeading() || listItem() || separator()
           || verbatim();
  }

  private void skipWhiteSpace()
  {
    while (curchar == ' ' || curchar == '\t')
      {
        nextChar();
      }
  }

  private int readChars(char c)
  {
    int count = 0;
    while (curchar == c)
      {
        nextChar();
        count++;
      }
    return count;
  }

  private boolean newParagraph() throws SAXException
  {
    // ([\ \t]* [\n])+
    if (skipBlankLine())
      {
        while (skipBlankLine())
          {
          }
        closeParagraph();
        return startNewLine();
      }
    else
      return false;
  }

  private void closeParagraph() throws SAXException
  {
    flushChars();
    for (int i = list_level; i > 0; i--)
      {
        handler.endElement("", "ul", "");
      }
    list_level = 0;
    for (String tag:at_end_of_par)
      {
        handler.endElement("", tag, "");
      }
    handler.endElement("", "p", "");
    at_end_of_par.clear();
  }

  private boolean skipBlankLine()
  {
    // [\ \t]* [\n]
    int oldpos = pos;
    if (!nextChar())
      return false;
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

  private void copyLineVerbatim() throws SAXException
  {
    // ~[\n]* [\n]
    nextChar();
    while (curchar != '\n' || curchar == '\r')
      {
        switch (curchar)
          {
          case '<':
            copyString("&lt;");
            break;
          case '>':
            copyString("&gt;");
            break;
          case '&':
            copyString("&amp;");
            break;
          default:
            copyChar(curchar);
            break;
          }
        nextChar();
      }
  }

  private boolean sectionHeading() throws SAXException
  {
    // \n ([-]3+ [\+]+ [\n])+

    int oldpos = pos;
    int level;
    nextChar();
    if (readChars('-') >= 3 && (level = readChars('+')) > 0)
      {
        skipWhiteSpace();
        pos--;
        flushChars();
        handler.startElement("", "h" + level, "", null);
        at_end_of_line.add("h" + level);
        return true;
      }
    else
      {
        resetCurrentChar(oldpos);
        return false;
      }
  }

  private boolean listItem() throws SAXException
  {
    int oldpos = pos;
    nextChar();
    int spaces = readChars(' ');
    int level = spaces / 3;
    if ((level * 3 == spaces) && (curchar == '*'))
      {
        flushChars();
        if (list_level > 0)
          {
            handler.endElement("", "li", "");
          }
        if (level > list_level)
          for (int i = level - list_level; i > 0; i--)
            {
              handler.startElement("", "ul", "", null);
            }
        else
          for (int i = list_level - level; i > 0; i--)
            {
              handler.endElement("", "ul", "");
            }
        list_level = level;
        handler.startElement("", "li", "", null);
        return true;
      }
    else
      {
        resetCurrentChar(oldpos);
        return false;
      }
  }

  private boolean separator() throws SAXException
  {
    // [-]3+

    int oldpos = pos;
    nextChar();
    int count = readChars('-');
    if (count >= 3)
      {
        pos--;
        flushChars();
        handler.startElement("", "hr", "", null);
        handler.endElement("", "hr", "");
        return true;
      }
    else
      {
        resetCurrentChar(oldpos);
        return false;
      }
  }

  private boolean parseLink() throws SAXException
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
            makeHref(link, ref);
            return true;
          }
      }
    resetCurrentChar(oldpos);
    return false;
  }

  private void makeHref(String link, String ref) throws SAXException
  {
    flushChars();
    AttributesImpl href = new AttributesImpl();
    href.addAttribute("", "href", "", "", wikiWordToURL(link));
    handler.startElement("", "a", "", href);
    copyString(ref);
    flushChars();
    handler.endElement("", "a", "");
  }

  private boolean matchWikiWord() throws SAXException
  {
    if (matchQualifiedWikiWord() || matchPathExp())
      return true;

    String word = matchBasicWikiWord();
    if (word != null)
      {
        makeHref(word, word);
        return true;
      }
    return false;
  }

  private boolean matchQualifiedWikiWord() throws SAXException
  {
    int oldpos = pos;

    StringBuffer pref = new StringBuffer("");

    if (isUpper(curchar))
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
                makeHref("/wiki1/view/" + pref + "/" + suff, suff);
                //out.write("<a href=\"/user1/view/" + pref + "/" + suff + "\">" + suff + "</a>");
                return true;
              }
          }
      }
    resetCurrentChar(oldpos);
    return false;
  }

  private String matchBasicWikiWord() throws SAXException
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

  private boolean matchPathExp() throws SAXException
  {
    int oldpos = pos;
    boolean hasslash = false;
    StringBuffer path = new StringBuffer("");

    path.append(curchar);
    hasslash = hasslash || curchar == '/';
    while (nextChar()
           && (isUpper(curchar) || isLower(curchar) || isDigit(curchar) || curchar == '/'))
      {
        hasslash = hasslash || curchar == '/';
        path.append(curchar);
      }
    if (hasslash)
      {
        if (path.charAt(0) == '/')
          makeHref("/wiki1/view" + path, path.toString());
        else
          makeHref(path.toString(), path.toString());
        resetCurrentChar(pos - 1);
        return true;
      }
    resetCurrentChar(oldpos);
    return false;
  }

  private boolean isUpper(char c)
  {
    return (int) c >= 65 && (int) c <= 90;
  }

  private boolean isLower(char c)
  {
    return (int) c >= 97 && (int) c <= 122;
  }

  private boolean isDigit(char c)
  {
    return (int) c >= 48 && (int) c <= 57;
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
        return "/wiki1/view" + s;
      }
    else if (s.matches("[A-Z][a-z]+[A-Z][a-z]+"))
      {
        // wiki word relative to current 'directory'
        return s;
      }
    else if (s.matches("[A-Z][A-Za-z]*\\.[A-Z][a-z]+[A-Z][a-z]+"))
      {
        return "/wiki1/view/" + s.replaceFirst("\\.", "/");
      }
    else
      {
        // interpret as phrase to be wikiwordified
        // capitalize first letters of each word
        // e.g. [[wiki word]] -> [[WikiWord]]

        StringBuffer b = new StringBuffer(s);
        StringBuffer c = new StringBuffer("");

        c.append(b.substring(0, 1).toUpperCase());
        for (int i = 1; i < b.length(); i++)
          {
            if (b.charAt(i) == ' ')
              {
                if (i + 1 < b.length())
                  {
                    i++;
                    c.append(b.substring(i, i + 1).toUpperCase());
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
        while (nextChar() && curchar != ']' && curchar != '\n'
               && curchar != '\r')
          {
            b.append(curchar);
          }
        if (curchar == ']')
          return b.toString();
      }
    resetCurrentChar(oldpos);
    return null;
  }

  private boolean verbatim() throws SAXException
  {
    if (!matchWord("<verbatim>"))
      {
        return false;
      }

    handler.startElement("", "pre", "", null);

    while (!matchWord("</verbatim>") && !eof)
      copyLineVerbatim();
    
    handler.endElement("", "pre", "");

    return true;
  }

  private boolean matchWord(String w) throws SAXException
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
  
  private void makeSimpleVariable(String varname) throws SAXException
  {
    flushChars();
    AttributesImpl name = new AttributesImpl();
    name.addAttribute("", "name", "", "", varname);
    handler.startElement("", "variable", "", name);
    handler.endElement("", "variable", ""); 
  }

  private void makeVariable(String varname, List<Argument> arguments) throws SAXException
  {
    flushChars();
    AttributesImpl name = new AttributesImpl();
    name.addAttribute("", "name", "", "", varname);
    handler.startElement("", "variable", "", name);
    for (Argument arg : arguments)
      {
         arg.send();
      }
    handler.endElement("", "variable", ""); 
  }

  private boolean matchVariable() throws SAXException
  {
    String varname = matchSimpleVariable();
    if (varname != null)
      {
        makeSimpleVariable(varname);
        return true;
      }
    return matchVariableWithArguments();
  }

  private String matchSimpleVariable() throws SAXException
  {
    int oldpos = pos;
    StringBuffer varname = new StringBuffer("");
    while (nextChar() && (isUpper(curchar) || isLower(curchar)))
      {
        varname.append(curchar);
      }
    if (curchar == '%')
      {
        // out.write("simple variable: " + varname);
        return varname.toString();
      }
    resetCurrentChar(oldpos);
    return null;
  }

  private boolean matchVariableWithArguments() throws SAXException
  {
    int oldpos = pos;
    StringBuffer varname = new StringBuffer("");
    while (nextChar() && (isUpper(curchar) || isLower(curchar)))
      {
        varname.append(curchar);
      }
    if (curchar == '{')
      {
        List<Argument> arguments = new LinkedList();
        StringBuffer argchars = new StringBuffer("");

        while (nextChar() && curchar != '}' && curchar != '\n'
               && curchar != '\r')
          {
            if (curchar == '%')
              {
                String value = matchSimpleVariable();
                if (value != null)
                  {
                    arguments.add(new Argument(argchars.toString(), false));
                    argchars = new StringBuffer("");
                    arguments.add(new Argument(value, true));
                    continue;
                  }
              }
            argchars.append(curchar);
          }

        if (curchar == '}' && nextChar() && curchar == '%')
          {
            makeVariable(varname.toString(), arguments);
            return true;
          }
      }
    resetCurrentChar(oldpos);
    return false;
  }

  public class Variable
  {
    String name;

    String arguments;

    public Variable(String name, String arguments)
    {
      this.name = name;
      this.arguments = arguments;
    }
  }
  
  public class Argument
  {
    String value;
    Boolean isvariable;
    
    public Argument(String value, boolean isvariable)
    {
      this.value = value;
      this.isvariable = isvariable;
    }
    
    public void send() throws SAXException
    {
      if (isvariable) 
        makeSimpleVariable(value);
      else
        copyString(value);
    }
  }

  private boolean noautolink() throws SAXException
  {
    if (matchWord("noautolink>"))
      {
        autolink = false;
        return true;
      }
    else if (matchWord("/noautolink>"))
      {
        autolink = true;
        return true;
      }
    return false;
  }

  private boolean htmlTag() throws SAXException
  {
    int oldpos = pos;
    StringBuffer tag = new StringBuffer("");
    tag.append(curchar);
    while (nextChar() && curchar != '>' && curchar != '\n' && curchar != '\r')
      {
        // this code handles a wiki variable inside a tag
        // to deal with things like <a href="%SCRIPTURL%/view/%TOPIC%">%TOPIC%</a>
        // this is problematic in the current model (content handler)
        // since we cannot produce a variable element event inside an annotation
        //if (curchar == '%')
        //  {
        //    String varname = matchSimpleVariable();
        //   if (varname != null)
        //      {
        //        String value = evaluateSimpleVariable(varname);
        //        if (value != null)
        //          {
        //            tag.append(value);
        //            continue;
        //          }
        //      }
        //  }
        tag.append(curchar);
      }
    if (curchar == '>')
      {
        tag.append(curchar);
        // hmm, we don't distinghuish start and end tags here
        // so for the time being, just copying html tags as character content
        // (which assumption then makes it possible to handle variables ...)
        copyString(tag.toString());
        //out.write(tag.toString());
        return true;
      }
    resetCurrentChar(oldpos);
    return false;
  }

}
