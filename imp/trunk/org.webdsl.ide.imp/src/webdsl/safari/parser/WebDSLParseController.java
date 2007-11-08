package webdsl.safari.parser;

import lpg.runtime.IAst;

import org.spoofax.jsglr.NotImplementedException;
import org.strategoxt.imp.runtime.parser.SGLRParseController;

import aterm.ATerm;

/**
 * Parse controller for WebDSL language.
 * 
 * @author Lennart Kats <L.C.L.Kats add tudelft.nl>
 */
public class WebDSLParseController extends SGLRParseController {

	public WebDSLParseController() {
		super("/ln/WebDSL.tbl");
	}

	@Override
	protected IAst getAst(ATerm term) {
		// FIXME: Convert ATerm to AST
		throw new NotImplementedException();
	}
}
