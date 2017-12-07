package hasibuan.sabtusore;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailCatatan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton2;
    TextView text1, text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catatan);
        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM note WHERE catatan = '" + getIntent().getStringExtra("catatan") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(0).toString());
        }
        ton2 = (Button) findViewById(R.id.button2);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}

