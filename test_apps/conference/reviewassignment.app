module reviewassignment

section pages

globals {
  function initializeReviews(c : Conference) : Conference {
    var r : Review;
    for(p : Paper in c.papers) {
      // Create 3 reviews for each
      r := Review { paper := p };
      r.persist();
      r := Review { paper := p };
      r.persist();
      r := Review { paper := p };
      r.persist();
    }
    return c;
  }
}

define decideReviewAssignment(c : Conference) {
  header{"PC Member preferences"}
  for(u : User in c.pcList) {
    section {
      header{output(u)}
      list {
        for(b : Bid in u.bidsList) {
          listitem {
            output(b.paper) " - " output(b.category)
          }
        }
      }
    }
  }
  header{"Assign"}
  form {
    table {
      for(p : Paper in c.papersList) {
        row { 
          output(p)
          container {
            for(r : Review in p.reviewsList) {
              input(r.author)
            }
          }
        }
      }
    }
    action("Assign", assign())
    action assign() {
      return message("Assignments saved");
    }
  }
}
