entity Paper {
  title :: String
  body  :: Text
}

entity Review {
  paper    :: Paper
  review   :: Text
  reviewer :: String
}

define page home() {
  main()
  define body() {
    form {
      action("Make new controlled paper", make())
      action make() {
        var p : Paper := startworkflow processPaper(); // not within do{} - no dependentWorkflows
      }
    }
  }
}

workflow processPaper(p : Paper) {
  init {
    p := Paper {}
  }
  done {
    p.allReviewsPerformed
  }
}

operation startReviews(p : Paper) {
  when { !p.startReviews.performed }
  do {
    // start workflows on both reviews
    r1 : Review := startworkflow reviewPaper(p)        // p.dependentWorkflows.add(r1)
    newReviewPaper(review2)
  }
  view {
    title{"Start 2 reviews"}
    form {
      action("Start", do())
    }
  }
}

define page doSomething(p : Paper) {
  // ...
}

operation allReviewsPerformed(p : Paper) {
  var allPerformed : Bool := true;
  for (r : Review where r.paper = p) {
    if (!r.review.performed) {
      allPerformed := false;
    }
  }
  when { allPerformed }
  do { p.allReviewsPerformed := true }
}

workflow reviewPaper(r : Review) {
  init(p : Paper) {
    r.paper := Review {
      paper := p
    }
  }
  done {
    r.review.performed
  }
}

operation review(p : Paper) {
  who { securityContext.principal in reviewers }
  when { !status.reviewed }
  view {
    title{"Review paper " d.title}
    derive operationPage from d
  }
}