module person

  entity Person {
    fullName     :: String (name)
    displayName  :: String (id, validate(isUniquePersonId(this.displayName),"Display Name is taken"))
    profile      :: WikiText           
    //portrait     :: Image          
    homepage     :: URL     (optional) // derive from current affiliation
    affiliations -> Set<Affiliation>
    currentPosition -> Affiliation
  }
  
  define page editPerson(p:Person) {
    form{
      formgroup("Person")[labelWidth := globalSettings.labelWidth]{
        label("Full Name") {    input(p.fullName) }
        label("Display Name") { input(p.displayName) }
        label("Profile") {      input(p.profile) }
        //label("Portrait") {   input(p.portrait) }
        label("Homepage") {     input(p.homepage) }
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(person(p),"Cancel")
        }
      }
    }
    action save(){
      p.save();
      return person(p);
    }
  }
  
  define page person(p:Person) {
    outputMessage("Person information updated")
    formgroup("Person")[labelWidth := globalSettings.labelWidth]{
      label("Full Name") {    output(p.fullName) }
      label("Display Name") { output(p.displayName) }
      label("Profile") {      output(p.profile) }
      //label("Portrait") {     output(p.portrait) }
      label("Homepage") {     output(p.homepage) }
      block()[style := globalSettings.formButtonsStyle]{
        navigate(editPerson(p)){"edit"}
      }
    }    
  }
  
          //label("Affiliations") {    input(p.) }
        //label("Current Position") {input(p.fullname) }
        

 