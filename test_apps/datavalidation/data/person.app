module person

  entity Person {
    fullName     :: String (name)
    displayName  :: String (id, validate(isUniquePerson(this),"Display Name is taken"))
    profile      :: WikiText           
    //portrait     :: Image          
    homepage     :: URL     (optional) // derive from current affiliation
    affiliations -> Set<Affiliation>
    currentPosition -> Affiliation
  }
  
  define page editPerson(p:Person) {
    form{
      fieldset("Person"){
        label("Full Name") {    input(p.fullName) }
        label("Display Name") { input(p.displayName) }
        label("Profile") {      input(p.profile) }
        //label("Portrait") {   input(p.portrait) }
        label("Homepage") {     input(p.homepage) }
        action("Save",save())
      }
    }
    action save(){
      p.save();
      message("Person information updated");
      return person(p);
    }
  }
  
  define page person(p:Person) {
    fieldset("Person"){
      label("Full Name") {    wrapProperty{ output(p.fullName) } }
      label("Display Name") { wrapProperty{ output(p.displayName) } }
      label("Profile") {      wrapProperty{ output(p.profile) } }
      label("Homepage") {     wrapProperty{ output(p.homepage) } }
    }    
    navigate(editPerson(p)){"edit"}
  }
  
          //label("Affiliations") {    input(p.) }
        //label("Current Position") {input(p.fullname) }
        

 