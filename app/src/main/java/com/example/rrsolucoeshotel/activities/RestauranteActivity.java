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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterProdutos;
import com.example.rrsolucoeshotel.adapter.RecyclerItemClickListener;
import com.example.rrsolucoeshotel.model.ProdutosServicosHotel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RestauranteActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaProdutos = new ArrayList<>();

    private String nomeHospede, cpfHospede;
    private int[] quantidade = {1};

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
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL)); // Adiciona Linhas de Divisão dos itens
        recyclerProdutos.setAdapter( adapter );

        //Eventos Click
        recyclerProdutos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerProdutos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // Descobrir como buscar o nome da classe.. exemplo
                                //Filme filme = listaProdutos.get(position); (Filme deveria ser o nome da classe construtora, mas não consigo)

                                Toast.makeText(
                                        getApplicationContext(),
                                        "pressionado",
                                        Toast.LENGTH_SHORT
                                ).show();

                                // configurar o valor para quando o usuario clicar

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Clique Longo",
                                        Toast.LENGTH_SHORT
                                ).show();

                                // Configurar para quando o usuario fazer o click  Longo

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );


    }

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");

        recyclerProdutos = findViewById(R.id.recyclerViewRestaurante);
    }

    public void criarProdutosRestaurantes(){

        ProdutosServicosHotel produto = new ProdutosServicosHotel("Strogonoff de Frango", "22", "Serve 1 pessoa - Acompanha Arroz e Fritas");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Strogonoff de Carne", "32", "Serve 1 pessoa - Acompanha Arroz e Fritas");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Pizza de Quatro Queijos", "40", "Serve 4 pessoas");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Pizza de Hot Dogs", "40", "Serve 4 pessoas");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Risoto de Limão Siciliano", "40", "Serve 1 pessoa");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Lasagna alla Bolognesa", "28", "Serve 2 pessoa\"");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Lasagna Quatro Queijos", "28", "Serve 2 pessoa\"");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Coca-cola", "6", "Lata de Refrigerante - 350ml");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Sprite", "6", "Lata de Refrigerante - 350ml");
        this.listaProdutos.add( produto );

        produto = new ProdutosServicosHotel("Fanta", "6", "Lata de Refrigerante - 350ml");
        this.listaProdutos.add( produto );

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