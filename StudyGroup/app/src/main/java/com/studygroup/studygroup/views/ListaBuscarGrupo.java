package com.studygroup.studygroup.views;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.studygroup.studygroup.MainActivity;
import com.studygroup.studygroup.MapsActivity;
import com.studygroup.studygroup.R;
import com.studygroup.studygroup.Usuario;
import com.studygroup.studygroup.controllers.HttpGet;
import com.studygroup.studygroup.utilities.JsonHandler;
import com.studygroup.studygroup.utilities.SystemUtilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaBuscarGrupo extends ListFragment {

    private BroadcastReceiver br = null;

    private final String URL_GET = "http://mongostudygroup-app4tbd.rhcloud.com/servicios/usuarios/ramos_a_elegir/";



    public ListaBuscarGrupo() {
        // Required empty public constructor
    }


    /**
     * Método que se llama una vez que se ha creado la actividad que contiene al fragmento
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }// onActivityCreated(Bundle savedInstanceState)

    /**
     * Método que escucha las pulsaciones en los items de la lista
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent principal = new Intent(getActivity().getApplicationContext(),MapsActivity.class);
        startActivity(principal);
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


}
