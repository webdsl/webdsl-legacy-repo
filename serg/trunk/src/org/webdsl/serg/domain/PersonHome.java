package org.webdsl.serg.domain;

import java.util.HashSet;
import java.util.Set;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("personHome")
public class PersonHome extends EntityHome<Person> {
	  
	@Factory("person")
	public Person initPerson() {
		return getInstance();
	}

//	@DataModel
//	private Set<String> homepageList = new HashSet<String>();
//
//	@DataModelSelection
//	@Out(required = false)
//	private String selectedHomepage;
//
//	
//	@Factory("homepageList")
//	public void findHomepages() {
//		homepageList = getInstance().getHomepages();
//	    log.info("call to findHomepages: list = " + homepageList);
//	}
//	
	private String newHomepage = "";

	public String getNewHomepage() {
		return newHomepage;
	}

	public void setNewHomepage(String newHomepage) {
		this.newHomepage = newHomepage;
	}

	public String addHomepage()
	{
		getInstance().addHomepages(newHomepage);
	    return null;
	}
//	
//	public void addHomepages(String homepage)
//	{
//	    log.info("call to addHomepages 1: list = " + homepageList);
//		getInstance().addHomepages(homepage);
//		findHomepages();
//	    log.info("call to addHomepages 2: list = " + homepageList);
//	}
}