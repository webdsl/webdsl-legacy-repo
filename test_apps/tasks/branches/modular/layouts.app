module layouts

define main() {
  includeCSS("webtasks.css")
  <div id="pagewrapper">
     <div id="header">
        mainheader()
     </div>
     <div id="navbar">
       applicationmenu()
     </div>
     <div id="content">
       messages()
       elements()
     </div>
     <div id="footer">
       <p />"powered by " <a href="http://webdsl.org">"WebDSL"</a><p />
     </div>
  </div>
}

define mainheader() {
  par{"Web Tasks"}
}

define applicationmenu() {
  list{
    listitem{ navigate(root()){"Home"}}
    if(!loggedIn()) { listitem{ navigate(register()){"Register"} } }
    signinoffMenu()
  }
}

define usertable() {
  table[id:=usertable] {
    row{
      column{"User"}
      column{"#Tasks"}
      column{""}
    }
    elements()
  }
}
