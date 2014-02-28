package com.skorulis.drone1.def;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.collision.BoundingBox;

public class TurretDef extends BaseDef {

	public String modelName;
	public BoundingBox modelBounding;
	
	public ArrayList<String> allModels() {
		return new ArrayList<String>(Arrays.asList(modelName));
	}
	
	public void modelLoaded(Model model) {
		if(modelBounding == null) {
			modelBounding = model.calculateBoundingBox(new BoundingBox());
		}
		
	}
}
