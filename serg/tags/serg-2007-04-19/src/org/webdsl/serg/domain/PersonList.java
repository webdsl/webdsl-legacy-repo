package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("personListBean")
public class PersonList implements IPersonList {
	@Logger
	private Log log;

	@In
	FacesMessages facesMessages;

	@DataModel
	private List<Person> personList;

	@DataModelSelection
	@Out(required = false)
	private Person person;

	@PersistenceContext(type = EXTENDED)
	private EntityManager em;
	
	@Create
	public void initialize() {
		findEntries();
	}

	//@Begin(join = true)
	//public String listPersons() {
	//	findEntries();
	//	return null;
	//}
	
	@Factory("personList")
	public void findEntries() {
		personList = em.createQuery("from " + "Person").getResultList();
		log.info("call to findEntries: list = " + personList);
	}

	public void refresh() {
		findEntries();
	}

	public void delete() {
		if (personList == null)
			facesMessages.add("entries list is null");
		else if (person == null)
			facesMessages.add("entry is null");
		else {
			facesMessages.add("deleting ~x_class #{entry.id}");
			personList.remove(person);
			em.remove(person);
			person = null;
		}
	}

	@Destroy
	@Remove
	public void destroy() {
	}
}