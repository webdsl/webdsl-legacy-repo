application sortable
    
    
  define inputList1(list:Ref<List<Entity>>, from : List<Entity>){
    var tname := getTemplate().getUniqueId()
    request var errors : List<String> := null
    
    if(errors != null && errors.length > 0){
      errorTemplateInput(errors){
        inputListInternal(list,from,tname)[all attributes]
      }
    }
    else{
      inputListInternal(list,from,tname)[all attributes]
    }
    validate{
      errors := list.getValidationErrors();
      errors := handleValidationErrors(errors);
    }
  }  
    
  function updateListRequest(request:String, list:List<Entity>,selectfrom : List<Entity>): List<Entity>{
    if(request == null){ // nothing submitted
      return list;
    }
    
    var elementids := request.split(",");
    //log(""+elementids);
    //log(""+elementids.length);
    var options := List<Entity>();
    options.addAll(list);
    options.addAll(selectfrom);
    var newlist := List<Entity>();
    for(s:String in elementids){
      var ent := [e | e:Entity in options where e.id.toString() == s];
      if(ent.length > 0){
        var selected := ent[0];
        newlist.add(selected);
      }
    }
    return newlist;
  }  
    
  define inputListInternal(list: Ref<List<Entity>>, selectfrom : List<Entity>, tname:String){
    var hiddenid := "hidden"+tname
    var sortableid := "sortable"+tname
    var selectid := "select"+tname
    var tmp := getRequestParameter(hiddenid);
    var newlist := updateListRequest(tmp,list,selectfrom);
    
    databind {
      //log("1 "+newlist.length);
      list := newlist;
      //log("2 "+newlist.length);
    }
    
    includeCSS("sortable.css")
    includeCSS("css/smoothness/jquery-ui-1.8.5.custom.css")
    includeJS("jquery-1.4.2.min.js")
    includeJS("jquery-ui-1.8.5.custom.min.js")
    
    <script type="text/javascript">
      $(function() {
        $('#~sortableid').sortable();
        $('#~sortableid').disableSelection();
        $('#~sortableid').sortable({
              stop: function(event, ui){ 
                $('#~hiddenid').attr('value', $('#~sortableid').sortable('toArray'));
              }
          });
          //initial values
          $('#~hiddenid').attr('value', $('#~sortableid').sortable('toArray'));
          //optional stuff
          //constrain dragging to list
          //$('#~sortableid').sortable( "option", "containment", 'parent' );
      });
    </script>
    <input type="hidden" name=hiddenid id=hiddenid/>
    <ul id=sortableid class="sortable">
      for(e:Entity in newlist){
        <li id=e.id class="ui-state-default">
          <span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
          output(e.name)
          <span class="ui-icon ui-icon-close" onclick="$(this).parent().remove(); $('#"+hiddenid+"').attr('value', $('#"+sortableid+"').sortable('toArray'));"></span>
        </li>	 
      }
    </ul>
    
    //@TODO should become possible to re-use render of template in client
    var p1 := "<li id=\""
    var p2 := "\" class=\"ui-state-default\"><span class=\"ui-icon ui-icon-arrowthick-2-n-s\"></span>" 
    var p3 := "<span class=\"ui-icon ui-icon-close\" onclick=\"$(this).parent().remove()\"></span></li>"
    
    if(selectfrom.length > 0){
      <select id=selectid>
      for(e:Entity in selectfrom){
        <option value=e.id>
          output(e.name)
        </option>
      }
      </select>
      
      <input type="button" value="add" 
        onclick="$('select#"+selectid+" option:selected').each(function(){ $('#"+sortableid+"').append('"+p1+"'+$(this).attr('value')+'"+p2+"'+$(this).html()+'"+p3+"');}); $('#"+hiddenid+"').attr('value', $('#"+sortableid+"').sortable('toArray')); return false;" />
    }
  } 
  
  //test
  
  entity User{
    name::String
    list -> List<User>
    validate(list.length > 3,"list must be longer than 3")
  }
  
  var u1 := User{name:="u1"}
  var u2 := User{name:="u2"}
  var u3 := User{name:="u3"}
  var u4 := User{name:="u4"}
    
  init{
    u1.list:=[u1,u2,u3,u4,u1,u1,u2];
  }
    
  define page root(){
    form{
      inputList1(u1.list, [u1,u2,u3,u4])
      submit action{} {"save"}
    }
    output(u1.list)
  }

  