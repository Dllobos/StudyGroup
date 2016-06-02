package com.studygroup.studygroup;

/**
 * Created by Daniel Lobos
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    //Usuario usuario;
    // variables que se van a usar a lo largo de la actividad
    Button buttonCrear;
    EditText editTextNombre,editTextApellidos,editTextMail,editTextPass;
    PostUsuarioRest postUsuarioRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //asignar a cada variable la parte del layout
        editTextNombre=(EditText)findViewById(R.id.editTextNombre);
        editTextApellidos=(EditText)findViewById(R.id.editTextApellidos);
        editTextMail=(EditText)findViewById(R.id.editTextMail);
        editTextPass=(EditText)findViewById(R.id.editTextPass);

        buttonCrear=(Button)findViewById(R.id.buttonCrear);

        //variables que van a ser Clickeadas

        buttonCrear.setOnClickListener(this);

        //usuario=((Usuario)getApplicationContext());

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonCrear://botonCrear, cuando ya se han ingresado los datos
                    postUsuarioRest= new PostUsuarioRest();
                    postUsuarioRest.execute(
                            editTextNombre.getText().toString(),
                            editTextApellidos.getText().toString(),
                            editTextMail.getText().toString(),
                            editTextPass.getText().toString());
                    //if(usuario.getVerificadores().equals("true")){
                        //}
                break;
        }
    }

    public class sacarDelJson{

    }
    public class PostUsuarioRest extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String verificar ="";//variable que entrega un veficador dependiendo de la respuesta del REST
            try {
                //abrir la coneccion a la URL
                HttpURLConnection urlConn;
                URL url = new URL(Direcciones.UrlUsuarios);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/json");
                urlConn.connect();
                //Creo json para añadir los datos
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nombre", params[0]);
                jsonParam.put("apellidos", params[1]);
                jsonParam.put("mail", params[2]);
                jsonParam.put("pass", params[3]);
                //json para ir al segundo tipo de elemento
                JSONObject jsonCarrera = new JSONObject();
                jsonCarrera.put("carreraId","10");//prueba por ahora
                jsonParam.put("carrera",jsonCarrera);
                // envio para el post
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(jsonParam.toString());
                writer.flush();
                writer.close();// se termina la escritura

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
                    //Accedemos al vector de resultados

                    String resultadoJSON = respuestaJSON.getString("usuarioAgregado");

                    if (resultadoJSON.equals("true")) {//respuesta positiva
                        //devuelve = "Usuario agregado correctamente";
                        verificar="true";
                        //MyAppApplication mApp = ((MyAppApplication)getApplicationContext());
                        //usuario.setVerificadores(verificar);

                    } else if (resultadoJSON.equals("false")) {//respuesta negativa
                        //devuelve = "El usuario ya existe";
                        verificar="false";
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return verificar;
        }
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
        @Override
        protected void onPostExecute(String s) {
            //usuario.setVerificadores(s);
            if(s.equals("true")){
            Intent principal = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(principal);}
            else{
                Toast.makeText(getBaseContext(),"No se pudo crear la cuenta",Toast.LENGTH_LONG).show();
            }
            //super.onPostExecute(s);
        }
    }
    private boolean esVacio(String variable){
        return variable.trim().length()==0;
    }

    //creo la clase interfaz para sacar el dato del asyncTask
    public interface AsyncResponse{
        void processFinish(String output);
    }
}
