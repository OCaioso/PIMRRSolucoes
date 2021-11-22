package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterProdutos;
import com.example.rrsolucoeshotel.model.ProdutosServicosHotel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PiscinaActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaPiscina = new ArrayList<>();

    private String nomeHospede, cpfHospede;
    private int[] quantidade = {1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piscina);


        IniciarComponentes();
        PegaDataAtual();

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

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");

        recyclerProdutos = findViewById(R.id.recyclerViewPiscina);
    }

    private void criarProdutosPiscina() {

        ProdutosServicosHotel produto = new ProdutosServicosHotel("PISCINA", "22", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("PISCINA", "32", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Pizza de Quatro Queijos", "20", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Pizza de Hot Dogs", "18", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Risoto de Limão Siciliano", "40", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Lasagna alla Bolognesa", "28", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Lasagna Quatro Queijos", "28", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Coca-cola", "6", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Sprite", "6", "Descrição teste");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Fanta", "6", "Descrição teste");
        this.listaPiscina.add( produto );
    }

    //Molde de data caixa de mensagem para quando for confirmar o pedido
    private void CriaCaixaDialogo() {
        int[] quantidade = {1};

        AlertDialog.Builder criaCaixa = new AlertDialog.Builder(this);
        criaCaixa.setTitle("Confirmação de compra");
        criaCaixa.setIcon(R.drawable.ic_feedback);

        View caixaView = getLayoutInflater().inflate(R.layout.adapter_alertdialog_confirmar_produto,
                null);
        criaCaixa.setView(caixaView);

        criaCaixa.setPositiveButton("Confimar", (dialogInterface, i) -> {
            //DadosHospede dadosHospede = new DadosHospede();

            //dadosHospede.setNome(nomeHospede);
            //dadosHospede.setsetCPF(cpfHospede);
            //dadosHospede.setDescricao(FaltaDESCRICAO);
            //dadosHospede.setValor_Produto(String.valueOf(FaltaVALOR_PRODUTO));
            //dadosHospede.setQuantidade(String.valueOf(quantidade[0]));

            //double VALOR_TOTAL = quantidade[0] * VALOR_PRODUTO;
            //dadosHospede.setValor_Total(String.valueOf(FaltaVALOR_TOTAL));
            //dadosHospede.setData(PegaDataAtual());

            //RegistrarConsumoHospede(dadosHospede);
            Log.w("Confirmar pedido", PegaDataAtual());
        });
        criaCaixa.setNegativeButton("Cancelar",
                (dialogInterface, i) -> Log.w("Cancelar pedido", PegaDataAtual()));

        TextView descricaoProduto =  caixaView.findViewById(R.id.txtDescricaoAlertDialog);
        TextView quantidadeProduto = caixaView.findViewById(R.id.txtQuantidadeAlertDialog);

        SubtraiAQuantidade(quantidade, caixaView, quantidadeProduto);
        SomaAQuantidade(quantidade, caixaView, quantidadeProduto);

        AlertDialog caixaDialogo = criaCaixa.create();
        caixaDialogo.show();
    }

    private void SubtraiAQuantidade(int[] quantidade, View caixaView, TextView quantidadeProduto) {
        Button btnMenosQuantidade = caixaView.findViewById(R.id.btnMenosQuantidadeAlertDialog);
        btnMenosQuantidade.setOnClickListener(v -> {
            if(quantidade[0] > 1){
                quantidade[0]--;
                quantidadeProduto.setText(String.valueOf(quantidade[0]));
                Log.w("Teste botão menor", "apertou botão -");
            }
        });
    }

    private void SomaAQuantidade(int[] quantidade, View caixaView, TextView quantidadeProduto) {
        Button btnMaisQuantidade = caixaView.findViewById(R.id.btnMaisQuantidadeAlertDialog);
        btnMaisQuantidade.setOnClickListener(v -> {
            quantidade[0]++;
            quantidadeProduto.setText(String.valueOf(quantidade[0]));
            Log.w("Teste botão maior", "apertou botão +");
        });
    }

    private String PegaDataAtual() {
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date data = new Date();
        String dataFormatada = formataData.format(data);
        Log.w("Teste pegar data", "Data sistema: " + dataFormatada );

        return dataFormatada;
    }
}