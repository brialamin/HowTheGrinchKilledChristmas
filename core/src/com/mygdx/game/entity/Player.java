package com.mygdx.game.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGame;
import com.mygdx.game.TextureManager;
import com.mygdx.game.camera.OrthoCamera;

public class Player extends Entity{

    private final EntityManager missileOnScreen;
    private long lastFired;
    private final OrthoCamera camera;
    public static int endOfScreenWidth = Gdx.graphics.getWidth();
    public static int endofScreenHeight = Gdx.graphics.getHeight();

    public Player(Vector2 pos, Vector2 direction, EntityManager entityManager, OrthoCamera camera) {
        super(TextureManager.PLAYER, pos, direction);
        this.missileOnScreen = entityManager;
        this.camera = camera;
    }

    @Override
    public void update() {
        pos.add(direction);

        int dir = 0;
        int endOfScreenLeft;
        int endOfScreenRight;

        Gdx.input.isPeripheralAvailable( Input.Peripheral.Accelerometer );

        if(this.getPosition().x > endofScreenHeight) {
            endOfScreenLeft = 1;
        }
        else
        {
            endOfScreenLeft = 0;
        }
        if(this.getPosition().x < 0)
        {
            endOfScreenRight = 1;
        }
        else
        {
            endOfScreenRight = 0;
        }
        if((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.getAccelerometerY() < 0) && endOfScreenRight != 1) {
            setDirection(-300, 0);
            /*if(this.getPosition().x == endofScreenHeight || this.getPosition().x == 0) {
                setDirection(0,0);
            }*/
        }
        else if((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.getAccelerometerY() > 0) && endOfScreenLeft != 1) {
            setDirection(300, 0);
            /*if(this.getPosition().x == endofScreenHeight || this.getPosition().x == 0) {
                setDirection(0,0);
            }*/
        } else {
            setDirection(0,0);
        }

        if(Gdx.input.justTouched()) {
            if(System.currentTimeMillis() - lastFired >= 250) {
                missileOnScreen.addEntity(new Missile(pos.cpy().add(TextureManager.PLAYER
                                .getWidth()/2,
                        TextureManager.PLAYER.getHeight())));
                lastFired = System.currentTimeMillis();
            }
        }
    }
}
