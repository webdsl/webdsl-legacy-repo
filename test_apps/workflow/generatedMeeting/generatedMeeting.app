application org.webdsl.generatedMeeting

description {
  A workflow example for a personal development plan workflow
}

imports templates
imports ac

section data

  entity User {
    username  :: String
    password  :: Secret
    name      :: String
    manager   -> User (inverse=User.employees)
    employees -> Set<User> 
  }

  entity ProgressMeeting {
    name           :: String := this.employee.name 
    employee       -> User
    employeeView   :: Text
    managerView    :: Text
    report         :: Text
    reportApproved :: Bool
    commentaar     :: Text
    meetings       -> Set<ProgressMeeting>
  }
  
section pages 

  define page home() {
    main()
    define body() {
      var employee : User
      //navigatebutton(signin(), "Sign in!")
      header{"Organize PDP Meeting"}
      form {
        "For: " input(employee)
        action("Organize", organize())

        action organize() {
          var p : ProgressMeeting := ProgressMeeting{ };
          p.employee := employee;
          p.persist();
          p.meeting.enable();
          // Test stuff
          return message("Done!");
        }
      }
    }
  }
  
  define page progressMeeting(p : ProgressMeeting) {
    main()
    define body() {
      table {
        derive viewRows from p for (employee, employeeView, managerView, report, commentaar)
      }
    }  
  }
section procedures

/*  procedure meeting(p : ProgressMeeting) {
    process {
      (employeeView(p) |AND| managerView(p));
      scheduleMeeting(p);
      repeat { 
        writeReport(p); 
        (approveReport(p) |XOR| commentReport(p))
      } until finalizeReport(p) 
    }  
  }*/
  
  // TODO: do-clause invoegen bij niet-gegenereerde procedures
  
  auto procedure meeting(p : ProgressMeeting) {
    enabled {
      p.andSplit.enable();
    }
  }
  
  auto procedure andSplit(p : ProgressMeeting) {
    done {
      p.employeeViewProc.enable();
      p.managerViewProc.enable();
    }
  }
  
  procedure employeeViewProc(p : ProgressMeeting) {
    who { securityContext.principal = p.employee }
    view {
      title{"Fill in employee view"}
      derive procedurePage from p 
         for (view(employee), employeeView)
    }
    done {
      p.andJoin.enable();
    }
  }

  procedure managerViewProc(p : ProgressMeeting) {
    who { securityContext.principal = p.employee.manager }
    view {
      title{"Fill in manager view"}
      derive procedurePage from p 
         for (view(employee), managerView)
    }
    done {
      p.andJoin.enable();
    }
  }

  extend entity ProgressMeeting { 
    counter :: Int
  }
  auto procedure andJoin(p : ProgressMeeting) {
    do {
      p.counter := p.counter + 1;
      if (p.counter = 2) {
        p.writeReport.enable();
      }
    }
  }

  procedure writeReport(p : ProgressMeeting) {
    who { securityContext.principal = p.employee.manager }
    view {
      title{"Write report"}
      derive procedurePage from p 
         for (view(employee), view(employeeView), 
              view(managerView), report)
    }
    done {
      p.finalizeReport.disable();
      p.xorSplit.enable();
    }
  }
  
  auto procedure xorSplit(p : ProgressMeeting) {
    do {
      p.approveReport.enable();
      p.commentReport.enable();
    }
  }

  procedure approveReport(p : ProgressMeeting) {
    who { securityContext.principal = p.employee }
    do {
      p.reportApproved := true;
    }
    done {
      p.xorJoin.enable();
    }
  }

  procedure commentReport(p : ProgressMeeting) {
    who { securityContext.principal = p.employee }
    view {
      title{"Provide comments"}
      derive procedurePage from p 
         for (view(employee), view(report), 
              commentaar)
    }
    done {
      p.xorJoin.enable();
    }
  }
  
  auto procedure xorJoin(p : ProgressMeeting) {
    done {
      p.approveReport.disable();
      p.commentReport.disable();
      p.finalizeReport.enable();
      p.writeReport.enable();
    }
  }
  
  procedure finalizeReport(p : ProgressMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { p.reportApproved }
    done {
      p.writeReport.disable();
    }
  }
