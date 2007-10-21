%options package=webdsl.safari.parser
%options template=LexerTemplate.gi
%options filter=WebDSLKWLexer.gi

%Globals
    /.import java.util.*;
    import org.eclipse.imp.parser.ILexer;
    ./
%End

%Define
    $additional_interfaces /., ILexer./
    $kw_lexer_class /.$WebDSLKWLexer./
%End

%Include
    LexerBasicMap.gi
%End

%Export
    --
    -- List all the token types the lexer will directly process
    -- and export to the parser. If a keyword lexer is used as
    -- a filter for this lexer, it may export a set of keywords
    -- that will also be passed along to the parser.
    -- 
    -- For example:
    --
        SINGLE_LINE_COMMENT
        IDENTIFIER 
        NUMBER
        STRING
        FLOAT
        COMMA
        SEMICOLON
        PLUS
        PERCENT
        MINUS
        TIMES
        DIVIDE
        GREATER
        LESS
        EQUAL
        NOTEQUAL
        SMALLERGREATER
        COLON
        COLONCOLON
        RIGHTARROW
        ASSIGN
        DOT
        LEFTPAREN
        RIGHTPAREN
        LEFTBRACE
        RIGHTBRACE
        LEFTSQUARE
        RIGHTSQUARE
        QUESTIONMARK
        IS
        SECTIONCOMMENT
        NOTECOMMENT
        DESCRIPTIONCOMMENT
        define -- HACK: 'define' must be defined here, together with 'description'
%End

%Terminals
    CtlCharNotWS

    LF   CR   HT   FF

    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
    _

    A    B    C    D    E    F    G    H    I    J    K    L    M
    N    O    P    Q    R    S    T    U    V    W    X    Y    Z

    0    1    2    3    4    5    6    7    8    9

    AfterASCII   ::= '\u0080..\ufffe'
    Space        ::= ' '
    LF           ::= NewLine
    CR           ::= Return
    HT           ::= HorizontalTab
    FF           ::= FormFeed
    DoubleQuote  ::= '"'
    SingleQuote  ::= "'"
    Percent      ::= '%'
    VerticalBar  ::= '|'
    Exclamation  ::= '!'
    AtSign       ::= '@'
    BackQuote    ::= '`'
    Tilde        ::= '~'
    Sharp        ::= '#'
    DollarSign   ::= '$'
    Ampersand    ::= '&'
    Caret        ::= '^'
    Colon        ::= ':'
    SemiColon    ::= ';'
    BackSlash    ::= '\'
    LeftBrace    ::= '{'
    RightBrace   ::= '}'
    LeftBracket  ::= '['
    RightBracket ::= ']'
    QuestionMark ::= '?'
    Comma        ::= ','
    Dot          ::= '.'
    LessThan     ::= '<'
    GreaterThan  ::= '>'
    Plus         ::= '+'
    Minus        ::= '-'
    Slash        ::= '/'
    Star         ::= '*'
    LeftParen    ::= '('
    RightParen   ::= ')'
    Equal        ::= '='
%End

%Start
    Token
%End

%Rules
    Token ::= identifier
        /.$BeginJava
                    checkForKeyWord();
          $EndJava
        ./
    Token ::= number
        /.$BeginJava
                    makeToken($_NUMBER);
          $EndJava
        ./
    Token ::= FLOAT
        /.$BeginJava
                    makeToken($_FLOAT);
          $EndJava
        ./
    Token ::= white
        /.$BeginJava
                    skipToken();
          $EndJava
        ./
    Token ::= slc
        /.$BeginJava
                    makeComment($_SINGLE_LINE_COMMENT);
          $EndJava
        ./
    Token ::= ';'
        /.$BeginJava
                    makeToken($_SEMICOLON);
          $EndJava
        ./

    Token ::= ','
        /.$BeginJava
                    makeToken($_COMMA);
          $EndJava
        ./

    Token ::= '+'
        /.$BeginJava
                    makeToken($_PLUS);
          $EndJava
        ./

    Token ::= '-'
        /.$BeginJava
                    makeToken($_MINUS);
          $EndJava
        ./

    Token ::= ':' '='
        /.$BeginJava
                    makeToken($_ASSIGN);
          $EndJava
        ./

    Token ::= '.'
        /.$BeginJava
                    makeToken($_DOT);
          $EndJava
        ./

    Token ::= '%'
        /.$BeginJava
                    makeToken($_PERCENT);
          $EndJava
        ./

    Token ::= '('
        /.$BeginJava
                    makeToken($_LEFTPAREN);
          $EndJava
        ./

    Token ::= ')'
        /.$BeginJava
                    makeToken($_RIGHTPAREN);
          $EndJava
        ./

    Token ::= '}'
        /.$BeginJava
                    makeToken($_RIGHTBRACE);
          $EndJava
        ./
        
    Token ::= '{'
        /.$BeginJava
                    makeToken($_LEFTBRACE);
          $EndJava
        ./

    Token ::= ']'
        /.$BeginJava
                    makeToken($_RIGHTSQUARE);
          $EndJava
        ./

    Token ::= '['
        /.$BeginJava
                    makeToken($_LEFTSQUARE);
          $EndJava
        ./

    Token ::= '*'
        /.$BeginJava
                    makeToken($_TIMES);
          $EndJava
        ./

    Token ::= '/'
        /.$BeginJava
                    makeToken($_DIVIDE);
          $EndJava
        ./

    Token ::= '>'
        /.$BeginJava
                    makeToken($_GREATER);
          $EndJava
        ./

    Token ::= '<'
        /.$BeginJava
                    makeToken($_LESS);
          $EndJava
        ./

    Token ::= '=' '='
        /.$BeginJava
                    makeToken($_EQUAL);
          $EndJava
        ./

    Token ::= '!' '='
        /.$BeginJava
                    makeToken($_NOTEQUAL);
          $EndJava
        ./

    Token ::= '<' '>'
        /.$BeginJava
                    makeToken($_SMALLERGREATER);
          $EndJava
        ./

    Token ::= '-' '>'
        /.$BeginJava
                    makeToken($_RIGHTARROW);
          $EndJava
        ./

    Token ::= ':'
        /.$BeginJava
                    makeToken($_COLON);
          $EndJava
        ./

    Token ::= '?'
        /.$BeginJava
                    makeToken($_QUESTIONMARK);
          $EndJava
        ./

    Token ::= '='
        /.$BeginJava
                    makeToken($_IS);
          $EndJava
        ./

    Token ::= ':' ':'
        /.$BeginJava
                    makeToken($_COLONCOLON);
          $EndJava
        ./
        
    Token ::= '"' notQuotes '"'
        /.$BeginJava
                    makeToken($_STRING);
          $EndJava
        ./
        
    Token ::= '"' '"'
        /.$BeginJava
                    makeToken($_STRING);
          $EndJava
        ./
        
    Token ::= s e c t i o n white notEOLs
        /.$BeginJava
                    makeToken($_SECTIONCOMMENT);
          $EndJava
        ./
        
    Token ::= d e s c r i p t i o n white bracketComment
        /.$BeginJava
                    makeToken($_DESCRIPTIONCOMMENT);
          $EndJava
        ./
        
    Token ::= d e f i n e
        /.$BeginJava
                    makeToken($_define);
          $EndJava
        ./
        
    Token ::= n o t e white bracketComment
        /.$BeginJava
                    makeToken($_NOTECOMMENT);
          $EndJava
        ./

    identifier -> letter
                | identifier letter
                | identifier digit

    number ::= digit
             | number digit

    FLOAT ::= Decimal
            | Decimal Exponent
            | number Exponent
                    
    Exponent ::= LetterEe number
               | LetterEe '-' number
               | LetterEe '+' number

    LetterEe ::= 'e'
               | 'E'

    Decimal ::= '.' number
              | number '.'
              | number '.' number
    
    white ::= whiteChar
            | white whiteChar

    slc ::= '/' '/'
          | slc notEOL
    
    notEOLs ::= notEOL
              | notEOLs notEOL
    
    notQuotes ::= notQuote
                | notQuotes notQuote
    
    bracketComment ::= white '{' notBrackets '}'
                     | '{' notBrackets '}'

    notBrackets ::= notBracket
                  | notBrackets notBracket

    digit ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

    aA ::= a | A
    bB ::= b | B
    cC ::= c | C
    dD ::= d | D
    eE ::= e | E
    fF ::= f | F
    gG ::= g | G
    hH ::= h | H
    iI ::= i | I
    jJ ::= j | J
    kK ::= k | K
    lL ::= l | L
    mM ::= m | M
    nN ::= n | N
    oO ::= o | O
    pP ::= p | P
    qQ ::= q | Q
    rR ::= r | R
    sS ::= s | S
    tT ::= t | T
    uU ::= u | U
    vV ::= v | V
    wW ::= w | W
    xX ::= x | X
    yY ::= y | Y
    zZ ::= z | Z

    letter ::= aA | bB | cC | dD | eE | fF | gG | hH | iI | jJ | kK | lL | mM | nN | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ

    --any ::= letter | digit | Space | HT | FF | LF | CR

    whiteChar ::= Space | LF | CR | HT | FF

    --notWhite ::= letter | digit | special 

    specialNotQuote ::= '+' | '-' | '(' | ')' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'
    
    specialNotBracket ::=  '+' | '-' | '(' | ')' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$' | '"'
    
    special ::= specialNotQuote | '"'

    notEOL ::= letter | digit | special | Space | HT | FF
    
    notQuote ::= letter | digit | specialNotQuote | Space | HT | FF | LF | CR

    notBracket ::= letter | digit | specialNotBracket | Space | HT | FF | LF | CR

    --letterNotE ::= aA | bB | cC | dD | fF | E | gG | hH | iI | jJ | kK | lL | mM | nN | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ
    --letterNotN ::= aA | bB | cC | dD | eE | fF | gG | hH | iI | jJ | kK | lL | mM | N | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ
    --letterNotD ::= aA | bB | cC | D | eE | fF | gG | hH | iI | jJ | kK | lL | mM | nN | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ
    
    --notE ::= letterNotE | digit | special | Space | HT | FF | LF | CR
    --notN ::= letterNotN | digit | special | Space | HT | FF | LF | CR
    --notD ::= letterNotD | digit | special | Space | HT | FF | LF | CR
%End
