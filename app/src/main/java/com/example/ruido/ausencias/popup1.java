package com.example.ruido.ausencias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by ruido on 22/06/2018.
 */
//Class do popup depois de inserir uma ausencia
public class popup1 extends AppCompatActivity {
    String idutilizador, nivelacesso, primeironome, ultimonome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_inserir1);
        idutilizador = getIntent().getExtras().getString("id_user");
        primeironome = getIntent().getExtras().getString("firstname");
        ultimonome = getIntent().getExtras().getString("lastname");
        nivelacesso = getIntent().getExtras().getString("acesslevel");
    }

    public void next(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id_user", idutilizador);
        intent.putExtra("acesslevel", nivelacesso);
        intent.putExtra("firstname", primeironome);
        intent.putExtra("lastname", ultimonome);
        startActivity(intent);
        finish();
    }
}
