package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by jayke on 7/12/2017.
 */

public class EnemyBullet extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,25,25);
    private Handler handler;
    Random r = new Random();

    public EnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = r.nextInt(16 + 1 + 16) - 16;//random velX between -5 and 5
        velY = 32;

        GamePanel.clamp(velX, 0, 16);
        GamePanel.clamp(velY, 0, 32);

    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        if(y >= Constants.SCREEN_HEIGHT) handler.removeObject(this);

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
