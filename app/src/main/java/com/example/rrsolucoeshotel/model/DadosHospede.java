package com.example.rrsolucoeshotel.model;

public class DadosHospede {

    private int ID_Consumos;
    private String Nome, CPF, NomeProduto, Valor_Produto, Valor_Total, Quantidade, Data,
            Total_Final_Despesas;

    public DadosHospede(){

    }

    public int getID_Consumos() {
        return ID_Consumos;
    }

    public void setID_Consumos(int ID_Consumos) {
        this.ID_Consumos = ID_Consumos;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNomeProduto() {
        return NomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        NomeProduto = nomeProduto;
    }

    public String getValor_Produto() {
        return Valor_Produto;
    }

    public void setValor_Produto(String valor_Produto) {
        Valor_Produto = valor_Produto;
    }

    public String getValor_Total() {
        return Valor_Total;
    }

    public void setValor_Total(String valor_Total) {
        Valor_Total = valor_Total;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getTotal_Final_Despesas() {
        return Total_Final_Despesas;
    }

    public void setTotal_Final_Despesas(String total_Final_Despesas) {
        Total_Final_Despesas = total_Final_Despesas;
    }
}
