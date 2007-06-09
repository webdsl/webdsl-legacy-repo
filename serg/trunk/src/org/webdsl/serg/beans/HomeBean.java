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

@Stateful @Name("home") public class HomeBean  implements HomeBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("home" + ".initalize()");
    initPerson101List();
    initProject111List();
    initPub0List();
    initPers0List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void initDB()
  { 
    Address var0 = new Address();
    var0.setStreet("Mekelweg");
    var0.setCity("Delft");
    var0.setPhone("015");
    Address Mekelweg40 = var0;
    Address var1 = new Address();
    var1.setStreet("Ringwade 1");
    var1.setCity("Nieuwegein");
    var1.setPhone("030");
    Address Ordina0 = var1;
    Person var2 = new Person();
    var2.setFullname("Eelco Visser");
    var2.setEmail("visser@acm.org");
    var2.setAddress(Mekelweg40);
    var2.setHomepage("http://www.eelcovisser.net");
    var2.setPhoto("/img/eelcovisser.jpg");
    Person EelcoVisser0 = var2;
    User var3 = new User();
    var3.setUsername("EelcoVisser");
    var3.setPassword("foo");
    var3.setPerson(EelcoVisser0);
    EelcoVisser0.setUser(var3);
    Person var4 = new Person();
    var4.setFullname("Arie van Deursen");
    var4.setEmail("A.vanDeursen@tudelft.nl");
    var4.setAddress(Mekelweg40);
    var4.setHomepage("http://www.st.ewi.tudelft.nl/~arie/");
    var4.setPhoto("http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg");
    Person ArieVanDeursen0 = var4;
    Person var5 = new Person();
    var5.setFullname("Jos Warmer");
    var5.setEmail("jos@ordina.nl");
    var5.setAddress(Ordina0);
    var5.setHomepage("http://www.klasse.nl/who/cv-jos.html");
    var5.setPhoto("http://www.klasse.nl/who/images/jos.gif");
    Person JosWarmer0 = var5;
    ResearchProject var6 = new ResearchProject();
    var6.setFullname("Model-Driven Software Evolution");
    var6.setAcronym("MoDSE");
    Set var7 = new HashSet();
    var7.add(EelcoVisser0);
    var7.add(ArieVanDeursen0);
    var7.add(JosWarmer0);
    var6.setMembers(var7);
    var6.setDescription("The promise of model-driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. The problem with model-driven engineering is that it can lead to a lock-in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model-driven approaches are no exception. What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. The first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain-specific modeling languages. After developing a number of systems using a particular meta-model, new patterns may be recognized that can be captured in a higher-level or richer meta-model. The second premise is that reengineering of legacy systems to the model-driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model-driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain-specific languages.");
    ResearchProject MoDSE0 = var6;
    Publication var8 = new Publication();
    var8.setTitle("Domain-Specific Language Engineering");
    List var9 = new ArrayList();
    var9.add(EelcoVisser0);
    var8.setAuthors(var9);
    var8.setYear(2007);
    var8.setAbstract("The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment.");
    Set var10 = new HashSet();
    var10.add(MoDSE0);
    var8.setProjects(var10);
    Publication GTTSE070 = var8;
    Publication var11 = new Publication();
    var11.setTitle("Model-Driven Software Evolution: A Research Agenda");
    List var12 = new ArrayList();
    var12.add(ArieVanDeursen0);
    var12.add(JosWarmer0);
    var12.add(EelcoVisser0);
    var11.setAuthors(var12);
    var11.setYear(2006);
    var11.setAbstract("Software systems need to evolve, and systems built using model-driven approaches are no exception.  What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model-based software systems and identify challenges to be addressed by research in this area.");
    Set var13 = new HashSet();
    var13.add(MoDSE0);
    var11.setProjects(var13);
    Publication MoDSE070 = var11;
    MoDSE0.setProposal(MoDSE070);
    Set var14 = new HashSet();
    var14.add(MoDSE070);
    var14.add(GTTSE070);
    MoDSE0.setPublications(var14);
    em.persist(MoDSE0);
  }

  @DataModel("person101List") private List<Person> person101List;

  public List<Person> getPerson101List()
  { 
    log.info("getPerson101List");
    return person101List;
  }

  @Factory("person101List") public void initPerson101List()
  { 
    log.info("initPerson101List");
    person101List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project111List") private List<ResearchProject> project111List;

  public List<ResearchProject> getProject111List()
  { 
    log.info("getProject111List");
    return project111List;
  }

  @Factory("project111List") public void initProject111List()
  { 
    log.info("initProject111List");
    project111List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("pub0List") private List<Publication> pub0List;

  public List<Publication> getPub0List()
  { 
    log.info("getPub0List");
    return pub0List;
  }

  @Factory("pub0List") public void initPub0List()
  { 
    log.info("initPub0List");
    pub0List = em.createQuery("from " + "Publication").getResultList();
  }

  @DataModel("pers0List") private List<Person> pers0List;

  public List<Person> getPers0List()
  { 
    log.info("getPers0List");
    return pers0List;
  }

  @Factory("pers0List") public void initPers0List()
  { 
    log.info("initPers0List");
    pers0List = em.createQuery("from " + "Person").getResultList();
  }
}