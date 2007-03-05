import org.jdom.Element;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import java.math.BigInteger;
import java.io.IOException;


public class UnprefixedFibonacci {

  public static void main(String[] args) {

    Element root = new Element("math", "mathml",
     "http://www.w3.org/1998/Math/MathML");

    BigInteger low  = BigInteger.ONE;
    BigInteger high = BigInteger.ONE;

    for (int i = 1; i <= 5; i++) {

      Element mrow = new Element("mrow", 
       "http://www.w3.org/1998/Math/MathML");

      Element mi = new Element("mi", 
       "http://www.w3.org/1998/Math/MathML");
      mi.setText("f(" + i + ")");
      mrow.addContent(mi);

      Element mo = new Element("mo", 
       "http://www.w3.org/1998/Math/MathML");
      mo.setText("=");
      mrow.addContent(mo);

      Element mn = new Element("mn", 
       "http://www.w3.org/1998/Math/MathML");
      mn.setText(low.toString());
      mrow.addContent(mn);

      BigInteger temp = high;
      high = high.add(low);
      low = temp;
      root.addContent(mrow);

    }

    Document doc = new Document(root);
    try {
      XMLOutputter serializer = new XMLOutputter("  ", true);
      serializer.output(doc, System.out);
    }
    catch (IOException e) {
      System.err.println(e);
    }

  }

}
