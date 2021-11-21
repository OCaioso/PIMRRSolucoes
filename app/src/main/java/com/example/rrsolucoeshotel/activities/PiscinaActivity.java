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

public class PiscinaActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<produtosRestaurante> listaPiscina = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piscina);

        recyclerProdutos = findViewById(R.id.recyclerProdutos2);

        // Criar listagem de Produtos
        this.criarProdutosPiscina();


        // Configurar Adapter
        // Esse cara faz a exibição da lista no app - Necessita criar um construtor para classe adapter receber uma lista
        // Fazer isso dentro do AdapterProdutos.java
        AdapterProdutos adapter2 = new AdapterProdutos(listaPiscina);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true); // Tamanho fixo para otimizar o layout
        recyclerProdutos.setAdapter( adapter2 );


    }

    private void criarProdutosPiscina() {

        produtosRestaurante produto = new produtosRestaurante("PISCINA", "22", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("PISCINA", "32", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Pizza de Quatro Queijos", "20", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Pizza de Hot Dogs", "18", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Risoto de Limão Siciliano", "40", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Lasagna alla Bolognesa", "28", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Lasagna Quatro Queijos", "28", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Coca-cola", "6", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Sprite", "6", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new produtosRestaurante("Fanta", "6", "Descrição teste");
        this.listaPiscina.add( produto );
    }
}