package com.example.ruido.ausencias.Pedidos;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruido.ausencias.Conexao;
import com.example.ruido.ausencias.MainActivity;
import com.example.ruido.ausencias.R;


public class PedidoActivity extends AppCompatActivity {

    TextView Nome, DataInicio, DataFim, Motivo, Horas, Observacoes;
    String idpedido, nome, datainicio, datafim, motivo, horas, observacoes;
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
    }


    public void Aceitar(View view) {
        Aprovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    if (idpedido.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo obrigatório pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://192.168.2.252:81/Ausencia/aceitar.php?id=" + idpedido;//metodo POST
                    }
                    new PedidoActivity.SolicitaDados().execute(url);
                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Rejeitar(View view) {
        Rejeitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    if (idpedido.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo obrigatório pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://192.168.2.252:81/Ausencia/rejeitar.php?id=" + idpedido;//metodo POST
                    }
                    new PedidoActivity.SolicitaDados().execute(url);
                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void next(View view) {
        Intent intent = new Intent(PedidoActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("registo_ok")) {
                setContentView(R.layout.popup_inserir2);
            } else {
                Toast.makeText(getApplicationContext(), "Algo correu mal", Toast.LENGTH_LONG).show();
            }
        }
    }

}


