application org.webdsl.pdp


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac

procedures for PdpMeeting
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
/*
  workflow meetingWorkflow(p : PdpMeeting) {
<<<<<<< .mine
    // adds p.meetingWorkflow :: procedureStatus property
    // generate page meetingWorkflow(p : PdpdMeeting) with status
    init {
      p := PdpMeeting { };
    }
=======
>>>>>>> .r1234
    done { p.approveReport.performed }
<<<<<<< .mine
    // generate global function checkMeetingWorkflowPerformed(p : PdpMeeting)
    // that is called every time an procedure is performed on PdpMeeting
  }*/

  procedure employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { !p.employeeFillInForm.performed }
    view {
      title{"Fill in employee form"}
      derive procedurePage from p for (employeePreparation)
    }
  }

  procedure managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { !p.managerFillInForm.performed }
    view {
      title{"Fill in manager form"}
      derive procedurePage from p for (managerPreparation)
    }
  }

  procedure writeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { p.employeeFillInForm.performed && p.managerFillInForm.performed && !p.finalizeReport.performed }
    view {
      title{"Write report"}
      derive procedurePage from p for (report)
    }
  }

  procedure finalizeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { p.writeReport.performed && !p.finalizeReport.performed }
  }

  procedure approveReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { p.finalizeReport.performed && !p.approveReport.performed }
  }

section pages

  define page home() {
    main()
    define body() {
      var employee : User
      navigatebutton(signin(), "Sign in!")
      form {
        header{"Organize PDP Meeting"}
        "For: " input(employee)
        action("Organize", organize())

        action organize() {
          var p : PdpMeeting := PdpMeeting{ };
          p.employee := employee;
          //p.meetingWorkflow.start();
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
  
access control rules 
  rule page pdpMeeting(pdpMeeting : PdpMeeting) {
    securityContext.principal = pdpMeeting.employee || securityContext.principal = pdpMeeting.employee.manager
  }
    
section pages

  define pdpMeetingOperations(p : PdpMeeting) {
    "haha!"
    pdpMeetingOperationsList(p)
  }

/*
  define page pdpMeeting(pdpMeeting : PdpMeeting) {
    title {"Pdp Meeting " output(pdpMeeting)}
    main()
    define contextSidebar() {
      pdpMeetingprocedures(pdpMeeting)
    }
    define body() {
      header{"Pdp meeting " output(pdpMeeting)}
      table {
        derive viewRows from pdpMeeting
      }
    }
  }
  */

