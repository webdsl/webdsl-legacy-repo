package AST;

import beaver.Symbol;
import beaver.Scanner;
import AST.WebDSLParser.Terminals;

%%

%public
%final
%class WebDSLScanner
%extends Scanner
%unicode
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%line
%column

%{
  private Symbol sym(short id) {
    return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

// Helper Definitions

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]
Comment = "//" {InputCharacter}* {LineTerminator}?

Identifier = [:letter:]([:letter:] | [:digit:])*

%% // Rules

"module"        { return sym(Terminals.MODULE); }
"entity"        { return sym(Terminals.ENTITY); }
"define"        { return sym(Terminals.DEFINE); }
"for"           { return sym(Terminals.FOR); }
//"navigate"      { return sym(Terminals.NAVIGATE); }
//"return"        { return sym(Terminals.RETURN); }

"in"            { return sym(Terminals.IN); }
"var"           { return sym(Terminals.VAR); }
"action"        { return sym(Terminals.ACTION); }
//"submit"        { return sym(Terminals.SUBMIT); }
"elements"      { return sym(Terminals.ELEMENTS); }

"output"        { return sym(Terminals.OUTPUT); }
"address"       { return sym(Terminals.ADDRESS); }
"binding_scope" { return sym(Terminals.BINDING_SCOPE); }
"accept_bind"   { return sym(Terminals.ACCEPT_BIND); }
"link_page"     { return sym(Terminals.LINK_PAGE); }
"redirect"      { return sym(Terminals.REDIRECT); }
//"perform"       { return sym(Terminals.PERFORM); }
"asexp"         { return sym(Terminals.AS_EXP); }

"do"          { return sym(Terminals.DO); }
"if"          { return sym(Terminals.IF); }
"then"        { return sym(Terminals.THEN); }
"else"        { return sym(Terminals.ELSE); }

":="          { return sym(Terminals.ASSIGN); }
"="           { return sym(Terminals.EQUAL); }
":"           { return sym(Terminals.COLON); }
"<"           { return sym(Terminals.LT); }
">"           { return sym(Terminals.GT); }

"page"        { return sym(Terminals.PAGE); }
//"id"          { return sym(Terminals.PROP_ID); }
//"name"        { return sym(Terminals.NAME); }
"inverse"     { return sym(Terminals.INVERSE); }
"Set"         { return sym(Terminals.SET); }

// "true"        { return sym(Terminals.BOOLEAN_LITERAL); }
// "false"       { return sym(Terminals.BOOLEAN_LITERAL); }

"("           { return sym(Terminals.LPAREN); }
")"           { return sym(Terminals.RPAREN); }
"{"           { return sym(Terminals.LBRACE); }
"}"           { return sym(Terminals.RBRACE); }
","           { return sym(Terminals.COMMA); }
";"           { return sym(Terminals.SEMICOLON); }
"."           { return sym(Terminals.DOT); }
"</"          { return sym(Terminals.LT_SLASH); }

"\"" [^\"]* "\""  { return sym(Terminals.STRING); }

{Comment}     { /* discard token */ }
{WhiteSpace}  { /* discard token */ }
{Identifier}  { return sym(Terminals.ID); }

.|\n          { throw new RuntimeException("Illegal character \""+yytext()+ "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>       { return sym(Terminals.EOF); }
