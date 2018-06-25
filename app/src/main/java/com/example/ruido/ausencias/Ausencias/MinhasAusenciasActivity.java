package com.example.ruido.ausencias.Ausencias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ruido.ausencias.Inserir.InserirActivity;
import com.example.ruido.ausencias.Pedidos.PedidosActivity;
import com.example.ruido.ausencias.R;



// Class responsavel pela leitura do json e descodificação e iniciação do layout
public class MinhasAusenciasActivity extends AppCompatActivity {
    RecyclerView rv;
    String idutilizador, nivelacesso, primeironome, ultimonome;
    Handler handler = new Handler();
    private Context context;
    private AusenciasController ausencias = new AusenciasController();

    // Quando cria a activity faz estes processos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhasausencias);
        context = this;
        idutilizador = getIntent().getExtras().getString("id_user");
        nivelacesso = getIntent().getExtras().getString("acesslevel");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");
        ausencias.ausencia(idutilizador, nivelacesso, primeironome, ultimonome, context);

        rv = findViewById(R.id.listaausencias);
        rv.setLayoutManager(new GridLayoutManager(context, 1));
        rv.setHasFixedSize(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AusenciaAdapter adapter = new AusenciaAdapter(context, ausencias.getName(), ausencias.getReason(), ausencias.getStartdate(), ausencias.getFinishdate(), ausencias.getState());
                rv.setAdapter(adapter);
            }
        };
        handler.postDelayed(runnable, 500);


    }


    //Cria o menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem m1 = menu.add(0, 0, 0, "Pedidos Ausência");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m2 = menu.add(0, 1, 1, "Inserir Ausências");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return (true);
    }

    //Ao seleccionar uma opção da menu abre uma activity
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
                Intent intent2 = new Intent(this, InserirActivity.class);
                intent2.putExtra("id_user", idutilizador);
                intent2.putExtra("acesslevel", nivelacesso);
                intent2.putExtra("firstname", primeironome);
                intent2.putExtra("lastname", ultimonome);
                startActivity(intent2);
                finish();
                break;

        }
        return (true);
    }

}

