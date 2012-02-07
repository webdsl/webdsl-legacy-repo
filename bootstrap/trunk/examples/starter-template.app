module examples/starter-template

section javascript

  define bootstrapInitStarter() {
  	includeCSS("bootstrap/css/bootstrap.css")

    includeCSS("bootstrap/css/bootstrap-responsive.css")
    
    //<!-- Placed at the end of the document so the pages load faster -->
    includeJS("bootstrap/js/jquery.js")
    includeJS("bootstrap/js/bootstrap-transition.js")
    includeJS("bootstrap/js/bootstrap-alert.js")
    includeJS("bootstrap/js/bootstrap-modal.js")
    includeJS("bootstrap/js/bootstrap-dropdown.js")
    includeJS("bootstrap/js/bootstrap-scrollspy.js")
    includeJS("bootstrap/js/bootstrap-tab.js")
    includeJS("bootstrap/js/bootstrap-tooltip.js")
    includeJS("bootstrap/js/bootstrap-popover.js")
    includeJS("bootstrap/js/bootstrap-button.js")
    includeJS("bootstrap/js/bootstrap-collapse.js")
    includeJS("bootstrap/js/bootstrap-carousel.js")
    includeJS("bootstrap/js/bootstrap-typeahead.js")
    
    // <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png" />

  }

  define repairIE() {
	  // <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    //  <!--[if lt IE 9]>
    //      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    //  <![endif]-->
  }

section the page

  define page starter() {
    bootstrapInitStarter
    includeCSS("examples/starter.css")
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">"Project name"</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="active"><a href="#">"Home"</a></li>
              <li><a href="#about">"About"</a></li>
              <li><a href="#contact">"Contact"</a></li>
            </ul>
          </div>//<!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
      <h1>"Bootstrap starter template"</h1>
      <p>"Use this document as a way to quick start any new project."
        <br></br> "All you get is this message and a barebones HTML document."
      </p>
    </div>// <!-- /container -->
  }
  