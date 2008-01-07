application org.strategoxt.www

description {
  This application is the website for webdsl.org
}

imports templates/main
imports wiki/main
imports users/main
//imports issues/main
//imports blog/main
//imports forum/main
//imports contexts/main
//imports news/main
//imports presentations/main

section home page

  define page home() 
  {
    title{output(config.homepage.name)}
    main()
    define body() {
      output(config.homepage.content)
      //section{
      //  header{navigate(news()){"News"}}
      //  recentNews()
      //}
    }
  }

section application configuration

  entity Configuration {
    logo          :: Image
    //blogs         -> Set<Blog>
    //forums        -> Set<Forum>
    homepage      -> Page
    sidebar       -> Page
    users         -> Set<User>
    wikistartpage -> Page
    startpages    -> Set<Page>
    //projects      -> Set<Project>
  }
  
  access control rules {
    rules page configuration(*) {
      securityContext.loggedIn
    }
    rules page editConfiguration(*) {
      securityContext.loggedIn
    }
  }

section initialization of application configuration

  globals {
  
    var appSidebar : Page := Page{
      key    := "Sidebar"
      content := ""
    };
    
    var homePage : Page := Page{
      key    := "WebHome"
      content := ""
      author  := eelco
    };
    
    var config : Configuration := Configuration {
      homepage      := homePage
      wikistartpage := homePage
      sidebar       := appSidebar
    };
    
  }


  

