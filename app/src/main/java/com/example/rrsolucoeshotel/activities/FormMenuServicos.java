package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.BDHelper;

import java.util.Objects;

public class FormMenuServicos extends AppCompatActivity {

    TextView edBVindo;
    String emailHospede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu_servicos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();

        IniciarComponentes();
        adicionarNomeHospede();
    }

    private void IniciarComponentes() {
        edBVindo = findViewById(R.id.txtBemVindo);
        emailHospede = getIntent().getStringExtra("emailUsado");
    }

    private void adicionarNomeHospede() {
        BDHelper bancoDados = new BDHelper();

        edBVindo.setText("Bem vindo, " + bancoDados.RetornarNomeCliente(emailHospede));
    }
}