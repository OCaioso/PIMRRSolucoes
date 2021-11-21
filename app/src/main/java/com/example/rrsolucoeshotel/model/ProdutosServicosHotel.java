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

    public void setTituloPS(String tituloPS) {
        this.tituloPS = tituloPS;
    }

    public String getValor() {
        return valorPS;
    }

    public void setValorPS(String valorPS) {
        this.valorPS = valorPS;
    }

    public String getDescricao() {
        return descricaoPS;
    }

    public void setDescricaoPS(String descricaoPS) {
        this.descricaoPS = descricaoPS;
    }
}
