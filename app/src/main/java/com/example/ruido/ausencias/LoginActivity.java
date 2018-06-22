package com.example.ruido.ausencias;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//Class responsavel para enviar os dados de autenticação para o servidor e receber uma resposta, em caso de login_ok avança.
public class LoginActivity extends AppCompatActivity {

    EditText textemail, textpassword;
    Button btlogin;
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textemail=findViewById(R.id.email);
        textpassword= findViewById(R.id.password);
        btlogin= findViewById(R.id.login);


        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){
                    String email = textemail.getText().toString();
                    email = email.replace(" ","");
                    String pass = textpassword.getText().toString();

                    if (email.isEmpty()||pass.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    }else {
                        url = "http://192.168.2.252:81/Ausencia/login.php?email=" + email + "&password=" + pass;//metodo GET
                    }
                    new SolicitaDados().execute(url);
                }else{
                    Toast.makeText(getApplicationContext(), "Nenhuma Conexão foi detetada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class SolicitaDados extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("login_ok")){
                String[] dados = resultado.split(",");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("id_user", dados[1]);
                intent.putExtra("firstname", dados[2]);
                intent.putExtra("lastname", dados[3]);
                intent.putExtra("acesslevel", dados[4]);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Email ou Password erradas", Toast.LENGTH_LONG).show();
            }
        }
    }


}
