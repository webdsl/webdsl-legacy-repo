--
-- The Java KeyWord Lexer
--
%options package=webdsl.safari.parser
%options template=KeywordTemplate.gi

%Include
    KWLexerLowerCaseMap.gi
%End

%Export

    -- List all the keywords the kwlexer will export to the lexer and parser
    application
    module
    imports
    session
    globals
    function
    inverse
    inverseSlave
    entity
    for
    where
    page
    select
    init
    action
    return
    var
    if
    else
    in
    rules
    task
    List
    Set
    true
    false
    null
    -- define is also a keyword, sort of, but defined in the regular lexer
%End

%Terminals
    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
%End

%Start
    Keyword
%End

%Rules

    -- The Goal for the parser is a single Keyword

    Keyword ::= e l s e
        /.$BeginAction
            $setResult($_else);
          $EndAction
        ./

    Keyword ::= i f
        /.$BeginAction
            $setResult($_if);
          $EndAction
        ./

    Keyword ::= r e t u r n
        /.$BeginAction
            $setResult($_return);
          $EndAction
        ./

    Keyword ::= a p p l i c a t i o n
        /.$BeginAction
            $setResult($_application);
          $EndAction
        ./

    Keyword ::= m o d u l e
        /.$BeginAction
            $setResult($_module);
          $EndAction
        ./

    Keyword ::= i m p o r t s
        /.$BeginAction
            $setResult($_imports);
          $EndAction
        ./

    Keyword ::= s e s s i o n
        /.$BeginAction
            $setResult($_session);
          $EndAction
        ./

    Keyword ::= g l o b a l s
        /.$BeginAction
            $setResult($_globals);
          $EndAction
        ./

    Keyword ::= f u n c t i o n
        /.$BeginAction
            $setResult($_function);
          $EndAction
        ./

    Keyword ::= i n v e r s e
        /.$BeginAction
            $setResult($_inverse);
          $EndAction
        ./

    Keyword ::= i n v e r s e 'S' l a v e
        /.$BeginAction
            $setResult($_inverseSlave);
          $EndAction
        ./

    Keyword ::= e n t i t y
        /.$BeginAction
            $setResult($_entity);
          $EndAction
        ./

    Keyword ::= f o r
        /.$BeginAction
            $setResult($_for);
          $EndAction
        ./

    Keyword ::= w h e r e
        /.$BeginAction
            $setResult($_where);
          $EndAction
        ./

    Keyword ::= p a g e
        /.$BeginAction
            $setResult($_page);
          $EndAction
        ./

    Keyword ::= s e l e c t
        /.$BeginAction
            $setResult($_select);
          $EndAction
        ./

    Keyword ::= i n i t
        /.$BeginAction
            $setResult($_init);
          $EndAction
        ./

    Keyword ::= a c t i o n
        /.$BeginAction
            $setResult($_action);
          $EndAction
        ./

    Keyword ::= v a r
        /.$BeginAction
            $setResult($_var);
          $EndAction
        ./

    Keyword ::= i n
        /.$BeginAction
            $setResult($_in);
          $EndAction
        ./

    Keyword ::= t r u e
        /.$BeginAction
            $setResult($_true);
          $EndAction
        ./

    Keyword ::= f a l s e
        /.$BeginAction
            $setResult($_false);
          $EndAction
        ./

    Keyword ::= n u l l
        /.$BeginAction
            $setResult($_null);
          $EndAction
        ./

    Keyword ::= r u l e s
        /.$BeginAction
            $setResult($_rules);
          $EndAction
        ./

    Keyword ::= t a s k
        /.$BeginAction
            $setResult($_task);
          $EndAction
        ./

    Keyword ::= S e t
        /.$BeginAction
            $setResult($_Set);
          $EndAction
        ./

    Keyword ::= 'L' i s t
        /.$BeginAction
            $setResult($_List);
          $EndAction
        ./
        
%End
