application org.webdsl.actesting

section access control testing
  define page home() 
  { 
    main()
    define body() {
      list{ for(p : Person) {
        listitem{ output(p) }
      } }
    }
  }
  globals{
    var eelco : Person := Person {
      firstname    := "Eelco"
      username     := "eelco"
      password     := "foo"
    };

    var zef : Person := Person {
      firstname    := "Zef"
      username     := "zef"
      password     := "groningen"
    };  

    var danny : Person := Person {
      firstname    := "Danny"
      username     := "danny"
      password     := "ac"
    };  
  }
  entity Person {
    firstname    :: String

  }

  extend entity Person {
    roles    -> Set<Role>
    username :: String
    password :: Secret
  }

  entity Role {
    role :: String (name)
  }

  globals {
    var speaker    : Role := Role { role := "speaker" };
    var moderator  : Role := Role { role := "moderator" };
    var researcher : Role := Role { role := "researcher" };
    var audience   : Role := Role { role := "audience" };
  }

  access control rules {

    principal is Person with credentials username, password

    rules page home() 
    {
      true
    }

    //global rules on actions
    rules action cancel(*)
    {
      true
    }
    
    rules page testTemplateAC()
    {
      true
      //template inside page with exact expression match
      rules template div("bla")
      {
         true
      }
      //template inside page with argument matching (similar to pages and actions)
      rules template tempbla(*)
      {
         true
      }
    }
    
    //global rules on templates with template action specific checks
    rules template tempbla()
    {
      true
      rules template div("divinsidetemplatedef")
      {
        true
        true
      
      }
      rules action doit()
      {
        true&&true
      }
    }
    
        
    //global rules on templates with exact expression match
    rules template div("bla2")
    {
      true
      true
      true
    }
    

    rules template div("divinsidetemplatedef")
    {
      true
      
    }
  }
  
  define tempbla()
  {
    output("tempbla")
    div("divinsidetemplatedef"){output("meertempbla")}
    action doit()
    {
      if(true=true){}
    } 
  }
  
  define page testTemplateAC(){
    
    tempbla()
    
    div("bla"){
      
      output(securityContext.principal)
      
    }
    div("bla2"){
    
      output(securityContext.loggedIn)
      
    }   
  
  }
  
  

  
section main template.

  define main() {
    div("outersidebar") {
      sidebar()
    }
    div("outerbody") {
      div("menubar") {
        menu()
      }
      body()
      footer()
    }
  }

section basic page elements.

  define sidebar() {
    list {
      listitem{ navigate("Home", home()) }
    }
  }
  
  define footer() {
    "generated with "
    navigate("Stratego/XT", url("http://www.strategoxt.org"))
  }
  
section menus.
  
  define menu() {

    
  }
  
section entity management.

  define manageMenu() {}
  
  define page manage() {
    main()
    define sidebar() {}
    define body() {
      createMenu()
      allMenu()
    }
  }

