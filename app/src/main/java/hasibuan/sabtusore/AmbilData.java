package hasibuan.sabtusore;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * Created by Fahri Ramadhan H on 12/20/2017.
 */

public class AmbilData extends Activity {

    public static final String[] daftar = new String[]{"asas","asasas"};
    protected Cursor cursor;
    DataHelper dbcenter;
    public static AmbilData ma;

    protected void onCreate(Bundle savedInstanceState) {

        ma = this;
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal",null);
        //daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = new String(cursor.getString(1).toString());
        }



        super.onCreate(savedInstanceState);
    }
}
