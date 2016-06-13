package com.studygroup.studygroup.views;


import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studygroup.studygroup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCrearGrupo extends Fragment {

    private BroadcastReceiver br = null;

    private final String URL_GET = "http://mongostudygroup-app4tbd.rhcloud.com/servicios/usuarios/ramos_a_elegir/";



    public ListaCrearGrupo() {
        // Required empty public constructor
    }


    /**
     * Método que se llama una vez que se ha creado la actividad que contiene al fragmento
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }// onActivityCreated(Bundle savedInstanceState)


}
