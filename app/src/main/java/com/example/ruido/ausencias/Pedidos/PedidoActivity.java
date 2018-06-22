package com.example.ruido.ausencias.Pedidos;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruido.ausencias.Ausencias.MinhasAusenciasActivity;
import com.example.ruido.ausencias.Conexao;
import com.example.ruido.ausencias.R;
import com.example.ruido.ausencias.popup2;


public class PedidoActivity extends AppCompatActivity {

    TextView Nome, DataInicio, DataFim, Motivo, Horas, Observacoes;
    String idpedido, nome, datainicio, datafim, motivo, horas, observacoes, idutilizador, nivelacesso, primeironome, ultimonome;
    String url = "";
    Button Aprovar;
    Button Rejeitar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovacaopedido);
        Nome = findViewById(R.id.nome2);
        DataInicio = findViewById(R.id.dateStartapro);
        DataFim = findViewById(R.id.dateFinishapro);
        Motivo = findViewById(R.id.spinnermotivoapro);
        Horas = findViewById(R.id.spinnerhorasapro);
        Observacoes = findViewById(R.id.observaçõestextoapro);
        Aprovar = findViewById(R.id.botaoaprova);
        Rejeitar = findViewById(R.id.rejeita);
        idpedido = getIntent().getExtras().getString("id");
        nome = getIntent().getExtras().getString("nome");
        datainicio = getIntent().getExtras().getString("datainicio");
        datafim = getIntent().getExtras().getString("datafim");
        motivo = getIntent().getExtras().getString("motivo");
        horas = getIntent().getExtras().getString("horas");
        observacoes = getIntent().getExtras().getString("observacoes");
        Nome.setText(nome);
        DataInicio.setText(datainicio);
        DataFim.setText(datafim);
        Motivo.setText(motivo);
        Horas.setText(horas);
        Observacoes.setText(observacoes);
        idutilizador = getIntent().getExtras().getString("id_user");
        nivelacesso = getIntent().getExtras().getString("acesslevel");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");
    }


    public void Aceitar(View view) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            url = "http://192.168.2.252:81/Ausencia/aceitar.php?id=" + idpedido;//metodo POST
            new PedidoActivity.SolicitaDados().execute(url);
        } else {
            Toast.makeText(getApplicationContext(), "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
        }
    }


    public void Rejeitar(View view) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            url = "http://192.168.2.252:81/Ausencia/rejeitar.php?id=" + idpedido;//metodo POST
            new PedidoActivity.SolicitaDados().execute(url);
        } else {
            Toast.makeText(getApplicationContext(), "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);


        MenuItem m1 = menu.add(0, 0, 0, "Pedidos Ausência");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m2 = menu.add(0, 1, 1, "Inserir Ausência");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m3 = menu.add(0, 2, 2, "Todas Ausências");
        m3.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case 0:
                Intent intent = new Intent(this, PedidosActivity.class);
                intent.putExtra("id_user", idutilizador);
                intent.putExtra("acesslevel", nivelacesso);
                intent.putExtra("firstname", primeironome);
                intent.putExtra("lastname", ultimonome);
                startActivity(intent);
                finish();
                break;

            case 1:
                Intent intent2 = new Intent(this, MinhasAusenciasActivity.class);
                intent2.putExtra("id_user", idutilizador);
                intent2.putExtra("acesslevel", nivelacesso);
                intent2.putExtra("firstname", primeironome);
                intent2.putExtra("lastname", ultimonome);
                startActivity(intent2);
                finish();
                break;

            case 2:
                Intent intent3 = new Intent(this, MinhasAusenciasActivity.class);
                intent3.putExtra("id_user", idutilizador);
                intent3.putExtra("acesslevel", nivelacesso);
                intent3.putExtra("firstname", primeironome);
                intent3.putExtra("lastname", ultimonome);
                startActivity(intent3);
                finish();
                break;

        }
        return (true);
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("registo_ok")) {
                Intent intent3 = new Intent(PedidoActivity.this, popup2.class);
                intent3.putExtra("id_user", idutilizador);
                intent3.putExtra("acesslevel", nivelacesso);
                intent3.putExtra("firstname", primeironome);
                intent3.putExtra("lastname", ultimonome);
                startActivity(intent3);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Algo correu mal", Toast.LENGTH_LONG).show();
            }
        }
    }
}




