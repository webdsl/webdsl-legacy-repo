package org.webdsl.serg;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.validator.Length;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.webdsl.serg.domain.User;

@Stateful
@Name("register")
public class RegisterBean implements Register {

	@Logger
	private Log log;

	@In
	FacesMessages facesMessages;

	@In
	User user;

	@PersistenceContext
	private EntityManager em;

	private String value;

	// seam-gen method
	public String register() {
		// implement your business logic here
		//log.info("register.register() action called with: #{register.value} and #{user.name}");
		//facesMessages.add("user #{user.name}");
		//facesMessages.add("register #{register.value}");
		// return "success";

		List existing = em.createQuery(
				"select u.username from User u where u.username=:username")
				.setParameter("username", user.getUsername()).getResultList();

		if (existing.size() == 0) {
			em.persist(user);
			log.info("Registered new user #{user.username}");
			//return "/registered.jspx";
			return "success";
		} else {
			FacesMessages.instance()
					.add("User #{user.username} already exists");
			return null;
		}

	}

	// add additional action methods

	@Length(max = 10)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Destroy
	@Remove
	public void destroy() {
	}
}
