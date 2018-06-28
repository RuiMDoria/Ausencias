package com.example.ruido.ausencias.Pedidos;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ruido.ausencias.Conexao;
import com.example.ruido.ausencias.popup2;

/**
 * Created by ruido on 25/06/2018.
 */

public class PedidoController {

    String url = "";
    String idutilizador, ultimonome, nivelacesso, primeironome;
    private Context context;

    public void aceitar(String idpedido, String idutilizador, String ultimonome, String nivelacesso, String primeironome, Context context, NetworkInfo networkInfo) {

        this.context = context;
        this.idutilizador = idutilizador;
        this.ultimonome = ultimonome;
        this.nivelacesso = nivelacesso;
        this.primeironome = primeironome;

        if (networkInfo != null && networkInfo.isConnected()) {
            url = "http://visualthinking.ddns.net/Ausencia/aceitar.php?id=" + idpedido;//metodo POST
            new PedidoController.SolicitaDados().execute(url);
        } else {
            Toast.makeText(context, "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
        }
    }

    public void rejeitar(String idpedido, String idutilizador, String ultimonome, String nivelacesso, String primeironome, Context context, NetworkInfo networkInfo) {

        this.context = context;
        this.idutilizador = idutilizador;
        this.ultimonome = ultimonome;
        this.nivelacesso = nivelacesso;
        this.primeironome = primeironome;


        if (networkInfo != null && networkInfo.isConnected()) {
            url = "http://visualthinking.ddns.net/Ausencia/rejeitar.php?id=" + idpedido;//metodo POST
            new PedidoController.SolicitaDados().execute(url);
        } else {
            Toast.makeText(context, "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
        }
    }


    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("registo_ok")) {
                Intent intent3 = new Intent(context, popup2.class);
                intent3.putExtra("id_user", idutilizador);
                intent3.putExtra("acesslevel", nivelacesso);
                intent3.putExtra("firstname", primeironome);
                intent3.putExtra("lastname", ultimonome);
                context.startActivity(intent3);
                ((PedidoActivity) context).finish();
            } else {
                Toast.makeText(context, "Algo correu mal", Toast.LENGTH_LONG).show();
            }
        }
    }
}


