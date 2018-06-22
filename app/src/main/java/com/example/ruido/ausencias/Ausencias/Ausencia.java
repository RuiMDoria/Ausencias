package com.example.ruido.ausencias.Ausencias;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ruido.ausencias.R;

//Class que atribui variaveis a cada elemento do layout activity_minhaausencia
public class Ausencia extends RecyclerView.ViewHolder {
    TextView nome, motivo, datainicio, datafim, estado;

    public Ausencia(View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.tv_name1);
        motivo = itemView.findViewById(R.id.tv_motivo1);
        datainicio = itemView.findViewById(R.id.tv_startdate1);
        datafim = itemView.findViewById(R.id.tv_finishdate1);
        estado = itemView.findViewById(R.id.tv_state1);
    }
}

