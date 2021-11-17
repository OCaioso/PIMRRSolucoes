package com.example.rrsolucoeshotel.model;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDHelper {

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

    public Boolean VerificarEmail(String email) {
        Connection conexaoBD = Conectar();
        String usuarioBd = "";

        String querySelect = "SELECT Usuario FROM LOGIN_Hospedes ";
        querySelect +="WHERE Usuario = ?;";

        try {
            PreparedStatement pdst = conexaoBD.prepareStatement(querySelect);

            pdst.setString(1, email);

            ResultSet resultadoQuery = pdst.executeQuery();

            while (resultadoQuery.next()) {
                usuarioBd = resultadoQuery.getString("Usuario");
            }
            resultadoQuery.close();
            conexaoBD.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(usuarioBd.equals(email)){
            Log.w("Email igual BD", "Usuario: "+ usuarioBd);
            return true;
        } else{
            Log.w("Email diferente BD", "Usuario: "+ usuarioBd);
            Log.w("Email diferente Tela", "Email: "+ email);
            return false;
        }
    }

    public Boolean VerificarSenhaDoEmail(String email, String senha) {
        Connection conexaoBD2 = Conectar();
        String usuarioBd = "", senhaBd = "";

        String querySelect = "SELECT Usuario, Senha FROM LOGIN_Hospedes ";
        querySelect+= "WHERE Usuario = ? AND Senha = ?;";

        try {
            PreparedStatement ppst = conexaoBD2.prepareStatement(querySelect);

            ppst.setString(1, email);
            ppst.setString(2, senha);

            ResultSet resultadoQuery = ppst.executeQuery();

            while (resultadoQuery.next()) {
                usuarioBd = resultadoQuery.getString("Usuario");
                senhaBd = resultadoQuery.getString("Senha");
                Log.w("valores do resultset", "Usuario: "+ usuarioBd + "  Senha: " + senhaBd);
            }
            resultadoQuery.close();
            conexaoBD2.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if((usuarioBd.equals(email)) && (senhaBd.equals(senha))){
            Log.w("Senha igual Tela", "Email: "+ email + "  Senha: " + senha);
            return true;
        } else{
            Log.w("Senha diferente BD", "Usuario: "+ usuarioBd + "  Senha: " + senhaBd);
            Log.w("Senha diferente Tela", "Email: "+ email + "  Senha: " + senha);
            return false;
        }
    }

    public String RetornarNomeCliente(String email){
        Connection conexaoBD3 = Conectar();
        String nomeCliente = "";

        String querySelect = "SELECT Nome FROM Hospedes h LEFT JOIN LOGIN_Hospedes lh " +
                "ON h.Email = lh.Usuario ";
        querySelect+= "WHERE h.Email = ?;";

        try {
            PreparedStatement ppst = conexaoBD3.prepareStatement(querySelect);

            ppst.setString(1, email);

            ResultSet resultadoQuery = ppst.executeQuery();

            while (resultadoQuery.next()) {
                nomeCliente = resultadoQuery.getString("Nome");
                Log.w("valores do resultset", "Email: "+ nomeCliente);
            }
            resultadoQuery.close();
            conexaoBD3.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nomeCliente;
    }
}
