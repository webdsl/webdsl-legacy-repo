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
    Address var1 = new Address();
    var1.setStreet("Mekelweg");
    var1.setCity("Delft");
    var1.setPhone("015");
    Address Mekelweg4 = var1;
    Address var2 = new Address();
    var2.setStreet("Ringwade 1");
    var2.setCity("Nieuwegein");
    var2.setPhone("030");
    Address Ordina = var2;
    Person var3 = new Person();
    var3.setFullname("Eelco Visser");
    var3.setEmail("visser@acm.org");
    var3.setAddress(Mekelweg4);
    var3.setHomepage("http://www.eelcovisser.net");
    var3.setPhoto("/img/eelcovisser.jpg");
    Person EelcoVisser = var3;
    User var4 = new User();
    var4.setUsername("EelcoVisser");
    var4.setPassword("foo");
    var4.setPerson(EelcoVisser);
    EelcoVisser.setUser(var4);
    Person var5 = new Person();
    var5.setFullname("Arie van Deursen");
    var5.setEmail("A.vanDeursen@tudelft.nl");
    var5.setAddress(Mekelweg4);
    var5.setHomepage("http://www.st.ewi.tudelft.nl/~arie/");
    var5.setPhoto("http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg");
    Person ArieVanDeursen = var5;
    Person var6 = new Person();
    var6.setFullname("Jos Warmer");
    var6.setEmail("jos@ordina.nl");
    var6.setAddress(Ordina);
    var6.setHomepage("http://www.klasse.nl/who/cv-jos.html");
    var6.setPhoto("http://www.klasse.nl/who/images/jos.gif");
    Person JosWarmer = var6;
    ResearchProject var7 = new ResearchProject();
    var7.setFullname("Model-Driven Software Evolution");
    var7.setAcronym("MoDSE");
    Set var8 = new HashSet();
    var8.add(EelcoVisser);
    var8.add(ArieVanDeursen);
    var8.add(JosWarmer);
    var7.setMembers(var8);
    var7.setDescription("The promise of model-driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. The problem with model-driven engineering is that it can lead to a lock-in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model-driven approaches are no exception. What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. The first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain-specific modeling languages. After developing a number of systems using a particular meta-model, new patterns may be recognized that can be captured in a higher-level or richer meta-model. The second premise is that reengineering of legacy systems to the model-driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model-driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain-specific languages.");
    ResearchProject MoDSE = var7;
    Publication var9 = new Publication();
    var9.setTitle("Domain-Specific Language Engineering");
    List var10 = new ArrayList();
    var10.add(EelcoVisser);
    var9.setAuthors(var10);
    var9.setYear(2007);
    var9.setAbstract("The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment.");
    Set var11 = new HashSet();
    var11.add(MoDSE);
    var9.setProjects(var11);
    Publication GTTSE07 = var9;
    Publication var12 = new Publication();
    var12.setTitle("Model-Driven Software Evolution: A Research Agenda");
    List var13 = new ArrayList();
    var13.add(ArieVanDeursen);
    var13.add(JosWarmer);
    var13.add(EelcoVisser);
    var12.setAuthors(var13);
    var12.setYear(2006);
    var12.setAbstract("Software systems need to evolve, and systems built using model-driven approaches are no exception.  What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model-based software systems and identify challenges to be addressed by research in this area.");
    Set var14 = new HashSet();
    var14.add(MoDSE);
    var12.setProjects(var14);
    Publication MoDSE07 = var12;
    MoDSE.setProposal(MoDSE07);
    Set var15 = new HashSet();
    var15.add(MoDSE07);
    var15.add(GTTSE07);
    MoDSE.setPublications(var15);
    em.persist(MoDSE);
  }
}