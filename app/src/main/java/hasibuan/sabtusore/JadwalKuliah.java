package hasibuan.sabtusore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;


public class JadwalKuliah extends Activity {
    ListView list;

    String[] web, web2, web3, web4, web5, web6;
    //String[] web2;
    //String[] web3;
    //String[] web4;
    //String[] web5;
    //String[] web6;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static JadwalKuliah ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ma = this;
        dbcenter = new DataHelper(this);
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);

        CustomList adapter = new
                CustomList(JadwalKuliah.this, web, web2, web3, web4, web5, web6);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(JadwalKuliah.this, web2[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }
}