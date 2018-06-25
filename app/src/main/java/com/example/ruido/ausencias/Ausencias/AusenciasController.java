package com.example.ruido.ausencias.Ausencias;

import android.content.Context;

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

/**
 * Created by ruido on 25/06/2018.
 */

public class AusenciasController {

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    private Context context;
    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Reason = new ArrayList<String>();
    private ArrayList<String> State = new ArrayList<String>();
    private ArrayList<String> Startdate = new ArrayList<String>();
    private ArrayList<String> Finishdate = new ArrayList<String>();
    private ArrayList<String> IDuser = new ArrayList<String>();
    private ArrayList<String> Acess = new ArrayList<String>();
    private ArrayList<String> First = new ArrayList<String>();
    private ArrayList<String> Last = new ArrayList<String>();

    public void ausencia(final String idutilizador, final String nivelacesso, final String primeironome, final String ultimonome, Context context) {
        Request request = new Request.Builder()
                .url("http://visualthinking.ddns.net:81/Ausencia/listarausencias.php?acesslevel=" + nivelacesso + "&id_user=" + idutilizador)
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
                        String nome = obj.getString("name");
                        String motivo = obj.getString("reason");
                        String datainicio = obj.getString("startdate");
                        String datafim = obj.getString("finishdate");
                        String estado = obj.getString("state");
                        Name.add(nome);
                        Reason.add(motivo);
                        Startdate.add(datainicio);
                        Finishdate.add(datafim);
                        IDuser.add(idutilizador);
                        Acess.add(nivelacesso);
                        First.add(primeironome);
                        Last.add(ultimonome);
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

    }

    public ArrayList<String> getName() {
        return this.Name;
    }

    public ArrayList<String> getReason() {
        return this.Reason;
    }

    public ArrayList<String> getStartdate() {
        return this.Startdate;
    }

    public ArrayList<String> getFinishdate() {
        return this.Finishdate;
    }

    public ArrayList<String> getState() {
        return this.State;
    }
}
