package com.hoxsey.speed;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoxsey.speed.states.*;

public class Speed extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Speed";
	private com.hoxsey.speed.states.StateManager sm;
	private  SpriteBatch batch;
	private Music music;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sm = new com.hoxsey.speed.states.StateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		sm.push(new MenuState(sm));
		music = Gdx.audio.newMusic(Gdx.files.internal("Arcade_fast.ogg"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}

    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
    }


}
