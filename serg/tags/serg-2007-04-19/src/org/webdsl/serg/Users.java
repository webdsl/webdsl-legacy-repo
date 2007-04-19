package org.webdsl.serg;

import javax.ejb.Local;

@Local
public interface Users {  

	public void findUsers();
	public String select();
	public String delete();
	public void destroy();
}