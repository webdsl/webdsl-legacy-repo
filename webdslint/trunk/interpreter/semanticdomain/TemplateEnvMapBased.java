package semanticdomain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import semanticdomain.value.TIdentifier;

public class TemplateEnvMapBased extends SemanticVar {
	
	private Map<TIdentifier, Template> map = new HashMap<TIdentifier, Template>();
	
	public TemplateEnvMapBased() {
	}
	
	public Template get(TIdentifier key) {
		if (key == null)
			return null;
		else
			return map.get(key);
	}
	
	public void put(TIdentifier key, Template value) {
		map.put(key, value);
	}
	
	public TemplateEnvMapBased extend(TIdentifier key, Template value) {
		TemplateEnvMapBased newEnv = new TemplateEnvMapBased();
		for (Iterator<TIdentifier> iter = map.keySet().iterator(); iter.hasNext();) {
			TIdentifier copyKey = iter.next();
			Template copyValue = map.get(copyKey);
			newEnv.map.put(copyKey, copyValue);
		}
		newEnv.map.put(key, value);
		return newEnv;
	}
}
