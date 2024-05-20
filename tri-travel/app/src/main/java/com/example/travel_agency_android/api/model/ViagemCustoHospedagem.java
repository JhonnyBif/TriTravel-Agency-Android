package com.example.travel_agency_android.api.model;

import java.io.Serializable;
import java.util.Date;

public class ViagemCustoHospedagem implements Serializable {

    private double custoMedioNoite;
    private int totalNoite;
    private int totalQuartos;

    public ViagemCustoHospedagem(double custoMedioNoite, int totalNoite, int totalQuartos) {
        this.custoMedioNoite = custoMedioNoite;
        this.totalNoite = totalNoite;
        this.totalQuartos = totalQuartos;
    }

    public double getCustoMedioNoite() {
        return custoMedioNoite;
    }

    public void setCustoMedioNoite(double custoMedioNoite) {
        this.custoMedioNoite = custoMedioNoite;
    }

    public int getTotalNoite() {
        return totalNoite;
    }

    public void setTotalNoite(int totalNoite) {
        this.totalNoite = totalNoite;
    }

    public int getTotalQuartos() {
        return totalQuartos;
    }

    public void setTotalQuartos(int totalQuartos) {
        this.totalQuartos = totalQuartos;
    }

}
