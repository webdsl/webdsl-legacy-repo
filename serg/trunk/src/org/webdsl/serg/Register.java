package org.webdsl.serg;

import javax.ejb.Local;

@Local
public interface Register {  
   
	//seam-gen methods
	public String register(); 
	public String getValue();
	public void setValue(String value);
	public void destroy();
	
   //add additional interface methods here
}