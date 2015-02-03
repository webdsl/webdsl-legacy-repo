application test

  entity Bar {
    foo :: String (id)
  }

  var glob := Bar { foo := "test" }

  define page root(){
    "root page"
    form{
      input(glob.foo)
      submit("save", action{ return view(glob); })
      submit("ajaxsave", action{ return view(glob); })[ajax]
      submit("ajaxsave2", action{ replace(pl,viewA(glob, pl)); })[ajax]
    }
    
    placeholder pl {}
    
    navigate view(glob) {"view"}
    
  }
  
  define page view(g:Bar){
    "view page"
    output(g.foo)
    form{
    
      input(glob.foo)
      submit("save", action{ return view(glob); })
      submit("ajaxsave", action{ return view(glob); })[ajax]
      submit("ajaxsave2", action{ replace(pl2,viewA(glob, pl2)); })[ajax]
      
    }
    
    placeholder pl2 {}
  }
  
  define ajax viewA(g:Bar, pl: Placeholder){
    "viewA ajax template"
    navigate view(g) { output(g.foo) }
    form{
    
      input(glob.foo)
      submit("save", action{ return view(glob); })
      submit("ajaxsave", action{ return view(glob); })[ajax]
      submit("ajaxsave2", action{ replace(pl,viewA(glob, pl)); })[ajax]
    }
    block[onclick:=action{refresh();}]{
      "clickme"
    }
  }
  
  test encodingstest {
    var d : WebDriver := getFirefoxDriver();
    
    //root first submit button
    d.get(navigate(root()));
    assert(d.getPageSource().contains("root page"), "expected to be on root page");
    
    var elist : List<WebElement> := d.findElements(SelectBy.tagName("input"));
    assert(elist.length == 2, "incorrect number of input elements found");
    
    var entered1 := "+ /\\'?^:\u1234\u4352\u0044\u00F6"; //TODO webdsl should parse these \u as unicode escape
    
    elist[1].sendKeys(entered1);
    d.getSubmits()[0].click();
    
    assert(d.getPageSource().contains("view page"), "expected to be on view page");
    var elist : List<WebElement> := d.findElements(SelectBy.tagName("input"));
    assert(elist.length == 3, "incorrect number of input elements found");
    
    assert(elist[2].getValue() == "test"+entered1, "input not correctly displayed after submit");   
    
    //root second submit button 
    d.get(navigate(root()));
    assert(d.getPageSource().contains("root page"), "expected to be on root page");
    
    var elist : List<WebElement> := d.findElements(SelectBy.tagName("input"));
    assert(elist.length == 2, "incorrect number of input elements found");
    
    var entered2 := "\u0055";
    
    elist[1].sendKeys(entered2);
    d.getSubmits()[1].click();
    
    assert(d.getPageSource().contains("view page"), "expected to be on view page");
    var elist : List<WebElement> := d.findElements(SelectBy.tagName("input"));
    assert(elist.length == 3, "incorrect number of input elements found");
    
    assert(elist[2].getValue() == "test"+entered1+entered2, "input not correctly displayed after submit");   
    
    
    //root third submit button
    d.get(navigate(root()));
    assert(d.getPageSource().contains("root page"), "expected to be on root page");
    
    var elist : List<WebElement> := d.findElements(SelectBy.tagName("input"));
    assert(elist.length == 2, "incorrect number of input elements found");
    
    var entered3 := "+ + \\//";
    
    elist[1].sendKeys(entered3);
    log(elist[1].getValue());
    d.getSubmits()[2].click();
    
    assert(d.getPageSource().contains("viewA ajax template"), "expected to see ajax template viewA");
    var elist : List<WebElement> := d.findElements(SelectBy.tagName("input"));
    assert(elist.length == 6, "incorrect number of input elements found");
    
    assert(elist[3].getValue() == "test"+entered1+entered2+entered3, "input not correctly displayed after submit");   
  }
   