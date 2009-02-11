application datavalidation

  imports data/global-settings
  imports data/init
  imports data/user
  imports data/usergroup
  imports data/person
  imports data/affiliation
  imports data/address
  imports data/organization

section home page
  
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
  }
 
  define outputMessage(s:String){
    if(s.length() > 0){
      block()[class := error, style := "display:inline; border: 1px solid #BB8800; padding: 4px; color:#BB8800;"]{
        output(s)   
      }
      break
      break
    }
  } 
   
  
  define ignore-access-control errorTemplate(errorMessages : List<String>){
    block()[class := error, style := "clear:left; float:left; border: 1px solid #FF0000; margin-left: -5px; margin-top: 5px; margin-bottom: 5px; padding: 4px"]{
      validatedInput
      for(ve: String in errorMessages){
        block()[style := "width:100%; clear:left; float:left; color: #FF0000; margin-top: 5px;"]{
          output(ve)
        }     
      }
    }
  } 
  
  /*
  define ignore-access-control errorTemplate(errorMessages : List<String>){
    block()[class := error, style := "clear:left; float:left; border: 1px solid #FF0000; margin-left: -5px; margin-top: 5px; margin-bottom: 5px; padding: 4px"]{
      validatedInput
      block()[style := "clear:left; float:left; color: #FF0000; margin-top: 5px;"]{
        for(ve: String in errorMessages){
          output(ve)
        } separated-by{", "}          
      }
    }
  }
  */
/*
  define ignore-access-control errorTemplate(errorMessages : List<String>){
    validatedInput()
    block()[class := error]{
      row{
        column[colspan := "3"]{
          for(ve: String in errorMessages){
            output(ve)
          } separated-by{", "}
        }
      }
    }
  }
*/