application smalltest

//imports accesscontrol
//imports init
imports templates
imports data

section fdsfsd
  principal is User with credentials name
  predicate bla(){true}

access control rules
  rule page home(*)
  {true rule action testaction(d:Document){true}}
access control rules
  rule page *()
  {
    true
   // rule action testaction(d34:Document)
    //{
    //  d34.name = "dsfdfS"
    
    //}
  }
  rule action testactio*(*)
  {
    true
  }
  //rule action testaction(d123123132321:Document)
//  {
  //  d123123132321.name = "sdfsdfsfdsdfDS"
  //}
  rule page testpage(d2:Document,m2:Mission)
  {
    d2.name = m2.name
  }
section MAC pages

  define page testpage(d1:Document,m1:Mission)
  {
    main()
    define body()
    {
      output(d1.name)
      output(m1.name)
    }
  }

  define page home(){
    var adoc : Document := Document{};
    var amis : Mission := Mission{}; 
    action testaction(d:Document) {
      return home();
    }
  
    main()
    define body(){
    
      if(bla()){
        "dfds"
      }
      navigate(home()){"dfgdfg"}
      navigate(home()){"dfgdfg"}
    
    

      for(d:Document) {
        action("testaction",testaction(d))
      }
      
      
      navigate(testpage(adoc,amis)){"dfgdfg"}
    }
   }