package com.macfaq.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.*;


public class PseudoAttributes {

  private NamedNodeMap pseudo;

  public PseudoAttributes(ProcessingInstruction pi) 
   throws SAXException {
  
    StringBuffer sb = new StringBuffer("<");
    sb.append(pi.getTarget());
    sb.append(" ");
    sb.append(pi.getData());
    sb.append("/>");
    StringReader reader = new StringReader(sb.toString());
    InputSource source = new InputSource(reader);
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();
      
      // This line will throw a SAXException if the processing
      // instruction does not use pseudo-attributes
      Document doc = parser.parse(source);
      Element root = doc.getDocumentElement();
      pseudo = root.getAttributes();
      
    }
    catch (FactoryConfigurationError e) { 
      // I don't absolutely need to catch this, but I hate to 
      // throw an Error for no good reason.
      throw new SAXException(e.getMessage()); 
    }    
    catch (SAXException e) { 
      throw e; 
    }    
    catch (Exception e) { 
      throw new SAXException(e); 
    }    
    
  }

  // delegator methods
  public Attr item(int index) {
    return (Attr) pseudo.item(index);
  }
  
  public int getLength() {
    return pseudo.getLength();
  }

  public String getValue(String name) {
    Attr att = (Attr) pseudo.getNamedItem(name);
    if (att == null) return "";
    return att.getValue();
  }
  
}
