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

@Stateful @Name("allCategory") public class AllCategoryBean  implements AllCategoryBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allCategory" + ".initalize()");
    initPerson54List();
    initProject41List();
    initCategory4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeCategory(Category category5)
  { 
    em.remove(category5);
  }

  @DataModel("person54List") private List<Person> person54List;

  public List<Person> getPerson54List()
  { 
    log.info("getPerson54List");
    return person54List;
  }

  @Factory("person54List") public void initPerson54List()
  { 
    log.info("initPerson54List");
    person54List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project41List") private List<ResearchProject> project41List;

  public List<ResearchProject> getProject41List()
  { 
    log.info("getProject41List");
    return project41List;
  }

  @Factory("project41List") public void initProject41List()
  { 
    log.info("initProject41List");
    project41List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("category4List") private List<Category> category4List;

  public List<Category> getCategory4List()
  { 
    log.info("getCategory4List");
    return category4List;
  }

  @Factory("category4List") public void initCategory4List()
  { 
    log.info("initCategory4List");
    category4List = em.createQuery("from " + "Category").getResultList();
  }
}