package org.webdsl.tools.strategoxt;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class StrategoProgram {
	private static final HashMap<String, StrategoProgram> allPrograms = new HashMap<String, StrategoProgram>();

	private final Interpreter interpreter;
	
	private StrategoProgram(Interpreter interpreter) {
		this.interpreter = interpreter;
	}
	
	/**
	 * Register a new Stratego program.
	 */
	public static synchronized StrategoProgram get(String programName, Class<?> tableOwner) {
		try {
			StrategoProgram result = get(programName);
			if (result == null)
				result = register(programName, tableOwner.getResourceAsStream("/" + programName + ".ctree"));
			return result;
		} catch (InterpreterException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Register a new Stratego program.
	 */
	private static synchronized StrategoProgram register(String programName, InputStream program) throws InterpreterException, IOException {
		Interpreter interpreter = Environment.createInterpreter();
		interpreter.load(program);

		StrategoProgram result = new StrategoProgram(interpreter);
		allPrograms.put(programName, result);
		
		return result;
	}
	
	private static synchronized StrategoProgram get(String programName) {
		return allPrograms.get(programName);
	}
	
	// TODO: Finer grained synchronization (but probably register in a synchronized fashion)
	
	public synchronized IStrategoTerm invoke(String strategy, String string) {
		return invoke(strategy, Environment.getWrappedTermFactory().makeString(string));
	}
	
	public synchronized IStrategoTerm invoke(String strategy, IStrategoTerm term) {
		interpreter.setCurrent(term);
		
		try {
			boolean success = interpreter.invoke(strategy);
			if (!success) return Environment.getWrappedTermFactory().makeString("Evaluation failed");
		} catch (InterpreterException e) {
			throw new RuntimeException(e);
		}
		
		// TODO: Better handling of failure
		
		return interpreter.current();
	}
}
