package hasibuan.sabtusore;

/**
 * Created by Fahri Ramadhan H on 1/8/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AJISETYA on 8/18/2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<String> kota;
    private ArrayList<String> w;
    private ArrayList<String> w2;
    private ArrayList<String> w3;
    private ArrayList<String> w4;
    private ArrayList<String> w5;
    private ArrayList<String> w6;

    public DataAdapter(ArrayList<String> w, ArrayList<String> w2, ArrayList<String> w3, ArrayList<String> w4, ArrayList<String> w5, ArrayList<String> w6){
        this.kota = kota;
        this.w = w;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
        this.w5 = w5;
        this.w6 = w6;

    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.txtw.setText(w.get(i));
        viewHolder.txtw2.setText(w2.get(i));
        viewHolder.txtw3.setText(w3.get(i));
        viewHolder.txtw4.setText(w4.get(i));
        viewHolder.txtw5.setText(w5.get(i));
        viewHolder.txtw6.setText(w6.get(i));
    }

    @Override
    public int getItemCount() {
        return w.size();
        //return w2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtw,txtw2,txtw3, txtw4, txtw5, txtw6;
        public ViewHolder(View view) {
            super(view);

            txtw = (TextView)view.findViewById(R.id.txt);
            txtw2 = (TextView)view.findViewById(R.id.txt2);
            txtw3 = (TextView)view.findViewById(R.id.txt3);
            txtw4 = (TextView)view.findViewById(R.id.txt4);
            txtw5 = (TextView)view.findViewById(R.id.txt5);
            txtw6 = (TextView)view.findViewById(R.id.txt6);
        }
    }
}
