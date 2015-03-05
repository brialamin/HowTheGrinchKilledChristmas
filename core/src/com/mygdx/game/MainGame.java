package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.ScreenManager;
import com.mygdx.game.screens.StartScreen;

public class MainGame extends ApplicationAdapter {
	public static int WIDTH = 1280;
    public static int HEIGHT = 720;

    SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        AssetLoader.load();
        ScreenManager.setScreen(new StartScreen());
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(ScreenManager.getScreen() != null) {
            ScreenManager.getScreen().update();
        }

        if(ScreenManager.getScreen() != null) {
            ScreenManager.getScreen().render(batch);
        }
		batch.begin();
		batch.end();
	}

    @Override
    public void dispose(){
        if(ScreenManager.getScreen() != null) {
            ScreenManager.getScreen().dispose();
        }
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        if(ScreenManager.getScreen() != null) {
            ScreenManager.getScreen().resize(width, height);
        }
    }

    @Override
    public void pause(){
        if(ScreenManager.getScreen() != null) {
            ScreenManager.getScreen().pause();
        }
    }

    @Override
    public void resume(){
        if(ScreenManager.getScreen() != null) {
            ScreenManager.getScreen().resume();
        }
    }
}
