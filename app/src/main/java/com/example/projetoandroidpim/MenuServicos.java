package com.example.projetoandroidpim;

import static com.example.projetoandroidpim.R.layout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MenuServicos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_menu_servicos);

        //esconde barra de ação
        getSupportActionBar().hide();
    }
}