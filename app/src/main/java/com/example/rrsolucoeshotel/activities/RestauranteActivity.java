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

public class RestauranteActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaProdutos = new ArrayList<>();

    private String nomeHospede, cpfHospede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);


        IniciarComponentes();

        // Criar listagem de Produtos
        this.criarProdutosRestaurantes();


        // Configurar Adapter
        // Esse cara faz a exibição da lista no app - Necessita criar um construtor para classe adapter receber uma lista
        // Fazer isso dentro do AdapterProdutos.java
        AdapterProdutos adapter = new AdapterProdutos(listaProdutos);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true); // Tamanho fixo para otimizar o layout
        recyclerProdutos.setAdapter( adapter );
    }

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");

        recyclerProdutos = findViewById(R.id.recyclerViewRestaurante);
    }

    public void criarProdutosRestaurantes(){

        ProdutosServicosHotel produto = new ProdutosServicosHotel("Strogonoff de Frango", "22", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Strogonoff de Carne", "32", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Pizza de Quatro Queijos", "20", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Pizza de Hot Dogs", "18", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Risoto de Limão Siciliano", "40", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Lasagna alla Bolognesa", "28", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Lasagna Quatro Queijos", "28", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Coca-cola", "6", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Sprite", "6", "Descrição teste");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Fanta", "6", "Descrição teste");
        this.listaProdutos.add( produto );

    }


}