module inplaceedit

section templates

/** can be used either inside a form (providing automatic binding of 'value', 
  or without a form, in which case the calllback 'onblur(value: String)' should be provided
*/
define inputtemplate inplaceFieldEdit(value: String, name: String) {
  <script>
    function showTA(t,a) {
      a.width = t.clientWidth;
      t.className = 'hidden';
      a.className = 'visible';
    }
    function hideTA(t,a) {
      a.innerHTML = t.value;
      if (t.form != null) {
        t.form.onsubmit();
      }
      a.className = 'visible';
      t.className = 'hidden';
    }
  </script>
  <span onclick="showTA(this,this.nextSibling);" class="visible"> 
    output(value)
  </span> 
  <input onblur="hideTA(this, this.previousSibling);"+event(onblur,[value:="this.value"])  
    name=name class="hidden"
    value=value
    style="width:80%"
  /> 
}

define inputtemplate inplaceTextArea(value: Text, name: String) {
  <script>
    function showTA(t,a) {
      a.height = t.clientHeight+40;
      t.className = 'hidden';
      a.className = 'visible';
    }
    function hideTA(t,a) {
      a.innerHTML = t.innerHTML;
      if (t.form != null) {
        t.form.onsubmit();
      }
      a.className = 'visible';
      t.className = 'hidden';
    }
  </script>
  <div onclick="showTA(this,this.nextSibling);" class="visible"> 
    outputText(value)
  </div> 
  <textarea onblur="hideTA(this, this.previousSibling);"+event(onblur,[value:="this.value"]) 
    name=name class="hidden"
  > 
    output(value)
  </textarea>
}
