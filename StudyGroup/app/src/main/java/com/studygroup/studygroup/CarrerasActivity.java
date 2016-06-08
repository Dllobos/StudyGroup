package com.studygroup.studygroup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.studygroup.studygroup.views.ItemList;
import com.studygroup.studygroup.R;
import com.studygroup.studygroup.views.NewItem;

public class CarrerasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreras);
        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();
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

    /**@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/

}
