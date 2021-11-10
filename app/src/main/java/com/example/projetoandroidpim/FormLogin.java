package com.example.projetoandroidpim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FormLogin extends AppCompatActivity {

    private AppCompatButton botao_acessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        //esconde barra de ação
        getSupportActionBar().hide();


        botao_acessar.setOnClickListener(v -> {
            // Implementar entrada
            Intent logar = new Intent(getApplicationContext(),
                    MenuServicos.class);
            startActivity(logar);
        });
    }

    private void IniciarComponentes(){

        botao_acessar = findViewById(R.id.btnAcessarLogin);
    }



}