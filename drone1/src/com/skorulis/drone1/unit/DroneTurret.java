package com.skorulis.drone1.unit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.skorulis.drone1.def.TurretDef;
import com.skorulis.drone1.def.TurretPointDef;
import com.skorulis.drone1.scene.SceneNode;

public class DroneTurret implements SceneNode {

	private DroneUnit parent;
	
	public Matrix4 relTransform;
	public ModelInstance modelInstance;
	private TurretPointDef pointDef;
	private TurretDef def;
	
	public DroneTurret(DroneUnit unit, TurretPointDef pointDef, TurretDef def, AssetManager assets) {
		this.parent = unit;
		this.pointDef = pointDef;
		this.def = def;
		
		Model model = assets.get(def.modelName);
		def.modelLoaded(model);
		modelInstance = new ModelInstance(model);
		
		float scale = pointDef.getScale(def.modelBounding);
		modelInstance.transform = modelInstance.transform.scl(scale);
	}
	
	public void render(ModelBatch batch, Environment environment) {
		batch.render(modelInstance, environment);
	}
	
	public Matrix4 absTransform() {
		return modelInstance.transform;
	}
	
	public Matrix4 relTransform() {
		return relTransform;
	}
	
}
