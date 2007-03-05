import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.math.BigInteger;
import java.io.IOException;


public class FibonacciJDOM {

  public static void main(String[] args) {

    Element root = new Element("Fibonacci_Numbers");

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

    Document doc = new Document(root);
    // serialize it onto System.out
    try {
      XMLOutputter serializer = new XMLOutputter();
      serializer.output(doc, System.out);
    }
    catch (IOException e) {
      System.err.println(e);
    }

  }

}

