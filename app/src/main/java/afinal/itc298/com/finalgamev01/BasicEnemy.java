package afinal.itc298.com.finalgamev01;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

/**
 * Created by jayke on 7/12/2017.
 */

public class BasicEnemy extends GameObject {

    Paint paint = new Paint();
    Rect rect = new Rect(0,0,75,75);
    Handler handler;
    private Bitmap bmp;
    private int srcX, srcY;
    private int width,height;
    private static final int BMP_ROWS = 1;
    private static final int BMP_COLUMNS = 7;

    public BasicEnemy(int x, int y, ID id, Handler handler, Bitmap bmp) {
        super(x, y, id);
        this.handler = handler;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        velX = 16;
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

        if(y <= 39 || y >= Constants.SCREEN_HEIGHT - 39) velY *= -1;
        if(x <= 39 || x >= Constants.SCREEN_WIDTH -39) velX *= -1;

    }

    @Override
    public void render(Canvas canvas) {

        srcX = 4 * width;
        srcY = 0 * height;

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
