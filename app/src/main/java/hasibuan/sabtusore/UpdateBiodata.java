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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
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
import android.widget.ListView;
import android.support.v4.view.MenuItemCompat;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class UpdateBiodata extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text2, text3, text4, text5, text6, text7;
    TextView text01;
    Spinner spinner;
    private TimePicker timePicker1, timePicker2;
    private TextView time, time2;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);
        dbHelper = new DataHelper(this);

        spinner = (Spinner) findViewById(R.id.hari_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hari_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        /////////////////////////TimePicker
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker1.setIs24HourView(true);
        time = (TextView) findViewById(R.id.textViewJamMasuk);

        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        timePicker2.setIs24HourView(true);
        time2 = (TextView) findViewById(R.id.textViewJamKeluar);

        calendar = Calendar.getInstance();
        //calendar2 = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int hour2 = calendar.get(Calendar.HOUR_OF_DAY);
        int min2 = calendar.get(Calendar.MINUTE);
        showTime(hour, min);
        showTime2(hour2, min2);
        ///////////////////////////////

        text01 = (TextView) findViewById(R.id.textView01);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        //text5 = (EditText) findViewById(R.id.editText5);
        //text6 = (EditText) findViewById(R.id.editText6);
        //text7 = (EditText) findViewById(R.id.editText7);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal WHERE namamk = '" + getIntent().getStringExtra("namamk") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text01.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            //text5.setText(cursor.getString(4).toString());
            //text6.setText(cursor.getString(5).toString());
            //text7.setText(cursor.getString(6).toString());
        }
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
        // daftarkan even onClick pada btnSimpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update jadwal set namamk='" +
                        text2.getText().toString() + "', ruangan='" +
                        text3.getText().toString() + "', dosen='" +
                        text4.getText().toString() + "', hari='" +
                        spinner.getSelectedItem().toString() + "', jammasuk='" +
                        time.getText().toString() + "', jamkeluar='" +
                        time2.getText().toString() + "' where kodemk='" +
                        text01.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                LihatData.ma.RefreshList();
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

    ///////////////Sambungan TIMEPICKER
    @SuppressWarnings("deprecation")
    public void setTime(View view) {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTime(hour, min);
        int hour2 = timePicker2.getCurrentHour();
        int min2 = timePicker2.getCurrentMinute();
        showTime2(hour2, min2);
    }

    public void showTime(int hour, int min) {
        /*if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }*/

        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
        );
    }

    /*@SuppressWarnings("deprecation")
    public void setTime2(View view) {
        int hour2 = timePicker2.getCurrentHour();
        int min2 = timePicker2.getCurrentMinute();
        showTime2(hour2, min2);
    }*/

    public void showTime2(int hour2, int min2) {
        /*if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }*/

        time2.setText(new StringBuilder().append(hour2).append(" : ").append(min2)
        );
    }
    ////////////////////////////
}

