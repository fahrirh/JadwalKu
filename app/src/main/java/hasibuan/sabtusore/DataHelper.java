package hasibuan.sabtusore;

/**
 * Created by Fahri Ramadhan Hsb on 11/5/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "perkuliahan.db";
    private static final int DATABASE_VERSION = 1;
    String sql,sql1,sql2;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        // tes commit
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        sql = "create table jadwal(kodemk text primary key, namamk text null, " +
                "ruangan text null, dosen text null, hari text null, jammasuk text null, jamkeluar text null);";
        Log.d("Data", "onCreate: " + sql);
        //Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql);
        //db.execSQL(sql2);
        sql = "INSERT INTO jadwal (kodemk, namamk, ruangan, dosen, hari, jammasuk, jamkeluar) " +
                "VALUES ('IF8501', 'Sistem Multimedia', 'R.4.09', 'Cecep Nurul Alam','Senin', '7.00', '8.40')," +
                    "('IF8505', 'Data Mining', 'R.4.05', 'Rahmat Zainal','Senin', '8.40', '10.20')," +
                "('IF8506', 'Peraktikum Sistem Multimedia', 'R.4.11', 'Cepy Slamet','Selasa', '7.00', '8.40')," +
                "('IF8501L', 'E-Commerce', 'R.4.03', 'Acep Hida','Selasa', '9.30', '12.00')," +
                "('IF8708', 'Praktikum Mobile Programming', 'R.4.07', 'Achmad Subagja','Selasa', '16.20', '18.00')," +
                "('IF8701L', 'Mobile Programming', 'R.4.05', 'Nur Lukman','Rabu', '10.20', '12.00')," +
                "('IF8503', 'Proyek Perangkat Lunak', 'R.4.01', 'Cecep Nurul Alam','Rabu', '12.00', '15.10')," +
                "('IF8701', 'Wireless dan Mobile Comp', 'R.4.04', 'Diena Raudiena','Kamis', '8.40', '10.20')," +
                "('IF8502L', 'Praktikum Wireless dan MC', 'R.4.01', 'Wildan Budiawan','Kamis', '10.20', '12.00')," +
                "('IF8505L', 'Metode Penelitian', 'R.4.11', 'Rahmat Zainal','Kamis', '14.20', '16.00')," +
                "('IF8502', 'Sites Informasi Geografis', 'R.4.10', 'Wisnu Uriawan','Jumat', '6.50', '8.30')," +
                "('IF8504', 'Sistem Cerdas', 'R.4.11', 'Esa Firmansyah','Jumat', '9.20', '11.50');";
        db.execSQL(sql);
        //sql = "INSERT INTO note (no, catatan) VALUES ('1', 'Jangan lupa bimbingan hari ini');";
        //db.execSQL(sql);

        sql1 = "create table note(catatan text primary key, waktu text null);";
        Log.d("Data", "onCreate: " + sql1);
        //Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql1);
        //db.execSQL(sql2);
        sql1 = "INSERT INTO note (catatan, waktu) VALUES ('Jangan lupa bimbingan hari ini','21-13-20116; 03.01 PM');";
        db.execSQL(sql1);

        /*//////Membuat Trigger Update Note dengan primary key baru
        sql2 = "create table tes(no text primary key, catatan text null);";
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);
        /////////////////////////*/

        /////////////////////////////////////// dITAMBAHKAN 3 Desember
        sql2 = "create table datahari(hari text primary key, urut text null);";
        Log.d("Data", "onCreate: " + sql2);
        //Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);
        //db.execSQL(sql2);
        sql2 = "INSERT INTO datahari (hari, urut) VALUES ('Senin','a'),('Selasa','b'),('Rabu','c')," +
                "('Kamis','d'),('Jumat','e'),('Sabtu','f'),('Minggu','g');";
        db.execSQL(sql2);
        /////////////////////////////////////////

        /*String sql2 = "create table note(no integer primary key, cacatan text null);";
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);
        sql2 = "INSERT INTO jadwal (no, catatan) VALUES ('1', 'Jangan lupa bimbingan hari ini');";
        db.execSQL(sql2);*/


    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}


