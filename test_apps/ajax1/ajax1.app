application ajax1

imports init
imports data

description {
  
}

section the pages

  define page home(){
    block("top"){ top() }
    block("body"){
      block("left_innerbody")[id := "sidebar"]{ sidebar() }
      block("main_innerbody"){ 
        for(document: Document) {
           block [onclick:= @ replace details << @ \output(document.title) ] {
               output(document.title) "click to view text"
           } 
        }
        block[id:="details"] {
            "nothing selected"
        }
      }
    }
    //             output(document.title) "click to view details"
    
    action detailclick(document: Document) {
      replace details << @ \output(document.text) ; // ajaxDocument(document);
    }    
  }

  define editableRow(document: Document)
  {
    block [onclick:= editdetailclick(document)] {
      output(document.title) "[click to edit title]"
    }
    block [onclick:= doubleme(document)] {
      "[double me]"
    } 
    action editdetailclick(document: Document)	{
      replace this << editRow(document);
    }
    action doubleme(document: Document) {
      append this << editableRow(document);
    }
    spacer
  }
  
  define editRow(document: Document)
  {
    block {
      form {
        "new title: "
        input(document.title)
        action("save", save())
        actionLink("cancel", cancel())
      }
      action cancel(){
          replace this <<  editableRow(document);
      }
      action save(){
          document.save();
          replace this <<  editableRow(document);
          replace sidebar << sidebar();
          return; //should make no difference here, but fail neither
      }
    }
    spacer
  }

  define page home2() {
    block("top"){ top() }
    block("body"){
      block("left_innerbody")[id := "sidebar"]{ sidebar() }
      block("main_innerbody"){
        for(document: Document) {
          editableRow(document)
        }
      }       
    }
  }
  

  define page editDocument(document:Document){
   derive editPage from document
  }

  define ajaxDocument(document:Document){
    block[onclick := thisreplace(document)] { 
      "[Click me to see text]" 
      spacer       
      output(document.author.name)
    }
    action thisreplace(document : Document) {
      replace this << ajaxDocumentWithText(document);
    }    
  }
  
  define ajaxDocumentWithText(document:Document){
      block{ 
      output(document.text) 
      spacer       
      output(document.author.name)
    }

  }
  
  define page document(document: Document){
    block("top"){ top() }
    block("body"){
      block("left_innerbody"){ sidebar() }
      block("main_innerbody"){ 
        section
        {
          header{
              output(document.title)
          }
          table
          {
            row{ "Author:" 
               output(document.author.name)
            }
            row{
              block[id:="myfirstdynamicrow"]{
                block[onclick := myfirstonclick(document)]{ 
                  "[edit this using the cool new ajax feature]"
                }  
              }
            }
          }     
        }        
      }
    }
    action myfirstonclick(document: Document)
    {
      replace myfirstdynamicrow << ajaxDocument(document);
    }
  }//page doc
  
  define page createDocument(){
    var document: Document := Document {}
    derive createPage from document
  }

 
  define main() 
  {
    block("top"){ top() }
    block("body"){
      block("left_innerbody"){ sidebar() }
      block("main_innerbody"){ body() }
    }
    block("footer"){ footer() }
  }
  
   
  define footer(){ output("Cool, ajax") }
  
  define top(){
    block("header"){}
    block("menubar"){ menubar{ applicationmenu() }}
  }
  
  define sidebar(){
    list{
      listitem{ navigate(home()){ output("home") }}
      listitem{ navigate(home2()){ output("even more ajax home") }}
      spacer
      "documents: "
      for (document: Document) {
        navigate(document(document)) { output (document.title) }
      }
    }
  }
  
  define applicationmenu()
  {
    menu{
      menuheader[onclick:=doubleSide()]{ "++"}
      menuheader[onclick:=showSide()]{ "+"}
      menuheader[onclick:=hideSide()]{ "-"} 
      menuheader{ "View Document" }
      for(d:Document){
        menuitem{ navigate(document(d)){ output(d.title) }}
      }
    }
    menu{
      menuheader{ "Edit Document" }
      for(d:Document){
        menuitem[onclick := gotoEditPage(d)]{ output(d.title) }
      }
    }    
    menu{ menuheader{ navigate(createDocument()){ output("New document") }}}
    menu{ menuheader[onclick := restyle()] { "restyle" }}
    action showSide() { visibility sidebar << "show"; }
    action hideSide() { visibility sidebar << "hide"; }
    action gotoEditPage(d: Document) { relocate to << editDocument(d); }
    action doubleSide(){ append sidebar << sidebar(); } 
    action restyle() { restyle sidebar << "footer"; }  
  }


/*
style mainStyle

  template top() {
    width := 100%;
    background-color := #123456;
    padding-top := 1em;
    padding-bottom := 1em;
  }
  */
  
  

