application com.example.colloquium

description {
  This is an automatically generated description
}

imports app/templates

section people

  entity Person {
    firstname :: String
    lastname  :: String
    fullname  :: String (name) := firstname + " " + lastname
    email     :: Email
    homepage  :: URL
  }

section colloquium

  entity Presentation {
    speaker  -> Person
    title    :: String (name)
    abstract :: Text
    date     :: Date
    time     :: String
    room     :: String
    status   :: PresentationStatus
  }

  entity PresentationStatus {
    status :: String
  }

  globals {
    var proposed  : PresentationStatus := PresentationStatus { status := "proposed" };
    var tentative : PresentationStatus := PresentationStatus { status := "tentative" };
    var confirmed : PresentationStatus := PresentationStatus { status := "confirmed" };
  }

section pages

define page home() {
  main()
  define body() {

    section { 
      header{"Next"}
      // all data of next presentation in time
    }

    section {
      header{"Coming up "}
      // presentations in the next 3 months
      list { for(p : Presentation) {
        listitem { output(p.date) ": " output(p.speaker) " : '" output(p) "'" }
      } }
    }

    section {
      header{""}
      list {
        listitem { "Future presentations" }
        listitem { "Past presentations" }
      }
    }

    navigate(createPresentation()){"new presentation"}
  }
}


  define page presentation(p : Presentation) {
    // show presentation
  }

  define page future() {
    // show future presentations
  }

  define page past() {
    // past presentations
  }

  define page editPresentation(p : Presentation) {

    // change data of a page, taking into account different levels of access

    main()

    define body() {
      if(securityContext.principal = p.speaker) 
      {
        form {
          table {
            row{ "Title" input(p.title) }
    	    row{ "Abstract" input(p.abstract) }
          }
          action("Save", saveSpeaker())
          action saveSpeaker() { p.save(); }
        }
     }

      if(securityContext.principal.role = moderator) 
      {
        form { 
          table {
            row{ "Title" input(p.title) }
    	    row{ "Abstract" input(p.abstract) }
            row{ "Speaker" input(p.speaker) }
            row{ "Date"    input(p.date) }
            row{ "Time"    input(p.time) }
            row{ "Room"    input(p.room) }
          }
          action("Save", savePresentation())
          action savePresentation() { p.save(); }
        }
      }
    }
  }
    
  define page createPresentation() {
    
    // add new presentation
    // take into account who makes it

    table {
      if(securityContext.principal.role = moderator 
         || securityContext.principal.role = researcher 
         || securityContext.principal = p.speaker) 
      {
         row{ "Title" input(p.title) }

    	 row{ "Abstract" input(p.abstract) }
      }

    }

  }

section access control

  // everyone can view colloquium pages

  // speaker can edit his presentation title and abstract, but not time and room

  // moderator can edit everything

  // audience gets email announcements

  // research group can propose new presentations

  extend entity Person {
    roles    -> Set<Role>
    username :: String
    password :: Secret
  }

  entity Role {
    role :: String
  }

  globals {
    var speaker    : Role := Role { role := "speaker" };
    var moderator  : Role := Role { role := "moderator" };
    var researcher : Role := Role { role := "researcher" };
    var audience   : Role := Role { role := "audience" };
  }

  access control rules {

    principal is Person with credentials username, password
 
    rules page editPresentation(p : Presentation) {
      rules action saveSpeaker() {
        securityContext.principal = p.speaker
      }
      rules action savePresentation() {
        securityContext.principal = p.moderator
      }
    }

  }

section init

 globals {
   var eelco : Person := Person {
      firstname := "Eelco"
      lastname := "Visser"
      email := "e.visser@tudelft.nl"
      homepage := "http://www.eelcovisser.net"
    };
   var pres : Presentation := Presentation {
      speaker := eelco
      title   := "Very abstract"
      abstract := "Very abstract"
    };
 }
