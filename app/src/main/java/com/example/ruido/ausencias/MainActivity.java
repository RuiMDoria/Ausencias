package com.example.ruido.ausencias;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//Class dedicada ao menu principal que faz com que os botões abram novos layouts
public class MainActivity extends AppCompatActivity {

    Button pedidos;
    Button inserir;
    Button minhas;
    String idutilizador;
    String nivelacesso;
    String primeironome;
    String ultimonome;
    private MainController menu = new MainController();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        pedidos = findViewById(R.id.botaopedidos);
        inserir = findViewById(R.id.inserirAusencia);
        minhas = findViewById(R.id.minhasAusencias);
        idutilizador = getIntent().getExtras().getString("id_user");
        nivelacesso = getIntent().getExtras().getString("acesslevel");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");

        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.pedidos(idutilizador, nivelacesso, primeironome, ultimonome, context);
            }
        });
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.inserir(idutilizador, nivelacesso, primeironome, ultimonome, context);
            }
        });
        minhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.minhasausencias(idutilizador, nivelacesso, primeironome, ultimonome, context);
            }
        });
    }
}



