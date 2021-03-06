application com.example.moderatedwiki

description {
  This is an automatically generated description
}

imports templates
imports data
imports user
imports init

section access control

  access control rules {
    principal is User with credentials username, password

    rules page *(*) {
      true
    }
    rules template *(*) {
      true
    }
    rules action *(*) {
      true
    }
  }

section pages

  define page home() {
    main()
    title{"Wiki Page Index"}
    define body() {
      section { 
        header{"Wiki Page Index"}
        list {
          for(p : Page) { 
            listitem { output(p) }
          }
        }
      }
    }
  }

  define page page(p : Page) {
    /*init {
      if(p.authors.length = 0) {
        // This is a new page
        goto editPage(p);
      }
    }*/
    main()
    title{output(p.name)}
    define contextSidebar() {
      navigate(editPage(p)) { "Edit" }
      revisionsToApprove(p)
    }
    define body() {
      section {
        header{ output(p.name) }
        par{ output(p.content) }
	
        block("wikiPageByLine") {
          par{"Contributions by " 
            for(author : User in p.authorsList) {
              output(author) " "
            }
          }
          par{ previousLink(p) }
        }
      }
    }
  }

  access control rules {
    rules template revisionsToApprove(*) {
      moderatorRole in securityContext.principal.roles
    }
  }
  define revisionsToApprove(p : Page) {
    section {
      header {"Revisions to approve"}
      list {
        for(pd : PageDiff where pd.page = p && pd.status = notApprovedStatus) {
          listitem { navigate(approveRevision(pd)) { "by " output(pd.author) } }
        }
      }
    }
  }
  
section wiki page history
  
  define previousLink(p : Page)
  {
    if (p.previous != null) {
      navigate(diff(p.previous)){"Previous"}
    }
  }
	
  define page diff(diff : PageDiff)
  {
    main()
    title{output(diff.page.name) " / version " output(diff.version)}
    define body() {
      section{
        header{output(diff.page) " / version " output(diff.version)}
        output(diff.content)
        par{ "Last changes by " output(diff.author) }
        par{ nextPreviousLink(diff) }
      }
    }
  }
  
  define nextPreviousLink(diff : PageDiff)
  {
     if (diff.previous != null ) {
          navigate(diff(diff.previous)){"Previous"} " "
     }
     if (diff.previous = null ) {
          "Previous" " "
     }
     if (diff.next != null ) {
       navigate(diff(diff.next)){"Next"}
     }
     if (diff.next = null ) {
       navigate(page(diff.page)){"Next"}
     }
  }
  
section wiki page editing

  define page editPage(p : Page) {
    var newContent : WikiText := p.content;
    main() 
    title{"Edit Page: " output(p.name)}
    define body() {
      section {
        header{"Edit Page: " output(p.name)}
        form { 
          par{ input(newContent) }
          par{ action("Save changes", savePage()) }

          action savePage() {
            makeChange(p, newContent, securityContext.principal);
            p.persist();
            return message("Thank you, your changes will be available online once a moderator approves it.");
          }
        }
      }
    }
  }
  
  define page newPage() {
    var newName    : String;
    var newContent : WikiText;
    main() 
    title{"Create New Wiki Page"}
    define body() {
      section {
        header{"Create New Wiki Page"}
        form { 
          par{ "Name" input(newName) }
          par{ input(newContent) }
          par{ action("Save changes", savePage()) }
          par{}
          par{ 
            "*) The name of a page is the key that is used to refer to it "
            "and cannot be changed after creation. " 
          }
               
          action savePage() {
            var p : Page := newPage(newName);
            makeChange(p, newContent, securityContext.principal);
            p.persist();
            return message("Thank you, your changes will be available online once a moderator approves it.");
          }
        }
      }
    }
  }

  access control rules {
    rules page approveRevision(*) {
      moderatorRole in securityContext.principal.roles
    }
  }
  define page approveRevision(pd : PageDiff) {
    main()
    title{"Approve revision for " output(pd.page.name) }
    define body() {
     header{"Approve revision for " output(pd.page) }
     form {
       table {
         row { "Submitted by" output(pd.author) }
         row { "Content:" output(pd.content) }
       }
       action("Accept", accept())
       action("Reject", reject())

       action accept() {
         approveRevision(pd.page, pd);
         pd.status := approvedStatus;
         return page(pd.page);
       }
       action reject() {
         pd.status := rejectedStatus;
         return page(pd.page);
       }
     }
    }
  }

