package com.example.ruido.ausencias.Ausencias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruido.ausencias.R;

import java.util.ArrayList;


//Class responsável da ligação entre a class Ausencia com a class MinhasAusenciasActivity

public class AusenciaAdapter extends RecyclerView.Adapter<Ausencia> {
    Context context;
    ArrayList<String> Name, Reason, Startdate, Finishdate, State;

    public AusenciaAdapter(Context context, ArrayList<String> Name, ArrayList<String> Reason, ArrayList<String> Startdate, ArrayList<String> Finishdate, ArrayList<String> State) {
        this.context = context;
        this.Name = Name;
        this.Reason = Reason;
        this.Startdate = Startdate;
        this.Finishdate = Finishdate;
        this.State = State;

    }

    //Class que faz com que o layout activity_pedido apareça as vezes necessárias para a informação existente
    @Override
    public Ausencia onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.activity_minhaausencia, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new Ausencia(v);
    }


    //Class que faz os dados serem exibidos
    @Override
    public void onBindViewHolder(Ausencia holder, int position) {

        holder.nome.setText(Name.get(position));
        holder.motivo.setText(Reason.get(position));
        holder.datainicio.setText(Startdate.get(position));
        holder.datafim.setText(Finishdate.get(position));
        holder.estado.setText(State.get(position));
    }

    //Class que retorna o numero de itens na RecycleView
    @Override
    public int getItemCount() {
        return Name.size();
    }
}