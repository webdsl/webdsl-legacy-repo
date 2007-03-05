package org.jdom;

public class Text implements Serializable, Cloneable {

  protected String value;
  protected Object parent;

  protected Text();
  public    Text(String s);
  
  public String getText();
  public String getTextTrim();
  public String getTextNormalize();

  public static String normalizeString(String s);

  public Text     setText(String s);
  public void     append(String s);
  public void     append(Text text);
  public Element  getParent();
  public Document getDocument();
  protected Text  setParent(Element parent);
  public Text     detach();

  public       String  toString();
  public final int     hashCode();
  public final boolean equals(Object ob);
  public       Object  clone();
  
}
