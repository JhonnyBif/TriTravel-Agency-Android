package com.example.travel_agency_android.api.model;

import java.io.Serializable;
import java.util.Date;

public class ViagemCustoAereo implements Serializable {
    private double custoPessoa;
    private double custoAluguelVeiculo;

    public ViagemCustoAereo(double custoPessoa, double custoAluguelVeiculo) {
        this.custoPessoa = custoPessoa;
        this.custoAluguelVeiculo = custoAluguelVeiculo;
    }

    public double getCustoPessoa() {
        return custoPessoa;
    }

    public void setCustoPessoa(double custoPessoa) {
        this.custoPessoa = custoPessoa;
    }

    public double getCustoAluguelVeiculo() {
        return custoAluguelVeiculo;
    }

    public void setCustoAluguelVeiculo(double custoAluguelVeiculo) {
        this.custoAluguelVeiculo = custoAluguelVeiculo;
    }

}
