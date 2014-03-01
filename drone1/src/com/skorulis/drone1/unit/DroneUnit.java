package com.skorulis.drone1.unit;

import java.util.Arrays;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.skorulis.drone1.def.TurretPointDef;
import com.skorulis.drone1.def.UnitDef;

public class DroneUnit {

	
	public UnitDef def;
	private ModelInstance hullInstance;
	private DroneTurret[] turrets;
	private Vector3 position;
	private BoundingBox hullBounds;
	
	public DroneUnit target;
	
	public DroneUnit(UnitDef def) {
		this.def = def;
		turrets = new DroneTurret[def.hull.maxTurrets()];
		position = new Vector3(0,0,0);
	}
	
	public void setup(AssetManager assets) {
		Model model = assets.get(def.hull.modelName,Model.class);
		hullInstance = new ModelInstance(model);
		for(int i = 0; i < def.turrets.length; ++i) {
			if(def.turrets[i] != null) {
				turrets[i] = new DroneTurret(this,def.hull.turretPoints.get(i),def.turrets[i],assets);
			}
		}
		hullBounds = hullInstance.calculateBoundingBox(new BoundingBox());
		updateModelPositions();
	}
	
	public void update(float delta) {
		for(DroneTurret dt: turrets) {
			if(dt != null) {
				//dt.update(delta);
			}
		}
		updateModelPositions();
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
		this.position = vec;
	}
	
	public Vector3 position() {
		return position;
	}
	
	private void updateModelPositions() {
		hullInstance.transform.setTranslation(position);
		
		for(int i = 0; i < turrets.length; ++i) {
			if(turrets[i] != null) {
				if(target != null) {
					turrets[i].modelInstance.transform.setToLookAt(position(), target.position(), new Vector3(0,1,0));
				}
				TurretPointDef tpd = def.hull.turretPoints.get(i);
				Vector3 vec = tpd.getTurretPos(hullBounds);
				turrets[i].modelInstance.transform.setTranslation(vec.add(position));
			}
		}
	}
	
	
	
}
