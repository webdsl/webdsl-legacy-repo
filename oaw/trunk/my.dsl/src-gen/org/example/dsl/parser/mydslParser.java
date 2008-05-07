// $ANTLR 3.0 ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g 2008-05-07 15:27:55

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "'application'", "'section'", "'entity'", "'{'", "'}'", "':'", "'('", "')'", "','", "'<'", "'>'", "'define'", "'page'", "'for'", "'in'", "'.'"
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
            ruleMemo = new HashMap[52+1];
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

                if ( (LA2_0==12||LA2_0==21) ) {
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
            else if ( (LA3_0==21) ) {
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
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:177:1: ruleProperty returns [EObject result] : ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( ( '(' ) (temp_annotations= ruleAnnotations ) ( ')' ) )? ) ;
    public EObject ruleProperty() throws RecognitionException {
        EObject result = null;
        int ruleProperty_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_type = null;

        EObject temp_annotations = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:178:4: ( ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( ( '(' ) (temp_annotations= ruleAnnotations ) ( ')' ) )? ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:178:4: ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( ( '(' ) (temp_annotations= ruleAnnotations ) ( ')' ) )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Property");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:182:1: ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( ( '(' ) (temp_annotations= ruleAnnotations ) ( ')' ) )? )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:182:2: (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ( ( '(' ) (temp_annotations= ruleAnnotations ) ( ')' ) )?
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

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:190:1: ( ( '(' ) (temp_annotations= ruleAnnotations ) ( ')' ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==16) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:190:2: ( '(' ) (temp_annotations= ruleAnnotations ) ( ')' )
                    {
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:190:2: ( '(' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:190:3: '('
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)).eContents().get(0)),line(),start());
                    }
                    match(input,16,FOLLOW_16_in_ruleProperty319); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:192:1: (temp_annotations= ruleAnnotations )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:192:2: temp_annotations= ruleAnnotations
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleAnnotations_in_ruleProperty328);
                    temp_annotations=ruleAnnotations();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"annotations",convert(temp_annotations),false); ptm.ruleFinished(temp_annotations,end()); 
                    }

                    }

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:195:1: ( ')' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:195:2: ')'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(3)).eContents().get(2)),line(),start());
                    }
                    match(input,17,FOLLOW_17_in_ruleProperty337); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;

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


    // $ANTLR start ruleAnnotations
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:200:1: ruleAnnotations returns [EObject result] : ( (temp_Annotation= ruleAnnotation ) ( ( ',' ) (temp_Annotation= ruleAnnotation ) )* ) ;
    public EObject ruleAnnotations() throws RecognitionException {
        EObject result = null;
        int ruleAnnotations_StartIndex = input.index();
        EObject temp_Annotation = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 7) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:201:4: ( ( (temp_Annotation= ruleAnnotation ) ( ( ',' ) (temp_Annotation= ruleAnnotation ) )* ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:201:4: ( (temp_Annotation= ruleAnnotation ) ( ( ',' ) (temp_Annotation= ruleAnnotation ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Annotations");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:205:1: ( (temp_Annotation= ruleAnnotation ) ( ( ',' ) (temp_Annotation= ruleAnnotation ) )* )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:205:2: (temp_Annotation= ruleAnnotation ) ( ( ',' ) (temp_Annotation= ruleAnnotation ) )*
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:205:2: (temp_Annotation= ruleAnnotation )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:205:3: temp_Annotation= ruleAnnotation
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleAnnotation_in_ruleAnnotations368);
            temp_Annotation=ruleAnnotation();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_Annotation;
            }
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:209:1: ( ( ',' ) (temp_Annotation= ruleAnnotation ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==18) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:209:2: ( ',' ) (temp_Annotation= ruleAnnotation )
            	    {
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:209:2: ( ',' )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:209:3: ','
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    match(input,18,FOLLOW_18_in_ruleAnnotations379); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }

            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:211:1: (temp_Annotation= ruleAnnotation )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:211:2: temp_Annotation= ruleAnnotation
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleAnnotation_in_ruleAnnotations389);
            	    temp_Annotation=ruleAnnotation();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      result =temp_Annotation;
            	    }
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
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
            if ( backtracking>0 ) { memoize(input, 7, ruleAnnotations_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleAnnotations


    // $ANTLR start ruleSort
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:218:1: ruleSort returns [EObject result] : (temp_simplesort= ruleSimpleSort | temp_genericsort= ruleGenericSort );
    public EObject ruleSort() throws RecognitionException {
        EObject result = null;
        int ruleSort_StartIndex = input.index();
        EObject temp_simplesort = null;

        EObject temp_genericsort = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:219:9: (temp_simplesort= ruleSimpleSort | temp_genericsort= ruleGenericSort )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==19) ) {
                    alt7=2;
                }
                else if ( (LA7_1==EOF||LA7_1==RULE_ID||LA7_1==14||(LA7_1>=16 && LA7_1<=18)||LA7_1==20||LA7_1==24) ) {
                    alt7=1;
                }
                else {
                    if (backtracking>0) {failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("218:1: ruleSort returns [EObject result] : (temp_simplesort= ruleSimpleSort | temp_genericsort= ruleGenericSort );", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("218:1: ruleSort returns [EObject result] : (temp_simplesort= ruleSimpleSort | temp_genericsort= ruleGenericSort );", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:219:9: temp_simplesort= ruleSimpleSort
                    {
                    pushFollow(FOLLOW_ruleSimpleSort_in_ruleSort422);
                    temp_simplesort=ruleSimpleSort();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_simplesort;
                    }

                    }
                    break;
                case 2 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:219:76: temp_genericsort= ruleGenericSort
                    {
                    pushFollow(FOLLOW_ruleGenericSort_in_ruleSort437);
                    temp_genericsort=ruleGenericSort();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_genericsort;
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
            if ( backtracking>0 ) { memoize(input, 8, ruleSort_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSort


    // $ANTLR start ruleSimpleSort
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:221:1: ruleSimpleSort returns [EObject result] : (temp_name= RULE_ID ) ;
    public EObject ruleSimpleSort() throws RecognitionException {
        EObject result = null;
        int ruleSimpleSort_StartIndex = input.index();
        Token temp_name=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:222:4: ( (temp_name= RULE_ID ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:222:4: (temp_name= RULE_ID )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SimpleSort");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:226:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:226:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSimpleSort461); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 9, ruleSimpleSort_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSimpleSort


    // $ANTLR start ruleGenericSort
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:230:1: ruleGenericSort returns [EObject result] : ( (temp_name= RULE_ID ) ( '<' ) (temp_arguments= ruleSortArguments ) ( '>' ) ) ;
    public EObject ruleGenericSort() throws RecognitionException {
        EObject result = null;
        int ruleGenericSort_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_arguments = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 10) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:231:4: ( ( (temp_name= RULE_ID ) ( '<' ) (temp_arguments= ruleSortArguments ) ( '>' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:231:4: ( (temp_name= RULE_ID ) ( '<' ) (temp_arguments= ruleSortArguments ) ( '>' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "GenericSort");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:235:1: ( (temp_name= RULE_ID ) ( '<' ) (temp_arguments= ruleSortArguments ) ( '>' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:235:2: (temp_name= RULE_ID ) ( '<' ) (temp_arguments= ruleSortArguments ) ( '>' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:235:2: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:235:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGenericSort488); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:238:1: ( '<' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:238:2: '<'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,19,FOLLOW_19_in_ruleGenericSort497); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:240:1: (temp_arguments= ruleSortArguments )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:240:2: temp_arguments= ruleSortArguments
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSortArguments_in_ruleGenericSort506);
            temp_arguments=ruleSortArguments();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:243:1: ( '>' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:243:2: '>'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,20,FOLLOW_20_in_ruleGenericSort515); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 10, ruleGenericSort_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleGenericSort


    // $ANTLR start ruleSortArguments
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:247:1: ruleSortArguments returns [EObject result] : ( (temp_Sort= ruleSort ) ( ( ',' ) (temp_Sort= ruleSort ) )* ) ;
    public EObject ruleSortArguments() throws RecognitionException {
        EObject result = null;
        int ruleSortArguments_StartIndex = input.index();
        EObject temp_Sort = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 11) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:248:4: ( ( (temp_Sort= ruleSort ) ( ( ',' ) (temp_Sort= ruleSort ) )* ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:248:4: ( (temp_Sort= ruleSort ) ( ( ',' ) (temp_Sort= ruleSort ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "SortArguments");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:252:1: ( (temp_Sort= ruleSort ) ( ( ',' ) (temp_Sort= ruleSort ) )* )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:252:2: (temp_Sort= ruleSort ) ( ( ',' ) (temp_Sort= ruleSort ) )*
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:252:2: (temp_Sort= ruleSort )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:252:3: temp_Sort= ruleSort
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleSort_in_ruleSortArguments543);
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

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:256:1: ( ( ',' ) (temp_Sort= ruleSort ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==18) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:256:2: ( ',' ) (temp_Sort= ruleSort )
            	    {
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:256:2: ( ',' )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:256:3: ','
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    match(input,18,FOLLOW_18_in_ruleSortArguments554); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }

            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:258:1: (temp_Sort= ruleSort )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:258:2: temp_Sort= ruleSort
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleSort_in_ruleSortArguments564);
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
            	    break;

            	default :
            	    break loop8;
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
            if ( backtracking>0 ) { memoize(input, 11, ruleSortArguments_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleSortArguments


    // $ANTLR start ruleAnnotation
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:265:1: ruleAnnotation returns [EObject result] : (temp_name= RULE_ID ) ;
    public EObject ruleAnnotation() throws RecognitionException {
        EObject result = null;
        int ruleAnnotation_StartIndex = input.index();
        Token temp_name=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 12) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:266:4: ( (temp_name= RULE_ID ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:266:4: (temp_name= RULE_ID )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Annotation");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:270:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:270:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(10)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAnnotation596); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 12, ruleAnnotation_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleAnnotation


    // $ANTLR start ruleTemplateDefinition
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:274:1: ruleTemplateDefinition returns [EObject result] : ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArgs )? ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) ;
    public EObject ruleTemplateDefinition() throws RecognitionException {
        EObject result = null;
        int ruleTemplateDefinition_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_modifiers = null;

        EObject temp_arguments = null;

        EObject temp_elements = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 13) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:275:4: ( ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArgs )? ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:275:4: ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArgs )? ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "TemplateDefinition");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:279:1: ( ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArgs )? ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:279:2: ( 'define' ) (temp_modifiers= ruleModifier )* (temp_name= RULE_ID ) ( '(' ) (temp_arguments= ruleFormalArgs )? ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:279:2: ( 'define' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:279:3: 'define'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,21,FOLLOW_21_in_ruleTemplateDefinition621); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:281:1: (temp_modifiers= ruleModifier )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==22) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:281:2: temp_modifiers= ruleModifier
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleModifier_in_ruleTemplateDefinition630);
            	    temp_modifiers=ruleModifier();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"modifiers",convert(temp_modifiers),false); ptm.ruleFinished(temp_modifiers,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:284:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:284:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTemplateDefinition642); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:287:1: ( '(' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:287:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleTemplateDefinition651); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:289:1: (temp_arguments= ruleFormalArgs )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:289:2: temp_arguments= ruleFormalArgs
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(4)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleFormalArgs_in_ruleTemplateDefinition660);
                    temp_arguments=ruleFormalArgs();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); 
                    }

                    }
                    break;

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:292:1: ( ')' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:292:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleTemplateDefinition670); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:294:1: ( '{' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:294:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(6)),line(),start());
            }
            match(input,13,FOLLOW_13_in_ruleTemplateDefinition677); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:296:1: (temp_elements= ruleTemplateElement )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=RULE_ID && LA11_0<=RULE_STRING)||LA11_0==21||LA11_0==23) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:296:2: temp_elements= ruleTemplateElement
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(7)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleTemplateElement_in_ruleTemplateDefinition686);
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

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:299:1: ( '}' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:299:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(8)),line(),start());
            }
            match(input,14,FOLLOW_14_in_ruleTemplateDefinition696); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 13, ruleTemplateDefinition_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTemplateDefinition


    // $ANTLR start ruleFormalArgs
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:303:1: ruleFormalArgs returns [EObject result] : ( (temp_FormalArg= ruleFormalArg ) ( ( ',' ) (temp_FormalArg= ruleFormalArg ) )* ) ;
    public EObject ruleFormalArgs() throws RecognitionException {
        EObject result = null;
        int ruleFormalArgs_StartIndex = input.index();
        EObject temp_FormalArg = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 14) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:304:4: ( ( (temp_FormalArg= ruleFormalArg ) ( ( ',' ) (temp_FormalArg= ruleFormalArg ) )* ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:304:4: ( (temp_FormalArg= ruleFormalArg ) ( ( ',' ) (temp_FormalArg= ruleFormalArg ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "FormalArgs");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:308:1: ( (temp_FormalArg= ruleFormalArg ) ( ( ',' ) (temp_FormalArg= ruleFormalArg ) )* )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:308:2: (temp_FormalArg= ruleFormalArg ) ( ( ',' ) (temp_FormalArg= ruleFormalArg ) )*
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:308:2: (temp_FormalArg= ruleFormalArg )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:308:3: temp_FormalArg= ruleFormalArg
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleFormalArg_in_ruleFormalArgs724);
            temp_FormalArg=ruleFormalArg();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_FormalArg;
            }
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:312:1: ( ( ',' ) (temp_FormalArg= ruleFormalArg ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==18) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:312:2: ( ',' ) (temp_FormalArg= ruleFormalArg )
            	    {
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:312:2: ( ',' )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:312:3: ','
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    match(input,18,FOLLOW_18_in_ruleFormalArgs735); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }

            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:314:1: (temp_FormalArg= ruleFormalArg )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:314:2: temp_FormalArg= ruleFormalArg
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleFormalArg_in_ruleFormalArgs745);
            	    temp_FormalArg=ruleFormalArg();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      result =temp_FormalArg;
            	    }
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
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
            if ( backtracking>0 ) { memoize(input, 14, ruleFormalArgs_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFormalArgs


    // $ANTLR start ruleFormalArg
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:321:1: ruleFormalArg returns [EObject result] : ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ) ;
    public EObject ruleFormalArg() throws RecognitionException {
        EObject result = null;
        int ruleFormalArg_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_type = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 15) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:322:4: ( ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:322:4: ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "FormalArg");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:326:1: ( (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:326:2: (temp_name= RULE_ID ) ( ':' ) (temp_type= ruleSort )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:326:2: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:326:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFormalArg778); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:329:1: ( ':' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:329:2: ':'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,15,FOLLOW_15_in_ruleFormalArg787); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:331:1: (temp_type= ruleSort )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:331:2: temp_type= ruleSort
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleSort_in_ruleFormalArg796);
            temp_type=ruleSort();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); 
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
            if ( backtracking>0 ) { memoize(input, 15, ruleFormalArg_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFormalArg


    // $ANTLR start ruleModifier
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:336:1: ruleModifier returns [EObject result] : temp_pagemodifier= rulePageModifier ;
    public EObject ruleModifier() throws RecognitionException {
        EObject result = null;
        int ruleModifier_StartIndex = input.index();
        EObject temp_pagemodifier = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 16) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:337:9: (temp_pagemodifier= rulePageModifier )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:337:9: temp_pagemodifier= rulePageModifier
            {
            pushFollow(FOLLOW_rulePageModifier_in_ruleModifier825);
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
            if ( backtracking>0 ) { memoize(input, 16, ruleModifier_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleModifier


    // $ANTLR start rulePageModifier
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:339:1: rulePageModifier returns [EObject result] : ( 'page' ) ;
    public EObject rulePageModifier() throws RecognitionException {
        EObject result = null;
        int rulePageModifier_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 17) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:340:4: ( ( 'page' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:340:4: ( 'page' )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "PageModifier");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:344:1: ( 'page' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:344:2: 'page'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)),line(),start());
            }
            match(input,22,FOLLOW_22_in_rulePageModifier847); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 17, rulePageModifier_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePageModifier


    // $ANTLR start ruleTemplateElement
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:347:1: ruleTemplateElement returns [EObject result] : (temp_templatecall= ruleTemplateCall | temp_templatedefinition= ruleTemplateDefinition | temp_stringliteral= ruleStringLiteral | temp_fortemplate= ruleForTemplate );
    public EObject ruleTemplateElement() throws RecognitionException {
        EObject result = null;
        int ruleTemplateElement_StartIndex = input.index();
        EObject temp_templatecall = null;

        EObject temp_templatedefinition = null;

        EObject temp_stringliteral = null;

        EObject temp_fortemplate = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 18) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:348:9: (temp_templatecall= ruleTemplateCall | temp_templatedefinition= ruleTemplateDefinition | temp_stringliteral= ruleStringLiteral | temp_fortemplate= ruleForTemplate )
            int alt13=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt13=1;
                }
                break;
            case 21:
                {
                alt13=2;
                }
                break;
            case RULE_STRING:
                {
                alt13=3;
                }
                break;
            case 23:
                {
                alt13=4;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("347:1: ruleTemplateElement returns [EObject result] : (temp_templatecall= ruleTemplateCall | temp_templatedefinition= ruleTemplateDefinition | temp_stringliteral= ruleStringLiteral | temp_fortemplate= ruleForTemplate );", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:348:9: temp_templatecall= ruleTemplateCall
                    {
                    pushFollow(FOLLOW_ruleTemplateCall_in_ruleTemplateElement872);
                    temp_templatecall=ruleTemplateCall();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_templatecall;
                    }

                    }
                    break;
                case 2 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:348:82: temp_templatedefinition= ruleTemplateDefinition
                    {
                    pushFollow(FOLLOW_ruleTemplateDefinition_in_ruleTemplateElement887);
                    temp_templatedefinition=ruleTemplateDefinition();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_templatedefinition;
                    }

                    }
                    break;
                case 3 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:348:173: temp_stringliteral= ruleStringLiteral
                    {
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleTemplateElement902);
                    temp_stringliteral=ruleStringLiteral();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_stringliteral;
                    }

                    }
                    break;
                case 4 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:348:249: temp_fortemplate= ruleForTemplate
                    {
                    pushFollow(FOLLOW_ruleForTemplate_in_ruleTemplateElement917);
                    temp_fortemplate=ruleForTemplate();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_fortemplate;
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
            if ( backtracking>0 ) { memoize(input, 18, ruleTemplateElement_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTemplateElement


    // $ANTLR start ruleForTemplate
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:350:1: ruleForTemplate returns [EObject result] : ( ( 'for' ) ( '(' ) (temp_iterator= ruleVar ) ( ':' ) (temp_type= ruleSort ) ( 'in' ) (temp_source= ruleExp ) ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) ;
    public EObject ruleForTemplate() throws RecognitionException {
        EObject result = null;
        int ruleForTemplate_StartIndex = input.index();
        EObject temp_iterator = null;

        EObject temp_type = null;

        EObject temp_source = null;

        EObject temp_elements = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 19) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:351:4: ( ( ( 'for' ) ( '(' ) (temp_iterator= ruleVar ) ( ':' ) (temp_type= ruleSort ) ( 'in' ) (temp_source= ruleExp ) ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:351:4: ( ( 'for' ) ( '(' ) (temp_iterator= ruleVar ) ( ':' ) (temp_type= ruleSort ) ( 'in' ) (temp_source= ruleExp ) ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ForTemplate");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:355:1: ( ( 'for' ) ( '(' ) (temp_iterator= ruleVar ) ( ':' ) (temp_type= ruleSort ) ( 'in' ) (temp_source= ruleExp ) ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:355:2: ( 'for' ) ( '(' ) (temp_iterator= ruleVar ) ( ':' ) (temp_type= ruleSort ) ( 'in' ) (temp_source= ruleExp ) ( ')' ) ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:355:2: ( 'for' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:355:3: 'for'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,23,FOLLOW_23_in_ruleForTemplate940); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:357:1: ( '(' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:357:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleForTemplate947); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:359:1: (temp_iterator= ruleVar )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:359:2: temp_iterator= ruleVar
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleVar_in_ruleForTemplate956);
            temp_iterator=ruleVar();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"iterator",convert(temp_iterator),false); ptm.ruleFinished(temp_iterator,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:362:1: ( ':' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:362:2: ':'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,15,FOLLOW_15_in_ruleForTemplate965); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:364:1: (temp_type= ruleSort )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:364:2: temp_type= ruleSort
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            pushFollow(FOLLOW_ruleSort_in_ruleForTemplate974);
            temp_type=ruleSort();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"type",convert(temp_type),false); ptm.ruleFinished(temp_type,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:367:1: ( 'in' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:367:2: 'in'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(5)),line(),start());
            }
            match(input,24,FOLLOW_24_in_ruleForTemplate983); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:369:1: (temp_source= ruleExp )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:369:2: temp_source= ruleExp
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(6)),line(),start());
            }
            pushFollow(FOLLOW_ruleExp_in_ruleForTemplate992);
            temp_source=ruleExp();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"source",convert(temp_source),false); ptm.ruleFinished(temp_source,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:372:1: ( ')' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:372:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(7)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleForTemplate1001); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:374:1: ( '{' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:374:2: '{'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(8)),line(),start());
            }
            match(input,13,FOLLOW_13_in_ruleForTemplate1008); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:376:1: (temp_elements= ruleTemplateElement )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=RULE_ID && LA14_0<=RULE_STRING)||LA14_0==21||LA14_0==23) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:376:2: temp_elements= ruleTemplateElement
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(9)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleTemplateElement_in_ruleForTemplate1017);
            	    temp_elements=ruleTemplateElement();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"elements",convert(temp_elements),false); ptm.ruleFinished(temp_elements,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:379:1: ( '}' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:379:2: '}'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(17)).eContents().get(1)).eContents().get(10)),line(),start());
            }
            match(input,14,FOLLOW_14_in_ruleForTemplate1027); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 19, ruleForTemplate_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleForTemplate


    // $ANTLR start ruleStringLiteral
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:383:1: ruleStringLiteral returns [EObject result] : (temp_value= RULE_STRING ) ;
    public EObject ruleStringLiteral() throws RecognitionException {
        EObject result = null;
        int ruleStringLiteral_StartIndex = input.index();
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 20) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:384:4: ( (temp_value= RULE_STRING ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:384:4: (temp_value= RULE_STRING )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "StringLiteral");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:388:1: (temp_value= RULE_STRING )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:388:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(18)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral1053); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 20, ruleStringLiteral_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleStringLiteral


    // $ANTLR start ruleTemplateCall
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:392:1: ruleTemplateCall returns [EObject result] : ( (temp_name= RULE_ID ) ( ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )? ( ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )? ) ;
    public EObject ruleTemplateCall() throws RecognitionException {
        EObject result = null;
        int ruleTemplateCall_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_arguments = null;

        EObject temp_elements = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 21) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:393:4: ( ( (temp_name= RULE_ID ) ( ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )? ( ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )? ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:393:4: ( (temp_name= RULE_ID ) ( ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )? ( ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "TemplateCall");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:397:1: ( (temp_name= RULE_ID ) ( ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )? ( ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )? )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:397:2: (temp_name= RULE_ID ) ( ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )? ( ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )?
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:397:2: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:397:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTemplateCall1080); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),true); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:401:1: ( ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==16) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:401:2: ( '(' ) (temp_arguments= ruleArguments ) ( ')' )
                    {
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:401:2: ( '(' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:401:3: '('
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
                    }
                    match(input,16,FOLLOW_16_in_ruleTemplateCall1091); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:403:1: (temp_arguments= ruleArguments )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:403:2: temp_arguments= ruleArguments
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleArguments_in_ruleTemplateCall1100);
                    temp_arguments=ruleArguments();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); 
                    }

                    }

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:406:1: ( ')' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:406:2: ')'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    match(input,17,FOLLOW_17_in_ruleTemplateCall1109); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:409:1: ( ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==13) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:409:2: ( '{' ) (temp_elements= ruleTemplateElement )* ( '}' )
                    {
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:409:2: ( '{' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:409:3: '{'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)).eContents().get(0)),line(),start());
                    }
                    match(input,13,FOLLOW_13_in_ruleTemplateCall1120); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:411:1: (temp_elements= ruleTemplateElement )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>=RULE_ID && LA16_0<=RULE_STRING)||LA16_0==21||LA16_0==23) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:411:2: temp_elements= ruleTemplateElement
                    	    {
                    	    if ( backtracking==0 ) {
                    	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)).eContents().get(1)),line(),start());
                    	    }
                    	    pushFollow(FOLLOW_ruleTemplateElement_in_ruleTemplateCall1129);
                    	    temp_elements=ruleTemplateElement();
                    	    _fsp--;
                    	    if (failed) return result;
                    	    if ( backtracking==0 ) {
                    	      factory.add(result,"elements",convert(temp_elements),false); ptm.ruleFinished(temp_elements,end()); 
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:414:1: ( '}' )
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:414:2: '}'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(19)).eContents().get(1)).eContents().get(2)).eContents().get(2)),line(),start());
                    }
                    match(input,14,FOLLOW_14_in_ruleTemplateCall1139); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 21, ruleTemplateCall_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTemplateCall


    // $ANTLR start ruleExp
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:419:1: ruleExp returns [EObject result] : temp_fieldaccess= ruleFieldAccess ;
    public EObject ruleExp() throws RecognitionException {
        EObject result = null;
        int ruleExp_StartIndex = input.index();
        EObject temp_fieldaccess = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 22) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:420:9: (temp_fieldaccess= ruleFieldAccess )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:420:9: temp_fieldaccess= ruleFieldAccess
            {
            pushFollow(FOLLOW_ruleFieldAccess_in_ruleExp1169);
            temp_fieldaccess=ruleFieldAccess();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              result =temp_fieldaccess;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 22, ruleExp_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleExp


    // $ANTLR start ruleFieldAccess
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:422:1: ruleFieldAccess returns [EObject result] : ( (temp_target= rulePrimaryExp ) ( ( '.' ) (temp_fields= RULE_ID ) )* ) ;
    public EObject ruleFieldAccess() throws RecognitionException {
        EObject result = null;
        int ruleFieldAccess_StartIndex = input.index();
        Token temp_fields=null;
        EObject temp_target = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 23) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:423:4: ( ( (temp_target= rulePrimaryExp ) ( ( '.' ) (temp_fields= RULE_ID ) )* ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:423:4: ( (temp_target= rulePrimaryExp ) ( ( '.' ) (temp_fields= RULE_ID ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "FieldAccess");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:427:1: ( (temp_target= rulePrimaryExp ) ( ( '.' ) (temp_fields= RULE_ID ) )* )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:427:2: (temp_target= rulePrimaryExp ) ( ( '.' ) (temp_fields= RULE_ID ) )*
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:427:2: (temp_target= rulePrimaryExp )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:427:3: temp_target= rulePrimaryExp
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_rulePrimaryExp_in_ruleFieldAccess1194);
            temp_target=rulePrimaryExp();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"target",convert(temp_target),false); ptm.ruleFinished(temp_target,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:430:1: ( ( '.' ) (temp_fields= RULE_ID ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==25) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:430:2: ( '.' ) (temp_fields= RULE_ID )
            	    {
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:430:2: ( '.' )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:430:3: '.'
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    match(input,25,FOLLOW_25_in_ruleFieldAccess1204); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }

            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:432:1: (temp_fields= RULE_ID )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:432:2: temp_fields= RULE_ID
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(21)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    temp_fields=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFieldAccess1213); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"fields",convert(temp_fields),false); ptm.ruleFinished(temp_fields,end()); 
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
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
            if ( backtracking>0 ) { memoize(input, 23, ruleFieldAccess_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFieldAccess


    // $ANTLR start rulePrimaryExp
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:438:1: rulePrimaryExp returns [EObject result] : (temp_parenexp= ruleParenExp | temp_var= ruleVar | temp_stringliteral= ruleStringLiteral | temp_funcall= ruleFunCall );
    public EObject rulePrimaryExp() throws RecognitionException {
        EObject result = null;
        int rulePrimaryExp_StartIndex = input.index();
        EObject temp_parenexp = null;

        EObject temp_var = null;

        EObject temp_stringliteral = null;

        EObject temp_funcall = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 24) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:439:9: (temp_parenexp= ruleParenExp | temp_var= ruleVar | temp_stringliteral= ruleStringLiteral | temp_funcall= ruleFunCall )
            int alt19=4;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt19=1;
                }
                break;
            case RULE_ID:
                {
                int LA19_2 = input.LA(2);

                if ( (LA19_2==EOF||(LA19_2>=17 && LA19_2<=18)||LA19_2==25) ) {
                    alt19=2;
                }
                else if ( (LA19_2==16) ) {
                    alt19=4;
                }
                else {
                    if (backtracking>0) {failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("438:1: rulePrimaryExp returns [EObject result] : (temp_parenexp= ruleParenExp | temp_var= ruleVar | temp_stringliteral= ruleStringLiteral | temp_funcall= ruleFunCall );", 19, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                alt19=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("438:1: rulePrimaryExp returns [EObject result] : (temp_parenexp= ruleParenExp | temp_var= ruleVar | temp_stringliteral= ruleStringLiteral | temp_funcall= ruleFunCall );", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:439:9: temp_parenexp= ruleParenExp
                    {
                    pushFollow(FOLLOW_ruleParenExp_in_rulePrimaryExp1245);
                    temp_parenexp=ruleParenExp();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_parenexp;
                    }

                    }
                    break;
                case 2 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:439:70: temp_var= ruleVar
                    {
                    pushFollow(FOLLOW_ruleVar_in_rulePrimaryExp1260);
                    temp_var=ruleVar();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_var;
                    }

                    }
                    break;
                case 3 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:439:116: temp_stringliteral= ruleStringLiteral
                    {
                    pushFollow(FOLLOW_ruleStringLiteral_in_rulePrimaryExp1275);
                    temp_stringliteral=ruleStringLiteral();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_stringliteral;
                    }

                    }
                    break;
                case 4 :
                    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:439:192: temp_funcall= ruleFunCall
                    {
                    pushFollow(FOLLOW_ruleFunCall_in_rulePrimaryExp1290);
                    temp_funcall=ruleFunCall();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      result =temp_funcall;
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
            if ( backtracking>0 ) { memoize(input, 24, rulePrimaryExp_StartIndex); }
        }
        return result;
    }
    // $ANTLR end rulePrimaryExp


    // $ANTLR start ruleVar
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:441:1: ruleVar returns [EObject result] : (temp_name= RULE_ID ) ;
    public EObject ruleVar() throws RecognitionException {
        EObject result = null;
        int ruleVar_StartIndex = input.index();
        Token temp_name=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 25) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:442:4: ( (temp_name= RULE_ID ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:442:4: (temp_name= RULE_ID )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Var");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:446:1: (temp_name= RULE_ID )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:446:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(23)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleVar1314); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 25, ruleVar_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleVar


    // $ANTLR start ruleParenExp
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:450:1: ruleParenExp returns [EObject result] : ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) ) ;
    public EObject ruleParenExp() throws RecognitionException {
        EObject result = null;
        int ruleParenExp_StartIndex = input.index();
        EObject temp_Exp = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 26) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:451:4: ( ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:451:4: ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ParenExp");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:455:1: ( ( '(' ) (temp_Exp= ruleExp ) ( ')' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:455:2: ( '(' ) (temp_Exp= ruleExp ) ( ')' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:455:2: ( '(' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:455:3: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleParenExp1339); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:457:1: (temp_Exp= ruleExp )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:457:2: temp_Exp= ruleExp
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleExp_in_ruleParenExp1349);
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

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:461:1: ( ')' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:461:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(24)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleParenExp1359); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 26, ruleParenExp_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleParenExp


    // $ANTLR start ruleFunCall
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:465:1: ruleFunCall returns [EObject result] : ( (temp_function= ruleVar ) ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) ) ;
    public EObject ruleFunCall() throws RecognitionException {
        EObject result = null;
        int ruleFunCall_StartIndex = input.index();
        EObject temp_function = null;

        EObject temp_arguments = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 27) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:466:4: ( ( (temp_function= ruleVar ) ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:466:4: ( (temp_function= ruleVar ) ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "FunCall");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:470:1: ( (temp_function= ruleVar ) ( '(' ) (temp_arguments= ruleArguments ) ( ')' ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:470:2: (temp_function= ruleVar ) ( '(' ) (temp_arguments= ruleArguments ) ( ')' )
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:470:2: (temp_function= ruleVar )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:470:3: temp_function= ruleVar
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleVar_in_ruleFunCall1386);
            temp_function=ruleVar();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"function",convert(temp_function),false); ptm.ruleFinished(temp_function,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:473:1: ( '(' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:473:2: '('
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            match(input,16,FOLLOW_16_in_ruleFunCall1395); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:475:1: (temp_arguments= ruleArguments )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:475:2: temp_arguments= ruleArguments
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleArguments_in_ruleFunCall1404);
            temp_arguments=ruleArguments();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"arguments",convert(temp_arguments),false); ptm.ruleFinished(temp_arguments,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:478:1: ( ')' )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:478:2: ')'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(25)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            match(input,17,FOLLOW_17_in_ruleFunCall1413); if (failed) return result;
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
            if ( backtracking>0 ) { memoize(input, 27, ruleFunCall_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleFunCall


    // $ANTLR start ruleArguments
    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:482:1: ruleArguments returns [EObject result] : ( (temp_expressions= ruleExp ) ( ( ',' ) (temp_expressions= ruleExp ) )* ) ;
    public EObject ruleArguments() throws RecognitionException {
        EObject result = null;
        int ruleArguments_StartIndex = input.index();
        EObject temp_expressions = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 28) ) { return result; }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:483:4: ( ( (temp_expressions= ruleExp ) ( ( ',' ) (temp_expressions= ruleExp ) )* ) )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:483:4: ( (temp_expressions= ruleExp ) ( ( ',' ) (temp_expressions= ruleExp ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Arguments");
              				ptm.setModelElement(result);
              			 
            }
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:487:1: ( (temp_expressions= ruleExp ) ( ( ',' ) (temp_expressions= ruleExp ) )* )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:487:2: (temp_expressions= ruleExp ) ( ( ',' ) (temp_expressions= ruleExp ) )*
            {
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:487:2: (temp_expressions= ruleExp )
            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:487:3: temp_expressions= ruleExp
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleExp_in_ruleArguments1440);
            temp_expressions=ruleExp();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.add(result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); 
            }

            }

            // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:490:1: ( ( ',' ) (temp_expressions= ruleExp ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==18) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:490:2: ( ',' ) (temp_expressions= ruleExp )
            	    {
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:490:2: ( ',' )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:490:3: ','
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            	    }
            	    match(input,18,FOLLOW_18_in_ruleArguments1450); if (failed) return result;
            	    if ( backtracking==0 ) {
            	      ptm.ruleFinished(getLastToken(),end());
            	    }

            	    }

            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:492:1: (temp_expressions= ruleExp )
            	    // ..//my.dsl/src-gen//org/example/dsl/parser/mydsl.g:492:2: temp_expressions= ruleExp
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(26)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleExp_in_ruleArguments1459);
            	    temp_expressions=ruleExp();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); 
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
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
            if ( backtracking>0 ) { memoize(input, 28, ruleArguments_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleArguments


 

    public static final BitSet FOLLOW_ruleApplication_in_parse67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ruleApplication91 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleApplication100 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleSection_in_ruleApplication111 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_ruleSection139 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSection148 = new BitSet(new long[]{0x0000000000201002L});
    public static final BitSet FOLLOW_ruleDefinition_in_ruleSection159 = new BitSet(new long[]{0x0000000000201002L});
    public static final BitSet FOLLOW_ruleEntity_in_ruleDefinition189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemplateDefinition_in_ruleDefinition204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleEntity227 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntity236 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntity245 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_ruleProperty_in_ruleEntity254 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_14_in_ruleEntity264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleProperty291 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleProperty300 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSort_in_ruleProperty309 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleProperty319 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAnnotations_in_ruleProperty328 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleProperty337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnnotation_in_ruleAnnotations368 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleAnnotations379 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAnnotation_in_ruleAnnotations389 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ruleSimpleSort_in_ruleSort422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGenericSort_in_ruleSort437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSimpleSort461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGenericSort488 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleGenericSort497 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSortArguments_in_ruleGenericSort506 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleGenericSort515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSort_in_ruleSortArguments543 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleSortArguments554 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSort_in_ruleSortArguments564 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAnnotation596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleTemplateDefinition621 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_ruleModifier_in_ruleTemplateDefinition630 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTemplateDefinition642 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTemplateDefinition651 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_ruleFormalArgs_in_ruleTemplateDefinition660 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleTemplateDefinition670 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleTemplateDefinition677 = new BitSet(new long[]{0x0000000000A04030L});
    public static final BitSet FOLLOW_ruleTemplateElement_in_ruleTemplateDefinition686 = new BitSet(new long[]{0x0000000000A04030L});
    public static final BitSet FOLLOW_14_in_ruleTemplateDefinition696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFormalArg_in_ruleFormalArgs724 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleFormalArgs735 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFormalArg_in_ruleFormalArgs745 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFormalArg778 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleFormalArg787 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSort_in_ruleFormalArg796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePageModifier_in_ruleModifier825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rulePageModifier847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemplateCall_in_ruleTemplateElement872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemplateDefinition_in_ruleTemplateElement887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleTemplateElement902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForTemplate_in_ruleTemplateElement917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleForTemplate940 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleForTemplate947 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleVar_in_ruleForTemplate956 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleForTemplate965 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSort_in_ruleForTemplate974 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleForTemplate983 = new BitSet(new long[]{0x0000000000010030L});
    public static final BitSet FOLLOW_ruleExp_in_ruleForTemplate992 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleForTemplate1001 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleForTemplate1008 = new BitSet(new long[]{0x0000000000A04030L});
    public static final BitSet FOLLOW_ruleTemplateElement_in_ruleForTemplate1017 = new BitSet(new long[]{0x0000000000A04030L});
    public static final BitSet FOLLOW_14_in_ruleForTemplate1027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTemplateCall1080 = new BitSet(new long[]{0x0000000000012002L});
    public static final BitSet FOLLOW_16_in_ruleTemplateCall1091 = new BitSet(new long[]{0x0000000000010030L});
    public static final BitSet FOLLOW_ruleArguments_in_ruleTemplateCall1100 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleTemplateCall1109 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_ruleTemplateCall1120 = new BitSet(new long[]{0x0000000000A04030L});
    public static final BitSet FOLLOW_ruleTemplateElement_in_ruleTemplateCall1129 = new BitSet(new long[]{0x0000000000A04030L});
    public static final BitSet FOLLOW_14_in_ruleTemplateCall1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFieldAccess_in_ruleExp1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExp_in_ruleFieldAccess1194 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleFieldAccess1204 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFieldAccess1213 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleParenExp_in_rulePrimaryExp1245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVar_in_rulePrimaryExp1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_rulePrimaryExp1275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunCall_in_rulePrimaryExp1290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleVar1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleParenExp1339 = new BitSet(new long[]{0x0000000000010030L});
    public static final BitSet FOLLOW_ruleExp_in_ruleParenExp1349 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleParenExp1359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVar_in_ruleFunCall1386 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleFunCall1395 = new BitSet(new long[]{0x0000000000010030L});
    public static final BitSet FOLLOW_ruleArguments_in_ruleFunCall1404 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleFunCall1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExp_in_ruleArguments1440 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleArguments1450 = new BitSet(new long[]{0x0000000000010030L});
    public static final BitSet FOLLOW_ruleExp_in_ruleArguments1459 = new BitSet(new long[]{0x0000000000040002L});

}