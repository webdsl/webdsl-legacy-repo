package semanticdomain;

import semanticdomain.value.Identifier;
import semanticdomain.value.Value;

public class Env extends SemanticVar {
	
	private Env prev = null;
	
	private Identifier key;
	private Value value;

	public Env(Identifier key, Value value, Env prev) {
		this.key = key;
		this.value = value;
		this.prev = prev;
	}
	
	public Env() {
		
	}
	
	public Value get(Identifier key) {
		if (this.key != null && this.key.equals(key))
			return value;
		else if (prev != null)
			return prev.get(key);
		else
			return null;
	}
	
	public Env extend(Identifier key, Value value) {
		return new Env(key, value, this);
	}
}
