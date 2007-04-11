package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.SESSION;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

@Name("topicList") public class TopicList  implements ITopicList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<Topic> topicList;

  @DataModelSelection @Out(required = false) private Topic topic;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("topicList") public void findEntries()
  { 
    topicList = em.createQuery("from " + "Topic").getResultList();
    log.info("call to findEntries: list = " + topicList);
  }

  public void delete()
  { 
    if(topicList == null)
      facesMessages.add("entries list is null");
    else
      if(topic == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        topicList.remove(topic);
        em.remove(topic);
        topic = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}