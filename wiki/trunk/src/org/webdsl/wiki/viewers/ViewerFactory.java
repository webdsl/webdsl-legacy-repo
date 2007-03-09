package org.webdsl.wiki.viewers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ViewerFactory {

	private static Map<String, Class> viewertypes = new HashMap();

	public static Viewer getViewer(String mimetype) {
		try {
			Constructor constructor = viewertypes.get(mimetype).getConstructor(new Class[0]);
			return (Viewer) constructor.newInstance(new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static {
		viewertypes.put("text/plain", PlainViewer.class);
		viewertypes.put("text/css", CssViewer.class);
		viewertypes.put("text/twiki", TWikiViewer.class);

	}

}
