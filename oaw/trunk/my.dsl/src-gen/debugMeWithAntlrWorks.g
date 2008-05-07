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

(('(')

(temp_annotations=ruleAnnotations )

(')')
)?
)
;

ruleAnnotations  :
((
temp_Annotation=ruleAnnotation {$result=temp_Annotation;}
)

((',')

(
temp_Annotation=ruleAnnotation {$result=temp_Annotation;}
)
)*
)
;

ruleSort  :
        temp_simplesort=ruleSimpleSort 	|        temp_genericsort=ruleGenericSort 	;

ruleSimpleSort  :
(temp_name=RULE_ID )
;

ruleGenericSort  :
((temp_name=RULE_ID )

('<')

(temp_arguments=ruleSortArguments )

('>')
)
;

ruleSortArguments  :
((
temp_Sort=ruleSort {$result=temp_Sort;}
)

((',')

(
temp_Sort=ruleSort {$result=temp_Sort;}
)
)*
)
;

ruleAnnotation  :
(temp_name=RULE_ID )
;

ruleTemplateDefinition  :
(('define')

(temp_modifiers=ruleModifier )*

(temp_name=RULE_ID )

('(')

(temp_arguments=ruleFormalArgs )?

(')')

('{')

(temp_elements=ruleTemplateElement )*

('}')
)
;

ruleFormalArgs  :
((
temp_FormalArg=ruleFormalArg {$result=temp_FormalArg;}
)

((',')

(
temp_FormalArg=ruleFormalArg {$result=temp_FormalArg;}
)
)*
)
;

ruleFormalArg  :
((temp_name=RULE_ID )

(':')

(temp_type=ruleSort )
)
;

ruleModifier  :
        temp_pagemodifier=rulePageModifier 	;

rulePageModifier  :
('page')
;

ruleTemplateElement  :
        temp_templatecall=ruleTemplateCall 	|        temp_templatedefinition=ruleTemplateDefinition 	|        temp_stringliteral=ruleStringLiteral 	|        temp_fortemplate=ruleForTemplate 	;

ruleForTemplate  :
(('for')

('(')

(temp_iterator=ruleVar )

(':')

(temp_type=ruleSort )

('in')

(temp_source=ruleExp )

(')')

('{')

(temp_elements=ruleTemplateElement )*

('}')
)
;

ruleStringLiteral  :
(temp_value=RULE_STRING )
;

ruleTemplateCall  :
((temp_name=RULE_ID
)

(('(')

(temp_arguments=ruleArguments )

(')')
)?

(('{')

(temp_elements=ruleTemplateElement )*

('}')
)?
)
;

ruleExp  :
        temp_fieldaccess=ruleFieldAccess 	;

ruleFieldAccess  :
((temp_target=rulePrimaryExp )

(('.')

(temp_fields=RULE_ID )
)*
)
;

rulePrimaryExp  :
        temp_parenexp=ruleParenExp 	|        temp_var=ruleVar 	|        temp_stringliteral=ruleStringLiteral 	|        temp_funcall=ruleFunCall 	;

ruleVar  :
(temp_name=RULE_ID )
;

ruleParenExp  :
(('(')

(
temp_Exp=ruleExp {$result=temp_Exp;}
)

(')')
)
;

ruleFunCall  :
((temp_function=ruleVar )

('(')

(temp_arguments=ruleArguments )

(')')
)
;

ruleArguments  :
((temp_expressions=ruleExp )

((',')

(temp_expressions=ruleExp )
)*
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

