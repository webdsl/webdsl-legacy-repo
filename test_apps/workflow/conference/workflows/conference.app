module workflows/conference

imports workflows/pcinvitation
imports workflows/bid
imports workflows/review
imports workflows/finalversion

operations conference  
  workflow conferenceWorkflow(c : Conference) {
    init {
// hoeft niet meer; er is al een conference-object, en we hoeven niets te doen aan het begin      c := Conference{}
    }
    done { c.finalizeConference.performed }
  }
  
  /**
   * Inviting pc members. Separate workflows manage this invitation. 
   */
  operation invitePcMember(c: Conference) {
    who { securityContext.principal in c.chairs }
    when { !c.finalizePc.performed }
    do {
      var pcInv : PcInvitation := PcInvitation{};
      pcInv.pcInvitationWorkflow.start(name, email);
      c.pcInvitations.add(pcInv);
    }
    view {
      var name : String
      var email : Email
      main()
      define contextSidebar() {
        conferenceOperations(c)
      }
      define body() {      
        form {
          table {
            row { "Name:" input(name) }
            row { "Email:" input(email) }
          }
          action("Invite", do())
        }
      }
    }
  }
  
  /**
   * If there are enough pc members that have accepted the invitation, we can finalize the pc
   */
  operation finalizePc(c: Conference) {
    who { securityContext.principal in c.chairs }
    when { !c.finalizePc.performed }
    do {
      c.finalizePc.performed := true;
    }
    view {
      main()
      define contextSidebar() {
        conferenceOperations(c)
      }
      define body() {
        section() {
          header{"Finalize the program committee"}
          
          // view all invitations with their statuses
          section() {
            header{"Invitations"}
            table {
              row {
                "" "name" "response"
              }
              for (pcInv : PcInvitation in c.pcInvitationsList) {
                row {
                  "Invitation: "
                  text(pcInv.user.name)
                  if (pcInv.pcInvitationWorkflow.done) {
                    if (pcInv.accepted) {
                      "accepted"
                    } 
                    if (!pcInv.accepted) {
                      "rejected"
                    }
                  } 
                  if (!pcInv.pcInvitationWorkflow.done) {
                    "not responded yet"
                  }
                }
              }
            }
          }
          
          // view the list of program committee members
          section() {
            header{"Program Committee members"}
            table {
              for (user : User in c.pcMembersList) {
                row {
                  output(user)
                }
              }
            }
          }
        
          form {
            action("Finalize", do())
          }       
        }
      }
    }
  }
  
  operation submitPaper(c: Conference) {
    when { c.finalizePc.performed && !c.stopAcceptingPapers.performed }
    do {
      paper.save();
      c.papers.add(paper);
    }
    view {
      var paper : Paper := Paper{}
      title{"Submit paper"}
      main()
      define contextSidebar() {
        conferenceOperations(c)
      }
      define body () {
        section(){
          header{"Submit paper"}
          form {
            table {
              derive editRows from paper for (title, abstract, authors)
            }
            action("Save", do()){}
            action("Cancel", cancel()){}
          }
        }
        action cancel ( )
        {
          return home();
        }
      }
    }
  }

  operation stopAcceptingPapers(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.finalizePc.performed && !c.stopAcceptingPapers.performed }
    view {
      main()
      define contextSidebar() {
        conferenceOperations(c)
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
  operation startBidding(c : Conference) {
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
        var review1 : Review := Review{};
        var review2 : Review := Review{};
        p.reviews.add(review1);
        p.reviews.add(review2);
      }
    }
  }
  
  operation assignReviews(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.stopAcceptingPapers.performed && !c.startReviewing.performed }
    view {
      main()
      define contextSidebar() {
        conferenceOperations(c)
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
                        select(r.author from c.pcMembers)
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
  
  operation startReviewing(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.stopAcceptingPapers.performed && !c.startReviewing.performed }
    do {
      for (p : Paper in c.papersList) {
        for (r : Review in p.reviewsList) {
          r.reviewWorkflow.start();
        }
      }
    }
  }
  
  operation decideOnAcceptance(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.startReviewing.performed && !c.decideOnAcceptance.performed }
    view {
      main()
      define contextSidebar() {
        conferenceOperations(c)
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
                      row { "Reviewer: " text(review.author.name) "Acceptance: " text(review.acceptance) }
                    } 
                  }
                }
                section {
                  header {"Accept paper?"}
                  input(paper.accepted)
                }
              }
            }
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
  
  operation finalizeConference(c : Conference) {
    who { securityContext.principal in c.chairs }
    when { c.decideOnAcceptance.performed && !c.finalizeConference.performed }
  }