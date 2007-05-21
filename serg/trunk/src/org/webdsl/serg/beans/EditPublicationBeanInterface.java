package org.webdsl.serg.beans;

import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import org.jboss.annotation.ejb.Local;
import org.webdsl.serg.domain.Publication;

@Local
public interface EditPublicationBeanInterface {
	public void initialize();

	public void destroy();

	public void setPub(Publication pub);

	public Publication getPub();

	public String save();

	public String cancel();

	public void setNewAuthor(String p);
	
	public String getNewAuthor();
	
	public String addAuthor();
	
	//public void addAuthor(ValueChangeEvent event);
	
	//public List<Person> getAuthors();
	
	public Map<String, String> getAuthors();
	
	public String removeAuthor(Long authorId);
}