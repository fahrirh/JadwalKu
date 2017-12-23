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

    @Override
    public void onReceive(Context context, Intent intent) {

        ct=context;

        dbcenter= new DataHelper(ct);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "ORDER BY urut ASC;",null);
        web = new String[cursor.getCount()];
        web2 = new String[cursor.getCount()];
        web3 = new String[cursor.getCount()];
        web4 = new String[cursor.getCount()];
        web5 = new String[cursor.getCount()];
        web6 = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition  (cc);
            web[cc] = cursor.getString(0).toString();
            web2[cc] = cursor.getString(1).toString();
            web3[cc] = "Ruangan: "+cursor.getString(2).toString();
            web4[cc] = "Dosen: "+cursor.getString(3).toString();
            web5[cc] = "Jam Masuk: "+cursor.getString(4).toString();
            web6[cc] = "Jam Keluar: "+cursor.getString(5).toString();
        }

        AlarmManagerUtil alarmUtil = new AlarmManagerUtil();
        alarmUtil.initAlarmNotification(context);

        createNotification(context, 1, web[0], web2[0], web3[0], web4[0], web5[0], web6[0]);
    }

    private static PendingIntent criarPendingIntent(
            Context ctx, int id) {

        Intent resultIntent = new Intent(ctx, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        return stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void createNotification(Context ctx, int id, String web, String web2, String web3, String web4, String web5, String web6) {


        CustomList adapter = new CustomList(ctx, web, web2, web3, web4, web5, web6);
        //Tes Fahri
        RemoteViews remoteViews = new RemoteViews(ctx.getPackageName(), R.layout.list_notif);
        //remoteViews.setImageViewResource(R.id.image_icon, iconResource);
        remoteViews.setTextViewText(R.id.tvJamMasuk, web2);
        remoteViews.setTextViewText(R.id.tvNamaMK, web3);
        //

        Bitmap largeIcon = BitmapFactory.decodeResource(
                ctx.getResources(), R.mipmap.ic_launcher);

        PendingIntent pitNotificacao = criarPendingIntent(ctx, id);

        Calendar calendar = Calendar.getInstance();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Tes")
                        .setContentText("HOUR: " + web + ":" + calendar.get(Calendar.MINUTE))
                        .setCustomContentView(remoteViews)
                        //.setCustomBigContentView(bigRemoteView)
                        .setWhen(System.currentTimeMillis())
                        .setLargeIcon(largeIcon)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentIntent(pitNotificacao)
                        .setNumber(id)
                        .setSubText("GORIO Engenharia");

        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id, mBuilder.build());
    }
}

