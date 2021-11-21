package com.example.rrsolucoeshotel.activities;

import static com.example.rrsolucoeshotel.activities.ConstantesActivities.MSG_VOLTAR;

import androidx.activity.result.contract.ActivityResultContracts;
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
import com.example.rrsolucoeshotel.model.BDQuery;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class FormMenuServicos extends AppCompatActivity {

    private TextView txBVindo;
    private Button btSair, btGMenu;

    boolean botaoVoltarClicadoDuasVezes;
    private String nomeHospede;
    private String quartoHospede;
    private String emailHospede;
    private String senhaHospede;

    private final static String TXT_BVINDO = "Bem vindo, ";

    private Button botaoRestaurante;
    private Button botaoPiscina;
    private Button botaoSalaoFestas;
    private Button botaoSalaoJogos;
    private Button botaoSPA;
    private Button botaoTurismo;
    private Button botaoConsultarGastos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu_servicos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();

        IniciarComponentes();
        ClicarSairFormMenuServicos();
        ClicarGastos();

        botaoRestaurante = findViewById(R.id.btnRestauranteMenu);
        botaoRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormMenuServicos.this, RestauranteActivity.class);
                startActivity(intent);
            }

        });

        botaoPiscina = findViewById(R.id.btnPiscinaMenu);
        botaoPiscina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormMenuServicos.this, PiscinaActivity.class);
                startActivity(intent);
            }
        });

        botaoSalaoFestas = findViewById(R.id.btnSalaoFMenu);
        botaoSalaoFestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormMenuServicos.this, SalaodeFestasActivity.class);
                startActivity(intent);
            }
        });


        botaoSalaoJogos = findViewById(R.id.btnSalaoJMenu);
        botaoSalaoJogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormMenuServicos.this, SalaoJogosActivity.class);
                startActivity(intent);
            }

        });

        botaoSPA = findViewById(R.id.btnSpaMenu);
        botaoSPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormMenuServicos.this, SPAActivity.class);
                startActivity(intent);
            }

        });

        botaoTurismo = findViewById(R.id.btnTurismoMenu);
        botaoTurismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormMenuServicos.this, TurismoActivity.class);
                startActivity(intent);
            }

        });






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
        ConstraintLayout layout = findViewById(R.id.ConstraintLayout_menu);

        this.botaoVoltarClicadoDuasVezes = true;
        SnackbarMsg(layout);
    }

    private void ClicarSairFormMenuServicos() {
        btSair.setOnClickListener(view -> CriaCaixaDialogo());
    }

    private void ClicarGastos() {
        btGMenu.setOnClickListener(view -> {
            IrFormGastos();
        });
    }

    private void IrFormGastos() {
        Intent irGMenu = new Intent(getApplicationContext(),
                FormGastos.class);
        irGMenu.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
        irGMenu.putExtra("cpfHospede", senhaHospede);
        irGMenu.putExtra("quartoHospede", quartoHospede);
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
        BDQuery bancoDados = new BDQuery();
        emailHospede = getIntent().getStringExtra("emailUsado");
        senhaHospede = getIntent().getStringExtra("senhaUsado");
        nomeHospede = bancoDados.RetornarNomeHospede(emailHospede, senhaHospede);
        quartoHospede = bancoDados.RetornarQuartoHospede(nomeHospede, senhaHospede);

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