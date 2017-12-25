package hasibuan.sabtusore;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class Alarm extends Activity {

    private NotificationManager mNotifyManager;
    //private NotificationReceiver mReceiver = new NotificationReceiver();
    public int bisa = 1;

    //tes lagi

    TimePicker picker;
    ImageButton buttonStart;
    ImageButton buttonCancel;
    TextView textPrompt;

    TimePickerDialog timePickerYeah;

    final static int req = 1;

    private static final int NOTIFICATION_ID = 0;
    private static final String NOTIFICATION_GUIDE_URL =
            "https://developer.android.com/design/patterns/notifications.html";
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICATION =
            "com.example.android.notifyme.ACTION_CANCEL_NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        textPrompt=(TextView) findViewById(R.id.promptID);
        buttonStart=(ImageButton) findViewById(R.id.btSet);

        buttonStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textPrompt.setText("");
                openTimePicker(false);
            }});

        buttonCancel=(ImageButton) findViewById(R.id.btCancel);
        buttonCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                cancelAlarm();
            }});

        /*if(bisa == 2){
            sendNotification();
        }*/
    }

    private void openTimePicker(boolean is24jam)
    {
        Calendar kalender=Calendar.getInstance();
        timePickerYeah = new TimePickerDialog(
                Alarm.this,
                timeSetListener,
                kalender.get(Calendar.HOUR_OF_DAY),
                kalender.get(Calendar.MINUTE),
                true);

        timePickerYeah.setTitle("Set Alarm Anda");
        timePickerYeah.show();
    }

    OnTimeSetListener timeSetListener=new OnTimeSetListener()
    {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if(calSet.compareTo(calNow) <= 0){
                //jika ternyata waktu lewat maka alarm akan di atur untuk besok
                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }};

    private void setAlarm(Calendar targetCal){

        textPrompt.setText("\n\n"+ "Alarm Telah Diatur Pada: "+targetCal.getTime());

        Intent intent = new Intent(getBaseContext(), ReceiverYeah.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), req, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        //sendNotification();

    }
    private void cancelAlarm(){

        textPrompt.setText("\n\n"+"Alarm Telah Dibatalkan!");

        Intent intent = new Intent(getBaseContext(), ReceiverYeah.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), req, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public void sendNotification() {

        //Sets up the pending intent that is delivered when the notification is clicked
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // Sets up the pending intent to cancel the notification,
        // delivered when the user dismisses the notification
        Intent cancelIntent = new Intent(ACTION_CANCEL_NOTIFICATION);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, cancelIntent, PendingIntent.FLAG_ONE_SHOT);

        //Sets up the pending intent associated with the Learn More notification action,
        //uses an implicit intent to go to the web.
        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        //Sets up the pending intent to update the notification. Corresponds to a press of the
        //Update Me! button
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        //Builds the notification with all of the parameters
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.alarm)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .addAction(R.drawable.note, getString(R.string.learn_more),
                        learnMorePendingIntent)
                .addAction(R.drawable.bell, getString(R.string.update), updatePendingIntent)
                .setDeleteIntent(cancelPendingIntent);

        //Delivers the notification
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        //Enables the update and cancel buttons but disables the "Notify Me!" button
       // mNotifyButton.setEnabled(false);
        //mUpdateButton.setEnabled(true);
        //mCancelButton.setEnabled(true);

    }

    /*private class NotificationReceiver extends BroadcastReceiver {

        /**
         * Gets the action from the incoming broadcast intent and responds accordingly
         * @param context Context of the app when the broadcast is received.
         * @param intent The broadcast intent containing the action.
         */
        /*@Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ACTION_CANCEL_NOTIFICATION:
                    //cancelNotification();
                    break;
                case ACTION_UPDATE_NOTIFICATION:
                    //updateNotification();
                    break;
            }
        }
    }*/


}
