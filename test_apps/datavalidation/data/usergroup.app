module user

  entity UserGroup {
    name :: String (id)
    owner -> User // owner should always be moderator and member
    memberLimit :: Int
    moderators -> Set<User> (validate(owner in moderators,"Owner must always be a moderator"))
    members -> Set<User> (validate(owner in members,"Owner must always be a member")
                         ,validate(members.length <= memberLimit,"Exceeds member limit"))
  }

  define page editUserGroup(ug:UserGroup) {
    form{
      formgroup("User Group")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(ug.name) }
        label("Members") { input(ug.members) }
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(userGroup(ug),"Cancel")
        }
      }
    }
    action save(){
      ug.save();
      return userGroup(ug);
    }
    action cancel(){
      return userGroup(ug);
    }
  }
  
  define page editUserGroupOwner(ug:UserGroup) {
    form{
      formgroup("User Group")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(ug.name) }
        label("Member Limit") { input(ug.memberLimit) }
        label("Moderators") { input(ug.moderators) }
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(userGroup(ug),"Cancel")
        }
      }
    }
    action save(){
      ug.save();
      return userGroup(ug);
    }
    action cancel(){
      return userGroup(ug);
    }
  }
  
  define page userGroup(ug:UserGroup) {
    formgroup("User Group")[labelWidth := globalSettings.labelWidth]{
      label("Name") { output(ug.name) }
      label("Owner") { output(ug.owner) }
      label("Member Limit") { output(ug.memberLimit) }
      label("Moderators") { output(ug.moderators) }
      label("Members") { output(ug.members) }
      label("") { 
        navigate(editUserGroup(ug)){"edit"}  navigate(editUserGroupOwner(ug)){"owneredit"}
      } 
    }    
  }
