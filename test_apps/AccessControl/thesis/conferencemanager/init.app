module init

section RBAC init

  globals
  {
    var alice : User := User
    {
      name := "Alice"
      organization := o0
    };
    var bob : User := User
    {
      name := "Bob"
      organization := o0
    };
    var charlie : User := User
    {
      name := "Charlie"
      organization := o1
    };
    var dave : User := User
    {
      name := "Dave"
      organization := o2
    };
    
    var danny : User := User
    {
      name := "Danny"
      organization := o2
    };
    var eelco : User := User
    {
      name := "Eelco"
      organization := o2
    };
    
    var o0 : Organization := Organization
    {
      name := "Organization 1"
    };
    var o1 : Organization := Organization
    {
      name := "Organization 2"
    };
    var o2 : Organization := Organization
    {
      name := "Organization 3"
    };
    
    
    var c1 : Conference := Conference {
      name := "ICWE08"
      topic := "International Conference on Web Engineering"
      programChair := alice
      programCommittee := {bob}
      reviewCommittee := {dave}
      submissionsOpen := true
      //submittedPapers -> Set<Paper>
      //acceptedPapers -> Set<Paper>
      //rejectedPapers -> Set<Paper>
    };
    
    var pap1 : Paper := Paper{
      title := "Declarative Access Control for Web Applications"
      conference := c1
      authors := {danny,eelco}
      assignedReviewers := {dave}
      assignedPC := {bob}
    };
  }