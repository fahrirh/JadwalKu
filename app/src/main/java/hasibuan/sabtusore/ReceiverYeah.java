package hasibuan.sabtusore;

/**
 * Created by Fahri Ramadhan Hsb on 12/4/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class ReceiverYeah extends BroadcastReceiver{
    MediaPlayer mp;
    //Alarm A = new Alarm();

    @Override
    public void onReceive(Context c, Intent arg1) {
        mp = MediaPlayer.create(c, R.raw.instrumental);
        //A.sendNotification();
        mp.start();
        //A.sendNotification();
        //A.bisa = 2;
        Toast.makeText(c, "Alarm Telah Menyala :D", Toast.LENGTH_LONG).show();
    }
}
