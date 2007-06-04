package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
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

@Stateful @Name("initDatabase") public class InitDatabaseBean  implements InitDatabaseBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("initDatabase" + ".initalize()");
  }

  @Destroy @Remove public void destroy()
  { }

  public void initDB()
  { 
    Address var15 = new Address();
    var15.setStreet("Mekelweg");
    var15.setCity("Delft");
    var15.setPhone("015");
    Address Mekelweg4 = var15;
    Address var16 = new Address();
    var16.setStreet("Ringwade 1");
    var16.setCity("Nieuwegein");
    var16.setPhone("030");
    Address Ordina = var16;
    Person var17 = new Person();
    var17.setFullname("Eelco Visser");
    var17.setEmail("visser@acm.org");
    var17.setAddress(Mekelweg4);
    var17.setHomepage("http://www.eelcovisser.net");
    var17.setPhoto("/img/eelcovisser.jpg");
    Person EelcoVisser = var17;
    User var18 = new User();
    var18.setUsername("EelcoVisser");
    var18.setPassword("foo");
    var18.setPerson(EelcoVisser);
    EelcoVisser.setUser(var18);
    Person var19 = new Person();
    var19.setFullname("Arie van Deursen");
    var19.setEmail("A.vanDeursen@tudelft.nl");
    var19.setAddress(Mekelweg4);
    var19.setHomepage("http://www.st.ewi.tudelft.nl/~arie/");
    var19.setPhoto("http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg");
    Person ArieVanDeursen = var19;
    Person var20 = new Person();
    var20.setFullname("Jos Warmer");
    var20.setEmail("jos@ordina.nl");
    var20.setAddress(Ordina);
    var20.setHomepage("http://www.klasse.nl/who/cv-jos.html");
    var20.setPhoto("http://www.klasse.nl/who/images/jos.gif");
    Person JosWarmer = var20;
    ResearchProject var21 = new ResearchProject();
    var21.setFullname("Model-Driven Software Evolution");
    var21.setAcronym("MoDSE");
    Set var22 = new HashSet();
    var22.add(EelcoVisser);
    var22.add(ArieVanDeursen);
    var22.add(JosWarmer);
    var21.setMembers(var22);
    var21.setDescription("The promise of model-driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. The problem with model-driven engineering is that it can lead to a lock-in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model-driven approaches are no exception. What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. The first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain-specific modeling languages. After developing a number of systems using a particular meta-model, new patterns may be recognized that can be captured in a higher-level or richer meta-model. The second premise is that reengineering of legacy systems to the model-driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model-driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain-specific languages.");
    ResearchProject MoDSE = var21;
    Publication var23 = new Publication();
    var23.setTitle("Domain-Specific Language Engineering");
    List var24 = new ArrayList();
    var24.add(EelcoVisser);
    var23.setAuthors(var24);
    var23.setYear(2007);
    var23.setAbstract("The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment.");
    Set var25 = new HashSet();
    var25.add(MoDSE);
    var23.setProjects(var25);
    Publication GTTSE07 = var23;
    Publication var26 = new Publication();
    var26.setTitle("Model-Driven Software Evolution: A Research Agenda");
    List var27 = new ArrayList();
    var27.add(ArieVanDeursen);
    var27.add(JosWarmer);
    var27.add(EelcoVisser);
    var26.setAuthors(var27);
    var26.setYear(2006);
    var26.setAbstract("Software systems need to evolve, and systems built using model-driven approaches are no exception.  What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model-based software systems and identify challenges to be addressed by research in this area.");
    Set var28 = new HashSet();
    var28.add(MoDSE);
    var26.setProjects(var28);
    Publication MoDSE07 = var26;
    MoDSE.setProposal(MoDSE07);
    Set var29 = new HashSet();
    var29.add(MoDSE07);
    var29.add(GTTSE07);
    MoDSE.setPublications(var29);
    em.persist(MoDSE);
  }
}