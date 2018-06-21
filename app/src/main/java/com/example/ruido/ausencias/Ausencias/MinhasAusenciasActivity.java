package com.example.ruido.ausencias.Ausencias;

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

import com.example.ruido.ausencias.Inserir.InserirActivity;
import com.example.ruido.ausencias.Pedidos.PedidosActivity;
import com.example.ruido.ausencias.R;

import java.util.ArrayList;
import java.util.List;



public class MinhasAusenciasActivity extends AppCompatActivity {
    private ListView lvAusencia;
    private AusenciasAdapter adapter;
    private List<Ausencia> mAusenciaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhasausencias);

        lvAusencia = (ListView)findViewById(R.id.listaminhasausencias);

        mAusenciaList = new ArrayList<>();
        mAusenciaList.add(new Ausencia(1, "iPhone4", 200, "Apple iPhone4 16GB"));
        mAusenciaList.add(new Ausencia(3, "iPhone4S", 250, "Apple iPhone4S 16GB"));
        mAusenciaList.add(new Ausencia(4, "iPhone5", 300, "Apple iPhone5 16GB"));
        mAusenciaList.add(new Ausencia(5, "iPhone5S", 350, "Apple iPhone5S 16GB"));
        mAusenciaList.add(new Ausencia(6, "iPhone6", 400, "Apple iPhone6 16GB"));
        mAusenciaList.add(new Ausencia(7, "iPhone6S", 450, "Apple iPhone6S 16GB"));
        mAusenciaList.add(new Ausencia(8, "iPhone7", 500, "Apple iPhone7 16GB"));
        mAusenciaList.add(new Ausencia(9, "iPhone7S", 600, "Apple iPhon7S 16GB"));
        mAusenciaList.add(new Ausencia(10, "iPhone8", 700, "Apple iPhone8 16GB"));
        mAusenciaList.add(new Ausencia(11, "iPhone8S", 800, "Apple iPhone8S 16GB"));

        //Init adapter
        adapter = new AusenciasAdapter((Context) getApplicationContext(), mAusenciaList);
        lvAusencia.setAdapter(adapter);

        lvAusencia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                Toast.makeText(getApplicationContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem m1= menu.add(0, 0, 0, "Pedidos Ausência");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem m2 = menu.add(0, 1, 1, "Inserir Ausência");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case 0:
                Intent intent = new Intent(this, PedidosActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(this, InserirActivity.class);
                startActivity(intent2);
                break;

        }
        return(true);
    }

}
