

package org.webdsl.wiki.viewers;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ViewerFactory
{

  private static Map<String,Class> viewertypes = new HashMap();

  private static Map<String,String> viewservlets = new HashMap();

  public static Viewer getViewer(String mimetype)
  {
    Class viewerclass = viewertypes.get(mimetype);
    if (viewerclass == null)
      throw new RuntimeException("unknown type: " + mimetype);
    try
      {
        Constructor constructor = viewerclass.getConstructor(new Class[0]);
        return (Viewer) constructor.newInstance(new Object[0]);
      }
    catch (Exception e)
      {
        throw new RuntimeException(e);
      }
  }

  public static String getViewerServlet(String mimetype)
  {
    return viewservlets.get(mimetype);
  }

  static
    {
      viewertypes.put("text/plain", PlainViewer.class);
      viewertypes.put("text/html", HtmlViewer.class);
      viewertypes.put("text/css", CssViewer.class);
      viewertypes.put("text/twiki", TWikiViewer.class);

      viewservlets.put("text/html",
                       "/WEB-INF/classes/org/webdsl/wiki/servlets/TopicView.jsp");
      // TODO view servlets for other types
    }

}
