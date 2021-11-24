package com.example.rrsolucoeshotel.activities;

import static com.example.rrsolucoeshotel.activities.ConstantesActivities.TXT_HOSPEDE;
import static com.example.rrsolucoeshotel.activities.ConstantesActivities.TXT_QUARTOS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterGastos;
import com.example.rrsolucoeshotel.model.BDQuery;
import com.example.rrsolucoeshotel.model.DadosHospede;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GastosHospedeActivity extends AppCompatActivity {

    private TextView txtHospede, txtQuarto, txtTotalGastoHospede;

    private RecyclerView recyclerGastos;
    private AdapterGastos adapterGastos;

    private List<DadosHospede> listaDadosHospede = new ArrayList<>();
    private DadosHospede dadosHospede = new DadosHospede();
    private final String TXT_TOTAL_GASTO = "TOTAL PARCIAL: R$ ";

    boolean botaoVoltarClicadoDuasVezes;
    private String nomeHospede, cpfHospede, quartoHospede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_hospede);

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
        recyclerGastos = findViewById(R.id.RecyclerView_gastos);
        botaoVoltarClicadoDuasVezes = false;
    }

    private void AdicionarNumeroQuartoATela(DadosHospede d) {
        txtHospede.setText(TXT_HOSPEDE + nomeHospede);
        txtQuarto.setText(TXT_QUARTOS + quartoHospede);
        txtTotalGastoHospede.setText(TXT_TOTAL_GASTO + d.getTotal_Final_Despesas());
    }

    private void CarregarDadosParaLista() {
        //listar tarefas
        BDQuery bancoDados = new BDQuery();

        listaDadosHospede = bancoDados.ListarGastosEmDadosHospede(listaDadosHospede, nomeHospede, cpfHospede);
        dadosHospede = listaDadosHospede.get(listaDadosHospede.size()-1);
        AdicionarNumeroQuartoATela(dadosHospede);

        //Configurar um adapter
        adapterGastos = new AdapterGastos(listaDadosHospede);

        //Configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerGastos.setLayoutManager(layoutManager);
        //tamanho fixo
        recyclerGastos.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.HORIZONTAL));
        recyclerGastos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL)); // Adiciona Linhas de Divisão dos itens//criando linha
        recyclerGastos.setAdapter(adapterGastos);
    }
}