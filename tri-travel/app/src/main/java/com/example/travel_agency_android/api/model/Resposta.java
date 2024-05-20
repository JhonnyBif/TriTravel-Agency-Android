package com.example.travel_agency_android.api.model;

import java.io.Serializable;

public class Resposta implements Serializable {

    private boolean Sucesso;
    private int Dados;
    private String Mensagem;

    public Resposta() {

    }

    public boolean isSucesso() {
        return Sucesso;
    }

    public void setSucesso(boolean sucesso) {
        Sucesso = sucesso;
    }

    public int getDado() {
        return Dados;
    }

    public void setDado(int dado) {
        Dados = dado;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }
}
