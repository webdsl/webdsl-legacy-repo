module access-control

access control rules

principal is User with credentials username, password

access control rules

rule template *(*) { true }

rule page *(*) { true }

rule template manageTask(task : Task) {
  loggedIn && task.user == principal
}

rule template removeUser(user : User) {
  loggedIn && user == principal
}

rule template userrow(user : User) {
  loggedIn
}
