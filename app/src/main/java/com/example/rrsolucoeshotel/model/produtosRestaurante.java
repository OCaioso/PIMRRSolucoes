package com.example.rrsolucoeshotel.model;

public class produtosRestaurante {

    private String tituloPrato;
    private String valorPrato;
    private String descricaoPrato;

    public produtosRestaurante(String tituloPrato, String valorPrato, String descricaoPrato) {
        this.tituloPrato = tituloPrato;
        this.valorPrato = valorPrato;
        this.descricaoPrato = descricaoPrato;
    }

    public String getTituloPrato() {
        return tituloPrato;
    }

    public void setTituloPrato(String tituloPrato) {
        this.tituloPrato = tituloPrato;
    }

    public String getValorPrato() {
        return valorPrato;
    }

    public void setValorPrato(String valorPrato) {
        this.valorPrato = valorPrato;
    }

    public String getDescricaoPrato() {
        return descricaoPrato;
    }

    public void setDescricaoPrato(String descricaoPrato) {
        this.descricaoPrato = descricaoPrato;
    }
}
