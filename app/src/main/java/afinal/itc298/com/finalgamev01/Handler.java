package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;

import java.util.LinkedList;

/**
 * Created by jayke on 7/11/2017.
 */

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();
    private boolean clearing = false;//checks if all objects are removed

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Canvas canvas){
        for(int i = 0; i < object.size(); i++){
            if (clearing) {//exit method if clear(NullPointerExceptionBug)
                return;
            }
            GameObject tempObject = object.get(i);
            tempObject.render(canvas);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void clearEnemies() {//clears all enemies except player when game starts
        clearing = true;
        for (int i = 0; i < object.size(); i++) {// loops through all objects
            GameObject tempObject = object.get(i);//gets object stores it into tempObject
            if (tempObject.getId() != ID.Player && tempObject.getId() != ID.Trail && tempObject.getId() != ID.Explosion && tempObject.getId() != ID.Starfield && tempObject.getId() != ID.HPItem && tempObject.getId() != ID.SlowItem) {//removes all objects except player object
                removeObject(tempObject);
                /*if(!AudioPlayer.getSound("explosion").playing()){
                    AudioPlayer.getSound("explosion").play();
                }
                addObject(new Explosion((int)tempObject.getX(), (int)tempObject.getY(), ID.Explosion, this));//create an explosion effect at death
                */i--;//for some reason it skips every other iteration of object in the linked list(note)
            }//else if(/*Game.gameState == STATE.End &&*/ tempObject.getId() != ID.Starfield){//removes all objects when game ends except starfield
               // removeObject(tempObject);
               // i--;
           // }
        }clearing = false;
    }

}
