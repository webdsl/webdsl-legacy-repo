application org.webdsl.test3

section domain .

  Person {
    fullname :: String ( name )
  }

section setup .

  define main () {
    sidebar(){
    }
    body(){
    }
  }

  define page home () {
  }

section generated edit pages .

  define page editPerson (person : Person) {
    div("main"){
      div("sidebar"){
      }
      div("body"){
        section(){
          header(){
            "Edit "
            "Person"
            " "
            text(person.name){
            }
          }
          form(){
            table(){
              div("editRowsPerson"){
                row(){
                  "Fullname"
                  inputString(person.fullname){
                  }
                }
              }
            }
            action("Save", save()){
            }
            action("Cancel", cancel()){
            }
          }
        }
        action cancel ( )
        {
          return viewPerson(person);
        }
        action save ( )
        {
          person.save();
          return viewPerson(person);
        }
      }
    }
  }

  define page createPerson () {
    var person : Person := Person{} ;
    div("main"){
      div("sidebar"){
      }
      div("body"){
        section(){
          header(){
            "Create new "
            "Person"
          }
          form(){
            table(){
              div("editRowsPerson"){
                row(){
                  "Fullname"
                  inputString(person.fullname){
                  }
                }
              }
            }
            action("Save", save()){
            }
            action("Cancel", cancel()){
            }
          }
        }
        action cancel ( )
        {
          return home();
        }
        action save ( )
        {
          person.save();
          return viewPerson(person);
        }
      }
    }
  }

  define editRowsPerson (person : Person) {
    row(){
      "Fullname"
      inputString(person.fullname){
      }
    }
  }

  define page viewPerson (person : Person) {
    div("main"){
      div("sidebar"){
      }
      div("body"){
        section(){
          header(){
            text(person.name){
            }
          }
          table(){
            div("viewRowsPerson"){
              row(){
                "Fullname"
                text(person.fullname){
                }
              }
            }
          }
        }
      }
    }
  }

  define viewRowsPerson (person : Person) {
    row(){
      "Fullname"
      text(person.fullname){
      }
    }
  }