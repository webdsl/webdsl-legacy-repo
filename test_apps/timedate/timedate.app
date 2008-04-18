application com.example.timedate

description {
  This is an automatically generated description
}

imports templates
section pages

entity AnEntity {
  date :: Date
  datetime :: DateTime
}

globals {
  var ae : AnEntity := AnEntity { 
    date := Date("22/06/1983"),
    datetime := now()
  };
}

define page home() {
  main()
  define body() {
    form {
      table {
        row { "Current date:" output(ae.date) }
        row { "New date:" input(ae.date) }
        row { "Current datetime:" output(ae.datetime) }
        row { "New datetime:" input(ae.datetime) }
      }
      action("Save", save())

      action save() {
        return home();
      }
    }
  }
}
