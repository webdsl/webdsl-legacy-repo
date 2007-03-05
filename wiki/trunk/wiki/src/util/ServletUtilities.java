package util;

public class ServletUtilities
{

  public static String getNameFromPathInfo(String foo)
  {
     if(foo != null && foo.startsWith("/")) {
         return foo.substring(1);
     } else {
         return null;
     }
  }

}
