module workflows/conference

imports workflows/pcinvitation
imports workflows/bid
imports workflows/review
imports workflows/finalversion

procedures conference  

  procedure createConference(m : ConferenceManager) {
    who {
      principal = m.admin
    }
    view {
      var c : Conference := Conference{};
      derive editPage from c for (name, chairs)
    }
    do {
      c.conferenceWorkflow.start();
      m.conferences.add(c);
    }
  }

  procedure conference(c : Conference) {
    process {
      inviteProgramCommittee;
      publishCallForPapers;
      repeat { 
        // should enable parallel non-blocking invocations of these procedures
        submitAbstract 
        |OR| submitPaper 
        |OR| extendAbstractDeadline
        |OR| extendPaperDeadline
      };
      assignReviewers;
    }
  }
  
  procedure stopAcceptingPapers(c : Conference) {
    who { principal in c.chairs }
    view {
      main()
      define contextSidebar() {
        conferenceProcedures(c)
      }
      define body() {
        section() {
          header{"Stop accepting papers"}
          "Currently submitted papers: "
          table {
            for (p: Paper in c.papersList) {
              row{ output(p) }
            }
          }
          form {
            action("Stop accepting papers", do())
          }
        }
      }
    }
  }

  /**
   * add bids for all papers, for all possible reviewers
   * also add 2 reviews for all papers
   */
  procedure startBidding(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.stopAcceptingPapers.performed && !c.startBidding.performed }
    do {
      for (p: Paper in c.papersList) {
        for (reviewer : User in c.pcMembersList) {
          var bid : Bid := Bid{
            paper    := p
            reviewer := reviewer
          };
          bid.bidWorkflow.start();
          c.bids.add(bid);
        }
        var review1 : Review := Review{
          paper := p
        };
        var review2 : Review := Review{
          paper := p
        };
        p.reviews.add(review1);
        p.reviews.add(review2);
      }
    }
  }
  
  procedure assignReviews(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.startBidding.performed && !c.startReviewing.performed }
    view {
      main()
      define contextSidebar() {
        conferenceProcedures(c)
      }
      define body() {
        section() {
          header{"Select reviewers"}

          section {
            header{"PC Member preferences"}
            for(u : User in c.pcMembersList) {
              section {
                header{output(u)}
                list {
                  for(b : Bid in u.bidsList) {
                    listitem {
                      if (b.doBid.performed)  { output(b.paper) " - " output(b.category) }
                      if (!b.doBid.performed) { output(b.paper) " - Not yet performed bid" }
                    }
                  }
                }
              }
            }
          }

          section {
            header{"Assign"}
            form {
              table {
                for(p : Paper in c.papersList) {
                  row { 
                    output(p)
                    container {
                      for(r : Review in p.reviewsList) {
                        table {
                          derive editRows from r for (reviewer)
                        }
                      }
                    }
                  }
                }
                row {
                  action("Save", do())
                }
              }
            }
          }
        }
      }
    }
  }
  
  procedure startReviewing(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.assignReviews.performed && !c.startReviewing.performed }
    do {
      for (p : Paper in c.papersList) {
        for (r : Review in p.reviewsList) {
          r.reviewWorkflow.start();
        }
      }
    }
  }
  
  procedure decideOnAcceptance(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.startReviewing.performed && !c.decideOnAcceptance.performed }
    view {
      main()
      define contextSidebar() {
        conferenceProcedures(c)
      }
      define body() {
        section() {
          header{"Decide on paper acceptance"}

          form {
            for (paper : Paper in c.papersList) {
              section {
                header{"Paper: " text(paper.title)}
                section {
                  header{"Reviews"}
                  table {
                    for (review : Review in paper.reviewsList) {
                      row { "Reviewer: " text(review.reviewer.name) "Acceptance: " text(review.acceptance.name) }
                    } 
                  }
                }
                section {
                  header {"Accept paper?"}
                  input(paper.accepted)
                }
              }
            }
            action("Save", do())
          }
        }
      }
    }
    do {
      // start workflows for all accepted papers
      for (paper : Paper in c.papersList where paper.accepted) {
        paper.editFinalVersionWorkflow.start();
      }
    }
  }
  
  procedure finalizeConference(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.decideOnAcceptance.performed && !c.finalizeConference.performed }
  }
