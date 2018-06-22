package com.example.ruido.ausencias.Ausencias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruido.ausencias.R;

import java.util.ArrayList;


/**
 * Created by ruido on 14/05/2018.
 */

public class AusenciaAdapter extends RecyclerView.Adapter<Ausencia> {
    Context _context;
    ArrayList<String> Name, Reason, Startdate, Finishdate, State;

    public AusenciaAdapter(Context _context, ArrayList<String> Name, ArrayList<String> Reason, ArrayList<String> Startdate, ArrayList<String> Finishdate, ArrayList<String> State) {
        this._context = _context;
        this.Name = Name;
        this.Reason = Reason;
        this.Startdate = Startdate;
        this.Finishdate = Finishdate;
        this.State = State;

    }

    @Override
    public Ausencia onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(_context).inflate(R.layout.activity_pedido, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new Ausencia(v);
    }

    @Override
    public void onBindViewHolder(Ausencia holder, int position) {

        holder.nome.setText(Name.get(position));
        holder.motivo.setText(Reason.get(position));
        holder.datainicio.setText(Startdate.get(position));
        holder.datafim.setText(Finishdate.get(position));
        holder.estado.setText(State.get(position));
        ((MinhasAusenciasActivity) _context).finish();
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }
}