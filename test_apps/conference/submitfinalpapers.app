module submitfinalpapers

section data model

  entity SubmitFinalPaperTask : Task {
    paper -> Paper
    name  :: String := "Submit final version of paper '" + paper.name
  }

section business logic

  globals {
    function notifyAuthors(c : Conference) : Conference {
      c.stage := submitFinalPapers;
      for(p : Paper in c.papersList) {
        p.final := false; // Be sure it's not already marked as a final submission
        if(p.classification = rejectedClassification) { 
          // Too bad...
        } else {
          // Yay! Create a write final paper task
          var t : SubmitFinalPaperTask := SubmitFinalPaperTask { paper := p };
          for(author : User in p.authorsList) {
            assignTask(t, author);
          }
        }
      }
      return c;
    }
  }

section pages

  access control rules {
    rules page submitFinalPaperTask(t : SubmitFinalPaperTask) {
      t in securityContext.principal.tasks
    }
  }

  define page submitFinalPaperTask(t : SubmitFinalPaperTask) {
    title{"Submit final version of paper"}
    main()
    define contextSidebar() {
      conferenceSidebar(t.paper.conference)
    }
    define body() {
      header{"Submit final version of paper '" output(t.paper) "'"}
      form {
        table {
          row { "Title: " input(t.paper.title) }
          row { "Abstract: " input(t.paper.abstract) }
          row { "Authors: " output(t.paper.authors) }
        }
        action("Submit", submitPaper())

        action submitPaper() {
          t.paper.final := true;
          t.completed := true;
          return message("The final version of your paper has been submitted succesfully.");
        }
      }
    }
  }
