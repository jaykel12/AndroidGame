package afinal.itc298.com.finalgamev01;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jayke on 7/12/2017.
 */

public class Asteroid extends GameObject {

    Paint paint = new Paint();
    Rect rect = new Rect(0,0,200,200);
    Handler handler;
    private Bitmap bmp;
    private int srcX, srcY;
    private int width,height;
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 4;

    public Asteroid(int x, int y, ID id, Handler handler, Bitmap bmp) {
        super(x, y, id);
        this.handler = handler;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
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

        srcX = 1 * width;
        srcY = 0 * height;

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);//getting section of sprite image
        Rect dst = new Rect((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));

        canvas.drawBitmap(bmp,src,dst, null);

        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));

    }
}
