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
///ccess control rules
 // rule page *()
 // {
   // true
   // rule action testaction(d34:Document)
    //{
    //  d34.name = "dsfdfS"
    
    //}
 // }
  //rule action testactio*(*)
  //{
  //  true
  //}
  //rule action testaction(d123123132321:Document)
//  {
  //  d123123132321.name = "sdfsdfsfdsdfDS"
  //}
  rule page testpage(d2:Document,m2:Mission)
  {
    d2.name = m2.name
    rule action testaction(doc:Document,docfdsfd23:Document)
    {
      doc.name=d2.name && docfdsfd23.name=doc.name
    }
  }
  //rule template *(*){true}
  rule template testtemplate(doc12:Document)
  {
    doc12.name=doc12.name
  }
  rule template testtemplate(doc1222:Document)
  {
    doc1222.name=doc1222.name
    rule action testac(m1:Mission)
    {
      doc1222.name = m1.name
    }
  }
  rule function testfunc(d:Document)
  {
    d.name=d.name
  }
  
section MAC pages

globals{
function testfunc(d2:Document):Document
{
  d2.name;
  return d2;
  
}

}

  define page testpage(d1:Document,m1:Mission)
  {
    action testaction(d:Document,d23:Document) {
      testfunc(d);
      return home();
    }
    main()
    define body()
    {
    testtemplate(d1)
    
    navigate(testpage(d1,m1)){"dfgdfg"}
      output(d1.name)
      output(m1.name)
    }
  }
  define testtemplate(docu:Document)
  {
    output(docu.name)
    var m2:Mission := Mission{};
    action("action",testac(m2))
    action testac(m1:Mission)
    {
      m1.name;
    
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