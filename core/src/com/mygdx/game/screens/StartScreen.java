package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainGame;
import com.mygdx.game.camera.OrthoCamera;


/**
 * Created by root on 12/9/14.
 */
public class StartScreen extends Screen {

    private OrthoCamera camera;
    BitmapFont font;

    @Override
    public void create()
    {
        camera = new OrthoCamera();
        camera.resize();
        font = new BitmapFont();
    }

    @Override
    public void update()
    {
        camera.update();
        if(Gdx.input.justTouched())
        {
            ScreenManager.setScreen(new GameScreen());
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(camera.combined);
        CharSequence str = "How the Grinch Killed Christmas!";
        CharSequence instr = "Tap to start the game!";
        CharSequence howTo = "Tilt the phone to move the tree";
        CharSequence howToCont = "Tap the screen to fire";
        sb.begin();
        font.setColor(1.0f, 0f, 0f, 1.0f);
        font.setScale(3);
        font.draw(sb, str, MainGame.WIDTH/4, 2*MainGame.HEIGHT/3);
        font.setColor(0f, 0f, 0f, 1.0f);
        font.draw(sb, howTo, MainGame.WIDTH/4 + 30, MainGame.HEIGHT/2 + 25);
        font.draw(sb, howToCont, MainGame.WIDTH/3 + 15, MainGame.HEIGHT/2 - 25);

        font.setColor(0f, 1.0f, 0f, 1.0f);
        font.draw(sb, instr, MainGame.WIDTH/3 + 10, MainGame.HEIGHT/3);
        sb.end();
    }

    @Override
    public void resize(int width, int height)
    {
        camera.resize();
    }

    @Override
    public void dispose()
    {


    }

    @Override public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

}
