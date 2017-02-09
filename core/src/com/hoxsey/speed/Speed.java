package com.hoxsey.speed;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Speed extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Speed";
	private StateManager sm;
	private  SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sm = new StateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		sm.push(new Menu(sm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}
}
