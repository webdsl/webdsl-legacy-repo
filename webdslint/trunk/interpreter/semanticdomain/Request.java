package semanticdomain;
import java.util.HashMap;
import java.util.Map;

import semanticdomain.value.VString;

//public class Request extends SemanticVar {
//	
//	private Map<VString, VString> pairs = new HashMap<VString, VString>();
//
//	public VString get(VString key) {
//		return pairs.get(key);
//	}
//	
//	public void put(VString key, VString value) {
//		pairs.put(key, value);
//	}
//}

public class Request extends SemanticVar {
	
	private Map<String, String> pairs = new HashMap<String, String>();

	public boolean contains(String key) {
		return pairs.containsKey(key);
	}
	
	public String get(String key) {
		return pairs.get(key);
	}
	
	public void put(String key, String value) {
		pairs.put(key, value);
	}
}