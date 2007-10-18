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

  access control rules {

    principal is Person with credentials username, password

    rules page home() {
      true
    }

    rules page view*(*) {
      true
    }

    rules page future(*) {
      true
    }

    rules page past(*) {
      true
    }
 
    rules page editPresentation(p : Presentation) {
        securityContext.principal = p.colloquium.moderator
        || securityContext.principal = p.speaker
    }

    rules page editPresentationFoo(p : Presentation) {
      true
      rules action saveSpeaker() {
        securityContext.principal = p.speaker
      }
      rules action savePresentation() {
        securityContext.principal = p.colloquium.moderator
      }
      rules action save() {
        securityContext.principal = p.colloquium.moderator
      }
    }

  }
