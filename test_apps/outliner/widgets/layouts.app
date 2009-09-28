module widgets

section templates

define no-span template loadCSS(stylesheet: String) {
  <script>loadCSS("~(baseURL())"+"/stylesheets/"+"~stylesheet")</script>
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

define no-span template masterdetail() requires detailview(),masterview()  {
  twoColumns[width:= "100%", left := attribute("left",""), right:= "100%"] with {
    left() {
      collapseLeft() {
        masterview()
      }
    }
    right() {
      detailview()
    }
  }  
}