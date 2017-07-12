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

    //private Point playerPoint;

    //constructor
    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        handler = new Handler();

        handler.addObject(new Player(550,750,ID.Player));//player object added
        //playerPoint = new Point(150, 150);//no idea how this works




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

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //player movement CODE HERE
                //playerPoint.set((int)event.getX(), (int)event.getY());

        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void tick(){//update game
        handler.tick();
    }

    @Override
    public void draw(Canvas canvas){//draws the game
        super.draw(canvas);

        canvas.drawColor(Color.WHITE);//background color

        handler.render(canvas);//draw graphics
    }

}
