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

@Stateful @Name("editCategory") public class EditCategoryBean  implements EditCategoryBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editCategory" + ".initalize()");
    if(categoryId == null)
    { 
      log.info("No " + "categoryId" + " defined, creating new " + "Category");
      category = new Category();
    }
    else
    { 
      category = em.find(Category.class, categoryId);
    }
    initPerson1021List();
    initProject1121List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("category") private Long categoryId;

  private Category category;

  public void setCategory(Category category)
  { 
    log.info("setCategory");
    this.category = category;
  }

  public Category getCategory()
  { 
    log.info("getCategory");
    return category;
  }

  @End public String cancel()
  { 
    return "/" + "viewCategory" + ".seam?" + ("category" + "=" + category.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getCategory());
    return "/" + "viewCategory" + ".seam?" + ("category" + "=" + category.getId() + "");
  }

  @DataModel("person1021List") private List<Person> person1021List;

  public List<Person> getPerson1021List()
  { 
    log.info("getPerson1021List");
    return person1021List;
  }

  @Factory("person1021List") public void initPerson1021List()
  { 
    log.info("initPerson1021List");
    person1021List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1121List") private List<ResearchProject> project1121List;

  public List<ResearchProject> getProject1121List()
  { 
    log.info("getProject1121List");
    return project1121List;
  }

  @Factory("project1121List") public void initProject1121List()
  { 
    log.info("initProject1121List");
    project1121List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}