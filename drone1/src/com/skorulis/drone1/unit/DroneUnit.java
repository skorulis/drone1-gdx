package com.skorulis.drone1.unit;

import java.util.Arrays;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drone1.def.UnitDef;

public class DroneUnit {

	
	public UnitDef def;
	private ModelInstance hullInstance;
	private ModelInstance[] turrets;
	private Vector3 position;
	
	public DroneUnit(UnitDef def) {
		this.def = def;
		turrets = new ModelInstance[def.hull.maxTurrets()];
		position = new Vector3(0,0,0);
	}
	
	public void setup(AssetManager assets) {
		Model model = assets.get(def.hull.modelName,Model.class);
		hullInstance = new ModelInstance(model);
		for(int i = 0; i < def.turrets.length; ++i) {
			if(def.turrets[i] != null) {
				model = assets.get(def.turrets[i].modelName);
				turrets[i] = new ModelInstance(model);
			}
		}
		updateModelPositions();
	}
	
	public void render(ModelBatch batch, Environment environment) {
		batch.render(Arrays.asList(turrets),environment);
		batch.render(hullInstance,environment);
	}
	
	public void setPosition(Vector3 vec) {
		this.position = vec;
		updateModelPositions();
	}
	
	private void updateModelPositions() {
		hullInstance.transform.setTranslation(position);
		for(int i = 0; i < turrets.length; ++i) {
			if(turrets[i] != null) {
				Vector3 vec = def.hull.turretPoints.get(i).position;
				turrets[i].transform.setTranslation(vec);
			}
		}
	}
	
}
