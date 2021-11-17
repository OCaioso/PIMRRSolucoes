package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.BDHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class FormLogin extends AppCompatActivity {

    private EditText edEmail, edSenha;
    private Button btnAcessar;
    private ProgressBar progressBar;

    boolean botaoVoltarClicadoDuasVezes;
    private static final String[] MENSAGENS = {"Preencha todos os campos.",
            "Email não cadastrado.", "Senha incorreta.", "Toque novamente para sair"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

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
        BDHelper bancoDados = new BDHelper();

        if (bancoDados.VerificarEmail(email)) {
            if (bancoDados.VerificarSenhaDoEmail(email, senha)) {
                EfetuarLogin(email);
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

    private void EfetuarLogin(String email) {
        Intent logar = new Intent(getApplicationContext(),
                FormMenuServicos.class);
        logar.putExtra("emailUsado", email); //passando os dados do email para próxima atcivity
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

}