application org.strategoxt.www

description {
  This application is the website for webdsl.org
}

imports templates/main
imports wiki/main
imports users/main

section home page

  define page home() 
  {
    title{output(config.homepage.name)}
    main()
    define sidebar() {
      if(config.sidebar != null) { output(config.sidebar.content) } 
    }
    define body() {
      if(config.homepage != null) { output(config.homepage.content) }
    }
  }

section application configuration

  entity Configuration {
    logo          :: Image
    homepage      -> Topic
    sidebar       -> Topic
    users         -> Set<User>
    wikistartpage -> Topic
    starttopics   -> Set<Topic>
  }
  
  access control rules {
    rules page configuration(*) {
      isAdministrator()
    }
    rules page editConfiguration(*) {
      isAdministrator()
    }
  }

section initialization of application configuration

  globals {
        
    var config : Configuration := Configuration { };
    
  }
