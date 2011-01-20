application dialog

  define dialogbutton(linktext:String, dialogtitle :String){ 
    dialogbutton(linktext, dialogtitle, "")
  }
  
  define dialogbutton(linktext:String, dialogtitle :String, options:String){
    includeJS("https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js")
    includeJS("https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.8/jquery-ui.min.js")
    includeCSS("http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.8/themes/base/jquery-ui.css")
    
    var tid := getUniqueTemplateId()
    <div id=""+tid class="dialog" title=dialogtitle style="display:none;">
      elements()
    </div>
    <input type="button" onclick="javascript:$('#"+tid+"').dialog("+options+");" value=linktext />
  }
  
  function closeAllDialogs() {
    runscript("$('.dialog').dialog('close');");
  }


  entity Test{
    i::Int
  }
  var globaltest := Test{}

  define page root(){
    dialogbutton("edit value", "enter new value"){ placeholder "ph1"{} initfoo("ph1")  }
    dialogbutton("edit value", "enter new value","{width:500}"){ placeholder "ph2"{} initfoo("ph2") }
  }
  
  define ajax foo(ph:String){
    form{
      input(globaltest.i)
      submit action{ replace(""+ph,foo(ph));closeAllDialogs(); } {"save"}
    }
  }
  
  define initfoo(ph:String){  
    //need to trigger ajax load initially, @TODO better webdsl support for this
    var tid := getUniqueTemplateId()
    form{ submitlink action{replace(""+ph,foo(ph));}[id=""+tid, style="display:none;"]{} }
    <script>
      $("#~tid").click();
    </script>
  }
  

  