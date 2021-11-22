package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterProdutos;
import com.example.rrsolucoeshotel.model.ProdutosServicosHotel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SalaoJogosActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaSalaojogos= new ArrayList<>();

    private String nomeHospede, cpfHospede;
    private int[] quantidade = {1};

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
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter( adapter );
    }

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");

        recyclerProdutos = findViewById(R.id.recyclerViewSalaoJogos);
    }

    public void criarProdutosSalaoJogos(){

        ProdutosServicosHotel produto = new ProdutosServicosHotel("Baralho simples", "25", "Baralho Convencional de plastico 52 cartas");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Baralho Duplo", "45", "Baralho Convencional de plasticos 104 cartas");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Dealer para jogos de Poker - minimo 4 horas", "500", "Profissional embaralhador para jogos de poker");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Mesa de Sinuca - 1 hora", "20", "Aluguel de mesa de sinuca para jogos de bilhar com Tacos e Jogo de Bolas");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Playstation 5 - 1 hora", "20", "Aluguel do Playstation 5 - Consultar jogos disponiveis");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Xbox One - 1 hora", "18", "Aluguel do Xbox One - Consultar jogos disponiveis");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Mesa de Xadrez", "15", "Aluguel da mesa de xadrez");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Tenis de Mesa", "12", "Aluguel da mesa de Tenis de mesa com Raquete e Bolinhas");
        this.listaSalaojogos.add( produto );

        produto = new ProdutosServicosHotel("Arcade Jogos Retrô", "12", "Aluguel do sistema de Arcade com jogos retrô - 4 Jogadores");
        this.listaSalaojogos.add( produto );
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