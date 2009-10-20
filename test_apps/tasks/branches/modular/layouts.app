module layouts

define main() {
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
  <p>"Web Tasks"</p>
}

define applicationmenu() {
  <ul>
    <li>navigate(root()){"Home"}</li>
    if(!loggedIn()) { <li>navigate(register()){"Register"}</li> }
    signinoffMenu()
  </ul>
}

define usertable() {
  <table id="usertable">
    row{
      column{"User"}
      column{"#Tasks"}
      column{""}
    }
    elements()
  </table>
}
 
define tasktable() {
  <table id="tasktable">
    row{
      column{"Done"}
      column{"Task"}
      column{"Actions"}
    }
    elements()
  </table>
}
 