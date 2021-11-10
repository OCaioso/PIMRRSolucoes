package com.example.projetoandroidpim;

import static com.example.projetoandroidpim.R.layout.activity_form_gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FormGastos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_form_gastos);

        //esconde barra de ação
        getSupportActionBar().hide();
    }
}