package hasibuan.sabtusore;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * Created by Fahri Ramadhan H on 12/15/2017.
 */

public class GetHariMingguan extends Activity {
    protected Cursor cursorsenin, cursorselasa, cursorrabu, cursorkamis, cursorjumat;
    DataHelper senin, selasa, rabu, kamis, jumat;
    String[] seninhari, seninmk, seninruangan, senindosen, seninjammasuk, seninjamkeluar;
    String[] selasahari, selasamk, selasaruangan, selasadosen, selasajammasuk, selasajamkeluar;
    String[] rabuhari, rabumk, raburuangan, rabudosen, rabujammasuk, rabujamkeluar;
    String[] kamishari, kamismk, kamisruangan, kamisdosen, kamisjammasuk, kamisjamkeluar;
    String[] jumathari, jumatmk, jumatruangan, jumatdosen, jumatjammasuk, jumatjamkeluar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetSenin();
        GetSelasa();
        GetRabu();
        GetKamis();
        GetJumat();
    }

    public String[] GetSenin(){
        senin = new DataHelper(this);
        SQLiteDatabase dbsenin = senin.getReadableDatabase();
        cursorsenin = dbsenin.rawQuery("SELECT datahari.hari, " +
                "jadwal.namamk, " +
                "jadwal.ruangan, " +
                "jadwal.dosen, " +
                "jadwal.jammasuk, " +
                "jadwal.jamkeluar " +
                "FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.hari = 'senin';",null);

        seninhari = new String[cursorsenin.getCount()];
        seninmk = new String[cursorsenin.getCount()];
        seninruangan = new String[cursorsenin.getCount()];
        senindosen = new String[cursorsenin.getCount()];
        seninjammasuk = new String[cursorsenin.getCount()];
        seninjamkeluar = new String[cursorsenin.getCount()];
        cursorsenin.moveToFirst();
        for (int cc=0; cc < cursorsenin.getCount(); cc++){
            cursorsenin.moveToPosition  (cc);
            seninhari[cc] = cursorsenin.getString(0).toString();
            seninmk[cc] = cursorsenin.getString(1).toString();
            seninruangan[cc] = "Ruangan: "+cursorsenin.getString(2).toString();
            senindosen[cc] = "Dosen: "+cursorsenin.getString(3).toString();
            seninjammasuk[cc] = "Jam Masuk: "+cursorsenin.getString(4).toString();
            seninjamkeluar[cc] = "Jam Keluar: "+cursorsenin.getString(5).toString();
        }
        return seninjammasuk;
    }

    public void GetSelasa(){
        selasa = new DataHelper(this);
        SQLiteDatabase dbselasa = selasa.getReadableDatabase();
        cursorselasa = dbselasa.rawQuery("SELECT datahari.hari, " +
                "jadwal.namamk, " +
                "jadwal.ruangan, " +
                "jadwal.dosen, " +
                "jadwal.jammasuk, " +
                "jadwal.jamkeluar " +
                "FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.urut = 'b';",null);

        selasahari = new String[cursorselasa.getCount()];
        selasamk = new String[cursorselasa.getCount()];
        selasaruangan = new String[cursorselasa.getCount()];
        selasadosen = new String[cursorselasa.getCount()];
        selasajammasuk = new String[cursorselasa.getCount()];
        selasajamkeluar = new String[cursorselasa.getCount()];
        cursorselasa.moveToFirst();
        for (int cc=0; cc < cursorselasa.getCount(); cc++){
            cursorselasa.moveToPosition  (cc);
            selasahari[cc] = cursorselasa.getString(0).toString();
            selasamk[cc] = cursorselasa.getString(1).toString();
            selasaruangan[cc] = "Ruangan: "+cursorselasa.getString(2).toString();
            selasadosen[cc] = "Dosen: "+cursorselasa.getString(3).toString();
            selasajammasuk[cc] = "Jam Masuk: "+cursorselasa.getString(4).toString();
            selasajamkeluar[cc] = "Jam Keluar: "+cursorselasa.getString(5).toString();
        }
    }

    public void GetRabu(){
        rabu = new DataHelper(this);
        SQLiteDatabase dbrabu = rabu.getReadableDatabase();
        cursorrabu = dbrabu.rawQuery("SELECT datahari.hari, " +
                "jadwal.namamk, " +
                "jadwal.ruangan, " +
                "jadwal.dosen, " +
                "jadwal.jammasuk, " +
                "jadwal.jamkeluar " +
                "FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.urut = 'c';",null);

        rabuhari = new String[cursorrabu.getCount()];
        rabumk = new String[cursorrabu.getCount()];
        raburuangan = new String[cursorrabu.getCount()];
        rabudosen = new String[cursorrabu.getCount()];
        rabujammasuk = new String[cursorrabu.getCount()];
        rabujamkeluar = new String[cursorrabu.getCount()];
        cursorrabu.moveToFirst();
        for (int cc=0; cc < cursorrabu.getCount(); cc++){
            cursorrabu.moveToPosition  (cc);
            rabuhari[cc] = cursorrabu.getString(0).toString();
            rabumk[cc] = cursorrabu.getString(1).toString();
            raburuangan[cc] = "Ruangan: "+cursorrabu.getString(2).toString();
            rabudosen[cc] = "Dosen: "+cursorrabu.getString(3).toString();
            rabujammasuk[cc] = "Jam Masuk: "+cursorrabu.getString(4).toString();
            rabujamkeluar[cc] = "Jam Keluar: "+cursorrabu.getString(5).toString();
        }
    }

    public void GetKamis(){
        kamis = new DataHelper(this);
        SQLiteDatabase dbkamis = kamis.getReadableDatabase();
        cursorkamis = dbkamis.rawQuery("SELECT datahari.hari, " +
                "jadwal.namamk, " +
                "jadwal.ruangan, " +
                "jadwal.dosen, " +
                "jadwal.jammasuk, " +
                "jadwal.jamkeluar " +
                "FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.urut = 'd';",null);

        kamishari = new String[cursorkamis.getCount()];
        kamismk = new String[cursorkamis.getCount()];
        kamisruangan = new String[cursorkamis.getCount()];
        kamisdosen = new String[cursorkamis.getCount()];
        kamisjammasuk = new String[cursorkamis.getCount()];
        kamisjamkeluar = new String[cursorkamis.getCount()];
        cursorkamis.moveToFirst();
        for (int cc=0; cc < cursorkamis.getCount(); cc++){
            cursorkamis.moveToPosition  (cc);
            kamishari[cc] = cursorkamis.getString(0).toString();
            kamismk[cc] = cursorkamis.getString(1).toString();
            kamisruangan[cc] = "Ruangan: "+cursorkamis.getString(2).toString();
            kamisdosen[cc] = "Dosen: "+cursorkamis.getString(3).toString();
            kamisjammasuk[cc] = "Jam Masuk: "+cursorkamis.getString(4).toString();
            kamisjamkeluar[cc] = "Jam Keluar: "+cursorkamis.getString(5).toString();
        }
    }

    public void GetJumat(){
        jumat = new DataHelper(this);
        SQLiteDatabase dbjumat = jumat.getReadableDatabase();
        cursorjumat = dbjumat.rawQuery("SELECT datahari.hari, " +
                "jadwal.namamk, " +
                "jadwal.ruangan, " +
                "jadwal.dosen, " +
                "jadwal.jammasuk, " +
                "jadwal.jamkeluar " +
                "FROM jadwal " +
                "JOIN datahari ON jadwal.hari=datahari.hari " +
                "WHERE datahari.urut = 'e';",null);

        jumathari = new String[cursorjumat.getCount()];
        jumatmk = new String[cursorjumat.getCount()];
        jumatruangan = new String[cursorjumat.getCount()];
        jumatdosen = new String[cursorjumat.getCount()];
        jumatjammasuk = new String[cursorjumat.getCount()];
        jumatjamkeluar = new String[cursorjumat.getCount()];
        cursorjumat.moveToFirst();
        for (int cc=0; cc < cursorjumat.getCount(); cc++){
            cursorjumat.moveToPosition  (cc);
            jumathari[cc] = cursorjumat.getString(0).toString();
            jumatmk[cc] = cursorjumat.getString(1).toString();
            jumatruangan[cc] = "Ruangan: "+cursorjumat.getString(2).toString();
            jumatdosen[cc] = "Dosen: "+cursorjumat.getString(3).toString();
            jumatjammasuk[cc] = "Jam Masuk: "+cursorjumat.getString(4).toString();
            jumatjamkeluar[cc] = "Jam Keluar: "+cursorjumat.getString(5).toString();
        }
    }
}
