package hasibuan.sabtusore;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.w3c.dom.Text;

public class UpdateCatatan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text2;
    TextView text01, text3;
    String a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_catatan);
        dbHelper = new DataHelper(this);
        text01 = (TextView) findViewById(R.id.textView01);
        text2 = (EditText) findViewById(R.id.editText2);
        //text3 = (TextView) findViewById(R.id.textView3);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM note WHERE catatan = '" + getIntent().getStringExtra("catatan") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            text01.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(0).toString());
            //text3.getText().toString();
        }
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
        // daftarkan even onClick pada btnSimpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.execSQL("update note set catatan='"+
                        text2.getText().toString()   + "' where catatan='" +
                        text01.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                LihatCatatan.ma.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}

