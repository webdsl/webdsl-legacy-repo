package org.webdsl.serg.beans;

import static javax.persistence.PersistenceContextType.EXTENDED;

import javax.ejb.Remove;
import javax.ejb.Stateful;
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
import org.jboss.seam.contexts.Context;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
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

	@In
	private Context conversationContext;

	@RequestParameter("pub")
	private Long pubId;

	private Publication pub;

	public void setPub(Publication pub) {
		this.pub = pub;
	}

	public Publication getPub() {
		return pub;
	}

	@Create
	@Begin
	public void initialize() {
		if (pubId == null) {
			log.info("No " + "pubId" + " defined, couldn't start conversation");
		} else {
			pub = em.find(Publication.class, pubId);
		}
	}

	@End
	public String save() {
		if (pub == null)
			log.info("pub is null");
		log.info("saving publication " + pub + " (id = " + pub.getId() + ")");
		// em.persist(pub);
		em.flush();
		return "/viewPublication.seam?pub=" + pub.getId() ;
	}

	@End
	public String cancel() {
		log.info("not saving publication " + pub + " (id = " + pub.getId() + ")");
		// em.persist(pub);
		//em.flush();
		return "/viewPublication.seam?pub=" + pub.getId() ;
	}
	
	@Destroy
	@Remove
	public void destroy() {
	}
}