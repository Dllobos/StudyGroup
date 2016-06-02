package com.studygroup.studygroup;

/**
 * Created by Daniel Lobos
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //Usuario usuario;

    //variables a usar a lo largo de la actividad
    Button bIrRegistro,bIngresar;
    EditText editTextMail, editTextPass;

    LoginUsuarioRest loginUsuarioRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((Usuario)this.getApplication()).getVerificadores();
        //asignar cada variable a su parte del layout
        bIrRegistro=(Button)findViewById(R.id.bIrRegistro);
        bIngresar=(Button)findViewById(R.id.bIngresar);
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextPass=(EditText)findViewById(R.id.editTextPass);
        //variables que van a ser clickeadas
        bIrRegistro.setOnClickListener(this);
        bIngresar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){// metodo para ver si fue presionado o no
        switch (v.getId()){//switch para ver el tipo de boton que fue clickeado
            case R.id.bIrRegistro:
                Intent ventana = new Intent(this,RegisterActivity.class);// intent para iniciar una actividad
                startActivity(ventana);// inicia la actividad del intent
                break;
            case R.id.bIngresar:

                loginUsuarioRest= new LoginUsuarioRest();
                loginUsuarioRest.execute(
                        editTextMail.getText().toString(),
                        editTextPass.getText().toString());
                break;
        }
    }

    public class LoginUsuarioRest extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String devuelve ="";//variable que va a entregar el doOnBackground
            try {
                // abre la conexion al REST
                HttpURLConnection urlConn;
                URL url = new URL(Direcciones.UrlLoginUsuario);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/json");// tipo de conexion al REST
                urlConn.connect();//conecta
                //Creo json para añadir los datos en este caso 2
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("mail", params[0]);
                jsonParam.put("pass", params[1]);
                // envio para el post
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(jsonParam.toString());
                writer.flush();
                writer.close();//termina la escritura
                //variable para respuesta del servidor
                int respuesta = urlConn.getResponseCode();
                StringBuilder result = new StringBuilder();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        result.append(line);
                    }
                    //Creo json para acceder a la respuesta
                    JSONObject respuestaJSON = new JSONObject(result.toString());
                    //Accedoa los resultados
                    String resultadoJSON = respuestaJSON.getString("usuarioConectado");
                    String idJSON = respuestaJSON.getString("usuarioId");
                    if (resultadoJSON.equals("true")) {//respuesta positiva
                        devuelve = idJSON;
                        //Log.e()

                    } else if (resultadoJSON.equals("false")) {//respuesta negativa
                        devuelve = "-1";
                    }
                }
            } catch (MalformedURLException e) {//error de URL
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {//error al trabajar con el Json
                e.printStackTrace();
            }
            return devuelve;// devuelve una variable
        }
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }//si se cancela el hilo
        @Override
        protected void onPostExecute(String s) {//cuando se ejecuta el Post
            if(s.equals("-1")){
                Toast.makeText(getBaseContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
            }
            else{
                Intent principal = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(principal);
            }
            //super.onPostExecute(s);
        }
    }
}//LogiAtivity
