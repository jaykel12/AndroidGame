package afinal.itc298.com.finalgamev01;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by jayke on 8/14/2017.
 */

public class SoundPlayer {
    public static MediaPlayer hitSound;
    public static MediaPlayer healSound;
    public static MediaPlayer bgmSound;
    public static MediaPlayer bossSound;

    public SoundPlayer(Context context){

        hitSound = MediaPlayer.create(context,R.raw.hit);
        healSound = MediaPlayer.create(context,R.raw.heal_sound);
        bgmSound = MediaPlayer.create(context,R.raw.bow_chippa_bow_wow);
        bossSound = MediaPlayer.create(context,R.raw.final_boss);


    }



}
