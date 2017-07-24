package afinal.itc298.com.finalgamev01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jayke on 7/16/2017.
 */

public class HUD {
    //HEADS UP DISPLAY, displays needed information for the player

    private Paint paint = new Paint();
    Rect rect = new Rect();

    public static float HEALTH = 100;
    //public static float barrierMeter = 200;
    private float greenValue = 255;//initial color of health bar
    private float redValue = 255;//decrease  color of health bar
    public static int score = 0;
    private int level = 1;
    private int levelCount = 1;

    private int scoreKeep = 0;//keeps track of score gained at each level

    private int timer = 500;

    public void tick(){
        HEALTH = (int) GamePanel.clamp(HEALTH, 0, 100);//clamps health between 0 and 100
        greenValue = (int) GamePanel.clamp(greenValue, 0, 255);
        redValue = (int) GamePanel.clamp(redValue, 0, 255);
        greenValue = HEALTH * (255/100);// = 255

        score++;//add 1 point to score per tick
    }
    public void render(Canvas canvas){//as health goes down, the color value changes, also renders the health bar

        paint.setColor(Color.GRAY);//setting color
        canvas.drawRect(rect = new Rect(50,50,555,100), paint);//drawing rect
        paint.setColor(Color.rgb((((int)redValue - (int) greenValue))*(199/100),(int) greenValue, 0));//changes color when ship is hit
        canvas.drawRect(rect = new Rect(50,50,(int) (HEALTH * 5) + 55,100), paint);//bar length decreases when hit

        paint.setColor(Color.WHITE);
        paint.setTextSize(50);//setting text size
        canvas.drawText("Score: " + score,50, 150, paint );
        canvas.drawText("Level: " + level,50, 200, paint );
    }
    public void setScore(int score){
        HUD.score = score;
    }
    public int getScore(){
        return score;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public void setScoreKeep(int scoreKeep){//sets score
        this.scoreKeep = scoreKeep;
    }
    public int getScoreKeep(){//gets score
        return scoreKeep;
    }
    public int getLevelCount() {
        return levelCount;
    }
    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

}
