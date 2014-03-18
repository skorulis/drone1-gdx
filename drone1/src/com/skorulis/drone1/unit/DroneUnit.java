package com.skorulis.drone1.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.skorulis.drone1.def.UnitDef;

public class DroneUnit {

	
	public UnitDef def;
	private ModelInstance hullInstance;
	private DroneTurret[] turrets;
	public BoundingBox hullBounds;
	
	public DroneUnit target;
	
	public DroneUnit(UnitDef def) {
		this.def = def;
		turrets = new DroneTurret[def.hull.maxTurrets()];
	}
	
	public void setup(AssetManager assets) {
		Model model = assets.get(def.hull.modelName,Model.class);
		hullInstance = new ModelInstance(model);
		hullBounds = hullInstance.calculateBoundingBox(new BoundingBox());
		for(int i = 0; i < def.turrets.length; ++i) {
			if(def.turrets[i] != null) {
				turrets[i] = new DroneTurret(this,def.hull.turretPoints.get(i),def.turrets[i]);
				turrets[i].setup(assets);
			}
		}
	}
	
	public void update(float delta) {
		for(DroneTurret dt: turrets) {
			if(dt != null) {
				dt.update(delta);
			}
		}
	}
	
	public void render(ModelBatch batch, Environment environment) {
		for(DroneTurret dt : turrets) {
			if(dt != null) {
				dt.render(batch, environment);
			}
		}
		batch.render(hullInstance,environment);
	}
	
	public void setPosition(Vector3 vec) {
		hullInstance.transform.setToTranslation(vec);
	}
	
	public Vector3 position() {
		return hullInstance.transform.getTranslation(new Vector3());
	}
	
	public Matrix4 absTransform() {
		return hullInstance.transform;
	}
	
	
	
}
