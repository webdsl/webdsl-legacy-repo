package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
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
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("userEditor")
public class UserEditorBean implements UserEditor {
	@Logger
	private Log log;

	@In
	private FacesMessages facesMessages;

	@PersistenceContext(type = EXTENDED)
	private EntityManager em;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Create
	public void initialize() {
		user = new User();
	}

	private boolean isNew = true;

	public boolean isNew() {
		return isNew;
	}

	@Begin(join = true)
	public String create() {
		em.persist(user);
		isNew = false;
		log.info("creating new " + "user");
		return null;
	}

	@RequestParameter("userId")
	private Long userId;

	@Begin(join = true)
	public String edit() {
		user = em.find(User.class, userId);
		isNew = false;
		log.info("loaded new " + "user" + " for editing " + user);
		return "editAddress";
	}

	public String save() {
		em.joinTransaction();
		em.flush();
		log.info("saving " + "user");
		return null;
	}

	@End
	public String delete() {
		em.remove(user);
		user = null;
		log.info("deleting " + "User");
		return "find" + "User";
	}

	@End
	public String done() {
		if (!isNew)
			em.refresh(user);
		return "find" + "User";
	}

	@End
	public String view() {
		if (!isNew)
			em.refresh(user);
		return "view" + "User";
	}

	@Remove
	@Destroy
	public void destroy() {
	}
}