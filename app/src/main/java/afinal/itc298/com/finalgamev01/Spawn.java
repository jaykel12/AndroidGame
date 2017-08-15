package afinal.itc298.com.finalgamev01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.Random;

/**
 * Created by jayke on 7/16/2017.
 */

public class Spawn {
    //this class creates enemies in sequences according to score and level
    //also sets the new level as the player progresses
    //every three levels an enemy enemies2 will appear
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int x = r.nextInt(Constants.SCREEN_WIDTH-60);
    private int y = r.nextInt(Constants.SCREEN_HEIGHT-60);
    private SoundPlayer sound;


    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;

    }

    public void tick(){
        hud.setScoreKeep(hud.getScoreKeep() + 1);//+1 each tick (the score for that level)

        GamePanel.clamp(x,150,Constants.SCREEN_WIDTH-60);
        GamePanel.clamp(y,150,Constants.SCREEN_HEIGHT-60);

        int spawnHP = r.nextInt(125);
        if(spawnHP == 0)handler.addObject(new HPItem((r.nextInt(Constants.SCREEN_WIDTH)), (-50), ID.HPItem, handler));

        //spawn sequences of enemy at each level
        if(hud.getLevel() == 1) {//LEVEL ONE
            sound.bgmSound.start();
            if(hud.getScoreKeep()>= 650) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 100){
                //add enemy object
                handler.addObject(new BasicEnemy(x, y,ID.BasicEnemy, handler, GamePanel.bmpBasicEnemy));
            }
            if(hud.getScoreKeep() == 150){
                handler.addObject(new FastEnemyLR(Constants.SCREEN_WIDTH - 26,0,ID.FastEnemyLR, handler, GamePanel.bmpFastEnemyLR));
                handler.addObject(new FastEnemyLR(26,0,ID.FastEnemyLR, handler, GamePanel.bmpFastEnemyLR));
            }
        }else if(hud.getLevel() == 2){
            if(hud.getScoreKeep()>= 650) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                handler.clearEnemies();
            }
            int spawn = r.nextInt(20);
            if(spawn == 0)handler.addObject(new Asteroid((r.nextInt(Constants.SCREEN_WIDTH)), -150, ID.Asteroid, handler, GamePanel.bmpAsteroid));
            if(hud.getScoreKeep() == 100){
                handler.addObject(new FastEnemy(Constants.SCREEN_WIDTH -26,0, ID.FastEnemy, handler, GamePanel.bmpFastEnemy));
                handler.addObject(new FastEnemy(26,0, ID.FastEnemy, handler, GamePanel.bmpFastEnemy));
            }
        }else if(hud.getLevel() == 3){//BOSS LEVEL
            if(hud.getScoreKeep()>= 850) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                handler.clearEnemies();
            }
            if(hud.getScoreKeep() == 50){
                handler.addObject(new EnemyBoss((Constants.SCREEN_WIDTH/2), -500, ID.EnemyBoss, handler, GamePanel.bmpEnemyBoss));
            }
        }else if(hud.getLevel() == 4){
            if(hud.getScoreKeep()>= 650) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                handler.clearEnemies();
            }
            if(hud.getScoreKeep() == 100)handler.addObject(new BasicEnemy(x, y,ID.BasicEnemy, handler, GamePanel.bmpBasicEnemy));
            if(hud.getScoreKeep() == 200)handler.addObject(new BasicEnemy(x, y,ID.BasicEnemy, handler, GamePanel.bmpBasicEnemy));
            if(hud.getScoreKeep() == 300){
                handler.addObject(new BasicEnemy(x, y,ID.BasicEnemy, handler, GamePanel.bmpBasicEnemy));
                handler.addObject(new BasicEnemy(x, y,ID.BasicEnemy, handler, GamePanel.bmpBasicEnemy));
            }
            if(hud.getScoreKeep() == 50)handler.addObject(new FastEnemyLR(Constants.SCREEN_WIDTH/2, 0, ID.FastEnemyLR, handler, GamePanel.bmpFastEnemyLR));
        }else if(hud.getLevel() == 5){
            if(hud.getScoreKeep()>= 650) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                handler.clearEnemies();
            }
            int spawn = r.nextInt(15);
            if(spawn == 0)handler.addObject(new Asteroid((r.nextInt(Constants.SCREEN_WIDTH)), -150, ID.Asteroid, handler, GamePanel.bmpAsteroid));
            if(hud.getScoreKeep() == 100){
                handler.addObject(new FastEnemyLR(Constants.SCREEN_WIDTH -26,0, ID.FastEnemyLR, handler, GamePanel.bmpFastEnemyLR));
                handler.addObject(new FastEnemyLR(26,0, ID.FastEnemyLR, handler, GamePanel.bmpFastEnemyLR));
            }
        }else if(hud.getLevel() == 6){//BOSS LEVEL
            if(hud.getScoreKeep()>= 850) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                sound.bgmSound.stop();
                sound.bossSound.setLooping(true);
                sound.bossSound.start();
                handler.clearEnemies();
            }

            if(hud.getScoreKeep() == 50){
                handler.addObject(new SmartEnemy(r.nextInt(Constants.SCREEN_WIDTH-39), r.nextInt(Constants.SCREEN_HEIGHT),ID.SmartEnemy, handler, GamePanel.bmpSmartEnemy));
                handler.addObject(new EnemyBoss2((Constants.SCREEN_WIDTH/2), -500, ID.EnemyBoss2, handler, GamePanel.bmpEnemyBoss2));
            }
        }else if(hud.getLevel() == 7){
            if(hud.getScoreKeep()>= 650) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                handler.clearEnemies();
            }

            if(hud.getScoreKeep() == 100){
                handler.addObject(new SmartEnemy(r.nextInt(Constants.SCREEN_WIDTH-39), r.nextInt(Constants.SCREEN_HEIGHT),ID.SmartEnemy, handler, GamePanel.bmpSmartEnemy));
                handler.addObject(new FastEnemy(Constants.SCREEN_WIDTH/2, 0, ID.FastEnemy, handler, GamePanel.bmpFastEnemy));
                handler.addObject(new FastEnemy(Constants.SCREEN_WIDTH/2,440 , ID.FastEnemy, handler, GamePanel.bmpFastEnemy));
                handler.addObject(new FastEnemyLR(Constants.SCREEN_WIDTH/2, 0, ID.FastEnemyLR, handler, GamePanel.bmpFastEnemyLR));
                handler.addObject(new FastEnemyLR(Constants.SCREEN_WIDTH/2,440 , ID.FastEnemyLR, handler, GamePanel.bmpFastEnemyLR));
            }
        }else if(hud.getLevel() == 8){//MINI BOSS
            if(hud.getScoreKeep()>= 750) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                handler.clearEnemies();
            }

            if(hud.getScoreKeep() == 100){
                handler.addObject(new SmartEnemy(r.nextInt(Constants.SCREEN_WIDTH-39), r.nextInt(Constants.SCREEN_HEIGHT),ID.SmartEnemy, handler, GamePanel.bmpSmartEnemy));
                handler.addObject(new EnemyBossMini(-150, Constants.SCREEN_HEIGHT/2, ID.EnemyBoss, handler, GamePanel.bmpEnemyBossMini));
            }
        }else if(hud.getLevel() == 9){//FINAL BOSS
            if(hud.getScoreKeep()>= 750) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 1){
                handler.clearEnemies();
            }

            if(hud.getScoreKeep() == 100){
                handler.addObject(new EnemyBoss3((Constants.SCREEN_WIDTH)+(48*4), (Constants.SCREEN_HEIGHT/2)+280, ID.EnemyBoss3, handler, GamePanel.bmpEnemyBoss3));
                handler.addObject(new EnemyBoss3((Constants.SCREEN_WIDTH)+(48), (Constants.SCREEN_HEIGHT/2)+160, ID.EnemyBoss3, handler, GamePanel.bmpEnemyBoss3));
                handler.addObject(new EnemyBoss3((Constants.SCREEN_WIDTH)+(48*4), (Constants.SCREEN_HEIGHT/2)+40, ID.EnemyBoss3, handler, GamePanel.bmpEnemyBoss3));
            }
        }else if(hud.getLevel() == 10){
            int spawn = r.nextInt(30);
            if(spawn == 0)handler.addObject(new Asteroid((r.nextInt(Constants.SCREEN_WIDTH)), -150, ID.Asteroid, handler, GamePanel.bmpAsteroid));//after
        }

    }
}