package AST;

import AST.Parser.Terminals;
import java.io.*;

%%

%public 
%final 
%class Scanner
%extends beaver.Scanner

%type beaver.Symbol 
%function nextToken 
%yylexthrow beaver.Scanner.Exception

%unicode
%line %column

%{
  StringBuffer strbuf = new StringBuffer(128);

  private beaver.Symbol sym(short id) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, len(), str());
  }

  private beaver.Symbol sym(short id, String value) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, len(), value);
  }

  private String str() { return yytext(); }
  private int len() { return yylength(); }

  private void error(String msg) throws beaver.Scanner.Exception {
    throw new beaver.Scanner.Exception(yyline + 1, yycolumn + 1, msg);
  }
%}

LineTerminator = \n|\r|\r\n
InputCharacter = [^\r\n]

WhiteSpace = [ ] | \t | \f | {LineTerminator}

Comment = {TraditionalComment}
        | {EndOfLineComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/" | "/*" "*"+ [^/*] ~"*/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?

Identifier = [:jletter:][:jletterdigit:]*

%%

<YYINITIAL> { {WhiteSpace} { } }

<YYINITIAL> { {Comment} { } }

<YYINITIAL> {
  "module" { return sym(Terminals.MODULE); }
  "entity" { return sym(Terminals.ENTITY); }
  "extend" { return sym(Terminals.EXTEND); }
  "imports" { return sym(Terminals.IMPORTS); }
  "section" { return sym(Terminals.SECTION); }
  "inverse" { return sym(Terminals.INVERSE); }
  "->" { return sym(Terminals.ARROW); }
  "::" { return sym(Terminals.COLONS); }
  "{" { return sym(Terminals.LBRACE); }
  "}" { return sym(Terminals.RBRACE); }
  "<" { return sym(Terminals.LT); }
  ">" { return sym(Terminals.GT); }
  "=" { return sym(Terminals.EQ); }
  "." { return sym(Terminals.DOT); }
  "," { return sym(Terminals.COMMA); }
  "(" { return sym(Terminals.LPAREN); }
  ")" { return sym(Terminals.RPAREN); }

}

<YYINITIAL> {
  {Identifier}                   { return sym(Terminals.IDENTIFIER); }
}

// fall through errors
.|\n                             { error("illegal character \""+str()+ "\""); }
<<EOF>>                          { return sym(Terminals.EOF); }
