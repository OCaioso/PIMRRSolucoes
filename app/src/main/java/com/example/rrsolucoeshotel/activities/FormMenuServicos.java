package com.example.rrsolucoeshotel.activities;

import static com.example.rrsolucoeshotel.activities.ConstantesActivities.MSG_VOLTAR;

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
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class FormMenuServicos extends AppCompatActivity {

    private TextView txBVindo;
    private Button btSair, btGMenu;

    boolean botaoVoltarClicadoDuasVezes;
    private String nomeHospede;
    private String emailHospede;
    private String senhaHospede;

    private final static String TXT_BVINDO = "Bem vindo, ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu_servicos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();


        IniciarComponentes();

        ClicarSairFormMenuServicos();
        ClicarGastos();
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
        ConstraintLayout view = findViewById(R.id.ConstraintLayout_login);

        this.botaoVoltarClicadoDuasVezes = true;
        SnackbarMsg(view);
    }

    private void VerificaCliqueDuplo() {
        new Handler().postDelayed(() -> botaoVoltarClicadoDuasVezes = false, 2000);
    }

    private void ClicarSairFormMenuServicos() {
        btSair.setOnClickListener(view -> CriaCaixaDialogo());
    }

    private void ClicarGastos() {
        btGMenu.setOnClickListener(view -> {
            IrFormGastos(nomeHospede, senhaHospede);
        });
    }

    private void IrFormGastos(String nome, String cpf) {
        Intent irGMenu = new Intent(getApplicationContext(),
                FormGastos.class);
        irGMenu.putExtra("nomeHospede", nome); //passando os dados do email para próxima atcivity
        irGMenu.putExtra("cpfHospede", cpf);
        //finish();
        startActivity(irGMenu);
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
        emailHospede = getIntent().getStringExtra("emailUsado");
        senhaHospede = getIntent().getStringExtra("senhaUsado");
        nomeHospede = bancoDados.RetornarNomeHospede(emailHospede, senhaHospede);

        txBVindo = findViewById(R.id.txtBemVindo);
        btSair = findViewById(R.id.btnSairMenu);
        btGMenu = findViewById(R.id.btnConsultaGMenu);
        botaoVoltarClicadoDuasVezes = false;

        AdicionarNomeHospede();
    }

    private void AdicionarNomeHospede() {
        txBVindo.setText(TXT_BVINDO + nomeHospede);
    }

    private void SnackbarMsg(View view) {
        Snackbar barraMsg = Snackbar.make(view, MSG_VOLTAR, Snackbar.LENGTH_SHORT);
        barraMsg.setBackgroundTint(Color.WHITE);
        barraMsg.setTextColor(Color.BLACK);
        barraMsg.show();
    }
}