module usecases

use cases

  /* Syntax:
     "case" Id "(" FArg* ")" "<" ActorExpression ">" "{"
       "pre" "(" Expr ")"
       "logic" "{" LogicStatement* "}"
     "}"
     
     "new" Id ":" Sort ";" -> LogicStatement
     "edit" Id ";"         -> LogicStatement
     Assignment            -> LogicStatement
     Call                  -> LogicStatement // add to collection, remove from collections
  */

  case newPage() <anyone> {
    logic {
      new p : Page;
      new pd : PageDiff;
      p.add(pd);
      pd.status := notApprovedStatus;
      return home();
    }
  }

  case editPage(p : Page) <anyone> {
    logic {
      new pd : PageDiff;
      p.add(pd);
      pd.status := notApprovedStatus;
      //return message("Your changes have been saved, but still have to be approved by a moderator.");
    }
  }

  define page editPage(p : Page) {
    form {
      action("Save", logic())
    }
  }

  case approveRevision(pd : PageDiff) <moderatorRole in actor.roles> {
    pre(pd.status = notApprovedStatus)
    logic {
      pd.status := approvedStatus
    }
  }

  case rejectRevision(pd : PageDiff) <moderatorRole in actor.roles> {
    pre(pd.status = notApprovedStatus)
    logic {
      pd.status := rejectedStatus
    }
  }
