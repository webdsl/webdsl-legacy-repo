package org.xml.sax.helpers;


public class NamespaceSupport {

  public final static String XMLNS 
   = "http://www.w3.org/XML/1998/namespace";

  public NamespaceSupport();
  
  public void        reset();
  public void        pushContext();
  public void        popContext();
  public boolean     declarePrefix(String prefix, String uri);
  public String[]    processName(String qualifedName, 
   String parts[], boolean isAttribute);
  public String      getURI(String prefix);
  public Enumeration getPrefixes();
  public String      getPrefix(String uri);
  public Enumeration getPrefixes(String uri);
  public Enumeration getDeclaredPrefixes();

}
