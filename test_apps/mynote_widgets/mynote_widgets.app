application mynote_widgets

imports init
imports data
//imports style
//imports layout
//imports static

description {
  "manage your own notes!"
}

section pages

define template tabs() {
      <script>
        loadDojo(false, function() {
          dojo.require("dijit.layout.TabContainer");
          dojo.require("dijit.layout.ContentPane");
          dojo.require("dojo.parser");
          dojo.require("dijit.form.Button");
        });
      </script>
      <div id="mainTabContainer" dojoType="dijit.layout.TabContainer"
        class="tundra" style="width:500px;height:100px">
      elements()
      </div>
}

define template tab(title:String, selected: Bool, closable: Bool) {
      <div dojoType="dijit.layout.ContentPane" title=(title) selected=(selected) closable=(closable)>
        elements()
      </div>
}

define page home() {
  tabs {
    tab("Little Red Cap", true, true){
      "Once upon a time there was a dear little girl who was loved by every one who looked at her, but most of all by her grandmother, and there was nothing that she would not have given to the child."
    }
    tab("Hansel and Gretel",false, true) {
      "Hard by a great forest dwelt a poor wood-cutter with his wife and his two children. The boy was called Hansel and the girl Gretel. He had little to bite and to break, and once when great dearth fell on the land, he could no longer procure even daily bread."
    }
    tab("The Three Green Twigs", false, false) {
      "There was once upon a time a hermit who lived in a forest at the foot of a mountain, and passed his time in prayer and good works, and every evening he carried, to the glory of God, two pails of water up the mountain."
    }
  }
} 