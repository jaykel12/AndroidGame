package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jayke on 7/12/2017.
 */

public class SmartEnemy extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,75,75);
    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for(int i = 0; i < handler.object.size(); i++ ){
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }

    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        float diffX = x - player.getX() - 8;//distance between player and enemy x-axis
        float diffY = y - player.getY() - 8;//distance between player and enemy y-axis
        //distance between player and enemy (distance formula)
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY()));

        velX = ((-10 / distance) * diffX);//gradually matches player x position
        velY = ((-10 / distance) * diffY);//gradually matches player y position

    }

    @Override
    public void render(Canvas canvas) {


        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
