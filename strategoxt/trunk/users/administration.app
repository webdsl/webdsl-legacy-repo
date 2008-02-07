module users/administration
  
section ac administration

  define editPermissions(acl : ACL)
  {
    section{
      header{"Change Permissions"}
      form{
        table{
          row{"View"     input(acl.view)}
          row{"Edit"     input(acl.edit)}
          row{"Moderate" input(acl.moderate)}
          // admin is fixed to adminGroup
        }
        action("Save Permissions", savePermissions())
        action savePermissions() {
          acl.save();
        }
      }
    }
  }
  
  define viewPermissions(acl : ACL)
  {
    table{
      row{"View"     output(acl.view)}
      row{"Edit"     output(acl.edit)}
      row{"Moderate" output(acl.moderate)}
      row{"Admin"    output(acl.admin)}
    }
  }
  

  