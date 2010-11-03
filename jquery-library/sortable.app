module sortable
    
  define ignore-access-control validate sorter(list: Ref<List<Entity>>){
    var tname := getUniqueTemplateId()
    var hiddenid := "hidden"+tname
    var tmp := getRequestParameter(hiddenid);
    
    databind {
      var elementids := tmp.split(",");
      
      for(e:Entity in list){
        var index := 0;
        for(s:String in elementids){
          if(e.id.toString() == s){
            log("index = "+index);
            list.set(index,e);
          }
          index := index + 1;
        }
      }
    }
    
    includeCSS("css/smoothness/jquery-ui-1.8.5.custom.css")
    includeJS("jquery-1.4.2.min.js")
    includeJS("jquery-ui-1.8.5.custom.min.js")
    
    <script type="text/javascript">
      $(function() {
        $('#sortable').sortable();
        $('#sortable').disableSelection();
        $('#sortable').sortable({
              stop: function(event, ui){ 
                $('#~hiddenid').attr('value', $('#sortable').sortable('toArray'));	
              }
          });
          //initial values
          $('#~hiddenid').attr('value', $('#sortable').sortable('toArray'));
          //optional stuff
          //constrain dragging to list
          $('#sortable').sortable( "option", "containment", 'parent' );
      });
    </script>
    <input type="hidden" name=hiddenid id=hiddenid/>
    <ul id="sortable">
      for(e:Entity in list){
      <li id=e.id class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>output(e.name)</li>	  	
      }
    </ul>
  }
  