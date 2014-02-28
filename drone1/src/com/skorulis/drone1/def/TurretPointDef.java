package com.skorulis.drone1.def;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class TurretPointDef {
	
	public Vector3 position;
	
	public float size;
	
	public TurretPointDef(Vector3 position) {
		this.position = position;
	}
	
	public Vector3 getTurretPos(BoundingBox box) {
		Vector3 ret = new Vector3();
		ret.x = position.x * (position.x > 0 ? box.max.x : box.min.x);
		ret.y = position.y * (position.y > 0 ? box.max.y : box.min.y);
		ret.z = position.z * (position.z > 0 ? box.max.z : box.min.z);
		Gdx.app.log("TPD", "LOC " + ret);
		return ret;
	}
	
}
