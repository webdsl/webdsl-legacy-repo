package org.strategoxt.imp.runtime.parser.ast;

import java.util.ArrayList;

import aterm.ATermAppl;

import lpg.runtime.ILexStream;
import lpg.runtime.IToken;

/**
 * Default ATermAstNode factory.
 * 
 * Should be overridden to supply specialized AstNode classes for a specific grammar.
 * 
 * @author Lennart Kats <L.C.L.Kats add tudelft.nl>
 */
public class SGLRAstNodeFactory {
	/**
	 * Create a non-terminal AST node
	 */
	public SGLRAstNode createNonTerminal(String constructor, IToken leftToken, IToken rightToken,
			ArrayList<SGLRAstNode> children) {
		
		return new SGLRAstNode(constructor, leftToken, rightToken, children);
	}
	
	/**
	 * Create a terminal AST node.
	 */
	public SGLRAstNode createTerminal(String contents, IToken leftToken, IToken rightToken) {
		return new SGLRAstNode(contents, leftToken, rightToken);
	}
	
	/**
	 * Create a AST node list. 
	 */
	public SGLRAstNode createList(IToken leftToken, IToken rightToken,
			ArrayList<SGLRAstNode> children) {
		
		return new SGLRAstNode(SGLRAstNode.LIST_CONSTRUCTOR, leftToken, rightToken, children);
	}
	
	/**
	 * Get the token kind for a given sort.
	 */
	public int getTokenKind(ATermAppl sort) {
		// TODO: More default token kinds
		//       e.g., for numbers, opererators, comments 

		if (isLayoutSort(sort)) {
			return SGLRParsersym.TK_LAYOUT;
		} else if (sort.getName().equals("lex")) {
			return SGLRParsersym.TK_IDENTIFIER;
		} else {
			return SGLRParsersym.TK_KEYWORD;
		}
	}
	
	// Utility methods
	
	public final SGLRAstNode createTerminal(IToken token) {
		ILexStream lex = token.getPrsStream().getLexStream();
		
		int length = token.getEndOffset() - token.getStartOffset();
		StringBuilder tokenContents = new StringBuilder(length);
		
		for (int i = token.getStartOffset(); i < token.getEndOffset(); i++) {
			tokenContents.append(lex.getCharValue(i));
		}
		
		return createTerminal(tokenContents.toString(), token, token);
	}

	private static boolean isLayoutSort(ATermAppl sort) {
		ATermAppl details = (ATermAppl) sort.getChildAt(0);
	
		if (details.getName().equals("opt"))
			details = (ATermAppl) details.getChildAt(0);
			
		return details.getName().equals("layout");
	}
}
