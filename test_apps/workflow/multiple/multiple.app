entity Document {
  title :: String
  body  :: Text
}

operation spellCheck(d: Document) {
  who { securityContext.principal in secretaries }
  when { !status.spellChecked }
  view {
    title{"Do spellcheck on document " d.title}
    derive operationPage from d
  }
}

operation review(d: Document) {
  who { securityContext.principal in reviewers }
  when { !status.reviewed }
  view {
    title{"Review document " d.title}
    derive operationPage from d
  }
}