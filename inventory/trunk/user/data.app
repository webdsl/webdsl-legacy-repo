module user/data

  entity User {
    name 			:: String (name)
    email    		:: Email	(id, validate(isUniqueUser(this),"Email is taken"))
    password 		:: Secret	(validate(password.length() >= 6, "Password needs to be at least 6 characters"))
    inventories 	-> Set<Inventory>
  }
