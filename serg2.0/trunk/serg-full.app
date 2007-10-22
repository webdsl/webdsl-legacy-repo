application org.webdsl.serg

description
  {
  This application organizes information relevant for a research group,
  including people, publications, students, projects, colloquia, etc.
}

module app/templates
section main template .

  define main () {
    div("outersidebar"){
      logo(){
      }
      sidebar(){
      }
    }
    div("outerbody"){
      div("menubar"){
        menu(){
        }
      }
      body(){
      }
      footer(){
      }
    }
  }
section basic page elements .

  define logo () {
    navigate(home()){
      image("/img/serg-logo-color-smaller.png"){
      }
    }
  }

  define homesidebar () {
    list(){
      listitem(){
        navigate(home()){
          text("Home"){
          }
        }
      }
    }
  }

  define sidebar () {
    "default sidebar"
  }

  define sidebar () {
    "default body"
  }

  define footer () {
    "generated with "
    navigate(url("http://www.strategoxt.org")){
      text("Stratego/XT"){
      }
    }
  }
section menus .

  define menu () {
    list(){
      listitem(){
        "People"
        list(){
          for ( person : Person ) {
            listitem(){
              navigate(viewPerson(person)){
                text(person.name){
                }
              }
            }
          }
        }
      }
    }
    list(){
      listitem(){
        "Projects"
        list(){
          for ( project : ResearchProject ) {
            listitem(){
              navigate(viewResearchProject(project)){
                text(project.acronym){
                }
              }
            }
          }
        }
      }
    }
    list(){
      listitem(){
        navigate(manage()){
          text("Manage"){
          }
        }
        list(){
          manageMenu(){
          }
        }
      }
    }
    list(){
      listitem(){
        navigate(login()){
          "Login"
        }
      }
    }
  }
section entity management .

  define manageMenu () {
  }

  define page manage () {
    main(){
    }
    define sidebar () {
    }
    define body () {
      createMenu(){
      }
      allMenu(){
      }
    }
  }

module app/people
section users .

  entity User {
    username :: String ( name, unique )
    password :: Secret ( )
    person -> Person ( notnull )
  }
section persons .

  entity Address {
    street :: String ( )
    city :: String ( )
    phone :: String ( )
  }

  entity Person {
    fullname :: String ( name )
    email :: Email ( )
    homepage :: URL ( )
    photo :: Image ( )
    address <> Address ( )
    user -> User ( )
    blog -> Blog ( )
  }
section person pages .

  define personSidebar (p : Person) {
    list(){
      listitem(){
        navigate(viewPerson(p)){
          text(p.name){
          }
        }
      }
      listitem(){
        navigate(personPublications(p)){
          text("Publications"){
          }
        }
      }
      listitem(){
        navigate(viewBlog(p.blog)){
          text("Blog"){
          }
        }
        blogEntries(){
        }
      }
      listitem(){
        "Projects"
        listProjectAcronyms(p){
        }
      }
    }
  }

  define blogEntries () {
  }

  define page viewPerson (person : Person) {
    main(){
    }
    title(){
      text(person.name){
      }
    }
    define sidebar () {
      personSidebar(person){
      }
    }
    define manageMenu () {
      navigate(editPerson(person)){
        text("Edit"){
        }
      }
    }
    define body () {
      section(){
        div("photo"){
          image(person.photo){
          }
        }
        header(){
          text(person.name){
          }
        }
        section(){
          header(){
            "Coordinates"
          }
          table(){
            row(){
              "homepage"
              navigate(url(person.homepage)){
                text(person.homepage){
                }
              }
            }
            row(){
              "email"
              navigate(url(person.email)){
                text(person.email){
                }
              }
            }
            row(){
              "address"
              table(){
                row(){
                  text(person.address.street){
                  }
                }
                row(){
                  text(person.address.city){
                  }
                }
              }
            }
            row(){
              "phone"
              text(person.address.phone){
              }
            }
          }
        }
        section(){
          header(){
            "Publications"
          }
          publicationTitlesBy(person){
          }
        }
        section(){
          header(){
            "Projects"
          }
          listProjects(person){
          }
        }
      }
    }
  }
section basic crud pages .

  define page viewPersonSimple (person : Person) {
    div("crudTable"){
      table(){
        row(){
          "Fullname"
          outputString(person.fullname){
          }
        }
        row(){
          "Email"
          outputString(person.email){
          }
        }
        row(){
          "Homepage"
          outputString(person.homepage){
          }
        }
        row(){
          "Photo"
          outputString(person.photo){
          }
        }
        row(){
          "Address"
          ""
        }
        row(){
          "Street"
          outputString(person.address.street){
          }
        }
        row(){
          "City"
          outputString(person.address.city){
          }
        }
        row(){
          "Phone"
          outputString(person.address.phone){
          }
        }
        row(){
          "user"
          output(person.user){
          }
        }
        row(){
          navigate(editPersonSimple(person)){
            text("Edit"){
            }
          }
          ""
        }
      }
    }
  }

  define page editPersonSimple (person : Person) {
    div("crudTable"){
      form(){
        table(){
          row(){
            "Fullname"
            input(person.fullname){
            }
          }
          row(){
            "Email"
            input(person.email){
            }
          }
          row(){
            "Homepage"
            input(person.homepage){
            }
          }
          row(){
            "Photo"
            input(person.photo){
            }
          }
          row(){
            "Address"
            ""
          }
          row(){
            "Street"
            input(person.address.street){
            }
          }
          row(){
            "City"
            input(person.address.city){
            }
          }
          row(){
            "Phone"
            input(person.address.phone){
            }
          }
          row(){
            "user"
            input(person.user){
            }
          }
          row(){
            action("Save", save()){
            }
            ""
          }
        }
      }
    }
    action save ( )
    {
      person.save();
      return viewPersonSimple(person);
    }
  }

module app/access
section login .

  define page login () {
    main(){
    }
    define sidebar () {
    }
    var user : User ;
    define body () {
      form(){
        table(){
          row(){
            "username"
            input(user.username){
            }
          }
          row(){
            "password"
            input(user.password){
            }
          }
        }
        action("Login", login()){
        }
      }
    }
    action login ( )
    {
      return home();
    }
  }

module app/blog
description
  {
  A blog is a journal-like sequence of time-stamped entries. The main page of a
  blog shows the n most recent entries. Entries also have their own page.
}
section domain .

  entity Blog {
    title :: String ( name )
    author -> Person ( )
    entries <> List<BlogEntry> ( )
    categories -> List<Category> ( )
  }

  entity BlogEntry {
    blog -> Blog ( )
    title :: String ( name )
    created :: Date ( )
    category -> Category ( )
    intro :: Text ( )
    body :: Text ( )
    comments <> List<BlogComment> ( )
  }

  entity Category {
    name :: String ( )
  }

  entity BlogComment {
    author -> Person ( )
    text :: Text ( )
  }
section pages .

  define blogSidebar (blog : Blog) {
    personSidebar(blog.author){
    }
  }

  define blogEntries () {
  }

  define page viewBlog (blog : Blog) {
    main(){
    }
    var entries : List<BlogEntry> := select distinct e from BlogEntry as e , Blog as b where e member of b . _entries order by e . _created desc ;
    define blogEntries () {
      list(){
        for ( entry : BlogEntry in entries ) {
          listitem(){
            navigate(viewBlogEntry(entry)){
              text(entry.name){
              }
            }
          }
        }
      }
    }
    define sidebar () {
      blogSidebar(blog){
      }
    }
    define manageMenu () {
      navigate(editBlog(blog)){
        text("Edit"){
        }
      }
      form(){
        actionLink("New Blog", createNewBlogEntry()){
        }
      }
      action createNewBlogEntry ( )
      {
        var entry : BlogEntry := BlogEntry{blog := blog title := "title here"} ;
        blog.entries.add(entry);
        blog.persist();
        return editBlogEntry(entry);
      }
    }
    define body () {
      title(){
        text(blog.title){
        }
      }
      section(){
        header(){
          text(blog.title){
          }
        }
        for ( entry : BlogEntry in entries ) {
          section(){
            header(){
              text(entry.title){
              }
            }
            output(entry.created){
            }
            par(){
              outputText(entry.intro){
              }
            }
            par(){
              form(){
                navigate(viewBlogEntry(entry)){
                  text("Read more"){
                  }
                }
                " | "
                navigate(editBlogEntry(entry)){
                  text("Edit"){
                  }
                }
                " | "
                actionLink("Delete", delete(entry)){
                }
                action delete ( entry : BlogEntry )
                {
                  blog.entries.remove(entry);
                  blog.save();
                  return viewBlog(blog);
                }
              }
            }
          }
        }
      }
    }
  }

  define page viewBlogEntry (entry : BlogEntry) {
    main(){
    }
    define sidebar () {
      blogSidebar(entry.blog){
      }
    }
    define manageMenu () {
      navigate(editBlogEntry(entry)){
        text("Edit"){
        }
      }
    }
    define body () {
      title(){
        text(entry.title){
        }
      }
      section(){
        header(){
          text(entry.title){
          }
        }
        div("blogDate"){
          outputDate(entry.created){
          }
        }
        div("blogIntro"){
          outputText(entry.intro){
          }
        }
        div("blogBody"){
          outputText(entry.body){
          }
        }
        section(){
          header(){
            "Comments"
          }
          output(entry.comments){
          }
        }
      }
    }
    define blogEntries () {
      list(){
        for ( entry : BlogEntry in entry.blog.entries ) {
          listitem(){
            navigate(viewBlogEntry(entry)){
              text(entry.name){
              }
            }
          }
        }
      }
    }
  }

module colloquium
description
  {
  A colloquium is a series of presentations.
}
section domain .

  entity Colloquium {
    name :: String ( )
    description :: Text ( )
    contact -> Person ( )
    mailinglist :: Email ( )
    group -> ResearchGroup ( )
    projects -> List<ResearchProject> ( )
    presentations <> List<Presentation> ( )
  }

  entity Presentation {
    title :: String ( name )
    speaker -> Person ( )
    date :: Date ( )
    time :: Date ( )
    end :: Date ( )
    venue :: String ( )
    abstract :: Text ( )
    projects :: List<ResearchProject> ( )
  }
section pages .

  define page viewColloquium (colloquium : Colloquium) {
    main(){
    }
    define sidebar () {
      groupSidebar(colloquium.group){
      }
    }
    define manageMenu () {
      navigate(editColloquium(colloquium)){
        text("Edit"){
        }
      }
    }
    define body () {
      section(){
        header(){
          text(colloquium.name){
          }
        }
        outputText(colloquium.description){
        }
        section(){
          header(){
            "Contact"
          }
          "The colloquium is organized by "
          output(colloquium.contact){
          }
          ". "
          "Announcements for presentations are sent to the "
          output(colloquium.mailinglist){
          }
          " mailinglist."
        }
        section(){
          header(){
            "Upcoming Presentations"
          }
          for ( presentation : Presentation in colloquium.presentations ) {
            row(){
              text(presentation.date){
              }
              output(presentation){
              }
              output(presentation.speaker){
              }
            }
          }
        }
      }
    }
  }

module app/publications
description
  {
  Publications are published documents. There is a large variety of publication
  mediums, each of which is cited in different ways. BibTeX provides a good
  domain model, which should be modeled here.
}
section publications .

  entity Publication {
    title :: String ( name )
    subtitle :: String ( )
    year :: Int ( )
    pdf :: URL ( )
    authors -> List<Person> ( )
    abstract :: Text ( )
    projects -> Set<ResearchProject> ( )
  }

  entity PublicationFoo {
    title :: String ( name )
    subtitle :: String ( )
    year :: Int ( )
    pdf :: URL ( )
    authors -> List<Person> ( )
    abstract :: Text ( )
    projects -> Set<ResearchProject> ( )
  }

  entity TechnicalReport : Publication {
    number :: Int ( )
    document :: Text ( )
    preprintof -> Publication ( )
  }

  entity InProceedings : Publication {
    conference -> Conference ( )
    pages :: String ( )
  }

  entity Conference {
    fullname :: String ( )
    acronym :: String ( name )
    booktitle :: String ( )
    editors -> List<Person> ( )
    place :: String ( )
    year :: Int ( )
    month :: String ( )
    url :: URL ( )
    acceptance :: Int ( )
  }

  entity Article : Publication {
    journal -> Journal ( )
    pages :: String ( )
    impact :: Int ( )
  }

  entity Journal {
    fullname :: String ( )
    acronym :: String ( name )
  }
section presenting publications .

  define showPublication (pub : Publication) {
    for ( author : Person in pub.authors ) {
      navigate(viewPerson(author)){
        text(author.name){
        }
      }
      ", "
    }
    navigate(viewPublication(pub)){
      text(pub.name){
      }
    }
    ", "
    text(pub.year){
    }
    "."
  }

  define publicationsPage (publications : List<Publication>) {
    list(){
      for ( publication : Publication in publications ) {
        listitem(){
          showPublication(publication){
          }
        }
      }
    }
  }
section looking up publications .

  define publicationsBy (person : Person) {
    var orderedPublications : List<Publication> := select pub from Publication as pub , Person as pers where ( pers . id = person.id ) and ( pers member of pub . _authors ) order by pub . _year desc ;
    list(){
      for ( pub : Publication in orderedPublications ) {
        listitem(){
          showPublication(pub){
          }
        }
      }
    }
  }

  define publicationTitlesBy (person : Person) {
    var orderedPublications : List<Publication> := select pub from Publication as pub , Person as pers where ( pers . id = person.id ) and ( pers member of pub . _authors ) order by pub . _year desc ;
    list(){
      for ( pub : Publication in orderedPublications ) {
        listitem(){
          output(pub){
          }
          " ("
          text(pub.year){
          }
          ")"
        }
      }
    }
  }
section publication pages .

  define page personPublications (person : Person) {
    main(){
    }
    title(){
      "Publications by "
      text(person.name){
      }
    }
    define sidebar () {
      personSidebar(person){
      }
    }
    define body () {
      header(){
        "Publications by "
        text(person.name){
        }
      }
      publicationsBy(person){
      }
    }
  }
section editing publications .

  define editRowsPublication (publication : Publication) {
    row(){
      "Title"
      input(publication.title){
      }
    }
    row(){
      "Subtitle"
      input(publication.subtitle){
      }
    }
    row(){
      "Authors"
      input(publication.authors){
      }
    }
    row(){
      ""
      table(){
        var newAuthor : Person := Person{} ;
        row(){
          "Fullname"
          input(newAuthor.fullname){
          }
        }
        row(){
          "Email"
          input(newAuthor.email){
          }
        }
        row(){
          ""
          action("Add new author", addNewAuthor()){
          }
        }
        action addNewAuthor ( )
        {
          publication.authors.add(newAuthor);
          newAuthor := Person{};
        }
      }
    }
    row(){
      "Year"
      input(publication.year){
      }
    }
    row(){
      "Abstract"
      input(publication.abstract){
      }
    }
    row(){
      "Projects"
      input(publication.projects){
      }
    }
    row(){
      "Pdf"
      input(publication.pdf){
      }
    }
  }

module app/projects
section domain .

  entity ResearchProject {
    fullname :: String ( )
    acronym :: String ( name )
    description :: Text ( )
    members -> Set<Person> ( )
    proposal -> Publication ( )
    publications -> Set<Publication> ( )
  }
section view .

  define page viewResearchProjectMy (project : ResearchProject) {
    title(){
      text(project.fullname){
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(project.fullname){
          }
        }
        text(project.description){
        }
        section(){
          header(){
            "Members"
          }
          list(){
            for ( member : Person in project.membersList ) {
              listitem(){
                navigate(viewPerson(member)){
                  text(member.name){
                  }
                }
              }
            }
          }
        }
        section(){
          header(){
            "Publications"
          }
          publicationsPage(project.publicationsList){
          }
        }
      }
    }
    main(){
    }
  }
section looking up projects .

  define listProjects (person : Person) {
    var projects : List<ResearchProject> := select pr from ResearchProject as pr , Person as pers where ( pers . id = person.id ) and ( pers member of pr . _members ) ;
    list(){
      for ( project : ResearchProject in projects ) {
        listitem(){
          navigate(viewResearchProject(project)){
            text(project.fullname){
            }
            " ("
            text(project.acronym){
            }
            ")"
          }
        }
      }
    }
  }

  define listProjectAcronyms (person : Person) {
    var projects : List<ResearchProject> := select pr from ResearchProject as pr , Person as pers where ( pers . id = person.id ) and ( pers member of pr . _members ) ;
    list(){
      for ( pr : ResearchProject in projects ) {
        listitem(){
          output(pr){
          }
        }
      }
    }
  }

module app/initdb
description
  {
  This module defines an action to add some objects to the database.
}
section init database .

  define page initDatabase () {
    form(){
      action("Init Database", initDB()){
      }
    }
    action initDB ( )
    {
      var Mekelweg4 : Address := Address{street := "Mekelweg" city := "Delft" phone := "015"} ;
      var Ordina : Address := Address{street := "Ringwade 1" city := "Nieuwegein" phone := "030"} ;
      var EelcoVisser : Person := Person{fullname := "Eelco Visser" email := "visser@acm.org" address := Mekelweg4 homepage := "http://www.eelcovisser.net" photo := "/img/eelcovisser.jpg"} ;
      EelcoVisser.user := User{username := "EelcoVisser" password := "foo" person := EelcoVisser};
      var ArieVanDeursen : Person := Person{fullname := "Arie van Deursen" email := "A.vanDeursen@tudelft.nl" address := Mekelweg4 homepage := "http://www.st.ewi.tudelft.nl/~arie/" photo := "http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg"} ;
      var JosWarmer : Person := Person{fullname := "Jos Warmer" email := "jos@ordina.nl" address := Ordina homepage := "http://www.klasse.nl/who/cv-jos.html" photo := "http://www.klasse.nl/who/images/jos.gif"} ;
      var MoDSE : ResearchProject := ResearchProject{fullname := "Model-Driven Software Evolution" acronym := "MoDSE" members := {EelcoVisser, ArieVanDeursen, JosWarmer} description := "The promise of model-driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. The problem with model-driven engineering is that it can lead to a lock-in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model-driven approaches are no exception. What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. The first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain-specific modeling languages. After developing a number of systems using a particular meta-model, new patterns may be recognized that can be captured in a higher-level or richer meta-model. The second premise is that reengineering of legacy systems to the model-driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model-driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain-specific languages."} ;
      var GTTSE07 : Publication := Publication{title := "Domain-Specific Language Engineering" authors := [EelcoVisser] year := 2007 abstract := "The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment." projects := {MoDSE}} ;
      var MoDSE07 : Publication := Publication{title := "Model-Driven Software Evolution: A Research Agenda" authors := [ArieVanDeursen, JosWarmer, EelcoVisser] year := 2006 abstract := "Software systems need to evolve, and systems built using model-driven approaches are no exception.  What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model-based software systems and identify challenges to be addressed by research in this area." projects := {MoDSE}} ;
      MoDSE.proposal := MoDSE07;
      MoDSE.publications := {MoDSE07, GTTSE07};
      MoDSE.persist();
    }
  }

module groups
section domain .

  entity ResearchGroup {
    acronym :: String ( name )
    fullname :: String ( )
    mission :: Text ( )
    logo :: Image ( )
    members -> Set<Person> ( )
    projects -> Set<ResearchProject> ( )
    colloquia -> Set<Colloquium> ( )
    news -> List<News> ( )
  }
section group page .

  define groupSidebar (group : ResearchGroup) {
    list(){
      listitem(){
        navigate(viewResearchGroup(group)){
          text(group.acronym){
          }
        }
      }
      listitem(){
        navigate(groupMembers(group)){
          text("People"){
          }
        }
      }
      listitem(){
        navigate(groupPublications(group)){
          text("Publications"){
          }
        }
      }
      listitem(){
        navigate(groupProjects(group)){
          text("Projects"){
          }
        }
        list(){
          for ( project : ResearchProject in group.projectsList ) {
            listitem(){
              navigate(viewResearchProject(project)){
                text(project.name){
                }
              }
            }
          }
        }
      }
      listitem(){
        "Colloquia"
        list(){
          for ( coll : Colloquium in group.colloquiaList ) {
            listitem(){
              navigate(viewColloquium(coll)){
                text(coll.name){
                }
              }
            }
          }
        }
      }
    }
  }

  define groupTemplate (group : ResearchGroup) {
    main(){
    }
    define sidebar () {
      groupSidebar(group){
      }
    }
  }

  define page viewResearchGroup (group : ResearchGroup) {
    main(){
    }
    define sidebar () {
      groupSidebar(group){
      }
    }
    title(){
      text(group.acronym){
      }
    }
    define manageMenu () {
      navigate(editResearchGroup(group)){
        text("Edit"){
        }
      }
    }
    define body () {
      section(){
        header(){
          text(group.fullname){
          }
        }
        section(){
          header(){
            "Mission"
          }
          outputText(group.mission){
          }
        }
        section(){
          header(){
            "Recent Publications"
          }
          recentGroupPublications(group){
          }
        }
        section(){
          header(){
            "People"
          }
          output(group.members){
          }
        }
      }
    }
  }

  define page viewResearchGroupPlain (group : ResearchGroup) {
    section(){
      header(){
        text(group.fullname){
        }
      }
      section(){
        header(){
          "Mission"
        }
        outputText(group.mission){
        }
      }
      section(){
        header(){
          "Recent Publications"
        }
        recentGroupPublications(group){
        }
      }
      section(){
        header(){
          "People"
        }
        output(group.members){
        }
      }
    }
  }
section members .

  define page groupMembers (group : ResearchGroup) {
    main(){
    }
    define sidebar () {
      groupSidebar(group){
      }
    }
    define body () {
      section(){
        header(){
          "Group Members"
        }
        table(){
          for ( person : Person in group.membersList ) {
            row(){
              div("smallphoto"){
                image(person.photo){
                }
              }
              output(person){
              }
            }
          }
        }
      }
    }
  }
section projects. .

  define page groupProjects (group : ResearchGroup) {
    main(){
    }
    define sidebar () {
      groupSidebar(group){
      }
    }
    define body () {
      section(){
        header(){
          "Projects"
        }
        list(){
          for ( project : ResearchProject in group.projectsList ) {
            listitem(){
              navigate(viewResearchProject(project)){
                text(project.fullname){
                }
                " ("
                text(project.acronym){
                }
                ")"
              }
            }
          }
        }
      }
    }
  }
section publications .

  define page groupPublications (group : ResearchGroup) {
    main(){
    }
    define sidebar () {
      groupSidebar(group){
      }
    }
    define body () {
      section(){
        header(){
          "Publications by "
          text(group.acronym){
          }
          " Members"
        }
        allGroupPublications(group){
        }
      }
    }
  }

  define recentGroupPublications (group : ResearchGroup) {
    var publications : List<Publication> := select distinct pub from Publication as pub , Person as pers , ResearchGroup as g where ( g . id = group.id ) and ( ( pers member of g . _members ) and ( pers member of pub . _authors ) ) order by pub . _year desc ;
    list(){
      for ( pub : Publication in publications ) {
        listitem(){
          navigate(viewPublication(pub)){
            text(pub.name){
            }
          }
        }
      }
    }
  }

  define allGroupPublications (group : ResearchGroup) {
    list(){
      for ( pub : Publication ) {
        listitem(){
          navigate(viewPublication(pub)){
            text(pub.name){
            }
          }
        }
      }
    }
  }

module news
section domain .

  entity News {
    title :: String ( )
    text :: Text ( )
    date :: Date ( )
  }

module issues
description
  {
  Issue descriptions and assignments to people.
}
section domain .

  entity Issue {
    title :: String ( name )
    description :: Text ( )
    due :: Date ( )
    priority :: Int ( )
    issues -> Set<Issue> ( )
    assigned -> Set<Person> ( )
    status :: String ( )
  }

  entity Project : Issue {
  }

  entity Bug : Issue {
  }

  entity Task : Issue {
  }
section pages .

module app/forum
description
  {
  A forum is a collection of discussion threads.
}
section domain .

  entity Forum {
    title :: String ( name )
    discussions -> List<Discussion> ( )
  }

  entity Discussion {
    topic :: String ( name )
    author -> Person ( )
    posted :: Date ( )
    forum :: Forum ( back )
    text :: Text ( )
    replies <> List<Reply> ( inline )
  }

  entity Reply {
    subject :: String ( name )
    author -> Person ( )
    posted :: Date ( )
    discussion -> Discussion ( back )
    text :: Text ( )
  }

  entity Post {
    subject :: String ( name )
    author -> Person ( )
    posted :: Date ( )
    discussion -> Discussion ( back )
    text :: Text ( )
  }
note
  @acl Access control requirements: - the author of a reply is Person associated
  to the logged in User this may only be changed by an admin - a reply may only
  be edited by its author - a reply may be deleted by its author or by the
  author of the discussion
end
note
  Some forums have a notion of nested threads, i.e., replies can be replies to
  an earlier reply.
end
section pages .

  define page viewDiscussion (discussion : Discussion) {
    main(){
    }
    title(){
      text(discussion.forum.name){
      }
      " : "
      text(discussion.name){
      }
    }
    define body () {
      output(discussion.forum){
      }
      " Forum"
      section(){
        header(){
          text(discussion.name){
          }
        }
        "by "
        output(discussion.author){
        }
        " posted "
        output(discussion.posted){
        }
        par(){
          output(discussion.text){
          }
        }
        for ( reply : Reply in discussion.replies ) {
          div("reply"){
            section(){
              header(){
                output(reply.name){
                }
              }
              "by "
              output(reply.author){
              }
              " posted "
              output(reply.posted){
              }
              par(){
                output(reply.text){
                }
              }
              par(){
                form(){
                  navigate(editReply(reply)){
                    text("Edit"){
                    }
                  }
                  " | "
                  actionLink("Delete", delete(reply)){
                  }
                  action delete ( reply : Reply )
                  {
                    discussion.replies.remove(reply);
                  }
                }
              }
            }
          }
        }
        section(){
          header(){
            "Reply"
          }
          var newReply : Reply := Reply{discussion := discussion} ;
          form(){
            table(){
              row(){
                "Subject: "
                input(newReply.subject){
                }
              }
              row(){
                "Author: "
                input(newReply.author){
                }
              }
              row(){
                ""
                input(newReply.text){
                }
              }
            }
            action("Post", post()){
            }
            action post ( )
            {
              newReply.save();
              discussion.replies.add(newReply);
              newReply := Reply{discussion := discussion};
            }
          }
        }
      }
    }
  }

module app/shop
section s domain .

  entity Shop {
    name :: String ( name )
    products -> List<Product> ( )
    carts -> List<Cart> ( )
    first -> Product ( )
    last -> Product ( )
  }

  entity Product {
    name :: String ( )
    price :: Int ( )
    photo :: Image ( )
    shop -> Shop ( )
    previous -> Product ( )
    next -> Product ( )
  }

  entity Cart {
    shopper -> Person ( )
    products -> List<Product> ( )
  }
section s ideal template .

  define mainShopIdeal (shop : Shop) {
    main(){
    }
    define logo () {
      image("http://farm1.static.flickr.com/72/162651609_026c457b83_t.jpg"){
      }
    }
    define sidebar () {
    }
    define menu () {
      list(){
        listitem(){
          navigate(viewShop(shop)){
            text("Shop"){
            }
          }
          list(){
            listitem(){
              navigate(editShop(shop)){
                text("Edit"){
                }
              }
            }
            listitem(){
              form(){
                actionLink("Add Product", addProduct(shop)){
                }
              }
            }
          }
        }
      }
      list(){
        listitem(){
          "Photo"
          list(){
            photoMenu(){
            }
          }
        }
      }
    }
    action addProduct ( shop : Shop )
    {
      var p : Product := Product{} ;
      p.shop := shop;
      p.previous := shop.last;
      p.next := shop.first;
      shop.last.next := p;
      shop.last := p;
      shop.products.add(p);
      shop.persist();
      return editProduct(p);
    }
  }
section template . .

  define mainShop (shop : Shop) {
    div("outersidebar"){
      shopLogo(){
      }
      div("sidebar"){
        shopSidebar(){
        }
      }
    }
    div("outerbody"){
      div("menubar"){
        div("menu"){
          list(){
            listitem(){
              navigate(viewShop(shop)){
                text("Shop"){
                }
              }
              list(){
                listitem(){
                  navigate(editShop(shop)){
                    text("Edit"){
                    }
                  }
                }
                listitem(){
                  addPhoto(shop){
                  }
                }
              }
            }
          }
          list(){
            listitem(){
              "Photo"
              list(){
                photoMenu(){
                }
              }
            }
          }
          list(){
            listitem(){
              navigate(shopLogin(shop)){
                text("Login"){
                }
              }
              list(){
                loginMenu(){
                }
              }
            }
          }
          extraMenus(){
          }
        }
      }
      body(){
      }
      footer(){
      }
    }
  }

  define extraMenus () {
  }

  define loginMenu () {
  }

  define shopLogo () {
    image("http://farm1.static.flickr.com/72/162651609_026c457b83_t.jpg"){
    }
  }

  define addPhoto (shop : Shop) {
    form(){
      actionLink("Add Product", addProduct(shop)){
      }
    }
    action addProduct ( shop : Shop )
    {
      var p : Product := Product{} ;
      p.shop := shop;
      p.previous := shop.last;
      p.next := shop.first;
      shop.last.next := p;
      shop.last := p;
      shop.products.add(p);
      shop.persist();
      return editProduct(p);
    }
  }

  define photoMenu () {
  }
section shop page. .

  define page viewShop (shop : Shop) {
    mainShop(shop){
    }
    define body () {
      section(){
        header(){
          text(shop.name){
          }
        }
        section(){
          header(){
            "Start Browsing"
          }
          par(){
            navigate(viewProduct(shop.last)){
              image(shop.last.photo){
              }
            }
          }
          par(){
            navigate(viewProduct(shop.first)){
              image(shop.first.photo){
              }
            }
          }
        }
        section(){
          header(){
            "Catalogue"
          }
          output(shop.products){
          }
        }
      }
    }
  }
section photopage .

  define page viewProduct (product : Product) {
    mainShop(product.shop){
    }
    define photoMenu () {
      listitem(){
        navigate(editProduct(product)){
          text("Edit"){
          }
        }
      }
    }
    define body () {
      output(product.shop){
      }
      section(){
        header(){
          text(product.name){
          }
        }
        par(){
          image(product.photo){
          }
        }
        par(){
          navigate(viewProduct(product.previous)){
            text("&lt;&lt;"){
            }
          }
          " "
          navigate(viewProduct(product.next)){
            text("&gt;&gt;"){
            }
          }
        }
        par(){
          "Buy this photo for "
          text(product.price){
          }
        }
      }
    }
  }
section client .

  define page shopLogin (shop : Shop) {
    mainShop(shop){
    }
    var user : User := User{} ;
    define body () {
      form(){
        table(){
          row(){
            "username"
            input(user.username){
            }
          }
          row(){
            "password"
            input(user.password){
            }
          }
        }
        action("Login", login()){
        }
      }
    }
    action login ( )
    {
      return viewShop(shop);
    }
  }

module software
description
  {
  Meta-data about software products and releases.
}
section domain .

  entity SoftwareProduct {
    name :: String ( )
    description :: Text ( )
    releases -> List<SoftwareRelease> ( )
    lead -> Person ( )
    developers -> List<Person> ( )
    licence -> License ( )
  }

  entity SoftwareRelease {
    product -> SoftwareProduct ( )
    version :: String ( name )
    url :: URL ( )
    released :: Date ( )
    changes :: Text ( )
    contributors -> List<Person> ( )
  }

  entity License {
    acronym :: String ( name )
    text :: Text ( )
  }
section pages .

section home .

  define page home () {
    main(){
    }
    define sidebar () {
    }
    define body () {
      title(){
        "SERG Starting Points"
      }
      section(){
        header(){
          "Starting Points"
        }
        section(){
          header(){
            "Research Groups"
          }
          list(){
            for ( x : ResearchGroup ) {
              listitem(){
                output(x){
                }
              }
            }
          }
        }
        section(){
          header(){
            "Research Projects"
          }
          list(){
            for ( x : ResearchProject ) {
              listitem(){
                output(x){
                }
              }
            }
          }
        }
        section(){
          header(){
            "Forums"
          }
          list(){
            for ( x : Forum ) {
              listitem(){
                output(x){
                }
              }
            }
          }
        }
        section(){
          header(){
            "Blogs"
          }
          list(){
            for ( x : Blog ) {
              listitem(){
                output(x){
                }
              }
            }
          }
        }
        section(){
          header(){
            "Projects"
          }
          list(){
            for ( x : Project ) {
              listitem(){
                output(x){
                }
              }
            }
          }
        }
        section(){
          header(){
            "People"
          }
          list(){
            for ( x : Person ) {
              listitem(){
                output(x){
                }
              }
            }
          }
        }
      }
    }
  }

section generated pages. .

  define createMenu () {
    listitem(){
      "New"
      list(){
        listitem(){
          navigate(createLicense()){
            "License"
          }
        }
        listitem(){
          navigate(createSoftwareRelease()){
            "SoftwareRelease"
          }
        }
        listitem(){
          navigate(createSoftwareProduct()){
            "SoftwareProduct"
          }
        }
        listitem(){
          navigate(createCart()){
            "Cart"
          }
        }
        listitem(){
          navigate(createProduct()){
            "Product"
          }
        }
        listitem(){
          navigate(createShop()){
            "Shop"
          }
        }
        listitem(){
          navigate(createPost()){
            "Post"
          }
        }
        listitem(){
          navigate(createReply()){
            "Reply"
          }
        }
        listitem(){
          navigate(createDiscussion()){
            "Discussion"
          }
        }
        listitem(){
          navigate(createForum()){
            "Forum"
          }
        }
        listitem(){
          navigate(createTask()){
            "Task"
          }
        }
        listitem(){
          navigate(createBug()){
            "Bug"
          }
        }
        listitem(){
          navigate(createProject()){
            "Project"
          }
        }
        listitem(){
          navigate(createIssue()){
            "Issue"
          }
        }
        listitem(){
          navigate(createNews()){
            "News"
          }
        }
        listitem(){
          navigate(createResearchGroup()){
            "ResearchGroup"
          }
        }
        listitem(){
          navigate(createResearchProject()){
            "ResearchProject"
          }
        }
        listitem(){
          navigate(createJournal()){
            "Journal"
          }
        }
        listitem(){
          navigate(createArticle()){
            "Article"
          }
        }
        listitem(){
          navigate(createConference()){
            "Conference"
          }
        }
        listitem(){
          navigate(createInProceedings()){
            "InProceedings"
          }
        }
        listitem(){
          navigate(createTechnicalReport()){
            "TechnicalReport"
          }
        }
        listitem(){
          navigate(createPublicationFoo()){
            "PublicationFoo"
          }
        }
        listitem(){
          navigate(createPublication()){
            "Publication"
          }
        }
        listitem(){
          navigate(createPresentation()){
            "Presentation"
          }
        }
        listitem(){
          navigate(createColloquium()){
            "Colloquium"
          }
        }
        listitem(){
          navigate(createBlogComment()){
            "BlogComment"
          }
        }
        listitem(){
          navigate(createCategory()){
            "Category"
          }
        }
        listitem(){
          navigate(createBlogEntry()){
            "BlogEntry"
          }
        }
        listitem(){
          navigate(createBlog()){
            "Blog"
          }
        }
        listitem(){
          navigate(createPerson()){
            "Person"
          }
        }
        listitem(){
          navigate(createAddress()){
            "Address"
          }
        }
        listitem(){
          navigate(createUser()){
            "User"
          }
        }
      }
    }
  }

  define allMenu () {
    listitem(){
      "All"
      list(){
        listitem(){
          navigate(allLicense()){
            "License"
          }
        }
        listitem(){
          navigate(allSoftwareRelease()){
            "SoftwareRelease"
          }
        }
        listitem(){
          navigate(allSoftwareProduct()){
            "SoftwareProduct"
          }
        }
        listitem(){
          navigate(allCart()){
            "Cart"
          }
        }
        listitem(){
          navigate(allProduct()){
            "Product"
          }
        }
        listitem(){
          navigate(allShop()){
            "Shop"
          }
        }
        listitem(){
          navigate(allPost()){
            "Post"
          }
        }
        listitem(){
          navigate(allReply()){
            "Reply"
          }
        }
        listitem(){
          navigate(allDiscussion()){
            "Discussion"
          }
        }
        listitem(){
          navigate(allForum()){
            "Forum"
          }
        }
        listitem(){
          navigate(allTask()){
            "Task"
          }
        }
        listitem(){
          navigate(allBug()){
            "Bug"
          }
        }
        listitem(){
          navigate(allProject()){
            "Project"
          }
        }
        listitem(){
          navigate(allIssue()){
            "Issue"
          }
        }
        listitem(){
          navigate(allNews()){
            "News"
          }
        }
        listitem(){
          navigate(allResearchGroup()){
            "ResearchGroup"
          }
        }
        listitem(){
          navigate(allResearchProject()){
            "ResearchProject"
          }
        }
        listitem(){
          navigate(allJournal()){
            "Journal"
          }
        }
        listitem(){
          navigate(allArticle()){
            "Article"
          }
        }
        listitem(){
          navigate(allConference()){
            "Conference"
          }
        }
        listitem(){
          navigate(allInProceedings()){
            "InProceedings"
          }
        }
        listitem(){
          navigate(allTechnicalReport()){
            "TechnicalReport"
          }
        }
        listitem(){
          navigate(allPublicationFoo()){
            "PublicationFoo"
          }
        }
        listitem(){
          navigate(allPublication()){
            "Publication"
          }
        }
        listitem(){
          navigate(allPresentation()){
            "Presentation"
          }
        }
        listitem(){
          navigate(allColloquium()){
            "Colloquium"
          }
        }
        listitem(){
          navigate(allBlogComment()){
            "BlogComment"
          }
        }
        listitem(){
          navigate(allCategory()){
            "Category"
          }
        }
        listitem(){
          navigate(allBlogEntry()){
            "BlogEntry"
          }
        }
        listitem(){
          navigate(allBlog()){
            "Blog"
          }
        }
        listitem(){
          navigate(allPerson()){
            "Person"
          }
        }
        listitem(){
          navigate(allAddress()){
            "Address"
          }
        }
        listitem(){
          navigate(allUser()){
            "User"
          }
        }
      }
    }
  }

  define editRowsObject (o : Object) {
  }

  define viewRowsObject (o : Object) {
  }

  define page editUser (user : User) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "User"
          " "
          text(user.name){
          }
        }
        form(){
          table(){
            editRowsUser(user){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewUser(user);
      }
      action save ( )
      {
        user.save();
        return viewUser(user);
      }
    }
  }

  define page createUser () {
    var user : User := User{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "User"
        }
        form(){
          table(){
            editRowsUser(user){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        user.save();
        return viewUser(user);
      }
    }
  }

  define editRowsUser (user : User) {
    editRowsObject(user){
    }
    row(){
      "Username"
      input(user.username){
      }
    }
    row(){
      "Password"
      input(user.password){
      }
    }
    row(){
      "Person"
      input(user.person){
      }
    }
  }

  define page viewUser (user : User) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editUser(user)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(user.name){
          }
        }
        viewRowsUser(user){
        }
      }
    }
  }

  define viewRowsUser (user : User) {
    viewRowsObject(user){
    }
    par(){
      "Password"
      " : "
      output(user.password){
      }
    }
    section(){
      header(){
        "Person"
      }
      list(){
        listitem(){
          output(user.person){
          }
        }
      }
    }
  }

  define page allUser () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "User"
        }
        form(){
          list(){
            for ( user : User ) {
              listitem(){
                navigate(viewUser(user)){
                  text(user.name){
                  }
                }
                " "
                actionLink("[X]", removeUser(user)){
                }
                action removeUser ( user : User )
                {
                  user.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editAddress (address : Address) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Address"
          " "
          text(address.name){
          }
        }
        form(){
          table(){
            editRowsAddress(address){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewAddress(address);
      }
      action save ( )
      {
        address.save();
        return viewAddress(address);
      }
    }
  }

  define page createAddress () {
    var address : Address := Address{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Address"
        }
        form(){
          table(){
            editRowsAddress(address){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        address.save();
        return viewAddress(address);
      }
    }
  }

  define editRowsAddress (address : Address) {
    editRowsObject(address){
    }
    row(){
      "Street"
      input(address.street){
      }
    }
    row(){
      "City"
      input(address.city){
      }
    }
    row(){
      "Phone"
      input(address.phone){
      }
    }
  }

  define page viewAddress (address : Address) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editAddress(address)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(address.name){
          }
        }
        viewRowsAddress(address){
        }
      }
    }
  }

  define viewRowsAddress (address : Address) {
    viewRowsObject(address){
    }
    par(){
      "Street"
      " : "
      output(address.street){
      }
    }
    par(){
      "City"
      " : "
      output(address.city){
      }
    }
    par(){
      "Phone"
      " : "
      output(address.phone){
      }
    }
  }

  define page allAddress () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Address"
        }
        form(){
          list(){
            for ( address : Address ) {
              listitem(){
                navigate(viewAddress(address)){
                  text(address.name){
                  }
                }
                " "
                actionLink("[X]", removeAddress(address)){
                }
                action removeAddress ( address : Address )
                {
                  address.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editPerson (person : Person) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Person"
          " "
          text(person.name){
          }
        }
        form(){
          table(){
            editRowsPerson(person){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewPerson(person);
      }
      action save ( )
      {
        person.save();
        return viewPerson(person);
      }
    }
  }

  define page createPerson () {
    var person : Person := Person{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Person"
        }
        form(){
          table(){
            editRowsPerson(person){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        person.save();
        return viewPerson(person);
      }
    }
  }

  define editRowsPerson (person : Person) {
    editRowsObject(person){
    }
    row(){
      "Fullname"
      input(person.fullname){
      }
    }
    row(){
      "Email"
      input(person.email){
      }
    }
    row(){
      "Homepage"
      input(person.homepage){
      }
    }
    row(){
      "Photo"
      input(person.photo){
      }
    }
    row(){
      "Address"
      ""
    }
    row(){
      "Street"
      input(person.address.street){
      }
    }
    row(){
      "City"
      input(person.address.city){
      }
    }
    row(){
      "Phone"
      input(person.address.phone){
      }
    }
    row(){
      "User"
      input(person.user){
      }
    }
    row(){
      "Blog"
      input(person.blog){
      }
    }
  }

  define viewRowsPerson (person : Person) {
    viewRowsObject(person){
    }
    par(){
      "Email"
      " : "
      output(person.email){
      }
    }
    par(){
      "Homepage"
      " : "
      output(person.homepage){
      }
    }
    par(){
      "Photo"
      " : "
      output(person.photo){
      }
    }
    section(){
      header(){
        "Address"
      }
      par(){
        "Street"
        " : "
        output(person.address.street){
        }
      }
      par(){
        "City"
        " : "
        output(person.address.city){
        }
      }
      par(){
        "Phone"
        " : "
        output(person.address.phone){
        }
      }
    }
    section(){
      header(){
        "User"
      }
      list(){
        listitem(){
          output(person.user){
          }
        }
      }
    }
    section(){
      header(){
        "Blog"
      }
      list(){
        listitem(){
          output(person.blog){
          }
        }
      }
    }
  }

  define page allPerson () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Person"
        }
        form(){
          list(){
            for ( person : Person ) {
              listitem(){
                navigate(viewPerson(person)){
                  text(person.name){
                  }
                }
                " "
                actionLink("[X]", removePerson(person)){
                }
                action removePerson ( person : Person )
                {
                  person.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editBlog (blog : Blog) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Blog"
          " "
          text(blog.name){
          }
        }
        form(){
          table(){
            editRowsBlog(blog){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewBlog(blog);
      }
      action save ( )
      {
        blog.save();
        return viewBlog(blog);
      }
    }
  }

  define page createBlog () {
    var blog : Blog := Blog{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Blog"
        }
        form(){
          table(){
            editRowsBlog(blog){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        blog.save();
        return viewBlog(blog);
      }
    }
  }

  define editRowsBlog (blog : Blog) {
    editRowsObject(blog){
    }
    row(){
      "Title"
      input(blog.title){
      }
    }
    row(){
      "Author"
      input(blog.author){
      }
    }
    row(){
      "Entries"
      input(blog.entries){
      }
    }
    row(){
      "Categories"
      input(blog.categories){
      }
    }
  }

  define viewRowsBlog (blog : Blog) {
    viewRowsObject(blog){
    }
    section(){
      header(){
        "Author"
      }
      list(){
        listitem(){
          output(blog.author){
          }
        }
      }
    }
    section(){
      header(){
        "Entries"
      }
      for ( blogEntry : BlogEntry in blog.entriesList ) {
        section(){
          header(){
            output(blogEntry){
            }
          }
          viewRowsBlogEntry(blogEntry){
          }
        }
      }
      form(){
        actionLink("New BlogEntry", createNewEntriesBlogEntry(blog, blog.entries)){
        }
      }
      action createNewEntriesBlogEntry ( blog0 : Blog, entries : List<BlogEntry> )
      {
        var blogEntry0 : BlogEntry := BlogEntry{} ;
        entries.add(blogEntry0);
        blog0.persist();
        return editBlogEntry(blogEntry0);
      }
    }
    section(){
      header(){
        "Categories"
      }
      output(blog.categories){
      }
      form(){
        actionLink("New Category", createNewCategoriesCategory(blog, blog.categories)){
        }
      }
      action createNewCategoriesCategory ( blog1 : Blog, categories : List<Category> )
      {
        var category0 : Category := Category{} ;
        categories.add(category0);
        blog1.persist();
        return editCategory(category0);
      }
    }
  }

  define page allBlog () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Blog"
        }
        form(){
          list(){
            for ( blog : Blog ) {
              listitem(){
                navigate(viewBlog(blog)){
                  text(blog.name){
                  }
                }
                " "
                actionLink("[X]", removeBlog(blog)){
                }
                action removeBlog ( blog : Blog )
                {
                  blog.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editBlogEntry (blogEntry : BlogEntry) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "BlogEntry"
          " "
          text(blogEntry.name){
          }
        }
        form(){
          table(){
            editRowsBlogEntry(blogEntry){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewBlogEntry(blogEntry);
      }
      action save ( )
      {
        blogEntry.save();
        return viewBlogEntry(blogEntry);
      }
    }
  }

  define page createBlogEntry () {
    var blogEntry : BlogEntry := BlogEntry{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "BlogEntry"
        }
        form(){
          table(){
            editRowsBlogEntry(blogEntry){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        blogEntry.save();
        return viewBlogEntry(blogEntry);
      }
    }
  }

  define editRowsBlogEntry (blogEntry : BlogEntry) {
    editRowsObject(blogEntry){
    }
    row(){
      "Blog"
      input(blogEntry.blog){
      }
    }
    row(){
      "Title"
      input(blogEntry.title){
      }
    }
    row(){
      "Created"
      input(blogEntry.created){
      }
    }
    row(){
      "Category"
      input(blogEntry.category){
      }
    }
    row(){
      "Intro"
      input(blogEntry.intro){
      }
    }
    row(){
      "Body"
      input(blogEntry.body){
      }
    }
    row(){
      "Comments"
      input(blogEntry.comments){
      }
    }
  }

  define viewRowsBlogEntry (blogEntry : BlogEntry) {
    viewRowsObject(blogEntry){
    }
    section(){
      header(){
        "Blog"
      }
      list(){
        listitem(){
          output(blogEntry.blog){
          }
        }
      }
    }
    par(){
      "Created"
      " : "
      output(blogEntry.created){
      }
    }
    section(){
      header(){
        "Category"
      }
      list(){
        listitem(){
          output(blogEntry.category){
          }
        }
      }
    }
    section(){
      header(){
        "Intro"
      }
      output(blogEntry.intro){
      }
    }
    section(){
      header(){
        "Body"
      }
      output(blogEntry.body){
      }
    }
    section(){
      header(){
        "Comments"
      }
      for ( blogComment : BlogComment in blogEntry.commentsList ) {
        section(){
          header(){
            output(blogComment){
            }
          }
          viewRowsBlogComment(blogComment){
          }
        }
      }
      form(){
        actionLink("New BlogComment", createNewCommentsBlogComment(blogEntry, blogEntry.comments)){
        }
      }
      action createNewCommentsBlogComment ( blogEntry1 : BlogEntry, comments : List<BlogComment> )
      {
        var blogComment0 : BlogComment := BlogComment{} ;
        comments.add(blogComment0);
        blogEntry1.persist();
        return editBlogComment(blogComment0);
      }
    }
  }

  define page allBlogEntry () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "BlogEntry"
        }
        form(){
          list(){
            for ( blogEntry : BlogEntry ) {
              listitem(){
                navigate(viewBlogEntry(blogEntry)){
                  text(blogEntry.name){
                  }
                }
                " "
                actionLink("[X]", removeBlogEntry(blogEntry)){
                }
                action removeBlogEntry ( blogEntry : BlogEntry )
                {
                  blogEntry.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editCategory (category : Category) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Category"
          " "
          text(category.name){
          }
        }
        form(){
          table(){
            editRowsCategory(category){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewCategory(category);
      }
      action save ( )
      {
        category.save();
        return viewCategory(category);
      }
    }
  }

  define page createCategory () {
    var category : Category := Category{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Category"
        }
        form(){
          table(){
            editRowsCategory(category){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        category.save();
        return viewCategory(category);
      }
    }
  }

  define editRowsCategory (category : Category) {
    editRowsObject(category){
    }
    row(){
      "Name"
      input(category.name){
      }
    }
  }

  define page viewCategory (category : Category) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editCategory(category)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(category.name){
          }
        }
        viewRowsCategory(category){
        }
      }
    }
  }

  define viewRowsCategory (category : Category) {
    viewRowsObject(category){
    }
    par(){
      "Name"
      " : "
      output(category.name){
      }
    }
  }

  define page allCategory () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Category"
        }
        form(){
          list(){
            for ( category : Category ) {
              listitem(){
                navigate(viewCategory(category)){
                  text(category.name){
                  }
                }
                " "
                actionLink("[X]", removeCategory(category)){
                }
                action removeCategory ( category : Category )
                {
                  category.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editBlogComment (blogComment : BlogComment) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "BlogComment"
          " "
          text(blogComment.name){
          }
        }
        form(){
          table(){
            editRowsBlogComment(blogComment){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewBlogComment(blogComment);
      }
      action save ( )
      {
        blogComment.save();
        return viewBlogComment(blogComment);
      }
    }
  }

  define page createBlogComment () {
    var blogComment : BlogComment := BlogComment{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "BlogComment"
        }
        form(){
          table(){
            editRowsBlogComment(blogComment){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        blogComment.save();
        return viewBlogComment(blogComment);
      }
    }
  }

  define editRowsBlogComment (blogComment : BlogComment) {
    editRowsObject(blogComment){
    }
    row(){
      "Author"
      input(blogComment.author){
      }
    }
    row(){
      "Text"
      input(blogComment.text){
      }
    }
  }

  define page viewBlogComment (blogComment : BlogComment) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editBlogComment(blogComment)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(blogComment.name){
          }
        }
        viewRowsBlogComment(blogComment){
        }
      }
    }
  }

  define viewRowsBlogComment (blogComment : BlogComment) {
    viewRowsObject(blogComment){
    }
    section(){
      header(){
        "Author"
      }
      list(){
        listitem(){
          output(blogComment.author){
          }
        }
      }
    }
    section(){
      header(){
        "Text"
      }
      output(blogComment.text){
      }
    }
  }

  define page allBlogComment () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "BlogComment"
        }
        form(){
          list(){
            for ( blogComment : BlogComment ) {
              listitem(){
                navigate(viewBlogComment(blogComment)){
                  text(blogComment.name){
                  }
                }
                " "
                actionLink("[X]", removeBlogComment(blogComment)){
                }
                action removeBlogComment ( blogComment : BlogComment )
                {
                  blogComment.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editColloquium (colloquium : Colloquium) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Colloquium"
          " "
          text(colloquium.name){
          }
        }
        form(){
          table(){
            editRowsColloquium(colloquium){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewColloquium(colloquium);
      }
      action save ( )
      {
        colloquium.save();
        return viewColloquium(colloquium);
      }
    }
  }

  define page createColloquium () {
    var colloquium : Colloquium := Colloquium{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Colloquium"
        }
        form(){
          table(){
            editRowsColloquium(colloquium){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        colloquium.save();
        return viewColloquium(colloquium);
      }
    }
  }

  define editRowsColloquium (colloquium : Colloquium) {
    editRowsObject(colloquium){
    }
    row(){
      "Name"
      input(colloquium.name){
      }
    }
    row(){
      "Description"
      input(colloquium.description){
      }
    }
    row(){
      "Contact"
      input(colloquium.contact){
      }
    }
    row(){
      "Mailinglist"
      input(colloquium.mailinglist){
      }
    }
    row(){
      "Group"
      input(colloquium.group){
      }
    }
    row(){
      "Projects"
      input(colloquium.projects){
      }
    }
    row(){
      "Presentations"
      input(colloquium.presentations){
      }
    }
  }

  define viewRowsColloquium (colloquium : Colloquium) {
    viewRowsObject(colloquium){
    }
    par(){
      "Name"
      " : "
      output(colloquium.name){
      }
    }
    section(){
      header(){
        "Description"
      }
      output(colloquium.description){
      }
    }
    section(){
      header(){
        "Contact"
      }
      list(){
        listitem(){
          output(colloquium.contact){
          }
        }
      }
    }
    par(){
      "Mailinglist"
      " : "
      output(colloquium.mailinglist){
      }
    }
    section(){
      header(){
        "Group"
      }
      list(){
        listitem(){
          output(colloquium.group){
          }
        }
      }
    }
    section(){
      header(){
        "Projects"
      }
      output(colloquium.projects){
      }
      form(){
        actionLink("New ResearchProject", createNewProjectsResearchProject(colloquium, colloquium.projects)){
        }
      }
      action createNewProjectsResearchProject ( colloquium0 : Colloquium, projects : List<ResearchProject> )
      {
        var researchProject0 : ResearchProject := ResearchProject{} ;
        projects.add(researchProject0);
        colloquium0.persist();
        return editResearchProject(researchProject0);
      }
    }
    section(){
      header(){
        "Presentations"
      }
      for ( presentation : Presentation in colloquium.presentationsList ) {
        section(){
          header(){
            output(presentation){
            }
          }
          viewRowsPresentation(presentation){
          }
        }
      }
      form(){
        actionLink("New Presentation", createNewPresentationsPresentation(colloquium, colloquium.presentations)){
        }
      }
      action createNewPresentationsPresentation ( colloquium1 : Colloquium, presentations : List<Presentation> )
      {
        var presentation0 : Presentation := Presentation{} ;
        presentations.add(presentation0);
        colloquium1.persist();
        return editPresentation(presentation0);
      }
    }
  }

  define page allColloquium () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Colloquium"
        }
        form(){
          list(){
            for ( colloquium : Colloquium ) {
              listitem(){
                navigate(viewColloquium(colloquium)){
                  text(colloquium.name){
                  }
                }
                " "
                actionLink("[X]", removeColloquium(colloquium)){
                }
                action removeColloquium ( colloquium : Colloquium )
                {
                  colloquium.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editPresentation (presentation : Presentation) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Presentation"
          " "
          text(presentation.name){
          }
        }
        form(){
          table(){
            editRowsPresentation(presentation){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewPresentation(presentation);
      }
      action save ( )
      {
        presentation.save();
        return viewPresentation(presentation);
      }
    }
  }

  define page createPresentation () {
    var presentation : Presentation := Presentation{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Presentation"
        }
        form(){
          table(){
            editRowsPresentation(presentation){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        presentation.save();
        return viewPresentation(presentation);
      }
    }
  }

  define editRowsPresentation (presentation : Presentation) {
    editRowsObject(presentation){
    }
    row(){
      "Title"
      input(presentation.title){
      }
    }
    row(){
      "Speaker"
      input(presentation.speaker){
      }
    }
    row(){
      "Date"
      input(presentation.date){
      }
    }
    row(){
      "Time"
      input(presentation.time){
      }
    }
    row(){
      "End"
      input(presentation.end){
      }
    }
    row(){
      "Venue"
      input(presentation.venue){
      }
    }
    row(){
      "Abstract"
      input(presentation.abstract){
      }
    }
    row(){
      "Projects"
      input(presentation.projects){
      }
    }
  }

  define page viewPresentation (presentation : Presentation) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editPresentation(presentation)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(presentation.name){
          }
        }
        viewRowsPresentation(presentation){
        }
      }
    }
  }

  define viewRowsPresentation (presentation : Presentation) {
    viewRowsObject(presentation){
    }
    section(){
      header(){
        "Speaker"
      }
      list(){
        listitem(){
          output(presentation.speaker){
          }
        }
      }
    }
    par(){
      "Date"
      " : "
      output(presentation.date){
      }
    }
    par(){
      "Time"
      " : "
      output(presentation.time){
      }
    }
    par(){
      "End"
      " : "
      output(presentation.end){
      }
    }
    par(){
      "Venue"
      " : "
      output(presentation.venue){
      }
    }
    section(){
      header(){
        "Abstract"
      }
      output(presentation.abstract){
      }
    }
    par(){
      "Projects"
      " : "
      output(presentation.projects){
      }
    }
  }

  define page allPresentation () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Presentation"
        }
        form(){
          list(){
            for ( presentation : Presentation ) {
              listitem(){
                navigate(viewPresentation(presentation)){
                  text(presentation.name){
                  }
                }
                " "
                actionLink("[X]", removePresentation(presentation)){
                }
                action removePresentation ( presentation : Presentation )
                {
                  presentation.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editPublication (publication : Publication) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Publication"
          " "
          text(publication.name){
          }
        }
        form(){
          table(){
            editRowsPublication(publication){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewPublication(publication);
      }
      action save ( )
      {
        publication.save();
        return viewPublication(publication);
      }
    }
  }

  define page createPublication () {
    var publication : Publication := Publication{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Publication"
        }
        form(){
          table(){
            editRowsPublication(publication){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        publication.save();
        return viewPublication(publication);
      }
    }
  }

  define page viewPublication (publication : Publication) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editPublication(publication)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(publication.name){
          }
        }
        viewRowsPublication(publication){
        }
      }
    }
  }

  define viewRowsPublication (publication : Publication) {
    viewRowsObject(publication){
    }
    par(){
      "Subtitle"
      " : "
      output(publication.subtitle){
      }
    }
    par(){
      "Year"
      " : "
      output(publication.year){
      }
    }
    par(){
      "Pdf"
      " : "
      output(publication.pdf){
      }
    }
    section(){
      header(){
        "Authors"
      }
      output(publication.authors){
      }
      form(){
        actionLink("New Person", createNewAuthorsPerson(publication, publication.authors)){
        }
      }
      action createNewAuthorsPerson ( publication0 : Publication, authors : List<Person> )
      {
        var person0 : Person := Person{} ;
        authors.add(person0);
        publication0.persist();
        return editPerson(person0);
      }
    }
    section(){
      header(){
        "Abstract"
      }
      output(publication.abstract){
      }
    }
    section(){
      header(){
        "Projects"
      }
      output(publication.projects){
      }
      form(){
        actionLink("New ResearchProject", createNewProjectsResearchProject(publication, publication.projects)){
        }
      }
      action createNewProjectsResearchProject ( publication1 : Publication, projects : Set<ResearchProject> )
      {
        var researchProject1 : ResearchProject := ResearchProject{} ;
        projects.add(researchProject1);
        publication1.persist();
        return editResearchProject(researchProject1);
      }
    }
  }

  define page allPublication () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Publication"
        }
        form(){
          list(){
            for ( publication : Publication ) {
              listitem(){
                navigate(viewPublication(publication)){
                  text(publication.name){
                  }
                }
                " "
                actionLink("[X]", removePublication(publication)){
                }
                action removePublication ( publication : Publication )
                {
                  publication.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editPublicationFoo (publicationFoo : PublicationFoo) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "PublicationFoo"
          " "
          text(publicationFoo.name){
          }
        }
        form(){
          table(){
            editRowsPublicationFoo(publicationFoo){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewPublicationFoo(publicationFoo);
      }
      action save ( )
      {
        publicationFoo.save();
        return viewPublicationFoo(publicationFoo);
      }
    }
  }

  define page createPublicationFoo () {
    var publicationFoo : PublicationFoo := PublicationFoo{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "PublicationFoo"
        }
        form(){
          table(){
            editRowsPublicationFoo(publicationFoo){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        publicationFoo.save();
        return viewPublicationFoo(publicationFoo);
      }
    }
  }

  define editRowsPublicationFoo (publicationFoo : PublicationFoo) {
    editRowsObject(publicationFoo){
    }
    row(){
      "Title"
      input(publicationFoo.title){
      }
    }
    row(){
      "Subtitle"
      input(publicationFoo.subtitle){
      }
    }
    row(){
      "Year"
      input(publicationFoo.year){
      }
    }
    row(){
      "Pdf"
      input(publicationFoo.pdf){
      }
    }
    row(){
      "Authors"
      input(publicationFoo.authors){
      }
    }
    row(){
      "Abstract"
      input(publicationFoo.abstract){
      }
    }
    row(){
      "Projects"
      input(publicationFoo.projects){
      }
    }
  }

  define page viewPublicationFoo (publicationFoo : PublicationFoo) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editPublicationFoo(publicationFoo)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(publicationFoo.name){
          }
        }
        viewRowsPublicationFoo(publicationFoo){
        }
      }
    }
  }

  define viewRowsPublicationFoo (publicationFoo : PublicationFoo) {
    viewRowsObject(publicationFoo){
    }
    par(){
      "Subtitle"
      " : "
      output(publicationFoo.subtitle){
      }
    }
    par(){
      "Year"
      " : "
      output(publicationFoo.year){
      }
    }
    par(){
      "Pdf"
      " : "
      output(publicationFoo.pdf){
      }
    }
    section(){
      header(){
        "Authors"
      }
      output(publicationFoo.authors){
      }
      form(){
        actionLink("New Person", createNewAuthorsPerson(publicationFoo, publicationFoo.authors)){
        }
      }
      action createNewAuthorsPerson ( publicationFoo0 : PublicationFoo, authors : List<Person> )
      {
        var person1 : Person := Person{} ;
        authors.add(person1);
        publicationFoo0.persist();
        return editPerson(person1);
      }
    }
    section(){
      header(){
        "Abstract"
      }
      output(publicationFoo.abstract){
      }
    }
    section(){
      header(){
        "Projects"
      }
      output(publicationFoo.projects){
      }
      form(){
        actionLink("New ResearchProject", createNewProjectsResearchProject(publicationFoo, publicationFoo.projects)){
        }
      }
      action createNewProjectsResearchProject ( publicationFoo1 : PublicationFoo, projects : Set<ResearchProject> )
      {
        var researchProject2 : ResearchProject := ResearchProject{} ;
        projects.add(researchProject2);
        publicationFoo1.persist();
        return editResearchProject(researchProject2);
      }
    }
  }

  define page allPublicationFoo () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "PublicationFoo"
        }
        form(){
          list(){
            for ( publicationFoo : PublicationFoo ) {
              listitem(){
                navigate(viewPublicationFoo(publicationFoo)){
                  text(publicationFoo.name){
                  }
                }
                " "
                actionLink("[X]", removePublicationFoo(publicationFoo)){
                }
                action removePublicationFoo ( publicationFoo : PublicationFoo )
                {
                  publicationFoo.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editTechnicalReport (technicalReport : TechnicalReport) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "TechnicalReport"
          " "
          text(technicalReport.name){
          }
        }
        form(){
          table(){
            editRowsTechnicalReport(technicalReport){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewTechnicalReport(technicalReport);
      }
      action save ( )
      {
        technicalReport.save();
        return viewTechnicalReport(technicalReport);
      }
    }
  }

  define page createTechnicalReport () {
    var technicalReport : TechnicalReport := TechnicalReport{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "TechnicalReport"
        }
        form(){
          table(){
            editRowsTechnicalReport(technicalReport){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        technicalReport.save();
        return viewTechnicalReport(technicalReport);
      }
    }
  }

  define editRowsTechnicalReport (technicalReport : TechnicalReport) {
    editRowsPublication(technicalReport){
    }
    row(){
      "Number"
      input(technicalReport.number){
      }
    }
    row(){
      "Document"
      input(technicalReport.document){
      }
    }
    row(){
      "Preprintof"
      input(technicalReport.preprintof){
      }
    }
  }

  define page viewTechnicalReport (technicalReport : TechnicalReport) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editTechnicalReport(technicalReport)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(technicalReport.name){
          }
        }
        viewRowsTechnicalReport(technicalReport){
        }
      }
    }
  }

  define viewRowsTechnicalReport (technicalReport : TechnicalReport) {
    viewRowsPublication(technicalReport){
    }
    par(){
      "Number"
      " : "
      output(technicalReport.number){
      }
    }
    section(){
      header(){
        "Document"
      }
      output(technicalReport.document){
      }
    }
    section(){
      header(){
        "Preprintof"
      }
      list(){
        listitem(){
          output(technicalReport.preprintof){
          }
        }
      }
    }
  }

  define page allTechnicalReport () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "TechnicalReport"
        }
        form(){
          list(){
            for ( technicalReport : TechnicalReport ) {
              listitem(){
                navigate(viewTechnicalReport(technicalReport)){
                  text(technicalReport.name){
                  }
                }
                " "
                actionLink("[X]", removeTechnicalReport(technicalReport)){
                }
                action removeTechnicalReport ( technicalReport : TechnicalReport )
                {
                  technicalReport.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editInProceedings (inProceedings : InProceedings) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "InProceedings"
          " "
          text(inProceedings.name){
          }
        }
        form(){
          table(){
            editRowsInProceedings(inProceedings){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewInProceedings(inProceedings);
      }
      action save ( )
      {
        inProceedings.save();
        return viewInProceedings(inProceedings);
      }
    }
  }

  define page createInProceedings () {
    var inProceedings : InProceedings := InProceedings{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "InProceedings"
        }
        form(){
          table(){
            editRowsInProceedings(inProceedings){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        inProceedings.save();
        return viewInProceedings(inProceedings);
      }
    }
  }

  define editRowsInProceedings (inProceedings : InProceedings) {
    editRowsPublication(inProceedings){
    }
    row(){
      "Conference"
      input(inProceedings.conference){
      }
    }
    row(){
      "Pages"
      input(inProceedings.pages){
      }
    }
  }

  define page viewInProceedings (inProceedings : InProceedings) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editInProceedings(inProceedings)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(inProceedings.name){
          }
        }
        viewRowsInProceedings(inProceedings){
        }
      }
    }
  }

  define viewRowsInProceedings (inProceedings : InProceedings) {
    viewRowsPublication(inProceedings){
    }
    section(){
      header(){
        "Conference"
      }
      list(){
        listitem(){
          output(inProceedings.conference){
          }
        }
      }
    }
    par(){
      "Pages"
      " : "
      output(inProceedings.pages){
      }
    }
  }

  define page allInProceedings () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "InProceedings"
        }
        form(){
          list(){
            for ( inProceedings : InProceedings ) {
              listitem(){
                navigate(viewInProceedings(inProceedings)){
                  text(inProceedings.name){
                  }
                }
                " "
                actionLink("[X]", removeInProceedings(inProceedings)){
                }
                action removeInProceedings ( inProceedings : InProceedings )
                {
                  inProceedings.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editConference (conference : Conference) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Conference"
          " "
          text(conference.name){
          }
        }
        form(){
          table(){
            editRowsConference(conference){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewConference(conference);
      }
      action save ( )
      {
        conference.save();
        return viewConference(conference);
      }
    }
  }

  define page createConference () {
    var conference : Conference := Conference{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Conference"
        }
        form(){
          table(){
            editRowsConference(conference){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        conference.save();
        return viewConference(conference);
      }
    }
  }

  define editRowsConference (conference : Conference) {
    editRowsObject(conference){
    }
    row(){
      "Fullname"
      input(conference.fullname){
      }
    }
    row(){
      "Acronym"
      input(conference.acronym){
      }
    }
    row(){
      "Booktitle"
      input(conference.booktitle){
      }
    }
    row(){
      "Editors"
      input(conference.editors){
      }
    }
    row(){
      "Place"
      input(conference.place){
      }
    }
    row(){
      "Year"
      input(conference.year){
      }
    }
    row(){
      "Month"
      input(conference.month){
      }
    }
    row(){
      "Url"
      input(conference.url){
      }
    }
    row(){
      "Acceptance"
      input(conference.acceptance){
      }
    }
  }

  define page viewConference (conference : Conference) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editConference(conference)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(conference.name){
          }
        }
        viewRowsConference(conference){
        }
      }
    }
  }

  define viewRowsConference (conference : Conference) {
    viewRowsObject(conference){
    }
    par(){
      "Fullname"
      " : "
      output(conference.fullname){
      }
    }
    par(){
      "Booktitle"
      " : "
      output(conference.booktitle){
      }
    }
    section(){
      header(){
        "Editors"
      }
      output(conference.editors){
      }
      form(){
        actionLink("New Person", createNewEditorsPerson(conference, conference.editors)){
        }
      }
      action createNewEditorsPerson ( conference0 : Conference, editors : List<Person> )
      {
        var person2 : Person := Person{} ;
        editors.add(person2);
        conference0.persist();
        return editPerson(person2);
      }
    }
    par(){
      "Place"
      " : "
      output(conference.place){
      }
    }
    par(){
      "Year"
      " : "
      output(conference.year){
      }
    }
    par(){
      "Month"
      " : "
      output(conference.month){
      }
    }
    par(){
      "Url"
      " : "
      output(conference.url){
      }
    }
    par(){
      "Acceptance"
      " : "
      output(conference.acceptance){
      }
    }
  }

  define page allConference () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Conference"
        }
        form(){
          list(){
            for ( conference : Conference ) {
              listitem(){
                navigate(viewConference(conference)){
                  text(conference.name){
                  }
                }
                " "
                actionLink("[X]", removeConference(conference)){
                }
                action removeConference ( conference : Conference )
                {
                  conference.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editArticle (article : Article) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Article"
          " "
          text(article.name){
          }
        }
        form(){
          table(){
            editRowsArticle(article){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewArticle(article);
      }
      action save ( )
      {
        article.save();
        return viewArticle(article);
      }
    }
  }

  define page createArticle () {
    var article : Article := Article{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Article"
        }
        form(){
          table(){
            editRowsArticle(article){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        article.save();
        return viewArticle(article);
      }
    }
  }

  define editRowsArticle (article : Article) {
    editRowsPublication(article){
    }
    row(){
      "Journal"
      input(article.journal){
      }
    }
    row(){
      "Pages"
      input(article.pages){
      }
    }
    row(){
      "Impact"
      input(article.impact){
      }
    }
  }

  define page viewArticle (article : Article) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editArticle(article)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(article.name){
          }
        }
        viewRowsArticle(article){
        }
      }
    }
  }

  define viewRowsArticle (article : Article) {
    viewRowsPublication(article){
    }
    section(){
      header(){
        "Journal"
      }
      list(){
        listitem(){
          output(article.journal){
          }
        }
      }
    }
    par(){
      "Pages"
      " : "
      output(article.pages){
      }
    }
    par(){
      "Impact"
      " : "
      output(article.impact){
      }
    }
  }

  define page allArticle () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Article"
        }
        form(){
          list(){
            for ( article : Article ) {
              listitem(){
                navigate(viewArticle(article)){
                  text(article.name){
                  }
                }
                " "
                actionLink("[X]", removeArticle(article)){
                }
                action removeArticle ( article : Article )
                {
                  article.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editJournal (journal : Journal) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Journal"
          " "
          text(journal.name){
          }
        }
        form(){
          table(){
            editRowsJournal(journal){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewJournal(journal);
      }
      action save ( )
      {
        journal.save();
        return viewJournal(journal);
      }
    }
  }

  define page createJournal () {
    var journal : Journal := Journal{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Journal"
        }
        form(){
          table(){
            editRowsJournal(journal){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        journal.save();
        return viewJournal(journal);
      }
    }
  }

  define editRowsJournal (journal : Journal) {
    editRowsObject(journal){
    }
    row(){
      "Fullname"
      input(journal.fullname){
      }
    }
    row(){
      "Acronym"
      input(journal.acronym){
      }
    }
  }

  define page viewJournal (journal : Journal) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editJournal(journal)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(journal.name){
          }
        }
        viewRowsJournal(journal){
        }
      }
    }
  }

  define viewRowsJournal (journal : Journal) {
    viewRowsObject(journal){
    }
    par(){
      "Fullname"
      " : "
      output(journal.fullname){
      }
    }
  }

  define page allJournal () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Journal"
        }
        form(){
          list(){
            for ( journal : Journal ) {
              listitem(){
                navigate(viewJournal(journal)){
                  text(journal.name){
                  }
                }
                " "
                actionLink("[X]", removeJournal(journal)){
                }
                action removeJournal ( journal : Journal )
                {
                  journal.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editResearchProject (researchProject : ResearchProject) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "ResearchProject"
          " "
          text(researchProject.name){
          }
        }
        form(){
          table(){
            editRowsResearchProject(researchProject){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewResearchProject(researchProject);
      }
      action save ( )
      {
        researchProject.save();
        return viewResearchProject(researchProject);
      }
    }
  }

  define page createResearchProject () {
    var researchProject : ResearchProject := ResearchProject{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "ResearchProject"
        }
        form(){
          table(){
            editRowsResearchProject(researchProject){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        researchProject.save();
        return viewResearchProject(researchProject);
      }
    }
  }

  define editRowsResearchProject (researchProject : ResearchProject) {
    editRowsObject(researchProject){
    }
    row(){
      "Fullname"
      input(researchProject.fullname){
      }
    }
    row(){
      "Acronym"
      input(researchProject.acronym){
      }
    }
    row(){
      "Description"
      input(researchProject.description){
      }
    }
    row(){
      "Members"
      input(researchProject.members){
      }
    }
    row(){
      "Proposal"
      input(researchProject.proposal){
      }
    }
    row(){
      "Publications"
      input(researchProject.publications){
      }
    }
  }

  define page viewResearchProject (researchProject : ResearchProject) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editResearchProject(researchProject)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(researchProject.name){
          }
        }
        viewRowsResearchProject(researchProject){
        }
      }
    }
  }

  define viewRowsResearchProject (researchProject : ResearchProject) {
    viewRowsObject(researchProject){
    }
    par(){
      "Fullname"
      " : "
      output(researchProject.fullname){
      }
    }
    section(){
      header(){
        "Description"
      }
      output(researchProject.description){
      }
    }
    section(){
      header(){
        "Members"
      }
      output(researchProject.members){
      }
      form(){
        actionLink("New Person", createNewMembersPerson(researchProject, researchProject.members)){
        }
      }
      action createNewMembersPerson ( researchProject3 : ResearchProject, members : Set<Person> )
      {
        var person3 : Person := Person{} ;
        members.add(person3);
        researchProject3.persist();
        return editPerson(person3);
      }
    }
    section(){
      header(){
        "Proposal"
      }
      list(){
        listitem(){
          output(researchProject.proposal){
          }
        }
      }
    }
    section(){
      header(){
        "Publications"
      }
      output(researchProject.publications){
      }
      form(){
        actionLink("New Publication", createNewPublicationsPublication(researchProject, researchProject.publications)){
        }
      }
      action createNewPublicationsPublication ( researchProject4 : ResearchProject, publications : Set<Publication> )
      {
        var publication2 : Publication := Publication{} ;
        publications.add(publication2);
        researchProject4.persist();
        return editPublication(publication2);
      }
    }
  }

  define page allResearchProject () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "ResearchProject"
        }
        form(){
          list(){
            for ( researchProject : ResearchProject ) {
              listitem(){
                navigate(viewResearchProject(researchProject)){
                  text(researchProject.name){
                  }
                }
                " "
                actionLink("[X]", removeResearchProject(researchProject)){
                }
                action removeResearchProject ( researchProject : ResearchProject )
                {
                  researchProject.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editResearchGroup (researchGroup : ResearchGroup) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "ResearchGroup"
          " "
          text(researchGroup.name){
          }
        }
        form(){
          table(){
            editRowsResearchGroup(researchGroup){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewResearchGroup(researchGroup);
      }
      action save ( )
      {
        researchGroup.save();
        return viewResearchGroup(researchGroup);
      }
    }
  }

  define page createResearchGroup () {
    var researchGroup : ResearchGroup := ResearchGroup{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "ResearchGroup"
        }
        form(){
          table(){
            editRowsResearchGroup(researchGroup){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        researchGroup.save();
        return viewResearchGroup(researchGroup);
      }
    }
  }

  define editRowsResearchGroup (researchGroup : ResearchGroup) {
    editRowsObject(researchGroup){
    }
    row(){
      "Acronym"
      input(researchGroup.acronym){
      }
    }
    row(){
      "Fullname"
      input(researchGroup.fullname){
      }
    }
    row(){
      "Mission"
      input(researchGroup.mission){
      }
    }
    row(){
      "Logo"
      input(researchGroup.logo){
      }
    }
    row(){
      "Members"
      input(researchGroup.members){
      }
    }
    row(){
      "Projects"
      input(researchGroup.projects){
      }
    }
    row(){
      "Colloquia"
      input(researchGroup.colloquia){
      }
    }
    row(){
      "News"
      input(researchGroup.news){
      }
    }
  }

  define viewRowsResearchGroup (researchGroup : ResearchGroup) {
    viewRowsObject(researchGroup){
    }
    par(){
      "Fullname"
      " : "
      output(researchGroup.fullname){
      }
    }
    section(){
      header(){
        "Mission"
      }
      output(researchGroup.mission){
      }
    }
    par(){
      "Logo"
      " : "
      output(researchGroup.logo){
      }
    }
    section(){
      header(){
        "Members"
      }
      output(researchGroup.members){
      }
      form(){
        actionLink("New Person", createNewMembersPerson(researchGroup, researchGroup.members)){
        }
      }
      action createNewMembersPerson ( researchGroup0 : ResearchGroup, members : Set<Person> )
      {
        var person4 : Person := Person{} ;
        members.add(person4);
        researchGroup0.persist();
        return editPerson(person4);
      }
    }
    section(){
      header(){
        "Projects"
      }
      output(researchGroup.projects){
      }
      form(){
        actionLink("New ResearchProject", createNewProjectsResearchProject(researchGroup, researchGroup.projects)){
        }
      }
      action createNewProjectsResearchProject ( researchGroup1 : ResearchGroup, projects : Set<ResearchProject> )
      {
        var researchProject5 : ResearchProject := ResearchProject{} ;
        projects.add(researchProject5);
        researchGroup1.persist();
        return editResearchProject(researchProject5);
      }
    }
    section(){
      header(){
        "Colloquia"
      }
      output(researchGroup.colloquia){
      }
      form(){
        actionLink("New Colloquium", createNewColloquiaColloquium(researchGroup, researchGroup.colloquia)){
        }
      }
      action createNewColloquiaColloquium ( researchGroup2 : ResearchGroup, colloquia : Set<Colloquium> )
      {
        var colloquium2 : Colloquium := Colloquium{} ;
        colloquia.add(colloquium2);
        researchGroup2.persist();
        return editColloquium(colloquium2);
      }
    }
    section(){
      header(){
        "News"
      }
      output(researchGroup.news){
      }
      form(){
        actionLink("New News", createNewNewsNews(researchGroup, researchGroup.news)){
        }
      }
      action createNewNewsNews ( researchGroup3 : ResearchGroup, news : List<News> )
      {
        var news0 : News := News{} ;
        news.add(news0);
        researchGroup3.persist();
        return editNews(news0);
      }
    }
  }

  define page allResearchGroup () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "ResearchGroup"
        }
        form(){
          list(){
            for ( researchGroup : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(researchGroup)){
                  text(researchGroup.name){
                  }
                }
                " "
                actionLink("[X]", removeResearchGroup(researchGroup)){
                }
                action removeResearchGroup ( researchGroup : ResearchGroup )
                {
                  researchGroup.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editNews (news : News) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "News"
          " "
          text(news.name){
          }
        }
        form(){
          table(){
            editRowsNews(news){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewNews(news);
      }
      action save ( )
      {
        news.save();
        return viewNews(news);
      }
    }
  }

  define page createNews () {
    var news : News := News{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "News"
        }
        form(){
          table(){
            editRowsNews(news){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        news.save();
        return viewNews(news);
      }
    }
  }

  define editRowsNews (news : News) {
    editRowsObject(news){
    }
    row(){
      "Title"
      input(news.title){
      }
    }
    row(){
      "Text"
      input(news.text){
      }
    }
    row(){
      "Date"
      input(news.date){
      }
    }
  }

  define page viewNews (news : News) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editNews(news)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(news.name){
          }
        }
        viewRowsNews(news){
        }
      }
    }
  }

  define viewRowsNews (news : News) {
    viewRowsObject(news){
    }
    par(){
      "Title"
      " : "
      output(news.title){
      }
    }
    section(){
      header(){
        "Text"
      }
      output(news.text){
      }
    }
    par(){
      "Date"
      " : "
      output(news.date){
      }
    }
  }

  define page allNews () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "News"
        }
        form(){
          list(){
            for ( news : News ) {
              listitem(){
                navigate(viewNews(news)){
                  text(news.name){
                  }
                }
                " "
                actionLink("[X]", removeNews(news)){
                }
                action removeNews ( news : News )
                {
                  news.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editIssue (issue : Issue) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Issue"
          " "
          text(issue.name){
          }
        }
        form(){
          table(){
            editRowsIssue(issue){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewIssue(issue);
      }
      action save ( )
      {
        issue.save();
        return viewIssue(issue);
      }
    }
  }

  define page createIssue () {
    var issue : Issue := Issue{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Issue"
        }
        form(){
          table(){
            editRowsIssue(issue){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        issue.save();
        return viewIssue(issue);
      }
    }
  }

  define editRowsIssue (issue : Issue) {
    editRowsObject(issue){
    }
    row(){
      "Title"
      input(issue.title){
      }
    }
    row(){
      "Description"
      input(issue.description){
      }
    }
    row(){
      "Due"
      input(issue.due){
      }
    }
    row(){
      "Priority"
      input(issue.priority){
      }
    }
    row(){
      "Issues"
      input(issue.issues){
      }
    }
    row(){
      "Assigned"
      input(issue.assigned){
      }
    }
    row(){
      "Status"
      input(issue.status){
      }
    }
  }

  define page viewIssue (issue : Issue) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editIssue(issue)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(issue.name){
          }
        }
        viewRowsIssue(issue){
        }
      }
    }
  }

  define viewRowsIssue (issue : Issue) {
    viewRowsObject(issue){
    }
    section(){
      header(){
        "Description"
      }
      output(issue.description){
      }
    }
    par(){
      "Due"
      " : "
      output(issue.due){
      }
    }
    par(){
      "Priority"
      " : "
      output(issue.priority){
      }
    }
    section(){
      header(){
        "Issues"
      }
      output(issue.issues){
      }
      form(){
        actionLink("New Issue", createNewIssuesIssue(issue, issue.issues)){
        }
      }
      action createNewIssuesIssue ( issue1 : Issue, issues : Set<Issue> )
      {
        var issue0 : Issue := Issue{} ;
        issues.add(issue0);
        issue1.persist();
        return editIssue(issue0);
      }
    }
    section(){
      header(){
        "Assigned"
      }
      output(issue.assigned){
      }
      form(){
        actionLink("New Person", createNewAssignedPerson(issue, issue.assigned)){
        }
      }
      action createNewAssignedPerson ( issue2 : Issue, assigned : Set<Person> )
      {
        var person5 : Person := Person{} ;
        assigned.add(person5);
        issue2.persist();
        return editPerson(person5);
      }
    }
    par(){
      "Status"
      " : "
      output(issue.status){
      }
    }
  }

  define page allIssue () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Issue"
        }
        form(){
          list(){
            for ( issue : Issue ) {
              listitem(){
                navigate(viewIssue(issue)){
                  text(issue.name){
                  }
                }
                " "
                actionLink("[X]", removeIssue(issue)){
                }
                action removeIssue ( issue : Issue )
                {
                  issue.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editProject (project : Project) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Project"
          " "
          text(project.name){
          }
        }
        form(){
          table(){
            editRowsProject(project){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewProject(project);
      }
      action save ( )
      {
        project.save();
        return viewProject(project);
      }
    }
  }

  define page createProject () {
    var project : Project := Project{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Project"
        }
        form(){
          table(){
            editRowsProject(project){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        project.save();
        return viewProject(project);
      }
    }
  }

  define editRowsProject (project : Project) {
    editRowsIssue(project){
    }
  }

  define page viewProject (project : Project) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editProject(project)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(project.name){
          }
        }
        viewRowsProject(project){
        }
      }
    }
  }

  define viewRowsProject (project : Project) {
    viewRowsIssue(project){
    }
  }

  define page allProject () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Project"
        }
        form(){
          list(){
            for ( project : Project ) {
              listitem(){
                navigate(viewProject(project)){
                  text(project.name){
                  }
                }
                " "
                actionLink("[X]", removeProject(project)){
                }
                action removeProject ( project : Project )
                {
                  project.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editBug (bug : Bug) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Bug"
          " "
          text(bug.name){
          }
        }
        form(){
          table(){
            editRowsBug(bug){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewBug(bug);
      }
      action save ( )
      {
        bug.save();
        return viewBug(bug);
      }
    }
  }

  define page createBug () {
    var bug : Bug := Bug{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Bug"
        }
        form(){
          table(){
            editRowsBug(bug){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        bug.save();
        return viewBug(bug);
      }
    }
  }

  define editRowsBug (bug : Bug) {
    editRowsIssue(bug){
    }
  }

  define page viewBug (bug : Bug) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editBug(bug)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(bug.name){
          }
        }
        viewRowsBug(bug){
        }
      }
    }
  }

  define viewRowsBug (bug : Bug) {
    viewRowsIssue(bug){
    }
  }

  define page allBug () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Bug"
        }
        form(){
          list(){
            for ( bug : Bug ) {
              listitem(){
                navigate(viewBug(bug)){
                  text(bug.name){
                  }
                }
                " "
                actionLink("[X]", removeBug(bug)){
                }
                action removeBug ( bug : Bug )
                {
                  bug.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editTask (task : Task) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Task"
          " "
          text(task.name){
          }
        }
        form(){
          table(){
            editRowsTask(task){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewTask(task);
      }
      action save ( )
      {
        task.save();
        return viewTask(task);
      }
    }
  }

  define page createTask () {
    var task : Task := Task{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Task"
        }
        form(){
          table(){
            editRowsTask(task){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        task.save();
        return viewTask(task);
      }
    }
  }

  define editRowsTask (task : Task) {
    editRowsIssue(task){
    }
  }

  define page viewTask (task : Task) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editTask(task)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(task.name){
          }
        }
        viewRowsTask(task){
        }
      }
    }
  }

  define viewRowsTask (task : Task) {
    viewRowsIssue(task){
    }
  }

  define page allTask () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Task"
        }
        form(){
          list(){
            for ( task : Task ) {
              listitem(){
                navigate(viewTask(task)){
                  text(task.name){
                  }
                }
                " "
                actionLink("[X]", removeTask(task)){
                }
                action removeTask ( task : Task )
                {
                  task.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editForum (forum : Forum) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Forum"
          " "
          text(forum.name){
          }
        }
        form(){
          table(){
            editRowsForum(forum){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewForum(forum);
      }
      action save ( )
      {
        forum.save();
        return viewForum(forum);
      }
    }
  }

  define page createForum () {
    var forum : Forum := Forum{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Forum"
        }
        form(){
          table(){
            editRowsForum(forum){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        forum.save();
        return viewForum(forum);
      }
    }
  }

  define editRowsForum (forum : Forum) {
    editRowsObject(forum){
    }
    row(){
      "Title"
      input(forum.title){
      }
    }
    row(){
      "Discussions"
      input(forum.discussions){
      }
    }
  }

  define page viewForum (forum : Forum) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editForum(forum)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(forum.name){
          }
        }
        viewRowsForum(forum){
        }
      }
    }
  }

  define viewRowsForum (forum : Forum) {
    viewRowsObject(forum){
    }
    section(){
      header(){
        "Discussions"
      }
      output(forum.discussions){
      }
      form(){
        actionLink("New Discussion", createNewDiscussionsDiscussion(forum, forum.discussions)){
        }
      }
      action createNewDiscussionsDiscussion ( forum0 : Forum, discussions : List<Discussion> )
      {
        var discussion0 : Discussion := Discussion{} ;
        discussions.add(discussion0);
        forum0.persist();
        return editDiscussion(discussion0);
      }
    }
  }

  define page allForum () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Forum"
        }
        form(){
          list(){
            for ( forum : Forum ) {
              listitem(){
                navigate(viewForum(forum)){
                  text(forum.name){
                  }
                }
                " "
                actionLink("[X]", removeForum(forum)){
                }
                action removeForum ( forum : Forum )
                {
                  forum.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editDiscussion (discussion : Discussion) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Discussion"
          " "
          text(discussion.name){
          }
        }
        form(){
          table(){
            editRowsDiscussion(discussion){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewDiscussion(discussion);
      }
      action save ( )
      {
        discussion.save();
        return viewDiscussion(discussion);
      }
    }
  }

  define page createDiscussion () {
    var discussion : Discussion := Discussion{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Discussion"
        }
        form(){
          table(){
            editRowsDiscussion(discussion){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        discussion.save();
        return viewDiscussion(discussion);
      }
    }
  }

  define editRowsDiscussion (discussion : Discussion) {
    editRowsObject(discussion){
    }
    row(){
      "Topic"
      input(discussion.topic){
      }
    }
    row(){
      "Author"
      input(discussion.author){
      }
    }
    row(){
      "Posted"
      input(discussion.posted){
      }
    }
    row(){
      "Forum"
      input(discussion.forum){
      }
    }
    row(){
      "Text"
      input(discussion.text){
      }
    }
    row(){
      "Replies"
      input(discussion.replies){
      }
    }
  }

  define viewRowsDiscussion (discussion : Discussion) {
    viewRowsObject(discussion){
    }
    section(){
      header(){
        "Author"
      }
      list(){
        listitem(){
          output(discussion.author){
          }
        }
      }
    }
    par(){
      "Posted"
      " : "
      output(discussion.posted){
      }
    }
    par(){
      "Forum"
      " : "
      output(discussion.forum){
      }
    }
    section(){
      header(){
        "Text"
      }
      output(discussion.text){
      }
    }
    section(){
      header(){
        "Replies"
      }
      for ( reply : Reply in discussion.repliesList ) {
        section(){
          header(){
            output(reply){
            }
          }
          viewRowsReply(reply){
          }
        }
      }
      form(){
        actionLink("New Reply", createNewRepliesReply(discussion, discussion.replies)){
        }
      }
      action createNewRepliesReply ( discussion1 : Discussion, replies : List<Reply> )
      {
        var reply0 : Reply := Reply{} ;
        replies.add(reply0);
        discussion1.persist();
        return editReply(reply0);
      }
    }
  }

  define page allDiscussion () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Discussion"
        }
        form(){
          list(){
            for ( discussion : Discussion ) {
              listitem(){
                navigate(viewDiscussion(discussion)){
                  text(discussion.name){
                  }
                }
                " "
                actionLink("[X]", removeDiscussion(discussion)){
                }
                action removeDiscussion ( discussion : Discussion )
                {
                  discussion.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editReply (reply : Reply) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Reply"
          " "
          text(reply.name){
          }
        }
        form(){
          table(){
            editRowsReply(reply){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewReply(reply);
      }
      action save ( )
      {
        reply.save();
        return viewReply(reply);
      }
    }
  }

  define page createReply () {
    var reply : Reply := Reply{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Reply"
        }
        form(){
          table(){
            editRowsReply(reply){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        reply.save();
        return viewReply(reply);
      }
    }
  }

  define editRowsReply (reply : Reply) {
    editRowsObject(reply){
    }
    row(){
      "Subject"
      input(reply.subject){
      }
    }
    row(){
      "Author"
      input(reply.author){
      }
    }
    row(){
      "Posted"
      input(reply.posted){
      }
    }
    row(){
      "Discussion"
      input(reply.discussion){
      }
    }
    row(){
      "Text"
      input(reply.text){
      }
    }
  }

  define page viewReply (reply : Reply) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editReply(reply)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(reply.name){
          }
        }
        viewRowsReply(reply){
        }
      }
    }
  }

  define viewRowsReply (reply : Reply) {
    viewRowsObject(reply){
    }
    section(){
      header(){
        "Author"
      }
      list(){
        listitem(){
          output(reply.author){
          }
        }
      }
    }
    par(){
      "Posted"
      " : "
      output(reply.posted){
      }
    }
    section(){
      header(){
        "Discussion"
      }
      list(){
        listitem(){
          output(reply.discussion){
          }
        }
      }
    }
    section(){
      header(){
        "Text"
      }
      output(reply.text){
      }
    }
  }

  define page allReply () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Reply"
        }
        form(){
          list(){
            for ( reply : Reply ) {
              listitem(){
                navigate(viewReply(reply)){
                  text(reply.name){
                  }
                }
                " "
                actionLink("[X]", removeReply(reply)){
                }
                action removeReply ( reply : Reply )
                {
                  reply.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editPost (post : Post) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Post"
          " "
          text(post.name){
          }
        }
        form(){
          table(){
            editRowsPost(post){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewPost(post);
      }
      action save ( )
      {
        post.save();
        return viewPost(post);
      }
    }
  }

  define page createPost () {
    var post : Post := Post{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Post"
        }
        form(){
          table(){
            editRowsPost(post){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        post.save();
        return viewPost(post);
      }
    }
  }

  define editRowsPost (post : Post) {
    editRowsObject(post){
    }
    row(){
      "Subject"
      input(post.subject){
      }
    }
    row(){
      "Author"
      input(post.author){
      }
    }
    row(){
      "Posted"
      input(post.posted){
      }
    }
    row(){
      "Discussion"
      input(post.discussion){
      }
    }
    row(){
      "Text"
      input(post.text){
      }
    }
  }

  define page viewPost (post : Post) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editPost(post)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(post.name){
          }
        }
        viewRowsPost(post){
        }
      }
    }
  }

  define viewRowsPost (post : Post) {
    viewRowsObject(post){
    }
    section(){
      header(){
        "Author"
      }
      list(){
        listitem(){
          output(post.author){
          }
        }
      }
    }
    par(){
      "Posted"
      " : "
      output(post.posted){
      }
    }
    section(){
      header(){
        "Discussion"
      }
      list(){
        listitem(){
          output(post.discussion){
          }
        }
      }
    }
    section(){
      header(){
        "Text"
      }
      output(post.text){
      }
    }
  }

  define page allPost () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Post"
        }
        form(){
          list(){
            for ( post : Post ) {
              listitem(){
                navigate(viewPost(post)){
                  text(post.name){
                  }
                }
                " "
                actionLink("[X]", removePost(post)){
                }
                action removePost ( post : Post )
                {
                  post.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editShop (shop : Shop) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Shop"
          " "
          text(shop.name){
          }
        }
        form(){
          table(){
            editRowsShop(shop){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewShop(shop);
      }
      action save ( )
      {
        shop.save();
        return viewShop(shop);
      }
    }
  }

  define page createShop () {
    var shop : Shop := Shop{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Shop"
        }
        form(){
          table(){
            editRowsShop(shop){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        shop.save();
        return viewShop(shop);
      }
    }
  }

  define editRowsShop (shop : Shop) {
    editRowsObject(shop){
    }
    row(){
      "Name"
      input(shop.name){
      }
    }
    row(){
      "Products"
      input(shop.products){
      }
    }
    row(){
      "Carts"
      input(shop.carts){
      }
    }
    row(){
      "First"
      input(shop.first){
      }
    }
    row(){
      "Last"
      input(shop.last){
      }
    }
  }

  define viewRowsShop (shop : Shop) {
    viewRowsObject(shop){
    }
    section(){
      header(){
        "Products"
      }
      output(shop.products){
      }
      form(){
        actionLink("New Product", createNewProductsProduct(shop, shop.products)){
        }
      }
      action createNewProductsProduct ( shop0 : Shop, products : List<Product> )
      {
        var product0 : Product := Product{} ;
        products.add(product0);
        shop0.persist();
        return editProduct(product0);
      }
    }
    section(){
      header(){
        "Carts"
      }
      output(shop.carts){
      }
      form(){
        actionLink("New Cart", createNewCartsCart(shop, shop.carts)){
        }
      }
      action createNewCartsCart ( shop1 : Shop, carts : List<Cart> )
      {
        var cart0 : Cart := Cart{} ;
        carts.add(cart0);
        shop1.persist();
        return editCart(cart0);
      }
    }
    section(){
      header(){
        "First"
      }
      list(){
        listitem(){
          output(shop.first){
          }
        }
      }
    }
    section(){
      header(){
        "Last"
      }
      list(){
        listitem(){
          output(shop.last){
          }
        }
      }
    }
  }

  define page allShop () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Shop"
        }
        form(){
          list(){
            for ( shop : Shop ) {
              listitem(){
                navigate(viewShop(shop)){
                  text(shop.name){
                  }
                }
                " "
                actionLink("[X]", removeShop(shop)){
                }
                action removeShop ( shop : Shop )
                {
                  shop.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editProduct (product : Product) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Product"
          " "
          text(product.name){
          }
        }
        form(){
          table(){
            editRowsProduct(product){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewProduct(product);
      }
      action save ( )
      {
        product.save();
        return viewProduct(product);
      }
    }
  }

  define page createProduct () {
    var product : Product := Product{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Product"
        }
        form(){
          table(){
            editRowsProduct(product){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        product.save();
        return viewProduct(product);
      }
    }
  }

  define editRowsProduct (product : Product) {
    editRowsObject(product){
    }
    row(){
      "Name"
      input(product.name){
      }
    }
    row(){
      "Price"
      input(product.price){
      }
    }
    row(){
      "Photo"
      input(product.photo){
      }
    }
    row(){
      "Shop"
      input(product.shop){
      }
    }
    row(){
      "Previous"
      input(product.previous){
      }
    }
    row(){
      "Next"
      input(product.next){
      }
    }
  }

  define viewRowsProduct (product : Product) {
    viewRowsObject(product){
    }
    par(){
      "Name"
      " : "
      output(product.name){
      }
    }
    par(){
      "Price"
      " : "
      output(product.price){
      }
    }
    par(){
      "Photo"
      " : "
      output(product.photo){
      }
    }
    section(){
      header(){
        "Shop"
      }
      list(){
        listitem(){
          output(product.shop){
          }
        }
      }
    }
    section(){
      header(){
        "Previous"
      }
      list(){
        listitem(){
          output(product.previous){
          }
        }
      }
    }
    section(){
      header(){
        "Next"
      }
      list(){
        listitem(){
          output(product.next){
          }
        }
      }
    }
  }

  define page allProduct () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Product"
        }
        form(){
          list(){
            for ( product : Product ) {
              listitem(){
                navigate(viewProduct(product)){
                  text(product.name){
                  }
                }
                " "
                actionLink("[X]", removeProduct(product)){
                }
                action removeProduct ( product : Product )
                {
                  product.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editCart (cart : Cart) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "Cart"
          " "
          text(cart.name){
          }
        }
        form(){
          table(){
            editRowsCart(cart){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewCart(cart);
      }
      action save ( )
      {
        cart.save();
        return viewCart(cart);
      }
    }
  }

  define page createCart () {
    var cart : Cart := Cart{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "Cart"
        }
        form(){
          table(){
            editRowsCart(cart){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        cart.save();
        return viewCart(cart);
      }
    }
  }

  define editRowsCart (cart : Cart) {
    editRowsObject(cart){
    }
    row(){
      "Shopper"
      input(cart.shopper){
      }
    }
    row(){
      "Products"
      input(cart.products){
      }
    }
  }

  define page viewCart (cart : Cart) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editCart(cart)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(cart.name){
          }
        }
        viewRowsCart(cart){
        }
      }
    }
  }

  define viewRowsCart (cart : Cart) {
    viewRowsObject(cart){
    }
    section(){
      header(){
        "Shopper"
      }
      list(){
        listitem(){
          output(cart.shopper){
          }
        }
      }
    }
    section(){
      header(){
        "Products"
      }
      output(cart.products){
      }
      form(){
        actionLink("New Product", createNewProductsProduct(cart, cart.products)){
        }
      }
      action createNewProductsProduct ( cart1 : Cart, products : List<Product> )
      {
        var product1 : Product := Product{} ;
        products.add(product1);
        cart1.persist();
        return editProduct(product1);
      }
    }
  }

  define page allCart () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "Cart"
        }
        form(){
          list(){
            for ( cart : Cart ) {
              listitem(){
                navigate(viewCart(cart)){
                  text(cart.name){
                  }
                }
                " "
                actionLink("[X]", removeCart(cart)){
                }
                action removeCart ( cart : Cart )
                {
                  cart.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editSoftwareProduct (softwareProduct : SoftwareProduct) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "SoftwareProduct"
          " "
          text(softwareProduct.name){
          }
        }
        form(){
          table(){
            editRowsSoftwareProduct(softwareProduct){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewSoftwareProduct(softwareProduct);
      }
      action save ( )
      {
        softwareProduct.save();
        return viewSoftwareProduct(softwareProduct);
      }
    }
  }

  define page createSoftwareProduct () {
    var softwareProduct : SoftwareProduct := SoftwareProduct{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "SoftwareProduct"
        }
        form(){
          table(){
            editRowsSoftwareProduct(softwareProduct){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        softwareProduct.save();
        return viewSoftwareProduct(softwareProduct);
      }
    }
  }

  define editRowsSoftwareProduct (softwareProduct : SoftwareProduct) {
    editRowsObject(softwareProduct){
    }
    row(){
      "Name"
      input(softwareProduct.name){
      }
    }
    row(){
      "Description"
      input(softwareProduct.description){
      }
    }
    row(){
      "Releases"
      input(softwareProduct.releases){
      }
    }
    row(){
      "Lead"
      input(softwareProduct.lead){
      }
    }
    row(){
      "Developers"
      input(softwareProduct.developers){
      }
    }
    row(){
      "Licence"
      input(softwareProduct.licence){
      }
    }
  }

  define page viewSoftwareProduct (softwareProduct : SoftwareProduct) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editSoftwareProduct(softwareProduct)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(softwareProduct.name){
          }
        }
        viewRowsSoftwareProduct(softwareProduct){
        }
      }
    }
  }

  define viewRowsSoftwareProduct (softwareProduct : SoftwareProduct) {
    viewRowsObject(softwareProduct){
    }
    par(){
      "Name"
      " : "
      output(softwareProduct.name){
      }
    }
    section(){
      header(){
        "Description"
      }
      output(softwareProduct.description){
      }
    }
    section(){
      header(){
        "Releases"
      }
      output(softwareProduct.releases){
      }
      form(){
        actionLink("New SoftwareRelease", createNewReleasesSoftwareRelease(softwareProduct, softwareProduct.releases)){
        }
      }
      action createNewReleasesSoftwareRelease ( softwareProduct0 : SoftwareProduct, releases : List<SoftwareRelease> )
      {
        var softwareRelease0 : SoftwareRelease := SoftwareRelease{} ;
        releases.add(softwareRelease0);
        softwareProduct0.persist();
        return editSoftwareRelease(softwareRelease0);
      }
    }
    section(){
      header(){
        "Lead"
      }
      list(){
        listitem(){
          output(softwareProduct.lead){
          }
        }
      }
    }
    section(){
      header(){
        "Developers"
      }
      output(softwareProduct.developers){
      }
      form(){
        actionLink("New Person", createNewDevelopersPerson(softwareProduct, softwareProduct.developers)){
        }
      }
      action createNewDevelopersPerson ( softwareProduct1 : SoftwareProduct, developers : List<Person> )
      {
        var person6 : Person := Person{} ;
        developers.add(person6);
        softwareProduct1.persist();
        return editPerson(person6);
      }
    }
    section(){
      header(){
        "Licence"
      }
      list(){
        listitem(){
          output(softwareProduct.licence){
          }
        }
      }
    }
  }

  define page allSoftwareProduct () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "SoftwareProduct"
        }
        form(){
          list(){
            for ( softwareProduct : SoftwareProduct ) {
              listitem(){
                navigate(viewSoftwareProduct(softwareProduct)){
                  text(softwareProduct.name){
                  }
                }
                " "
                actionLink("[X]", removeSoftwareProduct(softwareProduct)){
                }
                action removeSoftwareProduct ( softwareProduct : SoftwareProduct )
                {
                  softwareProduct.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editSoftwareRelease (softwareRelease : SoftwareRelease) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "SoftwareRelease"
          " "
          text(softwareRelease.name){
          }
        }
        form(){
          table(){
            editRowsSoftwareRelease(softwareRelease){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewSoftwareRelease(softwareRelease);
      }
      action save ( )
      {
        softwareRelease.save();
        return viewSoftwareRelease(softwareRelease);
      }
    }
  }

  define page createSoftwareRelease () {
    var softwareRelease : SoftwareRelease := SoftwareRelease{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "SoftwareRelease"
        }
        form(){
          table(){
            editRowsSoftwareRelease(softwareRelease){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        softwareRelease.save();
        return viewSoftwareRelease(softwareRelease);
      }
    }
  }

  define editRowsSoftwareRelease (softwareRelease : SoftwareRelease) {
    editRowsObject(softwareRelease){
    }
    row(){
      "Product"
      input(softwareRelease.product){
      }
    }
    row(){
      "Version"
      input(softwareRelease.version){
      }
    }
    row(){
      "Url"
      input(softwareRelease.url){
      }
    }
    row(){
      "Released"
      input(softwareRelease.released){
      }
    }
    row(){
      "Changes"
      input(softwareRelease.changes){
      }
    }
    row(){
      "Contributors"
      input(softwareRelease.contributors){
      }
    }
  }

  define page viewSoftwareRelease (softwareRelease : SoftwareRelease) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editSoftwareRelease(softwareRelease)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(softwareRelease.name){
          }
        }
        viewRowsSoftwareRelease(softwareRelease){
        }
      }
    }
  }

  define viewRowsSoftwareRelease (softwareRelease : SoftwareRelease) {
    viewRowsObject(softwareRelease){
    }
    section(){
      header(){
        "Product"
      }
      list(){
        listitem(){
          output(softwareRelease.product){
          }
        }
      }
    }
    par(){
      "Url"
      " : "
      output(softwareRelease.url){
      }
    }
    par(){
      "Released"
      " : "
      output(softwareRelease.released){
      }
    }
    section(){
      header(){
        "Changes"
      }
      output(softwareRelease.changes){
      }
    }
    section(){
      header(){
        "Contributors"
      }
      output(softwareRelease.contributors){
      }
      form(){
        actionLink("New Person", createNewContributorsPerson(softwareRelease, softwareRelease.contributors)){
        }
      }
      action createNewContributorsPerson ( softwareRelease1 : SoftwareRelease, contributors : List<Person> )
      {
        var person7 : Person := Person{} ;
        contributors.add(person7);
        softwareRelease1.persist();
        return editPerson(person7);
      }
    }
  }

  define page allSoftwareRelease () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "SoftwareRelease"
        }
        form(){
          list(){
            for ( softwareRelease : SoftwareRelease ) {
              listitem(){
                navigate(viewSoftwareRelease(softwareRelease)){
                  text(softwareRelease.name){
                  }
                }
                " "
                actionLink("[X]", removeSoftwareRelease(softwareRelease)){
                }
                action removeSoftwareRelease ( softwareRelease : SoftwareRelease )
                {
                  softwareRelease.delete();
                }
              }
            }
          }
        }
      }
    }
  }

  define page editLicense (license : License) {
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Edit "
          "License"
          " "
          text(license.name){
          }
        }
        form(){
          table(){
            editRowsLicense(license){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return viewLicense(license);
      }
      action save ( )
      {
        license.save();
        return viewLicense(license);
      }
    }
  }

  define page createLicense () {
    var license : License := License{} ;
    main(){
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          "Create new "
          "License"
        }
        form(){
          table(){
            editRowsLicense(license){
            }
          }
          action("Save", save()){
          }
          action("Cancel", cancel()){
          }
        }
      }
      action cancel ( )
      {
        return home();
      }
      action save ( )
      {
        license.save();
        return viewLicense(license);
      }
    }
  }

  define editRowsLicense (license : License) {
    editRowsObject(license){
    }
    row(){
      "Acronym"
      input(license.acronym){
      }
    }
    row(){
      "Text"
      input(license.text){
      }
    }
  }

  define page viewLicense (license : License) {
    main(){
    }
    define manageMenu () {
      listitem(){
        navigate(editLicense(license)){
          text("Edit"){
          }
        }
      }
    }
    define sidebar () {
    }
    define body () {
      section(){
        header(){
          text(license.name){
          }
        }
        viewRowsLicense(license){
        }
      }
    }
  }

  define viewRowsLicense (license : License) {
    viewRowsObject(license){
    }
    section(){
      header(){
        "Text"
      }
      output(license.text){
      }
    }
  }

  define page allLicense () {
    main(){
    }
    define body () {
      section(){
        header(){
          "All "
          "License"
        }
        form(){
          list(){
            for ( license : License ) {
              listitem(){
                navigate(viewLicense(license)){
                  text(license.name){
                  }
                }
                " "
                actionLink("[X]", removeLicense(license)){
                }
                action removeLicense ( license : License )
                {
                  license.delete();
                }
              }
            }
          }
        }
      }
    }
  }