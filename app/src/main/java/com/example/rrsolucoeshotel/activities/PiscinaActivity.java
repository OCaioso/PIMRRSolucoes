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
import android.widget.Toast;

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

public class PiscinaActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaPiscina = new ArrayList<>();

    private String nomeHospede, cpfHospede, nomeProduto, valorProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piscina);


        IniciarComponentes();

        // Criar listagem de Produtos
        criarProdutosPiscina();
        ClicarProdutoEspecifico();
    }

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");

        recyclerProdutos = findViewById(R.id.recyclerViewPiscina);
    }

    private void criarProdutosPiscina() {

        ProdutosServicosHotel produto = new ProdutosServicosHotel("Toalhas Grandes", "10", "Toalhas grandes para banho");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Toalhas Pequenas", "6", "Toalhas pequenas para mãos e rosto");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Batata Frita", "22", "Porção de batata frita");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Mini Pizza", "20", "Consulte sabores");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Porção de Frango a Passarinho", "28", "Porção média de frango a passarinho");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Cerveja lata", "9", "Cerveja lata tamnho 360ml - Consulte Disponibilidade");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Cerveja Garrafa", "15", "Cerveja 600ml - Consulte Dsiponibilidade");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Coca-cola", "6", "Lata de Refrigerante - 350ml");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Sprite", "6", "Lata de Refrigerante - 350ml");
        this.listaPiscina.add( produto );

        produto = new ProdutosServicosHotel("Fanta", "6", "Lata de Refrigerante - 350ml");
        this.listaPiscina.add( produto );
    }

    private void ClicarProdutoEspecifico() {
        // Configurar Adapter
        // Esse cara faz a exibição da lista no app - Necessita criar um construtor para classe adapter receber uma lista
        // Fazer isso dentro do AdapterProdutos.java
        AdapterProdutos adapter2 = new AdapterProdutos(listaPiscina);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true); // Tamanho fixo para otimizar o layout
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter( adapter2 );

        //Eventos Click
        recyclerProdutos.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerProdutos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ProdutosServicosHotel produtoClicado = listaPiscina.get(position);

                                nomeProduto = produtoClicado.getTitulo();
                                valorProduto = produtoClicado.getValor();
                                CriaCaixaDialogo(view, nomeProduto,
                                        Double.parseDouble(valorProduto));
                                Log.w("Cliquei produto", "Descricao: "+ nomeProduto + "Valor: " + valorProduto);
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

        View caixaView = getLayoutInflater().inflate(R.layout.adapter_alertdialog_confirmar_produto,
                null);
        criaCaixa.setView(caixaView);

        criaCaixa.setPositiveButton("Confimar", (dialogInterface, i) -> {
            BDQuery bancoDados = new BDQuery();
            double valorTotal = quantidade[0] * valor;
            DadosHospede dadosHospede = new DadosHospede();

            ArmazenaDadosHospede(produto, valor, quantidade[0], valorTotal, dadosHospede);

            bancoDados.RegistrarConsumoHospede(dadosHospede);

            SnackbarMsgs(view, 4);
            Log.w("Confirmar clicado", PegaDataAtual());
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
        Log.w("Teste pegar data", "Data sistema: " + dataFormatada );

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
                Log.w("Teste botão menor", "apertou botão <");
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
            Log.w("Teste botão maior", "apertou botão >");
        });
    }

    private void SnackbarMsgs(View view, int i) {
        Snackbar barraMsg = Snackbar.make(view, MENSAGENS[i], Snackbar.LENGTH_SHORT);
        barraMsg.setBackgroundTint(Color.WHITE);
        barraMsg.setTextColor(Color.BLACK);
        barraMsg.show();
    }
}