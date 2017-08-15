package afinal.itc298.com.finalgamev01;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by jayke on 7/12/2017.
 */

public class EnemyBulletSpread extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,50,50);
    private Handler handler;
    private Bitmap bmp;
    Random r = new Random();
    private int width,height;
    private static final int BMP_ROWS = 4;//number of rows in spritesheet
    private static final int BMP_COLUMNS = 4;//number of columns
    private int srcX, srcY;//sprite position
    private int timer = 50;
    private int currentFrame = 0;
    private int nextFrame = 0;

    public EnemyBulletSpread(int x, int y, ID id, Handler handler, Bitmap bmp) {
        super(x, y, id);
        this.handler = handler;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;

    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;
        timer--;

        if(timer <= 0){

            if(velY == 0) velY = 32;

            velX = 16;

            velY = GamePanel.clamp(velY, -32, 32);//clamps velocity of X so it does not got below -10 or above 10

        }

        if(x >= Constants.SCREEN_WIDTH) handler.removeObject(this);//removes bullet

        //f(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;//if object hits ceiling or floor, reverse velocity
        if(y <= 13 || y >= Constants.SCREEN_HEIGHT ) velY *= -1;//if object hits sides, reverse velocity
        //handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.red, 64, 64, 0.008f,handler));

        if(x <= 0) handler.removeObject(this);//removes bullet

        currentFrame = ++currentFrame % BMP_COLUMNS;//adding 1 to frame and finding the remainder after dividing by BMP_COLUMNS, MAX 4 FRAMES

        if(currentFrame == 4){
            nextFrame = ++nextFrame % BMP_ROWS;
        }

    }

    @Override
    public void render(Canvas canvas) {

        int srcX = currentFrame * width;
        int srcY = nextFrame * height;

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);//getting section of sprite image
        Rect dst = new Rect((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));

        canvas.drawBitmap(bmp,src,dst, null);

        //fill
        //paint.setStyle(Paint.Style.FILL);
        //paint.setColor(Color.RED);
        //canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
