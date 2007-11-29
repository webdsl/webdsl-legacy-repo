application email

description {
  This is an automatically generated description
}

imports templates
imports emailtemplates

section data

  entity EmailEntity {
    to      :: Email (notempty, email)
    from    :: Email (notempty, email)
    subject :: String (notempty)
    body    :: Text
  }
  
section pages

define page home() {
  var e : EmailEntity := EmailEntity{};
  main()
  define body() {
    div("body"){
      section(){
        header(){
          "Send Email "
        }
        form(){
          table(){
            div("editRowsUploadItem"){
              row(){
                "To"
                input(e.to) {}
              }
              row(){
                "From"
                input(e.from) {}
              }
              row(){
                "Subject"
                input(e.subject) {}
              }
              row(){
                "Message"
                input(e.body) {}
              }
            }
          }

          action("Send", send()) {}
        }
      }

      action send ( )
      {
        email(exampleEmail(e));

        return home();
      }
    }
  }
}

