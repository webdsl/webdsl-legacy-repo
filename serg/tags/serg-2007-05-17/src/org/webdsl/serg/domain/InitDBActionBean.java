package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.SESSION;
import java.util.*;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.seam.annotations.Create;
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

@Stateful @Name("initDBBean") public class InitDBActionBean  implements InitDBAction
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  public String initDB()
  { 
    Address var0 = new Address();
    Address Mekelweg4 = var0;
    Address var1 = new Address();
    Address Ordina = var1;
    Person var2 = new Person();
    var2.setAddress(Mekelweg4);
    Person EelcoVisser = var2;
    User var3 = new User();
    var3.setPerson(EelcoVisser);
    EelcoVisser.setUser(var3);
    Person var4 = new Person();
    var4.setAddress(Mekelweg4);
    Person ArieVanDeursen = var4;
    Person var5 = new Person();
    var5.setAddress(Ordina);
    Person JosWarmer = var5;
    ResearchProject var6 = new ResearchProject();
    Set var7 = new HashSet();
    var7.add(EelcoVisser);
    var7.add(ArieVanDeursen);
    var7.add(JosWarmer);
    var6.setMembers(var7);
    ResearchProject MoDSE = var6;
    Publication var8 = new Publication();
    List var9 = new ArrayList();
    var9.add(EelcoVisser);
    var8.setAuthors(var9);
    var8.setYear(2007);
    Set var10 = new HashSet();
    var10.add(MoDSE);
    var8.setProjects(var10);
    Publication GTTSE07 = var8;
    Publication var11 = new Publication();
    List var12 = new ArrayList();
    var12.add(ArieVanDeursen);
    var12.add(JosWarmer);
    var12.add(EelcoVisser);
    var11.setAuthors(var12);
    var11.setYear(2007);
    Set var13 = new HashSet();
    var13.add(MoDSE);
    var11.setProjects(var13);
    Publication MoDSE07 = var11;
    MoDSE.setProposal(MoDSE07);
    Set var14 = new HashSet();
    var14.add(MoDSE07);
    var14.add(GTTSE07);
    MoDSE.setPublications(var14);
    em.persist(MoDSE);
    return null;
  }

  @Remove @Destroy public void destroy()
  { }
}