package org.jdom;

public final class Namespace {

  // Common namespaces
  public static final Namespace NO_NAMESPACE;
  public static final Namespace XML_NAMESPACE;

  // Factory methods
  public static Namespace getNamespace(String prefix, String uri);
  public static Namespace getNamespace(String uri);
  
  // Getter methods
  public String getPrefix();
  public String getURI();
  
  // Utility methods
  public boolean equals(Object o);
  public String toString();
  public int hashCode();
  
}
