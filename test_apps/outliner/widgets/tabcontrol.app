module tabcontrol

section tabs

define template tabs() {
  <script>
    dojo.require("dijit.dijit");
    dojo.require("dijit.layout.TabContainer");
    dojo.require("dijit.layout.ContentPane");
  </script>
  <div id="mainTabContainer" dojoType="dijit.layout.TabContainer"
     class="tundra" style="width:100%;height:"+attribute("height","600px")>
     elements()
  </div>
}

define no-span template tab(title:String) {
    <div dojoType="dijit.layout.ContentPane" title=(title)>
      elements()
    </div>
}

define no-span template lazytab(title:String) requires loading(), contents() {
  <div dojoType="dijit.layout.ContentPane" title=(title)>
    <script type="dojo/method" event="onShow">
      console.log("Loading lazy tab");
      findTopDown(this.domNode,'loader').onclick();
    </script>
    form[class:=hidden]{
      button(">", action{replace(tabcontents, contents());})[id:=loader]
    }
    placeholder tabcontents {
      loading()
    }
  </div>       
} 