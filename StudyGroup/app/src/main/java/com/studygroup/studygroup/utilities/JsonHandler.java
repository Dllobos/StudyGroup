package com.studygroup.studygroup.utilities;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author: Jefferson Morales De la Parra
 * Clase que se utiliza para manipular objetos JSON
 */
public class JsonHandler {

    /**
     * Método que recibe un JSONArray en forma de String y devuelve un String[] con los actores
     */
    public String[] getRamos(String ramos) {
        try {
            JSONArray ja = new JSONArray(ramos);
            String[] result = new String[ja.length()];
            String ramo;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                ramo = " " + row.getString("ramoId")+ " " + row.getString("nombreRamo")+ " "+row.getString("carreraId")+" "+row.getString("nombreCarrera")+" ";
                result[i] = ramo;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }// getActors(String actors)
    public String[] getCarreras(String carreras) {
        try {
            JSONArray ja = new JSONArray(carreras);
            String[] result = new String[ja.length()];
            String carrera;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                carrera ="\t"+row.getString("carreraId")+"\t"+row.getString("nombreCarrera")+
                        "\n\t" + row.getString("ramoId")+ "\t" + row.getString("nombreRamo");
                result[i] = carrera;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }

}// JsonHandler