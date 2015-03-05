package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGame;
import com.mygdx.game.TextureManager;

public class Enemy extends Entity {


    public Enemy(Vector2 pos, Vector2 direction) {
        super(TextureManager.REINDEERENEMY, pos, direction);
    }

    @Override
    public void update() {
        pos.add(direction);

        if(pos.y <= -TextureManager.REINDEERENEMY.getHeight()) {
            float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.REINDEERENEMY.getWidth());
            pos.set(x, MainGame.HEIGHT);
        }
    }
}
