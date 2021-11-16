package com.example.rrsolucoeshotel.model;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHelper {

    public Boolean VerificarEmail(String email) {
        Connection conexaoBD = Conexao.Conectar();
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
        Connection conexaoBD2 = Conexao.Conectar();
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
}
