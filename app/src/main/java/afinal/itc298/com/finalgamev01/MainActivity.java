package afinal.itc298.com.finalgamev01;

import android.app.Activity;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.content.Context;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.app.Dialog;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets window to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //removes toolbar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.buttonHelp);

        // add button listener
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.box);
                dialog.setTitle("Help");

                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Your mission should you choose to accept it is to fly your ship left, right, up or down through space" +
                        " avoiding asteroids and interstellar enemies. " +
                        "Evade your enemies for as long as possible to advance to new levels and improve your score! " +
                        "Good luck space traveler!");

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the dialog box
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

   // private void stopGame() {

       // endGame(R.string.endGame);
       // gameOver(false);
       // return ;
   // }





    public void startGame(View view){
        setContentView(new GamePanel(this));
    }
   // public void endGame(View view){setContentView(new endGame(this));}
    public void quitGame(View view){finish();}







    @Override
    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();

    }

   // @Override
   // private class endGame extends Handler{
   //    
    //}
}
