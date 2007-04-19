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

@Stateful @Scope(SESSION) @Name("imageListBean") public class ImageList  implements IImageList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<Image> imageList;

  @DataModelSelection @Out(required = false) private Image image;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("imageList") public void findEntries()
  { 
    imageList = em.createQuery("from " + "Image").getResultList();
    log.info("call to findEntries: list = " + imageList);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(imageList == null)
      facesMessages.add("entries list is null");
    else
      if(image == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        imageList.remove(image);
        em.remove(image);
        image = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}