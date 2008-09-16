module emailtemplates

section emailTemplates

define email exampleEmail (e : EmailEntity) {
  to(e.to)
  from(e.from)
  subject(e.subject)
  body() {
    output(e.body)
  }
}
