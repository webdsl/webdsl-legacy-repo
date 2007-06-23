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
    initPerson49List();
    initProject38List();
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

  @DataModel("person49List") private List<Person> person49List;

  public List<Person> getPerson49List()
  { 
    log.info("getPerson49List");
    return person49List;
  }

  @Factory("person49List") public void initPerson49List()
  { 
    log.info("initPerson49List");
    person49List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project38List") private List<ResearchProject> project38List;

  public List<ResearchProject> getProject38List()
  { 
    log.info("getProject38List");
    return project38List;
  }

  @Factory("project38List") public void initProject38List()
  { 
    log.info("initProject38List");
    project38List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}