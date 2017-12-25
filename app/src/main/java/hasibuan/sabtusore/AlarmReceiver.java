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
    protected Cursor cursorsenin, cursorselasa, cursorrabu, cursorkamis, cursorjumat, cursorsabtu, cursorminggu;
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

        //Cek data hari
        cursorsenin = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                        "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                        "JOIN datahari ON jadwal.hari=datahari.hari " +
                        "WHERE datahari.hari = 'Senin' ORDER BY urut ASC;",null);
        web = new String[cursorsenin.getCount()];
        cursorsenin.moveToFirst();
        int ngetes= cursorsenin.getCount();
        int a = 0;
        for (int cc=0; cc < cursorsenin.getCount(); cc++){
            cursorsenin.moveToPosition  (cc);
            web[cc] = cursorsenin.getString(0).toString();
            a=cursorsenin.getCount();
        }

        cursorselasa = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.hari = 'Selasa' ORDER BY urut ASC;",null);
        web = new String[cursorselasa.getCount()];
        cursorselasa.moveToFirst();
        int ngetesselasa= cursorselasa.getCount();
        int aselasa = 0;
        for (int cc=0; cc < cursorselasa.getCount(); cc++){
            cursorselasa.moveToPosition  (cc);
            web[cc] = cursorselasa.getString(0).toString();
            aselasa=cursorselasa.getCount();
        }

        cursorrabu = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.hari = 'Rabu' ORDER BY urut ASC;",null);
        web = new String[cursorrabu.getCount()];
        cursorrabu.moveToFirst();
        int ngetesrabu= cursorrabu.getCount();
        int arabu = 0;
        for (int cc=0; cc < cursorrabu.getCount(); cc++){
            cursorrabu.moveToPosition  (cc);
            web[cc] = cursorrabu.getString(0).toString();
            arabu=cursorrabu.getCount();
        }

        cursorkamis = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.hari = 'Kamis' ORDER BY urut ASC;",null);
        web = new String[cursorkamis.getCount()];
        cursorkamis.moveToFirst();
        int ngeteskamis= cursorkamis.getCount();
        int akamis = 0;
        for (int cc=0; cc < cursorkamis.getCount(); cc++){
            cursorkamis.moveToPosition  (cc);
            web[cc] = cursorkamis.getString(0).toString();
            akamis=cursorkamis.getCount();
        }

        cursorjumat = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.hari = 'Jumat' ORDER BY urut ASC;",null);
        web = new String[cursorjumat.getCount()];
        cursorjumat.moveToFirst();
        int ngetesjumat= cursorjumat.getCount();
        int ajumat = 0;
        for (int cc=0; cc < cursorjumat.getCount(); cc++){
            cursorjumat.moveToPosition  (cc);
            web[cc] = cursorjumat.getString(0).toString();
            ajumat=cursorjumat.getCount();
        }

        cursorsabtu = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.hari = 'Sabtu' ORDER BY urut ASC;",null);
        web = new String[cursorsabtu.getCount()];
        cursorsabtu.moveToFirst();
        int ngetessabtu= cursorsabtu.getCount();
        int asabtu = 0;
        for (int cc=0; cc < cursorsabtu.getCount(); cc++){
            cursorsabtu.moveToPosition  (cc);
            web[cc] = cursorsabtu.getString(0).toString();
            asabtu=cursorsabtu.getCount();
        }

        cursorminggu = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, " +
                "jadwal.jammasuk, jadwal.jamkeluar FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.hari = 'Minggu' ORDER BY urut ASC;",null);
        web = new String[cursorminggu.getCount()];
        cursorminggu.moveToFirst();
        int ngetesminggu= cursorminggu.getCount();
        int aminggu = 0;
        for (int cc=0; cc < cursorminggu.getCount(); cc++){
            cursorminggu.moveToPosition  (cc);
            web[cc] = cursorminggu.getString(0).toString();
            aminggu=cursorminggu.getCount();
        }

        //percobaan ke 2 cek hari
        if(a==0){
           adasenin=0;
        }
        else if(a>0){
           adasenin=1;
        }

        if(aselasa==0){
            adaselasa=0;
        }
        else if(aselasa>0){
            adaselasa=1;
        }

        if(arabu==0){
            adarabu=0;
        }
        else if(arabu>0){
            adarabu=1;
        }

        if(akamis==0){
            adakamis=0;
        }
        else if(akamis>0){
            adakamis=1;
        }

        if(ajumat==0){
            adajumat=0;
        }
        else if(ajumat>0){
            adajumat=1;
        }

        if(asabtu==0){
            adasabtu=0;
        }
        else if(asabtu>0){
            adasabtu=1;
        }

        if(aminggu==0){
            adaminggu=0;
        }
        else if(aminggu>0){
            adaminggu=1;
        }

        AlarmManagerUtil alarmUtil = new AlarmManagerUtil();
        alarmUtil.initAlarmNotification(context);

        //Find dayy
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        //Kondisi notif setelah cek data hari
        if(currentDay == 2){
            if(adasenin == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngetes);
            }
            else if(adasenin == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }

        if(currentDay == 3){
            if(adaselasa == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngetesselasa);
            }
            else if(adaselasa == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }

        if(currentDay == 4){
            if(adarabu == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngetesrabu);
            }
            else if(adarabu == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }

        if(currentDay == 5){
            if(adakamis == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngeteskamis);
            }
            else if(adakamis == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }

        if(currentDay == 6){
            if(adajumat == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngetesjumat);
            }
            else if(adajumat == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }

        if(currentDay == 7){
            if(adasabtu == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngetessabtu);
            }
            else if(adasabtu == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }

        if(currentDay == 1){
            if(adaminggu == 1){
                createNotification(context, 1, web, web2, web3, web4, web5, web6, ngetesminggu);
            }
            else if(adaminggu == 0){
                createNotification2(context, 1, web, web2, web3, web4, web5, web6);
            }
        }

    }

    private static PendingIntent criarPendingIntent(
            Context ctx, int id) {

        Intent resultIntent = new Intent(ctx, JadwalHarian.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(JadwalHarian.class);
        stackBuilder.addNextIntent(resultIntent);
        return stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static PendingIntent criarPendingIntent2(
            Context ctx, int id) {

        Intent resultIntent = new Intent(ctx, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(MainActivity.class);
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

        PendingIntent pitNotificacao = criarPendingIntent2(ctx, id);

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

