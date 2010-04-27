package semanticdomain;

import semanticdomain.value.VAddress;

public class Context extends SemanticVar {

	private static final String delim = ".";
	private String prefix;

	public Context() {
		this("");
	}
	
	public Context(String prefix) {
		this.prefix = prefix;
	}
	
	public VAddress makeVarAddress(String varName) {
		String dot = "";
		if (prefix != null && prefix.length() > 0)
			dot = delim;
		return new VAddress(prefix + dot + varName);
	}
	
	public String getValue() {
		return this.prefix;
	}
	
	public void setValue(String prefix) {
		this.prefix = prefix;
	}
	
	public Context extend(String segment) {
		return new Context(prefix + delim + segment);
	}
	
}
