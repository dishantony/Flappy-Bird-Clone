package com.dishantpandauria.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dishantpandauria.flappybird.FlappyBirdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyBirdGame.WIDTH;
		config.height = FlappyBirdGame.HEIGHT;
		config.title = FlappyBirdGame.TITLE;
		new LwjglApplication(new FlappyBirdGame(), config);
	}
}
