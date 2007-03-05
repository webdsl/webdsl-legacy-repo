import java.math.BigInteger;


public class FibonacciConstants {

  public final static String rootElementName 
   = "Fibonacci_Numbers";
  public final static String fibonacciElementName = "fibonacci";
  public final static String xmlDeclaration 
   = "<?xml version=\"1.0\"?>";

  public static void main(String[] args) {
   
      BigInteger low  = BigInteger.ONE;
      BigInteger high = BigInteger.ONE;      
      
      System.out.println(xmlDeclaration);  
      System.out.println("<" + rootElementName + ">");  
      for (int i = 0; i < 10; i++) {
        System.out.print("  <" + fibonacciElementName +">");
        System.out.print(low);
        System.out.println("</" + fibonacciElementName +">");
        BigInteger temp = high;
        high = high.add(low);
        low = temp;
      }
      System.out.println("</" + rootElementName + ">");  

  }

}
