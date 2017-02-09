package com.hoxsey.speed.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hoxsey.speed.Speed;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Speed.WIDTH;
		config.height = Speed.HEIGHT;
		config.title = Speed.TITLE;
		new LwjglApplication(new Speed(), config);
	}
}
