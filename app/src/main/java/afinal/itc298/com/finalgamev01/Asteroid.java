package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jayke on 7/12/2017.
 */

public class Asteroid extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,200,200);
    private Handler handler;

    public Asteroid(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = 16;

        GamePanel.clamp(velY, 0, 16);

    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        if(y+200 >= Constants.SCREEN_HEIGHT + 500) handler.removeObject(this);

    }

    @Override
    public void render(Canvas canvas) {


        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
