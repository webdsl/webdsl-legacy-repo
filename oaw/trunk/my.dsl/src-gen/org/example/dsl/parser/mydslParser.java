// $ANTLR 3.0 ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g 2008-05-07 01:40:06

package org.example.dsl.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import org.example.dsl.MetaModelRegistration;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class mydslParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "'application'", "'section'", "'entity'", "'{'", "'}'", "':'", "'('", "')'", "'define'", "'page'", "'.'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=5;
    public static final int RULE_INT=6;
    public static final int RULE_WS=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=8;

        public mydslParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[34+1];
         }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g"; }



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




    // $ANTLR start parse
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:121:1: parse returns [Node r] : result= ruleApplication EOF ;
    public Node parse() throws RecognitionException {
        Node r = null;
        int parse_StartIndex = input.index();
        EObject result = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 1) ) { return r; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:122:3: (result= ruleApplication EOF )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:122:3: result= ruleApplication EOF
            {
            pushFollow(FOLLOW_ruleApplication_in_parse67);
            result=ruleApplication();
            _fsp--;
            if (failed) return r;
            match(input,EOF,FOLLOW_EOF_in_parse69); if (failed) return r;
            if ( backtracking==0 ) {
              ptm.ruleFinished(result,end());r = ptm.getCurrent();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 1, parse_StartIndex); }
        }
        return r;
    }
    // $ANTLR end parse


    // $ANTLR start ruleApplication
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:125:1: ruleApplication returns [EObject result] : ( ( 'application' ) (temp_name= RULE_ID ) (temp_sections= ruleSection )* ) ;
    public EObject ruleApplication() throws RecognitionException {
        EObject result = null;
        int ruleApplication_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_sections = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 2) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:126:4: ( ( ( 'application' ) (temp_name= RULE_ID ) (temp_sections= ruleSection )* ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:126:4: ( ( 'application' ) (temp_name= RULE_ID ) (temp_sections= ruleSection )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Application");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:130:1: ( ( 'application' ) (temp_name= RULE_ID ) (temp_sections= ruleSection )* )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:130:2: ( 'application' ) (temp_name= RULE_ID ) (temp_sections= ruleSection )*
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:130:2: ( 'application' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:130:3: 'application'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,10,FOLLOW_10_in_ruleApplication91); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:132:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:132:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleApplication100); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:135:1: (temp_sections= ruleSection )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:135:2: temp_sections= ruleSection
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleSection_in_ruleApplication111);
            	    temp_sections=ruleSection();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"sections",convert(temp_sections),false); ptm.ruleFinished(temp_sections,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 2, ruleApplication_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleApplication


    // $ANTLR start ruleSection
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:140:1: ruleSection returns [EObject result] : ( ( 'section' ) (temp_name= RULE_ID ) (temp_definitions= ruleDefinition )* ) ;
    public EObject ruleSection() throws RecognitionException {
        EObject result = null;
        int ruleSection_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_definitions = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 3) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:141:4: ( ( ( 'section' ) (temp_name= RULE_ID ) (temp_definitions= ruleDefinition )* ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:141:4: ( ( 'section' ) (temp_name= RULE_ID ) (temp_definitions= ruleDefinition )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Section");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:145:1: ( ( 'section' ) (temp_name= RULE_ID ) (temp_definitions= ruleDefinition )* )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:145:2: ( 'section' ) (temp_name= RULE_ID ) (temp_definitions= ruleDefinition )*
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:145:2: ( 'section' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:145:3: 'section'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleSection139); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:147:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:147:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSection148); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:150:1: (temp_definitions= ruleDefinition )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==12||LA2_0==18) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:150:2: temp_definitions= ruleDefinition
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleDefinition_in_ruleSection159);
            	    temp_definitions=ruleDefinition();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"definitions",convert(temp_definitions),false); ptm.ruleFinished(temp_definitions,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 3, ruleSection_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSection


    // $ANTLR start ruleDefinition
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:155:1: ruleDefinition returns [EObject result] : (temp_entity= ruleEntity | temp_templatedefinition= ruleTemplateDefinition );
    public EObject ruleDefinition() throws RecognitionException {
        EObject result = null;
        int ruleDefinition_StartIndex = input.index();
        EObject temp_entity = null;

        EObject temp_templatedefinition = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 4) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:156:9: (temp_entity= ruleEntity | temp_templatedefinition= ruleTemplateDefinition )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==12) ) {
                alt3=1;
            }
            else if ( (LA3_0==18) ) {
                alt3=2;
            }
            else {
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("155:1: ruleDefinition returns [EObject result] : (temp_entity= ruleEntity | temp_templatedefinition= ruleTemplateDefinition );", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:156:9: temp_entity= ruleEntity
                    {
                    pushFollow(FOLLOW_ruleEntity_in_ruleDefinition189);
                    temp_entity=ruleEntity();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_entity;
                    }

                    }
                    break;
                case 2 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:156:64: temp_templatedefinition= ruleTemplateDefinition
                    {
                    pushFollow(FOLLOW_ruleTemplateDefinition_in_ruleDefinition204);
                    temp_templatedefinition=ruleTemplateDefinition();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_templatedefinition;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 4, ruleDefinition_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleDefinition


    // $ANTLR start ruleEntity
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:158:1: ruleEntity returns [EObject result] : ( ( 'entity' ) (temp_name= RULE_ID ) ( '{' ) (temp_properties= ruleProperty )* ( '}' ) ) ;
    public EObject ruleEntity() throws RecognitionException {
        EObject result = null;
        int ruleEntity_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_properties = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 5) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:159:4: ( ( ( 'entity' ) (temp_name= RULE_ID ) ( '{' ) (temp_properties= ruleProperty )* ( '}' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:159:4: ( ( 'entity' ) (temp_name= RULE_ID ) ( '{' ) (temp_properties= ruleProperty )* ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Entity");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:163:1: ( ( 'entity' ) (temp_name= RULE_ID ) ( '{' ) (temp_properties= ruleProperty )* ( '}' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:163:2: ( 'entity' ) (temp_name= RULE_ID ) ( '{' ) (temp_properties= ruleProperty )* ( '}' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:163:2: ( 'entity' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:163:3: 'entity'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleEntity227); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:165:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:165:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntity236); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:168:1: ( '{' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:168:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,13,FOLLOW_13_in_ruleEntity245); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:170:1: (temp_properties= ruleProperty )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_ID) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:170:2: temp_properties= ruleProperty
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(3)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleProperty_in_ruleEntity254);
            	    temp_properties=ruleProperty();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"properties",convert(temp_properties),false); ptm.ruleFinished(temp_properties,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:173:1: ( '}' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:173:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,14,FOLLOW_14_in_ruleEntity264); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 5, ruleEntity_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleEntity


    // $ANTLR start ruleProperty
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:177:1: ruleProperty returns [EObject result] : ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( '(' ) (temp_annotations= ruleAnnotation )* ( ')' ) ) ;
    public EObject ruleProperty() throws RecognitionException {
        EObject result = null;
        int ruleProperty_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_type = null;

        EObject temp_annotations = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:178:4: ( ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( '(' ) (temp_annotations= ruleAnnotation )* ( ')' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:178:4: ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( '(' ) (temp_annotations= ruleAnnotation )* ( ')' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Property");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:182:1: ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( '(' ) (temp_annotations= ruleAnnotation )* ( ')' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:182:2: (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( '(' ) (temp_annotations= ruleAnnotation )* ( ')' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:182:2: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:182:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleProperty291); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:185:1: ( ':' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:185:2: ':'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,15,FOLLOW_15_in_ruleProperty300); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:187:1: (temp_type= ruleSort )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:187:2: temp_type= ruleSort
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSort_in_ruleProperty309);
            temp_type=ruleSort();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:190:1: ( '(' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:190:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleProperty318); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:192:1: (temp_annotations= ruleAnnotation )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ID) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:192:2: temp_annotations= ruleAnnotation
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(4)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleAnnotation_in_ruleProperty327);
            	    temp_annotations=ruleAnnotation();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"annotations",convert(temp_annotations),false); ptm.ruleFinished(temp_annotations,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:195:1: ( ')' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:195:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleProperty337); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 6, ruleProperty_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleProperty


    // $ANTLR start ruleSort
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:199:1: ruleSort returns [EObject result] : (temp_name= RULE_ID ) ;
    public EObject ruleSort() throws RecognitionException {
        EObject result = null;
        int ruleSort_StartIndex = input.index();
        Token temp_name=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 7) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:200:4: ( (temp_name= RULE_ID ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:200:4: (temp_name= RULE_ID )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Sort");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:204:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:204:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSort363); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 7, ruleSort_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSort


    // $ANTLR start ruleAnnotation
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:208:1: ruleAnnotation returns [EObject result] : (temp_name= RULE_ID ) ;
    public EObject ruleAnnotation() throws RecognitionException {
        EObject result = null;
        int ruleAnnotation_StartIndex = input.index();
        Token temp_name=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:209:4: ( (temp_name= RULE_ID ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:209:4: (temp_name= RULE_ID )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Annotation");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:213:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:213:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAnnotation389); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 8, ruleAnnotation_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleAnnotation


    // $ANTLR start ruleTemplateDefinition
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:217:1: ruleTemplateDefinition returns [EObject result] : ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArg )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) ;
    public EObject ruleTemplateDefinition() throws RecognitionException {
        EObject result = null;
        int ruleTemplateDefinition_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_modifiers = null;

        EObject temp_arguments = null;

        EObject temp_elements = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:218:4: ( ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArg )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:218:4: ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArg )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "TemplateDefinition");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:222:1: ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArg )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:222:2: ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArg )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:222:2: ( 'define' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:222:3: 'define'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,18,FOLLOW_18_in_ruleTemplateDefinition414); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:224:1: (temp_modifiers= ruleModifier )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==19) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:224:2: temp_modifiers= ruleModifier
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleModifier_in_ruleTemplateDefinition423);
            	    temp_modifiers=ruleModifier();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"modifiers",convert(temp_modifiers),false); ptm.ruleFinished(temp_modifiers,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:227:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:227:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTemplateDefinition435); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:230:1: ( '(' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:230:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleTemplateDefinition444); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:232:1: (temp_arguments= ruleFormalArg )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:232:2: temp_arguments= ruleFormalArg
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(4)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleFormalArg_in_ruleTemplateDefinition453);
            	    temp_arguments=ruleFormalArg();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:235:1: ( ')' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:235:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleTemplateDefinition463); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:237:1: ( '{' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:237:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(6)),line(),start());
            }
            match(input,13,FOLLOW_13_in_ruleTemplateDefinition470); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:239:1: (temp_elements= ruleTemplateElement )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=RULE_ID && LA8_0<=RULE_STRING)||LA8_0==18) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:239:2: temp_elements= ruleTemplateElement
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(7)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleTemplateElement_in_ruleTemplateDefinition479);
            	    temp_elements=ruleTemplateElement();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"elements",convert(temp_elements),false); ptm.ruleFinished(temp_elements,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:242:1: ( '}' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:242:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(8)),line(),start());
            }
            match(input,14,FOLLOW_14_in_ruleTemplateDefinition489); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 9, ruleTemplateDefinition_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTemplateDefinition


    // $ANTLR start ruleFormalArg
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:246:1: ruleFormalArg returns [EObject result] : ( (temp_name= RULE_ID ) ( ':' ) (temp_Sort= ruleSort ) ) ;
    public EObject ruleFormalArg() throws RecognitionException {
        EObject result = null;
        int ruleFormalArg_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_Sort = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 10) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:247:4: ( ( (temp_name= RULE_ID ) ( ':' ) (temp_Sort= ruleSort ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:247:4: ( (temp_name= RULE_ID ) ( ':' ) (temp_Sort= ruleSort ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "FormalArg");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:251:1: ( (temp_name= RULE_ID ) ( ':' ) (temp_Sort= ruleSort ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:251:2: (temp_name= RULE_ID ) ( ':' ) (temp_Sort= ruleSort )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:251:2: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:251:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFormalArg516); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:254:1: ( ':' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:254:2: ':'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,15,FOLLOW_15_in_ruleFormalArg525); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:256:1: (temp_Sort= ruleSort )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:256:2: temp_Sort= ruleSort
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSort_in_ruleFormalArg535);
            temp_Sort=ruleSort();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_Sort;
            }
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 10, ruleFormalArg_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFormalArg


    // $ANTLR start ruleModifier
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:262:1: ruleModifier returns [EObject result] : temp_pagemodifier= rulePageModifier ;
    public EObject ruleModifier() throws RecognitionException {
        EObject result = null;
        int ruleModifier_StartIndex = input.index();
        EObject temp_pagemodifier = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 11) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:263:9: (temp_pagemodifier= rulePageModifier )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:263:9: temp_pagemodifier= rulePageModifier
            {
            pushFollow(FOLLOW_rulePageModifier_in_ruleModifier565);
            temp_pagemodifier=rulePageModifier();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_pagemodifier;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 11, ruleModifier_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleModifier


    // $ANTLR start rulePageModifier
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:265:1: rulePageModifier returns [EObject result] : ( 'page' ) ;
    public EObject rulePageModifier() throws RecognitionException {
        EObject result = null;
        int rulePageModifier_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 12) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:266:4: ( ( 'page' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:266:4: ( 'page' )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "PageModifier");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:270:1: ( 'page' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:270:2: 'page'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(10)).eContents().get(1)),line(),start());
            }
            match(input,19,FOLLOW_19_in_rulePageModifier587); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 12, rulePageModifier_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePageModifier


    // $ANTLR start ruleTemplateElement
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:273:1: ruleTemplateElement returns [EObject result] : (temp_templatecall= ruleTemplateCall | temp_templatedefinition= ruleTemplateDefinition | temp_stringliteral= ruleStringLiteral );
    public EObject ruleTemplateElement() throws RecognitionException {
        EObject result = null;
        int ruleTemplateElement_StartIndex = input.index();
        EObject temp_templatecall = null;

        EObject temp_templatedefinition = null;

        EObject temp_stringliteral = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 13) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:274:9: (temp_templatecall= ruleTemplateCall | temp_templatedefinition= ruleTemplateDefinition | temp_stringliteral= ruleStringLiteral )
            int alt9=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt9=1;
                }
                break;
            case 18:
                {
                alt9=2;
                }
                break;
            case RULE_STRING:
                {
                alt9=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("273:1: ruleTemplateElement returns [EObject result] : (temp_templatecall= ruleTemplateCall | temp_templatedefinition= ruleTemplateDefinition | temp_stringliteral= ruleStringLiteral );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:274:9: temp_templatecall= ruleTemplateCall
                    {
                    pushFollow(FOLLOW_ruleTemplateCall_in_ruleTemplateElement612);
                    temp_templatecall=ruleTemplateCall();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_templatecall;
                    }

                    }
                    break;
                case 2 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:274:82: temp_templatedefinition= ruleTemplateDefinition
                    {
                    pushFollow(FOLLOW_ruleTemplateDefinition_in_ruleTemplateElement627);
                    temp_templatedefinition=ruleTemplateDefinition();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_templatedefinition;
                    }

                    }
                    break;
                case 3 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:274:173: temp_stringliteral= ruleStringLiteral
                    {
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleTemplateElement642);
                    temp_stringliteral=ruleStringLiteral();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_stringliteral;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 13, ruleTemplateElement_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTemplateElement


    // $ANTLR start ruleStringLiteral
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:276:1: ruleStringLiteral returns [EObject result] : ( RULE_STRING ) ;
    public EObject ruleStringLiteral() throws RecognitionException {
        EObject result = null;
        int ruleStringLiteral_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 14) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:277:4: ( ( RULE_STRING ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:277:4: ( RULE_STRING )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "StringLiteral");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:281:1: ( RULE_STRING )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:281:2: RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)),line(),start());
            }
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral664); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 14, ruleStringLiteral_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleStringLiteral


    // $ANTLR start ruleTemplateCall
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:284:1: ruleTemplateCall returns [EObject result] : ( (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleExp )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) ;
    public EObject ruleTemplateCall() throws RecognitionException {
        EObject result = null;
        int ruleTemplateCall_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_arguments = null;

        EObject temp_elements = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 15) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:285:4: ( ( (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleExp )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:285:4: ( (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleExp )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "TemplateCall");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:289:1: ( (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleExp )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:289:2: (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleExp )* ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:289:2: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:289:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTemplateCall689); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:292:1: ( '(' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:292:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleTemplateCall698); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:294:1: (temp_arguments= ruleExp )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=RULE_ID && LA10_0<=RULE_STRING)||LA10_0==16) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:294:2: temp_arguments= ruleExp
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleExp_in_ruleTemplateCall707);
            	    temp_arguments=ruleExp();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:297:1: ( ')' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:297:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleTemplateCall717); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:299:1: ( '{' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:299:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,13,FOLLOW_13_in_ruleTemplateCall724); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:301:1: (temp_elements= ruleTemplateElement )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=RULE_ID && LA11_0<=RULE_STRING)||LA11_0==18) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:301:2: temp_elements= ruleTemplateElement
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(5)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleTemplateElement_in_ruleTemplateCall733);
            	    temp_elements=ruleTemplateElement();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"elements",convert(temp_elements),false); ptm.ruleFinished(temp_elements,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:304:1: ( '}' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:304:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(6)),line(),start());
            }
            match(input,14,FOLLOW_14_in_ruleTemplateCall743); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 15, ruleTemplateCall_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTemplateCall


    // $ANTLR start ruleExp
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:308:1: ruleExp returns [EObject result] : (temp_fieldaccess= ruleFieldAccess | temp_primaryexp= rulePrimaryExp );
    public EObject ruleExp() throws RecognitionException {
        EObject result = null;
        int ruleExp_StartIndex = input.index();
        EObject temp_fieldaccess = null;

        EObject temp_primaryexp = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 16) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:309:9: (temp_fieldaccess= ruleFieldAccess | temp_primaryexp= rulePrimaryExp )
            int alt12=2;
            switch ( input.LA(1) ) {
            case 16:
                {
                int LA12_1 = input.LA(2);

                if ( (synpred13()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("308:1: ruleExp returns [EObject result] : (temp_fieldaccess= ruleFieldAccess | temp_primaryexp= rulePrimaryExp );", 12, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ID:
                {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==20) ) {
                    alt12=1;
                }
                else if ( (LA12_2==EOF||(LA12_2>=RULE_ID && LA12_2<=RULE_STRING)||(LA12_2>=16 && LA12_2<=17)) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("308:1: ruleExp returns [EObject result] : (temp_fieldaccess= ruleFieldAccess | temp_primaryexp= rulePrimaryExp );", 12, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                int LA12_3 = input.LA(2);

                if ( (LA12_3==20) ) {
                    alt12=1;
                }
                else if ( (LA12_3==EOF||(LA12_3>=RULE_ID && LA12_3<=RULE_STRING)||(LA12_3>=16 && LA12_3<=17)) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("308:1: ruleExp returns [EObject result] : (temp_fieldaccess= ruleFieldAccess | temp_primaryexp= rulePrimaryExp );", 12, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("308:1: ruleExp returns [EObject result] : (temp_fieldaccess= ruleFieldAccess | temp_primaryexp= rulePrimaryExp );", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:309:9: temp_fieldaccess= ruleFieldAccess
                    {
                    pushFollow(FOLLOW_ruleFieldAccess_in_ruleExp770);
                    temp_fieldaccess=ruleFieldAccess();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_fieldaccess;
                    }

                    }
                    break;
                case 2 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:309:79: temp_primaryexp= rulePrimaryExp
                    {
                    pushFollow(FOLLOW_rulePrimaryExp_in_ruleExp785);
                    temp_primaryexp=rulePrimaryExp();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_primaryexp;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 16, ruleExp_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleExp


    // $ANTLR start ruleVar
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:311:1: ruleVar returns [EObject result] : (temp_name= RULE_ID ) ;
    public EObject ruleVar() throws RecognitionException {
        EObject result = null;
        int ruleVar_StartIndex = input.index();
        Token temp_name=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 17) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:312:4: ( (temp_name= RULE_ID ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:312:4: (temp_name= RULE_ID )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Var");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:316:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:316:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleVar809); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 17, ruleVar_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleVar


    // $ANTLR start rulePrimaryExp
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:320:1: rulePrimaryExp returns [EObject result] : ( ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) ) | (temp_Var= ruleVar ) | (temp_StringLiteral= ruleStringLiteral ) ) ;
    public EObject rulePrimaryExp() throws RecognitionException {
        EObject result = null;
        int rulePrimaryExp_StartIndex = input.index();
        EObject temp_Exp = null;

        EObject temp_Var = null;

        EObject temp_StringLiteral = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 18) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:321:4: ( ( ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) ) | (temp_Var= ruleVar ) | (temp_StringLiteral= ruleStringLiteral ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:321:4: ( ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) ) | (temp_Var= ruleVar ) | (temp_StringLiteral= ruleStringLiteral ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "PrimaryExp");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:325:1: ( ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) ) | (temp_Var= ruleVar ) | (temp_StringLiteral= ruleStringLiteral ) )
            int alt13=3;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt13=1;
                }
                break;
            case RULE_ID:
                {
                alt13=2;
                }
                break;
            case RULE_STRING:
                {
                alt13=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("325:1: ( ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) ) | (temp_Var= ruleVar ) | (temp_StringLiteral= ruleStringLiteral ) )", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:325:2: ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) )
                    {
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:325:2: ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:325:3: ( '(' ) (temp_Exp= ruleExp ) ( ')' )
                    {
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:325:3: ( '(' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:325:4: '('
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(16)).eContents().get(1)).eContents().get(0)).eContents().get(0)),line(),start());
                    }
                    match(input,16,FOLLOW_16_in_rulePrimaryExp835); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:327:1: (temp_Exp= ruleExp )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:327:2: temp_Exp= ruleExp
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(16)).eContents().get(1)).eContents().get(0)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleExp_in_rulePrimaryExp845);
                    temp_Exp=ruleExp();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_Exp;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:331:1: ( ')' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:331:2: ')'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(16)).eContents().get(1)).eContents().get(0)).eContents().get(2)),line(),start());
                    }
                    match(input,17,FOLLOW_17_in_rulePrimaryExp855); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:334:1: (temp_Var= ruleVar )
                    {
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:334:1: (temp_Var= ruleVar )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:334:2: temp_Var= ruleVar
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(16)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleVar_in_rulePrimaryExp869);
                    temp_Var=ruleVar();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_Var;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;
                case 3 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:338:1: (temp_StringLiteral= ruleStringLiteral )
                    {
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:338:1: (temp_StringLiteral= ruleStringLiteral )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:338:2: temp_StringLiteral= ruleStringLiteral
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(16)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_rulePrimaryExp884);
                    temp_StringLiteral=ruleStringLiteral();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_StringLiteral;
                    }
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 18, rulePrimaryExp_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePrimaryExp


    // $ANTLR start ruleFieldAccess
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:344:1: ruleFieldAccess returns [EObject result] : ( (temp_PrimaryExp= rulePrimaryExp ) ( '.' ) (temp_field= RULE_ID ) ) ;
    public EObject ruleFieldAccess() throws RecognitionException {
        EObject result = null;
        int ruleFieldAccess_StartIndex = input.index();
        Token temp_field=null;
        EObject temp_PrimaryExp = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 19) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:345:4: ( ( (temp_PrimaryExp= rulePrimaryExp ) ( '.' ) (temp_field= RULE_ID ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:345:4: ( (temp_PrimaryExp= rulePrimaryExp ) ( '.' ) (temp_field= RULE_ID ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "FieldAccess");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:349:1: ( (temp_PrimaryExp= rulePrimaryExp ) ( '.' ) (temp_field= RULE_ID ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:349:2: (temp_PrimaryExp= rulePrimaryExp ) ( '.' ) (temp_field= RULE_ID )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:349:2: (temp_PrimaryExp= rulePrimaryExp )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:349:3: temp_PrimaryExp= rulePrimaryExp
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_rulePrimaryExp_in_ruleFieldAccess915);
            temp_PrimaryExp=rulePrimaryExp();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_PrimaryExp;
            }
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:353:1: ( '.' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:353:2: '.'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,20,FOLLOW_20_in_ruleFieldAccess925); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:355:1: (temp_field= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:355:2: temp_field= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_field=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFieldAccess934); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"field",convert(temp_field),false); ptm.ruleFinished(temp_field,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 19, ruleFieldAccess_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFieldAccess

    // $ANTLR start synpred13
    public void synpred13_fragment() throws RecognitionException {   
        // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:309:9: ( ruleFieldAccess )
        // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:309:9: ruleFieldAccess
        {
        pushFollow(FOLLOW_ruleFieldAccess_in_synpred13770);
        ruleFieldAccess();
        _fsp--;
        if (failed) return ;

        }
    }
    // $ANTLR end synpred13

    public final boolean synpred13() {
        backtracking++;
        int start = input.mark();
        try {
            synpred13_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_ruleApplication_in_parse67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ruleApplication91 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleApplication100 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleSection_in_ruleApplication111 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_ruleSection139 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSection148 = new BitSet(new long[]{0x0000000000041002L});
    public static final BitSet FOLLOW_ruleDefinition_in_ruleSection159 = new BitSet(new long[]{0x0000000000041002L});
    public static final BitSet FOLLOW_ruleEntity_in_ruleDefinition189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemplateDefinition_in_ruleDefinition204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleEntity227 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntity236 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntity245 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_ruleProperty_in_ruleEntity254 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_14_in_ruleEntity264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleProperty291 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleProperty300 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSort_in_ruleProperty309 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleProperty318 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_ruleAnnotation_in_ruleProperty327 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_17_in_ruleProperty337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSort363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAnnotation389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleTemplateDefinition414 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleModifier_in_ruleTemplateDefinition423 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTemplateDefinition435 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTemplateDefinition444 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_ruleFormalArg_in_ruleTemplateDefinition453 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_17_in_ruleTemplateDefinition463 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleTemplateDefinition470 = new BitSet(new long[]{0x0000000000044030L});
    public static final BitSet FOLLOW_ruleTemplateElement_in_ruleTemplateDefinition479 = new BitSet(new long[]{0x0000000000044030L});
    public static final BitSet FOLLOW_14_in_ruleTemplateDefinition489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFormalArg516 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleFormalArg525 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSort_in_ruleFormalArg535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePageModifier_in_ruleModifier565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rulePageModifier587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemplateCall_in_ruleTemplateElement612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemplateDefinition_in_ruleTemplateElement627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleTemplateElement642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTemplateCall689 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTemplateCall698 = new BitSet(new long[]{0x0000000000030030L});
    public static final BitSet FOLLOW_ruleExp_in_ruleTemplateCall707 = new BitSet(new long[]{0x0000000000030030L});
    public static final BitSet FOLLOW_17_in_ruleTemplateCall717 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleTemplateCall724 = new BitSet(new long[]{0x0000000000044030L});
    public static final BitSet FOLLOW_ruleTemplateElement_in_ruleTemplateCall733 = new BitSet(new long[]{0x0000000000044030L});
    public static final BitSet FOLLOW_14_in_ruleTemplateCall743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFieldAccess_in_ruleExp770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExp_in_ruleExp785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleVar809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rulePrimaryExp835 = new BitSet(new long[]{0x0000000000010030L});
    public static final BitSet FOLLOW_ruleExp_in_rulePrimaryExp845 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_rulePrimaryExp855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVar_in_rulePrimaryExp869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_rulePrimaryExp884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExp_in_ruleFieldAccess915 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFieldAccess925 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFieldAccess934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFieldAccess_in_synpred13770 = new BitSet(new long[]{0x0000000000000002L});

}