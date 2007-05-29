package org.webdsl.serg.beans;

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
import org.webdsl.serg.domain.*;

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
    var2.setEmail("visser@acm.org");
    var2.setAddress(Mekelweg4);
    var2.setHomepage("http://www.eelcovisser.net");
    var2.setPhoto("/img/eelcovisser.jpg");
    Person EelcoVisser = var2;
    User var3 = new User();
    var3.setUsername("EelcoVisser");
    var3.setPassword("foo");
    var3.setPerson(EelcoVisser);
    EelcoVisser.setUser(var3);
    Person var4 = new Person();
    var4.setFullname("Arie van Deursen");
    var4.setEmail("A.vanDeursen@tudelft.nl");
    var4.setAddress(Mekelweg4);
    var4.setHomepage("http://www.st.ewi.tudelft.nl/~arie/");
    var4.setPhoto("http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg");
    Person ArieVanDeursen = var4;
    Person var5 = new Person();
    var5.setFullname("Jos Warmer");
    var5.setEmail("jos@ordina.nl");
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
    var6.setDescription("The promise of model-driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. The problem with model-driven engineering is that it can lead to a lock-in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model-driven approaches are no exception. What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. The first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain-specific modeling languages. After developing a number of systems using a particular meta-model, new patterns may be recognized that can be captured in a higher-level or richer meta-model. The second premise is that reengineering of legacy systems to the model-driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model-driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain-specific languages.");
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
    var11.setYear(2006);
    var11.setPubabstract("Software systems need to evolve, and systems built using model-driven approaches are no exception.  What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model-based software systems and identify challenges to be addressed by research in this area.");
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