import java.math.BigInteger;

public class FibonacciNumbers {

  public static void main(String[] args) {
  
    BigInteger low  = BigInteger.ONE;
    BigInteger high = BigInteger.ONE;
    for (int i = 1; i <= 10; i++) {
      System.out.println(low);
      BigInteger temp = high;
      high = high.add(low);
      low = temp;
    }
    
  }

}
