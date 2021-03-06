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

@Stateful @Name("createCategory") public class CreateCategoryBean  implements CreateCategoryBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createCategory" + ".initalize()");
    Category var27 = new Category();
    category = var27;
    initPerson52List();
    initProject39List();
  }

  @Destroy @Remove public void destroy()
  { }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getCategory());
    return "/" + "viewCategory" + ".seam?" + ("category" + "=" + category.getId() + "");
  }

  @DataModel("person52List") private List<Person> person52List;

  public List<Person> getPerson52List()
  { 
    log.info("getPerson52List");
    return person52List;
  }

  @Factory("person52List") public void initPerson52List()
  { 
    log.info("initPerson52List");
    person52List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project39List") private List<ResearchProject> project39List;

  public List<ResearchProject> getProject39List()
  { 
    log.info("getProject39List");
    return project39List;
  }

  @Factory("project39List") public void initProject39List()
  { 
    log.info("initProject39List");
    project39List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Category category;

  public Category getCategory()
  { 
    log.info("getCategory");
    return category;
  }

  public void setCategory(Category category)
  { 
    log.info("setCategory");
    this.category = category;
  }
}