package com.studygroup.studygroup;

/**
 * Created by Daniel Lobos
 */

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentBuscarUsuarios.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentBuscarUsuarios extends Fragment {

    GetPreviosRest getPreviosRest;
    TextView respuesta;

    private OnFragmentInteractionListener mListener;

    public FragmentBuscarUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_buscar_grupo, container, false);
        getPreviosRest=new GetPreviosRest();
        getPreviosRest.execute("lectura");
        respuesta =(TextView)view.findViewById(R.id.textView3);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class GetPreviosRest extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String devuelve = "";
            try {
                URL url = new URL("http://mongostudygroup-app4tbd.rhcloud.com/servicios/gestion_relacion_usuarios/encuentros_previos/13");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }


                    JSONObject respuestaJSON = new JSONObject(result.toString());


                    JSONArray usuario = respuestaJSON.getJSONArray("usuario");
                    for (int i = 0; i < usuario.length(); i++) {
                        devuelve = devuelve + usuario.getJSONObject(i).getString("usuarioId") + " " +
                                usuario.getJSONObject(i).getString("mail") + " " +
                                usuario.getJSONObject(i).getString("nombre") +" " +
                                usuario.getJSONObject(i).getString("apellidos") +" " +
                                usuario.getJSONObject(i).getString("numeroMovil") +"\n";

                        }


                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return devuelve;
        }
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onPostExecute(String s) {
            respuesta.setText(s);
            //super.onPostExecute(s);
        }
    }
}
