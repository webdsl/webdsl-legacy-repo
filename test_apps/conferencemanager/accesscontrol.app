module accesscontrol

section conference manager AccessControl


  entity ConflictUserSet
  {
    conflictingusers -> Set<User>
  } 
  //entity Conflicts
 // {
   // conflictsets -> Set<ConflictUserSet>
 // }
  extend entity Conference
  {
    userconflicts -> Set<ConflictUserSet>
  }
 
  //globals
  //{
    //application wide conlicts
    //var globalconflicts : Conflicts := Conflicts {};
  //}
  
  define page addConflict(con:Conference)
  {
    main()
    define body()
    {
      var cset : ConflictUserSet := ConflictUserSet{};
      form
      {
        table
        {
          row{input(cset.conflictingusers)}
          action ("Add conflict",save(cset))
        }
      }
      action save(c:ConflictUserSet)
      {     
        c.save();
        con.userconflicts.add(c);
        return manageConflicts(con);
      }
    }
  }
  
  define page manageConflicts(con:Conference)
  {
    main()
    define body()
    {
      form
      {
        for(c:ConflictUserSet in con.userconflictsList)
        {
          table
          {
            row{output(c.conflictingusers) action ("Remove conflict",remove(c))}
          }
        }
      }
      action remove(c:ConflictUserSet)
      {     
        con.userconflicts.remove(c);
        return home();
      }
    }
  }

  globals
  {
    function usersInDeclaredConflict(u1:User, u2:User, conflictsets:Set<ConflictUserSet>):Bool
    {
      for(conflictset : ConflictUserSet in conflictsets)
      {
        if(u1 != u2 && u1 in conflictset.conflictingusers && u2 in conflictset.conflictingusers)
        {
          return true;
        }
      }
      return false;
    }  
  }

  access control rules
  {
    principal is User with credentials name

    pointcut openSections()
    {
      page home(),
      template sidebar(),
      page user(*),
      page createUser(*),
      
      page conference(*),
      page createConference(*),
      //chair page editConference(*),
      //chair/rv/pc page managePapers(*),
      //chair/rv/pc page managePaper(*),
      //chair page assignReview(*),
      //rv page paperReview(*),
      //pc page paperRecommend(*),
      //chair page paperAccepting(*),
      
      //chair page review(*),
      
      //chair page recommendation(*),
      
      page paper(*),
      page submitPaper(*)
      
      //chair page addConflict(*),
      //chair page manageConflicts(*)
      
    }
    
    rules pointcut openSections()
    {
      true
    }
    predicate isValidRevPCAssignment(p:Paper)
    {
      !(true in [authorInConflict(a,p) for(a:User in p.authors)])
    }
    predicate authorInConflict(u:User,p:Paper)
    {
      conflictingUsers(u,p.assignedReviewers,p.conference)
      || conflictingUsers(u,p.assignedPC,p.conference)
    }
    predicate conflictingUsers(u1:User,uset:Set<User>,c:Conference)  
    {
      true in [u1.organization = u2.organization
               || usersInDeclaredConflict(u1,u2,c.userconflicts)
               for(u2:User in uset)]
    } 
    predicate isPC(u:User,c:Conference)
    {
      u in c.programCommittee
    }      
    predicate isReviewer(u:User,c:Conference)
    {
      u in c.reviewCommittee
    }   
    predicate isChair(u:User,c:Conference)
    {
      u = c.programChair
    }
    predicate mayManageConferencePapers(u:User,c:Conference)
    {
      isChair(u,c)
      || isReviewer(u,c)
      || isPC(u,c)
    }
  
    
    rules page managePapers(c:Conference)
    {
      mayManageConferencePapers(securityContext.principal,c)
    }
    rules page managePaper(p:Paper)
    {
      mayManageConferencePapers(securityContext.principal,p.conference)
    }
    

   
    pointcut chairSections(c:Conference)
    {
      page editConference(c),
      page addConflict(c),
      page manageConflicts(c)
    }
    rules pointcut chairSections(c:Conference)
    {
      isChair(securityContext.principal,c)
    }
    pointcut chairSections(p:Paper)
    {
      page assignReview(p),
      page paperAccepting(p)
    }
    rules pointcut chairSections(p:Paper)
    {
      isChair(securityContext.principal,p.conference)
    }   
    
    rules page assignReview(p:Paper)
    {
      true
      rules action save(p:Paper)
      { 
        isValidRevPCAssignment(p)
      }
    }
    
     
    rules page review(r:Review)
    {
      isChair(securityContext.principal,r.paper.conference)
    }
    rules page recommendation(r:Recommendation)
    {
      isChair(securityContext.principal,r.paper.conference)
    }

    rules page paperRecommend(p:Paper)
    {
      securityContext.principal in p.assignedPC
    }
    rules page paperReview(p:Paper)
    {
      securityContext.principal in p.assignedReviewers
    }
  }

  globals
  {  
    function userLogin(u:User):Int
    {
      securityContext.principal := u;
      securityContext.loggedIn := true;

      return 0;
    }
    
    function userLogout():Int
    {
      securityContext.principal := null;
      securityContext.loggedIn := false;
 
      return 0;
    }
  }
   
 