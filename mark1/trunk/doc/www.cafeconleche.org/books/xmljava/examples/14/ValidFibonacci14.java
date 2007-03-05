import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.math.BigInteger;
import java.io.IOException;


public class ValidFibonacci {

  public static void main(String[] args) {

    Element root = new Element("Fibonacci_Numbers");
    DocType type = new DocType("Fibonacci_Numbers", "fibonacci.dtd");
    Document doc = new Document(root, type);

    BigInteger low  = BigInteger.ONE;
    BigInteger high = BigInteger.ONE;

    for (int i = 1; i <= 5; i++) {
      Element fibonacci = new Element("fibonacci");
      fibonacci.setAttribute("index", String.valueOf(i));
      fibonacci.setText(low.toString());
      root.addContent(fibonacci);

      BigInteger temp = high;
      high = high.add(low);
      low = temp;
    }

    // serialize with two space indents and extra line breaks
    try {
      XMLOutputter serializer = new XMLOutputter("  ", true);
      serializer.output(doc, System.out);
    }
    catch (IOException e) {
      System.err.println(e);
    }

  }

}
