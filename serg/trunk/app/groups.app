module groups

section domain

  entity ResearchGroup {
    acronym   :: String (name)
    fullname  :: String
    mission   :: Text
    logo      :: Image
    members   -> Set<Person>
    projects  -> Set<ResearchProject>
    colloquia -> Set<Colloquium>
    news      -> List<News>
  }
  
  // model roles of people
  
  // student, faculty, professor, etc.
  
  // publications of group = publications of members of group

section group page

  define groupSidebar(group : ResearchGroup) {
    list{
      listitem{ navigate(viewResearchGroup(group)){text(group.acronym)} }
      listitem{ navigate("People", groupMembers(group)) }
      listitem{ navigate("Publications", groupPublications(group)) }
      listitem { navigate("Projects", groupProjects(group))
        list { for(project : ResearchProject in group.projectsList) {
          listitem { navigate(project.name, viewResearchProject(project)) }
        } }
      }
      listitem { "Colloquia"
        list { for(coll : Colloquium in group.colloquiaList) {
          listitem { navigate(coll.name, viewColloquium(coll)) }
        } }
      }
    }
  }
  
  define groupTemplate(group : ResearchGroup) {
    main()
    define sidebar() { groupSidebar(group) }
  }
      
  define page viewResearchGroup(group : ResearchGroup) {
    //groupTemplate(group)
    main()
    define sidebar() { groupSidebar(group) }
    title{text(group.acronym)}
    
    define manageMenu() {
      navigate("Edit", editResearchGroup(group))
    }
    
    define body () {
      section { 
        header{text(group.fullname)}
        
        section { 
          header{"Mission"}
          outputText(group.mission)
        }
      
        // News
        
        section { 
          header{"Recent Publications"}
          recentGroupPublications(group)
        }
        
        section { 
          header{"People"}
          output(group.members)
        }
              
        //section { 
        //  header{"People"}
        //  list { for(pers : Person in group.membersList) {
        //    listitem { navigate(pers.name, viewPerson(pers)) }
        //  } }
        //}
      }
    }
  }

  define page viewResearchGroupPlain(group : ResearchGroup) {
    section { 
      header{text(group.fullname)}
      section { 
        header{"Mission"}
        outputText(group.mission)
      }
      section { 
        header{"Recent Publications"}
        recentGroupPublications(group)
      }       
      section { 
        header{"People"}
        output(group.members)
      }
    }
  }
  
section members

  define page groupMembers(group : ResearchGroup) {
    main()
    define sidebar() { groupSidebar(group) }
    define body() {
      section{
        header{"Group Members"}
        table{ for(person : Person in group.membersList) { 
          row{ div("smallphoto"){image(person.photo)} output(person) }
        } }
      }
    }
  }
  
section projects.

  define page groupProjects(group : ResearchGroup) {
    main()
    define sidebar() { groupSidebar(group) }
    define body() { 
      section{
        header{"Projects"}
        list { for(project : ResearchProject in group.projectsList) {
          listitem { navigate(viewResearchProject(project)){
            text(project.fullname) " (" text(project.acronym) ")"
          } }
        } }
      }
    }
  }

section publications

  define page groupPublications(group : ResearchGroup) {
    main()
    define sidebar() { groupSidebar(group) }
    define body() { 
      section{
        header{"Publications by " text(group.acronym) " Members"}
        allGroupPublications(group)
      }
    }
  }
  
  define recentGroupPublications(group : ResearchGroup) {
  
   var publications : List<Publication> :=
      select distinct pub from Publication as pub, Person as pers, ResearchGroup as g
       where (g.id = ~group.id)
         and ((pers member of g._members)
         and (pers member of pub._authors))
       order by pub._year descending;   
       
    list { for(pub : Publication in publications) {
      listitem { navigate(pub.name, viewPublication(pub)) }
    } }
    
  }  
  
  define allGroupPublications(group : ResearchGroup) {
    list { for(pub : Publication) {
      listitem { navigate(pub.name, viewPublication(pub)) }
    } }
  }
  