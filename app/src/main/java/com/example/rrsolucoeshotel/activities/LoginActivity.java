package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.BDQuery;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText edEmail, edSenha;
    private Button btnAcessar;
    private ProgressBar progressBar;

    boolean botaoVoltarClicadoDuasVezes;
    private static final String[] MENSAGENS = {"Preencha todos os campos.",
            "Email não cadastrado.", "Senha incorreta.", "Toque novamente para sair"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();


        IniciarComponentes();
        ClicarAcessar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        edEmail.setText("");
        edSenha.setText("");
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (botaoVoltarClicadoDuasVezes) {
            super.onBackPressed();
            return;
        }

        ConfiguraClicarDuasVezes();
        VerificaCliqueDuplo();
    }

    private void VerificaCliqueDuplo() {
        new Handler().postDelayed(() -> botaoVoltarClicadoDuasVezes = false, 2000);
    }

    private void ConfiguraClicarDuasVezes() {
        ConstraintLayout layout = findViewById(R.id.ConstraintLayout_login);

        this.botaoVoltarClicadoDuasVezes = true;
        SnackbarMsgs(layout, 3);
    }

    private void ClicarAcessar() {
        btnAcessar.setOnClickListener(view -> {
            //CriaCaixaDialogo();

            progressBar.setVisibility(View.VISIBLE);

            String email = edEmail.getText().toString();
            String senha = edSenha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                //Msg não preenchido
                SnackbarMsgs(view, 0);
                progressBar.setVisibility(View.INVISIBLE);
            } else {
                VerificarLogin(view, email, senha);
            }

        });
    }

    private void VerificarLogin(View view, String email, String senha) {
        BDQuery bancoDados = new BDQuery();

        if (bancoDados.VerificarEmail(email)) {
            if (bancoDados.VerificarSenhaDoEmail(email, senha)) {
                EfetuarLogin(email, senha);
            } else {
                //Msg erro com a senha
                SnackbarMsgs(view, 2);
            }
        } else {
            //Msg erro com o email
            SnackbarMsgs(view, 1);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void EfetuarLogin(String email, String senha) {
        Intent logar = new Intent(getApplicationContext(),
                MenuServicosActivity.class);
        logar.putExtra("emailUsado", email); //passando os dados do email para próxima atcivity
        logar.putExtra("senhaUsado", senha);
        //finish();
        startActivity(logar);
    }

    private void SnackbarMsgs(View view, int i) {
        Snackbar barraMsg = Snackbar.make(view, MENSAGENS[i], Snackbar.LENGTH_SHORT);
        barraMsg.setBackgroundTint(Color.WHITE);
        barraMsg.setTextColor(Color.BLACK);
        barraMsg.show();
    }

    private void IniciarComponentes() {
        edEmail = findViewById(R.id.txtEmailLogin);
        edSenha = findViewById(R.id.txtSenhaLogin);
        btnAcessar = findViewById(R.id.btnAcessarLogin);
        progressBar = findViewById(R.id.progressBarLogin);
        botaoVoltarClicadoDuasVezes = false;
    }

    //teste com caixa dialogo, deixei em comentário o comando de login para testar a criação dela
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