package com.example.travel_agency_android.api.model;

import java.io.Serializable;

public class ViagemCustoRefeicao implements Serializable {

    private double custoRefeicao;
    private int refeicoesDia;

    public ViagemCustoRefeicao(double custoRefeicao, int refeicoesDia) {
        this.custoRefeicao = custoRefeicao;
        this.refeicoesDia = refeicoesDia;
    }

    public double getCustoRefeicao() {
        return custoRefeicao;
    }

    public void setCustoRefeicao(double custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }

    public int getRefeicoesDia() {
        return refeicoesDia;
    }
    public void setRefeicoesDia(int refeicoesDia) {
        this.refeicoesDia = refeicoesDia;
    }

}
