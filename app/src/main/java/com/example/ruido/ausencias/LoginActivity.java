package com.example.ruido.ausencias;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


//Class responsavel para enviar os dados de autenticação para o servidor e receber uma resposta, em caso de login_ok avança.
public class LoginActivity extends AppCompatActivity {

    Button btlogin;
    private EditText textemail, textpassword;
    private LoginController login = new LoginController();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        textemail = findViewById(R.id.email);
        textpassword = findViewById(R.id.password);
        btlogin = findViewById(R.id.login);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        btlogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String email = textemail.getText().toString();
                email = email.replace(" ", "");
                String pass = textpassword.getText().toString();
                login.doGet(email, pass, context, networkInfo);
            }
        });
    }

}
