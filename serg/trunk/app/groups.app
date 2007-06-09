module groups

section domain.

  ResearchGroup {
    acronym  :: String (name)
    fullname :: String
    mission  :: Text
    logo     :: Image
    members  -> Set<Person>
    projects -> Set<ResearchProject>
    news     -> List<News>
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
    }
  }
  
  define groupTemplate(group : ResearchGroup) {
    main()
    define sidebar() { groupSidebar(group) }
  }
      
  define page viewResearchGroup(group : ResearchGroup) {
    groupTemplate(group)
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
    groupTemplate(group)
  }
  
  define page groupPublications(group : ResearchGroup) {
    groupTemplate(group)
    allGroupPublications(group)
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
  