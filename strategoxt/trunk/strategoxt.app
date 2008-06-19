application org.strategoxt.www

description {
  This application is the website for strategoxt.org
}

imports templates/main
imports wiki/main
imports users/main

section home page

  define page home() 
  {
    if(config.homepage != null) { title{output(config.homepage.title)} }
    main()
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

  define page configuration(c : Configuration) {
    derive viewPage from c
  }

  define page editConfiguration(c : Configuration) {
    derive editPage from c
  }

section initialization of application configuration

  globals {
    var config : Configuration := Configuration { };    
  }

  access control rules {
    
    rules page configuration(config : Configuration) {
      isAdministrator()
    }
    rules page editConfiguration(config : Configuration) {
      isAdministrator()
    }
    
  }
