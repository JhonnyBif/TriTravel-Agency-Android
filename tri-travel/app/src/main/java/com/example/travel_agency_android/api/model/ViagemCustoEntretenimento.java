package com.example.travel_agency_android.api.model;

import java.io.Serializable;
import java.util.Date;

public class ViagemCustoEntretenimento implements Serializable {
    private String entretenimento;
    private double valor;

    public ViagemCustoEntretenimento(String entretenimento, double valor) {
        this.entretenimento = entretenimento;
        this.valor = valor;
    }

    public String getEntretenimento() {
        return entretenimento;
    }

    public void setEntretenimento(String entretenimento) {
        this.entretenimento = entretenimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
