package com.skorulis.drone1.def;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.math.Vector3;

public class HullDef extends BaseDef {

	public String modelName;
	public ArrayList<TurretPointDef> turretPoints;
	
	public HullDef() {
		turretPoints = new ArrayList<TurretPointDef>();
	}
	
	public ArrayList<String> allModels() {
		return new ArrayList<String>(Arrays.asList(modelName));
	}
	
	public void addTurretPoint(Vector3 position) {
		turretPoints.add(new TurretPointDef(position));
	}
	
	public int maxTurrets() {
		return turretPoints.size();
	}
	
}
