module template

define body() { "FAIL!" }

define main() {
  <div id="pagewrapper">
    <div id="header">
      <img src="http://webdsl.org/webdslorg/images/logosmall.png" valign="middle"/>
      <span>"Engine"</span>
    </div>
    <div id="navbar">
      applicationmenu()
    </div>
    <div id="content">
      elements()
    </div>
    <div id="footer">
      <p>"powered by " <a href="http://webdsl.org">"WebDSL"</a></p>
    </div>
  </div>
}

define applicationmenu() {
  <ul>
    <li>navigate(home()){"Home"}</li>
    signinoffMenu()
  </ul>
}

