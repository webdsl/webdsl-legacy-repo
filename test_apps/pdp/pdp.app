application org.webdsl.pdp


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac

operations for PdpMeeting
/*

  pdpProcess(p : PdpMeeting) = 
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

  workflow meetingWorkflow(p : PdpMeeting) {
    done { p.approveReport.performed }
  }

  operation employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { !p.employeeFillInForm.performed }
    view {
      title{"Fill in employee form"}
      derive operationPage from p for (employeePreparation)
    }
  }

  operation managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { !p.managerFillInForm.performed }
    view {
      title{"Fill in manager form"}
      derive operationPage from p for (managerPreparation)
    }
  }

  operation writeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { p.employeeFillInForm.performed && p.managerFillInForm.performed && !p.finalizeReport.performed }
    view {
      title{"Write report"}
      derive operationPage from p for (report)
    }
  }

  operation finalizeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { p.writeReport.performed && !p.finalizeReport.performed }
  }

  operation approveReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { p.finalizeReport.performed && !p.approveReport.performed }
  }

section pages

  define page home() {
    main()
    define body() {
      var employee : User
      form {
        header{"Organize PDP Meeting"}
        "For: " input(employee)
        action("Organize", organize())

        action organize() {
          var p : PdpMeeting := PdpMeeting{ };
          p.employee := employee;
          p.meetingWorkflow.start();
          p.persist();
          // Test stuff
          if(Or[x > 3 | x : Int in [1, 2, 3, 4, 5]]) {
            // Bla
          }
          return message("Done!");
        }
      }
    }
  }

  define pdpMeetingOperations(p : PdpMeeting) {
    "haha!"
    pdpMeetingOperationsList(p)
  }

  access control rules {
    rules page pdpMeeting(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee || securityContext.principal = pdpMeeting.employee.manager
    }
  }

/*
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
  */

