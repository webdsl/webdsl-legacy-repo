package semanticdomain;

import semanticdomain.value.VIdentifier;
import semanticdomain.value.Value;

public class Env extends SemanticVar {
	
	private Env prev = null;
	
	private VIdentifier key;
	private Value value;

	public Env(VIdentifier key, Value value, Env prev) {
		this.key = key;
		this.value = value;
		this.prev = prev;
	}
	
	public Env() {
		
	}
	
	public Value get(VIdentifier key) {
		if (this.key != null && this.key.equals(key))
			return value;
		else if (prev != null)
			return prev.get(key);
		else
			return null;
	}
	
	public Env extend(VIdentifier key, Value value) {
		return new Env(key, value, deepCopy());
	}

	public Env deepCopy()
	{
		if (prev == null)
			return new Env(key, value, null);
		else
			return new Env(key, value, prev.deepCopy());
	}
	
	// is called to extend an Env in place (modifies this)
	public void update(VIdentifier key, Value value) {
		if (this.key != null && this.key.equals(key))
			this.value = value;
		else if (prev != null)
			prev.update(key, value);
		else
			prev = new Env(key, value, null);
	}

}
