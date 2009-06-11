module ac

access control rules

  principal is User with credentials username, password

  rule template *(*) { 
    true 
  }

  rule page *(*) { 
    true 
  }
