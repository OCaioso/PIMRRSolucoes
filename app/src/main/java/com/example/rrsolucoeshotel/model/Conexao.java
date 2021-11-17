package com.example.rrsolucoeshotel.model;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String DATABASE ="BDRRSolucoesHotel";
    private static final String LOGIN = "RRSolucoes_SQLLogin_1";
    private static final String SENHA= "2hjq1wkela";

    public static Connection Conectar(){
        Connection conexao = null;

        try{

            StrictMode.ThreadPolicy politica;
            politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            conexao = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://BDRRSolucoesHotel.mssql.somee.com;"+
                            "databaseName="+DATABASE+
                            ";user="+LOGIN+
                            ";password="+SENHA);
            Log.w("Connection","open");

        }catch(SQLException e){
            Log.w("SERVIDOR INDISPONÍVEL", e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            Log.w("Error connection", e.getMessage());
        }
        return conexao;
    }
}

//vai que eu precise então ta ai abaixo um modelo de conexão
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
