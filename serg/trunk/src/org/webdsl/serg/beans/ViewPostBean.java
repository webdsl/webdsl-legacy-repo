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

@Stateful @Name("viewPost") public class ViewPostBean  implements ViewPostBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewPost" + ".initalize()");
    if(postId == null)
    { 
      log.info("No " + "postId" + " defined, creating new " + "Post");
      post = new Post();
    }
    else
    { 
      post = em.find(Post.class, postId);
    }
    initPerson122List();
    initProject122List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("post") private Long postId;

  private Post post;

  public void setPost(Post post)
  { 
    log.info("setPost");
    this.post = post;
  }

  public Post getPost()
  { 
    log.info("getPost");
    return post;
  }

  @DataModel("person122List") private List<Person> person122List;

  public List<Person> getPerson122List()
  { 
    log.info("getPerson122List");
    return person122List;
  }

  @Factory("person122List") public void initPerson122List()
  { 
    log.info("initPerson122List");
    person122List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project122List") private List<ResearchProject> project122List;

  public List<ResearchProject> getProject122List()
  { 
    log.info("getProject122List");
    return project122List;
  }

  @Factory("project122List") public void initProject122List()
  { 
    log.info("initProject122List");
    project122List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}