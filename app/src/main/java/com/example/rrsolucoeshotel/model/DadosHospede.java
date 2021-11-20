package com.example.rrsolucoeshotel.model;

import java.util.Date;

public class DadosHospede {

    private String Descricao;
    private double Valor_Produto, Valor_Total;
    private int Quantidade;
    private Date Data_Consumo;

    //private double Valor_Total = Quantidade * Valor_Produto;

    public DadosHospede(){

    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public void setValor_Produto(double valor_Produto) {
        Valor_Produto = valor_Produto;
    }

    public void setValor_Total(double valor_Total) {
        Valor_Total = valor_Total;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public void setData_Consumo(Date data_Consumo) {
        Data_Consumo = data_Consumo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public double getValor_Produto() {
        return Valor_Produto;
    }

    public double getValor_Total() {
        return Valor_Total;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public Date getData_Consumo() {
        return Data_Consumo;
    }


}
