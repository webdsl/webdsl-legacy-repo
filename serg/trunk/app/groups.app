module groups

section domain.

  ResearchGroup {
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

section group page.

  define groupSidebar(group : ResearchGroup) {
    list{
      listitem{ navigate(viewResearchGroup(group)){text(group.acronym)} }
      listitem{ navigate("People", groupMembers(group)) }
      listitem{ navigate("Publications", groupPublications(group)) }
      list { navigate("Projects", groupProjects(group))
        for(project : ResearchProject in group.projectsList) {
          navigate(project.name, viewResearchProject(project))
        }
      }
      list { "Colloquia"
        for(coll : Colloquium in group.colloquiaList) {
          navigate(coll.name, viewColloquium(coll))
        }
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
          for(pers : Person in group.membersList) {
            navigate(pers.name, viewPerson(pers))
          }
        }
      }
    }
  }
  
  define page groupMembers(group : ResearchGroup) {
    main()
    define sidebar() { groupSidebar(group) }
    define body() {
      section{
        header{"Group Members"}
        for(person : Person) { 
          image(person.photo) output(person)
        }
      }
    }
  }
  
  define page groupProjects(group : ResearchGroup) {
    main()
    define sidebar() { groupSidebar(group) }
    define body() { 
      section{
        header{"Projects"}
        for(project : ResearchProject in group.projectsList) {
          navigate(viewResearchProject(project)){
            text(project.fullname) " (" text(project.acronym) ")"
          }
        }
      }
    }
  }
   
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
    for(pub : Publication) {
      navigate(pub.name, viewPublication(pub))
    }
  }  
  
  define allGroupPublications(group : ResearchGroup) {
    for(pub : Publication) {
      navigate(pub.name, viewPublication(pub))
    }
  }
  