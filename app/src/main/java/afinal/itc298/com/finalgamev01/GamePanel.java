package afinal.itc298.com.finalgamev01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jayke on 7/11/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Handler handler;
    private HUD hud;

    //constructor
    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        handler = new Handler();
        hud = new HUD();

        //add player object
        handler.addObject(new Player(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2,ID.Player, handler));
        //add enemy object
        handler.addObject(new BasicEnemy(23, 23,ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(Constants.SCREEN_WIDTH-100, 23,ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(23, Constants.SCREEN_HEIGHT-100,ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(Constants.SCREEN_WIDTH-100, Constants.SCREEN_HEIGHT-100,ID.BasicEnemy, handler));

        //set player movement listener
        this.setOnTouchListener(new OnSwipeTouchListener(this.getContext(),handler));


        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);//restart MainThread

        thread.setRunning(true);//make game loop start running
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(true){//stop current thread
            try{
                thread.setRunning(false);//stop game loop
                thread.join();//finish tread, then terminate
            } catch(Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }


    public void tick(){//update game
        handler.tick();
        hud.tick();
    }

    @Override
    public void draw(Canvas canvas){//draws the game
        super.draw(canvas);

        canvas.drawColor(Color.BLACK);//background color

        handler.render(canvas);//draw graphics

        hud.render(canvas);
    }

    //clamps a float variable between a max and a min
    public static float clamp(float var, float min, float max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }

}
