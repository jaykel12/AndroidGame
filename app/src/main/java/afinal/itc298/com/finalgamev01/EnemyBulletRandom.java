package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by jayke on 7/12/2017.
 */

public class EnemyBulletRandom extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,25,25);
    private Handler handler;
    private Random r = new Random();

    public EnemyBulletRandom(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(16 - -16) + -16);//moves in random directions and velocity
        velY = (r.nextInt(16 - -16) + -16);

        if(velX == 0) velX = 16;//no zero value, sets to 1 instead
        if(velY == 0) velY = 16;//no zero value,sets to 1 instead

    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        if(x <= 0) handler.removeObject(this);//removes bullet
        if(x >= Constants.SCREEN_WIDTH ) handler.removeObject(this);
        if(y <= 0) handler.removeObject(this);
        if(y>= Constants.SCREEN_HEIGHT) handler.removeObject(this);

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
