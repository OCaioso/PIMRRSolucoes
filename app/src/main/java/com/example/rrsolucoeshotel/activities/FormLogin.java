package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.QueryHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class FormLogin extends AppCompatActivity {

    private EditText edEmail, edSenha;
    private Button btnAcessar;
    private static final String [] MENSAGENS_ERRO = {"Preencha todos os campos.",
            "Email não cadastrado.", "Senha incorreta."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        //esconde barra de ação
        Objects.requireNonNull(getSupportActionBar()).hide();
        IniciarComponentes();

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String senha = edSenha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar barraMsg = Snackbar.make(view, MENSAGENS_ERRO[0],
                            Snackbar.LENGTH_SHORT);
                    barraMsg.setBackgroundTint(Color.WHITE);
                    barraMsg.setTextColor(Color.BLACK);
                    barraMsg.show();
                }else{
                    VerificarLogin(view);
                }
            }
        });

        /*
        Connection conexaoBD = Conexao.Conectar(); //tirei a necessidade de usar getApplicationContext() no método Conectar();
        try {

            if (conexaoBD != null) {
                if (!conexaoBD.isClosed())
                    texto.setText("CONEXÃO REALIZADA COM SUCESSO!!!");
                else
                    texto.setText("CONEXÃO FECHADA!!!");
            }else{
                texto.setText("CONEXÃO NULA, NÃO REALIZADA!!!");
            }

            String queryTeste = "SELECT * FROM LOGIN_Hospedes";

            PreparedStatement queryTestada = conexaoBD.prepareStatement(queryTeste);

            ResultSet resultadoQuery = queryTestada.executeQuery();

            while(resultadoQuery.next()){
                Log.w("print", "printou");
                texto.setText(resultadoQuery.getString(1));
            }

            queryTestada.close();
            conexaoBD.close();

        } catch (SQLException e) {
            //e.printStackTrace();
            Log.w("CONEXÃO FALHOU!!!", e.getMessage());
        }
        */
    }

    private void VerificarLogin(View view) {
        QueryHelper BancoDados = new QueryHelper();
        String email = edEmail.getText().toString();
        String senha = edSenha.getText().toString();

        if(BancoDados.VerificarEmail(email)){
            if(BancoDados.VerificarSenhaDoEmail(email, senha)){
                EfetuarLogin();
            } else{
                Snackbar barraMsg = Snackbar.make(view, MENSAGENS_ERRO[2], Snackbar.LENGTH_SHORT);
                barraMsg.setBackgroundTint(Color.WHITE);
                barraMsg.setTextColor(Color.BLACK);
                barraMsg.show();
            }
        } else{
            Snackbar barraMsg = Snackbar.make(view, MENSAGENS_ERRO[1], Snackbar.LENGTH_SHORT);
            barraMsg.setBackgroundTint(Color.WHITE);
            barraMsg.setTextColor(Color.BLACK);
            barraMsg.show();
        }
    }

    private void EfetuarLogin() {
        Log.w("LOGIN", "realizado com sucesso");
    }

    private void IniciarComponentes(){
        edEmail = findViewById(R.id.txtEmailLogin);
        edSenha = findViewById(R.id.txtSenhaLogin);
        btnAcessar = findViewById(R.id.btnAcessarLogin);
    }

}