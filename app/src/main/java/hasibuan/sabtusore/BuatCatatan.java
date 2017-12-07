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
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import android.widget.TimePicker;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class BuatCatatan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendarjam;
    private String format = "";
    //TimePicker timePicker;
    //TextView displayTime;
    //TimePicker tp;
    //TextView tv;
    //OnTimeChangedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_catatan);
        dbHelper = new DataHelper(this);

        //////////////////////
        dateView = (TextView) findViewById(R.id.textViewTanggal);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        //////////////////////////////////////////
        //timePicker1 = (TimePicker) findViewById(R.id.textViewJam);
        time = (TextView) findViewById(R.id.textViewJam);
        calendarjam = Calendar.getInstance();

        int hour = calendarjam.get(Calendar.HOUR_OF_DAY);
        int min = calendarjam.get(Calendar.MINUTE);
        showTime(hour, min);
        ///////////////////////////////////////////

        /*////////////////////////
        displayTime = (TextView) findViewById(R.id.textViewJam);
        timePicker.setIs24HourView(true);
        displayTime.setText(currentTime());
        ////////////////////////////*/
        //tp = (TimePicker) findViewById(R.id.timePicker1);
        //tv = (TextView) findViewById(R.id.textViewJam);
        //listener jika terjadi perubahan isi TimePicker
        /*listener = new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay,
                                      int minute) {
                Toast.makeText(BuatCatatan.this,"Set Waktu: "+hourOfDay+":"+minute,
                        Toast.LENGTH_SHORT).show();
            }
        };*/
        //Menerapkan listener pada TimePicker tp
        //tp.setOnTimeChangedListener(listener);

        ////////////////////////////////


        //text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into note(catatan, waktu) values('" +
                        text2.getText().toString() + "','" +
                        dateView.getText().toString() + "; " + time.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                LihatCatatan.ma.RefreshList();
                finish();
            }
        })
        ;
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    //////////////////////////////////
    @SuppressWarnings("deprecation")
    public void setTime(View view) {
        timePicker1.setIs24HourView(true);
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTime(hour, min);
    }


    //////////////////////////
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        //akan menampilkan teks ketika kalendar muncul setelah menekan tombol
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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
    /*/////////////////////////
    public String currentTime() {
        String mcurrentTime = "Time: " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
        return mcurrentTime;
    }
    /////////////////////////*/
}

