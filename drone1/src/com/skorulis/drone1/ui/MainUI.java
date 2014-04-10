package com.skorulis.drone1.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;

public class MainUI implements Disposable {
	
	public Stage stage;
	
	public MainUI() {
		stage = new Stage();
		
		Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
		
        //LabelStyle style = new LabelStyle();
        //Label label = new Label("SOME TEXT", style);
        resize(200,200);
        
	}
	
	public void resize (int width, int height) {
        stage.setViewport(width, height, true);
	}
	
	public void render() {
		stage.draw();
		Table.drawDebug(stage);
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	public void dispose() {
        stage.dispose();
}
	
}
