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
    section
    --description
    --note
    module
    imports
    session
    globals
    function
    inverse
    inverseSlave
    define
    for
    page
    select
    init
    action
    return
    var
    if
    then
    else
    in
    do
    rules
    List
    Set
    true
    false
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

    Keyword ::= s e c t i o n
        /.$BeginAction
            $setResult($_section);
          $EndAction
        ./

    --Keyword ::= d e s c r i p t i o n
    --    /.$BeginAction
    --        $setResult($_description);
    --      $EndAction
    --    ./

    --Keyword ::= n o t e
    --    /.$BeginAction
    --        $setResult($_note);
    --      $EndAction
    --    ./

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

    Keyword ::= d e f i n e
        /.$BeginAction
            $setResult($_define);
          $EndAction
        ./

    Keyword ::= f o r
        /.$BeginAction
            $setResult($_for);
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

    Keyword ::= t h e n
        /.$BeginAction
            $setResult($_then);
          $EndAction
        ./

    Keyword ::= i n
        /.$BeginAction
            $setResult($_in);
          $EndAction
        ./

    Keyword ::= d o
        /.$BeginAction
            $setResult($_do);
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

    Keyword ::= r u l e s
        /.$BeginAction
            $setResult($_rules);
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
