package semanticdomain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import semanticdomain.value.VTIdentifier;

public class TemplateEnvMapBased extends SemanticVar {
	
	private Map<VTIdentifier, Template> map = new HashMap<VTIdentifier, Template>();
	
	public TemplateEnvMapBased() {
	}
	
	public Template get(VTIdentifier key) {
		if (key == null)
			return null;
		else
			return map.get(key);
	}
	
	public void put(VTIdentifier key, Template value) {
		map.put(key, value);
	}
	
	public TemplateEnvMapBased extend(VTIdentifier key, Template value) {
		TemplateEnvMapBased newEnv = new TemplateEnvMapBased();
		for (Iterator<VTIdentifier> iter = map.keySet().iterator(); iter.hasNext();) {
			VTIdentifier copyKey = iter.next();
			Template copyValue = map.get(copyKey);
			newEnv.map.put(copyKey, copyValue);
		}
		newEnv.map.put(key, value);
		return newEnv;
	}
}
