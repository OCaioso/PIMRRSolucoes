package com.example.rrsolucoeshotel.activities;

import static com.example.rrsolucoeshotel.activities.ConstantesActivities.MENSAGENS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterProdutos;
import com.example.rrsolucoeshotel.adapter.RecyclerItemClickListener;
import com.example.rrsolucoeshotel.model.BDQuery;
import com.example.rrsolucoeshotel.model.DadosHospede;
import com.example.rrsolucoeshotel.model.ProdutosServicosHotel;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TurismoActivity extends AppCompatActivity {


    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaTurismo= new ArrayList<>();

    private String nomeHospede, cpfHospede, nomeProduto, valorProduto;
    private final String TITULO_TURISMO = "Serviços de Turismo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turismo);

        setTitle(TITULO_TURISMO);
        IniciarComponentes();

        // Criar listagem de Produtos
        criarProdutosTurismo();
        CarregarDadosParaLista();
        ClicarProdutoEspecifico();
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

    private void CarregarDadosParaLista() {
        // Configurar Adapter
        // Esse cara faz a exibição da lista no app - Necessita criar um construtor para classe adapter receber uma lista
        // Fazer isso dentro do AdapterProdutos.java
        AdapterProdutos adapterTurismo = new AdapterProdutos(listaTurismo);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true); // Tamanho fixo para otimizar o layout
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter( adapterTurismo );
    }


    private void ClicarProdutoEspecifico() {
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
                                CriaCaixaDialogo(view, nomeProduto,
                                        Double.parseDouble(valorProduto));
                            }

                            @Override
                            public void onLongItemClick(View view, int position) { }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i,
                                                    long l) { }
                        }
                )
        );
    }

    private void CriaCaixaDialogo(View view, String produto, double valor) {
        int[] quantidade = {1};

        AlertDialog.Builder criaCaixa = new AlertDialog.Builder(this);
        criaCaixa.setTitle("Confirmação de compra");
        criaCaixa.setIcon(R.drawable.ic_money);

        View caixaView = getLayoutInflater().inflate(R.layout.adapter_alertdialog_confirmar_pedido,
                null);
        criaCaixa.setView(caixaView);

        criaCaixa.setPositiveButton("Confimar", (dialogInterface, i) -> {
            BDQuery bancoDados = new BDQuery();
            double valorTotal = quantidade[0] * valor;
            DadosHospede dadosHospede = new DadosHospede();

            ArmazenaDadosHospede(produto, valor, quantidade[0], valorTotal, dadosHospede);

            bancoDados.RegistrarConsumoHospede(dadosHospede);

            SnackbarMsgs(view, 4);
        });

        criaCaixa.setNegativeButton("Cancelar",
                (dialogInterface, i) -> Log.w("Cancelar clicado", PegaDataAtual()));

        TextView produtoServico =  caixaView.findViewById(R.id.txtProdutoServicoAlertDialog);
        TextView produtoPreco = caixaView.findViewById(R.id.txtPrecoAlertDialog);
        TextView quantidadeProduto = caixaView.findViewById(R.id.txtQuantidadeAlertDialog);

        produtoServico.setText(produto);
        produtoPreco.setText("R$ " + valor);
        SubtraiAQuantidade(valor, quantidade, caixaView, quantidadeProduto, produtoPreco);
        SomaAQuantidade(valor, quantidade, caixaView, quantidadeProduto, produtoPreco);

        AlertDialog caixaDialogo = criaCaixa.create();
        caixaDialogo.show();
    }

    private void ArmazenaDadosHospede(String produto, double valor, int i1, double valorTotal,
                                      DadosHospede dadosHospede) {
        dadosHospede.setNome(nomeHospede);
        dadosHospede.setCPF(cpfHospede);
        dadosHospede.setNomeProduto(produto);
        dadosHospede.setValor_Produto(String.valueOf(valor));
        dadosHospede.setQuantidade(String.valueOf(i1));
        dadosHospede.setValor_Total(String.valueOf(valorTotal));
        dadosHospede.setData(PegaDataAtual());
    }

    private String PegaDataAtual() {
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date data = new Date();
        String dataFormatada = formataData.format(data);

        return dataFormatada;
    }

    private void SubtraiAQuantidade(double valor, int[] quantidade, View caixaView,
                                    TextView quantidadeProduto, TextView produtoPreco) {
        ImageButton btnMenosQuantidade = caixaView.findViewById(R.id.btnMenosQuantidadeAlertDialog);
        btnMenosQuantidade.setOnClickListener(v -> {
            if(quantidade[0] > 1){
                quantidade[0]--;
                double resultado = valor * quantidade[0];

                quantidadeProduto.setText(String.valueOf(quantidade[0]));
                produtoPreco.setText("R$ " + resultado);
            }
        });
    }

    private void SomaAQuantidade(double valor, int[] quantidade, View caixaView,
                                 TextView quantidadeProduto, TextView produtoPreco) {
        ImageButton btnMaisQuantidade = caixaView.findViewById(R.id.btnMaisQuantidadeAlertDialog);
        btnMaisQuantidade.setOnClickListener(v -> {
            quantidade[0]++;
            double resultado = valor * quantidade[0];

            quantidadeProduto.setText(String.valueOf(quantidade[0]));
            produtoPreco.setText("R$ " + resultado);
        });
    }

    private void SnackbarMsgs(View view, int i) {
        Snackbar barraMsg = Snackbar.make(view, MENSAGENS[i], Snackbar.LENGTH_SHORT);
        barraMsg.setBackgroundTint(Color.WHITE);
        barraMsg.setTextColor(Color.BLACK);
        barraMsg.show();
    }
}