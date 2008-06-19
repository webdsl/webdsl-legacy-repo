module users/profile

description {

  Viewing and editing of user profiles.
  
}

section menu

  define usersMenu() {
    menu { 
      menuheader{ navigate(users()){"Users"} }
      for(u : User order by u.username) {
        menuitem{ output(u) }
      }
    }
  }
  
  define thisUserMenu(user : User)
  {
    menu {
      menuheader{ navigate(user(user)){ "This User" } }
      menuitem{ navigate(editProfile(user)){"Edit"} }
    }
  }

section user profile

  define page user(user : User)
  {
    main()
    title{"User " output(user.fullname)}
    define sidebar() {
    }
    define thisMenu() { thisUserMenu(user) }
    define body() {
      section{
        header{output(user.fullname)}
        par{output(user.profile)}
        table{
          row{ "Username"    output(user.username) }
          row{ "Homepage"    output(user.homepage) }
          row{ "Country"     output(user.country) }
          row{ "Affiliation" output(user.affiliation) }
          row{ "Groups"      for(g : UserGroup in user.groupsList) { output(g) " " } }
        }
      }
    }
  }
  
  define page editProfile(user : User)
  {
    main()
    title{"Edit Profile " output(user.username)}
    define thisMenu() { thisUserMenu(user) }
    define body() {
      section{
        header{"Edit Profile " output(user.username)}
        form{
          table{
            row{ "Fullname"    input(user.fullname) }
            row{ "Homepage"    input(user.homepage) }
            row{ "Country"     input(user.country) }
            row{ "Affiliation" input(user.affiliation) }
            row{ "Active groups:" select(user.activeGroups from user.groups) }
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
  
