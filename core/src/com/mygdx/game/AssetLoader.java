package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by root on 12/9/14.
 */
public class AssetLoader {

    private static Preferences prefs;

    public static void load()
    {
        prefs = Gdx.app.getPreferences("GrinchKill");

        if (!prefs.contains("highScore")) {
            setHighScore(0);
        }
    }

    public static void setHighScore(long time)
    {
        prefs.putLong("highScore", time);
        prefs.flush();
    }

    public static long getHighScore() {
        return prefs.getLong("highScore");
    }

    public void dispose()
    {

    }
}
