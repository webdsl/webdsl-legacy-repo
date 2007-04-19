package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity
public class Person {
	
	public Person() {
		this.setFullname("Eelco Visser");
		this.setAddress(new Address());
		this.addHomepages("http://foo.bar");
		this.addHomepages("http://foo.baz");
		this.addHomepages("http://foo.boo");
	}

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private String fullname;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@ManyToOne
	@JoinColumn(name = "PersonAddress")
	@org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.ALL })
	private Address address = new Address();

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne
	@JoinColumn(name = "PersonHomepage")
	@org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.ALL })
	private URL homepage = new URL();

	public URL getHomepage() {
		return homepage;
	}

	public void setHomepage(URL homepage) {
		this.homepage = homepage;
	}

	@ManyToMany()
	@org.hibernate.annotations.Cascade( {
			org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.MERGE })
	private Map<String, Address> addresses = new HashMap<String, Address>();

	public Map<String, Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<String, Address> addresses) {
		this.addresses = addresses;
	}

	public void putAddresses(String key, Address value) {
		this.addresses.put(key, value);
	}

	@org.hibernate.annotations.CollectionOfElements(targetElement = String.class)
	@JoinTable(name = "Person_Homepages")
	@Column(name = "homepages", nullable = false)
	@org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.ALL })
	private List<String> homepages = new LinkedList<String>();

	public List<String> getHomepages() {
		return homepages;
	}

	public void setHomepages(List<String> homepages) {
		this.homepages = homepages;
	}

	public void addHomepages(String a_0) {
		this.homepages.add(a_0);
	}
	
	@ManyToOne
	@org.hibernate.annotations.Cascade( {
			org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.MERGE })
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return getFullname().toString();
	}
}