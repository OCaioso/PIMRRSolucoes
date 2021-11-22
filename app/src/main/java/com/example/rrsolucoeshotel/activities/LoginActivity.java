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

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.BDQuery;
import com.google.android.material.snackbar.Snackbar;

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

    //teste com caixa dialogo
    private void CriaCaixaDialogo() {
        AlertDialog.Builder criaCaixa = new AlertDialog.Builder(this);
        criaCaixa.setTitle("Confirmação de compra");
        criaCaixa.setIcon(R.drawable.ic_feedback);

        View caixaView = getLayoutInflater().inflate(R.layout.adapter_alertdialog_confirmar_produto,
                null);
        criaCaixa.setView(caixaView);

        criaCaixa.setPositiveButton("Confimar", (dialogInterface, i) -> {
            Log.w("caixaDialogo", "Clicou confirmar");
            //Deslogar();
        });
        criaCaixa.setNegativeButton("Cancelar", (dialogInterface, i) ->
                Log.w("caixaDialogo", "Clicou cancelar"));

        EditText nomeProduto =  caixaView.findViewById(R.id.textDescricaoAlertDialog);
        EditText quantidadeProduto = caixaView.findViewById(R.id.txtQuantidadeAlertDialog);

        Button btnMenosQuantidade = caixaView.findViewById(R.id.btnMenosQuantidadeAlertDialog);
        btnMenosQuantidade.setOnClickListener(v -> Log.w("Teste botão menor", "apertou botão -"));

        Button btnMaisQuantidade = caixaView.findViewById(R.id.btnMaisQuantidadeAlertDialog);
        btnMaisQuantidade.setOnClickListener(v -> Log.w("Teste botão maior", "apertou botão +"));

        AlertDialog alertDialog = criaCaixa.create();
        alertDialog.show();
    }
}