package com.skorulis.drone1.def;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class TurretPointDef {
	
	public Vector3 position;
	
	public float size; //Size along the aligning axis
	
	public TurretPointDef(Vector3 position, float size) {
		this.position = position;
		this.size = size;
	}
	
	public Vector3 getTurretPos(BoundingBox box) {
		Vector3 ret = new Vector3();
		ret.x = position.x * (position.x > 0 ? box.max.x : box.min.x);
		ret.y = position.y * (position.y > 0 ? box.max.y : box.min.y);
		ret.z = position.z * (position.z > 0 ? box.max.z : box.min.z);
		return ret;
	}
	
	public float getScale(BoundingBox box) {
		Vector3 dim = box.getDimensions();
		float scale = 1;
		scale = Math.min(scale, size / dim.x);
		scale = Math.min(scale, size / dim.y);
		scale = Math.min(scale, size / dim.z);
		
		return scale;
	}
	
}
