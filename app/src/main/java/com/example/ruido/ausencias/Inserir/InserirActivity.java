package com.example.ruido.ausencias.Inserir;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ruido.ausencias.Ausencias.MinhasAusenciasActivity;
import com.example.ruido.ausencias.Pedidos.PedidosActivity;
import com.example.ruido.ausencias.R;

import java.net.URLEncoder;

//Class para inserir ausencias na base de dados
public class InserirActivity extends AppCompatActivity {
    public static Button DateStart;
    public static Button DateFinish;
    Spinner spinnerMotivo;
    Spinner spinnerHoras;
    EditText textoObservacoes;
    Button Enviar;
    String idutilizador, primeironome, ultimonome, nivelacesso, nome;
    private InserirController inserir = new InserirController();
    private Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
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
        nivelacesso = getIntent().getExtras().getString("acesslevel");
        nome = primeironome + " " + ultimonome;

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String dateStart = DateStart.getText().toString();
                String dateFinish = DateFinish.getText().toString();
                String motivo = spinnerMotivo.getSelectedItem().toString();
                motivo = URLEncoder.encode(motivo);
                String horas = spinnerHoras.getSelectedItem().toString();
                String observacoes = textoObservacoes.getText().toString();
                nome = URLEncoder.encode(nome);
                observacoes = URLEncoder.encode(observacoes);
                inserir.Enviar(dateStart, dateFinish, motivo, horas, observacoes, nome, idutilizador, primeironome, ultimonome, nivelacesso, context, networkInfo);
            }
        });
    }



    public void setView (View view){
        PickerDialogs.PickerDialogs1 dialog = new PickerDialogs.PickerDialogs1();
        dialog.show(getFragmentManager(),"date_picker");
    }

    public void setView2 (View view){
        PickerDialogs.PickerDialogs2 dialog2 = new PickerDialogs.PickerDialogs2();
        dialog2.show(getFragmentManager(),"date_picker");
    }

    //Cria menu supeior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem m1= menu.add(0, 0, 0, "Pedidos Ausência");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m2 = menu.add(0, 1, 1, "Todas Ausências");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return (true);
    }

    //opções do menu superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

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

        }
        return(true);
    }

}