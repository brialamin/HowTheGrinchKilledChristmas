package com.mygdx.game.entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGame;
import com.mygdx.game.TextureManager;

public class Missile extends Entity{

    public Missile(Vector2 pos) {
        super(TextureManager.MISSILE, pos, new Vector2(0,3));
    }

    @Override
    public void update() {
        pos.add(direction);
    }

    public boolean pastScreen(){
        return pos.y >= MainGame.HEIGHT;
    }
}
