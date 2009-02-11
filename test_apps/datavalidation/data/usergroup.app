module user

  entity UserGroup {
    name :: String (id)
    owner -> User // owner should always be moderator and member
    memberLimit :: Int (validate(memberLimit >= members.length, "Remove members before lowering the limit"))
    moderators -> Set<User> (validate(owner in moderators,"Owner must always be a moderator"))
    members -> Set<User> (validate(owner in members,"Owner must always be a member")
                         ,validate(members.length <= memberLimit,"Exceeds member limit"))
  }

  define page createGroup() { //error page inserted needs different template
    var ug := UserGroup{}
    form{
      formgroup("User Group")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(ug.name) }
        label("Owner") { input(ug.owner) }
    block()[class := error, style := "clear:left; float:left; border: 1px solid #FF0000; margin-left: -5px; margin-top: 5px; margin-bottom: 5px; padding: 4px"]{
     
  
        block()[style := "width:100%; clear:left; float:left; color: #FF0000; margin-top: 5px;"]{
          "Owner must always be a moderator"
        }     
      
    }
        
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(userGroup(ug),"Cancel")
        }
      }
    }
    action save() {
      initUserGroup(ug);    
      
      validate(ug.owner in ug.moderators,"Owner must always be a moderator");
      validate(ug.owner in ug.members,"Owner must always be a member");
      ug.save();
      return userGroup(ug);
    }
  }
  
  function initUserGroup(ug: UserGroup){
    ug.memberLimit := 1;
    ug.members.add(ug.owner);
  }


  define page editUserGroupSmall(ug:UserGroup) {
    form{
      formgroup("User Group")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(ug.name) }
        label("Member Limit") { input(ug.memberLimit) }
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
  }

  define page editUserGroup(ug:UserGroup) {
    form{
      formgroup("User Group")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(ug.name) }
        label("Member Limit") { input(ug.memberLimit) }
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
  }
  
  define page editUserGroupBig(ug:UserGroup) {
    form{
      formgroup("User Group")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(ug.name) }
        label("Member Limit") { input(ug.memberLimit) }
        label("Moderators") { input(ug.moderators) }
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
