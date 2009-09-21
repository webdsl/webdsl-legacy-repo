application datavalidation

  imports data/global-settings
  imports data/init
  imports data/user
  imports data/usergroup
  imports data/person
  imports data/affiliation
  imports data/address
  imports data/organization


  imports notes/notes
  imports notes/init
  
section home page
  
  define page root(){
    init{
      return home();
    }
  }
  
  define page home(){


    validationErrors(test)
 
    for(u:User)
    {
      navigate(user(u)){output(u.name)}" "
      navigate(editUserSmall(u)){"editsmall"}" "
      navigate(editUser(u)){"edit"}" "
      navigate(editUserBig(u)){"editbig"}" "
    }
    break
    for(p:Person){
      navigate(person(p)){output(p.name)}" "
      navigate(editPerson(p)){"edit"}" "
    }
    break
    for(ug:UserGroup){
      navigate(userGroup(ug)){output(ug.name)}" "
      navigate(editUserGroupSmall(ug)){"editsmall"}" "
      navigate(editUserGroup(ug)){"edit"}" "
      navigate(editUserGroupOwner(ug)){"owneredit"}" "
      navigate(editUserGroupBig(ug)){"editbig"}" "
    }
    break
    navigate(createGroup()){"create"}" "
    break
    
    
    for(f:Folder)
    {
      navigate(folder(f)){output(f.name)}" "
      navigate(editFolder(f)){"edit"}" "
    }
    
    for(n:Note)
    {
      navigate(note(n)){output(n.name)}" "
      navigate(editNote(n)){"edit"}" "
    }
  }
 
  define templateSuccess(messages : List<String>){
    <div class = "successblock">
      for(message: String in messages){     
        output(message) 
      }  
    </div>
  } 
   
  define ignore-access-control errorTemplateInput(messages : List<String>){
    <div class = "errorblock">
      <div class = "errorinput">
        validatedInput
      </div>
      for(ve: String in messages){
          <div class = "errorline">
            output(ve)
          </div>
      }     
    </div>
  } 
  define ignore-access-control errorTemplateAction(messages : List<String>){
    <div class = "errorblock">
      <div class = "errorinput">
        validatedInput
      </div>
      for(ve: String in messages){
          <div class = "errorline">
            output(ve)
          </div>
      }     
    </div>
  } 
  define ignore-access-control errorTemplateForm(messages : List<String>){
    <div class = "errorblock">
      <div class = "errorinput">
        validatedInput
      </div>
      for(ve: String in messages){
          <div class = "errorline">
            output(ve)
          </div>
      }     
    </div>
  }