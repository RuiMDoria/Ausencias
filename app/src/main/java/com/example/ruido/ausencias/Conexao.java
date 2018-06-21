package com.example.ruido.ausencias;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ruido on 21/05/2018.
 */
//conexao  base de dado/api
public class Conexao {

    public static String postDados(String urlUtilizador){
        URL url;
        HttpURLConnection connection = null;

        try{

            url = new URL(urlUtilizador);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urllencoded");
            connection.setRequestProperty("Content-Language", "pt-PT");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter( connection.getOutputStream(), "UTF-8");
            outputStreamWriter.flush();


            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null){
               resposta.append(linha);
               resposta.append('\r');
            }

            bufferedReader.close();
            return resposta.toString();



        }catch (Exception e){

            return null;

        }finally{

            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
