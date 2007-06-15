module colloquium

description

  A colloquium is a series of presentations.
  
end

section domain.

  Colloquium {
    name          :: String
    description   :: Text
    contact       -> Person
    mailinglist   :: Email
    group         -> ResearchGroup
    projects      -> List<ResearchProject> // for which the colloquium is relevant
    presentations <> List<Presentation>
  }
  
  Presentation {
    title    :: String (name)
    speaker  -> Person
    date     :: Date
    time     :: Date
    end      :: Date
    venue    :: String
    abstract :: Text
    projects :: List<ResearchProject>
  }
  
section pages.

  define page viewColloquium(colloquium : Colloquium) {
    main()
    define sidebar() {
      groupSidebar(colloquium.group)
    }
    define manageMenu() {
      navigate("Edit", editColloquium(colloquium))
    }
    define body() {
      section{ 
        header{text(colloquium.name)}
        
        outputText(colloquium.description)
        
        section{ 
          header{"Contact"}
          "The colloquium is organized by " output(colloquium.contact) ". "
          "Announcements for presentations are sent to the " output(colloquium.mailinglist) " mailinglist."
        }
        
        section {
          header{"Upcoming Presentations"}
          for(presentation : Presentation in colloquium.presentations) {
            row{ text(presentation.date) 
                 output(presentation)
                 output(presentation.speaker) }
          }
        }
        
      }
    }
  }