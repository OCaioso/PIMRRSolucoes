package com.example.rrsolucoeshotel.activities;

import static com.example.rrsolucoeshotel.activities.ConstantesActivities.MSG_VOLTAR;
import static com.example.rrsolucoeshotel.activities.ConstantesActivities.TXT_HOSPEDE;
import static com.example.rrsolucoeshotel.activities.ConstantesActivities.TXT_QUARTOS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.GastosAdapter;
import com.example.rrsolucoeshotel.model.BDQuery;
import com.example.rrsolucoeshotel.model.DadosHospede;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FormGastos extends AppCompatActivity {

    private TextView txHospede, txQuarto, txTotalGastoHospede;
    private Button btSair;

    private RecyclerView recyclerView;
    private GastosAdapter gastosAdapter;

    private List<DadosHospede> listaDadosHospede = new ArrayList<>();
    private DadosHospede dadosHospede = new DadosHospede();
    private final String TXT_TOTAL_GASTO = "TOTAL PARCIAL: R$ ";

    boolean botaoVoltarClicadoDuasVezes;
    private String nomeHospede, cpfHospede, quartoHospede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_gastos);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();


        IniciarComponentes();
        CarregarDadosParaLista();

        ClicarSairFormGastos();
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
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");
        quartoHospede = getIntent().getStringExtra("quartoHospede");

        txHospede = findViewById(R.id.txtHospedeGasto);
        txQuarto = findViewById(R.id.txtQuartoGasto);
        txTotalGastoHospede = findViewById(R.id.txtTotalGastoHospede);
        btSair = findViewById(R.id.btnSairGastos);
        recyclerView = findViewById(R.id.RecyclerView_gastos);
        botaoVoltarClicadoDuasVezes = false;


    }

    private void AdicionarNomeQuartoATela(DadosHospede d) {
        txHospede.setText(TXT_HOSPEDE + nomeHospede);
        txQuarto.setText(TXT_QUARTOS + quartoHospede);
        txTotalGastoHospede.setText(TXT_TOTAL_GASTO + d.getTotal_Final_Despesas());
    }

    private void CarregarDadosParaLista() {
        //listar tarefas
        BDQuery bancoDados = new BDQuery();

        listaDadosHospede = bancoDados.ListarGastosEmDadosHospede(listaDadosHospede, nomeHospede, cpfHospede);
        dadosHospede = listaDadosHospede.get(listaDadosHospede.size()-1);
        AdicionarNomeQuartoATela(dadosHospede);

        //Configurar um adapter
        gastosAdapter = new GastosAdapter(listaDadosHospede);

        //Configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //tamanho fixo
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.HORIZONTAL)); //criando linha
        recyclerView.setAdapter(gastosAdapter);
    }
}