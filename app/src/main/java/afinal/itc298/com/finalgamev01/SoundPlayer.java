package afinal.itc298.com.finalgamev01;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by jayke on 8/14/2017.
 */

public class SoundPlayer {
    public static MediaPlayer hitSound;

    public SoundPlayer(Context context){

        hitSound = MediaPlayer.create(context,R.raw.hit);

    }



}
