package org.webdsl.serg.domain;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;

import org.jboss.annotation.ejb.Local;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.datamodel.DataModel;

@Local
public interface EditPerson {

	public abstract void initialize();

	public abstract boolean isNew();
	
	public Person getPerson();

	public void setPerson(Person person);
	
	public abstract String create();

	public abstract String update();

	public abstract String delete();

	public abstract String done();

    public String view();
    
	public abstract String getNewHomepage();

	public abstract void setNewHomepage(String newHomepage);

	public abstract void addHomepage();

	public abstract void deleteHomepage();

	public abstract void homepageChanged();

	public abstract List<String> getHomepages();

	public abstract void destroy();

}