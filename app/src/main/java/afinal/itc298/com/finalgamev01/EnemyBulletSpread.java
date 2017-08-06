package afinal.itc298.com.finalgamev01;

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
    private Rect rect = new Rect(0,0,25,25);
    private Handler handler;
    Random r = new Random();
    private int timer = 50;

    public EnemyBulletSpread(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

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
        if(y <= 0 || y >= Constants.SCREEN_HEIGHT ) velY *= -1;//if object hits sides, reverse velocity
        //handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.red, 64, 64, 0.008f,handler));

        if(x <= 0) handler.removeObject(this);//removes bullet

    }

    @Override
    public void render(Canvas canvas) {


        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
