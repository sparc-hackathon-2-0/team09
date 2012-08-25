package dix.walton.moore.util;

import android.content.Context;
import android.media.AudioManager;
import android.widget.Toast;

public class MuteUtil {

    public static void doMute(Context context) {

        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        audioManager.setStreamVolume(AudioManager.RINGER_MODE_VIBRATE, 0, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);

//		Toast.makeText(context, "Volume muted by GMuter!", Toast.LENGTH_LONG).show();

    }

    public static void doRing(Context context){

        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);

        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, maxVolume, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);

//		Toast.makeText(context, "Volume restored by GMuter!", Toast.LENGTH_LONG).show();
    }
}
