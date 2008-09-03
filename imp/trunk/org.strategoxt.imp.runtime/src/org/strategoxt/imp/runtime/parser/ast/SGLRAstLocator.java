package org.strategoxt.imp.runtime.parser.ast;

import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.parser.AstLocator;
import org.strategoxt.imp.runtime.parser.tokens.SGLRToken;

public class SGLRAstLocator extends AstLocator {

	@Override
	public Object findNode(Object node, int startOffset, int endOffset) {
		if (node instanceof SGLRToken)
			node = ((SGLRToken) node).getAstNode();
		
		return super.findNode(((AstNode) node), startOffset); // , endOffset is not supported
	}
	
	@Override
	public Object findNode(Object node, int offset) {
		if (node instanceof SGLRToken)
			node = ((SGLRToken) node).getAstNode();
		
		return super.findNode(node, offset);
	}
	
	@Override
	public int getStartOffset(Object node) {
//		return super.getStartOffset(((SGLRToken)node).getAstNode());
		return ((SGLRToken)node).getStartOffset();
	}
	
	@Override
	public int getEndOffset(Object node) {
//		return super.getEndOffset(((SGLRToken)node).getAstNode());
		return ((SGLRToken)node).getEndOffset();
	}
	
	@Override
	public int getLength(Object node) {
//		return super.getLength(((SGLRToken)node).getAstNode());
		return getEndOffset(node) - getStartOffset(node);
	}
	
	@Override
	public IPath getPath(Object node) {
		return super.getPath(((SGLRToken)node).getAstNode());
	}
	
}
