package com.example.rrsolucoeshotel.model;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BDQuery {

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
        Connection conexaoBD1 = Conectar();
        String usuarioBd = "";

        String querySelect = "SELECT Usuario FROM LOGIN_Hospedes ";
        querySelect +="WHERE Usuario = ?;";

        try {
            PreparedStatement pdst = conexaoBD1.prepareStatement(querySelect);

            pdst.setString(1, email);

            ResultSet resultadoQuery = pdst.executeQuery();

            while (resultadoQuery.next()) {
                usuarioBd = resultadoQuery.getString("Usuario");
            }
            resultadoQuery.close();
            conexaoBD1.close();

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

    public String RetornarNomeHospede(String email, String senha){
        Connection conexaoBD3 = Conectar();
        String nomeHospede = "";

        String querySelect = "SELECT Nome FROM Hospedes h LEFT JOIN LOGIN_Hospedes lh " +
                "ON h.Email = lh.Usuario AND h.CPF = lh.Senha ";
        querySelect+= "WHERE h.Email = ? AND h.CPF = ?;";

        try {
            PreparedStatement ppst = conexaoBD3.prepareStatement(querySelect);

            ppst.setString(1, email);
            ppst.setString(2, senha);

            ResultSet resultadoQuery = ppst.executeQuery();

            while (resultadoQuery.next()) {
                nomeHospede = resultadoQuery.getString("Nome");
                Log.w("valores do resultset", "Nome: "+ nomeHospede);
            }
            resultadoQuery.close();
            conexaoBD3.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nomeHospede;
    }

    public String RetornarQuartoHospede(String nome, String cpf){
        Connection conexaoBD4 = Conectar();
        String quartoHospede = "";

        String querySelect = "SELECT Numero_Quarto FROM Entradas e LEFT JOIN Hospedes h " +
                "ON e.Nome = h.Nome AND e.CPF = h.CPF ";
        querySelect+= "WHERE e.Nome = ? AND e.CPF = ?;";

        try {
            PreparedStatement ppst = conexaoBD4.prepareStatement(querySelect);

            ppst.setString(1, nome);
            ppst.setString(2, cpf);

            ResultSet resultadoQuery = ppst.executeQuery();

            while (resultadoQuery.next()) {
                quartoHospede = resultadoQuery.getString("Numero_Quarto");
                Log.w("valores do resultset", "Numero_Quarto: "+ quartoHospede);
            }
            resultadoQuery.close();
            conexaoBD4.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return quartoHospede;
    }

    public List<DadosHospede> ListarGastosEmDadosHospede(List<DadosHospede> listaDH, String nome, String cpf){
        Connection conexaoBD5 = Conectar();
        int quantidadeH = 0;
        double vProdutoH = 0, vTotalH = 0, totalFinalDespesas = 0;

        String querySelect = "SELECT c.Descricao, c.Valor_Produto, c.Quantidade "+
                "FROM Consumos c LEFT JOIN Hospedes h ON c.Nome = h.Nome AND c.CPF = h.CPF ";
        querySelect+= "WHERE c.Nome = ? AND c.CPF = ?;";

        try {
            PreparedStatement ppst = conexaoBD5.prepareStatement(querySelect);

            ppst.setString(1, nome);
            ppst.setString(2, cpf);

            ResultSet resultadoQuery = ppst.executeQuery();

            while (resultadoQuery.next()) {
                DadosHospede dadosHospede = new DadosHospede();

                dadosHospede.setDescricao(resultadoQuery.getString("Descricao"));
                Log.w("valores do resultset", "Valor_Produto: "+ dadosHospede.getDescricao());

                dadosHospede.setValor_Produto(resultadoQuery.getString("Valor_Produto"));
                Log.w("valores do resultset", "Valor_Produto: "+ dadosHospede.getValor_Produto());

                vProdutoH = Double.parseDouble(resultadoQuery.getString("Valor_Produto"));
                Log.w("valores do resultset", "Valor_Produto: "+ vProdutoH);

                dadosHospede.setQuantidade(resultadoQuery.getString("Quantidade"));
                Log.w("valores do resultset", "Valor_Produto: "+ dadosHospede.getQuantidade());

                quantidadeH = Integer.parseInt(resultadoQuery.getString("Quantidade"));
                Log.w("valores do resultset", "Quantidade: "+ quantidadeH);

                vTotalH = quantidadeH * vProdutoH;
                dadosHospede.setValor_Total(String.valueOf(vTotalH));
                Log.w("valores do resultset", "Valor_Total: "+ vTotalH);

                totalFinalDespesas += vTotalH;
                dadosHospede.setTotal_Final_Despesas(String.valueOf(totalFinalDespesas));
                Log.w("TotalFinalDespesas: ", String.valueOf(totalFinalDespesas));

                listaDH.add(dadosHospede);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaDH;
    }

    public void RegistrarConsumoHospede(DadosHospede dados) {
        Connection conexaoBD6 = Conectar();

        String queryInsert = "INSERT INTO Consumos (Nome, CPF, Descricao, Valor_Produto, "+
                "Valor_Total, Quantidade, Data_Consumo) ";

        queryInsert+= "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement ppst = conexaoBD6.prepareStatement(queryInsert);

            ppst.setString(1, dados.getNome());
            ppst.setString(2, dados.getCPF());
            ppst.setString(3, dados.getDescricao());
            ppst.setString(4, dados.getValor_Produto());
            ppst.setString(5, dados.getValor_Total());
            ppst.setString(6, dados.getQuantidade());
            ppst.setString(7, dados.getData());

            ppst.executeQuery();
            ppst.close();
            conexaoBD6.close();
            Log.w("Query UPDATE", "Executada com sucesso, dados inseridos: " +
                    dados.getNome() + dados.getCPF() + dados.getDescricao() +
                    dados.getValor_Produto() + dados.getValor_Total() + dados.getQuantidade() +
                    dados.getData());
        }  catch (SQLException throwables) {
            Log.w("Query UPDATE", "DEU ERRO");
            throwables.printStackTrace();
        }
    }
}
