package com.example.rrsolucoeshotel.activities;

import static com.example.rrsolucoeshotel.activities.ConstantesActivities.MENSAGENS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.BDQuery;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MenuServicosActivity extends AppCompatActivity {

    private TextView txtBVindo;
    private Button btnGastos, btnRestaurante, btnPiscina, btnSalaoFestas, btnSalaoJogos,
            btnSPA, btnTurismo;

    boolean botaoVoltarClicadoDuasVezes;
    private String nomeHospede;
    private String quartoHospede;
    private String emailHospede;
    private String senhaHospede;

    private final static String TXT_BVINDO = "Bem vindo, ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_servicos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();


        IniciarComponentes();

        ClicarRestaurante();
        ClicarPiscina();
        ClicarSalaoFestas();
        ClicarSalaoJogos();
        ClicarSPA();
        ClicarTurismo();
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
        ConstraintLayout layout = findViewById(R.id.ConstraintLayout_menu);

        this.botaoVoltarClicadoDuasVezes = true;
        SnackbarMsg(layout);
    }

    private void VerificaCliqueDuplo() {
        new Handler().postDelayed(() -> botaoVoltarClicadoDuasVezes = false, 2000);
    }

    private void ClicarRestaurante() {
        btnRestaurante.setOnClickListener(view -> {
            Intent intentRestaurante = new Intent(MenuServicosActivity.this,
                    RestauranteActivity.class);
            intentRestaurante.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
            intentRestaurante.putExtra("cpfHospede", senhaHospede);
            startActivity(intentRestaurante);
        });
    }

    private void ClicarPiscina() {
        btnPiscina.setOnClickListener(view -> {
            Intent intentPiscina = new Intent(MenuServicosActivity.this,
                    PiscinaActivity.class);
            intentPiscina.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
            intentPiscina.putExtra("cpfHospede", senhaHospede);
            startActivity(intentPiscina);
        });
    }

    private void ClicarSalaoFestas() {
        btnSalaoFestas.setOnClickListener(view -> {
            Intent intentSFestas = new Intent(MenuServicosActivity.this,
                    SalaodeFestasActivity.class);
            intentSFestas.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
            intentSFestas.putExtra("cpfHospede", senhaHospede);
            startActivity(intentSFestas);
        });
    }

    private void ClicarSalaoJogos() {
        btnSalaoJogos.setOnClickListener(view -> {
            Intent intentSJogos = new Intent(MenuServicosActivity.this,
                    SalaoJogosActivity.class);
            intentSJogos.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
            intentSJogos.putExtra("cpfHospede", senhaHospede);
            startActivity(intentSJogos);
        });
    }

    private void ClicarSPA() {
        btnSPA.setOnClickListener(view -> {
            Intent intentSPA = new Intent(MenuServicosActivity.this,
                    SPAActivity.class);
            intentSPA.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
            intentSPA.putExtra("cpfHospede", senhaHospede);
            startActivity(intentSPA);
        });
    }

    private void ClicarTurismo() {
        btnTurismo.setOnClickListener(view -> {
            Intent intentTurismo = new Intent(MenuServicosActivity.this,
                    TurismoActivity.class);
            intentTurismo.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
            intentTurismo.putExtra("cpfHospede", senhaHospede);
            startActivity(intentTurismo);
        });
    }

    private void ClicarGastos() {
        btnGastos.setOnClickListener(view -> {
            Intent intentGastos = new Intent(getApplicationContext(),
                    GastosHospedeActivity.class);
            intentGastos.putExtra("nomeHospede", nomeHospede); //passando os dados do email para próxima activity
            intentGastos.putExtra("cpfHospede", senhaHospede);
            intentGastos.putExtra("quartoHospede", quartoHospede);
            startActivity(intentGastos);
        });
    }

    private void IniciarComponentes() {
        BDQuery bancoDados = new BDQuery();
        emailHospede = getIntent().getStringExtra("emailUsado");
        senhaHospede = getIntent().getStringExtra("senhaUsado");
        nomeHospede = bancoDados.RetornarNomeHospede(emailHospede, senhaHospede);
        quartoHospede = bancoDados.RetornarQuartoHospede(nomeHospede, senhaHospede);

        txtBVindo = findViewById(R.id.txtBemVindo);
        btnRestaurante = findViewById(R.id.btnRestauranteMenu);
        btnPiscina = findViewById(R.id.btnPiscinaMenu);
        btnSalaoFestas = findViewById(R.id.btnSalaoFMenu);
        btnSalaoJogos = findViewById(R.id.btnSalaoJMenu);
        btnSPA = findViewById(R.id.btnSpaMenu);
        btnTurismo = findViewById(R.id.btnTurismoMenu);
        btnGastos = findViewById(R.id.btnConsultaGMenu);
        botaoVoltarClicadoDuasVezes = false;

        AdicionarNomeHospede();
    }

    private void AdicionarNomeHospede() {
        txtBVindo.setText(TXT_BVINDO + nomeHospede);
    }

    private void SnackbarMsg(View view) {
        Snackbar barraMsg = Snackbar.make(view, MENSAGENS[3], Snackbar.LENGTH_SHORT);
        barraMsg.setBackgroundTint(Color.WHITE);
        barraMsg.setTextColor(Color.BLACK);
        barraMsg.show();
    }
}