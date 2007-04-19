package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

//@Local
public interface IPersonHome {

	public Person initPerson();

	public void findHomepages();

	public void addHomepage();
	
	public void homepageChanged();
	
	public void create();
	
	public void destroy();
	
	public boolean isManaged();

}