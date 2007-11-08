package webdsl.safari.parser;

import lpg.runtime.IAst;

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
		try {
			// FIXME: Don't use absolute parse table path
		} catch (Exception x) {
			// TODO: Proper parse table exception handling
			throw new RuntimeException(x);
		}
	}

	@Override
	protected IAst getAst(ATerm term) {
		// FIXME: Convert ATerm to AST
		return null;
	}
}
