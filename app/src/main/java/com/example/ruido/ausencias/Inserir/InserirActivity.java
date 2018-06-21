package com.example.ruido.ausencias.Inserir;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ruido.ausencias.Ausencias.Ausencia;
import com.example.ruido.ausencias.Ausencias.MinhasAusenciasActivity;
import com.example.ruido.ausencias.Conexao;
import com.example.ruido.ausencias.MainActivity;
import com.example.ruido.ausencias.Pedidos.PedidosActivity;
import com.example.ruido.ausencias.R;

import java.net.URL;
import java.net.URLEncoder;


public class InserirActivity extends AppCompatActivity {
    public static Button DateStart;
    public static Button DateFinish;
    Spinner spinnerMotivo;
    Spinner spinnerHoras;
    EditText textoObservacoes;
    Button Enviar;
    String url="";
    String idutilizador;
    String primeironome;
    String ultimonome;
    String nome;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);
        DateStart = findViewById(R.id.dateStart);
        DateFinish = findViewById(R.id.dateFinish);
        spinnerMotivo = findViewById(R.id.spinnermotivo);
        spinnerHoras = findViewById(R.id.spinnerhoras);
        textoObservacoes = findViewById(R.id.observaçõestexto);
        Enviar = findViewById(R.id.enviar);
        idutilizador = getIntent().getExtras().getString("id_user");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");
        nome = primeironome+" "+ultimonome;

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){
                    String dateStart = DateStart.getText().toString();
                    String dateFinish = DateFinish.getText().toString();
                    String motivo = spinnerMotivo.getSelectedItem().toString();
                    motivo=URLEncoder.encode(motivo);
                    String horas = spinnerHoras.getSelectedItem().toString();
                    String observacoes = textoObservacoes.getText().toString();
                    nome = URLEncoder.encode(nome);




                    if (dateStart.isEmpty()||dateFinish.isEmpty()||motivo.isEmpty()||horas.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Nenhum campo obrigatório pode estar vazio", Toast.LENGTH_LONG).show();
                    }else {
                        url = "http://192.168.2.252:81/Ausencia/inserir.php?startdate=" + dateStart + "&finishdate=" + dateFinish + "&reason=" + motivo + "&hours=" + horas + "&comments=" + observacoes + "&fk_id_user=" + idutilizador + "&name=" + nome;//metodo POST
                    }
                    new InserirActivity.SolicitaDados().execute(url);
                }else{
                    Toast.makeText(getApplicationContext(), "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("registo_ok")){
                setContentView(R.layout.popup_inserir);
            }else{
                Toast.makeText(getApplicationContext(), "Algo correu mal", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setView (View view){
        PickerDialogs.PickerDialogs1 dialog = new PickerDialogs.PickerDialogs1();
        dialog.show(getFragmentManager(),"date_picker");
    }

    public void setView2 (View view){
        PickerDialogs.PickerDialogs2 dialog2 = new PickerDialogs.PickerDialogs2();
        dialog2.show(getFragmentManager(),"date_picker");
    }
    public void next(View view){
        Intent intent = new Intent(InserirActivity.this, MainActivity.class);
        intent.putExtra("id_user", idutilizador);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem m1= menu.add(0, 0, 0, "Pedidos Ausência");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m2 = menu.add(0, 1, 1, "Minhas Ausência");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case 0:
                Intent intent = new Intent(this, PedidosActivity.class);
                intent.putExtra("id_user", idutilizador);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(this, MinhasAusenciasActivity.class);
                intent2.putExtra("id_user", idutilizador);
                startActivity(intent2);
                break;

        }
        return(true);
    }



}