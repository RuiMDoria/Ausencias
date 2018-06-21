package com.example.ruido.ausencias.Ausencias;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;

import com.example.ruido.ausencias.R;

import java.util.List;


/**
 * Created by ruido on 14/05/2018.
 */

public class AusenciasAdapter extends BaseAdapter {

    private Context mContext;
    private List<Ausencia> mAusenciaList;

    //Constructor

    public AusenciasAdapter(Context mContext, List<Ausencia> mList) {
        this.mContext = mContext;
        this.mAusenciaList =mList;
    }

    @Override
    public int getCount() {
        return mAusenciaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAusenciaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate (mContext, R.layout.activity_minhaausencia, null);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvPrice = v.findViewById(R.id.tv_price);
        TextView tvDescription = v.findViewById(R.id.tv_description);
        //Set text for TextView
        tvName.setText(mAusenciaList.get(position).getName());
        tvPrice.setText(String.valueOf(mAusenciaList.get(position).getPrice()) + " $");
        tvDescription.setText(mAusenciaList.get(position).getDescription());

        //Save product id to tag
        v.setTag(mAusenciaList.get(position).getId());

        return v;
    }
}