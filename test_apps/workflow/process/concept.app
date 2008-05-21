



procedures

  procedure main(c : C) {
    enabled { }
  }

  procedure main(c : C) {
    who { }
    when { }
    enabled { }
    view { }
    do { }
    done { }
    process { }
    processed { 
      a; 
      b; 
      if(cond) { c; d } else { e; f }; 
      (g |OR| h);
      (i |AND| j);
      *(k);
      +(l);
      m
    }
  }
  
  procedure k {
    process {
      x; y; z
    }
  }
-

  a; b; if(cond) {c; d} => [a, b, if[c, d]]

  
  [e1|tail] => 
    done { map(first(tail)).enable() }
  
  [if(cond){e1;e1l}else{e2;e2l}|t1;tail] =>
    [ifSplit; ifTussen(cond){e1;e1l}else{e2;e2l}|[t1|tail]])
      where
        ifSplit = gen(if) {
          enabled {
            if (cond) {
              e1.enable();
            } else {
              e2.enable();
            }
          }
        }
      ; e1l {
          done = t1.enable();
        }
      ; e2l {
          done = t1.enable();
        }
  
  [a(x); (b1(e); e1; c1 |XOR| e2)|t1;tail] =>
    [xorSplit; xorTussen(e1 |XOR| e2); t1;tail]
      where
        procedure a(x : A) {
          processed {
            enable#b1(x);
            enable#b2(x);
          }
        }
      ; e1 {
          done = t1.enable();
        }
      ; e2 {
          done = t1.enable();
        }
        
  [*(e1)|t1;tail] =>
    e1 {
      enabled {
        t1.enable();
      }
      done {
        e1.enable();
      }
    }
    t1 {
      done {
        e1.disable();
      }
    }

  [+(e1)|t1;tail] =>
    e1 {
      done {
        t1.enable();
        e1.enable();
      }
    }    
    t1 {
      done {
        e1.disable();
      }
    }
/*
  first(e1) = {e1}
  first([e1;|tail]) = {e1}
  first(if(cond) {e1} else {e2}) = {gen(if)}
  first((e1 |XOR| e2)) = {e1, e2}
  first((e1 |AND| e2)) = {e1, e2}
*/
  
  gen(if) = (bijv) if1
  
=>

  procedure a(c : C) {
    done { b.enable() }
  }
  
  procedure b(c : C) {
    done { if1.enable() }
  }
  
  procedure if1(c : C) {
    enabled { 
      if (cond) {
        c.enable();
      } else {
        e.enable();
      }
    }
  }
  
  procedure c(c : C) {
    done { d.enable() }
  }
  
  procedure d(c : C) {
    done { or1.enable() }
  }
  
  procedure e(c : C) {
    done { f.enable() }
  }
  
  procedure f(c : C) {
    done { or1.enable() }
  }
  
  procedure or1(c : C) {
    enabled { 
      g.enable();
      h.enable();
    }
  }
  
  procedure g(c : C) {
    done {
      and1.enable()
    }
  }
  
  procedure h(c : C) {
    done {
      and1.enable()
    }
  }
  
  procedure and1(c : C) {
    enabled {
      i.enable();
      j.enable();
    }
  }

  procedure i(c : C) {
    done {
      and1Join.enable()
    }
  }
  
  procedure j(c : C) {
    done {
      and1Join.enable()
    }
  }
  
  procedure and1Join(c : C) {
    enabled {
      c.and1JoinStatus.count++;
      if (c.and1JoinStatus.count == 2) {
        k.enable()
      }
    }
  }
  
  procedure k(c : C) {
    enabled {
      l.enable();
    }
    done {
      k.enable();
    }
  }
  
  procedure l(c : C) {
    done {
      k.disable();
      m.enable();
      l.enable();
    }
  }
  
  procedure m(c : C) {
    done {
      l.disable();
    }
  }