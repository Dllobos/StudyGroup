package com.studygroup.studygroup;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.studygroup.studygroup.views.ItemList;

public class CarrerasActivity extends AppCompatActivity {

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreras);
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new ItemList());
        transaction.commit();
    }// onCreate(Bundle savedInstanceState)

    /**
     * Método que se ejecuta al momento de presionar el botón regresar del teclado
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }// onBackPressed()
}
