package org.webdsl.serg;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateless
@Name("register")
public class RegisterAction implements Register
{

   @In
   private User user;

   @PersistenceContext
   private EntityManager em;

   @Logger
   private Log log;

   public String register()
   {
      List existing = em.createQuery("select u.username from User u where u.username=:username")
         .setParameter("username", user.getUsername())
         .getResultList();

      if ( existing.size()==0 )
      {
         em.persist(user);
         log.info("Registered new user #{user.username}");
         return "success";
      }
      else
      {
         FacesMessages.instance().add("User #{user.username} already exists");
         return null;
      }
   }
   
}
