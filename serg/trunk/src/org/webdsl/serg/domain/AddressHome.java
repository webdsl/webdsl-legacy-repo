package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("addressHome")
public class AddressHome extends EntityHome<Address> {
	@Factory("address")
	public Address initAddress() {
		return getInstance();
	}
}