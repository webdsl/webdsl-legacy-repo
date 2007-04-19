package org.webdsl.serg.domain;

import javax.ejb.Stateful;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("personHome")
public class PersonHome extends EntityHome<Person> 
{
	@Factory("person")
	public Person initPerson() {
		return getInstance();
	}
}