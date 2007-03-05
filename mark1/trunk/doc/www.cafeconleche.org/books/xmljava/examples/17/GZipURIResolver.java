import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.io.InputStream;


public class GZipURIResolver implements URIResolver {

  public Source resolve(String href, String base) {
   
    try {
      href = href + ".gz";
      URL context = new URL(base);
      URL u = new URL(context, href); 
      InputStream in = u.openStream();
      GZIPInputStream gin = new GZIPInputStream(in);
      return new StreamSource(gin, u.toString());
    }
    catch (Exception e) {
      // If anything goes wrong, just return null and let
      // the default resolver try.
    }
    return null;
  }

}
