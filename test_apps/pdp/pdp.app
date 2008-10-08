application org.webdsl.pdp


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac
imports style
imports layout

section data

section procedures

  auto procedure startWf(p : Meeting) {
    process {
      (employeePrepare(p) and managerPrepare(p));
      repeat {
        writeReport(p);
        (approveReport(p) xor commentReport(p))
      } until {finalizeReport(p)}
    }
  }

  procedure employeePrepare(p : Meeting) {
    who { securityContext.principal == p.employee }
    view {
      title{"Fill in employee preparation"}
      derive procedurePage from p for (employeePreparation)
    }
  }

  procedure managerPrepare(p : Meeting) {
    who { securityContext.principal == p.employee.manager }
    view {
      title{"Fill in manager preparation"}
      derive procedurePage from p for (managerPreparation)
    }
  }

  procedure writeReport(p : Meeting) {
    who { securityContext.principal == p.employee.manager }
    view {
      title{"Write report"}
      derive procedurePage from p for (report)
    }
  }

  procedure finalizeReport(p : Meeting) {
    who { securityContext.principal == p.employee.manager }
  }

  procedure approveReport(p : Meeting) {
    who { securityContext.principal == p.employee }
    do {
      p.approved := true;
      p.save();
    }
  }

  procedure commentReport(p : Meeting) {
    who { securityContext.principal == p.employee }
    view {
      derive procedurePage from p for (comments)
    }
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
          var p : Meeting := newMeeting();
          p.employee := employee;
          p.startWf.enable();
          p.persist();
          // Test stuff
          return message("Done!");
        }
      }
    }
  }
  
access control rules 
  rule page meeting(meeting : Meeting) {
    securityContext.principal == meeting.employee || securityContext.principal == meeting.employee.manager
  }
    
section pages

/*  define meetingProcedures(p : Meeting) {
    "haha!"
    meetingProceduresList(p)
  }
  */

  define page meeting(meeting : Meeting) {
    title {"Pdp Meeting " output(meeting)}
    main()
    define contextSidebar() {
      meetingProcedures(meeting)
    }
    define body() {
      header{ output(meeting)}
      group("Meeting") {
        derive viewRows from meeting for (employee, employeePreparation, managerPreparation, report, comments, approved)
      }
    }
  }

  define page user(u : User) {
    output(u.name)
  }

  define page editUser(u : User) {
    form {
      table {
        row { "Name: " input(u.name) }
        row { "Manager: " input(u.manager) }
        row { "Employees: " input(u.employees) }
      }
      action("Save", save())

      action save() {
        u.save();
        return home();
      }
    }
  }

  define newUserForm() {
    var u : User := User{}
    form {
      input(u.username)
      action("Create", create())

      action create() {
        u.save();
        return home();
      }
    }
  }

