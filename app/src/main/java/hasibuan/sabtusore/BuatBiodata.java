package hasibuan.sabtusore;



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


public class BuatBiodata extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5, text6, text7;
    Spinner spinner;
    private TimePicker timePicker1, timePicker2;
    private TextView time, time2;
    private Calendar calendar;
    //private String format = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_biodata);
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

        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        //text5 = (EditText) findViewById(R.id.editText5);
        //text6 = (EditText) findViewById(R.id.editText6);
        //text7 = (EditText) findViewById(R.id.editText7);
        ton1 = (Button) findViewById(R.id.button1);
        //ton2 = (Button) findViewById(R.id.button2);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into jadwal(kodemk, namamk, ruangan, dosen, hari, jammasuk, jamkeluar) values('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "','" +
                        spinner.getSelectedItem().toString() + "','" +
                        time.getText().toString() + "','" +
                        time2.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                LihatData.ma.RefreshList();
                finish();
            }
        })
        ; /*ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });*/
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

