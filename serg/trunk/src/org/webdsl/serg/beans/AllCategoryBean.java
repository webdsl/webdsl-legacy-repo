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
    initPerson46List();
    initProject41List();
    initCategory3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeCategory(Category category4)
  { 
    em.remove(category4);
  }

  @DataModel("person46List") private List<Person> person46List;

  public List<Person> getPerson46List()
  { 
    log.info("getPerson46List");
    return person46List;
  }

  @Factory("person46List") public void initPerson46List()
  { 
    log.info("initPerson46List");
    person46List = em.createQuery("from " + "Person").getResultList();
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

  @DataModel("category3List") private List<Category> category3List;

  public List<Category> getCategory3List()
  { 
    log.info("getCategory3List");
    return category3List;
  }

  @Factory("category3List") public void initCategory3List()
  { 
    log.info("initCategory3List");
    category3List = em.createQuery("from " + "Category").getResultList();
  }
}