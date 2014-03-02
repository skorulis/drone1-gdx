package com.skorulis.drone1.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drone1.scene.SceneNode;

public class GameLevel implements SceneNode, Disposable{

	public Matrix4 transform;
	public Model model;
	public ModelInstance modelInstance;
	
	public GameLevel(int width, int depth) {
		transform = new Matrix4();
		ModelBuilder builder = new ModelBuilder();
		
		Texture texture = new Texture(Gdx.files.internal("data/rock.png"));
		
		Material material = new Material();
		material.set(new TextureAttribute(TextureAttribute.Diffuse, texture));
		
		model = builder.createRect(0, 0, 0, 0, 0, depth, width, 0, depth, width, 0, 0, 0, 1, 0, material, Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		modelInstance = new ModelInstance(model);
	}
	
	@Override
	public Matrix4 absTransform() {
		return transform;
	}

	@Override
	public Matrix4 relTransform() {
		return transform;
	}

	@Override
	public void render(ModelBatch batch, Environment environment) {
		batch.render(modelInstance,environment);
	}

	@Override
	public void dispose() {
		model.dispose();
	}

	
	
}
