package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jayke on 7/24/2017.
 */

public class Trail extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,10,10);
    private Handler handler;
    private float alpha = 1;
    private float life;
    private Color color;

    public Trail(int x, int y, ID id,float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.life = life;
        this.color = color;
    }

    public Rect getBounds() {
        return null;
    }

    @Override
    public void tick(){

        if(alpha > life){
            alpha -= (life - 0.0001f);
        }else handler.removeObject(this);

    }

    @Override
    public void render(Canvas canvas) {


        //fill
        paint.setColor(Color.WHITE);
        canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
