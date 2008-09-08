module datamodel

section definition

  entity User {
    username         :: String (id, name)
    password         :: Secret
  }

  enum PostStatus {
    draftStatus("Draft"),
    publishedStatus("Published")
  }

  entity Post {
    author       -> User (inline(name))
    title        :: String (name)
    text         :: WikiText
    date         :: DateTime
    status       -> PostStatus
    comments     -> Set<Comment>
    commentCount :: Int
  }

  entity Comment {
    post -> Post (inverse=Post.comments)
    name :: String
    text :: Text
    date :: DateTime
  }

  entity Page {
    name :: String (id)
    text :: WikiText
    author -> User (inline(name))
  }
