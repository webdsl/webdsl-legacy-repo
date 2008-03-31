module review

section data model

  entity ReviewTask : ConferenceTask {
    review -> Review
    name   :: String := "Review paper '" + review.paper.title + "' for " + this.conference.name
  }

section data logic

  globals {
    function initReviews(c : Conference) : Conference {
      // Assign the reviewer role and review tasks to every PC member
      var rrole : ConferenceRole := getConferenceRole(c, reviewerRole);
      for(u : User in c.pcList) {
        u.roles.add(rrole);
        for(r : Review in u.authoredReviewsList) {
          var t : ReviewTask := ReviewTask { conference := c, review := r };
          assignTask(t, u);
        }
      }
      return c;
    }
  }

section pages

  access control rules {
    rules page reviewTask(t : ReviewTask) {
      t in securityContext.principal.tasks && !t.completed
    }
  }

  define page reviewTask(t : ReviewTask) {
    title{"Review '" output(t.review.paper.name) "'"}
    main()
    define contextSidebar() {
      conferenceSidebar(t.review.paper.conference)
    }
    define body() {
      header{"Review '" output(t.review.paper) "'"}
      form {
        table {
          row { "Classification:" input(t.review.classification) }
          row { "Expertise:" input(t.review.expertise) }
          row { "Relevance:" input(t.review.relevance) }
          row { "Summary:" input(t.review.summary) }
          row { "PC Comments:" input(t.review.committeeComments) }
          row { "Evaluation:" input(t.review.evaluation) }
        }
        action("Submit review", submitReview())
        action submitReview() {
          t.completed := true;
          t.review.completed := true;
          checkReadyToDecideAcceptance(t.review.paper.conference);
          return tasks(securityContext.principal);
        }
      }
    }
  }
