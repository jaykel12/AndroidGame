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
    public void tick(){
        x+=velX;
        y+=velY;

        x = GamePanel.clamp(x, 50, Constants.SCREEN_WIDTH-50);//cannot go beyond left and right border
        y = GamePanel.clamp(y, 50, Constants.SCREEN_HEIGHT-50);//cannot go beyond top and bottom border

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

        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));


        //g.setColor(Color.white);
        //g.fillRect(x, y, 32, 32);
    }
}
