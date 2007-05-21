package org.webdsl.serg.beans;

import static javax.persistence.PersistenceContextType.EXTENDED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.webdsl.serg.domain.Person;
import org.webdsl.serg.domain.Publication;

@Stateful
@Name("editPublication")
public class EditPublicationBean implements EditPublicationBeanInterface {
	@Logger
	private Log log;

	@PersistenceContext(type = EXTENDED)
	private EntityManager em;

	@In
	private FacesMessages facesMessages;

	@Create
	@Begin
	public void initialize() {
		if (pubId == null) {
			log
					.debug("No " + "pubId"
							+ " defined, couldn't start conversation");
		} else {
			pub = em.find(Publication.class, pubId);
		}
		initAuthors();
	}

	@Destroy
	@Remove
	public void destroy() {
	}

	@RequestParameter("pub")
	private Long pubId;

	private Publication pub;

	public void setPub(Publication pub) {
		this.pub = pub;
	}

	public Publication getPub() {
		return pub;
	}

	@End
	public String save() {
		log.info("save");
		em.flush();
		return "/" + "viewPublication" + ".seam?"
				+ ("pub" + "=" + pub.getId() + "");
	}

	@End
	public String cancel() {
		log.info("cancel");
		return "/" + "viewPublication" + ".seam?"
				+ ("pub" + "=" + pub.getId() + "");
	}

	public String addAuthorOld() {
		Person author;
		author = null;
		pub.getAuthors().add(author);
		return null;
	}
	
	private Person newAuthor;
	
	public void setNewAuthor(Person p) {
		newAuthor = p;
	}
	
	public Person getNewAuthor() {
		return newAuthor;
	}
	
	public String addAuthor() {
		log.info("addAuthor " + newAuthor);
		pub.addAuthors(newAuthor);
		authors.remove(newAuthor);
		return null;
	}
	
//	public void addAuthor(ValueChangeEvent event) {
//		log.info("addAuthor : " + event);
//		Long newAuthorId = (Long) event.getNewValue();
//		Person author = em.find(Person.class, newAuthorId);
//		pub.addAuthors(author);
//		authors.remove(author);
//		// setAuthors(pub.getAuthors().add(author));
//	}

	@DataModel("authors")
	private List<Person> authors;

	public List<Person> getAuthors() {
		return authors;
	}

	public void initAuthors() {
		log.info("EditPublicationBean.getAuthors");
		authors = new ArrayList<Person>();
		log.info("EditPublicationBean.getAuthors initializing");
		for (Object o : em.createQuery("from Person").getResultList()) {
			Person p = (Person) o;
			if (!pub.getAuthors().contains(p)) {
				authors.add(p);
			}
		}
	}	
	
//	@DataModel("authors")
//	private Map<String, Integer> authors;
//
//	public Map<String, Integer> getAuthors() {
//		return authors;
//	}
//
//	public void initAuthors() {
//		log.info("EditPublicationBean.getAuthors");
//		authors = new HashMap<String, Integer>();
//		log.info("EditPublicationBean.getAuthors initializing");
//		for (Object o : em.createQuery("from Person").getResultList()) {
//			Person p = (Person) o;
//			if (!pub.getAuthors().contains(p)) {
//				authors.put(p.getName(), new Integer(p.getId().intValue()));
//			}
//		}
//	}
}