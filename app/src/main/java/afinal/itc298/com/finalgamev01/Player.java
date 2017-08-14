package afinal.itc298.com.finalgamev01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;

/**
 * Created by jayke on 7/11/2017.
 */

public class Player extends GameObject {

    Paint paint = new Paint();
    Rect rect = new Rect(0,0,100,100);
    Handler handler;
    private Bitmap bmp;
    private int width,height;
    private static final int BMP_ROWS = 4;//number of rows in spritesheet
    private static final int BMP_COLUMNS = 4;//number of columns
    private int currentFrame = 0;//frame counter for animation, can be tweaked
    private int srcX, srcY;//sprite positions
    private int timer = 30;
    private boolean hit = false;
    private boolean playing = false;

    private SoundPlayer sound;

    public Player(int x, int y, ID id, Handler handler, Bitmap bmp) {
        super(x, y, id);
        this.handler = handler;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        // TODO Auto-generated constructor stub
    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        if(x < 0) x = Constants.SCREEN_WIDTH ;//if traveling to the left end of the screen appear on the right end
        if(x > Constants.SCREEN_WIDTH ) x = 0;//if traveling to the right end of the screen appear on the left end
        y = GamePanel.clamp(y, 50, Constants.SCREEN_HEIGHT-50);//cannot go beyond top and bottom border
        //handler.addObject(new Trail((int)x,(int)y,ID.Trail,.15f, handler));

        //currentFrame = ++currentFrame % BMP_COLUMNS;//adding 1 to frame and finding the remainder after dividing by BMP_COLUMNS, MAX 4 FRAMES

        if(hit == true){
            shipHit();
        }else shipSpriteMovement();

        collision();

    }

    public void shipSpriteMovement(){

        //int srcX = currentFrame * width;//use this for animation, experiment with it
        srcX = 1 * width;//column of sprite
        srcY = 0 * height;//row of sprite

        if(velY < 0 ) {
            srcX = 1 * width;//column of sprite
            srcY = 0 * height;//row of sprite
        }else if(velY > 0){
            srcX = 3 * width;//column of sprite
            srcY = 1 * height;//row of sprite
        }else if(velX < 0){
            srcX = 0 * width;//column of sprite
            srcY = 1 * height;//row of sprite
        }else if(velX > 0){
            srcX = 3 * width;//column of sprite
            srcY = 0 * height;//row of sprite
        }

    }

    public void shipHit(){

        if(hit == true) {
            this.timer--;
            if(!sound.hitSound.isPlaying()&& playing == false){//if playing do not play again
                sound.hitSound.start();
                playing = true;
            }else playing = false;


            if(this.timer == 29){
                srcX = 2 * width;//column of sprite
                srcY = 0 * height;//row of sprite
            }
            if(this.timer == 25){
                srcX = 3 * width;//column of sprite
                srcY = 0 * height;//row of sprite
            }
            if(this.timer == 20){
                srcX = 2 * width;//column of sprite
                srcY = 1 * height;//row of sprite
            }
            if(this.timer == 15){
                srcX = 1 * width;//column of sprite
                srcY = 1 * height;//row of sprite
            }
            if(this.timer == 10){
                srcX = 0 * width;//column of sprite
                srcY = 1 * height;//row of sprite
            }
            if(this.timer == 5){
                srcX = 0 * width;//column of sprite
                srcY = 0 * height;//row of sprite
            }
            if(this.timer == 0) {
                hit = false;

                this.timer = 30;
            }
        }
    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.FastEnemyLR ||
                    tempObject.getId() == ID.Asteroid || tempObject.getId() == ID.EnemyBoss ||
                    tempObject.getId() == ID.EnemyBullet || tempObject.getId() == ID.SmartEnemy ||
                    tempObject.getId() == ID.EnemyBoss2 || tempObject.getId() == ID.EnemyBulletRandom ||
                    tempObject.getId() == ID.EnemyBulletSpread || tempObject.getId() == ID.EnemyBossMini||
                    tempObject.getId() == ID.EnemyBoss3 || tempObject.getId() == ID.EnemyBulletSmart ||
                    tempObject.getId() == ID.HPItem ){
                if(getBounds().intersect(tempObject.getBounds()) && tempObject.getId() != ID.HPItem){
                    Log.d("Hit:", "GOT HIT!");
                    HUD.HEALTH-=2;
                    hit = true;
                    if(getBounds().intersect(tempObject.getBounds()) && tempObject.getId() == ID.EnemyBullet || tempObject.getId() == ID.EnemyBulletSmart
                            || tempObject.getId() == ID.EnemyBulletSpread|| tempObject.getId() == ID.EnemyBulletRandom){
                        HUD.HEALTH-=4;
                        handler.removeObject(tempObject);
                    }
                }else if(getBounds().intersect(tempObject.getBounds()) && tempObject.getId() == ID.HPItem){
                    HUD.HEALTH+=15;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    @Override
    public void render(Canvas canvas) {

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);//getting section of sprite image
        Rect dst = new Rect((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
        //position of rect

        //fill
        //paint.setStyle(Paint.Style.FILL);
        //paint.setColor(Color.WHITE);
        //canvas.drawRect(rect, paint);

        //border
        //paint.setStyle(Paint.Style.STROKE);
        //paint.setColor(Color.BLACK);
        //canvas.drawRect(rect, paint);
        canvas.drawBitmap(bmp,src,dst, null);//read the documentation to understand
        //position middle of square
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));


    }
}
