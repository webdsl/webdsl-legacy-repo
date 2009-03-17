package org.strategoxt.imp.runtime.parser.ast;

import static java.lang.Math.*;
import static org.spoofax.jsglr.Term.*;
import static org.strategoxt.imp.runtime.parser.tokens.TokenKind.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import lpg.runtime.IToken;
import lpg.runtime.PrsStream;

import org.jboss.util.collection.WeakValueHashMap;
import org.strategoxt.imp.runtime.Debug;
import org.strategoxt.imp.runtime.parser.tokens.SGLRTokenizer;
import org.strategoxt.imp.runtime.parser.tokens.TokenKindManager;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.pure.ATermListImpl;

/**
 * Class to convert an Asfix tree to another format.
 * 
 * @author Lennart Kats <L.C.L.Kats add tudelft.nl>
 */
public class AsfixImploder {
	private static final Map<ATerm, AstNode> implodedCache =
		Collections.synchronizedMap(new WeakValueHashMap<ATerm, AstNode>());
	
	protected final static int PARSE_TREE = 0;
	
	protected final static int APPL_PROD = 0;
	
	protected final static int APPL_CONTENTS = 1;

	protected final static int PROD_LHS = 0;
	
	protected final static int PROD_RHS = 1;

	protected final static int PROD_ATTRS = 2;
	
	protected final static int TERM_CONS = 0;
	
	protected final static int CONS_NAME = 0;
	
	protected final static int PARAMETRIZED_SORT_NAME = 0;
	
	protected final static int PARAMETRIZED_SORT_ARGS = 1;
	
	protected final static int ALT_SORT_LEFT = 0;
	
	protected final static int ALT_SORT_RIGHT = 1;
	
	protected final static int EXPECTED_NODE_CHILDREN = 5;
	
	protected final AstNodeFactory factory = new AstNodeFactory();
	
	protected final TokenKindManager tokenManager;
	
	protected SGLRTokenizer tokenizer;
	
	/** Character offset for the current implosion. */ 
	protected int offset;
	
	protected boolean lexicalContext;
	
    public AsfixImploder(TokenKindManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	
	public AstNode implode(ATerm asfix, SGLRTokenizer tokenizer) {
		this.tokenizer = tokenizer;
		
		// TODO: Return null if imploded tree has null constructor??
		
		AstNode result = implodedCache.get(asfix);
		if (result != null) return result;
		
		Debug.startTimer();

		if (!(asfix instanceof ATermAppl || ((ATermAppl) asfix).getName().equals("parsetree")))
			throw new IllegalArgumentException("Parse tree expected");
		
		assert offset == 0 : "Race condition in AsfixImploder";
		
		ATerm top = (ATerm) asfix.getChildAt(PARSE_TREE);
		offset = 0;
		lexicalContext = false;
		
		try {
			result = implodeAppl(top);
		} finally {
			tokenizer.endStream();
			offset = 0;
		}
		
		if (Debug.ENABLED) {
			Debug.stopTimer("Parse tree imploded");
			Debug.log("Parsed " + result.toString());
		}
		
		implodedCache.put(asfix, result);

		return result;
	}
	
	/**
	 * Implode any appl(_, _).
	 */
	protected AstNode implodeAppl(ATerm term) {
		ATermAppl appl = resolveAmbiguities(term);
		ATermAppl prod = termAt(appl, APPL_PROD);
		ATermList lhs = termAt(prod, PROD_LHS);
		ATermAppl rhs = termAt(prod, PROD_RHS);
		ATermAppl attrs = termAt(prod, PROD_ATTRS);
		ATermList contents = termAt(appl, APPL_CONTENTS);
		
		IToken prevToken = tokenizer.currentToken();
		
		// Enter lexical context if this is a lex node
		boolean lexicalStart = !lexicalContext
			&& (rhs.getName().equals("lex") || AsfixAnalyzer.isLiteral(rhs)
			    || AsfixAnalyzer.isLayout(rhs));
		
		if (lexicalStart) lexicalContext = true;
		
		boolean isList = !lexicalContext && AsfixAnalyzer.isList(rhs);
		boolean isVar  = !lexicalContext && !isList && rhs.getName().equals("varsym");
		
		if (isVar) lexicalContext = true;
		
		// Recurse the tree (and set children if applicable)
		ArrayList<AstNode> children =
			implodeChildNodes(contents, lexicalContext);
		
		if (lexicalStart || isVar) {
			return createTerminal(lhs, rhs);
		} else if (lexicalContext) {
			return null; // don't create tokens inside lexical context; just create one big token at the top
		} else {
			return createNonTerminalOrInjection(lhs, rhs, attrs, prevToken, children, isList);
		}
	}
	
	/*
	private static ATermList extractLHS(ATermAppl prod) {
		ATerm lhs = termAt(prod, i)
		if (term.getType() == ATerm.APPL && asAppl(term).getName().equals("list")) {
			return term.getFactory().makeList(termAt(term, 0));
		} else {
			return (ATermList) term;
		}
	}
	*/

	protected ArrayList<AstNode> implodeChildNodes(ATermList contents, boolean tokensOnly) {
		ArrayList<AstNode> result = tokensOnly
				? null
				: new ArrayList<AstNode>(
						min(EXPECTED_NODE_CHILDREN, contents.getChildCount()));

		for (int i = 0; i < contents.getLength(); i++) {
			ATerm child = contents.elementAt(i);

			if (isInt(child)) {
				implodeLexical((ATermInt) child);
			} else {
				// Recurse
				AstNode childNode = implodeAppl(child);

				if (childNode != null)
					result.add(childNode);
			}
		}

		return result;
	}

	private AstNode createTerminal(ATermList lhs, ATermAppl rhs) {
		// TODO: Also create int terminals
		// TODO2: Optimize - don't construct a token's string value until it is used
		
		lexicalContext = false;
		IToken token = tokenizer.makeToken(offset, tokenManager.getTokenKind(lhs, rhs), true);
		String sort = getSort(rhs);
		
		if (sort == null) return null;
		
		//Debug.log("Creating node ", sort, " from ", SGLRTokenizer.dumpToString(token));	
		
		return factory.createTerminal(sort, token);
	}

	private AstNode createNonTerminalOrInjection(ATermList lhs, ATermAppl rhs, ATermAppl attrs,
			IToken prevToken, ArrayList<AstNode> children, boolean isList) {
		
		String constructor = getConstructor(attrs);
		String sort = getSort(rhs);
		
		if(constructor == null) {
			if (isList) {
				return createNonTerminal(sort, null, prevToken, children, true);
			} else if (children.size() == 0) {
				return createNonTerminal(sort, "None", prevToken, children, false);
			} else {
				assert children.size() == 1;
				return children.get(0);
			}
		} else {
			tokenizer.makeToken(offset, tokenManager.getTokenKind(lhs, rhs));
			return createNonTerminal(sort, constructor, prevToken, children, isList);
		}
	}

	/** Implode a context-free node. */
	private AstNode createNonTerminal(String sort, String constructor, IToken prevToken,
			ArrayList<AstNode> children, boolean isList) {
		
		IToken left = getStartToken(prevToken);
		IToken right = getEndToken(left, tokenizer.currentToken());
		
		/*
		if (Debug.ENABLED) {
			String name = isList ? "list" : sort;
			Debug.log("Creating node ", name, ":", constructor, AstNode.getSorts(children), " from ", SGLRTokenizer.dumpToString(left, right));
		}
		*/
		
		if (isList) {
			return factory.createList(sort, left, right, children);
		} else if (constructor == null && children.size() == 1 && children.get(0).getSort() == AstNode.STRING_SORT) {
			// Child node was a <string> node (rare case); unpack it and create a new terminal
			assert left == right && children.get(0).getChildren().size() == 0;
			return factory.createTerminal(sort, left);
		} else {
			return factory.createNonTerminal(sort, constructor, left, right, children);
		}
	}
	
	/**
	 * Resolve or ignore any ambiguities in the parse tree.
	 */
	protected ATermAppl resolveAmbiguities(final ATerm node) {
		if (!"amb".equals(((ATermAppl) node).getName()))
			return (ATermAppl) node;
		
		final ATermListImpl ambs = termAt(node, 0);
		
		ATermAppl lastNonAvoid = null;
		boolean multipleNonAvoids = false;
		
	alts:
		for (int i = 0; i < ambs.getLength(); i++) {
			ATermAppl prod = resolveAmbiguities(termAt(ambs, i));
			ATermAppl appl = termAt(prod, APPL_PROD);
			ATermAppl attrs = termAt(appl, PROD_ATTRS);
			
			if ("attrs".equals(attrs.getName())) {
				ATermList attrList = termAt(attrs, 0);
				
				for (int j = 0; j < attrList.getLength(); j++) {
					ATerm attr = termAt(attrList, j);
					if (isAppl(attr) && "prefer".equals(asAppl(attr).getName())) {
						return resolveAmbiguities(prod);
					} else if (isAppl(attr) && "avoid".equals(asAppl(attr).getName())) {
						continue alts;
					}
				}
				
				if (lastNonAvoid == null) {
					lastNonAvoid = prod;
				} else {
					multipleNonAvoids = true;
				}
			}
		}
		
		if (!multipleNonAvoids) {
			return lastNonAvoid != null ? lastNonAvoid : applAt(ambs, 0);
		} else {
			if (Debug.ENABLED && !lexicalContext) reportUnresolvedAmb(ambs);
			return resolveAmbiguities(ambs.getFirst());
		}
	}
	
	private void reportUnresolvedAmb(ATermList ambs) {
		Debug.log("Ambiguity found during implosion: ");
		
		for (ATerm amb : ambs) {
			String ambString = amb.toString();
			if (ambString.length() > 1000) ambString = ambString.substring(0, 1000) + "...";
			Debug.log("  amb: ", ambString);
		}
	}
	
	/** Get the token after the previous node's ending token, or null if N/A. */
	private IToken getStartToken(IToken prevToken) {
		PrsStream parseStream = tokenizer.getParseStream();
		
		if (prevToken == null) {
			return parseStream.getSize() == 0 ? null
			                                  : parseStream.getTokenAt(0);
		} else {
			int index = prevToken.getTokenIndex();
			
			if (parseStream.getSize() - index <= 1) {
				// UNDONE: Assumed empty tokens were harmful
				// throw new InvalidParseTreeException("Cannot create a AST node for an empty token");

				// Create new empty token
				// HACK: Assume TK_LAYOUT kind for empty tokens in AST nodes
				return tokenizer.makeToken(offset, TK_LAYOUT, true);
			} else {
				return parseStream.getTokenAt(index + 1); 
			}
		}
	}
	
	/** Get the last no-layout token for an AST node. */
	private IToken getEndToken(IToken startToken, IToken lastToken) {
		PrsStream parseStream = tokenizer.getParseStream();
		int begin = startToken.getTokenIndex();
		
		for (int i = lastToken.getTokenIndex(); i > begin; i--) {
			lastToken = parseStream.getTokenAt(i);
			if (lastToken.getKind() != TK_LAYOUT.ordinal()
					|| lastToken.getStartOffset() == lastToken.getEndOffset()-1)
				break;
		}
		
		return lastToken;
	}
	
	/** Implode any appl(_, _) that constructs a lex terminal. */
	protected void implodeLexical(ATermInt character) {
		assert tokenizer.getLexStream().getInputChars().length > offset
		    && character.getInt() == tokenizer.getLexStream().getCharValue(offset)
			: "Character from asfix stream (" + character.getInt()
			+ ") must be in lex stream ("
			+ (tokenizer.getLexStream().getInputChars().length > offset 
			   ? "???"
			   : (int) tokenizer.getLexStream().getCharValue(offset)) + ")";
		
		offset++;
	}

	/** Return the contents of the cons() attribute, or null if not found. */
	private static String getConstructor(ATermAppl attrs) {
		if (attrs.getName().equals("no-attrs"))
			return null;
		
		ATermList list = termAt(attrs, 0);
		
		for (int i = 0; i < list.getLength(); i++) {
			ATerm attr = list.elementAt(i);
			
			if (attr instanceof ATermAppl) {
				ATermAppl namedAttr = (ATermAppl) attr;
				if (namedAttr.getName().equals("term")) {
					namedAttr = (ATermAppl) namedAttr.getChildAt(TERM_CONS);
					
					if (namedAttr.getName().equals("cons")) {
						namedAttr = (ATermAppl) namedAttr.getChildAt(CONS_NAME);
						return namedAttr.getName();
					}
				}				
			}
		}
		
		return null; // no cons found
	}

	// TODO2: Optimize - cache getSort (especially for parametrized-sort!)
	/** 
	 * Get the RTG sort name of a production RHS, or for lists, the RTG element sort name.
	 */
    private static String getSort(ATermAppl rhs) {
    	ATerm node = rhs;
    	
    	while (node.getChildCount() > 0 && isAppl(node)) {
    		if (asAppl(node).getName().equals("sort"))
    			return applAt(node, 0).getName();
    		if (asAppl(node).getName().equals("alt"))
    			return getAltSortName(node);
    		if (asAppl(node).getName().equals("parameterized-sort"))
    			return getParameterizedSortName(node);
    		
    		node = termAt(node, 0);
    	}
    	
    	return null;
    }
    
    private static String getParameterizedSortName(ATerm node) {
    	StringBuilder result = new StringBuilder();
    	
    	result.append(applAt(node, PARAMETRIZED_SORT_NAME).getName());
    	result.append('_');
    	
		ATermList args = termAt(node, PARAMETRIZED_SORT_ARGS);
		
        for (ATermAppl arg = (ATermAppl) args.getFirst(); !args.getNext().isEmpty(); args = args.getNext()) {
			result.append(arg.getName());
		}
		
		return result.toString();
    }
    
    private static String getAltSortName(ATerm node) {
		String left = getSort(applAt(node, ALT_SORT_LEFT));
		String right = getSort(applAt(node, ALT_SORT_RIGHT));
		
		// HACK: In the RTG, alt sorts appear with a number at the end
		return left + "_" + right + "0";
    }
}
