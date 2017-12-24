package hasibuan.sabtusore;

/**
 * Created by Fahri Ramadhan H on 12/15/2017.
 */

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

/**
 * Created by ihsan on 11/03/16.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {

    //Tes Fahri
    DataHelper dbcenter;
    Context ct;
    protected Cursor cursor;
    String[] web, web2, web3, web4, web5, web6;
    int adasenin;
    int adaselasa;
    int adarabu;
    int adakamis;
    int adajumat;
    int adasabtu;
    int adaminggu;
    //public static AlarmReceiver ma;

    @Override
    public void onReceive(Context context, Intent intent) {
        ct=context;

        dbcenter= new DataHelper(ct);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                        "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                        "JOIN datahari ON jadwal.hari=datahari.hari " +
                        "WHERE datahari.hari = 'Senin' ORDER BY urut ASC;",null);
        web = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ngetes= cursor.getCount();
        int a = 0;
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition  (cc);
            web[cc] = cursor.getString(0).toString();
            a=cursor.getCount();
        }

        //percobaan ke 2 cek hari
        if(a==0){
           adaminggu=0;
        }
        else if(a>0){
           adaminggu=1;
        }

        AlarmManagerUtil alarmUtil = new AlarmManagerUtil();
        alarmUtil.initAlarmNotification(context);

        //Find day
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        /*if(currentDay > 1 && currentDay < 7) {
            createNotification(context, 1, web, web2, web3, web4, web5, web6);
        }
        if(currentDay == 1 || currentDay == 7) {
            createNotification2(context, 1, web, web2, web3, web4, web5, web6);
        }*/

        //Kondisi notif setelah cek data hari
        if(currentDay == 2){
            if(adaminggu == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngetes);
            }
            else if(adaminggu == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }
        /*if(currentDay == 2){
            if(adasenin == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6);
            }
            else{
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }
        if(currentDay == 3){
            if(adaselasa == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6);
            }
            else{
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }
        if(currentDay == 4){
            if(adarabu == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6);
            }
            else{
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }
        if(currentDay == 5){
            if(adakamis == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6);
            }
            else{
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }
        if(currentDay == 6){
            if(adajumat == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6);
            }
            else{
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }
        if(currentDay == 7){
            if(adasabtu == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6);
            }
            else{
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }*/
    }

    private static PendingIntent criarPendingIntent(
            Context ctx, int id) {

        Intent resultIntent = new Intent(ctx, JadwalHarian.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(JadwalHarian.class);
        stackBuilder.addNextIntent(resultIntent);
        return stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void createNotification(Context ctx, int id, String web[], String web2[],
                                          String web3[], String web4[], String web5[], String web6[], int ngetes) {


        //Tes Fahri
        /*RemoteViews remoteViews = new RemoteViews(ctx.getPackageName(), R.layout.list_notif);
        remoteViews.setImageViewResource(R.id.imageIcon, R.drawable.school);
        remoteViews.setTextViewText(R.id.tvJamMasuk1, web2[0]);
        remoteViews.setTextViewText(R.id.tvNamaMK1, web3[0]);
        remoteViews.setTextViewText(R.id.tvJamMasuk2, web2[1]);
        remoteViews.setTextViewText(R.id.tvNamaMK2, web3[1]);
        remoteViews.setTextViewText(R.id.tvJamMasuk3, web2[2]);
        remoteViews.setTextViewText(R.id.tvNamaMK3, web3[2]);
        remoteViews.setTextViewText(R.id.tvJamMasuk4, web2[3]);
        remoteViews.setTextViewText(R.id.tvNamaMK4, web3[3]);
        remoteViews.setTextViewText(R.id.tvJamMasuk5, web2[4]);
        remoteViews.setTextViewText(R.id.tvNamaMK5, web3[4]);
        remoteViews.setTextViewText(R.id.tvJamMasuk6, web2[5]);
        remoteViews.setTextViewText(R.id.tvNamaMK6, web3[5]);*/
        //

        Bitmap largeIcon = BitmapFactory.decodeResource(
                ctx.getResources(), R.mipmap.ic_launcher);

        PendingIntent pitNotificacao = criarPendingIntent(ctx, id);

        Calendar calendar = Calendar.getInstance();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.school)
                        .setContentTitle("Semangat Kuliah!")
                        .setContentText("Kamu ada " +ngetes+" jadwal hari ini.")
                        //.setCustomContentView(remoteViews)
                        //.setCustomBigContentView(bigRemoteView)
                        .setWhen(System.currentTimeMillis())
                        .setLargeIcon(largeIcon)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentIntent(pitNotificacao)
                        .setNumber(id)
                        .setSubText("Tap untuk cek jadwal kuliah kamu hari ini.");

        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id, mBuilder.build());
    }

    public static void createNotification2(Context ctx, int id, String web[], String web2[],
                                          String web3[], String web4[], String web5[], String web6[]) {


        //Tes Fahri
        /*RemoteViews remoteViews = new RemoteViews(ctx.getPackageName(), R.layout.list_notif);
        remoteViews.setImageViewResource(R.id.imageIcon, R.drawable.school);
        remoteViews.setTextViewText(R.id.tvJamMasuk1, web2[0]);
        remoteViews.setTextViewText(R.id.tvNamaMK1, web3[0]);
        remoteViews.setTextViewText(R.id.tvJamMasuk2, web2[1]);
        remoteViews.setTextViewText(R.id.tvNamaMK2, web3[1]);
        remoteViews.setTextViewText(R.id.tvJamMasuk3, web2[2]);
        remoteViews.setTextViewText(R.id.tvNamaMK3, web3[2]);
        remoteViews.setTextViewText(R.id.tvJamMasuk4, web2[3]);
        remoteViews.setTextViewText(R.id.tvNamaMK4, web3[3]);
        remoteViews.setTextViewText(R.id.tvJamMasuk5, web2[4]);
        remoteViews.setTextViewText(R.id.tvNamaMK5, web3[4]);
        remoteViews.setTextViewText(R.id.tvJamMasuk6, web2[5]);
        remoteViews.setTextViewText(R.id.tvNamaMK6, web3[5]);*/
        //

        Bitmap largeIcon = BitmapFactory.decodeResource(
                ctx.getResources(), R.mipmap.ic_launcher);

        PendingIntent pitNotificacao = criarPendingIntent(ctx, id);

        Calendar calendar = Calendar.getInstance();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.school)
                        .setContentTitle("Selamat Beristirahat!")
                        .setContentText("Kamu libur hari ini.")
                        //.setCustomContentView(remoteViews)
                        //.setCustomBigContentView(bigRemoteView)
                        .setWhen(System.currentTimeMillis())
                        .setLargeIcon(largeIcon)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentIntent(pitNotificacao)
                        .setNumber(id)
                        .setSubText("Gunakan waktu luang mu dengan benar :)");

        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id, mBuilder.build());
    }



}

