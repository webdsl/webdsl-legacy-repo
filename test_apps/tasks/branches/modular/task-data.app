module task-data

entity Task {
  name        :: String (name)
  description :: Text
  done        :: Bool
  archived    :: Bool
  user        -> User (inverse=User.tasks)
}

extend entity User {
  tasks      -> List<Task>
  unfinished -> List<Task> := [t | t : Task in this.tasks where !(t.done)]
  todo       -> List<Task> := [t | t : Task in this.tasks where !(t.archived)]
  archive    -> List<Task> := [t | t : Task in this.tasks where t.archived]
}
