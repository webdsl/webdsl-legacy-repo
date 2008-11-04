application com.example.subprocedure

imports templates

section dummy principal

  entity User
  {
    name :: String
    password :: Secret
  }

  access control rules
  {
    principal is User with credentials name, password
    
    rules page *(*) {
      true
    }
    rules action *(*) {
      true
    }
    rules template *(*) {
      true
    }
  }
  
  
  

section data

  entity Persoon {
    a3 <> A3ProcedureStatus ( )
    a2 <> A2ProcedureStatus ( )
    a1 <> A1ProcedureStatus ( )
    a <> AProcedureStatus ( )
    repeatUntilProc0 <> RepeatUntilProc0ProcedureStatus ( )
    name :: String ( )
    function startA() {
      var a : AProcedureStatus := AProcedureStatus{}; 
      a.persist();
      this.a := a;
      this.a.p := this;
      this.a.persist();
      this.persist();
      this.a.enable();
    }
  }
  
  entity ProcedureStatus {
    ancestor -> ProcedureStatus()
    caller -> ProcedureStatus ( )
    descendents -> Set<ProcedureStatus> (inverse=ProcedureStatus.ancestor)
    returnstate :: Int ( )
    branch :: Int ( )
    name :: String ( ) := "Procedure status"
    isEnabled :: Bool ( )
    date :: DateTime ( )
    function enable ( c : ProcedureStatus, r : Int, b : Int ) : Void
    {
      this.isEnabled := true;
      this.caller := c;
      this.branch := b;
      this.returnstate := r;
      this.persist();
      this.enabled();
    }
    function next ( state : Int ) : Void
    {
    }
    function processed ( ) : Void
    {
      this.isEnabled := false;
      this.persist();
      if ( this.caller != null )
      {
        if ( this.branch != null )
        {
          this.caller.notifyOfActivity(this.branch);
        }
        else
        {
        }
        this.caller.next(this.returnstate);
      }
      else
      {
      }
    }
    function notifyOfActivity ( branch : Int ) : Void
    {
      this.cascadeNotification();
    }
    function cascadeNotification ( ) : Void
    {
      if ( this.caller != null && this.branch != null )
      {
        this.caller.notifyOfActivity(this.branch);
      }
      else
      {
      }
    }
    function partialCommit ( ) : Void
    {
      if ( this.caller != null )
      {
        this.caller.partialCommit();
      }
      else
      {
      }
    }
    function enabled ( ) : Void
    {
    }
    function disabled ( ) : Void
    {
    }
    function do ( ) : Void
    {
    }
    function done ( ) : Void
    {
    }
    function enable ( ) : Void
    {
      this.isEnabled := true;
      this.persist();
      this.enabled();
    }
    function disable ( ) : Void
    {
      this.isEnabled := false;
      this.persist();
      this.disabled();
    }
  }

  entity RepeatUntilProc0ProcedureStatus : ProcedureStatus {
    p -> Persoon ( )
    function next ( state : Int ) : Void
    {
      if ( state == 0 )
      {
        if (this.p.a2 == null) {
          this.p.a2 := A2ProcedureStatus {};
          this.p.a2.p := this.p;
          this.p.a2.ancestor := this;
          this.p.a2.ancestor.persist();
          this.p.a2.persist();
          this.p.persist();
        }
        this.p.a2.enable(this as ProcedureStatus, 1, 1);
      }
      else
      {
      }
      if ( state == 1 )
      {
        this.enableRepeat();
        this.enableUntil();
      }
      else
      {
      }
      if ( state == 2 )
      {
        if (this.p.a3 == null) {
          this.p.a3 := A3ProcedureStatus {};
          this.p.a3.p := this.p;
          this.p.a3.ancestor := this;
          this.p.a3.ancestor.persist();
          this.p.a3.persist();
          this.p.persist();
        }
        this.p.a3.enable(this as ProcedureStatus, 3, 2);
      }
      else
      {
      }
      if ( state == 3 )
      {
        this.disableRepeat();
        this.processed();
      }
      else
      {
      }
    }
    function enableRepeat ( ) : Void
    {
      this.next(0);
    }
    function disableRepeat ( ) : Void
    {
      this.p.a2.disable();
    }
    function enableUntil ( ) : Void
    {
      this.next(2);
    }
    function notifyOfActivity ( branch : Int ) : Void
    {
      this.cascadeNotification();
      if ( branch == 1 )
      {
        if (this.p.a3 != null) {
          this.p.a3.disable();
        }
      }
      else
      {
      }
    }
    function enabled ( ) : Void
    {
      this.enableRepeat();
    }
    function done ( ) : Void
    {
      this.isEnabled := false;
      this.persist();
      this.next(0);
    }
    function disabled ( ) : Void
    {
      this.p.a2.disable();
      this.p.a3.disable();
    }
  }

  entity AProcedureStatus : ProcedureStatus {
    p -> Persoon ( )
    function next ( state : Int ) : Void
    {
      if ( state == 0 )
      {
        this.prepareA1();
        this.p.a1.enable(this as ProcedureStatus, 1, 0);
      }
      else
      {
      }
      if ( state == 1 )
      {
        this.startRepeatUntilProc0();
      }
      else
      {
      }
      if ( state == 2 )
      {
        this.processed();
      }
      else
      {
      }
    }
    function enabled ( ) : Void
    {
      this.done();
    }
    function done ( ) : Void
    {
      this.isEnabled := false;
      this.persist();
      this.next(0);
    }
    function disabled ( ) : Void
    {
    }
    function prepareA1() {
      if (this.p.a1 == null) {
        this.p.a1 := A1ProcedureStatus {};
        this.p.a1.p := this.p;
        this.p.a1.ancestor := this;
        this.p.a1.ancestor.persist();
        this.p.a1.persist();  
        this.p.persist();
      }
    }
    function startRepeatUntilProc0() {
      if (this.p.repeatUntilProc0 == null) {
        this.p.repeatUntilProc0 := RepeatUntilProc0ProcedureStatus {};
        this.p.repeatUntilProc0.p := this.p;
        this.p.repeatUntilProc0.ancestor := this;
        this.p.repeatUntilProc0.persist();
        this.p.persist();
      }
      this.p.repeatUntilProc0.enable(this as ProcedureStatus, 2, 0);
    }
  }

  entity A1ProcedureStatus : ProcedureStatus {
    p -> Persoon ( )
    function enabled ( ) : Void
    {
    }
    function done ( ) : Void
    {
      this.isEnabled := false;
      this.persist();
      this.processed();
    }
    function disabled ( ) : Void
    {
    }
  }

  entity A2ProcedureStatus : ProcedureStatus {
    p -> Persoon ( )
    function enabled ( ) : Void
    {
    }
    function done ( ) : Void
    {
      this.isEnabled := false;
      this.persist();
      this.processed();
    }
    function disabled ( ) : Void
    {
    }
  }

  entity A3ProcedureStatus : ProcedureStatus {
    p -> Persoon ( )
    function enabled ( ) : Void
    {
    }
    function done ( ) : Void
    {
      this.isEnabled := false;
      this.persist();
      this.processed();
    }
    function disabled ( ) : Void
    {
    }
  }

  
/**
 * Procedures
 */
access control rules
  anonymous
  rule page a ( p : Persoon ) {
    true && true && true && true && p.a.isEnabled
    rule action doAction (  ) {
      true && true && true && true && p.a.isEnabled
    }
  }

access control rules
  anonymous
  rule page a1 ( p : Persoon ) {
    true && true && true && p.a1.isEnabled
    rule action doAction (  ) {
      true && true && true && p.a1.isEnabled
    }
  }

access control rules
  anonymous
  rule page a2 ( p : Persoon ) {
    true && true && true && p.a2.isEnabled
    rule action doAction (  ) {
      true && true && true && p.a2.isEnabled
    }
  }

access control rules
  anonymous
  rule page a3 ( p : Persoon ) {
    true && true && true && p.a3.isEnabled
    rule action doAction (  ) {
      true && true && true && p.a3.isEnabled
    }
  }

access control rules
  anonymous
  rule page repeatUntilProc0 ( p : Persoon ) {
    true && true && true && true && true && p.repeatUntilProc0.isEnabled
    rule action doAction (  ) {
      true && true && true && true && true && p.repeatUntilProc0.isEnabled
    }
  }
  
section procedures

  define template persoonProceduresList (persoon : Persoon) {
    list()[]{
      if ( true && persoon.a3 != null && persoon.a3.isEnabled && true ) {
        listitem()[]{
          navigatebutton(a3(persoon), "A3")[]{
          }
        }
      }

      if ( true && persoon.a2 != null && persoon.a2.isEnabled && true ) {
        listitem()[]{
          navigatebutton(a2(persoon), "A2")[]{
          }
        }
      }

      if ( true && persoon.a1 != null && persoon.a1.isEnabled && true ) {
        listitem()[]{
          navigatebutton(a1(persoon), "A1")[]{
          }
        }
      }

      if ( true && persoon.a != null && persoon.a.isEnabled && true ) {
        listitem()[]{
          navigate(a(persoon))[]{
            text("A")[]{
            }
          }
        }
      }
      else
      {
      }
    }
  }

  define template persoonProcedures (persoon : Persoon) {
    header()[]{
      dummy()[]{
        if ( persoon != null ) {
          navigate(persoon(persoon))[]{
            text(persoon.name)[]{
            }
          }
        }

        if ( persoon == null ) {
          text("null")[]{
          }
        }
        else
        {
        }
      }
    }
    persoonProceduresList(persoon)[]{
    }
  }

  define page allTasks () {
    title()[]{
      "All tasks"
    }
    main()[]{
    }
    define local body () {
      header()[]{
        "All tasks"
      }
      section()[]{
        persoonTaskList()[]{
        }
      }
    }
  }

  function persoonHasProcedures ( persoon : Persoon ) : Bool
  {
    return true && persoon.a3 != null && persoon.a3.isEnabled && true || true && persoon.a2 != null && persoon.a2.isEnabled && true || true && persoon.a1 != null && persoon.a1.isEnabled && true || true && persoon.a != null && persoon.a.isEnabled && true || false;
  }

  define template persoonTaskList () {
    header()[]{
      "Persoon"
    }
    list()[]{
      for ( persoon : Persoon where true order by null asc limit 1000 offset 0 ) {
        if ( persoonHasProcedures(persoon) ) {
          listitem()[]{
            dummy()[]{
              if ( persoon != null ) {
                navigate(persoon(persoon))[]{
                  text(persoon.name)[]{
                  }
                }
              }
              else
              {
              }
              if ( persoon == null ) {
                text("null")[]{
                }
              }
              else
              {
              }
            }
            persoonProceduresList(persoon)[]{
            }
          }
        }
        else
        {
        }
      }
    }
  }

  define page persoonTasks () {
    title()[]{
      "Persoon"
    }
    main()[]{
    }
    define local body () {
      header()[]{
        "Tasks"
      }
      persoonTaskList()[]{
      }
    }
  }

  define template persoonStatusList () {
    header()[]{
      "Persoon"
    }
    list()[]{
      for ( persoon : Persoon where true order by null asc limit 1000 offset 0 ) {
        listitem()[]{
          dummy()[]{
            if ( persoon != null ) {
              navigate(persoon(persoon))[]{
                text(persoon.name)[]{
                }
              }
            }
            else
            {
            }
            if ( persoon == null ) {
              text("null")[]{
              }
            }
            else
            {
            }
          }
          list()[]{
            if ( persoon.a3 != null && persoon.a3.isEnabled && true ) {
              if ( true ) {
                listitem()[]{
                  navigate(a3(persoon))[]{
                    text("A3")[]{
                    }
                    " (X)"
                  }
                }
              }
              else
              {
              }
              if ( ! true ) {
                listitem()[]{
                  text("A3")[]{
                  }
                }
              }
              else
              {
              }
            }
            else
            {
            }
            if ( persoon.a2 != null && persoon.a2.isEnabled && true ) {
              if ( true ) {
                listitem()[]{
                  navigate(a2(persoon))[]{
                    text("A2")[]{
                    }
                    " (X)"
                  }
                }
              }
              else
              {
              }
              if ( ! true ) {
                listitem()[]{
                  text("A2")[]{
                  }
                }
              }
              else
              {
              }
            }
            else
            {
            }
            if ( persoon.a1 != null && persoon.a1.isEnabled && true ) {
              if ( true ) {
                listitem()[]{
                  navigate(a1(persoon))[]{
                    text("A1")[]{
                    }
                    " (X)"
                  }
                }
              }
              else
              {
              }
              if ( ! true ) {
                listitem()[]{
                  text("A1")[]{
                  }
                }
              }
              else
              {
              }
            }
            else
            {
            }
            if ( persoon.a != null && persoon.a.isEnabled && true ) {
              if ( true ) {
                listitem()[]{
                  navigate(a(persoon))[]{
                    text("A")[]{
                    }
                    ""
                  }
                }
              }
              else
              {
              }
              if ( ! true ) {
                listitem()[]{
                  text("A")[]{
                  }
                }
              }
              else
              {
              }
            }
            else
            {
            }
          }
        }
      }
    }
  }

  define page persoonStatus () {
    title()[]{
      "Persoon"
    }
    main()[]{
    }
    define local body () {
      section()[]{
        header()[]{
          "Status"
        }
        section()[]{
          persoonStatusList()[]{
          }
        }
      }
    }
  }

  define page procedureStatus (s : ProcedureStatus) {
    dummy()[]{
      main()[]{
      }
      define local body () {
        header()[]{
          text(s.name)[]{
          }
        }
        group("Details")[]{
          dummy()[]{
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem() {
              label("Ancestor") {
                output(s.ancestor)
              }
            }
            groupitem() {
              label("Descendents") {
                output(s.descendents)
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
          }
        }
      }
    }
  }
  
section  procedures .

  define page repeatUntilProc0ProcedureStatus (s : RepeatUntilProc0ProcedureStatus) {
    dummy()[]{
      main()[]{
      }
      define local body () {
        header()[]{
          text(s.name)[]{
          }
        }
        group("Details")[]{
          dummy()[]{
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem() {
              label("Ancestor") {
                output(s.ancestor)
              }
            }
            groupitem() {
              label("Descendents") {
                output(s.descendents)
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
          }
        }
      }
    }
  }

  define page repeatUntilProc0 (p : Persoon) {
  }

  define page aProcedureStatus (s : AProcedureStatus) {
    dummy()[]{
      main()[]{
      }
      define local body () {
        header()[]{
          text(s.name)[]{
          }
        }
        group("Details")[]{
          dummy()[]{
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
          }
        }
      }
    }
  }

  define page a1ProcedureStatus (s : A1ProcedureStatus) {
    dummy()[]{
      main()[]{
      }
      define local body () {
        header()[]{
          text(s.name)[]{
          }
        }
        group("Details")[]{
          dummy()[]{
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
          }
        }
      }
    }
  }

  define page a2ProcedureStatus (s : A2ProcedureStatus) {
    dummy()[]{
      main()[]{
      }
      define local body () {
        header()[]{
          text(s.name)[]{
          }
        }
        group("Details")[]{
          dummy()[]{
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
          }
        }
      }
    }
  }

  define page a3ProcedureStatus (s : A3ProcedureStatus) {
    dummy()[]{
      main()[]{
      }
      define local body () {
        header()[]{
          text(s.name)[]{
          }
        }
        group("Details")[]{
          dummy()[]{
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("P: ")[]{
                dummy()[]{
                  if ( s.p != null ) {
                    navigate(persoon(s.p))[]{
                      text(s.p.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.p == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
            groupitem()[]{
              label("Name: ")[]{
                text(s.name)[]{
                }
              }
            }
            groupitem()[]{
              label("Is enabled: ")[]{
                outputBool(s.isEnabled)[]{
                }
              }
            }
            groupitem()[]{
              label("Date: ")[]{
                outputDateTime(s.date)[]{
                }
              }
            }
            groupitem()[]{
              label("Caller: ")[]{
                dummy()[]{
                  if ( s.caller != null ) {
                    navigate(procedureStatus(s.caller))[]{
                      text(s.caller.name)[]{
                      }
                    }
                  }
                  else
                  {
                  }
                  if ( s.caller == null ) {
                    text("null")[]{
                    }
                  }
                  else
                  {
                  }
                }
              }
            }
            groupitem()[]{
              label("Returnstate: ")[]{
                outputInt(s.returnstate)[]{
                }
              }
            }
            groupitem()[]{
              label("Branch: ")[]{
                outputInt(s.branch)[]{
                }
              }
            }
          }
        }
      }
    }
  }

  define page a (p : Persoon) {
  }

  define page a1 (p : Persoon) {
    init
    {
      p.a1.done();
      goto persoon(p);
    }
  }

  define page a2 (p : Persoon) {
    init
    {
      p.a2.done();
      goto persoon(p);
    }
  }

  define page a3 (p : Persoon) {
    init
    {
      p.a3.done();
      goto persoon(p);
    }
  }
  
/*
  auto procedure a(p : Persoon) {
    process {
      a1(p)
      ; repeat { a2(p) } until a3(p)
    }
  }
  procedure a1(p : Persoon) { }
  procedure a2(p : Persoon) { }
  procedure a3(p : Persoon) { }*/
  
/*  procedure b(p : Persoon) { }*/

section pages

  define page persoon(p : Persoon) {
    derive procedurePage from p
  }

  define page home() {
  	main()
  	define body() {
  	  par{navigate(startTest()){"Start test"}}
  		par{navigate(allTasks()){"Tasks"}}
  	}
  }

  define page startTest() {
    init {
      var p : Persoon := Persoon {
        name := "Ruben"
      };
      
      p.startA();
      p.persist();
      p.a.enable();
    }
    main()
    define body() {
      par{"Test started"}
  		par{navigate(allTasks()) {"Tasks"}}
    }
  }
  
  define page allPersoon() {
    text("Alle personen")
    for (p : Persoon) {
      navigate(persoon(p))
    }
  }