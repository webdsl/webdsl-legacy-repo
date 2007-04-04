package org.webdsl.serg;

import javax.ejb.Local;

@Local
public interface People {

	//seam-gen method
	public String people();

	//add additional interface methods here
}