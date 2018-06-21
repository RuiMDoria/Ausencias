package com.example.ruido.ausencias.Pedidos;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

import com.example.ruido.ausencias.Ausencias.MinhasAusenciasActivity;
import com.example.ruido.ausencias.Inserir.InserirActivity;
import com.example.ruido.ausencias.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class PedidosActivity extends ListActivity {
    private ListView lvPedido;
    private PedidosAdapter adapter;
    private ArrayList<Pedido> mPedidoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DownloadJsonAsyncTask().execute("http://10.0.5.180:8080/samples/mock_pessoas");
        setContentView(R.layout.activity_pedidos);



        lvPedido = (ListView)findViewById(R.id.listapedidos);

        mPedidoList = new ArrayList<>();
        mPedidoList.add(new Pedido(1, "iPhone4", 200, "Apple iPhone4 16GB"));
        mPedidoList.add(new Pedido(3, "iPhone4S", 250, "Apple iPhone4S 16GB"));
        mPedidoList.add(new Pedido(4, "iPhone5", 300, "Apple iPhone5 16GB"));
        mPedidoList.add(new Pedido(5, "iPhone5S", 350, "Apple iPhone5S 16GB"));
        mPedidoList.add(new Pedido(6, "iPhone6", 400, "Apple iPhone6 16GB"));
        mPedidoList.add(new Pedido(7, "iPhone6S", 450, "Apple iPhone6S 16GB"));
        mPedidoList.add(new Pedido(8, "iPhone7", 500, "Apple iPhone7 16GB"));
        mPedidoList.add(new Pedido(9, "iPhone7S", 600, "Apple iPhon7S 16GB"));
        mPedidoList.add(new Pedido(10, "iPhone8", 700, "Apple iPhone8 16GB"));
        mPedidoList.add(new Pedido(11, "iPhone8S", 800, "Apple iPhone8S 16GB"));

        //Init adapter
        adapter = new PedidosAdapter((Context) getApplicationContext(), mPedidoList);
        lvPedido.setAdapter(adapter);


        lvPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                setContentView(R.layout.activity_aprovacaopedido);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem m1= menu.add(0, 0, 0, "Inserir Ausência");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m2 = menu.add(0, 1, 1, "Minhas Ausências");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case 0:
                Intent intent = new Intent(this, InserirActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(this, MinhasAusenciasActivity.class);
                startActivity(intent2);
                break;
        }
        return(true);
    }

    public void inserir(View view) {

        Intent intent = new Intent(this, InserirActivity.class);
        startActivity(intent);
    }
}

