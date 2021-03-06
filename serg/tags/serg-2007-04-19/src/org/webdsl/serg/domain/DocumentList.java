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

@Stateful @Scope(SESSION) @Name("documentListBean") public class DocumentList  implements IDocumentList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<Document> documentList;

  @DataModelSelection @Out(required = false) private Document document;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("documentList") public void findEntries()
  { 
    documentList = em.createQuery("from " + "Document").getResultList();
    log.info("call to findEntries: list = " + documentList);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(documentList == null)
      facesMessages.add("entries list is null");
    else
      if(document == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        documentList.remove(document);
        em.remove(document);
        document = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}