application org.webdsl.serg

description {
  This application organizes information relevant for a research group,
  including people, publications, students, projects, colloquia, etc.
}

module app/templates

section setup

module app/people

section users

  User {
    username :: String ( name, unique )
    password :: Secret ( )
    person -> Person ( notnull )
  }
section persons .

  Address {
    street :: String ( )
    city :: String ( )
    phone :: String ( )
  }

  Person {
    fullname :: String ( name )
    email :: Email ( )
    homepage :: URL ( )
    photo :: Image ( )
    address <> Address ( )
    user -> User ( )
    blog -> Blog ( )
  }
section person pages .

  define page viewPerson (person : Person) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("personSidebar"){
            list(){
              listitem(){
                navigate(viewPerson(viewPerson.person)){
                  text(viewPerson.person.name){
                  }
                }
              }
              listitem(){
                navigate(personPublications(viewPerson.person)){
                  text("Publications"){
                  }
                }
              }
              listitem(){
                navigate(viewBlog(viewPerson.person.blog)){
                  text("Blog"){
                  }
                }
                blogEntries(){
                }
              }
              listitem(){
                "Projects"
                div("listProjectAcronyms"){
                  var projects0 : List<ResearchProject> := select pr from ResearchProject as pr , Person as pers where ( pers . id = viewPerson.person.id ) and ( pers member of pr . _members ) ;
                  list(){
                    for ( pr0 : ResearchProject in viewPerson.projects0 ) {
                      listitem(){
                        navigate(viewResearchProject(pr0)){
                          text(pr0.name){
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person1 : Person ) {
                    listitem(){
                      navigate(viewPerson(person1)){
                        text(person1.name){
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
                  for ( project0 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project0)){
                        text(project0.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    navigate(editPerson(viewPerson.person)){
                      text("Edit"){
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            div("photo"){
              image(viewPerson.person.photo){
              }
            }
            header(){
              text(viewPerson.person.name){
              }
            }
            section(){
              header(){
                "Coordinates"
              }
              table(){
                row(){
                  "homepage"
                  navigate(url(viewPerson.person.homepage)){
                    text(viewPerson.person.homepage){
                    }
                  }
                }
                row(){
                  "email"
                  navigate(url(viewPerson.person.email)){
                    text(viewPerson.person.email){
                    }
                  }
                }
                row(){
                  "address"
                  table(){
                    row(){
                      text(viewPerson.person.address.street){
                      }
                    }
                    row(){
                      text(viewPerson.person.address.city){
                      }
                    }
                  }
                }
                row(){
                  "phone"
                  text(viewPerson.person.address.phone){
                  }
                }
              }
            }
            section(){
              header(){
                "Publications"
              }
              div("publicationTitlesBy"){
                var orderedPublications0 : List<Publication> := select pub from Publication as pub , Person as pers where ( pers . id = viewPerson.person.id ) and ( pers member of pub . _authors ) order by pub . _year desc ;
                list(){
                  for ( pub0 : Publication in viewPerson.orderedPublications0 ) {
                    listitem(){
                      navigate(viewPublication(pub0)){
                        text(pub0.name){
                        }
                      }
                      " ("
                      text(pub0.year){
                      }
                      ")"
                    }
                  }
                }
              }
            }
            section(){
              header(){
                "Projects"
              }
              div("listProjects"){
                var projects1 : List<ResearchProject> := select pr from ResearchProject as pr , Person as pers where ( pers . id = viewPerson.person.id ) and ( pers member of pr . _members ) ;
                list(){
                  for ( project1 : ResearchProject in viewPerson.projects1 ) {
                    listitem(){
                      navigate(viewResearchProject(project1)){
                        text(project1.fullname){
                        }
                        " ("
                        text(project1.acronym){
                        }
                        ")"
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
    title(){
      text(viewPerson.person.name){
      }
    }
  }

module app/access
section login .

  define page login () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person4 : Person ) {
                    listitem(){
                      navigate(viewPerson(person4)){
                        text(person4.name){
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
                  for ( project2 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project2)){
                        text(project2.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          form(){
            table(){
              row(){
                "username"
                inputString(login.user.username){
                }
              }
              row(){
                "password"
                inputSecret(login.user.password){
                }
              }
            }
            action("Login", login.login()){
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
    var user : User ;
    action login ( )
    {
      return home();
    }
  }

module app/blog
description
  A blog is a journal-like sequence of time-stamped entries. The main page of a
  blog shows the n most recent entries. Entries also have their own page.
end
section domain .

  Blog {
    title :: String ( name )
    author -> Person ( )
    entries <> List<BlogEntry> ( )
    categories -> List<Category> ( )
  }

  BlogEntry {
    blog -> Blog ( )
    title :: String ( name )
    created :: Date ( )
    category -> Category ( )
    intro :: Text ( )
    body :: Text ( )
    comments <> List<BlogComment> ( )
  }

  Category {
    name :: String ( )
  }

  BlogComment {
    author -> Person ( )
    text :: Text ( )
  }
section pages .

  define page viewBlog (blog : Blog) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("blogSidebar"){
            div("personSidebar"){
              list(){
                listitem(){
                  navigate(viewPerson(viewBlog.blog.author)){
                    text(viewBlog.blog.author.name){
                    }
                  }
                }
                listitem(){
                  navigate(personPublications(viewBlog.blog.author)){
                    text("Publications"){
                    }
                  }
                }
                listitem(){
                  navigate(viewBlog(viewBlog.blog.author.blog)){
                    text("Blog"){
                    }
                  }
                  div("blogEntries"){
                    list(){
                      for ( entry0 : BlogEntry in viewBlog.blog.entries ) {
                        listitem(){
                          navigate(viewBlogEntry(entry0)){
                            text(entry0.name){
                            }
                          }
                        }
                      }
                    }
                  }
                }
                listitem(){
                  "Projects"
                  div("listProjectAcronyms"){
                    var projects2 : List<ResearchProject> := select pr from ResearchProject as pr , Person as pers where ( pers . id = viewBlog.blog.author.id ) and ( pers member of pr . _members ) ;
                    list(){
                      for ( pr1 : ResearchProject in viewBlog.projects2 ) {
                        listitem(){
                          navigate(viewResearchProject(pr1)){
                            text(pr1.name){
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person6 : Person ) {
                    listitem(){
                      navigate(viewPerson(person6)){
                        text(person6.name){
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
                  for ( project3 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project3)){
                        text(project3.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    navigate(editBlog(viewBlog.blog)){
                      text("Edit"){
                      }
                    }
                    form(){
                      actionLink("New Blog", viewBlog.createNewBlogEntry()){
                      }
                    }
                    action createNewBlogEntry ( )
                    {
                      var entry1 : BlogEntry := BlogEntry{blog := viewBlog.blog title := "title here"} ;
                      viewBlog.blog.entries.add(entry1);
                      viewBlog.blog.persist();
                      return editBlogEntry(entry1);
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          title(){
            text(viewBlog.blog.title){
            }
          }
          section(){
            header(){
              text(viewBlog.blog.title){
              }
            }
            for ( entry2 : BlogEntry in viewBlog.blog.entries ) {
              section(){
                header(){
                  text(entry2.title){
                  }
                }
                div("blogIntro"){
                  outputText(entry2.intro){
                  }
                }
                " "
                navigate(viewBlogEntry(entry2)){
                  text("read more ..."){
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewBlogEntry (entry : BlogEntry) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("blogSidebar"){
            div("personSidebar"){
              list(){
                listitem(){
                  navigate(viewPerson(viewBlogEntry.entry.blog.author)){
                    text(viewBlogEntry.entry.blog.author.name){
                    }
                  }
                }
                listitem(){
                  navigate(personPublications(viewBlogEntry.entry.blog.author)){
                    text("Publications"){
                    }
                  }
                }
                listitem(){
                  navigate(viewBlog(viewBlogEntry.entry.blog.author.blog)){
                    text("Blog"){
                    }
                  }
                  div("blogEntries"){
                    list(){
                      for ( entry3 : BlogEntry in viewBlogEntry.entry.blog.entries ) {
                        listitem(){
                          navigate(viewBlogEntry(entry3)){
                            text(entry3.name){
                            }
                          }
                        }
                      }
                    }
                  }
                }
                listitem(){
                  "Projects"
                  div("listProjectAcronyms"){
                    var projects3 : List<ResearchProject> := select pr from ResearchProject as pr , Person as pers where ( pers . id = viewBlogEntry.entry.blog.author.id ) and ( pers member of pr . _members ) ;
                    list(){
                      for ( pr2 : ResearchProject in viewBlogEntry.projects3 ) {
                        listitem(){
                          navigate(viewResearchProject(pr2)){
                            text(pr2.name){
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person8 : Person ) {
                    listitem(){
                      navigate(viewPerson(person8)){
                        text(person8.name){
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
                  for ( project4 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project4)){
                        text(project4.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    navigate(editBlogEntry(viewBlogEntry.entry)){
                      text("Edit"){
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          title(){
            text(viewBlogEntry.entry.title){
            }
          }
          section(){
            header(){
              text(viewBlogEntry.entry.title){
              }
            }
            div("blogDate"){
              outputDate(viewBlogEntry.entry.created){
              }
            }
            div("blogIntro"){
              outputText(viewBlogEntry.entry.intro){
              }
            }
            div("blogBody"){
              outputText(viewBlogEntry.entry.body){
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

module colloquium
description
  A colloquium is a series of presentations.
end
section domain .

  Colloquium {
    name :: String ( )
    description :: Text ( )
    contact -> Person ( )
    mailinglist :: Email ( )
    group -> ResearchGroup ( )
    projects -> List<ResearchProject> ( )
    presentations <> List<Presentation> ( )
  }

  Presentation {
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
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("groupSidebar"){
            list(){
              listitem(){
                navigate(viewResearchGroup(viewColloquium.colloquium.group)){
                  text(viewColloquium.colloquium.group.acronym){
                  }
                }
              }
              listitem(){
                navigate(groupMembers(viewColloquium.colloquium.group)){
                  text("People"){
                  }
                }
              }
              listitem(){
                navigate(groupPublications(viewColloquium.colloquium.group)){
                  text("Publications"){
                  }
                }
              }
              listitem(){
                navigate(groupProjects(viewColloquium.colloquium.group)){
                  text("Projects"){
                  }
                }
                list(){
                  for ( project5 : ResearchProject in viewColloquium.colloquium.group.projectsList ) {
                    listitem(){
                      navigate(viewResearchProject(project5)){
                        text(project5.name){
                        }
                      }
                    }
                  }
                }
              }
              listitem(){
                "Colloquia"
                list(){
                  for ( coll0 : Colloquium in viewColloquium.colloquium.group.colloquiaList ) {
                    listitem(){
                      navigate(viewColloquium(coll0)){
                        text(coll0.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person9 : Person ) {
                    listitem(){
                      navigate(viewPerson(person9)){
                        text(person9.name){
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
                  for ( project6 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project6)){
                        text(project6.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    navigate(editColloquium(viewColloquium.colloquium)){
                      text("Edit"){
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewColloquium.colloquium.name){
              }
            }
            outputText(viewColloquium.colloquium.description){
            }
            section(){
              header(){
                "Contact"
              }
              "The colloquium is organized by "
              navigate(viewPerson(viewColloquium.colloquium.contact)){
                text(viewColloquium.colloquium.contact.name){
                }
              }
              ". "
              "Announcements for presentations are sent to the "
              navigate(url(viewColloquium.colloquium.mailinglist)){
                text(viewColloquium.colloquium.mailinglist){
                }
              }
              " mailinglist."
            }
            section(){
              header(){
                "Upcoming Presentations"
              }
              for ( presentation0 : Presentation in viewColloquium.colloquium.presentations ) {
                row(){
                  text(presentation0.date){
                  }
                  navigate(viewPresentation(presentation0)){
                    text(presentation0.name){
                    }
                  }
                  navigate(viewPerson(presentation0.speaker)){
                    text(presentation0.speaker.name){
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

module publications
description
  Publications are published documents. There is a large variety of publication
  mediums, each of which is cited in different ways. BibTeX provides a good
  domain model, which should be modeled here.
end
section publications .

  Publication {
    title :: String ( name )
    subtitle :: String ( )
    year :: Int ( )
    pdf :: URL ( )
    authors -> List<Person> ( )
    abstract :: Text ( )
    projects -> Set<ResearchProject> ( )
  }

  TechnicalReport : Publication {
    number :: Int ( )
    document :: Text ( )
    preprintof -> Publication ( )
  }

  InProceedings : Publication {
    conference -> Conference ( )
    pages :: String ( )
  }

  Conference {
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

  Article : Publication {
    journal -> Journal ( )
    pages :: String ( )
    impact :: Int ( )
  }

  Journal {
    fullname :: String ( )
    acronym :: String ( name )
  }
section presenting publications .
section looking up publications .
section publication pages .

  define page personPublications (person : Person) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("personSidebar"){
            list(){
              listitem(){
                navigate(viewPerson(personPublications.person)){
                  text(personPublications.person.name){
                  }
                }
              }
              listitem(){
                navigate(personPublications(personPublications.person)){
                  text("Publications"){
                  }
                }
              }
              listitem(){
                navigate(viewBlog(personPublications.person.blog)){
                  text("Blog"){
                  }
                }
                blogEntries(){
                }
              }
              listitem(){
                "Projects"
                div("listProjectAcronyms"){
                  var projects4 : List<ResearchProject> := select pr from ResearchProject as pr , Person as pers where ( pers . id = personPublications.person.id ) and ( pers member of pr . _members ) ;
                  list(){
                    for ( pr3 : ResearchProject in personPublications.projects4 ) {
                      listitem(){
                        navigate(viewResearchProject(pr3)){
                          text(pr3.name){
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person11 : Person ) {
                    listitem(){
                      navigate(viewPerson(person11)){
                        text(person11.name){
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
                  for ( project7 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project7)){
                        text(project7.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          header(){
            "Publications by "
            text(personPublications.person.name){
            }
          }
          div("publicationsBy"){
            var orderedPublications1 : List<Publication> := select pub from Publication as pub , Person as pers where ( pers . id = personPublications.person.id ) and ( pers member of pub . _authors ) order by pub . _year desc ;
            list(){
              for ( pub1 : Publication in personPublications.orderedPublications1 ) {
                listitem(){
                  div("showPublication"){
                    for ( author0 : Person in pub1.authors ) {
                      navigate(viewPerson(author0)){
                        text(author0.name){
                        }
                      }
                      ", "
                    }
                    navigate(viewPublication(pub1)){
                      text(pub1.name){
                      }
                    }
                    ", "
                    text(pub1.year){
                    }
                    "."
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
    title(){
      "Publications by "
      text(personPublications.person.name){
      }
    }
  }
section editing publications .

module app/projects
section domain .

  ResearchProject {
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
      text(viewResearchProjectMy.project.fullname){
      }
    }
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person13 : Person ) {
                    listitem(){
                      navigate(viewPerson(person13)){
                        text(person13.name){
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
                  for ( project8 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project8)){
                        text(project8.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewResearchProjectMy.project.fullname){
              }
            }
            text(viewResearchProjectMy.project.description){
            }
            section(){
              header(){
                "Members"
              }
              list(){
                for ( member0 : Person in viewResearchProjectMy.project.membersList ) {
                  listitem(){
                    navigate(viewPerson(member0)){
                      text(member0.name){
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
              div("publicationsPage"){
                list(){
                  for ( publication0 : Publication in viewResearchProjectMy.project.publicationsList ) {
                    listitem(){
                      div("showPublication"){
                        for ( author1 : Person in publication0.authors ) {
                          navigate(viewPerson(author1)){
                            text(author1.name){
                            }
                          }
                          ", "
                        }
                        navigate(viewPublication(publication0)){
                          text(publication0.name){
                          }
                        }
                        ", "
                        text(publication0.year){
                        }
                        "."
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }
section looking up projects .

module app/initdb
description
  This module defines an action to add some objects to the database.
end
section init database .

  define page initDatabase () {
    form(){
      action("Init Database", initDatabase.initDB()){
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

  ResearchGroup {
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

  define page viewResearchGroup (group : ResearchGroup) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("groupSidebar"){
            list(){
              listitem(){
                navigate(viewResearchGroup(viewResearchGroup.group)){
                  text(viewResearchGroup.group.acronym){
                  }
                }
              }
              listitem(){
                navigate(groupMembers(viewResearchGroup.group)){
                  text("People"){
                  }
                }
              }
              listitem(){
                navigate(groupPublications(viewResearchGroup.group)){
                  text("Publications"){
                  }
                }
              }
              listitem(){
                navigate(groupProjects(viewResearchGroup.group)){
                  text("Projects"){
                  }
                }
                list(){
                  for ( project9 : ResearchProject in viewResearchGroup.group.projectsList ) {
                    listitem(){
                      navigate(viewResearchProject(project9)){
                        text(project9.name){
                        }
                      }
                    }
                  }
                }
              }
              listitem(){
                "Colloquia"
                list(){
                  for ( coll1 : Colloquium in viewResearchGroup.group.colloquiaList ) {
                    listitem(){
                      navigate(viewColloquium(coll1)){
                        text(coll1.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person14 : Person ) {
                    listitem(){
                      navigate(viewPerson(person14)){
                        text(person14.name){
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
                  for ( project10 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project10)){
                        text(project10.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    navigate(editResearchGroup(viewResearchGroup.group)){
                      text("Edit"){
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewResearchGroup.group.fullname){
              }
            }
            section(){
              header(){
                "Mission"
              }
              outputText(viewResearchGroup.group.mission){
              }
            }
            section(){
              header(){
                "Recent Publications"
              }
              div("recentGroupPublications"){
                var publications1 : List<Publication> := select distinct pub from Publication as pub , Person as pers , ResearchGroup as g where ( g . id = viewResearchGroup.group.id ) and ( ( pers member of g . _members ) and ( pers member of pub . _authors ) ) order by pub . _year desc ;
                list(){
                  for ( pub4 : Publication in viewResearchGroup.publications1 ) {
                    listitem(){
                      navigate(viewPublication(pub4)){
                        text(pub4.name){
                        }
                      }
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
                for ( person107 : Person in viewResearchGroup.group.membersList ) {
                  listitem(){
                    navigate(viewPerson(person107)){
                      text(person107.name){
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
    title(){
      text(viewResearchGroup.group.acronym){
      }
    }
  }

  define page viewResearchGroupPlain (group : ResearchGroup) {
    section(){
      header(){
        text(viewResearchGroupPlain.group.fullname){
        }
      }
      section(){
        header(){
          "Mission"
        }
        outputText(viewResearchGroupPlain.group.mission){
        }
      }
      section(){
        header(){
          "Recent Publications"
        }
        div("recentGroupPublications"){
          var publications2 : List<Publication> := select distinct pub from Publication as pub , Person as pers , ResearchGroup as g where ( g . id = viewResearchGroupPlain.group.id ) and ( ( pers member of g . _members ) and ( pers member of pub . _authors ) ) order by pub . _year desc ;
          list(){
            for ( pub5 : Publication in viewResearchGroupPlain.publications2 ) {
              listitem(){
                navigate(viewPublication(pub5)){
                  text(pub5.name){
                  }
                }
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
          for ( person108 : Person in viewResearchGroupPlain.group.membersList ) {
            listitem(){
              navigate(viewPerson(person108)){
                text(person108.name){
                }
              }
            }
          }
        }
      }
    }
  }
section members .

  define page groupMembers (group : ResearchGroup) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("groupSidebar"){
            list(){
              listitem(){
                navigate(viewResearchGroup(groupMembers.group)){
                  text(groupMembers.group.acronym){
                  }
                }
              }
              listitem(){
                navigate(groupMembers(groupMembers.group)){
                  text("People"){
                  }
                }
              }
              listitem(){
                navigate(groupPublications(groupMembers.group)){
                  text("Publications"){
                  }
                }
              }
              listitem(){
                navigate(groupProjects(groupMembers.group)){
                  text("Projects"){
                  }
                }
                list(){
                  for ( project11 : ResearchProject in groupMembers.group.projectsList ) {
                    listitem(){
                      navigate(viewResearchProject(project11)){
                        text(project11.name){
                        }
                      }
                    }
                  }
                }
              }
              listitem(){
                "Colloquia"
                list(){
                  for ( coll2 : Colloquium in groupMembers.group.colloquiaList ) {
                    listitem(){
                      navigate(viewColloquium(coll2)){
                        text(coll2.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person15 : Person ) {
                    listitem(){
                      navigate(viewPerson(person15)){
                        text(person15.name){
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
                  for ( project12 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project12)){
                        text(project12.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Group Members"
            }
            table(){
              for ( person16 : Person in groupMembers.group.membersList ) {
                row(){
                  div("smallphoto"){
                    image(person16.photo){
                    }
                  }
                  navigate(viewPerson(person16)){
                    text(person16.name){
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }
section projects .

  define page groupProjects (group : ResearchGroup) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("groupSidebar"){
            list(){
              listitem(){
                navigate(viewResearchGroup(groupProjects.group)){
                  text(groupProjects.group.acronym){
                  }
                }
              }
              listitem(){
                navigate(groupMembers(groupProjects.group)){
                  text("People"){
                  }
                }
              }
              listitem(){
                navigate(groupPublications(groupProjects.group)){
                  text("Publications"){
                  }
                }
              }
              listitem(){
                navigate(groupProjects(groupProjects.group)){
                  text("Projects"){
                  }
                }
                list(){
                  for ( project13 : ResearchProject in groupProjects.group.projectsList ) {
                    listitem(){
                      navigate(viewResearchProject(project13)){
                        text(project13.name){
                        }
                      }
                    }
                  }
                }
              }
              listitem(){
                "Colloquia"
                list(){
                  for ( coll3 : Colloquium in groupProjects.group.colloquiaList ) {
                    listitem(){
                      navigate(viewColloquium(coll3)){
                        text(coll3.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person17 : Person ) {
                    listitem(){
                      navigate(viewPerson(person17)){
                        text(person17.name){
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
                  for ( project14 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project14)){
                        text(project14.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Projects"
            }
            list(){
              for ( project15 : ResearchProject in groupProjects.group.projectsList ) {
                listitem(){
                  navigate(viewResearchProject(project15)){
                    text(project15.fullname){
                    }
                    " ("
                    text(project15.acronym){
                    }
                    ")"
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }
section publications .

  define page groupPublications (group : ResearchGroup) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          div("groupSidebar"){
            list(){
              listitem(){
                navigate(viewResearchGroup(groupPublications.group)){
                  text(groupPublications.group.acronym){
                  }
                }
              }
              listitem(){
                navigate(groupMembers(groupPublications.group)){
                  text("People"){
                  }
                }
              }
              listitem(){
                navigate(groupPublications(groupPublications.group)){
                  text("Publications"){
                  }
                }
              }
              listitem(){
                navigate(groupProjects(groupPublications.group)){
                  text("Projects"){
                  }
                }
                list(){
                  for ( project16 : ResearchProject in groupPublications.group.projectsList ) {
                    listitem(){
                      navigate(viewResearchProject(project16)){
                        text(project16.name){
                        }
                      }
                    }
                  }
                }
              }
              listitem(){
                "Colloquia"
                list(){
                  for ( coll4 : Colloquium in groupPublications.group.colloquiaList ) {
                    listitem(){
                      navigate(viewColloquium(coll4)){
                        text(coll4.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person18 : Person ) {
                    listitem(){
                      navigate(viewPerson(person18)){
                        text(person18.name){
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
                  for ( project17 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project17)){
                        text(project17.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Publications by "
              text(groupPublications.group.acronym){
              }
              " Members"
            }
            div("allGroupPublications"){
              list(){
                for ( pub6 : Publication ) {
                  listitem(){
                    navigate(viewPublication(pub6)){
                      text(pub6.name){
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

module news
section domain .

  News {
    title :: String ( )
    text :: Text ( )
    date :: Date ( )
  }

module issues
description
  Issue descriptions and assignments to people.
end
section domain .

  Issue {
    title :: String ( name )
    description :: Text ( )
    due :: Date ( )
    priority :: Int ( )
    issues <> Set<Issue> ( )
    assigned -> Set<Person> ( )
    status :: String ( )
  }

  Project : Issue {
  }

  Bug : Issue {
  }

  Task : Issue {
  }
section pages .

section home .

  define page home () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group8 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group8)){
                  text(group8.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person19 : Person ) {
                    listitem(){
                      navigate(viewPerson(person19)){
                        text(person19.name){
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
                  for ( project18 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project18)){
                        text(project18.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        body(){
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

section generated pages .

  define page editUser (user : User) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person20 : Person ) {
                    listitem(){
                      navigate(viewPerson(person20)){
                        text(person20.name){
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
                  for ( project19 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project19)){
                        text(project19.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "User"
              " "
              text(editUser.user.name){
              }
            }
            form(){
              table(){
                div("editRowsUser"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Username"
                    inputString(editUser.user.username){
                    }
                  }
                  row(){
                    "Password"
                    inputSecret(editUser.user.password){
                    }
                  }
                  row(){
                    "Person"
                    div("inputSimpleRefAssociation"){
                      text(editUser.user.person.name){
                      }
                      " "
                      select ( person109 : Person , Select , setPerson0(person109) )
                      action setPerson0 ( person110 : Person )
                      {
                        user.person := person110;
                      }
                    }
                  }
                }
              }
              action("Save", editUser.save()){
              }
              action("Cancel", editUser.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewUser(editUser.user);
          }
          action save ( )
          {
            editUser.user.save();
            return viewUser(editUser.user);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createUser () {
    var user : User := User{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person21 : Person ) {
                    listitem(){
                      navigate(viewPerson(person21)){
                        text(person21.name){
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
                  for ( project20 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project20)){
                        text(project20.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "User"
            }
            form(){
              table(){
                div("editRowsUser"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Username"
                    inputString(createUser.user.username){
                    }
                  }
                  row(){
                    "Password"
                    inputSecret(createUser.user.password){
                    }
                  }
                  row(){
                    "Person"
                    div("inputSimpleRefAssociation"){
                      text(createUser.user.person.name){
                      }
                      " "
                      select ( person111 : Person , Select , setPerson1(person111) )
                      action setPerson1 ( person112 : Person )
                      {
                        user.person := person112;
                      }
                    }
                  }
                }
              }
              action("Save", createUser.save()){
              }
              action("Cancel", createUser.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createUser.user.save();
            return viewUser(createUser.user);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewUser (user : User) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person22 : Person ) {
                    listitem(){
                      navigate(viewPerson(person22)){
                        text(person22.name){
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
                  for ( project21 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project21)){
                        text(project21.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editUser(viewUser.user)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewUser.user.name){
              }
            }
            div("viewRowsUser"){
              div("viewRowsObject"){
              }
              par(){
                "Username"
                " : "
                text(viewUser.user.username){
                }
              }
              par(){
                "Password"
                " : "
                outputSecret(viewUser.user.password){
                }
              }
              section(){
                header(){
                  "Person"
                }
                list(){
                  listitem(){
                    navigate(viewPerson(viewUser.user.person)){
                      text(viewUser.user.person.name){
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allUser () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group9 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group9)){
                  text(group9.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person23 : Person ) {
                    listitem(){
                      navigate(viewPerson(person23)){
                        text(person23.name){
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
                  for ( project22 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project22)){
                        text(project22.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "User"
            }
            form(){
              list(){
                for ( user3 : User ) {
                  listitem(){
                    navigate(viewUser(user3)){
                      text(user3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allUser.removeUser(user3)){
                    }
                    action removeUser ( user4 : User )
                    {
                      user4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editAddress (address : Address) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person24 : Person ) {
                    listitem(){
                      navigate(viewPerson(person24)){
                        text(person24.name){
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
                  for ( project23 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project23)){
                        text(project23.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Address"
              " "
              text(editAddress.address.name){
              }
            }
            form(){
              table(){
                div("editRowsAddress"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Street"
                    inputString(editAddress.address.street){
                    }
                  }
                  row(){
                    "City"
                    inputString(editAddress.address.city){
                    }
                  }
                  row(){
                    "Phone"
                    inputString(editAddress.address.phone){
                    }
                  }
                }
              }
              action("Save", editAddress.save()){
              }
              action("Cancel", editAddress.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewAddress(editAddress.address);
          }
          action save ( )
          {
            editAddress.address.save();
            return viewAddress(editAddress.address);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createAddress () {
    var address : Address := Address{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person25 : Person ) {
                    listitem(){
                      navigate(viewPerson(person25)){
                        text(person25.name){
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
                  for ( project24 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project24)){
                        text(project24.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Address"
            }
            form(){
              table(){
                div("editRowsAddress"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Street"
                    inputString(createAddress.address.street){
                    }
                  }
                  row(){
                    "City"
                    inputString(createAddress.address.city){
                    }
                  }
                  row(){
                    "Phone"
                    inputString(createAddress.address.phone){
                    }
                  }
                }
              }
              action("Save", createAddress.save()){
              }
              action("Cancel", createAddress.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createAddress.address.save();
            return viewAddress(createAddress.address);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewAddress (address : Address) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person26 : Person ) {
                    listitem(){
                      navigate(viewPerson(person26)){
                        text(person26.name){
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
                  for ( project25 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project25)){
                        text(project25.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editAddress(viewAddress.address)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewAddress.address.name){
              }
            }
            div("viewRowsAddress"){
              div("viewRowsObject"){
              }
              par(){
                "Street"
                " : "
                text(viewAddress.address.street){
                }
              }
              par(){
                "City"
                " : "
                text(viewAddress.address.city){
                }
              }
              par(){
                "Phone"
                " : "
                text(viewAddress.address.phone){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allAddress () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group10 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group10)){
                  text(group10.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person27 : Person ) {
                    listitem(){
                      navigate(viewPerson(person27)){
                        text(person27.name){
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
                  for ( project26 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project26)){
                        text(project26.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Address"
            }
            form(){
              list(){
                for ( address3 : Address ) {
                  listitem(){
                    navigate(viewAddress(address3)){
                      text(address3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allAddress.removeAddress(address3)){
                    }
                    action removeAddress ( address4 : Address )
                    {
                      address4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editPerson (person : Person) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person28 : Person ) {
                    listitem(){
                      navigate(viewPerson(person28)){
                        text(person28.name){
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
                  for ( project27 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project27)){
                        text(project27.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Person"
              " "
              text(editPerson.person.name){
              }
            }
            form(){
              table(){
                div("editRowsPerson"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(editPerson.person.fullname){
                    }
                  }
                  row(){
                    "Email"
                    inputEmail(editPerson.person.email){
                    }
                  }
                  row(){
                    "Homepage"
                    inputURL(editPerson.person.homepage){
                    }
                  }
                  row(){
                    "Photo"
                    inputImage(editPerson.person.photo){
                    }
                  }
                  row(){
                    "Address"
                    ""
                  }
                  row(){
                    "Street"
                    inputString(editPerson.person.address.street){
                    }
                  }
                  row(){
                    "City"
                    inputString(editPerson.person.address.city){
                    }
                  }
                  row(){
                    "Phone"
                    inputString(editPerson.person.address.phone){
                    }
                  }
                  row(){
                    "User"
                    div("inputSimpleRefAssociation"){
                      text(editPerson.person.user.name){
                      }
                      " "
                      select ( user5 : User , Select , setUser0(user5) )
                      action setUser0 ( user6 : User )
                      {
                        person.user := user6;
                      }
                    }
                  }
                  row(){
                    "Blog"
                    div("inputSimpleRefAssociation"){
                      text(editPerson.person.blog.name){
                      }
                      " "
                      select ( blog6 : Blog , Select , setBlog0(blog6) )
                      action setBlog0 ( blog7 : Blog )
                      {
                        person.blog := blog7;
                      }
                    }
                  }
                }
              }
              action("Save", editPerson.save()){
              }
              action("Cancel", editPerson.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewPerson(editPerson.person);
          }
          action save ( )
          {
            editPerson.person.save();
            return viewPerson(editPerson.person);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createPerson () {
    var person : Person := Person{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person30 : Person ) {
                    listitem(){
                      navigate(viewPerson(person30)){
                        text(person30.name){
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
                  for ( project28 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project28)){
                        text(project28.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Person"
            }
            form(){
              table(){
                div("editRowsPerson"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(createPerson.person.fullname){
                    }
                  }
                  row(){
                    "Email"
                    inputEmail(createPerson.person.email){
                    }
                  }
                  row(){
                    "Homepage"
                    inputURL(createPerson.person.homepage){
                    }
                  }
                  row(){
                    "Photo"
                    inputImage(createPerson.person.photo){
                    }
                  }
                  row(){
                    "Address"
                    ""
                  }
                  row(){
                    "Street"
                    inputString(createPerson.person.address.street){
                    }
                  }
                  row(){
                    "City"
                    inputString(createPerson.person.address.city){
                    }
                  }
                  row(){
                    "Phone"
                    inputString(createPerson.person.address.phone){
                    }
                  }
                  row(){
                    "User"
                    div("inputSimpleRefAssociation"){
                      text(createPerson.person.user.name){
                      }
                      " "
                      select ( user7 : User , Select , setUser1(user7) )
                      action setUser1 ( user8 : User )
                      {
                        person.user := user8;
                      }
                    }
                  }
                  row(){
                    "Blog"
                    div("inputSimpleRefAssociation"){
                      text(createPerson.person.blog.name){
                      }
                      " "
                      select ( blog8 : Blog , Select , setBlog1(blog8) )
                      action setBlog1 ( blog9 : Blog )
                      {
                        person.blog := blog9;
                      }
                    }
                  }
                }
              }
              action("Save", createPerson.save()){
              }
              action("Cancel", createPerson.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createPerson.person.save();
            return viewPerson(createPerson.person);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allPerson () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group11 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group11)){
                  text(group11.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person32 : Person ) {
                    listitem(){
                      navigate(viewPerson(person32)){
                        text(person32.name){
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
                  for ( project29 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project29)){
                        text(project29.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Person"
            }
            form(){
              list(){
                for ( person33 : Person ) {
                  listitem(){
                    navigate(viewPerson(person33)){
                      text(person33.name){
                      }
                    }
                    " "
                    actionLink("[X]", allPerson.removePerson(person33)){
                    }
                    action removePerson ( person34 : Person )
                    {
                      person34.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editBlog (blog : Blog) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person35 : Person ) {
                    listitem(){
                      navigate(viewPerson(person35)){
                        text(person35.name){
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
                  for ( project30 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project30)){
                        text(project30.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Blog"
              " "
              text(editBlog.blog.name){
              }
            }
            form(){
              table(){
                div("editRowsBlog"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(editBlog.blog.title){
                    }
                  }
                  row(){
                    "Author"
                    div("inputSimpleRefAssociation"){
                      text(editBlog.blog.author.name){
                      }
                      " "
                      select ( person113 : Person , Select , setPerson2(person113) )
                      action setPerson2 ( person114 : Person )
                      {
                        blog.author := person114;
                      }
                    }
                  }
                  row(){
                    "Entries"
                    div("inputAssociationList"){
                      list(){
                        for ( blogEntry4 : BlogEntry in editBlog.blog.entries ) {
                          listitem(){
                            text(blogEntry4.name){
                            }
                            " "
                            actionLink("[X]", editBlog.removeBlogEntry0(blogEntry4)){
                            }
                            action removeBlogEntry0 ( blogEntry4 : BlogEntry )
                            {
                              editBlog.blog.entries.remove(blogEntry4);
                            }
                          }
                        }
                      }
                      select ( blogEntry5 : BlogEntry , Add BlogEntry , addBlogEntry0(blogEntry5) )
                      action addBlogEntry0 ( blogEntry4 : BlogEntry )
                      {
                        editBlog.blog.entries.add(blogEntry4);
                      }
                    }
                  }
                  row(){
                    "Categories"
                    div("inputAssociationList"){
                      list(){
                        for ( category5 : Category in editBlog.blog.categories ) {
                          listitem(){
                            text(category5.name){
                            }
                            " "
                            actionLink("[X]", editBlog.removeCategory0(category5)){
                            }
                            action removeCategory0 ( category5 : Category )
                            {
                              editBlog.blog.categories.remove(category5);
                            }
                          }
                        }
                      }
                      select ( category6 : Category , Add Category , addCategory0(category6) )
                      action addCategory0 ( category5 : Category )
                      {
                        editBlog.blog.categories.add(category5);
                      }
                    }
                  }
                }
              }
              action("Save", editBlog.save()){
              }
              action("Cancel", editBlog.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewBlog(editBlog.blog);
          }
          action save ( )
          {
            editBlog.blog.save();
            return viewBlog(editBlog.blog);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createBlog () {
    var blog : Blog := Blog{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person36 : Person ) {
                    listitem(){
                      navigate(viewPerson(person36)){
                        text(person36.name){
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
                  for ( project31 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project31)){
                        text(project31.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Blog"
            }
            form(){
              table(){
                div("editRowsBlog"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(createBlog.blog.title){
                    }
                  }
                  row(){
                    "Author"
                    div("inputSimpleRefAssociation"){
                      text(createBlog.blog.author.name){
                      }
                      " "
                      select ( person115 : Person , Select , setPerson3(person115) )
                      action setPerson3 ( person116 : Person )
                      {
                        blog.author := person116;
                      }
                    }
                  }
                  row(){
                    "Entries"
                    div("inputAssociationList"){
                      list(){
                        for ( blogEntry6 : BlogEntry in createBlog.blog.entries ) {
                          listitem(){
                            text(blogEntry6.name){
                            }
                            " "
                            actionLink("[X]", createBlog.removeBlogEntry1(blogEntry6)){
                            }
                            action removeBlogEntry1 ( blogEntry6 : BlogEntry )
                            {
                              createBlog.blog.entries.remove(blogEntry6);
                            }
                          }
                        }
                      }
                      select ( blogEntry7 : BlogEntry , Add BlogEntry , addBlogEntry1(blogEntry7) )
                      action addBlogEntry1 ( blogEntry6 : BlogEntry )
                      {
                        createBlog.blog.entries.add(blogEntry6);
                      }
                    }
                  }
                  row(){
                    "Categories"
                    div("inputAssociationList"){
                      list(){
                        for ( category7 : Category in createBlog.blog.categories ) {
                          listitem(){
                            text(category7.name){
                            }
                            " "
                            actionLink("[X]", createBlog.removeCategory1(category7)){
                            }
                            action removeCategory1 ( category7 : Category )
                            {
                              createBlog.blog.categories.remove(category7);
                            }
                          }
                        }
                      }
                      select ( category8 : Category , Add Category , addCategory1(category8) )
                      action addCategory1 ( category7 : Category )
                      {
                        createBlog.blog.categories.add(category7);
                      }
                    }
                  }
                }
              }
              action("Save", createBlog.save()){
              }
              action("Cancel", createBlog.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createBlog.blog.save();
            return viewBlog(createBlog.blog);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allBlog () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group12 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group12)){
                  text(group12.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person37 : Person ) {
                    listitem(){
                      navigate(viewPerson(person37)){
                        text(person37.name){
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
                  for ( project32 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project32)){
                        text(project32.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Blog"
            }
            form(){
              list(){
                for ( blog4 : Blog ) {
                  listitem(){
                    navigate(viewBlog(blog4)){
                      text(blog4.name){
                      }
                    }
                    " "
                    actionLink("[X]", allBlog.removeBlog(blog4)){
                    }
                    action removeBlog ( blog5 : Blog )
                    {
                      blog5.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editBlogEntry (blogEntry : BlogEntry) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person38 : Person ) {
                    listitem(){
                      navigate(viewPerson(person38)){
                        text(person38.name){
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
                  for ( project33 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project33)){
                        text(project33.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "BlogEntry"
              " "
              text(editBlogEntry.blogEntry.name){
              }
            }
            form(){
              table(){
                div("editRowsBlogEntry"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Blog"
                    div("inputSimpleRefAssociation"){
                      text(editBlogEntry.blogEntry.blog.name){
                      }
                      " "
                      select ( blog10 : Blog , Select , setBlog2(blog10) )
                      action setBlog2 ( blog11 : Blog )
                      {
                        blogEntry.blog := blog11;
                      }
                    }
                  }
                  row(){
                    "Title"
                    inputString(editBlogEntry.blogEntry.title){
                    }
                  }
                  row(){
                    "Created"
                    inputDate(editBlogEntry.blogEntry.created){
                    }
                  }
                  row(){
                    "Category"
                    div("inputSimpleRefAssociation"){
                      text(editBlogEntry.blogEntry.category.name){
                      }
                      " "
                      select ( category9 : Category , Select , setCategory0(category9) )
                      action setCategory0 ( category10 : Category )
                      {
                        blogEntry.category := category10;
                      }
                    }
                  }
                  row(){
                    "Intro"
                    inputText(editBlogEntry.blogEntry.intro){
                    }
                  }
                  row(){
                    "Body"
                    inputText(editBlogEntry.blogEntry.body){
                    }
                  }
                  row(){
                    "Comments"
                    div("inputAssociationList"){
                      list(){
                        for ( blogComment5 : BlogComment in editBlogEntry.blogEntry.comments ) {
                          listitem(){
                            text(blogComment5.name){
                            }
                            " "
                            actionLink("[X]", editBlogEntry.removeBlogComment0(blogComment5)){
                            }
                            action removeBlogComment0 ( blogComment5 : BlogComment )
                            {
                              editBlogEntry.blogEntry.comments.remove(blogComment5);
                            }
                          }
                        }
                      }
                      select ( blogComment6 : BlogComment , Add BlogComment , addBlogComment0(blogComment6) )
                      action addBlogComment0 ( blogComment5 : BlogComment )
                      {
                        editBlogEntry.blogEntry.comments.add(blogComment5);
                      }
                    }
                  }
                }
              }
              action("Save", editBlogEntry.save()){
              }
              action("Cancel", editBlogEntry.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewBlogEntry(editBlogEntry.blogEntry);
          }
          action save ( )
          {
            editBlogEntry.blogEntry.save();
            return viewBlogEntry(editBlogEntry.blogEntry);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createBlogEntry () {
    var blogEntry : BlogEntry := BlogEntry{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person39 : Person ) {
                    listitem(){
                      navigate(viewPerson(person39)){
                        text(person39.name){
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
                  for ( project34 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project34)){
                        text(project34.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "BlogEntry"
            }
            form(){
              table(){
                div("editRowsBlogEntry"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Blog"
                    div("inputSimpleRefAssociation"){
                      text(createBlogEntry.blogEntry.blog.name){
                      }
                      " "
                      select ( blog12 : Blog , Select , setBlog3(blog12) )
                      action setBlog3 ( blog13 : Blog )
                      {
                        blogEntry.blog := blog13;
                      }
                    }
                  }
                  row(){
                    "Title"
                    inputString(createBlogEntry.blogEntry.title){
                    }
                  }
                  row(){
                    "Created"
                    inputDate(createBlogEntry.blogEntry.created){
                    }
                  }
                  row(){
                    "Category"
                    div("inputSimpleRefAssociation"){
                      text(createBlogEntry.blogEntry.category.name){
                      }
                      " "
                      select ( category11 : Category , Select , setCategory1(category11) )
                      action setCategory1 ( category12 : Category )
                      {
                        blogEntry.category := category12;
                      }
                    }
                  }
                  row(){
                    "Intro"
                    inputText(createBlogEntry.blogEntry.intro){
                    }
                  }
                  row(){
                    "Body"
                    inputText(createBlogEntry.blogEntry.body){
                    }
                  }
                  row(){
                    "Comments"
                    div("inputAssociationList"){
                      list(){
                        for ( blogComment7 : BlogComment in createBlogEntry.blogEntry.comments ) {
                          listitem(){
                            text(blogComment7.name){
                            }
                            " "
                            actionLink("[X]", createBlogEntry.removeBlogComment1(blogComment7)){
                            }
                            action removeBlogComment1 ( blogComment7 : BlogComment )
                            {
                              createBlogEntry.blogEntry.comments.remove(blogComment7);
                            }
                          }
                        }
                      }
                      select ( blogComment8 : BlogComment , Add BlogComment , addBlogComment1(blogComment8) )
                      action addBlogComment1 ( blogComment7 : BlogComment )
                      {
                        createBlogEntry.blogEntry.comments.add(blogComment7);
                      }
                    }
                  }
                }
              }
              action("Save", createBlogEntry.save()){
              }
              action("Cancel", createBlogEntry.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createBlogEntry.blogEntry.save();
            return viewBlogEntry(createBlogEntry.blogEntry);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allBlogEntry () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group13 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group13)){
                  text(group13.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person40 : Person ) {
                    listitem(){
                      navigate(viewPerson(person40)){
                        text(person40.name){
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
                  for ( project35 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project35)){
                        text(project35.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "BlogEntry"
            }
            form(){
              list(){
                for ( blogEntry2 : BlogEntry ) {
                  listitem(){
                    navigate(viewBlogEntry(blogEntry2)){
                      text(blogEntry2.name){
                      }
                    }
                    " "
                    actionLink("[X]", allBlogEntry.removeBlogEntry(blogEntry2)){
                    }
                    action removeBlogEntry ( blogEntry3 : BlogEntry )
                    {
                      blogEntry3.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editCategory (category : Category) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person41 : Person ) {
                    listitem(){
                      navigate(viewPerson(person41)){
                        text(person41.name){
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
                  for ( project36 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project36)){
                        text(project36.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Category"
              " "
              text(editCategory.category.name){
              }
            }
            form(){
              table(){
                div("editRowsCategory"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Name"
                    inputString(editCategory.category.name){
                    }
                  }
                }
              }
              action("Save", editCategory.save()){
              }
              action("Cancel", editCategory.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewCategory(editCategory.category);
          }
          action save ( )
          {
            editCategory.category.save();
            return viewCategory(editCategory.category);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createCategory () {
    var category : Category := Category{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person42 : Person ) {
                    listitem(){
                      navigate(viewPerson(person42)){
                        text(person42.name){
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
                  for ( project37 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project37)){
                        text(project37.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Category"
            }
            form(){
              table(){
                div("editRowsCategory"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Name"
                    inputString(createCategory.category.name){
                    }
                  }
                }
              }
              action("Save", createCategory.save()){
              }
              action("Cancel", createCategory.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createCategory.category.save();
            return viewCategory(createCategory.category);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewCategory (category : Category) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person43 : Person ) {
                    listitem(){
                      navigate(viewPerson(person43)){
                        text(person43.name){
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
                  for ( project38 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project38)){
                        text(project38.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editCategory(viewCategory.category)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewCategory.category.name){
              }
            }
            div("viewRowsCategory"){
              div("viewRowsObject"){
              }
              par(){
                "Name"
                " : "
                text(viewCategory.category.name){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allCategory () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group14 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group14)){
                  text(group14.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person44 : Person ) {
                    listitem(){
                      navigate(viewPerson(person44)){
                        text(person44.name){
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
                  for ( project39 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project39)){
                        text(project39.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Category"
            }
            form(){
              list(){
                for ( category3 : Category ) {
                  listitem(){
                    navigate(viewCategory(category3)){
                      text(category3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allCategory.removeCategory(category3)){
                    }
                    action removeCategory ( category4 : Category )
                    {
                      category4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editBlogComment (blogComment : BlogComment) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person45 : Person ) {
                    listitem(){
                      navigate(viewPerson(person45)){
                        text(person45.name){
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
                  for ( project40 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project40)){
                        text(project40.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "BlogComment"
              " "
              text(editBlogComment.blogComment.name){
              }
            }
            form(){
              table(){
                div("editRowsBlogComment"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Author"
                    div("inputSimpleRefAssociation"){
                      text(editBlogComment.blogComment.author.name){
                      }
                      " "
                      select ( person117 : Person , Select , setPerson4(person117) )
                      action setPerson4 ( person118 : Person )
                      {
                        blogComment.author := person118;
                      }
                    }
                  }
                  row(){
                    "Text"
                    inputText(editBlogComment.blogComment.text){
                    }
                  }
                }
              }
              action("Save", editBlogComment.save()){
              }
              action("Cancel", editBlogComment.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewBlogComment(editBlogComment.blogComment);
          }
          action save ( )
          {
            editBlogComment.blogComment.save();
            return viewBlogComment(editBlogComment.blogComment);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createBlogComment () {
    var blogComment : BlogComment := BlogComment{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person46 : Person ) {
                    listitem(){
                      navigate(viewPerson(person46)){
                        text(person46.name){
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
                  for ( project41 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project41)){
                        text(project41.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "BlogComment"
            }
            form(){
              table(){
                div("editRowsBlogComment"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Author"
                    div("inputSimpleRefAssociation"){
                      text(createBlogComment.blogComment.author.name){
                      }
                      " "
                      select ( person119 : Person , Select , setPerson5(person119) )
                      action setPerson5 ( person120 : Person )
                      {
                        blogComment.author := person120;
                      }
                    }
                  }
                  row(){
                    "Text"
                    inputText(createBlogComment.blogComment.text){
                    }
                  }
                }
              }
              action("Save", createBlogComment.save()){
              }
              action("Cancel", createBlogComment.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createBlogComment.blogComment.save();
            return viewBlogComment(createBlogComment.blogComment);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewBlogComment (blogComment : BlogComment) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person47 : Person ) {
                    listitem(){
                      navigate(viewPerson(person47)){
                        text(person47.name){
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
                  for ( project42 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project42)){
                        text(project42.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editBlogComment(viewBlogComment.blogComment)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewBlogComment.blogComment.name){
              }
            }
            div("viewRowsBlogComment"){
              div("viewRowsObject"){
              }
              section(){
                header(){
                  "Author"
                }
                list(){
                  listitem(){
                    navigate(viewPerson(viewBlogComment.blogComment.author)){
                      text(viewBlogComment.blogComment.author.name){
                      }
                    }
                  }
                }
              }
              section(){
                header(){
                  "Text"
                }
                outputText(viewBlogComment.blogComment.text){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allBlogComment () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group15 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group15)){
                  text(group15.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person48 : Person ) {
                    listitem(){
                      navigate(viewPerson(person48)){
                        text(person48.name){
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
                  for ( project43 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project43)){
                        text(project43.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "BlogComment"
            }
            form(){
              list(){
                for ( blogComment3 : BlogComment ) {
                  listitem(){
                    navigate(viewBlogComment(blogComment3)){
                      text(blogComment3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allBlogComment.removeBlogComment(blogComment3)){
                    }
                    action removeBlogComment ( blogComment4 : BlogComment )
                    {
                      blogComment4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editColloquium (colloquium : Colloquium) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person49 : Person ) {
                    listitem(){
                      navigate(viewPerson(person49)){
                        text(person49.name){
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
                  for ( project44 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project44)){
                        text(project44.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Colloquium"
              " "
              text(editColloquium.colloquium.name){
              }
            }
            form(){
              table(){
                div("editRowsColloquium"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Name"
                    inputString(editColloquium.colloquium.name){
                    }
                  }
                  row(){
                    "Description"
                    inputText(editColloquium.colloquium.description){
                    }
                  }
                  row(){
                    "Contact"
                    div("inputSimpleRefAssociation"){
                      text(editColloquium.colloquium.contact.name){
                      }
                      " "
                      select ( person121 : Person , Select , setPerson6(person121) )
                      action setPerson6 ( person122 : Person )
                      {
                        colloquium.contact := person122;
                      }
                    }
                  }
                  row(){
                    "Mailinglist"
                    inputEmail(editColloquium.colloquium.mailinglist){
                    }
                  }
                  row(){
                    "Group"
                    div("inputSimpleRefAssociation"){
                      text(editColloquium.colloquium.group.name){
                      }
                      " "
                      select ( researchGroup4 : ResearchGroup , Select , setResearchGroup0(researchGroup4) )
                      action setResearchGroup0 ( researchGroup5 : ResearchGroup )
                      {
                        colloquium.group := researchGroup5;
                      }
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationList"){
                      list(){
                        for ( researchProject5 : ResearchProject in editColloquium.colloquium.projects ) {
                          listitem(){
                            text(researchProject5.name){
                            }
                            " "
                            actionLink("[X]", editColloquium.removeResearchProject0(researchProject5)){
                            }
                            action removeResearchProject0 ( researchProject5 : ResearchProject )
                            {
                              editColloquium.colloquium.projects.remove(researchProject5);
                            }
                          }
                        }
                      }
                      select ( researchProject6 : ResearchProject , Add ResearchProject , addResearchProject0(researchProject6) )
                      action addResearchProject0 ( researchProject5 : ResearchProject )
                      {
                        editColloquium.colloquium.projects.add(researchProject5);
                      }
                    }
                  }
                  row(){
                    "Presentations"
                    div("inputAssociationList"){
                      list(){
                        for ( presentation6 : Presentation in editColloquium.colloquium.presentations ) {
                          listitem(){
                            text(presentation6.name){
                            }
                            " "
                            actionLink("[X]", editColloquium.removePresentation0(presentation6)){
                            }
                            action removePresentation0 ( presentation6 : Presentation )
                            {
                              editColloquium.colloquium.presentations.remove(presentation6);
                            }
                          }
                        }
                      }
                      select ( presentation7 : Presentation , Add Presentation , addPresentation0(presentation7) )
                      action addPresentation0 ( presentation6 : Presentation )
                      {
                        editColloquium.colloquium.presentations.add(presentation6);
                      }
                    }
                  }
                }
              }
              action("Save", editColloquium.save()){
              }
              action("Cancel", editColloquium.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewColloquium(editColloquium.colloquium);
          }
          action save ( )
          {
            editColloquium.colloquium.save();
            return viewColloquium(editColloquium.colloquium);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createColloquium () {
    var colloquium : Colloquium := Colloquium{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person50 : Person ) {
                    listitem(){
                      navigate(viewPerson(person50)){
                        text(person50.name){
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
                  for ( project45 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project45)){
                        text(project45.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Colloquium"
            }
            form(){
              table(){
                div("editRowsColloquium"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Name"
                    inputString(createColloquium.colloquium.name){
                    }
                  }
                  row(){
                    "Description"
                    inputText(createColloquium.colloquium.description){
                    }
                  }
                  row(){
                    "Contact"
                    div("inputSimpleRefAssociation"){
                      text(createColloquium.colloquium.contact.name){
                      }
                      " "
                      select ( person123 : Person , Select , setPerson7(person123) )
                      action setPerson7 ( person124 : Person )
                      {
                        colloquium.contact := person124;
                      }
                    }
                  }
                  row(){
                    "Mailinglist"
                    inputEmail(createColloquium.colloquium.mailinglist){
                    }
                  }
                  row(){
                    "Group"
                    div("inputSimpleRefAssociation"){
                      text(createColloquium.colloquium.group.name){
                      }
                      " "
                      select ( researchGroup6 : ResearchGroup , Select , setResearchGroup1(researchGroup6) )
                      action setResearchGroup1 ( researchGroup7 : ResearchGroup )
                      {
                        colloquium.group := researchGroup7;
                      }
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationList"){
                      list(){
                        for ( researchProject7 : ResearchProject in createColloquium.colloquium.projects ) {
                          listitem(){
                            text(researchProject7.name){
                            }
                            " "
                            actionLink("[X]", createColloquium.removeResearchProject1(researchProject7)){
                            }
                            action removeResearchProject1 ( researchProject7 : ResearchProject )
                            {
                              createColloquium.colloquium.projects.remove(researchProject7);
                            }
                          }
                        }
                      }
                      select ( researchProject8 : ResearchProject , Add ResearchProject , addResearchProject1(researchProject8) )
                      action addResearchProject1 ( researchProject7 : ResearchProject )
                      {
                        createColloquium.colloquium.projects.add(researchProject7);
                      }
                    }
                  }
                  row(){
                    "Presentations"
                    div("inputAssociationList"){
                      list(){
                        for ( presentation8 : Presentation in createColloquium.colloquium.presentations ) {
                          listitem(){
                            text(presentation8.name){
                            }
                            " "
                            actionLink("[X]", createColloquium.removePresentation1(presentation8)){
                            }
                            action removePresentation1 ( presentation8 : Presentation )
                            {
                              createColloquium.colloquium.presentations.remove(presentation8);
                            }
                          }
                        }
                      }
                      select ( presentation9 : Presentation , Add Presentation , addPresentation1(presentation9) )
                      action addPresentation1 ( presentation8 : Presentation )
                      {
                        createColloquium.colloquium.presentations.add(presentation8);
                      }
                    }
                  }
                }
              }
              action("Save", createColloquium.save()){
              }
              action("Cancel", createColloquium.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createColloquium.colloquium.save();
            return viewColloquium(createColloquium.colloquium);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allColloquium () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group16 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group16)){
                  text(group16.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person51 : Person ) {
                    listitem(){
                      navigate(viewPerson(person51)){
                        text(person51.name){
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
                  for ( project46 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project46)){
                        text(project46.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Colloquium"
            }
            form(){
              list(){
                for ( colloquium2 : Colloquium ) {
                  listitem(){
                    navigate(viewColloquium(colloquium2)){
                      text(colloquium2.name){
                      }
                    }
                    " "
                    actionLink("[X]", allColloquium.removeColloquium(colloquium2)){
                    }
                    action removeColloquium ( colloquium3 : Colloquium )
                    {
                      colloquium3.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editPresentation (presentation : Presentation) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person52 : Person ) {
                    listitem(){
                      navigate(viewPerson(person52)){
                        text(person52.name){
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
                  for ( project47 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project47)){
                        text(project47.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Presentation"
              " "
              text(editPresentation.presentation.name){
              }
            }
            form(){
              table(){
                div("editRowsPresentation"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(editPresentation.presentation.title){
                    }
                  }
                  row(){
                    "Speaker"
                    div("inputSimpleRefAssociation"){
                      text(editPresentation.presentation.speaker.name){
                      }
                      " "
                      select ( person125 : Person , Select , setPerson8(person125) )
                      action setPerson8 ( person126 : Person )
                      {
                        presentation.speaker := person126;
                      }
                    }
                  }
                  row(){
                    "Date"
                    inputDate(editPresentation.presentation.date){
                    }
                  }
                  row(){
                    "Time"
                    inputDate(editPresentation.presentation.time){
                    }
                  }
                  row(){
                    "End"
                    inputDate(editPresentation.presentation.end){
                    }
                  }
                  row(){
                    "Venue"
                    inputString(editPresentation.presentation.venue){
                    }
                  }
                  row(){
                    "Abstract"
                    inputText(editPresentation.presentation.abstract){
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationList"){
                      list(){
                        for ( researchProject9 : ResearchProject in editPresentation.presentation.projects ) {
                          listitem(){
                            text(researchProject9.name){
                            }
                            " "
                            actionLink("[X]", editPresentation.removeResearchProject2(researchProject9)){
                            }
                            action removeResearchProject2 ( researchProject9 : ResearchProject )
                            {
                              editPresentation.presentation.projects.remove(researchProject9);
                            }
                          }
                        }
                      }
                      select ( researchProject10 : ResearchProject , Add ResearchProject , addResearchProject2(researchProject10) )
                      action addResearchProject2 ( researchProject9 : ResearchProject )
                      {
                        editPresentation.presentation.projects.add(researchProject9);
                      }
                    }
                  }
                }
              }
              action("Save", editPresentation.save()){
              }
              action("Cancel", editPresentation.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewPresentation(editPresentation.presentation);
          }
          action save ( )
          {
            editPresentation.presentation.save();
            return viewPresentation(editPresentation.presentation);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createPresentation () {
    var presentation : Presentation := Presentation{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person53 : Person ) {
                    listitem(){
                      navigate(viewPerson(person53)){
                        text(person53.name){
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
                  for ( project48 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project48)){
                        text(project48.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Presentation"
            }
            form(){
              table(){
                div("editRowsPresentation"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(createPresentation.presentation.title){
                    }
                  }
                  row(){
                    "Speaker"
                    div("inputSimpleRefAssociation"){
                      text(createPresentation.presentation.speaker.name){
                      }
                      " "
                      select ( person127 : Person , Select , setPerson9(person127) )
                      action setPerson9 ( person128 : Person )
                      {
                        presentation.speaker := person128;
                      }
                    }
                  }
                  row(){
                    "Date"
                    inputDate(createPresentation.presentation.date){
                    }
                  }
                  row(){
                    "Time"
                    inputDate(createPresentation.presentation.time){
                    }
                  }
                  row(){
                    "End"
                    inputDate(createPresentation.presentation.end){
                    }
                  }
                  row(){
                    "Venue"
                    inputString(createPresentation.presentation.venue){
                    }
                  }
                  row(){
                    "Abstract"
                    inputText(createPresentation.presentation.abstract){
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationList"){
                      list(){
                        for ( researchProject11 : ResearchProject in createPresentation.presentation.projects ) {
                          listitem(){
                            text(researchProject11.name){
                            }
                            " "
                            actionLink("[X]", createPresentation.removeResearchProject3(researchProject11)){
                            }
                            action removeResearchProject3 ( researchProject11 : ResearchProject )
                            {
                              createPresentation.presentation.projects.remove(researchProject11);
                            }
                          }
                        }
                      }
                      select ( researchProject12 : ResearchProject , Add ResearchProject , addResearchProject3(researchProject12) )
                      action addResearchProject3 ( researchProject11 : ResearchProject )
                      {
                        createPresentation.presentation.projects.add(researchProject11);
                      }
                    }
                  }
                }
              }
              action("Save", createPresentation.save()){
              }
              action("Cancel", createPresentation.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createPresentation.presentation.save();
            return viewPresentation(createPresentation.presentation);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewPresentation (presentation : Presentation) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person54 : Person ) {
                    listitem(){
                      navigate(viewPerson(person54)){
                        text(person54.name){
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
                  for ( project49 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project49)){
                        text(project49.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editPresentation(viewPresentation.presentation)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewPresentation.presentation.name){
              }
            }
            div("viewRowsPresentation"){
              div("viewRowsObject"){
              }
              par(){
                "Title"
                " : "
                text(viewPresentation.presentation.title){
                }
              }
              section(){
                header(){
                  "Speaker"
                }
                list(){
                  listitem(){
                    navigate(viewPerson(viewPresentation.presentation.speaker)){
                      text(viewPresentation.presentation.speaker.name){
                      }
                    }
                  }
                }
              }
              par(){
                "Date"
                " : "
                outputDate(viewPresentation.presentation.date){
                }
              }
              par(){
                "Time"
                " : "
                outputDate(viewPresentation.presentation.time){
                }
              }
              par(){
                "End"
                " : "
                outputDate(viewPresentation.presentation.end){
                }
              }
              par(){
                "Venue"
                " : "
                text(viewPresentation.presentation.venue){
                }
              }
              section(){
                header(){
                  "Abstract"
                }
                outputText(viewPresentation.presentation.abstract){
                }
              }
              section(){
                header(){
                  "Projects"
                }
                list(){
                  for ( researchProject13 : ResearchProject in viewPresentation.presentation.projects ) {
                    listitem(){
                      navigate(viewResearchProject(researchProject13)){
                        text(researchProject13.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allPresentation () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group17 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group17)){
                  text(group17.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person55 : Person ) {
                    listitem(){
                      navigate(viewPerson(person55)){
                        text(person55.name){
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
                  for ( project50 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project50)){
                        text(project50.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Presentation"
            }
            form(){
              list(){
                for ( presentation4 : Presentation ) {
                  listitem(){
                    navigate(viewPresentation(presentation4)){
                      text(presentation4.name){
                      }
                    }
                    " "
                    actionLink("[X]", allPresentation.removePresentation(presentation4)){
                    }
                    action removePresentation ( presentation5 : Presentation )
                    {
                      presentation5.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editPublication (publication : Publication) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person56 : Person ) {
                    listitem(){
                      navigate(viewPerson(person56)){
                        text(person56.name){
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
                  for ( project51 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project51)){
                        text(project51.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Publication"
              " "
              text(editPublication.publication.name){
              }
            }
            form(){
              table(){
                div("editRowsPublication"){
                  row(){
                    "Title"
                    inputString(editPublication.publication.title){
                    }
                  }
                  row(){
                    "Subtitle"
                    inputString(editPublication.publication.subtitle){
                    }
                  }
                  row(){
                    "Authors"
                    div("inputAssociationList"){
                      list(){
                        for ( person129 : Person in editPublication.publication.authors ) {
                          listitem(){
                            text(person129.name){
                            }
                            " "
                            actionLink("[X]", editPublication.removePerson0(person129)){
                            }
                            action removePerson0 ( person129 : Person )
                            {
                              editPublication.publication.authors.remove(person129);
                            }
                          }
                        }
                      }
                      select ( person130 : Person , Add Person , addPerson0(person130) )
                      action addPerson0 ( person129 : Person )
                      {
                        editPublication.publication.authors.add(person129);
                      }
                    }
                  }
                  row(){
                    ""
                    table(){
                      var newAuthor0 : Person := Person{} ;
                      row(){
                        "Fullname"
                        inputString(editPublication.newAuthor0.fullname){
                        }
                      }
                      row(){
                        "Email"
                        inputEmail(editPublication.newAuthor0.email){
                        }
                      }
                      row(){
                        ""
                        action("Add new author", editPublication.addNewAuthor()){
                        }
                      }
                      action addNewAuthor ( )
                      {
                        editPublication.publication.authors.add(editPublication.newAuthor0);
                        newAuthor0 := Person{};
                      }
                    }
                  }
                  row(){
                    "Year"
                    inputInt(editPublication.publication.year){
                    }
                  }
                  row(){
                    "Abstract"
                    inputText(editPublication.publication.abstract){
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationSet"){
                      list(){
                        for ( researchProject14 : ResearchProject in editPublication.publication.projectsList ) {
                          div("listAssociation"){
                            listitem(){
                              text(researchProject14.name){
                              }
                              " "
                              actionLink("[X]", editPublication.removeResearchProject4(researchProject14)){
                              }
                              action removeResearchProject4 ( researchProject14 : ResearchProject )
                              {
                                editPublication.publication.projects.remove(researchProject14);
                              }
                            }
                          }
                        }
                      }
                      select ( researchProject15 : ResearchProject , Add ResearchProject , addResearchProject4(researchProject15) )
                      action addResearchProject4 ( researchProject14 : ResearchProject )
                      {
                        editPublication.publication.projects.add(researchProject14);
                      }
                    }
                  }
                  row(){
                    "Pdf"
                    inputURL(editPublication.publication.pdf){
                    }
                  }
                }
              }
              action("Save", editPublication.save()){
              }
              action("Cancel", editPublication.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewPublication(editPublication.publication);
          }
          action save ( )
          {
            editPublication.publication.save();
            return viewPublication(editPublication.publication);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createPublication () {
    var publication : Publication := Publication{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person57 : Person ) {
                    listitem(){
                      navigate(viewPerson(person57)){
                        text(person57.name){
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
                  for ( project52 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project52)){
                        text(project52.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Publication"
            }
            form(){
              table(){
                div("editRowsPublication"){
                  row(){
                    "Title"
                    inputString(createPublication.publication.title){
                    }
                  }
                  row(){
                    "Subtitle"
                    inputString(createPublication.publication.subtitle){
                    }
                  }
                  row(){
                    "Authors"
                    div("inputAssociationList"){
                      list(){
                        for ( person131 : Person in createPublication.publication.authors ) {
                          listitem(){
                            text(person131.name){
                            }
                            " "
                            actionLink("[X]", createPublication.removePerson1(person131)){
                            }
                            action removePerson1 ( person131 : Person )
                            {
                              createPublication.publication.authors.remove(person131);
                            }
                          }
                        }
                      }
                      select ( person132 : Person , Add Person , addPerson1(person132) )
                      action addPerson1 ( person131 : Person )
                      {
                        createPublication.publication.authors.add(person131);
                      }
                    }
                  }
                  row(){
                    ""
                    table(){
                      var newAuthor1 : Person := Person{} ;
                      row(){
                        "Fullname"
                        inputString(createPublication.newAuthor1.fullname){
                        }
                      }
                      row(){
                        "Email"
                        inputEmail(createPublication.newAuthor1.email){
                        }
                      }
                      row(){
                        ""
                        action("Add new author", createPublication.addNewAuthor()){
                        }
                      }
                      action addNewAuthor ( )
                      {
                        createPublication.publication.authors.add(createPublication.newAuthor1);
                        newAuthor1 := Person{};
                      }
                    }
                  }
                  row(){
                    "Year"
                    inputInt(createPublication.publication.year){
                    }
                  }
                  row(){
                    "Abstract"
                    inputText(createPublication.publication.abstract){
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationSet"){
                      list(){
                        for ( researchProject16 : ResearchProject in createPublication.publication.projectsList ) {
                          div("listAssociation"){
                            listitem(){
                              text(researchProject16.name){
                              }
                              " "
                              actionLink("[X]", createPublication.removeResearchProject5(researchProject16)){
                              }
                              action removeResearchProject5 ( researchProject16 : ResearchProject )
                              {
                                createPublication.publication.projects.remove(researchProject16);
                              }
                            }
                          }
                        }
                      }
                      select ( researchProject17 : ResearchProject , Add ResearchProject , addResearchProject5(researchProject17) )
                      action addResearchProject5 ( researchProject16 : ResearchProject )
                      {
                        createPublication.publication.projects.add(researchProject16);
                      }
                    }
                  }
                  row(){
                    "Pdf"
                    inputURL(createPublication.publication.pdf){
                    }
                  }
                }
              }
              action("Save", createPublication.save()){
              }
              action("Cancel", createPublication.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createPublication.publication.save();
            return viewPublication(createPublication.publication);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewPublication (publication : Publication) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person58 : Person ) {
                    listitem(){
                      navigate(viewPerson(person58)){
                        text(person58.name){
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
                  for ( project53 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project53)){
                        text(project53.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editPublication(viewPublication.publication)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewPublication.publication.name){
              }
            }
            div("viewRowsPublication"){
              div("viewRowsObject"){
              }
              par(){
                "Title"
                " : "
                text(viewPublication.publication.title){
                }
              }
              par(){
                "Subtitle"
                " : "
                text(viewPublication.publication.subtitle){
                }
              }
              par(){
                "Year"
                " : "
                text(viewPublication.publication.year){
                }
              }
              par(){
                "Pdf"
                " : "
                navigate(url(viewPublication.publication.pdf)){
                  text(viewPublication.publication.pdf){
                  }
                }
              }
              section(){
                header(){
                  "Authors"
                }
                list(){
                  for ( person133 : Person in viewPublication.publication.authors ) {
                    listitem(){
                      navigate(viewPerson(person133)){
                        text(person133.name){
                        }
                      }
                    }
                  }
                }
              }
              section(){
                header(){
                  "Abstract"
                }
                outputText(viewPublication.publication.abstract){
                }
              }
              section(){
                header(){
                  "Projects"
                }
                list(){
                  for ( researchProject18 : ResearchProject in viewPublication.publication.projectsList ) {
                    listitem(){
                      navigate(viewResearchProject(researchProject18)){
                        text(researchProject18.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allPublication () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group18 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group18)){
                  text(group18.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person59 : Person ) {
                    listitem(){
                      navigate(viewPerson(person59)){
                        text(person59.name){
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
                  for ( project54 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project54)){
                        text(project54.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Publication"
            }
            form(){
              list(){
                for ( publication4 : Publication ) {
                  listitem(){
                    navigate(viewPublication(publication4)){
                      text(publication4.name){
                      }
                    }
                    " "
                    actionLink("[X]", allPublication.removePublication(publication4)){
                    }
                    action removePublication ( publication5 : Publication )
                    {
                      publication5.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editTechnicalReport (technicalReport : TechnicalReport) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person60 : Person ) {
                    listitem(){
                      navigate(viewPerson(person60)){
                        text(person60.name){
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
                  for ( project55 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project55)){
                        text(project55.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "TechnicalReport"
              " "
              text(editTechnicalReport.technicalReport.name){
              }
            }
            form(){
              table(){
                div("editRowsTechnicalReport"){
                  div("editRowsPublication"){
                    row(){
                      "Title"
                      inputString(editTechnicalReport.technicalReport.title){
                      }
                    }
                    row(){
                      "Subtitle"
                      inputString(editTechnicalReport.technicalReport.subtitle){
                      }
                    }
                    row(){
                      "Authors"
                      div("inputAssociationList"){
                        list(){
                          for ( person134 : Person in editTechnicalReport.technicalReport.authors ) {
                            listitem(){
                              text(person134.name){
                              }
                              " "
                              actionLink("[X]", editTechnicalReport.removePerson2(person134)){
                              }
                              action removePerson2 ( person134 : Person )
                              {
                                editTechnicalReport.technicalReport.authors.remove(person134);
                              }
                            }
                          }
                        }
                        select ( person135 : Person , Add Person , addPerson2(person135) )
                        action addPerson2 ( person134 : Person )
                        {
                          editTechnicalReport.technicalReport.authors.add(person134);
                        }
                      }
                    }
                    row(){
                      ""
                      table(){
                        var newAuthor2 : Person := Person{} ;
                        row(){
                          "Fullname"
                          inputString(editTechnicalReport.newAuthor2.fullname){
                          }
                        }
                        row(){
                          "Email"
                          inputEmail(editTechnicalReport.newAuthor2.email){
                          }
                        }
                        row(){
                          ""
                          action("Add new author", editTechnicalReport.addNewAuthor()){
                          }
                        }
                        action addNewAuthor ( )
                        {
                          editTechnicalReport.technicalReport.authors.add(editTechnicalReport.newAuthor2);
                          newAuthor2 := Person{};
                        }
                      }
                    }
                    row(){
                      "Year"
                      inputInt(editTechnicalReport.technicalReport.year){
                      }
                    }
                    row(){
                      "Abstract"
                      inputText(editTechnicalReport.technicalReport.abstract){
                      }
                    }
                    row(){
                      "Projects"
                      div("inputAssociationSet"){
                        list(){
                          for ( researchProject19 : ResearchProject in editTechnicalReport.technicalReport.projectsList ) {
                            div("listAssociation"){
                              listitem(){
                                text(researchProject19.name){
                                }
                                " "
                                actionLink("[X]", editTechnicalReport.removeResearchProject6(researchProject19)){
                                }
                                action removeResearchProject6 ( researchProject19 : ResearchProject )
                                {
                                  editTechnicalReport.technicalReport.projects.remove(researchProject19);
                                }
                              }
                            }
                          }
                        }
                        select ( researchProject20 : ResearchProject , Add ResearchProject , addResearchProject6(researchProject20) )
                        action addResearchProject6 ( researchProject19 : ResearchProject )
                        {
                          editTechnicalReport.technicalReport.projects.add(researchProject19);
                        }
                      }
                    }
                    row(){
                      "Pdf"
                      inputURL(editTechnicalReport.technicalReport.pdf){
                      }
                    }
                  }
                  row(){
                    "Number"
                    inputInt(editTechnicalReport.technicalReport.number){
                    }
                  }
                  row(){
                    "Document"
                    inputText(editTechnicalReport.technicalReport.document){
                    }
                  }
                  row(){
                    "Preprintof"
                    div("inputSimpleRefAssociation"){
                      text(editTechnicalReport.technicalReport.preprintof.name){
                      }
                      " "
                      select ( publication15 : Publication , Select , setPublication0(publication15) )
                      action setPublication0 ( publication16 : Publication )
                      {
                        technicalReport.preprintof := publication16;
                      }
                    }
                  }
                }
              }
              action("Save", editTechnicalReport.save()){
              }
              action("Cancel", editTechnicalReport.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewTechnicalReport(editTechnicalReport.technicalReport);
          }
          action save ( )
          {
            editTechnicalReport.technicalReport.save();
            return viewTechnicalReport(editTechnicalReport.technicalReport);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createTechnicalReport () {
    var technicalReport : TechnicalReport := TechnicalReport{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person61 : Person ) {
                    listitem(){
                      navigate(viewPerson(person61)){
                        text(person61.name){
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
                  for ( project56 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project56)){
                        text(project56.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "TechnicalReport"
            }
            form(){
              table(){
                div("editRowsTechnicalReport"){
                  div("editRowsPublication"){
                    row(){
                      "Title"
                      inputString(createTechnicalReport.technicalReport.title){
                      }
                    }
                    row(){
                      "Subtitle"
                      inputString(createTechnicalReport.technicalReport.subtitle){
                      }
                    }
                    row(){
                      "Authors"
                      div("inputAssociationList"){
                        list(){
                          for ( person136 : Person in createTechnicalReport.technicalReport.authors ) {
                            listitem(){
                              text(person136.name){
                              }
                              " "
                              actionLink("[X]", createTechnicalReport.removePerson3(person136)){
                              }
                              action removePerson3 ( person136 : Person )
                              {
                                createTechnicalReport.technicalReport.authors.remove(person136);
                              }
                            }
                          }
                        }
                        select ( person137 : Person , Add Person , addPerson3(person137) )
                        action addPerson3 ( person136 : Person )
                        {
                          createTechnicalReport.technicalReport.authors.add(person136);
                        }
                      }
                    }
                    row(){
                      ""
                      table(){
                        var newAuthor3 : Person := Person{} ;
                        row(){
                          "Fullname"
                          inputString(createTechnicalReport.newAuthor3.fullname){
                          }
                        }
                        row(){
                          "Email"
                          inputEmail(createTechnicalReport.newAuthor3.email){
                          }
                        }
                        row(){
                          ""
                          action("Add new author", createTechnicalReport.addNewAuthor()){
                          }
                        }
                        action addNewAuthor ( )
                        {
                          createTechnicalReport.technicalReport.authors.add(createTechnicalReport.newAuthor3);
                          newAuthor3 := Person{};
                        }
                      }
                    }
                    row(){
                      "Year"
                      inputInt(createTechnicalReport.technicalReport.year){
                      }
                    }
                    row(){
                      "Abstract"
                      inputText(createTechnicalReport.technicalReport.abstract){
                      }
                    }
                    row(){
                      "Projects"
                      div("inputAssociationSet"){
                        list(){
                          for ( researchProject21 : ResearchProject in createTechnicalReport.technicalReport.projectsList ) {
                            div("listAssociation"){
                              listitem(){
                                text(researchProject21.name){
                                }
                                " "
                                actionLink("[X]", createTechnicalReport.removeResearchProject7(researchProject21)){
                                }
                                action removeResearchProject7 ( researchProject21 : ResearchProject )
                                {
                                  createTechnicalReport.technicalReport.projects.remove(researchProject21);
                                }
                              }
                            }
                          }
                        }
                        select ( researchProject22 : ResearchProject , Add ResearchProject , addResearchProject7(researchProject22) )
                        action addResearchProject7 ( researchProject21 : ResearchProject )
                        {
                          createTechnicalReport.technicalReport.projects.add(researchProject21);
                        }
                      }
                    }
                    row(){
                      "Pdf"
                      inputURL(createTechnicalReport.technicalReport.pdf){
                      }
                    }
                  }
                  row(){
                    "Number"
                    inputInt(createTechnicalReport.technicalReport.number){
                    }
                  }
                  row(){
                    "Document"
                    inputText(createTechnicalReport.technicalReport.document){
                    }
                  }
                  row(){
                    "Preprintof"
                    div("inputSimpleRefAssociation"){
                      text(createTechnicalReport.technicalReport.preprintof.name){
                      }
                      " "
                      select ( publication17 : Publication , Select , setPublication1(publication17) )
                      action setPublication1 ( publication18 : Publication )
                      {
                        technicalReport.preprintof := publication18;
                      }
                    }
                  }
                }
              }
              action("Save", createTechnicalReport.save()){
              }
              action("Cancel", createTechnicalReport.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createTechnicalReport.technicalReport.save();
            return viewTechnicalReport(createTechnicalReport.technicalReport);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewTechnicalReport (technicalReport : TechnicalReport) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person62 : Person ) {
                    listitem(){
                      navigate(viewPerson(person62)){
                        text(person62.name){
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
                  for ( project57 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project57)){
                        text(project57.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editTechnicalReport(viewTechnicalReport.technicalReport)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewTechnicalReport.technicalReport.name){
              }
            }
            div("viewRowsTechnicalReport"){
              div("viewRowsPublication"){
                div("viewRowsObject"){
                }
                par(){
                  "Title"
                  " : "
                  text(viewTechnicalReport.technicalReport.title){
                  }
                }
                par(){
                  "Subtitle"
                  " : "
                  text(viewTechnicalReport.technicalReport.subtitle){
                  }
                }
                par(){
                  "Year"
                  " : "
                  text(viewTechnicalReport.technicalReport.year){
                  }
                }
                par(){
                  "Pdf"
                  " : "
                  navigate(url(viewTechnicalReport.technicalReport.pdf)){
                    text(viewTechnicalReport.technicalReport.pdf){
                    }
                  }
                }
                section(){
                  header(){
                    "Authors"
                  }
                  list(){
                    for ( person138 : Person in viewTechnicalReport.technicalReport.authors ) {
                      listitem(){
                        navigate(viewPerson(person138)){
                          text(person138.name){
                          }
                        }
                      }
                    }
                  }
                }
                section(){
                  header(){
                    "Abstract"
                  }
                  outputText(viewTechnicalReport.technicalReport.abstract){
                  }
                }
                section(){
                  header(){
                    "Projects"
                  }
                  list(){
                    for ( researchProject23 : ResearchProject in viewTechnicalReport.technicalReport.projectsList ) {
                      listitem(){
                        navigate(viewResearchProject(researchProject23)){
                          text(researchProject23.name){
                          }
                        }
                      }
                    }
                  }
                }
              }
              par(){
                "Number"
                " : "
                text(viewTechnicalReport.technicalReport.number){
                }
              }
              section(){
                header(){
                  "Document"
                }
                outputText(viewTechnicalReport.technicalReport.document){
                }
              }
              section(){
                header(){
                  "Preprintof"
                }
                list(){
                  listitem(){
                    navigate(viewPublication(viewTechnicalReport.technicalReport.preprintof)){
                      text(viewTechnicalReport.technicalReport.preprintof.name){
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allTechnicalReport () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group19 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group19)){
                  text(group19.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person63 : Person ) {
                    listitem(){
                      navigate(viewPerson(person63)){
                        text(person63.name){
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
                  for ( project58 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project58)){
                        text(project58.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "TechnicalReport"
            }
            form(){
              list(){
                for ( technicalReport3 : TechnicalReport ) {
                  listitem(){
                    navigate(viewTechnicalReport(technicalReport3)){
                      text(technicalReport3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allTechnicalReport.removeTechnicalReport(technicalReport3)){
                    }
                    action removeTechnicalReport ( technicalReport4 : TechnicalReport )
                    {
                      technicalReport4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editInProceedings (inProceedings : InProceedings) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person64 : Person ) {
                    listitem(){
                      navigate(viewPerson(person64)){
                        text(person64.name){
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
                  for ( project59 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project59)){
                        text(project59.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "InProceedings"
              " "
              text(editInProceedings.inProceedings.name){
              }
            }
            form(){
              table(){
                div("editRowsInProceedings"){
                  div("editRowsPublication"){
                    row(){
                      "Title"
                      inputString(editInProceedings.inProceedings.title){
                      }
                    }
                    row(){
                      "Subtitle"
                      inputString(editInProceedings.inProceedings.subtitle){
                      }
                    }
                    row(){
                      "Authors"
                      div("inputAssociationList"){
                        list(){
                          for ( person139 : Person in editInProceedings.inProceedings.authors ) {
                            listitem(){
                              text(person139.name){
                              }
                              " "
                              actionLink("[X]", editInProceedings.removePerson4(person139)){
                              }
                              action removePerson4 ( person139 : Person )
                              {
                                editInProceedings.inProceedings.authors.remove(person139);
                              }
                            }
                          }
                        }
                        select ( person140 : Person , Add Person , addPerson4(person140) )
                        action addPerson4 ( person139 : Person )
                        {
                          editInProceedings.inProceedings.authors.add(person139);
                        }
                      }
                    }
                    row(){
                      ""
                      table(){
                        var newAuthor4 : Person := Person{} ;
                        row(){
                          "Fullname"
                          inputString(editInProceedings.newAuthor4.fullname){
                          }
                        }
                        row(){
                          "Email"
                          inputEmail(editInProceedings.newAuthor4.email){
                          }
                        }
                        row(){
                          ""
                          action("Add new author", editInProceedings.addNewAuthor()){
                          }
                        }
                        action addNewAuthor ( )
                        {
                          editInProceedings.inProceedings.authors.add(editInProceedings.newAuthor4);
                          newAuthor4 := Person{};
                        }
                      }
                    }
                    row(){
                      "Year"
                      inputInt(editInProceedings.inProceedings.year){
                      }
                    }
                    row(){
                      "Abstract"
                      inputText(editInProceedings.inProceedings.abstract){
                      }
                    }
                    row(){
                      "Projects"
                      div("inputAssociationSet"){
                        list(){
                          for ( researchProject24 : ResearchProject in editInProceedings.inProceedings.projectsList ) {
                            div("listAssociation"){
                              listitem(){
                                text(researchProject24.name){
                                }
                                " "
                                actionLink("[X]", editInProceedings.removeResearchProject8(researchProject24)){
                                }
                                action removeResearchProject8 ( researchProject24 : ResearchProject )
                                {
                                  editInProceedings.inProceedings.projects.remove(researchProject24);
                                }
                              }
                            }
                          }
                        }
                        select ( researchProject25 : ResearchProject , Add ResearchProject , addResearchProject8(researchProject25) )
                        action addResearchProject8 ( researchProject24 : ResearchProject )
                        {
                          editInProceedings.inProceedings.projects.add(researchProject24);
                        }
                      }
                    }
                    row(){
                      "Pdf"
                      inputURL(editInProceedings.inProceedings.pdf){
                      }
                    }
                  }
                  row(){
                    "Conference"
                    div("inputSimpleRefAssociation"){
                      text(editInProceedings.inProceedings.conference.name){
                      }
                      " "
                      select ( conference5 : Conference , Select , setConference0(conference5) )
                      action setConference0 ( conference6 : Conference )
                      {
                        inProceedings.conference := conference6;
                      }
                    }
                  }
                  row(){
                    "Pages"
                    inputString(editInProceedings.inProceedings.pages){
                    }
                  }
                }
              }
              action("Save", editInProceedings.save()){
              }
              action("Cancel", editInProceedings.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewInProceedings(editInProceedings.inProceedings);
          }
          action save ( )
          {
            editInProceedings.inProceedings.save();
            return viewInProceedings(editInProceedings.inProceedings);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createInProceedings () {
    var inProceedings : InProceedings := InProceedings{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person65 : Person ) {
                    listitem(){
                      navigate(viewPerson(person65)){
                        text(person65.name){
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
                  for ( project60 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project60)){
                        text(project60.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "InProceedings"
            }
            form(){
              table(){
                div("editRowsInProceedings"){
                  div("editRowsPublication"){
                    row(){
                      "Title"
                      inputString(createInProceedings.inProceedings.title){
                      }
                    }
                    row(){
                      "Subtitle"
                      inputString(createInProceedings.inProceedings.subtitle){
                      }
                    }
                    row(){
                      "Authors"
                      div("inputAssociationList"){
                        list(){
                          for ( person141 : Person in createInProceedings.inProceedings.authors ) {
                            listitem(){
                              text(person141.name){
                              }
                              " "
                              actionLink("[X]", createInProceedings.removePerson5(person141)){
                              }
                              action removePerson5 ( person141 : Person )
                              {
                                createInProceedings.inProceedings.authors.remove(person141);
                              }
                            }
                          }
                        }
                        select ( person142 : Person , Add Person , addPerson5(person142) )
                        action addPerson5 ( person141 : Person )
                        {
                          createInProceedings.inProceedings.authors.add(person141);
                        }
                      }
                    }
                    row(){
                      ""
                      table(){
                        var newAuthor5 : Person := Person{} ;
                        row(){
                          "Fullname"
                          inputString(createInProceedings.newAuthor5.fullname){
                          }
                        }
                        row(){
                          "Email"
                          inputEmail(createInProceedings.newAuthor5.email){
                          }
                        }
                        row(){
                          ""
                          action("Add new author", createInProceedings.addNewAuthor()){
                          }
                        }
                        action addNewAuthor ( )
                        {
                          createInProceedings.inProceedings.authors.add(createInProceedings.newAuthor5);
                          newAuthor5 := Person{};
                        }
                      }
                    }
                    row(){
                      "Year"
                      inputInt(createInProceedings.inProceedings.year){
                      }
                    }
                    row(){
                      "Abstract"
                      inputText(createInProceedings.inProceedings.abstract){
                      }
                    }
                    row(){
                      "Projects"
                      div("inputAssociationSet"){
                        list(){
                          for ( researchProject26 : ResearchProject in createInProceedings.inProceedings.projectsList ) {
                            div("listAssociation"){
                              listitem(){
                                text(researchProject26.name){
                                }
                                " "
                                actionLink("[X]", createInProceedings.removeResearchProject9(researchProject26)){
                                }
                                action removeResearchProject9 ( researchProject26 : ResearchProject )
                                {
                                  createInProceedings.inProceedings.projects.remove(researchProject26);
                                }
                              }
                            }
                          }
                        }
                        select ( researchProject27 : ResearchProject , Add ResearchProject , addResearchProject9(researchProject27) )
                        action addResearchProject9 ( researchProject26 : ResearchProject )
                        {
                          createInProceedings.inProceedings.projects.add(researchProject26);
                        }
                      }
                    }
                    row(){
                      "Pdf"
                      inputURL(createInProceedings.inProceedings.pdf){
                      }
                    }
                  }
                  row(){
                    "Conference"
                    div("inputSimpleRefAssociation"){
                      text(createInProceedings.inProceedings.conference.name){
                      }
                      " "
                      select ( conference7 : Conference , Select , setConference1(conference7) )
                      action setConference1 ( conference8 : Conference )
                      {
                        inProceedings.conference := conference8;
                      }
                    }
                  }
                  row(){
                    "Pages"
                    inputString(createInProceedings.inProceedings.pages){
                    }
                  }
                }
              }
              action("Save", createInProceedings.save()){
              }
              action("Cancel", createInProceedings.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createInProceedings.inProceedings.save();
            return viewInProceedings(createInProceedings.inProceedings);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewInProceedings (inProceedings : InProceedings) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person66 : Person ) {
                    listitem(){
                      navigate(viewPerson(person66)){
                        text(person66.name){
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
                  for ( project61 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project61)){
                        text(project61.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editInProceedings(viewInProceedings.inProceedings)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewInProceedings.inProceedings.name){
              }
            }
            div("viewRowsInProceedings"){
              div("viewRowsPublication"){
                div("viewRowsObject"){
                }
                par(){
                  "Title"
                  " : "
                  text(viewInProceedings.inProceedings.title){
                  }
                }
                par(){
                  "Subtitle"
                  " : "
                  text(viewInProceedings.inProceedings.subtitle){
                  }
                }
                par(){
                  "Year"
                  " : "
                  text(viewInProceedings.inProceedings.year){
                  }
                }
                par(){
                  "Pdf"
                  " : "
                  navigate(url(viewInProceedings.inProceedings.pdf)){
                    text(viewInProceedings.inProceedings.pdf){
                    }
                  }
                }
                section(){
                  header(){
                    "Authors"
                  }
                  list(){
                    for ( person143 : Person in viewInProceedings.inProceedings.authors ) {
                      listitem(){
                        navigate(viewPerson(person143)){
                          text(person143.name){
                          }
                        }
                      }
                    }
                  }
                }
                section(){
                  header(){
                    "Abstract"
                  }
                  outputText(viewInProceedings.inProceedings.abstract){
                  }
                }
                section(){
                  header(){
                    "Projects"
                  }
                  list(){
                    for ( researchProject28 : ResearchProject in viewInProceedings.inProceedings.projectsList ) {
                      listitem(){
                        navigate(viewResearchProject(researchProject28)){
                          text(researchProject28.name){
                          }
                        }
                      }
                    }
                  }
                }
              }
              section(){
                header(){
                  "Conference"
                }
                list(){
                  listitem(){
                    navigate(viewConference(viewInProceedings.inProceedings.conference)){
                      text(viewInProceedings.inProceedings.conference.name){
                      }
                    }
                  }
                }
              }
              par(){
                "Pages"
                " : "
                text(viewInProceedings.inProceedings.pages){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allInProceedings () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group20 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group20)){
                  text(group20.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person67 : Person ) {
                    listitem(){
                      navigate(viewPerson(person67)){
                        text(person67.name){
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
                  for ( project62 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project62)){
                        text(project62.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "InProceedings"
            }
            form(){
              list(){
                for ( inProceedings3 : InProceedings ) {
                  listitem(){
                    navigate(viewInProceedings(inProceedings3)){
                      text(inProceedings3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allInProceedings.removeInProceedings(inProceedings3)){
                    }
                    action removeInProceedings ( inProceedings4 : InProceedings )
                    {
                      inProceedings4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editConference (conference : Conference) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person68 : Person ) {
                    listitem(){
                      navigate(viewPerson(person68)){
                        text(person68.name){
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
                  for ( project63 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project63)){
                        text(project63.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Conference"
              " "
              text(editConference.conference.name){
              }
            }
            form(){
              table(){
                div("editRowsConference"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(editConference.conference.fullname){
                    }
                  }
                  row(){
                    "Acronym"
                    inputString(editConference.conference.acronym){
                    }
                  }
                  row(){
                    "Booktitle"
                    inputString(editConference.conference.booktitle){
                    }
                  }
                  row(){
                    "Editors"
                    div("inputAssociationList"){
                      list(){
                        for ( person144 : Person in editConference.conference.editors ) {
                          listitem(){
                            text(person144.name){
                            }
                            " "
                            actionLink("[X]", editConference.removePerson6(person144)){
                            }
                            action removePerson6 ( person144 : Person )
                            {
                              editConference.conference.editors.remove(person144);
                            }
                          }
                        }
                      }
                      select ( person145 : Person , Add Person , addPerson6(person145) )
                      action addPerson6 ( person144 : Person )
                      {
                        editConference.conference.editors.add(person144);
                      }
                    }
                  }
                  row(){
                    "Place"
                    inputString(editConference.conference.place){
                    }
                  }
                  row(){
                    "Year"
                    inputInt(editConference.conference.year){
                    }
                  }
                  row(){
                    "Month"
                    inputString(editConference.conference.month){
                    }
                  }
                  row(){
                    "Url"
                    inputURL(editConference.conference.url){
                    }
                  }
                  row(){
                    "Acceptance"
                    inputInt(editConference.conference.acceptance){
                    }
                  }
                }
              }
              action("Save", editConference.save()){
              }
              action("Cancel", editConference.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewConference(editConference.conference);
          }
          action save ( )
          {
            editConference.conference.save();
            return viewConference(editConference.conference);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createConference () {
    var conference : Conference := Conference{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person69 : Person ) {
                    listitem(){
                      navigate(viewPerson(person69)){
                        text(person69.name){
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
                  for ( project64 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project64)){
                        text(project64.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Conference"
            }
            form(){
              table(){
                div("editRowsConference"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(createConference.conference.fullname){
                    }
                  }
                  row(){
                    "Acronym"
                    inputString(createConference.conference.acronym){
                    }
                  }
                  row(){
                    "Booktitle"
                    inputString(createConference.conference.booktitle){
                    }
                  }
                  row(){
                    "Editors"
                    div("inputAssociationList"){
                      list(){
                        for ( person146 : Person in createConference.conference.editors ) {
                          listitem(){
                            text(person146.name){
                            }
                            " "
                            actionLink("[X]", createConference.removePerson7(person146)){
                            }
                            action removePerson7 ( person146 : Person )
                            {
                              createConference.conference.editors.remove(person146);
                            }
                          }
                        }
                      }
                      select ( person147 : Person , Add Person , addPerson7(person147) )
                      action addPerson7 ( person146 : Person )
                      {
                        createConference.conference.editors.add(person146);
                      }
                    }
                  }
                  row(){
                    "Place"
                    inputString(createConference.conference.place){
                    }
                  }
                  row(){
                    "Year"
                    inputInt(createConference.conference.year){
                    }
                  }
                  row(){
                    "Month"
                    inputString(createConference.conference.month){
                    }
                  }
                  row(){
                    "Url"
                    inputURL(createConference.conference.url){
                    }
                  }
                  row(){
                    "Acceptance"
                    inputInt(createConference.conference.acceptance){
                    }
                  }
                }
              }
              action("Save", createConference.save()){
              }
              action("Cancel", createConference.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createConference.conference.save();
            return viewConference(createConference.conference);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewConference (conference : Conference) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person70 : Person ) {
                    listitem(){
                      navigate(viewPerson(person70)){
                        text(person70.name){
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
                  for ( project65 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project65)){
                        text(project65.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editConference(viewConference.conference)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewConference.conference.name){
              }
            }
            div("viewRowsConference"){
              div("viewRowsObject"){
              }
              par(){
                "Fullname"
                " : "
                text(viewConference.conference.fullname){
                }
              }
              par(){
                "Acronym"
                " : "
                text(viewConference.conference.acronym){
                }
              }
              par(){
                "Booktitle"
                " : "
                text(viewConference.conference.booktitle){
                }
              }
              section(){
                header(){
                  "Editors"
                }
                list(){
                  for ( person148 : Person in viewConference.conference.editors ) {
                    listitem(){
                      navigate(viewPerson(person148)){
                        text(person148.name){
                        }
                      }
                    }
                  }
                }
              }
              par(){
                "Place"
                " : "
                text(viewConference.conference.place){
                }
              }
              par(){
                "Year"
                " : "
                text(viewConference.conference.year){
                }
              }
              par(){
                "Month"
                " : "
                text(viewConference.conference.month){
                }
              }
              par(){
                "Url"
                " : "
                navigate(url(viewConference.conference.url)){
                  text(viewConference.conference.url){
                  }
                }
              }
              par(){
                "Acceptance"
                " : "
                text(viewConference.conference.acceptance){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allConference () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group21 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group21)){
                  text(group21.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person71 : Person ) {
                    listitem(){
                      navigate(viewPerson(person71)){
                        text(person71.name){
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
                  for ( project66 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project66)){
                        text(project66.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Conference"
            }
            form(){
              list(){
                for ( conference3 : Conference ) {
                  listitem(){
                    navigate(viewConference(conference3)){
                      text(conference3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allConference.removeConference(conference3)){
                    }
                    action removeConference ( conference4 : Conference )
                    {
                      conference4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editArticle (article : Article) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person72 : Person ) {
                    listitem(){
                      navigate(viewPerson(person72)){
                        text(person72.name){
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
                  for ( project67 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project67)){
                        text(project67.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Article"
              " "
              text(editArticle.article.name){
              }
            }
            form(){
              table(){
                div("editRowsArticle"){
                  div("editRowsPublication"){
                    row(){
                      "Title"
                      inputString(editArticle.article.title){
                      }
                    }
                    row(){
                      "Subtitle"
                      inputString(editArticle.article.subtitle){
                      }
                    }
                    row(){
                      "Authors"
                      div("inputAssociationList"){
                        list(){
                          for ( person149 : Person in editArticle.article.authors ) {
                            listitem(){
                              text(person149.name){
                              }
                              " "
                              actionLink("[X]", editArticle.removePerson8(person149)){
                              }
                              action removePerson8 ( person149 : Person )
                              {
                                editArticle.article.authors.remove(person149);
                              }
                            }
                          }
                        }
                        select ( person150 : Person , Add Person , addPerson8(person150) )
                        action addPerson8 ( person149 : Person )
                        {
                          editArticle.article.authors.add(person149);
                        }
                      }
                    }
                    row(){
                      ""
                      table(){
                        var newAuthor6 : Person := Person{} ;
                        row(){
                          "Fullname"
                          inputString(editArticle.newAuthor6.fullname){
                          }
                        }
                        row(){
                          "Email"
                          inputEmail(editArticle.newAuthor6.email){
                          }
                        }
                        row(){
                          ""
                          action("Add new author", editArticle.addNewAuthor()){
                          }
                        }
                        action addNewAuthor ( )
                        {
                          editArticle.article.authors.add(editArticle.newAuthor6);
                          newAuthor6 := Person{};
                        }
                      }
                    }
                    row(){
                      "Year"
                      inputInt(editArticle.article.year){
                      }
                    }
                    row(){
                      "Abstract"
                      inputText(editArticle.article.abstract){
                      }
                    }
                    row(){
                      "Projects"
                      div("inputAssociationSet"){
                        list(){
                          for ( researchProject29 : ResearchProject in editArticle.article.projectsList ) {
                            div("listAssociation"){
                              listitem(){
                                text(researchProject29.name){
                                }
                                " "
                                actionLink("[X]", editArticle.removeResearchProject10(researchProject29)){
                                }
                                action removeResearchProject10 ( researchProject29 : ResearchProject )
                                {
                                  editArticle.article.projects.remove(researchProject29);
                                }
                              }
                            }
                          }
                        }
                        select ( researchProject30 : ResearchProject , Add ResearchProject , addResearchProject10(researchProject30) )
                        action addResearchProject10 ( researchProject29 : ResearchProject )
                        {
                          editArticle.article.projects.add(researchProject29);
                        }
                      }
                    }
                    row(){
                      "Pdf"
                      inputURL(editArticle.article.pdf){
                      }
                    }
                  }
                  row(){
                    "Journal"
                    div("inputSimpleRefAssociation"){
                      text(editArticle.article.journal.name){
                      }
                      " "
                      select ( journal5 : Journal , Select , setJournal0(journal5) )
                      action setJournal0 ( journal6 : Journal )
                      {
                        article.journal := journal6;
                      }
                    }
                  }
                  row(){
                    "Pages"
                    inputString(editArticle.article.pages){
                    }
                  }
                  row(){
                    "Impact"
                    inputInt(editArticle.article.impact){
                    }
                  }
                }
              }
              action("Save", editArticle.save()){
              }
              action("Cancel", editArticle.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewArticle(editArticle.article);
          }
          action save ( )
          {
            editArticle.article.save();
            return viewArticle(editArticle.article);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createArticle () {
    var article : Article := Article{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person73 : Person ) {
                    listitem(){
                      navigate(viewPerson(person73)){
                        text(person73.name){
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
                  for ( project68 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project68)){
                        text(project68.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Article"
            }
            form(){
              table(){
                div("editRowsArticle"){
                  div("editRowsPublication"){
                    row(){
                      "Title"
                      inputString(createArticle.article.title){
                      }
                    }
                    row(){
                      "Subtitle"
                      inputString(createArticle.article.subtitle){
                      }
                    }
                    row(){
                      "Authors"
                      div("inputAssociationList"){
                        list(){
                          for ( person151 : Person in createArticle.article.authors ) {
                            listitem(){
                              text(person151.name){
                              }
                              " "
                              actionLink("[X]", createArticle.removePerson9(person151)){
                              }
                              action removePerson9 ( person151 : Person )
                              {
                                createArticle.article.authors.remove(person151);
                              }
                            }
                          }
                        }
                        select ( person152 : Person , Add Person , addPerson9(person152) )
                        action addPerson9 ( person151 : Person )
                        {
                          createArticle.article.authors.add(person151);
                        }
                      }
                    }
                    row(){
                      ""
                      table(){
                        var newAuthor7 : Person := Person{} ;
                        row(){
                          "Fullname"
                          inputString(createArticle.newAuthor7.fullname){
                          }
                        }
                        row(){
                          "Email"
                          inputEmail(createArticle.newAuthor7.email){
                          }
                        }
                        row(){
                          ""
                          action("Add new author", createArticle.addNewAuthor()){
                          }
                        }
                        action addNewAuthor ( )
                        {
                          createArticle.article.authors.add(createArticle.newAuthor7);
                          newAuthor7 := Person{};
                        }
                      }
                    }
                    row(){
                      "Year"
                      inputInt(createArticle.article.year){
                      }
                    }
                    row(){
                      "Abstract"
                      inputText(createArticle.article.abstract){
                      }
                    }
                    row(){
                      "Projects"
                      div("inputAssociationSet"){
                        list(){
                          for ( researchProject31 : ResearchProject in createArticle.article.projectsList ) {
                            div("listAssociation"){
                              listitem(){
                                text(researchProject31.name){
                                }
                                " "
                                actionLink("[X]", createArticle.removeResearchProject11(researchProject31)){
                                }
                                action removeResearchProject11 ( researchProject31 : ResearchProject )
                                {
                                  createArticle.article.projects.remove(researchProject31);
                                }
                              }
                            }
                          }
                        }
                        select ( researchProject32 : ResearchProject , Add ResearchProject , addResearchProject11(researchProject32) )
                        action addResearchProject11 ( researchProject31 : ResearchProject )
                        {
                          createArticle.article.projects.add(researchProject31);
                        }
                      }
                    }
                    row(){
                      "Pdf"
                      inputURL(createArticle.article.pdf){
                      }
                    }
                  }
                  row(){
                    "Journal"
                    div("inputSimpleRefAssociation"){
                      text(createArticle.article.journal.name){
                      }
                      " "
                      select ( journal7 : Journal , Select , setJournal1(journal7) )
                      action setJournal1 ( journal8 : Journal )
                      {
                        article.journal := journal8;
                      }
                    }
                  }
                  row(){
                    "Pages"
                    inputString(createArticle.article.pages){
                    }
                  }
                  row(){
                    "Impact"
                    inputInt(createArticle.article.impact){
                    }
                  }
                }
              }
              action("Save", createArticle.save()){
              }
              action("Cancel", createArticle.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createArticle.article.save();
            return viewArticle(createArticle.article);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewArticle (article : Article) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person74 : Person ) {
                    listitem(){
                      navigate(viewPerson(person74)){
                        text(person74.name){
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
                  for ( project69 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project69)){
                        text(project69.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editArticle(viewArticle.article)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewArticle.article.name){
              }
            }
            div("viewRowsArticle"){
              div("viewRowsPublication"){
                div("viewRowsObject"){
                }
                par(){
                  "Title"
                  " : "
                  text(viewArticle.article.title){
                  }
                }
                par(){
                  "Subtitle"
                  " : "
                  text(viewArticle.article.subtitle){
                  }
                }
                par(){
                  "Year"
                  " : "
                  text(viewArticle.article.year){
                  }
                }
                par(){
                  "Pdf"
                  " : "
                  navigate(url(viewArticle.article.pdf)){
                    text(viewArticle.article.pdf){
                    }
                  }
                }
                section(){
                  header(){
                    "Authors"
                  }
                  list(){
                    for ( person153 : Person in viewArticle.article.authors ) {
                      listitem(){
                        navigate(viewPerson(person153)){
                          text(person153.name){
                          }
                        }
                      }
                    }
                  }
                }
                section(){
                  header(){
                    "Abstract"
                  }
                  outputText(viewArticle.article.abstract){
                  }
                }
                section(){
                  header(){
                    "Projects"
                  }
                  list(){
                    for ( researchProject33 : ResearchProject in viewArticle.article.projectsList ) {
                      listitem(){
                        navigate(viewResearchProject(researchProject33)){
                          text(researchProject33.name){
                          }
                        }
                      }
                    }
                  }
                }
              }
              section(){
                header(){
                  "Journal"
                }
                list(){
                  listitem(){
                    navigate(viewJournal(viewArticle.article.journal)){
                      text(viewArticle.article.journal.name){
                      }
                    }
                  }
                }
              }
              par(){
                "Pages"
                " : "
                text(viewArticle.article.pages){
                }
              }
              par(){
                "Impact"
                " : "
                text(viewArticle.article.impact){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allArticle () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group22 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group22)){
                  text(group22.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person75 : Person ) {
                    listitem(){
                      navigate(viewPerson(person75)){
                        text(person75.name){
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
                  for ( project70 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project70)){
                        text(project70.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Article"
            }
            form(){
              list(){
                for ( article3 : Article ) {
                  listitem(){
                    navigate(viewArticle(article3)){
                      text(article3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allArticle.removeArticle(article3)){
                    }
                    action removeArticle ( article4 : Article )
                    {
                      article4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editJournal (journal : Journal) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person76 : Person ) {
                    listitem(){
                      navigate(viewPerson(person76)){
                        text(person76.name){
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
                  for ( project71 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project71)){
                        text(project71.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Journal"
              " "
              text(editJournal.journal.name){
              }
            }
            form(){
              table(){
                div("editRowsJournal"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(editJournal.journal.fullname){
                    }
                  }
                  row(){
                    "Acronym"
                    inputString(editJournal.journal.acronym){
                    }
                  }
                }
              }
              action("Save", editJournal.save()){
              }
              action("Cancel", editJournal.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewJournal(editJournal.journal);
          }
          action save ( )
          {
            editJournal.journal.save();
            return viewJournal(editJournal.journal);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createJournal () {
    var journal : Journal := Journal{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person77 : Person ) {
                    listitem(){
                      navigate(viewPerson(person77)){
                        text(person77.name){
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
                  for ( project72 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project72)){
                        text(project72.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Journal"
            }
            form(){
              table(){
                div("editRowsJournal"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(createJournal.journal.fullname){
                    }
                  }
                  row(){
                    "Acronym"
                    inputString(createJournal.journal.acronym){
                    }
                  }
                }
              }
              action("Save", createJournal.save()){
              }
              action("Cancel", createJournal.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createJournal.journal.save();
            return viewJournal(createJournal.journal);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewJournal (journal : Journal) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person78 : Person ) {
                    listitem(){
                      navigate(viewPerson(person78)){
                        text(person78.name){
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
                  for ( project73 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project73)){
                        text(project73.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editJournal(viewJournal.journal)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewJournal.journal.name){
              }
            }
            div("viewRowsJournal"){
              div("viewRowsObject"){
              }
              par(){
                "Fullname"
                " : "
                text(viewJournal.journal.fullname){
                }
              }
              par(){
                "Acronym"
                " : "
                text(viewJournal.journal.acronym){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allJournal () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group23 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group23)){
                  text(group23.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person79 : Person ) {
                    listitem(){
                      navigate(viewPerson(person79)){
                        text(person79.name){
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
                  for ( project74 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project74)){
                        text(project74.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Journal"
            }
            form(){
              list(){
                for ( journal3 : Journal ) {
                  listitem(){
                    navigate(viewJournal(journal3)){
                      text(journal3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allJournal.removeJournal(journal3)){
                    }
                    action removeJournal ( journal4 : Journal )
                    {
                      journal4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editResearchProject (researchProject : ResearchProject) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person80 : Person ) {
                    listitem(){
                      navigate(viewPerson(person80)){
                        text(person80.name){
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
                  for ( project75 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project75)){
                        text(project75.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "ResearchProject"
              " "
              text(editResearchProject.researchProject.name){
              }
            }
            form(){
              table(){
                div("editRowsResearchProject"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(editResearchProject.researchProject.fullname){
                    }
                  }
                  row(){
                    "Acronym"
                    inputString(editResearchProject.researchProject.acronym){
                    }
                  }
                  row(){
                    "Description"
                    inputText(editResearchProject.researchProject.description){
                    }
                  }
                  row(){
                    "Members"
                    div("inputAssociationSet"){
                      list(){
                        for ( person154 : Person in editResearchProject.researchProject.membersList ) {
                          div("listAssociation"){
                            listitem(){
                              text(person154.name){
                              }
                              " "
                              actionLink("[X]", editResearchProject.removePerson10(person154)){
                              }
                              action removePerson10 ( person154 : Person )
                              {
                                editResearchProject.researchProject.members.remove(person154);
                              }
                            }
                          }
                        }
                      }
                      select ( person155 : Person , Add Person , addPerson10(person155) )
                      action addPerson10 ( person154 : Person )
                      {
                        editResearchProject.researchProject.members.add(person154);
                      }
                    }
                  }
                  row(){
                    "Proposal"
                    div("inputSimpleRefAssociation"){
                      text(editResearchProject.researchProject.proposal.name){
                      }
                      " "
                      select ( publication19 : Publication , Select , setPublication2(publication19) )
                      action setPublication2 ( publication20 : Publication )
                      {
                        researchProject.proposal := publication20;
                      }
                    }
                  }
                  row(){
                    "Publications"
                    div("inputAssociationSet"){
                      list(){
                        for ( publication21 : Publication in editResearchProject.researchProject.publicationsList ) {
                          div("listAssociation"){
                            listitem(){
                              text(publication21.name){
                              }
                              " "
                              actionLink("[X]", editResearchProject.removePublication0(publication21)){
                              }
                              action removePublication0 ( publication21 : Publication )
                              {
                                editResearchProject.researchProject.publications.remove(publication21);
                              }
                            }
                          }
                        }
                      }
                      select ( publication22 : Publication , Add Publication , addPublication0(publication22) )
                      action addPublication0 ( publication21 : Publication )
                      {
                        editResearchProject.researchProject.publications.add(publication21);
                      }
                    }
                  }
                }
              }
              action("Save", editResearchProject.save()){
              }
              action("Cancel", editResearchProject.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewResearchProject(editResearchProject.researchProject);
          }
          action save ( )
          {
            editResearchProject.researchProject.save();
            return viewResearchProject(editResearchProject.researchProject);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createResearchProject () {
    var researchProject : ResearchProject := ResearchProject{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person81 : Person ) {
                    listitem(){
                      navigate(viewPerson(person81)){
                        text(person81.name){
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
                  for ( project76 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project76)){
                        text(project76.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "ResearchProject"
            }
            form(){
              table(){
                div("editRowsResearchProject"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Fullname"
                    inputString(createResearchProject.researchProject.fullname){
                    }
                  }
                  row(){
                    "Acronym"
                    inputString(createResearchProject.researchProject.acronym){
                    }
                  }
                  row(){
                    "Description"
                    inputText(createResearchProject.researchProject.description){
                    }
                  }
                  row(){
                    "Members"
                    div("inputAssociationSet"){
                      list(){
                        for ( person156 : Person in createResearchProject.researchProject.membersList ) {
                          div("listAssociation"){
                            listitem(){
                              text(person156.name){
                              }
                              " "
                              actionLink("[X]", createResearchProject.removePerson11(person156)){
                              }
                              action removePerson11 ( person156 : Person )
                              {
                                createResearchProject.researchProject.members.remove(person156);
                              }
                            }
                          }
                        }
                      }
                      select ( person157 : Person , Add Person , addPerson11(person157) )
                      action addPerson11 ( person156 : Person )
                      {
                        createResearchProject.researchProject.members.add(person156);
                      }
                    }
                  }
                  row(){
                    "Proposal"
                    div("inputSimpleRefAssociation"){
                      text(createResearchProject.researchProject.proposal.name){
                      }
                      " "
                      select ( publication23 : Publication , Select , setPublication3(publication23) )
                      action setPublication3 ( publication24 : Publication )
                      {
                        researchProject.proposal := publication24;
                      }
                    }
                  }
                  row(){
                    "Publications"
                    div("inputAssociationSet"){
                      list(){
                        for ( publication25 : Publication in createResearchProject.researchProject.publicationsList ) {
                          div("listAssociation"){
                            listitem(){
                              text(publication25.name){
                              }
                              " "
                              actionLink("[X]", createResearchProject.removePublication1(publication25)){
                              }
                              action removePublication1 ( publication25 : Publication )
                              {
                                createResearchProject.researchProject.publications.remove(publication25);
                              }
                            }
                          }
                        }
                      }
                      select ( publication26 : Publication , Add Publication , addPublication1(publication26) )
                      action addPublication1 ( publication25 : Publication )
                      {
                        createResearchProject.researchProject.publications.add(publication25);
                      }
                    }
                  }
                }
              }
              action("Save", createResearchProject.save()){
              }
              action("Cancel", createResearchProject.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createResearchProject.researchProject.save();
            return viewResearchProject(createResearchProject.researchProject);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewResearchProject (researchProject : ResearchProject) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person82 : Person ) {
                    listitem(){
                      navigate(viewPerson(person82)){
                        text(person82.name){
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
                  for ( project77 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project77)){
                        text(project77.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editResearchProject(viewResearchProject.researchProject)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewResearchProject.researchProject.name){
              }
            }
            div("viewRowsResearchProject"){
              div("viewRowsObject"){
              }
              par(){
                "Fullname"
                " : "
                text(viewResearchProject.researchProject.fullname){
                }
              }
              par(){
                "Acronym"
                " : "
                text(viewResearchProject.researchProject.acronym){
                }
              }
              section(){
                header(){
                  "Description"
                }
                outputText(viewResearchProject.researchProject.description){
                }
              }
              section(){
                header(){
                  "Members"
                }
                list(){
                  for ( person158 : Person in viewResearchProject.researchProject.membersList ) {
                    listitem(){
                      navigate(viewPerson(person158)){
                        text(person158.name){
                        }
                      }
                    }
                  }
                }
              }
              section(){
                header(){
                  "Proposal"
                }
                list(){
                  listitem(){
                    navigate(viewPublication(viewResearchProject.researchProject.proposal)){
                      text(viewResearchProject.researchProject.proposal.name){
                      }
                    }
                  }
                }
              }
              section(){
                header(){
                  "Publications"
                }
                list(){
                  for ( publication27 : Publication in viewResearchProject.researchProject.publicationsList ) {
                    listitem(){
                      navigate(viewPublication(publication27)){
                        text(publication27.name){
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allResearchProject () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group24 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group24)){
                  text(group24.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person83 : Person ) {
                    listitem(){
                      navigate(viewPerson(person83)){
                        text(person83.name){
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
                  for ( project78 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project78)){
                        text(project78.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "ResearchProject"
            }
            form(){
              list(){
                for ( researchProject3 : ResearchProject ) {
                  listitem(){
                    navigate(viewResearchProject(researchProject3)){
                      text(researchProject3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allResearchProject.removeResearchProject(researchProject3)){
                    }
                    action removeResearchProject ( researchProject4 : ResearchProject )
                    {
                      researchProject4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editResearchGroup (researchGroup : ResearchGroup) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person84 : Person ) {
                    listitem(){
                      navigate(viewPerson(person84)){
                        text(person84.name){
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
                  for ( project79 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project79)){
                        text(project79.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "ResearchGroup"
              " "
              text(editResearchGroup.researchGroup.name){
              }
            }
            form(){
              table(){
                div("editRowsResearchGroup"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Acronym"
                    inputString(editResearchGroup.researchGroup.acronym){
                    }
                  }
                  row(){
                    "Fullname"
                    inputString(editResearchGroup.researchGroup.fullname){
                    }
                  }
                  row(){
                    "Mission"
                    inputText(editResearchGroup.researchGroup.mission){
                    }
                  }
                  row(){
                    "Logo"
                    inputImage(editResearchGroup.researchGroup.logo){
                    }
                  }
                  row(){
                    "Members"
                    div("inputAssociationSet"){
                      list(){
                        for ( person159 : Person in editResearchGroup.researchGroup.membersList ) {
                          div("listAssociation"){
                            listitem(){
                              text(person159.name){
                              }
                              " "
                              actionLink("[X]", editResearchGroup.removePerson12(person159)){
                              }
                              action removePerson12 ( person159 : Person )
                              {
                                editResearchGroup.researchGroup.members.remove(person159);
                              }
                            }
                          }
                        }
                      }
                      select ( person160 : Person , Add Person , addPerson12(person160) )
                      action addPerson12 ( person159 : Person )
                      {
                        editResearchGroup.researchGroup.members.add(person159);
                      }
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationSet"){
                      list(){
                        for ( researchProject34 : ResearchProject in editResearchGroup.researchGroup.projectsList ) {
                          div("listAssociation"){
                            listitem(){
                              text(researchProject34.name){
                              }
                              " "
                              actionLink("[X]", editResearchGroup.removeResearchProject12(researchProject34)){
                              }
                              action removeResearchProject12 ( researchProject34 : ResearchProject )
                              {
                                editResearchGroup.researchGroup.projects.remove(researchProject34);
                              }
                            }
                          }
                        }
                      }
                      select ( researchProject35 : ResearchProject , Add ResearchProject , addResearchProject12(researchProject35) )
                      action addResearchProject12 ( researchProject34 : ResearchProject )
                      {
                        editResearchGroup.researchGroup.projects.add(researchProject34);
                      }
                    }
                  }
                  row(){
                    "Colloquia"
                    div("inputAssociationSet"){
                      list(){
                        for ( colloquium4 : Colloquium in editResearchGroup.researchGroup.colloquiaList ) {
                          div("listAssociation"){
                            listitem(){
                              text(colloquium4.name){
                              }
                              " "
                              actionLink("[X]", editResearchGroup.removeColloquium0(colloquium4)){
                              }
                              action removeColloquium0 ( colloquium4 : Colloquium )
                              {
                                editResearchGroup.researchGroup.colloquia.remove(colloquium4);
                              }
                            }
                          }
                        }
                      }
                      select ( colloquium5 : Colloquium , Add Colloquium , addColloquium0(colloquium5) )
                      action addColloquium0 ( colloquium4 : Colloquium )
                      {
                        editResearchGroup.researchGroup.colloquia.add(colloquium4);
                      }
                    }
                  }
                  row(){
                    "News"
                    div("inputAssociationList"){
                      list(){
                        for ( news5 : News in editResearchGroup.researchGroup.news ) {
                          listitem(){
                            text(news5.name){
                            }
                            " "
                            actionLink("[X]", editResearchGroup.removeNews0(news5)){
                            }
                            action removeNews0 ( news5 : News )
                            {
                              editResearchGroup.researchGroup.news.remove(news5);
                            }
                          }
                        }
                      }
                      select ( news6 : News , Add News , addNews0(news6) )
                      action addNews0 ( news5 : News )
                      {
                        editResearchGroup.researchGroup.news.add(news5);
                      }
                    }
                  }
                }
              }
              action("Save", editResearchGroup.save()){
              }
              action("Cancel", editResearchGroup.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewResearchGroup(editResearchGroup.researchGroup);
          }
          action save ( )
          {
            editResearchGroup.researchGroup.save();
            return viewResearchGroup(editResearchGroup.researchGroup);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createResearchGroup () {
    var researchGroup : ResearchGroup := ResearchGroup{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person85 : Person ) {
                    listitem(){
                      navigate(viewPerson(person85)){
                        text(person85.name){
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
                  for ( project80 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project80)){
                        text(project80.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "ResearchGroup"
            }
            form(){
              table(){
                div("editRowsResearchGroup"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Acronym"
                    inputString(createResearchGroup.researchGroup.acronym){
                    }
                  }
                  row(){
                    "Fullname"
                    inputString(createResearchGroup.researchGroup.fullname){
                    }
                  }
                  row(){
                    "Mission"
                    inputText(createResearchGroup.researchGroup.mission){
                    }
                  }
                  row(){
                    "Logo"
                    inputImage(createResearchGroup.researchGroup.logo){
                    }
                  }
                  row(){
                    "Members"
                    div("inputAssociationSet"){
                      list(){
                        for ( person161 : Person in createResearchGroup.researchGroup.membersList ) {
                          div("listAssociation"){
                            listitem(){
                              text(person161.name){
                              }
                              " "
                              actionLink("[X]", createResearchGroup.removePerson13(person161)){
                              }
                              action removePerson13 ( person161 : Person )
                              {
                                createResearchGroup.researchGroup.members.remove(person161);
                              }
                            }
                          }
                        }
                      }
                      select ( person162 : Person , Add Person , addPerson13(person162) )
                      action addPerson13 ( person161 : Person )
                      {
                        createResearchGroup.researchGroup.members.add(person161);
                      }
                    }
                  }
                  row(){
                    "Projects"
                    div("inputAssociationSet"){
                      list(){
                        for ( researchProject36 : ResearchProject in createResearchGroup.researchGroup.projectsList ) {
                          div("listAssociation"){
                            listitem(){
                              text(researchProject36.name){
                              }
                              " "
                              actionLink("[X]", createResearchGroup.removeResearchProject13(researchProject36)){
                              }
                              action removeResearchProject13 ( researchProject36 : ResearchProject )
                              {
                                createResearchGroup.researchGroup.projects.remove(researchProject36);
                              }
                            }
                          }
                        }
                      }
                      select ( researchProject37 : ResearchProject , Add ResearchProject , addResearchProject13(researchProject37) )
                      action addResearchProject13 ( researchProject36 : ResearchProject )
                      {
                        createResearchGroup.researchGroup.projects.add(researchProject36);
                      }
                    }
                  }
                  row(){
                    "Colloquia"
                    div("inputAssociationSet"){
                      list(){
                        for ( colloquium6 : Colloquium in createResearchGroup.researchGroup.colloquiaList ) {
                          div("listAssociation"){
                            listitem(){
                              text(colloquium6.name){
                              }
                              " "
                              actionLink("[X]", createResearchGroup.removeColloquium1(colloquium6)){
                              }
                              action removeColloquium1 ( colloquium6 : Colloquium )
                              {
                                createResearchGroup.researchGroup.colloquia.remove(colloquium6);
                              }
                            }
                          }
                        }
                      }
                      select ( colloquium7 : Colloquium , Add Colloquium , addColloquium1(colloquium7) )
                      action addColloquium1 ( colloquium6 : Colloquium )
                      {
                        createResearchGroup.researchGroup.colloquia.add(colloquium6);
                      }
                    }
                  }
                  row(){
                    "News"
                    div("inputAssociationList"){
                      list(){
                        for ( news7 : News in createResearchGroup.researchGroup.news ) {
                          listitem(){
                            text(news7.name){
                            }
                            " "
                            actionLink("[X]", createResearchGroup.removeNews1(news7)){
                            }
                            action removeNews1 ( news7 : News )
                            {
                              createResearchGroup.researchGroup.news.remove(news7);
                            }
                          }
                        }
                      }
                      select ( news8 : News , Add News , addNews1(news8) )
                      action addNews1 ( news7 : News )
                      {
                        createResearchGroup.researchGroup.news.add(news7);
                      }
                    }
                  }
                }
              }
              action("Save", createResearchGroup.save()){
              }
              action("Cancel", createResearchGroup.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createResearchGroup.researchGroup.save();
            return viewResearchGroup(createResearchGroup.researchGroup);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allResearchGroup () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group25 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group25)){
                  text(group25.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person86 : Person ) {
                    listitem(){
                      navigate(viewPerson(person86)){
                        text(person86.name){
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
                  for ( project81 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project81)){
                        text(project81.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "ResearchGroup"
            }
            form(){
              list(){
                for ( researchGroup2 : ResearchGroup ) {
                  listitem(){
                    navigate(viewResearchGroup(researchGroup2)){
                      text(researchGroup2.name){
                      }
                    }
                    " "
                    actionLink("[X]", allResearchGroup.removeResearchGroup(researchGroup2)){
                    }
                    action removeResearchGroup ( researchGroup3 : ResearchGroup )
                    {
                      researchGroup3.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editNews (news : News) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person87 : Person ) {
                    listitem(){
                      navigate(viewPerson(person87)){
                        text(person87.name){
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
                  for ( project82 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project82)){
                        text(project82.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "News"
              " "
              text(editNews.news.name){
              }
            }
            form(){
              table(){
                div("editRowsNews"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(editNews.news.title){
                    }
                  }
                  row(){
                    "Text"
                    inputText(editNews.news.text){
                    }
                  }
                  row(){
                    "Date"
                    inputDate(editNews.news.date){
                    }
                  }
                }
              }
              action("Save", editNews.save()){
              }
              action("Cancel", editNews.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewNews(editNews.news);
          }
          action save ( )
          {
            editNews.news.save();
            return viewNews(editNews.news);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createNews () {
    var news : News := News{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person88 : Person ) {
                    listitem(){
                      navigate(viewPerson(person88)){
                        text(person88.name){
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
                  for ( project83 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project83)){
                        text(project83.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "News"
            }
            form(){
              table(){
                div("editRowsNews"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(createNews.news.title){
                    }
                  }
                  row(){
                    "Text"
                    inputText(createNews.news.text){
                    }
                  }
                  row(){
                    "Date"
                    inputDate(createNews.news.date){
                    }
                  }
                }
              }
              action("Save", createNews.save()){
              }
              action("Cancel", createNews.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createNews.news.save();
            return viewNews(createNews.news);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewNews (news : News) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person89 : Person ) {
                    listitem(){
                      navigate(viewPerson(person89)){
                        text(person89.name){
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
                  for ( project84 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project84)){
                        text(project84.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editNews(viewNews.news)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewNews.news.name){
              }
            }
            div("viewRowsNews"){
              div("viewRowsObject"){
              }
              par(){
                "Title"
                " : "
                text(viewNews.news.title){
                }
              }
              section(){
                header(){
                  "Text"
                }
                outputText(viewNews.news.text){
                }
              }
              par(){
                "Date"
                " : "
                outputDate(viewNews.news.date){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allNews () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group26 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group26)){
                  text(group26.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person90 : Person ) {
                    listitem(){
                      navigate(viewPerson(person90)){
                        text(person90.name){
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
                  for ( project85 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project85)){
                        text(project85.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "News"
            }
            form(){
              list(){
                for ( news3 : News ) {
                  listitem(){
                    navigate(viewNews(news3)){
                      text(news3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allNews.removeNews(news3)){
                    }
                    action removeNews ( news4 : News )
                    {
                      news4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editIssue (issue : Issue) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person91 : Person ) {
                    listitem(){
                      navigate(viewPerson(person91)){
                        text(person91.name){
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
                  for ( project86 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project86)){
                        text(project86.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Issue"
              " "
              text(editIssue.issue.name){
              }
            }
            form(){
              table(){
                div("editRowsIssue"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(editIssue.issue.title){
                    }
                  }
                  row(){
                    "Description"
                    inputText(editIssue.issue.description){
                    }
                  }
                  row(){
                    "Due"
                    inputDate(editIssue.issue.due){
                    }
                  }
                  row(){
                    "Priority"
                    inputInt(editIssue.issue.priority){
                    }
                  }
                  row(){
                    "Issues"
                    div("inputAssociationSet"){
                      list(){
                        for ( issue14 : Issue in editIssue.issue.issuesList ) {
                          div("listAssociation"){
                            listitem(){
                              text(issue14.name){
                              }
                              " "
                              actionLink("[X]", editIssue.removeIssue0(issue14)){
                              }
                              action removeIssue0 ( issue14 : Issue )
                              {
                                editIssue.issue.issues.remove(issue14);
                              }
                            }
                          }
                        }
                      }
                      select ( issue15 : Issue , Add Issue , addIssue0(issue15) )
                      action addIssue0 ( issue14 : Issue )
                      {
                        editIssue.issue.issues.add(issue14);
                      }
                    }
                  }
                  row(){
                    "Assigned"
                    div("inputAssociationSet"){
                      list(){
                        for ( person163 : Person in editIssue.issue.assignedList ) {
                          div("listAssociation"){
                            listitem(){
                              text(person163.name){
                              }
                              " "
                              actionLink("[X]", editIssue.removePerson14(person163)){
                              }
                              action removePerson14 ( person163 : Person )
                              {
                                editIssue.issue.assigned.remove(person163);
                              }
                            }
                          }
                        }
                      }
                      select ( person164 : Person , Add Person , addPerson14(person164) )
                      action addPerson14 ( person163 : Person )
                      {
                        editIssue.issue.assigned.add(person163);
                      }
                    }
                  }
                  row(){
                    "Status"
                    inputString(editIssue.issue.status){
                    }
                  }
                }
              }
              action("Save", editIssue.save()){
              }
              action("Cancel", editIssue.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewIssue(editIssue.issue);
          }
          action save ( )
          {
            editIssue.issue.save();
            return viewIssue(editIssue.issue);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createIssue () {
    var issue : Issue := Issue{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person92 : Person ) {
                    listitem(){
                      navigate(viewPerson(person92)){
                        text(person92.name){
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
                  for ( project87 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project87)){
                        text(project87.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Issue"
            }
            form(){
              table(){
                div("editRowsIssue"){
                  div("editRowsObject"){
                  }
                  row(){
                    "Title"
                    inputString(createIssue.issue.title){
                    }
                  }
                  row(){
                    "Description"
                    inputText(createIssue.issue.description){
                    }
                  }
                  row(){
                    "Due"
                    inputDate(createIssue.issue.due){
                    }
                  }
                  row(){
                    "Priority"
                    inputInt(createIssue.issue.priority){
                    }
                  }
                  row(){
                    "Issues"
                    div("inputAssociationSet"){
                      list(){
                        for ( issue16 : Issue in createIssue.issue.issuesList ) {
                          div("listAssociation"){
                            listitem(){
                              text(issue16.name){
                              }
                              " "
                              actionLink("[X]", createIssue.removeIssue1(issue16)){
                              }
                              action removeIssue1 ( issue16 : Issue )
                              {
                                createIssue.issue.issues.remove(issue16);
                              }
                            }
                          }
                        }
                      }
                      select ( issue17 : Issue , Add Issue , addIssue1(issue17) )
                      action addIssue1 ( issue16 : Issue )
                      {
                        createIssue.issue.issues.add(issue16);
                      }
                    }
                  }
                  row(){
                    "Assigned"
                    div("inputAssociationSet"){
                      list(){
                        for ( person165 : Person in createIssue.issue.assignedList ) {
                          div("listAssociation"){
                            listitem(){
                              text(person165.name){
                              }
                              " "
                              actionLink("[X]", createIssue.removePerson15(person165)){
                              }
                              action removePerson15 ( person165 : Person )
                              {
                                createIssue.issue.assigned.remove(person165);
                              }
                            }
                          }
                        }
                      }
                      select ( person166 : Person , Add Person , addPerson15(person166) )
                      action addPerson15 ( person165 : Person )
                      {
                        createIssue.issue.assigned.add(person165);
                      }
                    }
                  }
                  row(){
                    "Status"
                    inputString(createIssue.issue.status){
                    }
                  }
                }
              }
              action("Save", createIssue.save()){
              }
              action("Cancel", createIssue.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createIssue.issue.save();
            return viewIssue(createIssue.issue);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewIssue (issue : Issue) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person93 : Person ) {
                    listitem(){
                      navigate(viewPerson(person93)){
                        text(person93.name){
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
                  for ( project88 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project88)){
                        text(project88.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editIssue(viewIssue.issue)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewIssue.issue.name){
              }
            }
            div("viewRowsIssue"){
              div("viewRowsObject"){
              }
              par(){
                "Title"
                " : "
                text(viewIssue.issue.title){
                }
              }
              section(){
                header(){
                  "Description"
                }
                outputText(viewIssue.issue.description){
                }
              }
              par(){
                "Due"
                " : "
                outputDate(viewIssue.issue.due){
                }
              }
              par(){
                "Priority"
                " : "
                text(viewIssue.issue.priority){
                }
              }
              section(){
                header(){
                  "Issues"
                }
                list(){
                  for ( issue18 : Issue in viewIssue.issue.issuesList ) {
                    listitem(){
                      navigate(viewIssue(issue18)){
                        text(issue18.name){
                        }
                      }
                    }
                  }
                }
              }
              section(){
                header(){
                  "Assigned"
                }
                list(){
                  for ( person167 : Person in viewIssue.issue.assignedList ) {
                    listitem(){
                      navigate(viewPerson(person167)){
                        text(person167.name){
                        }
                      }
                    }
                  }
                }
              }
              par(){
                "Status"
                " : "
                text(viewIssue.issue.status){
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allIssue () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group27 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group27)){
                  text(group27.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person94 : Person ) {
                    listitem(){
                      navigate(viewPerson(person94)){
                        text(person94.name){
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
                  for ( project89 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project89)){
                        text(project89.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Issue"
            }
            form(){
              list(){
                for ( issue3 : Issue ) {
                  listitem(){
                    navigate(viewIssue(issue3)){
                      text(issue3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allIssue.removeIssue(issue3)){
                    }
                    action removeIssue ( issue4 : Issue )
                    {
                      issue4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editProject (project : Project) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person95 : Person ) {
                    listitem(){
                      navigate(viewPerson(person95)){
                        text(person95.name){
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
                  for ( project90 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project90)){
                        text(project90.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Project"
              " "
              text(editProject.project.name){
              }
            }
            form(){
              table(){
                div("editRowsProject"){
                  div("editRowsIssue"){
                    div("editRowsObject"){
                    }
                    row(){
                      "Title"
                      inputString(editProject.project.title){
                      }
                    }
                    row(){
                      "Description"
                      inputText(editProject.project.description){
                      }
                    }
                    row(){
                      "Due"
                      inputDate(editProject.project.due){
                      }
                    }
                    row(){
                      "Priority"
                      inputInt(editProject.project.priority){
                      }
                    }
                    row(){
                      "Issues"
                      div("inputAssociationSet"){
                        list(){
                          for ( issue19 : Issue in editProject.project.issuesList ) {
                            div("listAssociation"){
                              listitem(){
                                text(issue19.name){
                                }
                                " "
                                actionLink("[X]", editProject.removeIssue2(issue19)){
                                }
                                action removeIssue2 ( issue19 : Issue )
                                {
                                  editProject.project.issues.remove(issue19);
                                }
                              }
                            }
                          }
                        }
                        select ( issue20 : Issue , Add Issue , addIssue2(issue20) )
                        action addIssue2 ( issue19 : Issue )
                        {
                          editProject.project.issues.add(issue19);
                        }
                      }
                    }
                    row(){
                      "Assigned"
                      div("inputAssociationSet"){
                        list(){
                          for ( person168 : Person in editProject.project.assignedList ) {
                            div("listAssociation"){
                              listitem(){
                                text(person168.name){
                                }
                                " "
                                actionLink("[X]", editProject.removePerson16(person168)){
                                }
                                action removePerson16 ( person168 : Person )
                                {
                                  editProject.project.assigned.remove(person168);
                                }
                              }
                            }
                          }
                        }
                        select ( person169 : Person , Add Person , addPerson16(person169) )
                        action addPerson16 ( person168 : Person )
                        {
                          editProject.project.assigned.add(person168);
                        }
                      }
                    }
                    row(){
                      "Status"
                      inputString(editProject.project.status){
                      }
                    }
                  }
                }
              }
              action("Save", editProject.save()){
              }
              action("Cancel", editProject.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewProject(editProject.project);
          }
          action save ( )
          {
            editProject.project.save();
            return viewProject(editProject.project);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createProject () {
    var project : Project := Project{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person96 : Person ) {
                    listitem(){
                      navigate(viewPerson(person96)){
                        text(person96.name){
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
                  for ( project92 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project92)){
                        text(project92.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Project"
            }
            form(){
              table(){
                div("editRowsProject"){
                  div("editRowsIssue"){
                    div("editRowsObject"){
                    }
                    row(){
                      "Title"
                      inputString(createProject.project.title){
                      }
                    }
                    row(){
                      "Description"
                      inputText(createProject.project.description){
                      }
                    }
                    row(){
                      "Due"
                      inputDate(createProject.project.due){
                      }
                    }
                    row(){
                      "Priority"
                      inputInt(createProject.project.priority){
                      }
                    }
                    row(){
                      "Issues"
                      div("inputAssociationSet"){
                        list(){
                          for ( issue21 : Issue in createProject.project.issuesList ) {
                            div("listAssociation"){
                              listitem(){
                                text(issue21.name){
                                }
                                " "
                                actionLink("[X]", createProject.removeIssue3(issue21)){
                                }
                                action removeIssue3 ( issue21 : Issue )
                                {
                                  createProject.project.issues.remove(issue21);
                                }
                              }
                            }
                          }
                        }
                        select ( issue22 : Issue , Add Issue , addIssue3(issue22) )
                        action addIssue3 ( issue21 : Issue )
                        {
                          createProject.project.issues.add(issue21);
                        }
                      }
                    }
                    row(){
                      "Assigned"
                      div("inputAssociationSet"){
                        list(){
                          for ( person170 : Person in createProject.project.assignedList ) {
                            div("listAssociation"){
                              listitem(){
                                text(person170.name){
                                }
                                " "
                                actionLink("[X]", createProject.removePerson17(person170)){
                                }
                                action removePerson17 ( person170 : Person )
                                {
                                  createProject.project.assigned.remove(person170);
                                }
                              }
                            }
                          }
                        }
                        select ( person171 : Person , Add Person , addPerson17(person171) )
                        action addPerson17 ( person170 : Person )
                        {
                          createProject.project.assigned.add(person170);
                        }
                      }
                    }
                    row(){
                      "Status"
                      inputString(createProject.project.status){
                      }
                    }
                  }
                }
              }
              action("Save", createProject.save()){
              }
              action("Cancel", createProject.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createProject.project.save();
            return viewProject(createProject.project);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewProject (project : Project) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person97 : Person ) {
                    listitem(){
                      navigate(viewPerson(person97)){
                        text(person97.name){
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
                  for ( project94 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project94)){
                        text(project94.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editProject(viewProject.project)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewProject.project.name){
              }
            }
            div("viewRowsProject"){
              div("viewRowsIssue"){
                div("viewRowsObject"){
                }
                par(){
                  "Title"
                  " : "
                  text(viewProject.project.title){
                  }
                }
                section(){
                  header(){
                    "Description"
                  }
                  outputText(viewProject.project.description){
                  }
                }
                par(){
                  "Due"
                  " : "
                  outputDate(viewProject.project.due){
                  }
                }
                par(){
                  "Priority"
                  " : "
                  text(viewProject.project.priority){
                  }
                }
                section(){
                  header(){
                    "Issues"
                  }
                  list(){
                    for ( issue23 : Issue in viewProject.project.issuesList ) {
                      listitem(){
                        navigate(viewIssue(issue23)){
                          text(issue23.name){
                          }
                        }
                      }
                    }
                  }
                }
                section(){
                  header(){
                    "Assigned"
                  }
                  list(){
                    for ( person172 : Person in viewProject.project.assignedList ) {
                      listitem(){
                        navigate(viewPerson(person172)){
                          text(person172.name){
                          }
                        }
                      }
                    }
                  }
                }
                par(){
                  "Status"
                  " : "
                  text(viewProject.project.status){
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allProject () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group28 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group28)){
                  text(group28.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person98 : Person ) {
                    listitem(){
                      navigate(viewPerson(person98)){
                        text(person98.name){
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
                  for ( project96 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project96)){
                        text(project96.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Project"
            }
            form(){
              list(){
                for ( project97 : Project ) {
                  listitem(){
                    navigate(viewProject(project97)){
                      text(project97.name){
                      }
                    }
                    " "
                    actionLink("[X]", allProject.removeProject(project97)){
                    }
                    action removeProject ( project98 : Project )
                    {
                      project98.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editBug (bug : Bug) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person99 : Person ) {
                    listitem(){
                      navigate(viewPerson(person99)){
                        text(person99.name){
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
                  for ( project99 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project99)){
                        text(project99.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Bug"
              " "
              text(editBug.bug.name){
              }
            }
            form(){
              table(){
                div("editRowsBug"){
                  div("editRowsIssue"){
                    div("editRowsObject"){
                    }
                    row(){
                      "Title"
                      inputString(editBug.bug.title){
                      }
                    }
                    row(){
                      "Description"
                      inputText(editBug.bug.description){
                      }
                    }
                    row(){
                      "Due"
                      inputDate(editBug.bug.due){
                      }
                    }
                    row(){
                      "Priority"
                      inputInt(editBug.bug.priority){
                      }
                    }
                    row(){
                      "Issues"
                      div("inputAssociationSet"){
                        list(){
                          for ( issue24 : Issue in editBug.bug.issuesList ) {
                            div("listAssociation"){
                              listitem(){
                                text(issue24.name){
                                }
                                " "
                                actionLink("[X]", editBug.removeIssue4(issue24)){
                                }
                                action removeIssue4 ( issue24 : Issue )
                                {
                                  editBug.bug.issues.remove(issue24);
                                }
                              }
                            }
                          }
                        }
                        select ( issue25 : Issue , Add Issue , addIssue4(issue25) )
                        action addIssue4 ( issue24 : Issue )
                        {
                          editBug.bug.issues.add(issue24);
                        }
                      }
                    }
                    row(){
                      "Assigned"
                      div("inputAssociationSet"){
                        list(){
                          for ( person173 : Person in editBug.bug.assignedList ) {
                            div("listAssociation"){
                              listitem(){
                                text(person173.name){
                                }
                                " "
                                actionLink("[X]", editBug.removePerson18(person173)){
                                }
                                action removePerson18 ( person173 : Person )
                                {
                                  editBug.bug.assigned.remove(person173);
                                }
                              }
                            }
                          }
                        }
                        select ( person174 : Person , Add Person , addPerson18(person174) )
                        action addPerson18 ( person173 : Person )
                        {
                          editBug.bug.assigned.add(person173);
                        }
                      }
                    }
                    row(){
                      "Status"
                      inputString(editBug.bug.status){
                      }
                    }
                  }
                }
              }
              action("Save", editBug.save()){
              }
              action("Cancel", editBug.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewBug(editBug.bug);
          }
          action save ( )
          {
            editBug.bug.save();
            return viewBug(editBug.bug);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createBug () {
    var bug : Bug := Bug{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person100 : Person ) {
                    listitem(){
                      navigate(viewPerson(person100)){
                        text(person100.name){
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
                  for ( project100 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project100)){
                        text(project100.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Bug"
            }
            form(){
              table(){
                div("editRowsBug"){
                  div("editRowsIssue"){
                    div("editRowsObject"){
                    }
                    row(){
                      "Title"
                      inputString(createBug.bug.title){
                      }
                    }
                    row(){
                      "Description"
                      inputText(createBug.bug.description){
                      }
                    }
                    row(){
                      "Due"
                      inputDate(createBug.bug.due){
                      }
                    }
                    row(){
                      "Priority"
                      inputInt(createBug.bug.priority){
                      }
                    }
                    row(){
                      "Issues"
                      div("inputAssociationSet"){
                        list(){
                          for ( issue26 : Issue in createBug.bug.issuesList ) {
                            div("listAssociation"){
                              listitem(){
                                text(issue26.name){
                                }
                                " "
                                actionLink("[X]", createBug.removeIssue5(issue26)){
                                }
                                action removeIssue5 ( issue26 : Issue )
                                {
                                  createBug.bug.issues.remove(issue26);
                                }
                              }
                            }
                          }
                        }
                        select ( issue27 : Issue , Add Issue , addIssue5(issue27) )
                        action addIssue5 ( issue26 : Issue )
                        {
                          createBug.bug.issues.add(issue26);
                        }
                      }
                    }
                    row(){
                      "Assigned"
                      div("inputAssociationSet"){
                        list(){
                          for ( person175 : Person in createBug.bug.assignedList ) {
                            div("listAssociation"){
                              listitem(){
                                text(person175.name){
                                }
                                " "
                                actionLink("[X]", createBug.removePerson19(person175)){
                                }
                                action removePerson19 ( person175 : Person )
                                {
                                  createBug.bug.assigned.remove(person175);
                                }
                              }
                            }
                          }
                        }
                        select ( person176 : Person , Add Person , addPerson19(person176) )
                        action addPerson19 ( person175 : Person )
                        {
                          createBug.bug.assigned.add(person175);
                        }
                      }
                    }
                    row(){
                      "Status"
                      inputString(createBug.bug.status){
                      }
                    }
                  }
                }
              }
              action("Save", createBug.save()){
              }
              action("Cancel", createBug.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createBug.bug.save();
            return viewBug(createBug.bug);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewBug (bug : Bug) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person101 : Person ) {
                    listitem(){
                      navigate(viewPerson(person101)){
                        text(person101.name){
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
                  for ( project101 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project101)){
                        text(project101.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editBug(viewBug.bug)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewBug.bug.name){
              }
            }
            div("viewRowsBug"){
              div("viewRowsIssue"){
                div("viewRowsObject"){
                }
                par(){
                  "Title"
                  " : "
                  text(viewBug.bug.title){
                  }
                }
                section(){
                  header(){
                    "Description"
                  }
                  outputText(viewBug.bug.description){
                  }
                }
                par(){
                  "Due"
                  " : "
                  outputDate(viewBug.bug.due){
                  }
                }
                par(){
                  "Priority"
                  " : "
                  text(viewBug.bug.priority){
                  }
                }
                section(){
                  header(){
                    "Issues"
                  }
                  list(){
                    for ( issue28 : Issue in viewBug.bug.issuesList ) {
                      listitem(){
                        navigate(viewIssue(issue28)){
                          text(issue28.name){
                          }
                        }
                      }
                    }
                  }
                }
                section(){
                  header(){
                    "Assigned"
                  }
                  list(){
                    for ( person177 : Person in viewBug.bug.assignedList ) {
                      listitem(){
                        navigate(viewPerson(person177)){
                          text(person177.name){
                          }
                        }
                      }
                    }
                  }
                }
                par(){
                  "Status"
                  " : "
                  text(viewBug.bug.status){
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allBug () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group29 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group29)){
                  text(group29.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person102 : Person ) {
                    listitem(){
                      navigate(viewPerson(person102)){
                        text(person102.name){
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
                  for ( project102 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project102)){
                        text(project102.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Bug"
            }
            form(){
              list(){
                for ( bug3 : Bug ) {
                  listitem(){
                    navigate(viewBug(bug3)){
                      text(bug3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allBug.removeBug(bug3)){
                    }
                    action removeBug ( bug4 : Bug )
                    {
                      bug4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page editTask (task : Task) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person103 : Person ) {
                    listitem(){
                      navigate(viewPerson(person103)){
                        text(person103.name){
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
                  for ( project103 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project103)){
                        text(project103.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Edit "
              "Task"
              " "
              text(editTask.task.name){
              }
            }
            form(){
              table(){
                div("editRowsTask"){
                  div("editRowsIssue"){
                    div("editRowsObject"){
                    }
                    row(){
                      "Title"
                      inputString(editTask.task.title){
                      }
                    }
                    row(){
                      "Description"
                      inputText(editTask.task.description){
                      }
                    }
                    row(){
                      "Due"
                      inputDate(editTask.task.due){
                      }
                    }
                    row(){
                      "Priority"
                      inputInt(editTask.task.priority){
                      }
                    }
                    row(){
                      "Issues"
                      div("inputAssociationSet"){
                        list(){
                          for ( issue29 : Issue in editTask.task.issuesList ) {
                            div("listAssociation"){
                              listitem(){
                                text(issue29.name){
                                }
                                " "
                                actionLink("[X]", editTask.removeIssue6(issue29)){
                                }
                                action removeIssue6 ( issue29 : Issue )
                                {
                                  editTask.task.issues.remove(issue29);
                                }
                              }
                            }
                          }
                        }
                        select ( issue30 : Issue , Add Issue , addIssue6(issue30) )
                        action addIssue6 ( issue29 : Issue )
                        {
                          editTask.task.issues.add(issue29);
                        }
                      }
                    }
                    row(){
                      "Assigned"
                      div("inputAssociationSet"){
                        list(){
                          for ( person178 : Person in editTask.task.assignedList ) {
                            div("listAssociation"){
                              listitem(){
                                text(person178.name){
                                }
                                " "
                                actionLink("[X]", editTask.removePerson20(person178)){
                                }
                                action removePerson20 ( person178 : Person )
                                {
                                  editTask.task.assigned.remove(person178);
                                }
                              }
                            }
                          }
                        }
                        select ( person179 : Person , Add Person , addPerson20(person179) )
                        action addPerson20 ( person178 : Person )
                        {
                          editTask.task.assigned.add(person178);
                        }
                      }
                    }
                    row(){
                      "Status"
                      inputString(editTask.task.status){
                      }
                    }
                  }
                }
              }
              action("Save", editTask.save()){
              }
              action("Cancel", editTask.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return viewTask(editTask.task);
          }
          action save ( )
          {
            editTask.task.save();
            return viewTask(editTask.task);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page createTask () {
    var task : Task := Task{} ;
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person104 : Person ) {
                    listitem(){
                      navigate(viewPerson(person104)){
                        text(person104.name){
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
                  for ( project104 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project104)){
                        text(project104.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "Create new "
              "Task"
            }
            form(){
              table(){
                div("editRowsTask"){
                  div("editRowsIssue"){
                    div("editRowsObject"){
                    }
                    row(){
                      "Title"
                      inputString(createTask.task.title){
                      }
                    }
                    row(){
                      "Description"
                      inputText(createTask.task.description){
                      }
                    }
                    row(){
                      "Due"
                      inputDate(createTask.task.due){
                      }
                    }
                    row(){
                      "Priority"
                      inputInt(createTask.task.priority){
                      }
                    }
                    row(){
                      "Issues"
                      div("inputAssociationSet"){
                        list(){
                          for ( issue31 : Issue in createTask.task.issuesList ) {
                            div("listAssociation"){
                              listitem(){
                                text(issue31.name){
                                }
                                " "
                                actionLink("[X]", createTask.removeIssue7(issue31)){
                                }
                                action removeIssue7 ( issue31 : Issue )
                                {
                                  createTask.task.issues.remove(issue31);
                                }
                              }
                            }
                          }
                        }
                        select ( issue32 : Issue , Add Issue , addIssue7(issue32) )
                        action addIssue7 ( issue31 : Issue )
                        {
                          createTask.task.issues.add(issue31);
                        }
                      }
                    }
                    row(){
                      "Assigned"
                      div("inputAssociationSet"){
                        list(){
                          for ( person180 : Person in createTask.task.assignedList ) {
                            div("listAssociation"){
                              listitem(){
                                text(person180.name){
                                }
                                " "
                                actionLink("[X]", createTask.removePerson21(person180)){
                                }
                                action removePerson21 ( person180 : Person )
                                {
                                  createTask.task.assigned.remove(person180);
                                }
                              }
                            }
                          }
                        }
                        select ( person181 : Person , Add Person , addPerson21(person181) )
                        action addPerson21 ( person180 : Person )
                        {
                          createTask.task.assigned.add(person180);
                        }
                      }
                    }
                    row(){
                      "Status"
                      inputString(createTask.task.status){
                      }
                    }
                  }
                }
              }
              action("Save", createTask.save()){
              }
              action("Cancel", createTask.cancel()){
              }
            }
          }
          action cancel ( )
          {
            return home();
          }
          action save ( )
          {
            createTask.task.save();
            return viewTask(createTask.task);
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page viewTask (task : Task) {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person105 : Person ) {
                    listitem(){
                      navigate(viewPerson(person105)){
                        text(person105.name){
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
                  for ( project105 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project105)){
                        text(project105.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                    listitem(){
                      navigate(editTask(viewTask.task)){
                        text("Edit"){
                        }
                      }
                    }
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              text(viewTask.task.name){
              }
            }
            div("viewRowsTask"){
              div("viewRowsIssue"){
                div("viewRowsObject"){
                }
                par(){
                  "Title"
                  " : "
                  text(viewTask.task.title){
                  }
                }
                section(){
                  header(){
                    "Description"
                  }
                  outputText(viewTask.task.description){
                  }
                }
                par(){
                  "Due"
                  " : "
                  outputDate(viewTask.task.due){
                  }
                }
                par(){
                  "Priority"
                  " : "
                  text(viewTask.task.priority){
                  }
                }
                section(){
                  header(){
                    "Issues"
                  }
                  list(){
                    for ( issue33 : Issue in viewTask.task.issuesList ) {
                      listitem(){
                        navigate(viewIssue(issue33)){
                          text(issue33.name){
                          }
                        }
                      }
                    }
                  }
                }
                section(){
                  header(){
                    "Assigned"
                  }
                  list(){
                    for ( person182 : Person in viewTask.task.assignedList ) {
                      listitem(){
                        navigate(viewPerson(person182)){
                          text(person182.name){
                          }
                        }
                      }
                    }
                  }
                }
                par(){
                  "Status"
                  " : "
                  text(viewTask.task.status){
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }

  define page allTask () {
    div("main"){
      div("outersidebar"){
        div("logo"){
          navigate(home()){
            image("/img/serg-logo-color-smaller.png"){
            }
          }
        }
        div("sidebar"){
          list(){
            for ( group30 : ResearchGroup ) {
              listitem(){
                navigate(viewResearchGroup(group30)){
                  text(group30.name){
                  }
                }
              }
            }
          }
        }
      }
      div("outerbody"){
        div("menubar"){
          div("menu"){
            list(){
              listitem(){
                "People"
                list(){
                  for ( person106 : Person ) {
                    listitem(){
                      navigate(viewPerson(person106)){
                        text(person106.name){
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
                  for ( project106 : ResearchProject ) {
                    listitem(){
                      navigate(viewResearchProject(project106)){
                        text(project106.acronym){
                        }
                      }
                    }
                  }
                }
              }
            }
            list(){
              listitem(){
                "Manage"
                list(){
                  div("manageMenu"){
                  }
                  div("createMenu"){
                    listitem(){
                      "New"
                      list(){
                      }
                    }
                  }
                  div("allMenu"){
                    listitem(){
                      "All"
                      list(){
                      }
                    }
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
        }
        div("body"){
          section(){
            header(){
              "All "
              "Task"
            }
            form(){
              list(){
                for ( task3 : Task ) {
                  listitem(){
                    navigate(viewTask(task3)){
                      text(task3.name){
                      }
                    }
                    " "
                    actionLink("[X]", allTask.removeTask(task3)){
                    }
                    action removeTask ( task4 : Task )
                    {
                      task4.delete();
                    }
                  }
                }
              }
            }
          }
        }
        div("footer"){
          "generated with "
          navigate(url("http://www.strategoxt.org")){
            text("Stratego/XT"){
            }
          }
        }
      }
    }
  }