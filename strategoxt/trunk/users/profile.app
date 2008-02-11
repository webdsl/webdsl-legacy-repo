module users/profile

description {

  Viewing and editing of user profiles.
  
}

section menu

  define usersMenu() {
    menu { 
      menuheader{ navigate(users()){"Users"} }
      userOperationsMenu()
      for(b : User in config.usersList) {
        menuitem{ output(b) }
      }
    }
  }
  
  define userOperationsMenu() { }
      
section user profile

  define page user(user : User)
  {
    main()
    title{"User " output(user.fullname)}
    define sidebar() {
    }
    define userOperationsMenu() { 
      menuitem{ navigate(editUser(user)){"Edit User " output(user.name)} }
      menuspacer{}
    }
    define body() {
      section{
        header{output(user.fullname)}
        table{
          row{ "Username"    output(user.username) }
          row{ "Homepage"    output(user.homepage) }
          row{ "Country"     output(user.country) }
          row{ "Affiliation" output(user.affiliation) }
          row{ "Groups"      for(g : UserGroup in user.groupsList) { output(g) " " } }
        }
        par{output(user.profile)}
      }
    }
  }
  
  define page editProfile(user : User)
  {
    main()
    title{"Edit Profile " output(user.username)}
    define body() {
      section{
        header{"Edit Profile " output(user.username)}
        form{
          table{
            row{ "Fullname"    input(user.fullname) }
            row{ "Homepage"    input(user.homepage) }
            row{ "Country"     input(user.country) }
            row{ "Affiliation" input(user.affiliation) }
            row{ "Profile"     input(user.profile) }
          }
          action("Save",  saveProfile())
        }
        action saveProfile() {
          user.save();
          return user(user);
        }
      }
    }
  }