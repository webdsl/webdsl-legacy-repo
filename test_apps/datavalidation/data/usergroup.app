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
      fieldset("User Group"){
        label("Name") { input(ug.name) }
        label("Owner") { input(ug.owner) }
        /*<div class = "errorblock">
          <div class = "errorline">
            "Owner could not be notified by email"
          </div>
        </div>*/    
        action("Save",save())
      }
    }
    action save() {
      initUserGroup(ug);    
      validate(false, "Owner could not be notified by email");
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
      fieldset("User Group"){
        label("Name") { input(ug.name) }
        label("Member Limit") { input(ug.memberLimit) }
        label("Moderators") { input(ug.moderators) }
        label("Members") { input(ug.members) }
        action("Save",save())
      }
    }
    action save(){
      ug.save();
      return userGroup(ug);
    }
  }
  
  define page userGroup(ug:UserGroup) {
    fieldset("User Group"){
      label("Name") {         wrapProperty{ output(ug.name) } }
      label("Owner") {        wrapProperty{ output(ug.owner) } }
      label("Member Limit") { wrapProperty{ output(ug.memberLimit) } }
      wrapListProperty { label("Moderators") { output(ug.moderators) } }
      wrapListProperty { label("Members") {    output(ug.members) } }
    }    
    navigate(editUserGroupBig(ug)){"edit 4 properties"} 
  }
