package com.studygroup.studygroup.views;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.studygroup.studygroup.Direcciones;
import com.studygroup.studygroup.LoginActivity;
import com.studygroup.studygroup.R;
import com.studygroup.studygroup.Usuario;
import com.studygroup.studygroup.controllers.HttpGet;
import com.studygroup.studygroup.utilities.JsonHandler;
import com.studygroup.studygroup.utilities.SystemUtilities;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class ListarCrearGrupo extends ListFragment {


    private BroadcastReceiver br = null;
    private final String URL_GET = "http://mongostudygroup-app4tbd.rhcloud.com/servicios/usuarios/ramos_a_elegir/";
    PostGrupoRest postGrupoRest;

    public ListarCrearGrupo() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }// onActivityCreated(Bundle savedInstanceState)

    /**
     * Método que escucha las pulsaciones en los items de la lista
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = l.getItemAtPosition(position).toString();
        Fragment itemDetail = new ItemDetail();
        Bundle arguments = new Bundle();
        arguments.putString("item", item);
        itemDetail.setArguments(arguments);
        String variables = arguments.getString("item");
        String[] palabrasSeparadas= variables.split(" ");
        String ramo= palabrasSeparadas[1];
        String a =((Usuario)getActivity().getApplicationContext()).getUsuarioId();
        postGrupoRest= new PostGrupoRest();
        postGrupoRest.execute("1",ramo,"para estudiar");
    }// onListItemClick(ListView l, View v, int position, long id)

    /**
     * Método que se ejecuta luego que el fragmento es creado o restaurado
     */
    @Override
    public void onResume() {
        IntentFilter intentFilter = new IntentFilter("httpData");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                JsonHandler jh = new JsonHandler();
                String[] actorsList = jh.getRamos(intent.getStringExtra("data"));
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity()
                        , android.R.layout.simple_list_item_1, actorsList);
                setListAdapter(adapter);
            }
        };
        getActivity().registerReceiver(br, intentFilter);
        SystemUtilities su = new SystemUtilities(getActivity().getApplicationContext());
        try {
            if (su.isNetworkAvailable()) {
                String a =((Usuario)getActivity().getApplicationContext()).getUsuarioId();
                new HttpGet(getActivity().getApplicationContext()).execute(URL_GET+a);
            }
        }
        catch(Exception e){throw new RuntimeException(e);}
        super.onResume();
    }// onResume()
    /**
     * Método que se ejecuta luego que el fragmento se detiene
     */
    @Override
    public void onPause() {
        if (br != null) {
            getActivity().unregisterReceiver(br);
        }
        super.onPause();
    }// onPause()

    public class PostGrupoRest extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String verificar ="";//variable que entrega un veficador dependiendo de la respuesta del REST
            try {
                String a =((Usuario)getActivity().getApplicationContext()).getUsuarioId();
                //abrir la coneccion a la URL
                HttpURLConnection urlConn;
                URL url = new URL("http://mongostudygroup-app4tbd.rhcloud.com/servicios/grupos_temporales/"+a);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/json");
                urlConn.connect();
                //Creo json para añadir los datos
                int numero1,numero2;
                numero1=Integer.parseInt(params[0]);
                numero2=Integer.parseInt(params[1]);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("idLugar", numero1);
                jsonParam.put("ramoId", numero2);
                jsonParam.put("descripcionTemporal", params[2]);
                //json para ir al segundo tipo de elemento
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
                        verificar="true";
                }else{verificar ="error";}
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
            if(s.equals("true")){
                }
            else if(s.equals("error")){
                Toast.makeText(getActivity().getApplicationContext(),"Creado el grupo de estudio",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity().getApplicationContext(),"No se pudo crear",Toast.LENGTH_LONG).show();
            }
            //super.onPostExecute(s);
        }
    }

}
