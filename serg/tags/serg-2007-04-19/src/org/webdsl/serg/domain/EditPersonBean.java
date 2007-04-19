package org.webdsl.serg.domain;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.persistence.PersistenceContextType.EXTENDED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("editPerson")
@Scope(ScopeType.CONVERSATION)
public class EditPersonBean implements EditPerson {

	@Logger
	private Log log;

	@In
	private FacesMessages facesMessages;

	@PersistenceContext(type = EXTENDED)
	private EntityManager em;

	// person

	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Create
	public void initialize() {
		person = new Person();
	}

	private boolean isNew = true;

	@TransactionAttribute(NOT_SUPPORTED)
	public boolean isNew() {
		return isNew;
	}

	@Begin(join = true)
	public String create() {
		em.persist(person);
		isNew = false;
		// return "editPerson";
		log.info("creating new person");
		return null;
	}

	// @LoggedIn
	// @IfInvalid(outcome = Outcome.REDISPLAY, refreshEntities = true)
	public String update() {
		em.joinTransaction();
		em.flush();
		log.info("updating person");
		return null;
	}

	@End
	// @LoggedIn
	public String delete() {
		em.remove(person);
		person = null;
		// issue.getProject().getIssues().remove(issue);
		// refreshFinder();
		// return doneOutcome;
		log.info("deleting Person and going to PersonList");
		return "/PersonList.xhtml";
	}

	@End
	public String done() {
		if (!isNew)
			em.refresh(person);
		// return doneOutcome;
		log.info("going to view of PersonList");
		return "/PersonList.xhtml";
	}

	@End
	public String view() {
		if (!isNew)
			em.refresh(person);
		// return doneOutcome;
		log.info("going to view of Person");
		return "/viewPerson.xhtml";
	}

	// new homepage

	private String newHomepage = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.webdsl.serg.domain.EditPerson#getNewHomepage()
	 */
	public String getNewHomepage() {
		return newHomepage;
	}

	public void setNewHomepage(String newHomepage) {
		this.newHomepage = newHomepage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.webdsl.serg.domain.EditPerson#addHomepage()
	 */
	public void addHomepage() {
		person.addHomepages(getNewHomepage());
	}

	// delete existing homepage

	public void deleteHomepage() {
		if (getHomepages() == null)
			log.info("deleting homepage " + currentHomepage
					+ " but homepages list is empty");
		else
			getHomepages().remove(currentHomepage);
	}

	// change a homepage

	public void homepageChanged() {
		int i = getHomepages().indexOf(currentHomepage);
		getHomepages().remove(currentHomepage);
		getHomepages().add(i, currentHomepage);
	}

	@DataModel("homepages")
	public List<String> getHomepages() {
		return person == null || person.getHomepages() == null ? null
				: new ArrayList<String>(person.getHomepages());
	}

	@DataModelSelection(value = "homepages")
	String currentHomepage;

	// @DataModelSelectionIndex(value="homepages")
	// int currentHomepageIndex;

	@Remove
	@Destroy
	public void destroy() {
	}
}