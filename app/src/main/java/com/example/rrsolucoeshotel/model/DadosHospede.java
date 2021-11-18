package com.example.rrsolucoeshotel.model;

import java.util.Date;

public class DadosHospede {

    private String Nome, CPF, Descricao;
    private double Valor_Produto, Valor_Total;
    private int Quarto, Quantidade;
    private Date Data_Consumo;

    public DadosHospede(){

    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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

    public void setQuarto(int quarto) {
        Quarto = quarto;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public void setData_Consumo(Date data_Consumo) {
        Data_Consumo = data_Consumo;
    }

    public String getNome() {
        return Nome;
    }

    public String getCPF() {
        return CPF;
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

    public int getQuarto() {
        return Quarto;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public Date getData_Consumo() {
        return Data_Consumo;
    }
}
