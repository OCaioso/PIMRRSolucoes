package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterProdutos;
import com.example.rrsolucoeshotel.model.ProdutosServicosHotel;

import java.util.ArrayList;
import java.util.List;

public class SalaoJogosActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaSalaojogos= new ArrayList<>();

    private String nomeHospede, cpfHospede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salao_jogos);


        IniciarComponentes();

        // Criar listagem de Produtos
        this.criarProdutosSalaoJogos();


        // Configurar Adapter
        // Esse cara faz a exibição da lista no app - Necessita criar um construtor para classe adapter receber uma lista
        // Fazer isso dentro do AdapterProdutos.java
        AdapterProdutos adapter = new AdapterProdutos(listaSalaojogos);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true); // Tamanho fixo para otimizar o layout
        recyclerProdutos.setAdapter( adapter );
    }

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");

        recyclerProdutos = findViewById(R.id.recyclerViewSalaoJogos);
    }

    public void criarProdutosSalaoJogos(){

        ProdutosServicosHotel produto = new ProdutosServicosHotel("listaSalaojogos", "22", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("listaSalaojogos", "32", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("listaSalaojogos", "20", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Pizza de Hot Dogs", "18", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Risoto de Limão Siciliano", "40", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Lasagna alla Bolognesa", "28", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Lasagna Quatro Queijos", "28", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Coca-cola", "6", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Sprite", "6", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Fanta", "6", "Descrição teste");
        this.listaSalaojogos.add( produto );


    }
}