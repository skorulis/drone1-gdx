package com.skorulis.drone1.def;

import java.util.ArrayList;

public class UnitDef extends BaseDef {

	public HullDef hull;
	public TurretDef turret;
	
	public ArrayList<String> allModels() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.addAll(hull.allModels());
		ret.addAll(turret.allModels());
		return ret;
	}
	
}
