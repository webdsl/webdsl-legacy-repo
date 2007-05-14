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
    var0.setStreet("Mekelweg");
    var0.setCity("Delft");
    var0.setPhone("015");
    Address Mekelweg4 = var0;
    Address var1 = new Address();
    var1.setStreet("Ringwade 1");
    var1.setCity("Nieuwegein");
    var1.setPhone("030");
    Address Ordina = var1;
    Person var2 = new Person();
    var2.setFullname("Eelco Visser");
    var2.setAddress(Mekelweg4);
    var2.setHomepage("http://www.eelcovisser.net");
    var2.setPhoto("http://static.flickr.com/56/141569082_372ea07ea9_m.jpg");
    Person EelcoVisser = var2;
    User var3 = new User();
    var3.setUsername("EelcoVisser");
    var3.setEmail("visser@acm.org");
    var3.setPassword("foo");
    var3.setPerson(EelcoVisser);
    EelcoVisser.setUser(var3);
    Person var4 = new Person();
    var4.setFullname("Arie van Deursen");
    var4.setAddress(Mekelweg4);
    var4.setHomepage("http://www.st.ewi.tudelft.nl/~arie/");
    var4.setPhoto("http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg");
    Person ArieVanDeursen = var4;
    Person var5 = new Person();
    var5.setFullname("Jos Warmer");
    var5.setAddress(Ordina);
    var5.setHomepage("http://www.klasse.nl/who/cv-jos.html");
    var5.setPhoto("http://www.klasse.nl/who/images/jos.gif");
    Person JosWarmer = var5;
    ResearchProject var6 = new ResearchProject();
    var6.setFullname("Model-Driven Software Evolution");
    var6.setAcronym("MoDSE");
    Set var7 = new HashSet();
    var7.add(EelcoVisser);
    var7.add(ArieVanDeursen);
    var7.add(JosWarmer);
    var6.setMembers(var7);
    ResearchProject MoDSE = var6;
    Publication var8 = new Publication();
    var8.setTitle("Domain-Specific Language Engineering");
    List var9 = new ArrayList();
    var9.add(EelcoVisser);
    var8.setAuthors(var9);
    var8.setYear(2007);
    var8.setPubabstract("The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment.");
    Set var10 = new HashSet();
    var10.add(MoDSE);
    var8.setProjects(var10);
    Publication GTTSE07 = var8;
    Publication var11 = new Publication();
    var11.setTitle("Model-Driven Software Evolution: A Research Agenda");
    List var12 = new ArrayList();
    var12.add(ArieVanDeursen);
    var12.add(JosWarmer);
    var12.add(EelcoVisser);
    var11.setAuthors(var12);
    var11.setYear(2007);
    var11.setPubabstract("");
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
    MoDSE;
    return null;
  }

  @Remove @Destroy public void destroy()
  { }
}