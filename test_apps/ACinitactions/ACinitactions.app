application ACinitactions

description {
  Test AC on init actions
}

section testing
 
  entity Document
  {
    title :: String
  }
  
  globals
  {
    var doc0: Document := Document{title := "doc 0"};
    var d1: Document := Document{title := "doc 1"};
    var d2: Document := Document{title := "doc 2"};
  }
 
  define page home()
  {
    init
    {
      doc0=d1;
      doc0.title := "rgrgdgdr";
    }
    init
    {
      d1.title := "rgrgdgdr";
    }
    init
    {
      d2.title := "rgrgdgdr";
    }
    action df()
    {
      doc0=d1;
      doc0.title := "dfgdfgdgf";
    }
  }
  
  define page denyaccess()
  {
    init
    {
      goto errorpage();
    }
  }
  
  define page errorpage()
  {
    "Shouldn't be able to get here"
  }
  
  access control rules
  {
    rules pointcut openpages()
    {
      true
    }
    
    pointcut openpages()
    {
      page home(),
      page errorpage()
    }
   
    rules page denyaccess()
    {
      false
    }
  }
  
  
  
  define page editDoc(d:Document)
  {
    form
    {
      table{editRowsDocument(d)}
      action("save",save(d))
    }
    action save(d:Document)
    {
      d.save();
    }
    init
    {
      d.title := null;
    }
  }
  
  access control rules
  {
    rules page editDoc(doc:Document)
    {
      doc.title != null
    }
  }
  
  define modcheck()
  {
    init
    {
      d2.title := "rgrgdgdr";
    }
  }
  
  define page modules()
  {
    modcheck()
    init
    {
      d1.title := "rgrgdgdr";
    }
  }
  
  access control rules
  {
    rules page modules()
    {
      true
    }
  }