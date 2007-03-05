package javax.xml.transform;

public interface ErrorListener {

  public void warning(TransformerException exception)
   throws TransformerException;
  public void error(TransformerException exception)
   throws TransformerException;
  public void fatalError(TransformerException exception)
   throws TransformerException;
     
}
