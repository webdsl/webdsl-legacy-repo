module wiki/twiki

section data model for TWiki users and groups

  entity TwikiUser {
    username    :: String (id, name)
    fullname    :: String
    email       :: Email
    homepage    :: URL
    country     :: String
    affiliation :: String
    converted   :: Bool
  }
  
section user conversion

  globals {
     
    function findUser(name : String) : User {
      var users : List<User> := 
          select u from User as u where u.username = ~name;
          
      if (users.length > 0) 
        {
          for(u : User in users) {
            return u;
          }
        }
      else 
        {
          var tusers : List<TwikiUser> := 
              select u from TwikiUser as u 
               where (u.username = ~name);
          var nu : User := User { username := name };
               
          if (tusers.length > 0)
            {
              for (tu : TwikiUser in tusers) {
                nu.fullname    := tu.fullname;
                nu.email       := tu.email;
                nu.homepage    := tu.homepage;
                nu.country     := tu.country;
                nu.affiliation := tu.affiliation;
                nu.confirmed   := false;
                tu.converted   := true;
              }
            }

          nu.persist();
          return nu;
        }
    }
  
  }

section data model for TWiki pages

  entity TwikiPage {
    key      :: String (name) := this.web + "/" + this.topic + "_" + this.version.toString()
    web       :: String
    topic     :: String
    version   :: Int
    date      :: String
    author    :: String
    content   :: WikiText
    converted :: Bool
  }
  
  access control rules {
    
    rules page twikiPage(*) {
      true
    }
    
    rules page editTwikiPage(*) {
      securityContext.loggedIn
    }
    
    rules page createTwikiPage() {
      securityContext.loggedIn
    }
    
  }
  
section wiki page
  
  define page twikiPage (p : TwikiPage)
  {
    main()
    title{output(p.key)}
    define wikiOperationsMenuItems() {
      twikiPageOperationsMenuItems(p)
    }
    define body() {
      section {
        block("twikiTopicTitle") {
          header{ output(p.key) }
        }
	par{ output(p.content) }
	hr{}
	table
      }
    }
  }
  
section page operations
  
  define twikiPageOperationsMenuItems(p : TwikiPage)
  {
    menuitem{ navigate(editTwikiPage(p)) { "Edit This Page" } }
    menuspacer{}
  }
  
  
  globals {
  
    function twikiToWiki(p : TwikiPage) : String {
      return "";
    }
 
  }
  
  
  
note { 

  TODO
  
  - convert TwikiUser to User
    - do this on demand; 
      only users who have actually authored a page
  
  - convert TwikiPage to Page
  
  - convert Epoch date to Date + Time
  
  
}