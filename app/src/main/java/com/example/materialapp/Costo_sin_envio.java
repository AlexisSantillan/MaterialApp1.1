package com.example.materialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Costo_sin_envio extends AppCompatActivity {

    public EditText costo_senv;
    public EditText cant_senv;
    public TextView costo_total_senv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costo_sin_envio);

        costo_senv=(EditText)findViewById(R.id.ed_cos_mat_senv);
        cant_senv=(EditText)findViewById(R.id.ed_cant_mat_senv);
        costo_total_senv=(TextView)findViewById(R.id.tv_total_senv);

    }

    public void calcular_senv(View view){
        String costo_senv_String = costo_senv.getText().toString();
        String cant_senv_String = cant_senv.getText().toString();

        if(costo_senv_String.isEmpty() || cant_senv_String.isEmpty()){
            Toast.makeText(this, "Por favor ingrese los valores en todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }else{

        int costo_senv_int = Integer.parseInt(costo_senv_String);
        int cant_senv_int = Integer.parseInt(cant_senv_String);

        int total = costo_senv_int * cant_senv_int;

        costo_total_senv.setText("El costo total es: " + total);
        }
    }



}