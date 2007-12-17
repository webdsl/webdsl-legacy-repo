application ACvisibilityinference

imports accesscontrol
imports init
imports templates
imports data

description {
  Test visibility of menuheader, menuitem, form, action, and actionlink
   based on ac rules of pages and actions.
}

section pages
 
  session thedoc {
    d :: Document
    doclist -> List<Document>
    bools -> Set<Bool>
  }



  define page go()
  {
    main()
    define body()
    {
      "go"
    }
  }
  define page nogo()
  {
    main()
    define body()
    {
      "no go: should not be able to get here"
    }
  }
  define page home()
  {
    main()
    define body()
    {
      navigate(go()){output("go")}
      navigate(nogo()){output("no go")}    
    
    
      init{
        thedoc.d := d0 ; 
        thedoc.doclist.add(d0); 
        thedoc.doclist.add(d1); 
        thedoc.bools.add(true);
        thedoc.bools.add(false);
        thedoc.bools.add(true);
      } 
    
      list{listitem{navigate(document(thedoc.d)){output(thedoc.d.title)}}}
    
      list
      {
        for(d:Document)
        {
          listitem{"View: " navigate(document(d)){output(d.title)}}
        }
      }
      
      //var docs : List<Document> := select d from Document as d where d.id = 1 order by id;
      action save(d:Document) { d.save(); }
      
      form
      {
        table{ editRowsDocument(d0) }
        action("Save", save(d0))
      }
      form
      {
        table{ editRowsDocument(d1) }
        action("Save", save(d1))
      }
      form
      {
        table{ editRowsDocument(d2) }
        action("Save", save(d2))
      }
      form
      {
        table{ editRowsDocument(d3) }

        action("Save", save(d3))
        
      }
    }
  }
      
  define page formwithtemplates()
  {
    main()
    define body()
    {    
      
      //with templates
      form
      {
        table{ editRowsDocument(d0) }
        saveaction(d0)
      }
    }
  }
  
    
  define saveaction(d:Document){
    action savedoc(d:Document)
    {
      d.save();
    }
    action("Save", savedoc(d) )
  }
  
  define page formwithfor()
  {
    action save(d:Document) { d.save(); }
    main()
    define body()
    {   
         
      //for loops  
      form
      {
        for(d:Document in thedoc.doclist)
        {
          table{ editRowsDocument(d) }
          action("Save", save(d))
        }
      }
      
      //to compare "forexp" code with
      if(true in [x for (x:Bool in thedoc.bools)])
      {
        "fdfsfS"
      }
    }
  }
  
  define page formwithforall()
  {
    action save(d:Document) { d.save(); }
    main()
    define body()
    {     
      
      //  forall loops
      form
      {
        for(d:Document)
        {
          table{ editRowsDocument(d) }
          action("Save", save(d))
        }
      }  
    }
  }  
  
  globals
  { 
    function aSaveFunction(doccc:Document):Int
    {
      dummyfunction(doccc);
      doccc.save();
      return 0;
    }
    
    function dummyfunction(doc:Document):Int
    {
      if(false){aSaveFunction(doc);}
      return 0;
    }
  }
  define page formwithfunctioncall()
  {
    action saveAction(docc:Document) { aSaveFunction(docc); }
    main()
    define body()
    {       
      //forall with function call in action
      form
      {
        table{ editRowsDocument(d0) }
        action("Save", saveAction(d0))
        
        table{ editRowsDocument(d1) }
        actionLink("Save", saveAction(d1))
      }    
      
    }
  }

