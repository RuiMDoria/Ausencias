package com.example.ruido.ausencias.Ausencias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ruido.ausencias.Inserir.InserirActivity;
import com.example.ruido.ausencias.Pedidos.PedidosActivity;
import com.example.ruido.ausencias.R;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Class responsavel pela leitura do json e descodificação e iniciação do layout
public class MinhasAusenciasActivity extends AppCompatActivity {
    RecyclerView rv;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    String idutilizador, nivelacesso, primeironome, ultimonome;
    private Context context;
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Reason = new ArrayList<String>();
    private ArrayList<String> State = new ArrayList<String>();
    private ArrayList<String> Startdate = new ArrayList<String>();
    private ArrayList<String> Finishdate = new ArrayList<String>();
    private ArrayList<String> Comments = new ArrayList<String>();
    private ArrayList<String> Hours = new ArrayList<String>();


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
        Request request = new Request.Builder()
                .url("http://thmc.ddns.net:81/Ausencia/listarausencias.php?acesslevel=" + nivelacesso + "&id_user=" + idutilizador)
                .build();
        okhttp3.Call myCall = okHttpClient.newCall(request);
        myCall.enqueue(new Callback() {

            //Em caso de falha a chamada é cancelada
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
            }

            //Em caso de resposta, descodifica a informação do json, insere em strings e envia envia para o adapter
            @Override
            public void onResponse(okhttp3.Call call, final Response response) throws IOException {
                final String myResponse = response.body().string();
                try {
                    JSONArray jObj = new JSONArray(myResponse);
                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject obj = jObj.getJSONObject(i);
                        String id = obj.getString("id");
                        String nome = obj.getString("name");
                        String motivo = obj.getString("reason");
                        String datainicio = obj.getString("startdate");
                        String datafim = obj.getString("finishdate");
                        String estado = obj.getString("state");
                        String comments = obj.getString("comments");
                        String hours = obj.getString("hours");
                        ID.add(id);
                        Name.add(nome);
                        Reason.add(motivo);
                        Startdate.add(datainicio);
                        Finishdate.add(datafim);
                        Comments.add(comments);
                        Hours.add(hours);
                        if (estado.contains("1")) {
                            String state = "Aprovado";
                            State.add(state);
                        } else {
                            String state = "Rejeitado";
                            State.add(state);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        rv = findViewById(R.id.listaausencias);
        rv.setLayoutManager(new GridLayoutManager(context, 1));
        rv.setHasFixedSize(true);


        AusenciaAdapter adapter = new AusenciaAdapter(context, Name, Reason, Startdate, Finishdate, State);

        rv.setAdapter(adapter);

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

