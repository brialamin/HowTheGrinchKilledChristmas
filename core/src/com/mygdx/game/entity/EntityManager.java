package com.mygdx.game.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.AssetLoader;
import com.mygdx.game.MainGame;
import com.mygdx.game.TextureManager;
import com.mygdx.game.camera.OrthoCamera;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.ScreenManager;

public class EntityManager {

    private final Array<Entity> entities = new Array<Entity>();
    private final Player player;
    private int remaining = 9;
    private long highScore;
    private long startTime;
    private long thisScore;

    public EntityManager(int amount, OrthoCamera camera){
        player = new Player(new Vector2(MainGame.WIDTH/2, 20),
                new Vector2(0,0), this, camera);
        for(int i = 0; i < amount; i++) {
            float x = MathUtils.random(TextureManager.PLAYER.getWidth(), MainGame.WIDTH - TextureManager.PLAYER.getWidth());
            float y = MathUtils.random(MainGame.HEIGHT, MainGame.HEIGHT * 5);
            float speed = MathUtils.random(2,5);
            addEntity(new Enemy(new Vector2(x,y), new Vector2(0, -speed)));
            //highScore = AssetLoader.getHighScore();
            startTime = System.currentTimeMillis();
        }
    }

    public void update(){
        for(Entity e : entities) {
            e.update();
        }
        for(Missile m : getMissiles()) {
            if(m.pastScreen()) {
                entities.removeValue(m, false);
            }
        }
        player.update();
        checkCollision();
    }

    public void render(SpriteBatch sb){
        for(Entity e : entities) {
            e.render(sb);
        }
        player.render(sb);
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    private void checkCollision(){
        for(Enemy e : getEnemies()) {
            for(Missile m : getMissiles()) {
                if(e.getBounds().overlaps(m.getBounds())) {
                    entities.removeValue(e, false);
                    remaining--;
                    entities.removeValue(m, false);
                    if(BossSpawn()) {
                        //QSNOOPYJR FTW
                        //thisScore = System.currentTimeMillis() - startTime;
                        //if(thisScore <= highScore)
                        //{
                           // AssetLoader.setHighScore(thisScore);
                        //}
                        ScreenManager.setScreen(new GameOverScreen(true));
                    }
                }
            }
            if(player.getBounds().overlaps(e.getBounds())) {
                //YOU LOSE, GOOD DAY SIR
                ScreenManager.setScreen(new GameOverScreen(false));
            }
        }
    }

    private Array<Enemy> getEnemies(){
        Array<Enemy> ret = new Array<Enemy>();
        for(Entity e : entities) {
            if(e instanceof Enemy) {
                ret.add( (Enemy) e);
            }
        }
        return ret;
    }

    private Array<Missile> getMissiles(){
        Array<Missile> ret = new Array<Missile>();
        for(Entity e : entities) {
            if(e instanceof Missile) {
                ret.add( (Missile) e);
            }
        }
        return ret;
    }

    public int getRemaining()
    {
        return remaining;
    }

    public boolean BossSpawn(){
        return getEnemies().size <= 0;
    }
}
