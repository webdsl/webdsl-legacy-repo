package semanticdomain;
import java.util.HashMap;
import java.util.Map;

import semanticdomain.value.VString;

public class Request extends SemanticVar {
	
	private Map<VString, VString> pairs = new HashMap<VString, VString>();

	public VString get(VString key) {
		return pairs.get(key);
	}
	
	public void put(VString key, VString value) {
		pairs.put(key, value);
	}
}
