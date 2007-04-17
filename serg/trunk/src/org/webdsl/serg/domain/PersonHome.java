package org.webdsl.serg.domain;

import java.util.List;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.datamodel.DataModelSelectionIndex;
import org.jboss.seam.framework.EntityHome;

@Name("personHome")
public class PersonHome extends EntityHome<Person> {
	  
	@Factory("person")
	public Person initPerson() {
		return getInstance();
	}

	private String newHomepage = "";

	public String getNewHomepage() {
		return newHomepage;
	}

	public void setNewHomepage(String newHomepage) {
		this.newHomepage = newHomepage;
	}
	
	public void deleteHomepage() {
		homepages.remove(currentHomepageIndex);
	}
	
	//public void homepageChanged() {
	//	homepages.remove(currentHomepageIndex);
	//	homepages.add(currentHomepageIndex, currentHomepage);
	//}
	
	@DataModel("homepages")
	List<String> homepages;
	
	@Factory("homepages")
	public void findHomepages() {
		initPerson().getHomepages();
	}
	
	//@DataModelSelection(value="homepages")
	//String currentHomepage;

	@DataModelSelectionIndex(value="homepages")
	int currentHomepageIndex;
	
//	
//	public void addHomepages(String homepage)
//	{
//	    log.info("call to addHomepages 1: list = " + homepageList);
//		getInstance().addHomepages(homepage);
//		findHomepages();
//	    log.info("call to addHomepages 2: list = " + homepageList);
//	}
}