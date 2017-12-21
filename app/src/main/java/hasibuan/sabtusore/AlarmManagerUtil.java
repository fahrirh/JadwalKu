package hasibuan.sabtusore;

/**
 * Created by Fahri Ramadhan H on 12/15/2017.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.Calendar;

public class AlarmManagerUtil {

    //Tes Fahri
    DataHelper dbcenter;
    Context ct;
    protected Cursor cursor;
    String[] daftar;
    //

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    public void initAlarmNotification(Context context) {

        //Tes Fahri
        ct=context;

        dbcenter= new DataHelper(ct);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(5).toString();
        }
        /*String [] jam = new String[30];
        String [] menit = new String[30];
        int [] jamarray = new int[30];
        int [] menitarray = new int[30];
        for(int i=0; i<31; i++) {
            String [] pisah = daftar[i].split(".");
            jam [1]= pisah[0];
            menit [1] = pisah[1];
            jamarray[i]=Integer.parseInt(jam[i]);
            menitarray[i]=Integer.parseInt(menit[i]);
        }
        //*/


        Calendar calendar = getAlarmDate();

        if (calendar == null) {
            return;
        }

        Intent myIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void cancelAlarm(Context context) {
        // If the alarm has been set, cancel it.
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        // Disable {@code SampleBootReceiver} so that it doesn't automatically restart the
        // alarm when the device is rebooted.
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private Calendar getAlarmDate() {

        Calendar calendar = Calendar.getInstance();

        boolean setAlarm = false;

        int hour = Const.ALARM_HOUR_TIME[0];
        int minute = Const.ALARM_MINUTE_TIME[0];

        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < Const.ALARM_HOUR_TIME.length; i++) {

            //if (currentHour <= Const.ALARM_HOUR_TIME[i] && currentMinute < Const.ALARM_MINUTE_TIME[i] && !setAlarm) { -Backup
            if (currentHour <= Const.ALARM_HOUR_TIME[i] && currentMinute < Const.ALARM_MINUTE_TIME[i] && !setAlarm) {
                hour = Const.ALARM_HOUR_TIME[i];
                minute = Const.ALARM_MINUTE_TIME[i];
                setAlarm = true;
            } else if (i == (Const.ALARM_HOUR_TIME.length - 1) && !setAlarm) {
                calendar.add(Calendar.DATE, 1);
                hour = Const.ALARM_HOUR_TIME[0];
                minute = Const.ALARM_MINUTE_TIME[0];
            }
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Log.d("MyAlarm", "Next Alarm: " + hour + ":" + minute);

        return calendar;
    }
}
