application org.webdsl.colloquium

description {

  An application for managing and presenting presentations in a colloquium series.

  Creation

  - creating and editing presentations (by moderator, with restricted subsets open to
    others)

  - setting up colloquium series with repeating time slots

  - proposing presentations (by selected public)

  Notifications

  - inviting speakers

  - sending notifications to speakers

  - sending announcements to registered audience

}

imports app/templates
imports app/datamodel
imports app/init
imports app/accesscontrol

section all colloquia

  define page home() 
  { 
    main()
    define body() {
      list{ for(c : Colloquium) {
        listitem{ output(c) }
      } }
    }
  }

section colloquium pages

  define page viewColloquium(c : Colloquium)
  {
    main()
    define body()
    {
      section { 
        header{output(c.fullname)}

	output(c.description)


	// todo: select first scheduled presentation after today
	// var next : Presentation := select p from Presentation as p where 

	var next : Presentation := ac;
      
        section { 
          header{"Next Up"}
          showFullPresentation(next)
        }

        section {
          header{"Near Future"}
          // presentations in the next 3 months or first 10 future presentations
          list { for(p : Presentation in c.presentationsList) {
            listitem { output(p.date) ": " output(p.speaker) " : '" output(p) "'" }
          } }
        }

        section {
          header{"Future and Past"}
          list {
            listitem { navigate(future(c)){"Future presentations"} } 
            listitem { navigate(past(c)){"Past presentations"} }
	    // other indices of talks by speaker, by topic, by project, etc.
          }
        }

	par{ "For questions or suggestions contact " output(c.moderator) }

        par{ navigate(newPresentation(c)){"Schedule or propose a presentation"} }
      }
    }
  }

  define page future(c : Colloquium) {
    // show future presentations
    main()
    define body() { 
      section{
        header{output(c) " Colloquium: Future Presentations"}

		// note: we need string and date comparisons
		// note: this should be translated to a (more efficient) query
		// note: the list should be ordered
        for(p : Presentation in c.presentationsList 
            // where p.date > today() 
            // ordered by date
           )
        {
          section{ 
            header{output(p.date) " : " output(p)}
            "Speaker: " output(p.speaker)
	    par{ "Abstract: " output(p.abstract) }
          }
        }

      }
    }
  }

  // note: past is the same as future, but for the predicate applied in the query
  define page past(c : Colloquium) {
    // past presentations
    main()
  }


section presentations

  define showFullPresentation(p : Presentation) 
  {
    section{
      header{output(p.title)}
      list {
        listitem{ "Speaker: " output(p.speaker) " (" output(p.speaker.affilliation) ")" } 
        listitem{ "Date: "    output(p.date) " at " output(p.time) } 
        listitem{ "Room: "    output(p.room) }
      }
      section{
        header{"Abstract"}
        output(p.abstract)
      }
    }
  }

  define page viewPresentation(p : Presentation) 
  {
    // show presentation
    main()
    define body() {
      "The " output(p.colloquium) " Colloquium presents"
      showFullPresentation(p)
    }
  }

  define page newPresentation(c : Colloquium)
  {
    // change data of a page, taking into account different levels of access

    main()

    define body() {
      //if(securityContext.principal = p.speaker) 
      //{
      //  form {
      //    table {
      //      row{ "Title" input(p.title) }
      //	    row{ "Abstract" input(p.abstract) }
      //    }
      //    action("Save", saveSpeaker())
      //    action saveSpeaker() { p.save(); }
      //  }
      //}

      //if(securityContext.principal.role = moderator) 

      var p : Presentation := Presentation { colloquium := c };

      //{
        form {
          table {
            row{ "Title"    input(p.title) }
    	    row{ "Abstract" input(p.abstract) }
            row{ "Speaker"  input(p.speaker) }
	    row{ "" 
              table {
                var newSpeaker : Person := Person{};
                row{ "First name"   input(newSpeaker.firstname) }
                row{ "Last name"    input(newSpeaker.lastname) }
                row{ "Affilliation" input(newSpeaker.affilliation) }
                row{ "Email"        input(newSpeaker.email) }
                row{ "Homepage"     input(newSpeaker.homepage) }
		row{ ""             action("Add new speaker", addNewSpeaker()) }
                action addNewSpeaker() {
		  p.speaker := newSpeaker;
                  newSpeaker := Person{};
                }
              }
            }
            row{ "Date"     input(p.date) }
            row{ "Time"     input(p.time) }
            row{ "Room"     input(p.room) }
          }
          action("Save", savePresentation())
          action savePresentation() {
            c.presentations.add(p);
	    c.save();
            return viewColloquium(c); 
          }
        }
      //}
    }
  }
    
