package com.example.materialapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MaquinasActivity extends AppCompatActivity {

    public static final String EXTRA_LAWYER_ID = "extra_maquina_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquinas);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MaquinasFragment fragment = (MaquinasFragment)
                getSupportFragmentManager().findFragmentById(R.id.maquinas_container);

        if (fragment == null) {
            fragment = MaquinasFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.maquinas_container, fragment)
                    .commit();
        }
    }
}