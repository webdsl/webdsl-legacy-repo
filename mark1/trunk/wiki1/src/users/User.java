package users;

import java.util.*;

import org.hibernate.*;
import util.HibernateUtil;

public class User
{
    private Long id;
    private String username;
    private String fullname;
    private String password = "";
    private String email;
    private String url;

    public User() { }

    // getProperty

    public Long getId() {
      return id;
    }

    public String getUsername() {
	return username;
    }

    public String getFullname() {
	return fullname;
    }

    public String getPassword() {
	return password;
    }

    public String getEmail() {
	return email;
    }

    public String getUrl() {
	return url;
    }
    
    // setProperty

    private void setId(Long id) {
        this.id = id;
    }

    public void setUsername (String username) {
	this.username = username;
    }

    public void setFullname(String fullname) {
	this.fullname = fullname;
    }

    public void setPassword (String password) {
	this.password = password;
    }

    public void setEmail (String email) {
	this.email = email;
    }

    public void setUrl (String url) {
	this.url = url;
    }

    // topics that this user has contributed to

    private Set topics = new HashSet();

    public Set getTopics() {
      return topics;
    }

    public void setTopics(Set topics) {
      this.topics = topics;
    }

    public boolean isComplete() {
      return username != null
	   && fullname != null
	   && password != null
  	   && email != null
	   && topics != null;
	   // note: url is optional
    }

    public boolean equals(Object o) {
      if (o instanceof User) 
        return this.username.equals(((User)o).getUsername());
      else 
        return false;
    }

    public int hashCode() {
      return username.hashCode();
    }

    public static User getByName(String username)
    {
      Session session = HibernateUtil.getSessionFactory().getCurrentSession();

      session.beginTransaction();

      List users = session.createQuery("from User where username = ?")
			    .setString(0, username)
			    .list();

      session.getTransaction().commit();
	
      if(users.size() > 0)
         return (User)users.get(0);
      else
         return null;
    }

    public void save()
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tr = session.beginTransaction();
      session.save(this);
      tr.commit();
      session.close();
    }

    public void update()
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tr = session.beginTransaction();
      try {
        session.update(this);
        tr.commit();
      } catch (HibernateException e) {
        tr.rollback();
      }
      session.close();
    }

    public void delete()
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tr = session.beginTransaction();
      session.delete(this);
      tr.commit();
      session.close();
    }

}
