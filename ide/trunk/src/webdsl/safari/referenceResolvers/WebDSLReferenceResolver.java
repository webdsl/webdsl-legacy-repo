package webdsl.safari.referenceResolvers;

import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.IReferenceResolver;

import webdsl.safari.parser.WebDSLParser;
import webdsl.safari.parser.Ast.*;

public class WebDSLReferenceResolver implements IReferenceResolver,
		ILanguageService {

	public WebDSLReferenceResolver() {
	}

	/**
	 * Get the text associated with a given node for use in a link
	 * from (or to) that node
	 */
	public String getLinkText(Object node) {
		// TODO:  Replace the call to super.getLinkText(..) with an implementation
		// suitable to you language and link types
		return "Link text - " + node.toString();
	}

	/**
	 * Get the target for a given source node in the AST represented by a
	 * given Parse Controller.
	 */
	public Object getLinkTarget(Object node, IParseController controller) {
		// START_HERE
		// Replace the given implementation with an implementation
		// that is suitable to you language and link types

		// NOTE:  The code shown in this method body works with the
		// example grammar used in the SAFARI language-service templates.
		// It may be adaptable for use with other languages.  HOWEVER,
		// this particular code is not essential to reference resolvers
		// in general, and the user should provide an implementation
		// that is appropriate to the language and AST structure for
		// which the service is being defined.
		
		System.out.print("Hit->");
		
		if (node instanceof GetId && controller.getCurrentAst() != null) {
			GetId id = (GetId) node;
			WebDSLParser parser = (WebDSLParser) controller.getParser();
			WebDSLParser.SymbolTable symtab = parser.getEnclosingSymbolTable(id);

			System.out.println("Hit: " + node + (controller.getCurrentAst() != null) + "->" + id + "->" + symtab.findDeclaration(id.toString()));
			return symtab.findDeclaration(id.toString());
		}

		System.out.println("Hit: " + node.getClass() + "-> null");
		return null;
	}
}
