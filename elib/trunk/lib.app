module stdlib/lib

imports stdlib/math
imports stdlib/pageindex
imports stdlib/string
imports stdlib/accesscontrol
imports stdlib/datetime
imports stdlib/markup 
imports stdlib/editable
imports stdlib/coordinates
imports stdlib/modal-dialog
imports stdlib/rss
imports stdlib/wikitext
imports stdlib/counter
imports stdlib/ace
imports stdlib/tabs

imports stdlib/request

imports stdlib/bootstrap/bootstrap

section ajax lib

  define ajax ignore-access-control empty(){}
  