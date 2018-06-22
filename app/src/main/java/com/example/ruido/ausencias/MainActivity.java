package com.example.ruido.ausencias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ruido.ausencias.Ausencias.MinhasAusenciasActivity;
import com.example.ruido.ausencias.Inserir.InserirActivity;
import com.example.ruido.ausencias.Pedidos.PedidosActivity;


public class MainActivity extends AppCompatActivity {

    String idutilizador;
    String nivelacesso;
    String primeironome;
    String ultimonome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idutilizador = getIntent().getExtras().getString("id_user");
        nivelacesso = getIntent().getExtras().getString("acesslevel");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");

    }
        public void pedidos(View view) {

            Intent intent = new Intent(this, PedidosActivity.class);
            intent.putExtra("id_user", idutilizador);
            intent.putExtra("acesslevel", nivelacesso);
            intent.putExtra("firstname", primeironome);
            intent.putExtra("lastname", ultimonome);
            startActivity(intent);
        }

        public void inserir(View view) {

            Intent intent = new Intent(this, InserirActivity.class);
            intent.putExtra("id_user", idutilizador);
            intent.putExtra("acesslevel", nivelacesso);
            intent.putExtra("firstname",primeironome);
            intent.putExtra("lastname", ultimonome);
            startActivity(intent);
    }
        public void minhasausencias(View view) {

            Intent intent = new Intent(this, MinhasAusenciasActivity.class);
            intent.putExtra("id_user", idutilizador);
            intent.putExtra("acesslevel", nivelacesso);
            intent.putExtra("firstname", primeironome);
            intent.putExtra("lastname", ultimonome);
            startActivity(intent);
        }

}



