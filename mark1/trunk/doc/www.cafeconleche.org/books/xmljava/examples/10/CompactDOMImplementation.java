package org.w3c.dom;

public interface DOMImplementation {
  
  public DocumentType createDocumentType(
   String rootElementQualifiedName, 
   String publicID, String systemID) throws DOMException;
  public Document createDocument(String rootElementNamespaceURI,
   String rootElementQualifiedName, DocumentType doctype) 
   throws DOMException;
  public boolean hasFeature(String feature, String version);

}
