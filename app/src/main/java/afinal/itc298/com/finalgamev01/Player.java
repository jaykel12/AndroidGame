package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by jayke on 7/11/2017.
 */

public class Player extends GameObject {

    Paint paint = new Paint();
    Rect rect = new Rect(10,10,100,100);

    public Player(int x, int y, ID id) {
        super(x, y, id);

        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        x += velX;
        y += velY;
    }

    @Override
    public void render(Canvas canvas) {
        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(rect, paint);

        //border
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);
        //set draw location MUST FIGURE OUT A WAY TO DO THIS DIFFERENTLY
        rect.set(x - rect.width()/2, y - rect.height()/2, x + rect.width()/2,y + rect.height()/2);

        //g.setColor(Color.white);
        //g.fillRect(x, y, 32, 32);
    }
}
