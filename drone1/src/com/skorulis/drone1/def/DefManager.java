package com.skorulis.drone1.def;

import java.util.HashMap;
import java.util.Map;


public class DefManager {

	public Map<String,HullDef> hulls;
	
	public DefManager() {
		hulls = new HashMap<String,HullDef>();
		loadHulls();
	}
	
	private void loadHulls() {
		HullDef h = new HullDef();
		h.name = "hull1";
		h.modelName = "hull1.g3db";
		hulls.put(h.name, h);
	}
	
	public HullDef getHull(String name) {
		HullDef def = hulls.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No hull named " + name);
		}
		return def;
	}
	
}
