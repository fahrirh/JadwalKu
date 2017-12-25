package hasibuan.sabtusore;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton ton1, ton2, ton3, ton4;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tambahan Tes Alarm Notif
        AlarmManagerUtil alarmUtil = new AlarmManagerUtil();
        alarmUtil.initAlarmNotification(this);

        ton1=(ImageButton)findViewById(R.id.buttonLihatmk);
        ton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i =new Intent(getApplicationContext(),LihatData.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Mata Kuliah", Toast.LENGTH_SHORT).show();
            }
        });

        ton2=(ImageButton)findViewById(R.id.buttonLihatnote);
        ton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in =new Intent(getApplicationContext(),LihatCatatan.class);
                startActivity(in);
                Toast.makeText(MainActivity.this, "Catatan", Toast.LENGTH_SHORT).show();
            }
        });

        ton3=(ImageButton)findViewById(R.id.buttonLihatjadwal);
        ton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in =new Intent(getApplicationContext(),JadwalKuliah.class);
                startActivity(in);
                Toast.makeText(MainActivity.this, "Jadwal Kuliah", Toast.LENGTH_SHORT).show();
            }
        });

        ton4=(ImageButton)findViewById(R.id.buttonAlarm);
        ton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in =new Intent(getApplicationContext(),Info.class);
                startActivity(in);
                Toast.makeText(MainActivity.this, "Info", Toast.LENGTH_SHORT).show();
            }
        });



    }

    //


}




