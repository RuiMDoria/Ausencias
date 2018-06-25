package com.example.ruido.ausencias;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by ruido on 25/06/2018.
 */

public class LoginController {
    String url = "";
    private Context context;

    public void doGet(String email, String pass, Context context, NetworkInfo networkInfo) {

        this.context = context;

        if (networkInfo != null && networkInfo.isConnected()) {

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(context, "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
            } else {
                url = "http://192.168.2.252:81/Ausencia/login.php?email=" + email + "&password=" + pass;//metodo GET
            }
            new SolicitaDados().execute(url);
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

            if (resultado.contains("login_ok")) {
                String[] dados = resultado.split(",");
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("id_user", dados[1]);
                intent.putExtra("firstname", dados[2]);
                intent.putExtra("lastname", dados[3]);
                intent.putExtra("acesslevel", dados[4]);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Email ou Password erradas", Toast.LENGTH_LONG).show();
            }
        }
    }
}
