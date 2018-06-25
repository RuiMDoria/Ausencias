package com.example.ruido.ausencias;

import android.content.Context;
import android.content.Intent;

import com.example.ruido.ausencias.Ausencias.MinhasAusenciasActivity;
import com.example.ruido.ausencias.Inserir.InserirActivity;
import com.example.ruido.ausencias.Pedidos.PedidosActivity;

/**
 * Created by ruido on 25/06/2018.
 */

public class MainController {
    private Context context;

    public void pedidos(String idutilizador, String nivelacesso, String primeironome, String ultimonome, Context context) {
        this.context = context;
        Intent intent = new Intent(context, PedidosActivity.class);
        intent.putExtra("id_user", idutilizador);
        intent.putExtra("acesslevel", nivelacesso);
        intent.putExtra("firstname", primeironome);
        intent.putExtra("lastname", ultimonome);
        context.startActivity(intent);
    }

    public void inserir(String idutilizador, String nivelacesso, String primeironome, String ultimonome, Context context) {
        this.context = context;
        Intent intent = new Intent(context, InserirActivity.class);
        intent.putExtra("id_user", idutilizador);
        intent.putExtra("acesslevel", nivelacesso);
        intent.putExtra("firstname", primeironome);
        intent.putExtra("lastname", ultimonome);
        context.startActivity(intent);
    }

    public void minhasausencias(String idutilizador, String nivelacesso, String primeironome, String ultimonome, Context context) {
        this.context = context;
        Intent intent = new Intent(context, MinhasAusenciasActivity.class);
        intent.putExtra("id_user", idutilizador);
        intent.putExtra("acesslevel", nivelacesso);
        intent.putExtra("firstname", primeironome);
        intent.putExtra("lastname", ultimonome);
        context.startActivity(intent);
    }
}
