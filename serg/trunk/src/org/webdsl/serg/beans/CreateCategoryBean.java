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
    Category var21 = new Category();
    category = var21;
    initPerson1024List();
    initProject1124List();
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

  @DataModel("person1024List") private List<Person> person1024List;

  public List<Person> getPerson1024List()
  { 
    log.info("getPerson1024List");
    return person1024List;
  }

  @Factory("person1024List") public void initPerson1024List()
  { 
    log.info("initPerson1024List");
    person1024List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1124List") private List<ResearchProject> project1124List;

  public List<ResearchProject> getProject1124List()
  { 
    log.info("getProject1124List");
    return project1124List;
  }

  @Factory("project1124List") public void initProject1124List()
  { 
    log.info("initProject1124List");
    project1124List = em.createQuery("from " + "ResearchProject").getResultList();
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