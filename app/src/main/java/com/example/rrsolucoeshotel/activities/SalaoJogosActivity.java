package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterProdutos;
import com.example.rrsolucoeshotel.model.produtosRestaurante;

import java.util.ArrayList;
import java.util.List;

public class SalaoJogosActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<produtosRestaurante> listaSalaojogos= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salao_jogos);






        recyclerProdutos = findViewById(R.id.recyclerViewSalaoJogos);

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

    public void criarProdutosSalaoJogos(){

        produtosRestaurante produto = new produtosRestaurante("listaSalaojogos", "22", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("listaSalaojogos", "32", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("listaSalaojogos", "20", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("Pizza de Hot Dogs", "18", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("Risoto de Limão Siciliano", "40", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("Lasagna alla Bolognesa", "28", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("Lasagna Quatro Queijos", "28", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("Coca-cola", "6", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("Sprite", "6", "Descrição teste");
        this.listaSalaojogos.add( produto );

        produto = new produtosRestaurante("Fanta", "6", "Descrição teste");
        this.listaSalaojogos.add( produto );


    }
}