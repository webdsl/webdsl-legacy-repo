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


define page home() {
  
  
  tabs {
    tab("Cool, Ajax widgets and template arguments!", true, true){
/*      test with {
              hello() {
                showHello()
              }
              world(msg: String) {
                world()
              }		
           }
        */
    }
    tab("Hansel and Gretel",false, true) {
      "Hard by a great forest dwelt a poor wood-cutter with his wife and his two children. The boy was called Hansel and the girl Gretel. He had little to bite and to break, and once when great dearth fell on the land, he could no longer procure even daily bread."
    }
    lazytab("The Three Green Twigs", false, false) {
      "There was once upon a time a hermit who lived in a forest at the foot of a mountain, and passed his time in prayer and good works, and every evening he carried, to the glory of God, two pails of water up the mountain."
    }   
  }
} 


define template tabs() {
      <script>
        loadDojo(false, function() {
          dojo.require("dijit.dijit");
          dojo.require("dijit.layout.TabContainer");
          dojo.require("dijit.layout.ContentPane");
          dojo.require("dojo.parser");
          dojo.require("dijit.form.Button");
          dojo.require("dijit._Widget");
          dojo.require("dijit._base.place");
        });
      </script>
      <div id="mainTabContainer" dojoType="dijit.layout.TabContainer"
        class="tundra" style="width:600px;height:400px">
      elements()
      </div>
}

define no-span template tab(title:String, selected: Bool, closable: Bool) {
      <div dojoType="dijit.layout.ContentPane" title=(title) selected=(selected) closable=(closable)>
        elements()
      </div>
}

define no-span template lazytab(title:String, selected: Bool, closable: Bool) {
  <div dojoType="dijit.layout.ContentPane" title=(title) selected=(selected) closable=(closable)>
    <script>
      function lazyload(tab) {
        elems = tab.domNode.getElementsByTagName('input');
        if (elems[1] && elems[1].id == 'loader') {
          elems[1].onclick();
        }
      }
    </script>
    <script type="dojo/method" event="onShow">
      "lazyload(this);"
    </script>
    placeholder tabcontents {
      form{
        action(">",action{
          replace(tabcontents,template { elements() } );
        })[id:=loader]
        "loading..."
      }
    }
  </div>       
} 

/*
      
      dojo.addOnLoad(function() {
          dojo.require("dijit.layout.ContentPane");
          dojo.require("dijit.dijit");
          dojo.require("dijit._base.place");
          var p = dojo.byId("test");
//          dojo.connect(p,'onclick','pressed');
          dojo.subscribe(p.parentNode.id+"-selectChild", function(child){
            alert("A new child was selected:", child);
          });
      });
      </script>      



          var p = new dijit.layout.ContentPane({
              content:"Optionally set new content now",
              style:"height:125px",
              title:"dyntab"
          }, "mainTabContainer");
          dijit.byId("mainTabContainer").addChild(p);
       });
            
*/
    //  <script>
  //      dojo.connect( this.parentNode.parentNode, "onShow", function() { alert( 'o hai'); });
        //dojo.connect( dijit.byId("tab2"), "onShow", function() { console.log( 'o hai');  ... create grid... })
//      </script>



define template boeie() { "boeie:"  elements() }

/*
define template showHello() {
 "hello"
}

define template world() {
  "world"
}

define template test () requires hello(), world() {
  "the hello template should appear here"
  break "> " hello() " <"
  spacer
  "and world should b e just below"
  break "> " world() " <"
} 
*/

/* original:
define page home() {
      <script>
        loadDojo(true, function() {
          dojo.require("dijit.layout.TabContainer");
          dojo.require("dijit.layout.ContentPane");
          dojo.require("dojo.parser");
          dojo.require("dijit.form.Button");
        });
      </script>
      <div id="mainTabContainer" dojoType="dijit.layout.TabContainer"
        class="tundra" style="width:500px;height:100px">
            <div dojoType="dijit.layout.ContentPane" title="bla" selected="true" closable="true">
        "Once upon a time there was a dear little girl who was loved by every one who looked at her, but most of all by her grandmother, and there was nothing that she would not have given to the child."
      </div>
            <div dojoType="dijit.layout.ContentPane" title="(title)" selected="false" closable="false">
        "Once upon a time there was a dear little girl who was loved by every one who looked at her, but most of all by her grandmother, and there was nothing that she would not have given to the child."
      </div>
      </div>
} 
*/