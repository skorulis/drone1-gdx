package com.skorulis.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drone1.scene.SceneNode;

public class IsoFollowCamera {

	private SceneNode item;
	private Camera cam;
	
	public IsoFollowCamera(SceneNode item) {
		this.item = item;
		resizeViewport();
	}
	
	public void resizeViewport() {
		cam = new OrthographicCamera(20, 20 * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        cam.near = 0.1f;
        cam.far = 100f;
	}
	
	public void update(float delta) {
		Vector3 pos = item.absTransform().getTranslation(new Vector3());
		cam.position.set(pos.x+5, 5, pos.z+5);
		cam.direction.set(-1, -1, -1);
		cam.update();
	}
	
	public Camera cam() {
		return cam;
	}
}
