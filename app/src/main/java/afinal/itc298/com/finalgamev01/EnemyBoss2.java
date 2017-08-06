package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by jayke on 7/12/2017.
 */

public class EnemyBoss2 extends GameObject {

    private Paint paint = new Paint();
    private Rect rect = new Rect(0,0,250,250);
    private Handler handler;
    Random r = new Random();

    private int timer = 45;
    private int timer2 = 50;

    public EnemyBoss2(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = 30;

    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        if(timer <= 0) velY = 0;//when timer is less than 0, bossEnemy will stop moving
        else timer--;

        if(timer <= 0) timer2--;
        if(timer2 <= 0){
            int spawn = r.nextInt(5);
            if(spawn == 0) handler.addObject(new EnemyBulletRandom((int) x , (int) y, ID.EnemyBulletRandom, handler));
        }
    }

    @Override
    public void render(Canvas canvas) {


        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(rect, paint);


        //sets position of rect, middle point is the center of rectangle
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
    }
}
