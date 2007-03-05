import java.math.BigInteger;


public class MathMLFibonacci {

  public static void main(String[] args) {
   
      BigInteger low  = BigInteger.ONE;
      BigInteger high = BigInteger.ONE;      
      
      System.out.println("<?xml version=\"1.0\"?>");  
      System.out.println(
        "<mathml:math "
        + "xmlns:mathml=\"http://www.w3.org/1998/Math/MathML\">"
      );  
      for (int i = 1; i <= 10; i++) {
        System.out.println("  <mathml:mrow>");
        System.out.println("    <mathml:mi>f(" + i 
         + ")</mathml:mi>");
        System.out.println("    <mathml:mo>=</mathml:mo>");
        System.out.println("    <mathml:mn>" + low 
         + "</mathml:mn>");
        System.out.println("  </mathml:mrow>");
        BigInteger temp = high;
        high = high.add(low);
        low = temp;
      }
      System.out.println("</mathml:math>");  

  }

}
