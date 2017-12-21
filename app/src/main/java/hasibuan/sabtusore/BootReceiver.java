package hasibuan.sabtusore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Fahri Ramadhan H on 12/15/2017.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            AlarmManagerUtil cAlarm = new AlarmManagerUtil();
            cAlarm.initAlarmNotification(context);
        }
    }
}
