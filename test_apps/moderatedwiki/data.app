module data

section data model

  entity User {
    username :: String (name)
    password :: Secret
    email    :: Email
    status   -> UserStatus
    roles    -> Set<UserRole>
  }

  enum UserStatus {
    notApproved("Not yet approved"),
    registered("Registered")
  }

  enum UserRole {
    adminRole("Admin role"),
    moderatorRole("Moderator role")
  }

  entity Page {
    name     :: String    (id,name)
    content  :: WikiText
    authors  -> Set<User> 
  }
 
  extend entity User {
    authored -> Set<Page> (inverse=Page.authors)
  }
  
section versioning

  extend entity Page {
    previous -> PageDiff  // diff with respect to content
    modified :: Date
    version  :: Int
    author   -> User      // contributor of latest change
  }
  
  entity PageDiff {
    page     -> Page
    next     -> PageDiff
    patch    :: Patch     // patch to create content of this version from next
    created  :: Date
    previous -> PageDiff
    date     :: Date
    author   -> User
    version  :: Int
    status   -> PageDiffStatus
  }

  enum PageDiffStatus {
    notApprovedStatus("Not yet approved"),
    approvedStatus("Approved"),
    rejectedStatus("Rejected")
  }

section content of a page diff

  extend entity PageDiff {
    content  :: WikiText := computeContent()
    
    function computeContent() : WikiText {
      if (next = null) {
        return patch.applyPatch(page.content);
      } else {
        return patch.applyPatch(next.content);
      }
    }
  }
  
section creating new pages

  globals {
    function newPage(n : String) : Page {
      return Page {
        name    := n,
        author  := securityContext.principal,
        version := 0,
        content := ""
      };
    }
  }
  
section making change to a page

  // hmm, this implements a doubly linked list of diffs; you'd expect a List
  // implementation to deal with this correctly; however, sofar Hibernate
  // lists have not preserved ordering; but then one would always need the
  // Page in combination with the version; maybe good anyway to use the
  // page key as partial key of the diff anyway

  globals {
    function makeChange(p : Page, newText : WikiText, newAuthor : User) : Page {
      var diff : PageDiff := 
        PageDiff {
          page     := p,
          previous := p.previous,
          created  := p.modified,
          patch    := p.content.makePatch(newText), // was: newText.makePatch(p.content),
          author   := newAuthor, // was: p.author,
          version  := p.version,
          status   := notApprovedStatus
        };
      diff.persist();
      //p.modified := now();
      return p;
    }

    function approveRevision(p : Page, pd : PageDiff) : PageDiff {
      if (p.previous != null) {
        p.previous.next := pd;
      }
      p.previous := pd;
      p.version := p.version + 1;
      p.content := pd.content;
      p.author  := pd.author;
      p.authors.add(pd.author);
      if (p.author != null) {
        p.author.authored.add(p);
      }
      return pd;
    }
  }

