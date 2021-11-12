package com.example.rrsolucoeshotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class FormMenuServicos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu_servicos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();
    }


}