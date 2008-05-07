grammar mydsl;
 options{backtrack=true; memoize=true;} 

@lexer::header {
package org.example.dsl.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

@parser::header {
package org.example.dsl.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import org.example.dsl.MetaModelRegistration;

}
@lexer::members {
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

@parser::members {

	private Token getLastToken() {
		return input.LT(-1);
	}
	private Token getNextToken() {
		return input.LT(1);
	}

	private int line() {
		Token t = getNextToken();
		if (t==null)
			return 1;
		return t.getLine();
	}

	private int start() {
		Token t = getNextToken();
		if (t==null)
			return 0;
		if (t instanceof CommonToken) {
			return ((CommonToken)t).getStartIndex();
		}
		return t.getTokenIndex();
	}

	private int end() {
		Token t = getLastToken();
		if (t==null)
			return 1;
		if (t instanceof CommonToken) {
			return ((CommonToken)t).getStopIndex()+1;
		}
		return t.getTokenIndex();
	}

	protected Object convert(Object arg) {
		if (arg instanceof org.antlr.runtime.Token) {
			Token t = (Token) arg;
			String s = t.getText();
			if (t.getType() == mydslLexer.RULE_ID && s.startsWith("^")) {
				return s.substring(1);
			} else if (t.getType()==mydslLexer.RULE_STRING) {
				return s.substring(1,s.length()-1);
			} else if (t.getType()==mydslLexer.RULE_INT) {
				return Integer.valueOf(s);
			}
			return s;
		}
		return arg;
	}


	private EcoreModelFactory factory = new EcoreModelFactory(MetaModelRegistration.getEPackage());
    private ParseTreeManager ptm = new ParseTreeManager();
	private XtextFile xtextfile = MetaModelRegistration.getXtextFile();
	
	{
		
	}

	public ParseTreeManager getResult() {
		return ptm;
	}

	private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
	public List<ErrorMsg> getErrors() {
		return errors;
	}

	@Override
		public void reportError(RecognitionException e) {
		String msg = super.getErrorMessage(e,tokenNames);
		errors.add(AntlrUtil.create(msg,e,tokenNames));
			ptm.addError(msg, e);
			ptm.ruleFinished(null, end());
		}

}


parse returns [Node r]:
	 result=ruleApplication EOF
{ptm.ruleFinished(result,end());$r = ptm.getCurrent();};

ruleApplication returns [EObject result] :
			{
				$result = factory.create("", "Application");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(0)),line(),start());}'application'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(2)),line(),start());}temp_sections=ruleSection {factory.add($result,"sections",convert(temp_sections),false); ptm.ruleFinished(temp_sections,end()); }
)*
)
;

ruleSection returns [EObject result] :
			{
				$result = factory.create("", "Section");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}'section'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());}temp_definitions=ruleDefinition {factory.add($result,"definitions",convert(temp_definitions),false); ptm.ruleFinished(temp_definitions,end()); }
)*
)
;

ruleDefinition returns [EObject result] :
        temp_entity=ruleEntity {$result=temp_entity;}	|        temp_templatedefinition=ruleTemplateDefinition {$result=temp_templatedefinition;}	;

ruleEntity returns [EObject result] :
			{
				$result = factory.create("", "Entity");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());}'entity'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(3)),line(),start());}temp_properties=ruleProperty {factory.add($result,"properties",convert(temp_properties),false); ptm.ruleFinished(temp_properties,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(4)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleProperty returns [EObject result] :
			{
				$result = factory.create("", "Property");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());}':'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(2)),line(),start());}temp_type=ruleSort {factory.set($result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); }
)

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)).eContents().get(0)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)).eContents().get(1)),line(),start());}temp_annotations=ruleAnnotations {factory.set($result,"annotations",convert(temp_annotations),false); ptm.ruleFinished(temp_annotations,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)).eContents().get(2)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})
)?
)
;

ruleAnnotations returns [EObject result] :
			{
				$result = factory.create("", "Annotations");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());}
temp_Annotation=ruleAnnotation {$result=temp_Annotation;}
{ptm.ruleFinished(getLastToken(),end());})

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}','{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_Annotation=ruleAnnotation {$result=temp_Annotation;}
{ptm.ruleFinished(getLastToken(),end());})
)*
)
;

ruleSort returns [EObject result] :
        temp_simplesort=ruleSimpleSort {$result=temp_simplesort;}	|        temp_genericsort=ruleGenericSort {$result=temp_genericsort;}	;

ruleSimpleSort returns [EObject result] :
			{
				$result = factory.create("", "SimpleSort");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)
;

ruleGenericSort returns [EObject result] :
			{
				$result = factory.create("", "GenericSort");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)),line(),start());}'<'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)),line(),start());}temp_arguments=ruleSortArguments {factory.set($result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)),line(),start());}'>'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleSortArguments returns [EObject result] :
			{
				$result = factory.create("", "SortArguments");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(0)),line(),start());}
temp_Sort=ruleSort {$result=temp_Sort;}
{ptm.ruleFinished(getLastToken(),end());})

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}','{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_Sort=ruleSort {$result=temp_Sort;}
{ptm.ruleFinished(getLastToken(),end());})
)*
)
;

ruleAnnotation returns [EObject result] :
			{
				$result = factory.create("", "Annotation");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(10)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)
;

ruleTemplateDefinition returns [EObject result] :
			{
				$result = factory.create("", "TemplateDefinition");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(0)),line(),start());}'define'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(1)),line(),start());}temp_modifiers=ruleModifier {factory.add($result,"modifiers",convert(temp_modifiers),false); ptm.ruleFinished(temp_modifiers,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(2)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(3)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(4)),line(),start());}temp_arguments=ruleFormalArgs {factory.set($result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(5)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(6)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(7)),line(),start());}temp_elements=ruleTemplateElement {factory.add($result,"elements",convert(temp_elements),false); ptm.ruleFinished(temp_elements,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(8)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleFormalArgs returns [EObject result] :
			{
				$result = factory.create("", "FormalArgs");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(0)),line(),start());}
temp_FormalArg=ruleFormalArg {$result=temp_FormalArg;}
{ptm.ruleFinished(getLastToken(),end());})

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}','{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_FormalArg=ruleFormalArg {$result=temp_FormalArg;}
{ptm.ruleFinished(getLastToken(),end());})
)*
)
;

ruleFormalArg returns [EObject result] :
			{
				$result = factory.create("", "FormalArg");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());}':'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)),line(),start());}temp_type=ruleSort {factory.set($result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); }
)
)
;

ruleModifier returns [EObject result] :
        temp_pagemodifier=rulePageModifier {$result=temp_pagemodifier;}	;

rulePageModifier returns [EObject result] :
			{
				$result = factory.create("", "PageModifier");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)),line(),start());}'page'{ptm.ruleFinished(getLastToken(),end());})
;

ruleTemplateElement returns [EObject result] :
        temp_templatecall=ruleTemplateCall {$result=temp_templatecall;}	|        temp_templatedefinition=ruleTemplateDefinition {$result=temp_templatedefinition;}	|        temp_stringliteral=ruleStringLiteral {$result=temp_stringliteral;}	|        temp_fortemplate=ruleForTemplate {$result=temp_fortemplate;}	;

ruleForTemplate returns [EObject result] :
			{
				$result = factory.create("", "ForTemplate");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(0)),line(),start());}'for'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(1)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(2)),line(),start());}temp_iterator=ruleVar {factory.set($result,"iterator",convert(temp_iterator),false); ptm.ruleFinished(temp_iterator,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(3)),line(),start());}':'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(4)),line(),start());}temp_type=ruleSort {factory.set($result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(5)),line(),start());}'in'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(6)),line(),start());}temp_source=ruleExp {factory.set($result,"source",convert(temp_source),false); ptm.ruleFinished(temp_source,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(7)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(8)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(9)),line(),start());}temp_elements=ruleTemplateElement {factory.add($result,"elements",convert(temp_elements),false); ptm.ruleFinished(temp_elements,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(10)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleStringLiteral returns [EObject result] :
			{
				$result = factory.create("", "StringLiteral");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
;

ruleTemplateCall returns [EObject result] :
			{
				$result = factory.create("", "TemplateCall");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name=RULE_ID
 {factory.set($result,"name",convert(temp_name),true); ptm.ruleFinished(temp_name,end()); }
)

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_arguments=ruleArguments {factory.set($result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})
)?

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)).eContents().get(0)),line(),start());}'{'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)).eContents().get(1)),line(),start());}temp_elements=ruleTemplateElement {factory.add($result,"elements",convert(temp_elements),false); ptm.ruleFinished(temp_elements,end()); }
)*

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)).eContents().get(2)),line(),start());}'}'{ptm.ruleFinished(getLastToken(),end());})
)?
)
;

ruleExp returns [EObject result] :
        temp_fieldaccess=ruleFieldAccess {$result=temp_fieldaccess;}	;

ruleFieldAccess returns [EObject result] :
			{
				$result = factory.create("", "FieldAccess");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(0)),line(),start());}temp_target=rulePrimaryExp {factory.set($result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); }
)

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}'.'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_fields=RULE_ID {factory.add($result,"fields",convert(temp_fields),false); ptm.ruleFinished(temp_fields,end()); }
)
)*
)
;

rulePrimaryExp returns [EObject result] :
        temp_parenexp=ruleParenExp {$result=temp_parenexp;}	|        temp_var=ruleVar {$result=temp_var;}	|        temp_stringliteral=ruleStringLiteral {$result=temp_stringliteral;}	|        temp_funcall=ruleFunCall {$result=temp_funcall;}	;

ruleVar returns [EObject result] :
			{
				$result = factory.create("", "Var");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)
;

ruleParenExp returns [EObject result] :
			{
				$result = factory.create("", "ParenExp");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(0)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(1)),line(),start());}
temp_Exp=ruleExp {$result=temp_Exp;}
{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(2)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleFunCall returns [EObject result] :
			{
				$result = factory.create("", "FunCall");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(0)),line(),start());}temp_function=ruleVar {factory.set($result,"function",convert(temp_function),false); ptm.ruleFinished(temp_function,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(1)),line(),start());}'('{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(2)),line(),start());}temp_arguments=ruleArguments {factory.set($result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(3)),line(),start());}')'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleArguments returns [EObject result] :
			{
				$result = factory.create("", "Arguments");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(0)),line(),start());}temp_expressions=ruleExp {factory.add($result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); }
)

(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}','{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_expressions=ruleExp {factory.add($result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); }
)
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

