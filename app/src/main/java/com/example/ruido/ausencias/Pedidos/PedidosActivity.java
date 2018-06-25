package com.example.ruido.ausencias.Pedidos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ruido.ausencias.Ausencias.MinhasAusenciasActivity;
import com.example.ruido.ausencias.Inserir.InserirActivity;
import com.example.ruido.ausencias.R;



// Class responsavel pela leitura do json e descodificação e iniciação do layout
public class PedidosActivity extends AppCompatActivity {
    RecyclerView rv;
    String idutilizador, nivelacesso, primeironome, ultimonome;
    Handler handler = new Handler();
    private Context context;
    private PedidosController pedidos = new PedidosController();

    // Quando cria a activity faz estes processos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        context = this;
        idutilizador = getIntent().getExtras().getString("id_user");
        nivelacesso = getIntent().getExtras().getString("acesslevel");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");
        pedidos.pedir(idutilizador, nivelacesso, primeironome, ultimonome, context);

        rv = findViewById(R.id.listapedidos);
        rv.setLayoutManager(new GridLayoutManager(context, 1));
        rv.setHasFixedSize(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PedidosAdapter adapter = new PedidosAdapter(context, pedidos.getID(), pedidos.getName(), pedidos.getReason(), pedidos.getStartdate(), pedidos.getFinishdate(), pedidos.getState(), pedidos.getComments(), pedidos.getHours(), pedidos.getIDuser(), pedidos.getAcess(), pedidos.getFirst(), pedidos.getLast());
                rv.setAdapter(adapter);
            }
        };
        handler.postDelayed(runnable, 500);


    }

    //Quando carrega no botão novo pedido abre activity InserirActivity
    public void inserir(View view) {

        Intent intent = new Intent(this, InserirActivity.class);
        intent.putExtra("id_user", idutilizador);
        intent.putExtra("acesslevel", nivelacesso);
        intent.putExtra("firstname", primeironome);
        intent.putExtra("lastname", ultimonome);
        startActivity(intent);
        finish();
    }


    //Cria o menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem m1 = menu.add(0, 0, 0, "Inserir Ausência");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m2 = menu.add(0, 1, 1, "Todas Ausências");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return (true);
    }

    //Ao seleccionar uma opção da menu abre uma activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case 0:
                Intent intent = new Intent(this, InserirActivity.class);
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
        return (true);
    }

}

