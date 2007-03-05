package org.jdom;

public class ProcessingInstruction 
 implements Serializable, Cloneable {

  protected String target;
  protected String rawData;
  protected Map    mapData;
  protected Object parent;

  protected ProcessingInstruction();
  public    ProcessingInstruction(String target, Map data);
  public    ProcessingInstruction(String target, String data);
  
  public Element               getParent();
  public ProcessingInstruction detach();
  public Document              getDocument();

  public String getTarget();
  public String getData();
  public List   getNames();
  public String getValue(String name);

  public ProcessingInstruction setTarget(String target) 
   throws IllegalTargetException;
  public ProcessingInstruction setData(String data);
  public ProcessingInstruction setData(Map data);
  public ProcessingInstruction setValue(String name, 
   String value);
  
  public boolean removeValue(String name);

  public String        toString();
  public final boolean equals(Object ob);
  public final int     hashCode();
  public Object        clone();

}
