package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.SESSION;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.seam.annotations.Create;
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
import org.webdsl.serg.domain.Address;

@Name("initDBActionBean") public class InitDBActionBean  
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  public String initDB()
  { 
    Address var0 = new Address();
    var0.setStreet("Mekelweg");
    var0.setCity("Delft");
    var0.setPhone("015");
    Address Mekelweg4 = var0;
    Person var1 = new Person();
    var1.setFullname("Eelco Visser");
    var1.setAddress(Mekelweg4);
    var1.setHomepage("http://www.eelcovisser.net");
    var1.setPhoto("http://static.flickr.com/56/141569082_372ea07ea9_m.jpg");
    Person EelcoVisser = var1;
    User var2 = new User();
    var2.setUsername("Eelco Visser");
    var2.setEmail("visser@acm.org");
    var2.setPassword("foo");
    var2.setPerson(EelcoVisser);
    EelcoVisser.setUser(var2);
    em.persist(EelcoVisser);
    EelcoVisser;
    return null;
  }
}