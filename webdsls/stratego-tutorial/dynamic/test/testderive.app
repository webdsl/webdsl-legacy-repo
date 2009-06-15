application tasks

entity User {
  username :: String (id,name)
  tasks    -> Set<Task>
  log      -> Set<Task>
}

entity Task {
  description :: String (name)
  done        :: Bool
}

define page task(task : Task) {
  derive viewPage from task
}
