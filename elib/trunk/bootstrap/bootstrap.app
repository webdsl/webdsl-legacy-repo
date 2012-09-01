module stdlib/bootstrap/bootstrap

imports stdlib/bootstrap/icons

section positioning

  define pullRight() { 
  	span[class="pull-right", all attributes]{ elements }
  }
  define pullLeft() {  
  	span[class="pull-left", all attributes]{ elements }
  }

section grid system

  define gridContainer() {
  	div[class="container", all attributes]{ elements }
  }
  define gridContainerFluid(){
  	div[class="container", all attributes]{ elements }  	
  } 
  define gridRowFluid(){
  	div[class="row-fluid", all attributes]{ elements }
  }
  define gridRow(){
  	div[class="row", all attributes]{ elements }
  }
  define gridSpan(span: Int){
  	  div[class="span" + span, all attributes]{ elements }
  }
  define gridSpan(span: Int, offset: Int){
  	div[class="span" + span + " offset" + offset, all attributes]{ elements }
  }
  
section navigation bar

  define navbar() {
  	div[class="navbar navbar-fixed-top"]{
      div[class="navbar-inner"]{
        div[class="container"]{
        	elements
        }
      }
    }
  }
  define navbarStatic() {
  	div[class="navbar"]{
      div[class="navbar-inner"]{
        div[class="container"]{
        	elements
        }
      }
    }
  }
  define navCollapse() {
  	div[class="nav-collapse"]{
  		elements
  	}
  }  
  define navItems() {
  	list[class="nav"]{
  		elements
  	}
  }
  
section sections

  define pageHeader() {
  	div[class="page-header"]{
  		header1{ elements }
  	}
  }
  define small() {
  	<small>elements</small>
  }
 
section tables

  define tableBordered(){
  	table[class="table table-bordered table-striped table-condensed",  all attributes]{
  		elements
  	}
  }
  define theader() {
  	<thead all attributes>elements</thead>
  }
  define th(){
  	<th all attributes>elements</th>
  }

section forms

  define span() { <span all attributes>elements</span> }

  define inlForm() { 
  	span[class="inlineForm"]{ 
  		form{
  		  elements
  	  } 
  	}
  }

  define formEntry(l: String){    
    <label>output(l)</label> elements
  }
  
  define formEntry(l: String, help: String){    
    <label>output(l)</label>
    elements
    <span class="help-inline">output(help)</span>
  }
  
  define formActions(){
  	div[class="form-actions"]{ elements }
  }
  
section horizontal forms

  define horizontalForm(){
  	form[class="form-horizontal"] {
  		elements
  	}
  }
  define horizontalForm(title: String){
  	horizontalForm{
  		fieldset(title){
  			elements
  		}
  	}
  }  
  define controlGroup(s: String){
    div[class="control-group"]{
    	label(s)[class="control-label"]{ 
    		div[class="controls"]{
    		  elements
    		}
    	}
    }
  }

section breadcrumbs

  define breadcrumbs(){ 
  	<ul class="breadcrumb"> elements </ul>
  }
  define breadcrumb() {
  	<li> <span class="divider">"/"</span> elements </li>
  }
  define breadcrumbFirst() {
  	<li> elements </li>
  }
  
section pagers

  define pager() {
  	<ul class="pager">
  	  elements
  	</ul>
  }
  define pagerPrevious(nav: String){
  	<li>navigate url(nav) { "Previous" }</li>
  }  
  define pagerNext(nav: String){
  	<li>navigate url(nav) { "Next" }</li>
  }
  
section buttons

  define buttonToolbar() {
  	div[class="btn-toolbar"]{
  		elements
  	}
  }
  define buttonGroup(){
  	div[class="btn-group", all attributes]{
  		elements
  	}
  }
  define buttonNavigate(nav: String) {
  	//navigate url(nav) [class="btn"]{ elements }
  	<a href=nav class="btn">elements</a>
  }
  define button() {
  	div[class="btn", all attributes]{ elements }
  }
  
section dropdowns

  define dropdownMenu(){
  	list[class="dropdown-menu", all attributes]{
  		elements
  	}
  }
  define subMenu() {
  	dropdownMenuDivider
  	elements
  }
  define dropdownMenuItem() {
  	listitem[all attributes]{ elements }
  }
  define dropdownMenuDivider() {
  	listitem[class="divider"]{  }
  }  
  define dropdownToggle(cls: String){
  	<a class="btn dropdown-toggle "+cls href="#" data-toggle="dropdown" style="height:18px;">
  	  <span class="caret"></span>
  	</a>
  }
  define dropdownToggle(){ 
  	dropdownToggle("")
  }
  
  define dropdownInNavbar(title: String) {
  	<li class="dropdown">
  	  <a class="dropdown-toggle" href="#" data-toggle="dropdown">
  	    output(title) " " <span class="caret"></span>
  	  </a>
  	  elements
  	</li>
  }
  define dropdownButton(title: String) {
  	<a class="btn dropdown-toggle" href="#" data-toggle="dropdown">
	  	    output(title) " " <span class="caret"></span>
	  </a>
	  dropdownMenu{ elements }
  }
  
section miscellaneous

  define well(){ 
  	div[class="well", all attributes]{ elements }
  }
  
  define blockquote() {
  	<blockquote> elements </blockquote>
  }
  
section tabs

  define tabsBS() {  
  	<ul id="tab" class="nav nav-tabs">
  		elements
  	</ul>
  }
  
  define tabActive(label: String, id: String) { 
  	tab(label, id, true)
  }  
  define tabActive(label: String) { 
  	tab(label, label, true)
  }  
  define tab(label: String, id: String) { 
  	tab(label, id, false)
  }
  define tab(label: String, id: String, active: Bool) { 
  	<li class=activeClass(active)><a href="#"+id data-toggle="tab">output(label)</a></li>
  	<script>
      $(function () {
        $('#~id').tab('show')
      })
    </script>
  }
  function activeClass(active: Bool): String {
    if(active) { return "active"; } else { return ""; }
  }
  
  define tabBS(label: String) { 
  	tab(label, label){ elements } 
  }
  
  define tabContent(){
  	div[class="tab-content"]{ 
  		elements
  	}
  }
  
  define tabPaneActive(id: String){
  	tabPane(id, true) { elements }
  }
  define tabPane(id: String){
  	tabPane(id, false) { elements }
  }
  define tabPane(id: String, active: Bool){
  	div[class="tab-pane " + activeClass(active), id=id]{
  		elements 
  	}
  }
  
section alerts

  define alert() {
  	div[class="alert"]{
  		<a class="close" data-dismiss="alert">"x"</a>
  		elements
  	}
  }
  
  define alertSuccess() {
  	div[class="alert alert-success"]{
  		<a class="close" data-dismiss="alert">"x"</a>
  		elements
  	}
  }
  
  define alertInfo() {
  	div[class="alert alert-info"]{
  		<a class="close" data-dismiss="alert">"x"</a>
  		elements
  	}
  }  
  
  define alertError() {
  	div[class="alert alert-error"]{
  		<a class="close" data-dismiss="alert">"x"</a>
  		elements
  	}
  }

/*
  define tabExperiment() {
  	<ul id="tab" class="nav nav-tabs">
    <li><a href="#home" data-toggle="tab">"Home"</a></li>
    <li><a href="#profile" data-toggle="tab">"Profile"</a></li>
    <li><a href="#messages" data-toggle="tab">"Messages"</a></li>
    <li><a href="#settings" data-toggle="tab">"Settings"</a></li>
    </ul>
     
    <div class="tab-content">
      <div class="tab-pane active" id="home">"home content"</div>
      <div class="tab-pane" id="profile">"profile content"</div>
      <div class="tab-pane" id="messages">"messages content"</div>
      <div class="tab-pane" id="settings">"settings content"</div>
    </div>
     
    <script>
      $(function () {
        $('.tabs a:last').tab('show')
        $('#home').tab('show')
        $('#profile').tab('show')     
        $('#messages').tab('show')      
        $('#settings').tab('show')
      })
    </script>
  }
*/

/*   
  define tabDefault(label: String) { 
  	tab(label, true){ elements }
  }
    
  define tab(label: String, checked: Bool) { 
  	var tname := getTemplate().getUniqueId()
  	div[class="tab"]{
  		if(checked) {
	  	  <input type="radio" id=tname name="tab-group-1" checked="true"></input>
	  	} else {
	  	  <input type="radio" id=tname name="tab-group-1"></input>	  		
	  	}
	  	<label for=tname>output(label)</label>
	  	div[class="content"]{
	  		elements
	  	}
  	}
  }
*/



  
  
  


