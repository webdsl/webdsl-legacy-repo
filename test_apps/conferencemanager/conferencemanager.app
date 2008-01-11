application conferencemanager

imports accesscontrol
imports init
imports templates
imports data

description {
  Conference Manager
}

section Conference Manager pages

  define page home()
  {
    main()
    define body()
    {
      "Welcome to the conference manager"
    }
  }
  
  define page submitPaper()
  {
    main()
    define body()
    {
      var p:Paper := Paper{};
      
      form
      {
        table
        {
          row{input(p.title)}
          row{input(p.authors)}
          row{input(p.conference)}
        }
        
        action("submit",save(p))
      }
      
      action save(p:Paper)
      {
        p.save(); 
        return paper(p);
      }
    }
  }
  
  
  
  
  define page managePapers(c:Conference)
  {
    main()
    define body()
    {
    
      table
      {
        for(p:Paper where p in c.submittedPapers && !(p in c.rejectedPapers || p in c.acceptedPapers ))
        {   
          row{navigate(managePaper(p)){ output(p.name) }}
        }  
      }
    }
    
  }
  
  define page managePaper(p:Paper)
  {
    main()
    define body()
    {
      table
      {
        row{output(p.name)}
        
        viewRowsPaper(p)
        
        row{navigate(assignReview(p)) { "assign reviewers/pc" }}
        row{navigate(paperReview(p)) { "create review"}}
        row{navigate(paperRecommend(p)) { "create recommendation"}}
        row{navigate(paperAccepting(p)) { "acceptance for conference"}}
      }
    }
  }
  
  define page assignReview(p:Paper)
  {
    main()
    define body()
    {
      table
      {
        row{output(p.name)}
        form
        {
          row{"assign reviewers "}
          row{input(p.assignedReviewers)}
          row{"assign program committee "}
          row{input(p.assignedPC)}
          row{action("save",save(p))}
        }
      }
      action save(p:Paper)
      {
        //p.save();
        return managePaper(p);
      }
    }
  }
  
  

  define page paperAccepting(p:Paper)
  {
    main()
    define body()
    {
      table
      {
        row{output(p.name)}
        form
        {
          row{output(p.reviews)}
          row{output(p.recommendations)}
    
          row{action("accept",accept(p))}
          row{action("reject",reject(p))}
        }
      }
      action accept(p:Paper)
      {
        p.conference.acceptedPapers.add(p);
        return managePaper(p);
      }
      action reject(p:Paper)
      {
        p.conference.rejectedPapers.add(p);
        return managePaper(p);
      }
    }
  }
  
  
  define page paperReview(p:Paper)
  {
    main()
    define body()
    {
      table
      {
        row{output(p.name)}
        form
        {
          var r:Review := Review{ paper := p };
          row{input(r.comments)}
          row{input(r.author)}
     
          row{action("save",save(r))}
        }
      }
      action save(r:Review)
      {
        r.save();
        return review(r);
      }

    }
  }
  
  define page paperRecommend(p:Paper)
  {
    main()
    define body()
    {
      table
      {
        row{output(p.name)}
        form
        {
          var r:Recommendation := Recommendation{ paper := p };
          row{input(r.comments)}
          row{input(r.author)}
     
          row{action("save",save(r))}
        }
      }
      action save(r:Recommendation)
      {
        r.save();
        return recommendation(r);
      }

    }
  }