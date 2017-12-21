package hasibuan.sabtusore;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Calendar;

/**
 * Created by Fahri Ramadhan H on 12/15/2017.
 */

public class GetHariIni extends Activity{
    Calendar calendar = Calendar.getInstance();
    int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

    String [] jam = new String[100];
    String [] menit = new String[100];
    public static int [] j = new int[100];
    public static int [] m = new int[100];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cariHari();
        for(int i = 0; i<jam.length; i++){
            j[i]= Integer.parseInt(jam[i]);
            m[i]=Integer.parseInt(menit[i]);
        }
    }

    public void cariHari(){
        if(currentDay == 2){
            GetHariMingguan getHariMingguan = new GetHariMingguan();
            String[]jmasuk = getHariMingguan.seninjammasuk;
            for(int i=0; i<jmasuk.length; i++) {
                for (int a = 0; a < 2; i++) {
                    String [] pisah = jmasuk[i].split(".");
                    jam [i]= pisah[0];
                    menit [i] = pisah[1];
                }
            }
        }
        else if(currentDay == 3){
            GetHariMingguan getHariMingguan = new GetHariMingguan();
            String[]jmasuk = getHariMingguan.selasajammasuk;
            for(int i=0; i<jmasuk.length; i++) {
                for (int a = 0; a < 2; i++) {
                    String [] pisah = jmasuk[i].split(".");
                    jam [i]= pisah[0];
                    menit [i] = pisah[1];
                }
            }
        }
        else if(currentDay == 4){
            GetHariMingguan getHariMingguan = new GetHariMingguan();
            String[]jmasuk = getHariMingguan.rabujammasuk;
            for(int i=0; i<jmasuk.length; i++) {
                for (int a = 0; a < 2; i++) {
                    String [] pisah = jmasuk[i].split(".");
                    jam [i]= pisah[0];
                    menit [i] = pisah[1];
                }
            }
        }
        else if(currentDay == 5){
            GetHariMingguan getHariMingguan = new GetHariMingguan();
            String[]jmasuk = getHariMingguan.kamisjammasuk;
            for(int i=0; i<jmasuk.length; i++) {
                for (int a = 0; a < 2; i++) {
                    String [] pisah = jmasuk[i].split(".");
                    jam [i]= pisah[0];
                    menit [i] = pisah[1];
                }
            }
        }
        else if(currentDay == 6){
            GetHariMingguan getHariMingguan = new GetHariMingguan();
            String[]jmasuk = getHariMingguan.jumatjammasuk;
            for(int i=0; i<jmasuk.length; i++) {
                for (int a = 0; a < 2; i++) {
                    String [] pisah = jmasuk[i].split(".");
                    jam [i]= pisah[0];
                    menit [i] = pisah[1];
                }
            }
        }

    }


}
