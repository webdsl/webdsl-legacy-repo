package org.webdsl.wiki.viewers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Viewer {

	public void makeView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

}
