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

public class GastosHospedeActivity extends AppCompatActivity {

    private TextView txtHospede, txtQuarto, txtTotalGastoHospede;

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
    }

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");
        quartoHospede = getIntent().getStringExtra("quartoHospede");

        txtHospede = findViewById(R.id.txtHospedeGasto);
        txtQuarto = findViewById(R.id.txtQuartoGasto);
        txtTotalGastoHospede = findViewById(R.id.txtTotalGastoHospede);
        recyclerView = findViewById(R.id.RecyclerView_gastos);
        botaoVoltarClicadoDuasVezes = false;
    }

    private void AdicionarNomeQuartoATela(DadosHospede d) {
        txtHospede.setText(TXT_HOSPEDE + nomeHospede);
        txtQuarto.setText(TXT_QUARTOS + quartoHospede);
        txtTotalGastoHospede.setText(TXT_TOTAL_GASTO + d.getTotal_Final_Despesas());
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