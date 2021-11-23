package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class TurismoActivity extends AppCompatActivity {


    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaTurismo= new ArrayList<>();

    private String nomeHospede, cpfHospede;

    private String nomeProduto;
    private String valorProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turismo);


        IniciarComponentes();

        // Criar listagem de Produtos
        this.criarProdutosTurismo();


        // Configurar Adapter
        // Esse cara faz a exibição da lista no app - Necessita criar um construtor para classe adapter receber uma lista
        // Fazer isso dentro do AdapterProdutos.java
        AdapterProdutos adapter = new AdapterProdutos(listaTurismo);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true); // Tamanho fixo para otimizar o layout
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter( adapter );

        //Eventos Click
        recyclerProdutos.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerProdutos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // Descobrir como buscar o nome da classe.. exemplo
                                ProdutosServicosHotel produtoClicado = listaTurismo.get(position); //(Filme deveria ser o nome da classe construtora, mas não consigo)

                                nomeProduto = produtoClicado.getTitulo();
                                valorProduto = produtoClicado.getValor();
                                Toast.makeText(getApplicationContext(),
                                        "Selecionado " + nomeProduto+
                                                "Valor: " + valorProduto,
                                        Toast.LENGTH_SHORT
                                ).show();
                                Log.w("Cliquei produto", "Descricao: "+ nomeProduto + "Valor: " + valorProduto);

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

        recyclerProdutos = findViewById(R.id.recyclerViewTurismo);
    }

    public void criarProdutosTurismo(){

        ProdutosServicosHotel produto = new ProdutosServicosHotel("Charrete", "150", "Passeio noturno pela cidade em charretes");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Passeio de Helicoptero Diurno", "660", "Sobrevoo das lindas cataratas do Parque Ambiental");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Passeio de Helicoptero Noturno", "800", "Sobrevoo da iluminada cidade a noite");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Quadriciclo", "180", "Alguel de Quadriciclo para conhecer as Dunas");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Parque Aquatico", "350", "Refrescante atração para passar o dia com a familia");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Rota dos Aventureiros", "28", "Trilhas acompanhados de nossos guias da Natureza");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Bungee Jump", "200", "Pulo radical em cachoeira");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Rio Bravo", "250", "Enfrente a correnteza nessa emocionante experiencia");
        this.listaTurismo.add( produto );

        produto = new ProdutosServicosHotel("Catamarã", "150", "Passeio tranquilo em nosso barco - Almoço incluso");
        this.listaTurismo.add( produto );
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