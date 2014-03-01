package com.skorulis.drone1;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drone1.def.DefManager;
import com.skorulis.drone1.def.UnitDef;
import com.skorulis.drone1.unit.DroneUnit;

public class MyGdxGame implements ApplicationListener {
	public PerspectiveCamera cam;
    public ModelInstance instance;
    public ModelBatch modelBatch;
    public Environment environment;
    public CameraInputController camController;
    public FPSLogger fpsLogger;
    public AssetManager assets;
    public boolean loading;
    
    public DefManager defManager;
    public DroneUnit unit;
    
    public DroneUnit unit2;
    public float t = 0;
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(5f, 5f, 5f);
        cam.lookAt(0,0,0);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();
        
        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        defManager = new DefManager();
        UnitDef uDef = new UnitDef(defManager.getHull("hull1"));
        uDef.setTurret(0, defManager.getTurret("turret1"));
        unit = new DroneUnit(uDef);
        unit2 = new DroneUnit(uDef);
        
        unit2.target = unit;
        
        assets = new AssetManager();
        
        ArrayList<String> models = new ArrayList<String>();
        models.addAll(unit.def.allModels());
        for(String s : models) {
        	assets.load(s, Model.class);
        }
        loading = true;
        
        fpsLogger = new FPSLogger();
	}
	
	private void doneLoading() {
		unit.setup(assets);
		unit2.setup(assets);
        loading = false;
    }

	@Override
	public void dispose() {
		modelBatch.dispose();
		assets.clear();
	}

	@Override
	public void render() {
		if(loading) {
			if(assets.update()) {
				doneLoading();
			} else {
				return;
			}
		}
		float delta = Gdx.graphics.getDeltaTime();
		
		t += delta;
		
		camController.update();
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
        modelBatch.begin(cam);
        unit.render(modelBatch, environment);
        unit2.render(modelBatch, environment);
        modelBatch.end();
        //fpsLogger.log();
        
        float x = (float) Math.sin(t) * 5;
        float z = (float) Math.cos(t) * 4;
        
        unit.setPosition(new Vector3(x,0.0f,z));
        
        unit.update(delta);
		unit2.update(delta);
        
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
