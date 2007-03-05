import com.macfaq.net.URLGrabber;
import java.io.IOException;
import java.net.MalformedURLException;


public class URLGrabberTest {
  
  public static void main(String[] args) {
        
    for (int i = 0; i < args.length; i++) {
      try {
        String doc = URLGrabber.getDocumentAsString(args[i]);
        System.out.println(doc); 
      }
      catch (MalformedURLException e) {
        System.err.println(args[i] 
         + " cannot be interpreted as a URL.");
      }   
      catch (IOException e) {
        System.err.println("Unexpected IOException: " 
         + e.getMessage());        
      }   
    }
        
  }

}
