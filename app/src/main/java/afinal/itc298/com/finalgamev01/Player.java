package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;

/**
 * Created by jayke on 7/11/2017.
 */

public class Player extends GameObject {

    Paint paint = new Paint();
    Rect rect = new Rect(0,0,100,100);
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        // TODO Auto-generated constructor stub
    }

    public Rect getBounds() {
        return new Rect(rect);
    }

    @Override
    public void tick(){
        x+=velX;
        y+=velY;

        if(x < 0) x = Constants.SCREEN_WIDTH ;//if traveling to the left end of the screen appear on the right end
        if(x > Constants.SCREEN_WIDTH ) x = 0;//if traveling to the right end of the screen appear on the left end
        y = GamePanel.clamp(y, 50, Constants.SCREEN_HEIGHT-50);//cannot go beyond top and bottom border
        //handler.addObject(new Trail((int)x,(int)y,ID.Trail,.15f, handler));

        collision();

    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy ||
                    tempObject.getId() == ID.Asteroid || tempObject.getId() == ID.EnemyBoss ||
                    tempObject.getId() == ID.EnemyBullet || tempObject.getId() == ID.SmartEnemy ||
                    tempObject.getId() == ID.EnemyBoss2 || tempObject.getId() == ID.EnemyBulletRandom ||
                    tempObject.getId() == ID.EnemyBulletSpread || tempObject.getId() == ID.EnemyBossMini||
                    tempObject.getId() == ID.EnemyBoss3 || tempObject.getId() == ID.EnemyBulletSmart){
                if(getBounds().intersect(tempObject.getBounds())){
                    Log.d("Hit:", "GOT HIT!");
                    HUD.HEALTH-=2;
                }
            }
        }
    }

    @Override
    public void render(Canvas canvas) {
        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawRect(rect, paint);

        //border
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);

        //position middle of square
        rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));

    }
}
