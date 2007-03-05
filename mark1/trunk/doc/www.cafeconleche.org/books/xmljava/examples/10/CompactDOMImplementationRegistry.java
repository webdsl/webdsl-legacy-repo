package org.w3c.dom; 

public class DOMImplementationRegistry  { 

  // The system property that specifies DOMImplementationSource 
  // class names. 
  public static String PROPERTY 
   = "org.w3c.dom.DOMImplementationSourceList";

  public static DOMImplementation getDOMImplementation(
   String features) throws ClassNotFoundException, 
   InstantiationException, IllegalAccessException;

  public static void addSource(DOMImplementationSource s)
   throws ClassNotFoundException, InstantiationException, 
   IllegalAccessException;
   
}
