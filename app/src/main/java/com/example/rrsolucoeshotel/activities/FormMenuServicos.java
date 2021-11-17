package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.BDHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class FormMenuServicos extends AppCompatActivity {

    private TextView edBVindo;
    private Button btSair;

    boolean botaoVoltarClicadoDuasVezes;
    private String emailHospede;
    private static final String TXT_BVINDO = "Bem vindo, ";
    private static final String MSG_VOLTAR = "Toque novamente para voltar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu_servicos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();

        IniciarComponentes();
        adicionarNomeHospede();

        ClicarSairFormMenuServicos();
    }

    @Override
    public void onBackPressed() {
        if (botaoVoltarClicadoDuasVezes) {
            super.onBackPressed();
            return;
        }

        ConfiguraClicarDuasVezes();
        VerificaCliqueDuplo();
    }

    private void VerificaCliqueDuplo() {
        new Handler().postDelayed(() -> botaoVoltarClicadoDuasVezes = false, 2000);
    }

    private void ConfiguraClicarDuasVezes() {
        ConstraintLayout layout = findViewById(R.id.ConstraintLayout_login);

        this.botaoVoltarClicadoDuasVezes = true;
        SnackbarMsg(layout);
    }

    private void ClicarSairFormMenuServicos() {
        btSair.setOnClickListener(view -> {
            CriaCaixaDialogo();
        });
    }

    private void CriaCaixaDialogo() {
        AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(this);
        caixaDialogo.setTitle("Alerta!");
        caixaDialogo.setIcon(R.drawable.ic_feedback);
        caixaDialogo.setMessage("Tem certeza que deseja voltar para a tela de Login?");
        caixaDialogo.setPositiveButton("Sim", (dialogInterface, i) -> {
            Log.w("caixaDialogo", "Clicou sim");
            Deslogar();
        });
        caixaDialogo.setNegativeButton("Não", (dialogInterface, i) ->
                Log.w("caixaDialogo", "Clicou não"));
        caixaDialogo.show();
    }

    private void Deslogar() {
        Intent voltarLogin = new Intent(getApplicationContext(), FormLogin.class);
        finish();
        startActivity(voltarLogin);
    }

    private void IniciarComponentes() {
        edBVindo = findViewById(R.id.txtBemVindo);
        btSair = findViewById(R.id.btnSairMenu);
        emailHospede = getIntent().getStringExtra("emailUsado");
        botaoVoltarClicadoDuasVezes = false;
    }

    private void adicionarNomeHospede() {
        BDHelper bancoDados = new BDHelper();

        edBVindo.setText(TXT_BVINDO + bancoDados.RetornarNomeCliente(emailHospede));
    }

    private void SnackbarMsg(View view) {
        Snackbar barraMsg = Snackbar.make(view, MSG_VOLTAR, Snackbar.LENGTH_SHORT);
        barraMsg.setBackgroundTint(Color.WHITE);
        barraMsg.setTextColor(Color.BLACK);
        barraMsg.show();
    }
}