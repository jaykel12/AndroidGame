package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jayke on 7/12/2017.
 */

public class FastEnemy extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,50,50);
    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 32;
        velY = 64;

        GamePanel.clamp(velX, 0, 32);
        GamePanel.clamp(velY, 0, 64);

    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        if(y <= 25 || y >= Constants.SCREEN_HEIGHT - 25) velY *= -1;
        if(x <= 25 || x >= Constants.SCREEN_WIDTH -25 ) velX *= -1;

    }

    @Override
    public void render(Canvas canvas) {


        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);
        canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
