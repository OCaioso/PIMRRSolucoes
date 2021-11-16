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
