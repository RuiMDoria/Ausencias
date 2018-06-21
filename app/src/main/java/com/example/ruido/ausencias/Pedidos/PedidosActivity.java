package com.example.ruido.ausencias.Pedidos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ruido.ausencias.Inserir.InserirActivity;
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


public class PedidosActivity extends AppCompatActivity {
    RecyclerView rv;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    String idutilizador;
    String nivelacesso;
    String primeironome;
    String ultimonome;
    private Context context;
    private boolean boolres = false;
    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Reason = new ArrayList<String>();
    private ArrayList<String> State = new ArrayList<String>();
    private ArrayList<String> Startdate = new ArrayList<String>();
    private ArrayList<String> Finishdate = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        context = this;
        idutilizador = getIntent().getExtras().getString("id_user");
        nivelacesso = getIntent().getExtras().getString("acesslevel");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");
        Request request = new Request.Builder()
                .url("http://192.168.2.252:81/Ausencia/listarpedidos.php?acesslevel=" + nivelacesso + "&id_user=" + idutilizador)
                .build();
        Log.i("info", "request built: Confirmed");
        okhttp3.Call myCall = okHttpClient.newCall(request);
        myCall.enqueue(new Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(okhttp3.Call call, final Response response) throws IOException {
                Log.i("info", "request got response: response");
                final String myResponse = response.body().string();
                try {
                    JSONArray jObj = new JSONArray(myResponse);
                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject obj = jObj.getJSONObject(i);
                        String nome = obj.getString("name");
                        String motivo = obj.getString("reason");
                        String datainicio = obj.getString("startdate");
                        String datafim = obj.getString("finishdate");
                        String estado = obj.getString("state");
                        Name.add(nome);
                        Reason.add(motivo);
                        Startdate.add(datainicio);
                        Finishdate.add(datafim);
                        if (estado.contains("0")) {
                            String state = "Em Aprovação";
                            State.add(state);
                        }

                    }
                    boolres = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        rv = findViewById(R.id.listapedidos);
        rv.setLayoutManager(new GridLayoutManager(context, 1));
        rv.setHasFixedSize(true);

        PedidosAdapter adapter = new PedidosAdapter(context, Name, Reason, Startdate, Finishdate, State);

            rv.setAdapter(adapter);


    }

    public void inserir(View view) {

        Intent intent = new Intent(this, InserirActivity.class);
        intent.putExtra("id_user", idutilizador);
        intent.putExtra("firstname", primeironome);
        intent.putExtra("lastname", ultimonome);
        startActivity(intent);
    }
}

