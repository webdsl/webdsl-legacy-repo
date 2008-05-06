grammar debugMeWithAntlrWorks;
 options{backtrack=true; memoize=true;} 



parse :
	 result=ruleApplication EOF
;

ruleApplication  :
(('application')

(temp_name=RULE_ID )

(temp_sections=ruleSection )*
)
;

ruleSection  :
(('section')

(temp_name=RULE_ID )

(temp_definitions=ruleDefinition )*
)
;

ruleDefinition  :
        temp_entity=ruleEntity 	|        temp_templatedefinition=ruleTemplateDefinition 	;

ruleEntity  :
(('entity')

(temp_name=RULE_ID )

('{')

(temp_properties=ruleProperty )*

('}')
)
;

ruleProperty  :
((temp_name=RULE_ID )

(':')

(temp_type=ruleSort )

('(')

(temp_annotations=ruleAnnotation )*

(')')
)
;

ruleSort  :
(temp_name=RULE_ID )
;

ruleAnnotation  :
(temp_name=RULE_ID )
;

ruleTemplateDefinition  :
(('define')

(temp_modifiers=ruleModifier )*

(temp_name=RULE_ID )

('(')

(temp_arguments=ruleFormalArg )*

(')')

('{')

(temp_elements=ruleTemplateElement )*

('}')
)
;

ruleFormalArg  :
((temp_name=RULE_ID )

(':')

(
temp_Sort=ruleSort {$result=temp_Sort;}
)
)
;

ruleModifier  :
        temp_pagemodifier=rulePageModifier 	;

rulePageModifier  :
('page')
;

ruleTemplateElement  :
        temp_templatecall=ruleTemplateCall 	|        temp_templatedefinition=ruleTemplateDefinition 	|        temp_stringliteral=ruleStringLiteral 	;

ruleStringLiteral  :
(RULE_STRING)
;

ruleTemplateCall  :
((temp_name=RULE_ID )

('(')

(temp_arguments=ruleExp )*

(')')

('{')

(temp_elements=ruleTemplateElement )*

('}')
)
;

ruleExp  :
        temp_fieldaccess=ruleFieldAccess 	|        temp_primaryexp=rulePrimaryExp 	;

ruleVar  :
(temp_name=RULE_ID )
;

rulePrimaryExp  :
((('(')

(
temp_Exp=ruleExp {$result=temp_Exp;}
)

(')')
)
	|
(
temp_Var=ruleVar {$result=temp_Var;}
)
	|
(
temp_StringLiteral=ruleStringLiteral {$result=temp_StringLiteral;}
)
)
;

ruleFieldAccess  :
((
temp_PrimaryExp=rulePrimaryExp {$result=temp_PrimaryExp;}
)

('.')

(temp_field=RULE_ID )
)
;

RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

RULE_INT :

	 ('-')?('0'..'9')+
	 
;

RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

