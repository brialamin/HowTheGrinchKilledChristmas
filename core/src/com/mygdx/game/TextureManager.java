package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {

    public static Texture PLAYER = new Texture(Gdx.files.internal("tree.png"));
    public static Texture MISSILE = new Texture(Gdx.files.internal("missile.png"));
    public static Texture MALEELFENEMY = new Texture(Gdx.files.internal("maleElf.jpg"));
    public static Texture FEMALEELFEMENY = new Texture(Gdx.files.internal("femaleElf.jpg"));
    public static Texture REINDEERENEMY = new Texture(Gdx.files.internal("deer-sprite.png"));
    public static Texture SANTABOSS = new Texture(Gdx.files.internal("santa.jpg"));
    public static Texture BACKGROUND = new Texture(Gdx.files.internal("HTGKCbackgroundHorizontal.png"));
    public static Texture YOULOSE = new Texture(Gdx.files.internal("gameover.png"));
    public static Texture VICTORY = new Texture(Gdx.files.internal("gamewon.png"));
    public static Texture NUM_LIVES = new Texture(Gdx.files.internal("grinchFace2.png"));
}
