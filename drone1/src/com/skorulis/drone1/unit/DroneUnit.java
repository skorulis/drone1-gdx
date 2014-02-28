package com.skorulis.drone1.unit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.skorulis.drone1.def.UnitDef;

public class DroneUnit {

	public UnitDef def;
	private ModelInstance hullInstance;
	
	public DroneUnit(UnitDef def) {
		this.def = def;
	}
	
	public void setup(AssetManager assets) {
		Model model = assets.get(def.hull.modelName,Model.class);
		hullInstance = new ModelInstance(model);
	}
	
	public void render(ModelBatch batch, Environment environment) {
		batch.render(hullInstance,environment);
	}
	
}
