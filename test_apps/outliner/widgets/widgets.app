module widgets

section templates

define no-span template loadCSS(url: String) {
  <script>loadCSS("~url")</script>
}

define inputtemplate inplaceTextArea(value: Text, name: String) {
  <script>
    function showTA(t,a) {
      a.height = t.clientHeight+40;
      a.style.visibility = 'visible';
      t.style.visibility = 'hidden';
    }
    function hideTA(t,a) {
      a.innerHTML = t.innerHTML;
      t.form.onsubmit();
      a.style.visibility = 'visible';
      t.style.visibility = 'hidden';
    }
  </script>
  <div onclick="showTA(this,this.nextSibling);"> 
    output(value)
  </div> 
  <textarea onblur="hideTA(this, this.previousSibling);" 
    name=name style="visibility:hidden; width: 100%;"
  > 
    output(value)
  </textarea>
}

define inputtemplate inplaceFieldEdit(value: String, name: String) {
  <script>
    function showTA(t,a) {
      a.width = t.clientWidth;
      a.style.visibility = 'visible';
      t.style.visibility = 'hidden';
    }
    function hideTA(t,a) {
      a.innerHTML = t.value;
      t.form.onsubmit();
      a.style.visibility = 'visible';
      t.style.visibility = 'hidden';
    }
  </script>
  <span style="float:left" onclick="showTA(this,this.nextSibling);"> 
    output(value)
  </span> 
  <input onblur="hideTA(this, this.previousSibling);" 
    name=name style="visibility:hidden; width: 100%;"
    value=value
  /> 
}


define template no-span collapsePanel(collapsed: Bool) requires caption(), contents() {
  var initv : String;
  init {
    if (collapsed) { initv := "block"; } else { initv := "hidden"; }
  }
    
  block[style:=" border: 1px dashed #666666; width: 100%", class:="collapsePanel"] { 
    block[style:= "background-color:#CCCCCC; width: 100%; border: 1px solid #666666"] {
      navigate()[onclick := action{ visibility(collapsetarget, toggle); }]{ ">   |" } 
      caption()
    }
    break
    block[style:= "padding-left: 20px; visiblity:"+initv, id := collapsetarget] {
      contents()
    }
  }
}

define template collapseUp() {
  block[style:="width: "+attribute("width","auto"), id:= collapsecontentsup] {
    elements()
  }
  block[class:=hdivider]{
    navigate()[onclick := action { visibility (collapsecontentsup, toggle); }]{ "^^^" }
  }
}

define template collapseLeft() {
  twoColumns[right:= "16px", width:= ""] with {
    left() {
      block[id:= collapsecontentsleft] {
        elements()
      }
    }
    right() {
      block[class:= vdivider]{
        navigate()[onclick := action { visibility (collapsecontentsleft , toggle); }]{ "<\n<\n<" }
      }
    }
  }
}


define template no-span twoColumns() requires left(), right() {
  table[width := attribute("width","100%")] {
    row {
      column[width:= attribute("left","*"),  style:="vertical-align:top"] {
        left()
      }
      column[width:= attribute("right","*"),  style:="vertical-align:top"] {
        right()		
      }
    }
  }
}

define template no-span footerLayout(height: String) requires contents(), footer() {
  //source: http://ryanfait.com/sticky-footer/
  <div class="hcenter" style="min-height: 100%;height: auto !important;height: 100%;margin: 0 auto -"+height+"; width: "+attribute("width","auto")>
    contents()
    <div style="height: "+height></div>
  </div>
  <div class="hcenter" style="height: "+height+"; width: "+attribute("width","auto")>
    footer()
  </div>
}

