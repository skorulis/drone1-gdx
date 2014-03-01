package com.skorulis.drone1.def;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector3;


public class DefManager {

	public Map<String,HullDef> hulls;
	public Map<String,TurretDef> turrets;
	
	public DefManager() {
		hulls = new HashMap<String,HullDef>();
		turrets = new HashMap<String, TurretDef>();
		loadHulls();
		loadTurrets();
	}
	
	private void loadHulls() {
		HullDef h = new HullDef();
		h.name = "hull1";
		h.modelName = "data/hull1.g3db";
		h.addTurretPoint( new Vector3(0, 1, 0), 2.0f);
		hulls.put(h.name, h);
	}
	
	private void loadTurrets() {
		TurretDef t = new TurretDef();
		t.name = "turret1";
		t.modelName = "data/turret1.g3db";
		t.forward = new Vector3(0,0,1);
		turrets.put(t.name, t);
	}
	
	public HullDef getHull(String name) {
		HullDef def = hulls.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No hull named " + name);
		}
		return def;
	}
	
	public TurretDef getTurret(String name) {
		TurretDef def = turrets.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No turret named " + name);
		}
		return def;
	}
	
}
