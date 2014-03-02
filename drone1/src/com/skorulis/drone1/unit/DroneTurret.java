package com.skorulis.drone1.unit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drone1.def.TurretDef;
import com.skorulis.drone1.def.TurretPointDef;
import com.skorulis.drone1.scene.SceneNode;

public class DroneTurret implements SceneNode {

	private DroneUnit parent;
	
	public Matrix4 relTransform;
	public ModelInstance modelInstance;
	private TurretPointDef pointDef;
	private TurretDef def;
	private float scale;
	private Vector3 offset;
	
	public DroneTurret(DroneUnit unit, TurretPointDef pointDef, TurretDef def) {
		this.parent = unit;
		this.pointDef = pointDef;
		this.def = def;
		this.relTransform = new Matrix4();
	}
	
	public void setup(AssetManager assets) {
		Model model = assets.get(def.modelName);
		def.modelLoaded(model);
		modelInstance = new ModelInstance(model);
		
		offset = pointDef.getTurretPos(parent.hullBounds);
		scale = pointDef.getScale(def.modelBounding);
	}
	
	public void update(float delta) {
		if(parent.target != null) {
			relTransform.setToTranslation(offset);
			relTransform.scl(scale);
			Vector3 loc = absTransform().getTranslation(new Vector3());
			Vector3 tLoc = parent.target.absTransform().getTranslation(new Vector3());
			Vector3 dir = tLoc.sub(loc).nor();
			dir.y = 0;
			float angle = def.forward.dot(dir);
			angle = (float)Math.acos(angle);
			Vector3 cross = def.forward.cpy().crs(dir);
			relTransform.rotate(cross, (float)(angle * 180.0 / Math.PI));
		}
	}
	
	public void render(ModelBatch batch, Environment environment) {
		modelInstance.transform = parent.absTransform().cpy().mul(relTransform);
		batch.render(modelInstance, environment);
	}
	
	public Matrix4 absTransform() {
		return modelInstance.transform;
	}
	
	public Matrix4 relTransform() {
		return relTransform;
	}
	
}
