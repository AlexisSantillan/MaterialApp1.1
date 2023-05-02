package com.example.materialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.materialapp.controlador.Material;
import com.example.materialapp.controlador.PagerControler;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("user") && password.getText().toString().equals("1234")){
                    Toast.makeText(Login.this,"Acceso exitoso",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this, PantallaPrincipal.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Verifique sus datos",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}