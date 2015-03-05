package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetLoader;
import com.mygdx.game.MainGame;
import com.mygdx.game.TextureManager;
import com.mygdx.game.camera.OrthoCamera;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.Player;



public class GameScreen extends Screen {

    private OrthoCamera camera;
    private Player player;
    private EntityManager entityManager;
    private CharSequence reindeer;
    BitmapFont font;
    private long milliseconds;
    private long seconds;
    private long minutes;
    private long startTime;
    private long currentTime;
    private CharSequence time;
    private CharSequence highScore;
    private Texture texture;

    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.resize();
        entityManager = new EntityManager(9, camera);
        font = new BitmapFont();
        startTime = System.currentTimeMillis();
        texture = TextureManager.BACKGROUND;

    }

    @Override
    public void update() {
        camera.update();
        entityManager.update();
        currentTime = System.currentTimeMillis() - startTime;
        milliseconds = currentTime % 1000;
        seconds = (long)Math.floor(currentTime/1000) % 60;
        minutes = (long)Math.floor(currentTime/60000);
        highScore = "High score: " + String.valueOf(AssetLoader.getHighScore());

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        reindeer = "Reindeer Remaining: " + entityManager.getRemaining();
        time = "Current Score: " + minutes + ":" + seconds + "." + milliseconds;
        sb.begin();
        entityManager.render(sb);
        //sb.draw(texture, 0, 0);
        font.setScale(2);
        font.setColor(1f, 0f, 0f, 1.0f);
        font.draw(sb, reindeer, 3 * MainGame.WIDTH / 4, MainGame.HEIGHT + 25);
        font.setColor(0f, 1f, 0f, 1.0f);
        font.draw(sb, time, 3*MainGame.WIDTH/4, MainGame.HEIGHT - 15);

        //font.draw(sb, highScore, 3*MainGame.WIDTH/4, MainGame.HEIGHT - 55);
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
