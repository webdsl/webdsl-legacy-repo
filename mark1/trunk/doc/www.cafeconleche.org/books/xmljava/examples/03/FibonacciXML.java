import java.math.BigInteger;


public class FibonacciXML {

  public static void main(String[] args) {
   
      BigInteger low  = BigInteger.ONE;
      BigInteger high = BigInteger.ONE;      
      
      System.out.println("<?xml version=\"1.0\"?>");  
      System.out.println("<Fibonacci_Numbers>");  
      for (int i = 0; i < 10; i++) {
        System.out.print("  <fibonacci>");
        System.out.print(low);
        System.out.println("</fibonacci>");
        BigInteger temp = high;
        high = high.add(low);
        low = temp;
      }
      System.out.println("</Fibonacci_Numbers>");  

  }

}