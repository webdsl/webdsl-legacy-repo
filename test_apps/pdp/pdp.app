application org.webdsl.pdp


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac

operations for PdpMeeting
/*

  pdpProcess = 
    createMeeting; 
    (employeeFillInForm || managerFillInForm);
    writeReport*;
    finalizeReport;
    approveReport;
    archiveReport


  foobar(d) = 
    baz(d); (foo + bar); baz ; (stop + foobar(d))

  review(paper) =
    rev := createReview(paper);
    writeReview(rev)+;
    finalizeReview(rev);
    (viewAllReviews(paper) || commentReviews(paper))*;
*/
  operation employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { !status.employeeFilledIn }
    do {
      status.employeeFilledIn := true;
    }
    view {
      title{"Fill in employee form"}
      derive operationPage from p for (employeePreparation)
    }
  }

  operation managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { !status.managerFilledIn }
    do {
      status.managerFilledIn := true;
    }
    view {
      title{"Fill in manager form"}
      derive operationPage from p for (managerPreparation)
    }
  }

  operation writeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { status.employeeFilledIn && status.managerFilledIn && !status.final}
    view {
      title{"Write report"}
      derive operationPage from p for (report)
    }
  }

  operation finalizeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { status.employeeFilledIn && status.managerFilledIn  && !status.final }
    do {
      status.final := true;
    }
  }

  operation approveReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { status.employeeFilledIn && status.managerFilledIn && !status.employeeApproved && status.final}
    do {
      status.employeeApproved := true;
    }
  }

section pages

  define page home() {
    main()
    define body() {
      "Use the menus!"
    }
  }

  access control rules {
    rules page pdpMeeting(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee || securityContext.principal = pdpMeeting.employee.manager
    }
  }

  define page pdpMeeting(pdpMeeting : PdpMeeting) {
    title {"Pdp Meeting " output(pdpMeeting)}
    main()
    define contextSidebar() {
      pdpMeetingOperations(pdpMeeting)
    }
    define body() {
      header{"Pdp meeting " output(pdpMeeting)}
      table {
        derive viewRows from pdpMeeting
      }
    }
  }

