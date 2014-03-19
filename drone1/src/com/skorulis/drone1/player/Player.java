package com.skorulis.drone1.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.skorulis.drone1.unit.DroneUnit;

public class Player {

	public ArrayList<DroneUnit> allUnits;
	public DroneUnit unit;
	private float speed = 7.0f;
	
	public Player(DroneUnit unit) {
		allUnits = new ArrayList<DroneUnit>();
		this.unit = unit;
	}
	
	public void update(float delta) {
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			unit.absTransform().translate(-delta*speed, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			unit.absTransform().translate(delta*speed, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			unit.absTransform().translate(0, 0, -delta*speed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			unit.absTransform().translate(0, 0, delta*speed);
		}
		
		
	}
	
}
