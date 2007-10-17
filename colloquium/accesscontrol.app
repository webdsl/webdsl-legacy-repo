module accesscontrol

section access control

  // everyone can view colloquium pages

  // speaker can edit his presentation title and abstract, but not time and room

  // moderator can edit everything

  // audience gets email announcements

  // research group can propose new presentations

  extend entity Person {
    roles    -> Set<Role>
    username :: String
    password :: Secret
  }

  entity Role {
    role :: String (name)
  }

  globals {
    var speaker    : Role := Role { role := "speaker" };
    var moderator  : Role := Role { role := "moderator" };
    var researcher : Role := Role { role := "researcher" };
    var audience   : Role := Role { role := "audience" };
  }

  //access control rules {

    //principal is Person with credentials username, password
 
    //rules page editPresentation(p : Presentation) {
    //  rules action saveSpeaker() {
    //    securityContext.principal = p.speaker
    //  }
    //  rules action savePresentation() {
    //    securityContext.principal = p.moderator
    //  }
    //}

  //}
