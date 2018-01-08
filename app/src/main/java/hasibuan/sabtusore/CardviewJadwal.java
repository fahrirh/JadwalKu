package hasibuan.sabtusore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardviewJadwal extends AppCompatActivity {

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
    public static CardviewJadwal ma;

    private ArrayList<String> kota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        setContentView(R.layout.activity_cardview_jadwal);
        initView();
    }

    private void initView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        kota = new ArrayList<>();
        kota.add("Semarang");
        kota.add("Jakarta");
        kota.add("Surabaya");
        kota.add("Bandung");
        kota.add("Srakarta");
        kota.add("Depok");
        kota.add("Semarang");
        kota.add("Kendal");
        kota.add("Bogor");


        RecyclerView.Adapter adapter = new DataAdapter(kota);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e){
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(child);
                    Toast.makeText(getApplicationContext(), kota.get(position), Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}