package com.example.ruido.ausencias.Inserir;


import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ruido.ausencias.Conexao;
import com.example.ruido.ausencias.popup1;


public class InserirController {

    String url = "";
    String idutilizador, ultimonome, nivelacesso, primeironome;
    private Context context;

    public void Enviar(String dateStart, String dateFinish, String motivo, String horas, String observacoes, String nome, String idutilizador, String primeironome, String ultimonome, String nivelacesso, Context context, NetworkInfo networkInfo) {

        this.context = context;
        this.idutilizador = idutilizador;
        this.ultimonome = ultimonome;
        this.nivelacesso = nivelacesso;
        this.primeironome = primeironome;

        if (networkInfo != null && networkInfo.isConnected()) {


            if (dateStart.isEmpty() || dateFinish.isEmpty() || motivo.isEmpty() || horas.isEmpty()) {
                Toast.makeText(context, "Nenhum campo obrigatório pode estar vazio", Toast.LENGTH_LONG).show();
            } else {
                url = "http://192.168.2.252:81/Ausencia/inserir.php?startdate=" + dateStart + "&finishdate=" + dateFinish + "&reason=" + motivo + "&hours=" + horas + "&comments=" + observacoes + "&fk_id_user=" + idutilizador + "&name=" + nome;//metodo POST
            }
            new InserirController.SolicitaDados().execute(url);
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
                Intent intent3 = new Intent(context, popup1.class);
                intent3.putExtra("id_user", idutilizador);
                intent3.putExtra("acesslevel", nivelacesso);
                intent3.putExtra("firstname", primeironome);
                intent3.putExtra("lastname", ultimonome);
                context.startActivity(intent3);
                ((InserirActivity) context).finish();

            } else {
                Toast.makeText(context, "Algo correu mal", Toast.LENGTH_LONG).show();
            }
        }
    }
}
