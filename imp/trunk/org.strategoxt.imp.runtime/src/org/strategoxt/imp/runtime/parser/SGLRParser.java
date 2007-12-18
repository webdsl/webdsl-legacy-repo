package org.strategoxt.imp.runtime.parser;

import java.io.IOException;

import lpg.runtime.Monitor;
import lpg.runtime.PrsStream;

import org.eclipse.imp.parser.IParser;
import org.spoofax.NotImplementedException;
import org.spoofax.jsglr.ParseTable;
import org.spoofax.jsglr.SGLR;
import org.spoofax.jsglr.SGLRException;
import org.strategoxt.imp.runtime.Debug;
import org.strategoxt.imp.runtime.Environment;
import org.strategoxt.imp.runtime.parser.ast.AstNode;
import org.strategoxt.imp.runtime.parser.ast.AsfixConverter;
import org.strategoxt.imp.runtime.parser.tokens.SGLRParsersym;
import org.strategoxt.imp.runtime.parser.tokens.SGLRTokenKindManager;
import org.strategoxt.imp.runtime.parser.tokens.SGLRTokenizer;

import aterm.ATerm;

/**
 * IParser implementation for SGLR.
 *
 * @author Lennart Kats <L.C.L.Kats add tudelft.nl>
 */ 
public class SGLRParser implements IParser {
	private final SGLR parser;
	
	private final String startSymbol;
	
	private final AsfixConverter converter;
	
	private final SGLRTokenizer tokenizer;
	
	// Simple accessors
	
	public SGLRTokenizer getTokenizer() {
		return tokenizer; 
	}

	public int getEOFTokenKind() {
		return SGLRParsersym.TK_EOF;
	}

	public PrsStream getParseStream() {
		return getTokenizer().getParseStream();
	}
	
	// Initialization and parsing
	
	public SGLRParser(SGLRTokenKindManager tokenManager, ParseTable parseTable, String startSymbol) {	
		this.startSymbol = startSymbol;

		tokenizer = new SGLRTokenizer();		
		converter = new AsfixConverter(tokenManager, tokenizer);
		parser = Environment.createSGLR(parseTable);
	}
	
	/**
	 * Parse an input, returning the AST and initializing the parse stream.
	 * 
	 * @return  The abstract syntax tree.
	 */
	public AstNode parse(char[] input, String filename) throws SGLRException, IOException {
		Debug.startTimer();

		// Read stream using tokenizer/lexstream
		tokenizer.init(input, filename);
		
		ATerm asfix = parser.parse(tokenizer.toByteStream(), startSymbol);
			
		Debug.stopTimer("File parsed");
		Debug.startTimer();
		
		AstNode result = converter.implode(asfix);
			
		Debug.stopTimer("Parse tree imploded");
		
		return result;
	}
	
	// LPG legacy / compatibility

	@Deprecated
	public Object parser(Monitor monitor, int error_repair_count) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public String[] orderedTerminalSymbols() {
		// TODO2: SGLRParser.orderedTerminalSymbols() - should map token kinds to names
		throw new NotImplementedException();
	}
}
