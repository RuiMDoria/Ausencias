package com.example.ruido.ausencias.Pedidos;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruido.ausencias.R;


//Class que atribui variaveis a cada elemento do layout activity_pedido
public class Pedido extends RecyclerView.ViewHolder {
    TextView nome, motivo, datainicio, datafim, estado;
    RelativeLayout recyclelayout;


    public Pedido(View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.tv_name);
        motivo = itemView.findViewById(R.id.tv_motivo);
        datainicio = itemView.findViewById(R.id.tv_startdate);
        datafim = itemView.findViewById(R.id.tv_finishdate);
        estado = itemView.findViewById(R.id.tv_state);
        recyclelayout = itemView.findViewById(R.id.listapedidos);
    }


}
