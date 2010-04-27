package semanticdomain;

import semanticdomain.value.Value;
import semanticdomain.value.VString;
import semanticdomain.value.VBoolean;
import semanticdomain.value.VInt;

public class SemanticType extends SemanticVar {

	private String name = null;

	public SemanticType(String name) {
		this.name = name;
	}
	
	public Value castString(String value) {
		Value result = null;
		if (name.toLowerCase().equals("string"))
			result = new VString(value);
		else if (name.toLowerCase().equals("boolean"))
			result = new VBoolean(Boolean.parseBoolean(value));
		else if (name.toLowerCase().equals("int"))
			result = new VInt(Integer.parseInt(value));
		return result;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
