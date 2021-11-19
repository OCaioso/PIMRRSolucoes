package com.example.rrsolucoeshotel.activities;

import static com.example.rrsolucoeshotel.activities.ConstantesActivities.MSG_VOLTAR;
import static com.example.rrsolucoeshotel.activities.ConstantesActivities.TXT_HOSPEDE;
import static com.example.rrsolucoeshotel.activities.ConstantesActivities.TXT_QUARTOS;

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

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.BDHelper;
import com.example.rrsolucoeshotel.model.DadosHospede;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class FormGastos extends AppCompatActivity {

    private TextView txHospede, txQuarto;
    private Button btSair;

    boolean botaoVoltarClicadoDuasVezes;
    private String nomeHospede, cpfHospede, quartoHospede;

    private DadosHospede dadosHospede = new DadosHospede();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_gastos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();

        IniciarComponentes();
        SalvarDadosHospedeClasse();

        ClicarSairFormGastos();
        PegarDadosHospedeParaTela();
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

    private void ConfiguraClicarDuasVezes() {
        ConstraintLayout view = findViewById(R.id.ConstraintLayout_gastos);

        this.botaoVoltarClicadoDuasVezes = true;
        SnackbarMsg(view);
    }

    private void SnackbarMsg(View view) {
        Snackbar barraMsg = Snackbar.make(view, MSG_VOLTAR, Snackbar.LENGTH_SHORT);
        barraMsg.setBackgroundTint(Color.WHITE);
        barraMsg.setTextColor(Color.BLACK);
        barraMsg.show();
    }

    private void VerificaCliqueDuplo() {
        new Handler().postDelayed(() -> botaoVoltarClicadoDuasVezes = false, 2000);
    }

    private void ClicarSairFormGastos() {
        btSair.setOnClickListener(view -> CriaCaixaDialogo());
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
        BDHelper bancoDados = new BDHelper();

        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");
        quartoHospede = bancoDados.RetornarQuartoHospede(nomeHospede, cpfHospede);

        txHospede = findViewById(R.id.txtHospedeGasto);
        txQuarto = findViewById(R.id.txtQuartoGasto);
        btSair = findViewById(R.id.btnSairGastos);
        botaoVoltarClicadoDuasVezes = false;

        AdicionarNomeQuartoATela();
    }

    private void AdicionarNomeQuartoATela() {
        txHospede.setText(TXT_HOSPEDE + nomeHospede);
        txQuarto.setText(TXT_QUARTOS + quartoHospede);
    }

    private void SalvarDadosHospedeClasse() {
        BDHelper bancoDados = new BDHelper();

        bancoDados.SalvarGastosEmDadosHospede(dadosHospede, nomeHospede, cpfHospede);
    }

    private void PegarDadosHospedeParaTela() {

    }
}