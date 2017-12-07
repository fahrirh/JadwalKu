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

    /////////////////////////
    String[] web;
    String[] web2;
    String[] web3;
    String[] web4;
    String[] web5;
    String[] web6;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static JadwalKuliah ma;

    //////////////////////////

    /*String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    String[] web2 = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///////////////////////////////
        ma = this;
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT datahari.hari, jadwal.namamk, jadwal.ruangan, jadwal.dosen, jadwal.jammasuk, jadwal.jamkeluar FROM jadwal JOIN datahari ON jadwal.hari=datahari.hari ORDER BY urut ASC;",null);
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
        //////////////////////////////
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

/*
//////////////////////////////////////////////////////////////////Membalikkan nama
    private String kodemk;
    private String namamk;
    private String ruangan;
    private String dosen;
    private String hari;
    private String jammasuk;
    private String jamkeluar;

    public void MK(String kodemk, String namamk, String ruangan, String dosen, String hari, String jammasuk, String jamkeluar) {
        setKodemk(kodemk);
        setNamamk(namamk);
        setRuangan(ruangan);
        setDosen(dosen);
        setHari(hari);
        setJammasuk(jammasuk);
        setJamkeluar(jamkeluar);
    }

    public String getKodemk() {return kodemk;}

    public void setKodemk(String kodemk) {
        this.kodemk = kodemk;
    }

    public String getNamamk() {
        return namamk;
    }

    public void setNamamk(String namamk) {
        this.namamk = namamk;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) { this.hari = hari; }

    public String getJammasuk() {
        return jammasuk;
    }

    public void setJammasuk(String jammasuk) {this.jammasuk = jammasuk;}

    public String getJamkeluar() {
        return jamkeluar;
    }

    public void setJamkeluar(String jamkeluar) {
        this.jamkeluar = jamkeluar;
    }

//////////////////////////////////////////////////////////////////////////*/
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);

        ArrayList list = new ArrayList();
        list.add(new Phone("Galaxy S4", "Samsung"));
        list.add(new Phone("Galaxy S3", "Samsung"));
        list.add(new Phone("Galaxy Mega", "Samsung"));
        list.add(new Phone("Galaxy Note", "Samsung"));
        list.add(new Phone("Iphone", "Apple"));
        list.add(new Phone("HTC One", "HTC"));
        list.add(new Phone("Nexus 5", "LG"));
        list.add(new Phone("Nexus 4", "LG"));
        list.add(new Phone("LG G2", "LG"));
        list.add(new Phone("Moto x", "Motorola"));

        ListAdapter adapter = new ListAdapter(this, list);

        ListView listView = (ListView) findViewById(R.id.id_list);
        listView.setAdapter(adapter);
    }//////////////

    String[] daftar;
    String[] daftar2;
    String[] daftar3;
    String[] daftar4;
    String[] daftar5;
    String[] daftar6;
    String[] daftar7;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static JadwalKuliah ma;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);

        Button btn=(Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(JadwalKuliah.this, BuatBiodata.class);
                startActivity(inte);
            } });
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        ///////////
        ArrayList list = new ArrayList();
        //////////
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
            daftar2[cc] = cursor.getString(1).toString();
            daftar3[cc] = cursor.getString(2).toString();
            daftar4[cc] = cursor.getString(3).toString();
            daftar5[cc] = cursor.getString(4).toString();
            daftar6[cc] = cursor.getString(5).toString();
            daftar7[cc] = cursor.getString(6).toString();
            list.add(new MK(daftar[cc],daftar2[cc],daftar3[cc],daftar4[cc],daftar5[cc],daftar6[cc],daftar7[cc]));
        }
        ListAdapter adapter = new ListAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.id_list);
        listView.setAdapter(adapter);

        /*ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Detail", "Update Detail", "Hapus Mata Kuliah"};
                AlertDialog.Builder builder = new AlertDialog.Builder(LihatData.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 : Intent i = new Intent(getApplicationContext(), LihatBiodata.class);
                                i.putExtra("namamk", selection); startActivity(i); break;
                            case 1 : Intent in = new Intent(getApplicationContext(), UpdateBiodata.class);
                                in.putExtra("namamk", selection); startActivity(in); break;
                            case 2 : SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from jadwal where namamk = '"+selection+"'");
                                RefreshList();
                                break;
                        } } });
                builder.create().show();
            }});/
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
 //////////////////*/
}