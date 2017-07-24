package afinal.itc298.com.finalgamev01;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Starfield extends GameObject{
	Random r = new Random();
	private Paint paint = new Paint();
	private Rect rect = new Rect(0,0,4,4);
	private Handler handler;

	
	public Starfield(int x, int y, ID id, Handler handler){
		super(x,y,id);
		velX = 0;
		velY = r.nextInt(6 + 6 + 6) + 6;
		
	}

	public Rect getBounds() {
		return new Rect(rect);
	}

	public void tick(){
		
		x += velX;
		y += velY;
		
		if(y >= Constants.SCREEN_HEIGHT - 32){
			x=r.nextInt(Constants.SCREEN_HEIGHT + 1 + Constants.SCREEN_HEIGHT+75) - Constants.SCREEN_HEIGHT+75;
			y=0;
		}

	}
	
	public void render(Canvas canvas){
		//fill
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		canvas.drawRect(rect, paint);

		//border
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.WHITE);
		canvas.drawRect(rect, paint);

		//sets position of rect, middle point is the center of rectangle
		rect.set((int)(x - rect.width()/2), (int)(y - rect.height()/2),(int)(x + rect.width()/2),(int)(y + rect.height()/2));
	}
}