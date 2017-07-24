package afinal.itc298.com.finalgamev01;

import java.util.Random;

/**
 * Created by jayke on 7/16/2017.
 */

public class Spawn {
    //this class creates enemies in sequences according to score and level
    //also sets the new level as the player progresses
    //every three levels an enemy boss will appear
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        hud.setScoreKeep(hud.getScoreKeep() + 1);//+1 each tick (the score for that level)

        //spawn sequences of enemy at each level
        if(hud.getLevel() == 1) {//LEVEL ONE
            if(hud.getScoreKeep()>= 650) {
                hud.setScoreKeep(0);
                hud.setLevel(hud.getLevel() + 1);//sets the new level
            }
            if(hud.getScoreKeep() == 100){
                //add enemy object
                handler.addObject(new BasicEnemy(23, 23,ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(Constants.SCREEN_WIDTH-100, 23,ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(23, Constants.SCREEN_HEIGHT-100,ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(Constants.SCREEN_WIDTH-100, Constants.SCREEN_HEIGHT-100,ID.BasicEnemy, handler));
            }
        }

    }
}
