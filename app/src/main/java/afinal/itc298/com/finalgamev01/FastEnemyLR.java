package afinal.itc298.com.finalgamev01;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jayke on 7/12/2017.
 */

public class FastEnemyLR extends GameObject {

    Paint paint = new Paint();
    Rect rect = new Rect(0,0,50,50);
    Handler handler;
    private Bitmap bmp;
    private int srcX, srcY;
    private int width,height;
    private static final int BMP_ROWS = 1;
    private static final int BMP_COLUMNS = 6;

    public FastEnemyLR(int x, int y, ID id, Handler handler, Bitmap bmp) {
        super(x, y, id);
        this.handler = handler;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        velX = 64;
        velY = 32;

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

        srcX = 1 * width;
        srcY = 0 * height;

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);//getting section of sprite image
        Rect dst = new Rect((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));

        canvas.drawBitmap(bmp,src,dst, null);

        //fill
        //paint.setStyle(Paint.Style.FILL);
        //paint.setColor(Color.CYAN);
        //canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
