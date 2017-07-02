package com.PC.PC.ashpazi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC on 5/23/2017.
 */

public class KhordaniAdapter extends BaseAdapter {
    public KhordaniAdapter(Context context, List<Khordani> khordanis) {
        this.context = context;
        this.khordanis = khordanis;
    }
    //listi ke be in class dade mishavad ra be list_layout vasl mikonad ke dar
    private Context context;
    private List<Khordani> khordanis;

    @Override
    public int getCount() {
        return khordanis.size();
    }

    @Override
    public Object getItem(int position) {
        return khordanis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View row, ViewGroup root) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=inflater.inflate(R.layout.list_layout,root,false);
        TextView txtEsmeKhordani=(TextView)row.findViewById(R.id.txt_esme_khordani);
        TextView txtMavadeLazem=(TextView)row.findViewById(R.id.txt_mavade_lazem);
        txtEsmeKhordani.setText(khordanis.get(position).getEsmeKhordani());
        txtMavadeLazem.setText(khordanis.get(position).getMavadeLazem());
        return row;
    }
}
