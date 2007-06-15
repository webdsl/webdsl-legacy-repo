package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.faces.event.ValueChangeEvent;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.ejb.Remove;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Factory;
import org.webdsl.serg.domain.*;

@Stateful @Name("allPost") public class AllPostBean  implements AllPostBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allPost" + ".initalize()");
    initPerson128List();
    initProject123List();
    initPost3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePost(Post post4)
  { 
    em.remove(post4);
  }

  @DataModel("person128List") private List<Person> person128List;

  public List<Person> getPerson128List()
  { 
    log.info("getPerson128List");
    return person128List;
  }

  @Factory("person128List") public void initPerson128List()
  { 
    log.info("initPerson128List");
    person128List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project123List") private List<ResearchProject> project123List;

  public List<ResearchProject> getProject123List()
  { 
    log.info("getProject123List");
    return project123List;
  }

  @Factory("project123List") public void initProject123List()
  { 
    log.info("initProject123List");
    project123List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("post3List") private List<Post> post3List;

  public List<Post> getPost3List()
  { 
    log.info("getPost3List");
    return post3List;
  }

  @Factory("post3List") public void initPost3List()
  { 
    log.info("initPost3List");
    post3List = em.createQuery("from " + "Post").getResultList();
  }
}