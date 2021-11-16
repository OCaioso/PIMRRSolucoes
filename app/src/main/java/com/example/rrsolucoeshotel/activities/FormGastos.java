package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rrsolucoeshotel.R;

import java.util.Objects;

public class FormGastos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_gastos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}