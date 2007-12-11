package outliner;

import org.eclipse.imp.services.base.OutlinerBase;

import parser.ast.*;

/**
 * This file provides a skeletal implementation of the language-dependent aspects
 * of a source-program outliner.  This implementation is generated from a template
 * that is parameterized with respect to the name of the language, the package
 * containing the language-specific types for AST nodes and AbstractVisitors, and
 * the name of the outliner package and class.
 * 
 * @author suttons@us.ibm.com
 *
 */
public class StrategoJava15Outliner extends OutlinerBase {
	/*
	 * A visitor for ASTs.  Its purpose is to create outline-tree items
	 * for AST node types that are to appear in the outline view.
	 */
	private final class OutlineVisitor extends AbstractVisitor {

		public OutlineVisitor() {
			super();
			// TODO:  Replace the language name here with the name of your
			// own language (provided that your language isn't called "Leg"
			// and you want a heading like this at the top of your outline)
			// UNDONE: pushTopItem("", null);
		}

		public void unimplementedVisitor(java.lang.String s) {
			// Sometimes useful for debugging
			// System.out.println(s);
		}

		// START_HERE
		// Include visit(..) and endVisit(..) methods for each AST node
		// type that is to appear in the outline tree.  Typically, the
		// visit(..) method is used to create an outline item and to
		// push it onto a stack that contains the current ancestor items
		// (the top member of which will be the parent of the current item).
		// The  endVisit(..) method is used to pop items from the stack.
		// To aid in the implementation of these methods, methods to push
		// and pop top-level and sub-level outline items are provided.  The
		// method addSubItem(..) allows you to add an outline item at the
		// current level of indentation without changing the level.  Some
		// examples follow ...
		
		// Stratego

		public boolean visit(Module n) {
			Object descriptor = n.getChildren().get(0);
			
			java.lang.String text = descriptor.toString();
			pushTopItem(text, n);
			return true;
		}

		public boolean visit(Def_StrategoHostSort n) {
			Object descriptor = n.getChildren().get(0).getChildren().get(0);
			
			java.lang.String text = descriptor.toString();
			pushSubItem(text, n);
			return true;
		}
		
		public void endVisit(Def_StrategoHostSort n) {
			popSubItem();
		}

		public boolean visit(Overlay n) {
			Object descriptor = n.getChildren().get(0);
			
			java.lang.String text = descriptor.toString();
			pushSubItem(text, n);
			return true;
		}
		
		public void endVisit(Overlay n) {
			popSubItem();
		}

		public boolean visit(Rules n) {
			Object descriptor = "rules";
			
			java.lang.String text = descriptor.toString();
			pushSubItem(text, n);
			return true;
		}
		
		public void endVisit(Rules n) {
			popSubItem();
		}

		public boolean visit(Strategies n) {
			Object descriptor = "strategies";
			
			java.lang.String text = descriptor.toString();
			pushSubItem(text, n);
			return true;
		}
		
		public void endVisit(Strategies n) {
			popSubItem();
		}

		public boolean visit(Overlays n) {
			Object descriptor = "overlays";
			
			java.lang.String text = descriptor.toString();
			pushSubItem(text, n);
			return true;
		}
		
		public void endVisit(Overlays n) {
			popSubItem();
		}
		
		// Java

		public boolean visit(ClassDec n) {
			Object descriptor = n.getChildren().get(0);
			
			java.lang.String text = descriptor.toString();
			pushSubItem(text, n);
			return true;
		}
		
		public void endVisit(ClassDec n) {
			popSubItem();
		}

		public boolean visit(MethodDec n) {
			Object descriptor = n.getChildren().get(0);
			
			java.lang.String text = descriptor.toString();
			pushSubItem(text, n);
			return true;
		}
		
		public void endVisit(MethodDec n) {
			popSubItem();
		}
	}

	protected void sendVisitorToAST(Object node) {
		ASTNode root = (ASTNode) node;
		root.accept(new OutlineVisitor());
	}

}
