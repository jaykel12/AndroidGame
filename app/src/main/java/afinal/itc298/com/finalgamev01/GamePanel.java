package afinal.itc298.com.finalgamev01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by jayke on 7/11/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Handler handler;
    private HUD hud;
    private  Spawn spawner;
    private Random r = new Random();
    public static boolean paused = false;
    private Bitmap bmpPlayer;

    //constructor
    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        handler = new Handler();
        hud = new HUD();

        //add starfield
        for(int i = 0; i < 200; i++){
            handler.addObject(new Starfield(r.nextInt(Constants.SCREEN_WIDTH),r.nextInt(Constants.SCREEN_HEIGHT), ID.Starfield, handler));
        }
        //add player object
        bmpPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
        handler.addObject(new Player(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2,ID.Player, handler,bmpPlayer));

        //set player movement listener
        this.setOnTouchListener(new OnSwipeTouchListener(this.getContext(),handler));

        //set spawner
        spawner = new Spawn(handler, hud);


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
        while(retry){//stop current thread
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
       if(paused == false) {
           handler.tick();
           spawner.tick();
           hud.tick();
       }

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
