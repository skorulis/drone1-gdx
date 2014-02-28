package com.skorulis.drone1.def;

import java.util.ArrayList;

public class UnitDef extends BaseDef {

	public HullDef hull;
	public TurretDef[] turrets;
	
	public UnitDef(HullDef hull) {
		this.hull = hull;
		turrets = new TurretDef[1];
	}
	
	public ArrayList<String> allModels() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.addAll(hull.allModels());
		for(TurretDef td : turrets) {
			if(td != null) {
				ret.addAll(td.allModels());
			}
		}
		return ret;
	}
	
	public void setTurret(int pos, TurretDef turret) {
		turrets[pos] = turret;
	}
	
}
