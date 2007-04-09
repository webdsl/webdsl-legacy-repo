package org.webdsl.serg;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static org.jboss.seam.ScopeType.SESSION;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.validator.Length;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.webdsl.serg.domain.User;


@Stateful 
@Name("users")
public class UsersBean implements Users {

    @Logger private Log log;
    
    @In
    FacesMessages facesMessages;
    
    @DataModel
    private List<User> userList;
    
    @DataModelSelection
    @Out(required = false)
    private User selectedUser;
    
    @PersistenceContext(type=EXTENDED)
    private EntityManager em;
    
    @Factory("userList")
    public void findUsers()
    {
      userList = em.createQuery("from User u order by u.username asc").getResultList();
      log.info("call to finUsers: list = " + userList);
    }
	
	public String users()
	{
		//implement your business logic here
		log.info("users.users() action called with: #{users.value}");
		facesMessages.add("users #{users.value}");
		return "success";
	}
	
	public String select()
	{
		return null;
	}
	
	public String delete()
	{
		return null;
	}
	
	@Destroy @Remove                                                                      
	public void destroy() {}	
}
