package com.example.rrsolucoeshotel.model;

public class ProdutosServicosHotel {

    private String tituloPS;
    private String valorPS;
    private String descricaoPS;

    public ProdutosServicosHotel(String tituloPS, String valorPS, String descricaoPS) {
        this.tituloPS = tituloPS;
        this.valorPS = valorPS;
        this.descricaoPS = descricaoPS;
    }

    public String getTitulo() {
        return tituloPS;
    }

    public void setTitulo(String tituloPS) {
        this.tituloPS = tituloPS;
    }

    public String getValor() {
        return valorPS;
    }

    public void setValor(String valorPS) {
        this.valorPS = valorPS;
    }

    public String getDescricao() {
        return descricaoPS;
    }

    public void setDescricao(String descricaoPS) {
        this.descricaoPS = descricaoPS;
    }
}
