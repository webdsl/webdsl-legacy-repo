package webdsl.safari.parser;

import java.io.IOException;

import lpg.runtime.IAst;

import org.spoofax.jsglr.InvalidParseTableException;
import org.spoofax.jsglr.NotImplementedException;
import org.strategoxt.imp.runtime.parser.SGLRParseController;

import webdsl.Activator;

import aterm.ATerm;

/**
 * Parse controller for WebDSL language.
 * 
 * @author Lennart Kats <L.C.L.Kats add tudelft.nl>
 */
public class WebDSLParseController extends SGLRParseController {

	public WebDSLParseController() 
			throws IOException, InvalidParseTableException {
		
		super(Activator.class.getResourceAsStream("/syntax/WebDSL.tbl"));
	}

	@Override
	protected IAst getAst(ATerm term) {
		// FIXME: Convert ATerm to AST
		throw new NotImplementedException();
	}
}
