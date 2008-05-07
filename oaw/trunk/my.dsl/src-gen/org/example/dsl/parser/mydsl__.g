lexer grammar mydsl;
@members {
	 private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
	public List<ErrorMsg> getErrors() {
		return errors;
	}

	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
		String msg = super.getErrorMessage(e,tokenNames);
		errors.add(AntlrUtil.create(msg,e,tokenNames));
		return msg;
	}
}
@header {
package org.example.dsl.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

T10 : 'application' ;
T11 : 'section' ;
T12 : 'entity' ;
T13 : '{' ;
T14 : '}' ;
T15 : ':' ;
T16 : '(' ;
T17 : ')' ;
T18 : ',' ;
T19 : '<' ;
T20 : '>' ;
T21 : 'define' ;
T22 : 'page' ;
T23 : 'for' ;
T24 : 'in' ;
T25 : '.' ;

// $ANTLR src "..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g" 498
RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

// $ANTLR src "..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g" 504
RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

// $ANTLR src "..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g" 511
RULE_INT :

	 ('-')?('0'..'9')+
	 
;

// $ANTLR src "..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g" 517
RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g" 523
RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g" 529
RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

